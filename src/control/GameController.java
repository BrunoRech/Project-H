package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import control.abstractFactory.Spawner;
import control.abstractFactory.VersaoBasica;
import control.builder.Director;
import control.builder.TabuleiroBuilder;
import control.builder.TabuleiroPadraoBuilder;
import control.command.CommandInvoker;
import control.command.MoverVitoriasRegiasParaCimaCommand;
import exception.CampoInvalidoException;
import exception.MovimentoInvalidoException;
import exception.NenhumCampoSelecionadoException;
import exception.SemFloresNoMonteException;
import model.Agua;
import model.FlorAmarela;
import model.FlorVermelha;
import model.GameObject;
import model.Tabuleiro;
import model.TabuleiroPadrao;
import model.VitoriaRegia;
import model.Vr_Clara;
import model.Vr_ClaraOvas;
import model.Vr_ClaraOvasAmarelas;
import model.Vr_ClaraOvasVermelhas;
import model.Vr_Escura;

//classe do controlador do game
public class GameController implements InterfaceController {

	private static GameController instance;
	private Tabuleiro tabuleiro;
	private Spawner spawner;

	private Director director;
	private TabuleiroBuilder builder;

	private int indexX, indexY;
	// guarda a posição da ação anterior caso o usuário der desfazer
	private int executedX, executedY;

	private int valorAmarelo = -1;
	private int valorVermelho = -1;
	private boolean ventoIsPressed;
	private boolean teveEmpate;
	private boolean moveuVr;
	private boolean virouVr;
	private String corSapoRemovido;
	private String jogadorDaRodada = "amarelo";
	private String vencedorDaRodada = "";
	private Random r = new Random();

	private List<Observador> observadores = new ArrayList<>();
	private List<FlorAmarela> monteAmarelo = new ArrayList<>();
	private List<FlorVermelha> monteVermelho = new ArrayList<>();

	private List<FlorAmarela> maoAmarelo = new ArrayList<>();
	private List<FlorVermelha> maoVermelho = new ArrayList<>();

	//singleton
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	//construtor
	private GameController() {
		this.spawner = new VersaoBasica();// se tivesse outra versão o usuario escolheria e atribuiria aqui
		this.builder = new TabuleiroPadraoBuilder();
		this.director = new Director(this.builder);
	}

	@Override
	// adiciona o observador da lista de observadores
	//@param Observador obs, é o observador que irá ser adicionado na lista de observadores
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	@Override
	// cria o tabuleiro
	public void inicializarTabuleiro() {
		director.construir();
		tabuleiro = this.builder.getTabuleiro();
		ventoIsPressed = false;
		indexX = -1;
		indexY = -1;
	}

	@Override
	// pega o icone de cada tile do tabuleiro
	//@param col,row os index da peça cujo o sistema quer o Icon
	//@returns Icon, o icone da peça do index escolhido
	public Icon getPeca(int col, int row) {
		return (tabuleiro.getElementAt(col, row) == null ? null
				: new ImageIcon(tabuleiro.getElementAt(col, row).getImagem()));
	}

	@Override
	// embaralha as 8 flores de cada monte de flores de cada jogador
	public void embaralharMontes() {
		for (int i = 0; i < 8; i++) {
			// TODO botar isso em um factory
			monteAmarelo.add((FlorAmarela) spawner.spawnFlorAmarela(i + 1));
			monteVermelho.add((FlorVermelha) spawner.spawnFlorVermelha(i + 1));
		}
		pescar(3);
	}

	@Override
	// pesca as flores do monte e adiciona na mao dos jogadores
	// @param quantidade, o número de flores que os jogadores vão pescar
	public void pescar(int quantidade) {
		int[] valoresAmarelo = new int[3];
		int[] valoresVermelho = new int[3];

		if (monteAmarelo.size() > 0 || monteVermelho.size() > 0) {
			for (int i = 0; i < quantidade; i++) {
				maoAmarelo.add(monteAmarelo.remove(r.nextInt(monteAmarelo.size())));
				maoVermelho.add(monteVermelho.remove(r.nextInt(monteVermelho.size())));
			}
			for (int i = 0; i < 3; i++) {
				valoresAmarelo[i] = maoAmarelo.get(i).getNumero();
				valoresVermelho[i] = maoVermelho.get(i).getNumero();
			}

			if (jogadorDaRodada.equalsIgnoreCase("amarelo")) {
				for (Observador obs : observadores) {
					obs.notificarJogadorPescou(valoresAmarelo);
					obs.notificarIconesAmarelos();
				}
			} else {
				for (Observador obs : observadores) {
					obs.notificarJogadorPescou(valoresVermelho);
					obs.notificarIconesVermelhos();
				}
			}

		}

	}

	@Override
	// recebe as flores escolhidas de cada jogador e verifica quem ganhou
	//@param valor, o valor da flor que o usuário escolheu dentre as 3 disponíveis na mão de cada jogado
	public void escolherFlor(int valor) {
		teveEmpate = false;
		if (jogadorDaRodada.equalsIgnoreCase("amarelo")) {
			valorAmarelo = valor;
		} else {
			valorVermelho = valor;
		}

		if (valorAmarelo == -1 || valorVermelho == -1) {
			mudarJogador();
		} else {
			VitoriaRegia vr;
			int[] pos = encontrarVrEscura();
			if (valorAmarelo > valorVermelho) {
				vencedorDaRodada = "amarelo";
				vr = (VitoriaRegia) tabuleiro.getElementAt(pos[0], pos[1]);
				vr.adicionarFlorVermelha();
			} else if (valorAmarelo < valorVermelho) {
				vencedorDaRodada = "vermelho";
				vr = (VitoriaRegia) tabuleiro.getElementAt(pos[0], pos[1]);
				vr.adicionarFlorAmarela();
			} else {
				System.out.println("empate");
				teveEmpate = true;
				monteAmarelo.add((FlorAmarela) spawner.spawnFlorAmarela(valorAmarelo));
				monteVermelho.add((FlorVermelha) spawner.spawnFlorVermelha(valorVermelho));
			}
			removerFlorDaMao();
			pescar(1);
			valorAmarelo = -1;
			valorVermelho = -1;

			for (Observador obs : observadores) {
				obs.notificarAdicionarFlorHabilitado();
				obs.notificarSelecaoTabuleiroAprovada();
				obs.notificarSelecaoFlorIndisponivel();
				if (teveEmpate) {
					obs.notificarEmpateFlor();
					obs.notificarVentoDisponivel();
				}

			}

		}

	}

	@Override
	// vira a flor escolhida "de cabeça para baixo"
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void virarFlor(String action) {

		try {
			validarUndo(action);
			if (conferirIndex()) {

				if (tabuleiro.getElementAt(indexY, indexX).getClass() != Agua.class
						&& tabuleiro.getElementAt(indexY, indexX).getClass() != Vr_Escura.class) {
					Vr_Clara vr = (Vr_Clara) tabuleiro.getElementAt(indexY, indexX);
					if (vr.hasFlor()) {
						throw new CampoInvalidoException();
					} else {
						if (vr.hasSapo()) {
							corSapoRemovido = vr.removerSapo();
							notificarSapoRemovido();
						}
						vr.virar();
						notificarMudancaTabuleiro();
						virouVr = true;
						validarExecute(action);
						comecarNovaRodada();
						for (Observador obs : observadores) {
							obs.notificarFlorVirada();
						}
					}
				} else {
					throw new CampoInvalidoException();
				}
			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			for (Observador obs : observadores) {
				obs.notificarVirarFlorHabilitada();
				obs.notificarSelecaoTabuleiroAprovada();
			}
		}

	}

	// inicia uma nova rodada
	private void comecarNovaRodada() {
		ventoIsPressed = false;
		if (teveEmpate) {
			if (moveuVr) {
				for (Observador obs : observadores) {
					obs.notificarSelecaoFlorDisponivel();
					obs.notificarSelecaoTabuleiroReprovada();
				}
				virouVr = false;
				moveuVr = false;
			}
		} else {
			if (virouVr && moveuVr) {
				for (Observador obs : observadores) {
					obs.notificarSelecaoFlorDisponivel();
					obs.notificarSelecaoTabuleiroReprovada();
				}
				virouVr = false;
				moveuVr = false;
				try {
					if ((monteAmarelo.size() == 0 && maoAmarelo.size() <= 2) || (monteVermelho.size() == 0  && maoVermelho.size() <= 2)) {
						limparMesa();
						throw new SemFloresNoMonteException();
					}
				} catch (Exception e) {
					System.out.println("Sem flores nos montes");
				}

			} else {
				for (Observador obs : observadores) {
					obs.notificarSelecaoTabuleiroAprovada();
				}
			}
		}

	}

	@Override
	// quando o usuario clicar em 1 tile do tabuleiro esse método atualiza o
	// ponteiro da coordenada x e y
	public void atualizarIndexFlor(int x, int y) {
		if (x >= 0 && y >= 0) {
			this.indexX = x;
			this.indexY = y;
		}
	}

	// notifica a flor adicionada na view
	public void notificarFlorAdicionada() {
		for (Observador obs : observadores)
			obs.notificarFlorAdicionada();
	}

	// notifica o sapo adicionado na view
	public void notificarSapoAdicionado() {
		validarVento();
		for (Observador obs : observadores) {
			obs.notificarSapoAdicionado();
			obs.notificarMudouTabuleiro();
		}
	}

	// confere o index para ver se ele é válido
	//@return retorna se o index selecionado pelo usuário é válido
	public boolean conferirIndex() throws NenhumCampoSelecionadoException {
		if (this.indexX == -1 || indexY == -1) {
			throw new NenhumCampoSelecionadoException();

		} else {
			return true;
		}
	}

	@Override
	// adiciona uma flor em cima de uma vitória régia se ela estiver livre
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void adicionarFlor(String action) {
		try {
			validarUndo(action);
			if (conferirIndex()) {
				Vr_Clara vr = (Vr_Clara) tabuleiro.getElementAt(indexY, indexX);

				if (!vr.hasFlor()) {
					if (vr.hasSapo()) {
						corSapoRemovido = vr.removerSapo();
						notificarSapoRemovido();
					} else {
						for (Observador obs : observadores) {
							obs.notificarVentoDisponivel();
						}
					}
					if (vencedorDaRodada.equalsIgnoreCase("amarelo")) {
						vr.adicionarFlorAmarela();
					} else if (vencedorDaRodada.equalsIgnoreCase("vermelho")) {
						vr.adicionarFlorVermelha();
					}
					notificarFlorAdicionada();
					validarExecute(action);
					validarVento();
				} else {
					throw new CampoInvalidoException();
				}

				notificarMudancaTabuleiro();
			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			e.printStackTrace();
			for (Observador obs : observadores) {
				obs.notificarAdicionarFlorHabilitado();
				obs.notificarSelecaoTabuleiroAprovada();
			}
		}
	}

	@Override
	// adiciona um sapo em cima de uma vitória régia se ela estiver livre
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void adicionarSapo(String action) {
		try {
			validarUndo(action);

			if (conferirIndex() && (tabuleiro.getElementAt(indexY, indexX).getClass() == Vr_Clara.class)
					|| tabuleiro.getElementAt(indexY, indexX).getClass() == Vr_ClaraOvasAmarelas.class
					|| tabuleiro.getElementAt(indexY, indexX).getClass() == Vr_ClaraOvasVermelhas.class) {
				Vr_Clara vr = (Vr_Clara) tabuleiro.getElementAt(indexY, indexX);
				if (!vr.hasSapo() && !vr.hasFlor() && !vr.isVirada()) {
					if (corSapoRemovido.equalsIgnoreCase("amarelo")) {
						vr.adicionarSapoAmarelo();
					} else if (corSapoRemovido.equalsIgnoreCase("vermelho")) {
						vr.adicionarSapoVermelho();
					}
					notificarSapoAdicionado();
					validarExecute(action);
					validarVento();
				} else {
					throw new CampoInvalidoException();
				}

			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			e.printStackTrace();
			for (Observador obs : observadores) {
				obs.notificarSapoHabilitado();
				obs.notificarSelecaoTabuleiroAprovada();
			}
		}

	}

	@Override
	// limpa a mesa e deixa ela como no início do jogo, sem mudar as suas posições
	public void limparMesa() {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				if (tabuleiro.getElementAt(j, i).getClass() != Agua.class) {
					VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(j, i);
					vr.reset();
				}

			}

			for (Observador obs : observadores) {
				obs.notificarMudouTabuleiro();
				obs.notificarSelecaoFlorDisponivel();
			}

			embaralharMontes();
		}

	}

	@Override
	// notifica a view que começou o vento da primavera
	public void ventoDaPrimavera() {
		ventoIsPressed = true;
		for (Observador obs : observadores) {
			obs.notificarMovimentacaoHabilitada();
			obs.notificarSelecaoTabuleiroAprovada();
			if (!teveEmpate) {
				obs.notificarVirarFlorHabilitada();
			}
		}
	}

	@Override
	// remove a flor da mão de cada jogador depois de ambos escolherem 1 para a disputa na rodada
	public void removerFlorDaMao() {
		for (int i = 0; i < maoAmarelo.size(); i++) {
			if (maoAmarelo.get(i).getNumero() == valorAmarelo) {
				maoAmarelo.remove(i);
			}
		}

		for (int i = 0; i < maoVermelho.size(); i++) {
			if (maoVermelho.get(i).getNumero() == valorVermelho) {
				maoVermelho.remove(i);
			}
		}
		valorAmarelo = -1;
		valorVermelho = -1;

	}

	// notifica a view que mudou algumas peças no tabuleiro
	private void notificarMudancaTabuleiro() {
		for (Observador obs : observadores) {
			obs.notificarMudouTabuleiro();
		}
	}

	// notifica a view que um sapo foi removido de uma vitória régia
	private void notificarSapoRemovido() {
		for (Observador obs : observadores) {
			obs.notificarSapoHabilitado();
			obs.notificarSelecaoTabuleiroAprovada();
			obs.notificarVentoIndisponivel();
		}

	}

	// encontra uma vitória régia escura vaga para adicionar a flor do perdedor da rodada
	//@return retorna a posição x e y da vitória régia escura
	private int[] encontrarVrEscura() {
		VitoriaRegia vr;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tabuleiro.getElementAt(j, i).getClass() == Vr_Escura.class) {
					vr = (VitoriaRegia) tabuleiro.getElementAt(j, i);
					if (!vr.hasFlor()) {
						int[] pos = { j, i };
						return pos;
					}

				} else if (tabuleiro.getElementAt(j, i).getClass() == Vr_Clara.class 
						|| tabuleiro.getElementAt(j, i).getClass() == Vr_ClaraOvasVermelhas.class
						|| tabuleiro.getElementAt(j, i).getClass() == Vr_ClaraOvasAmarelas.class) {
					vr = (VitoriaRegia) tabuleiro.getElementAt(j, i);
					if (vr.isVirada() && !vr.hasFlor()) {
						int[] pos = { j, i };
						return pos;
					}
				}
			}
		}

		return null;
	}

	// método utilizado para evitar que o botão do vento fique disponível depois de um redo e quebre o jogo
	private void validarVento() {
		if (ventoIsPressed) {
			for (Observador obs : observadores)
				obs.notificarVentoIndisponivel();
		}else {
			for (Observador obs : observadores)
				obs.notificarVentoDisponivel();
		}
	}

	@Override
	// método que verifica as posições das peças no tabuleiro para aplicar as pontuações no fim de cada rodada
	// OBS: Método requerido apenas na SEGUNDA entrega
	//@param cor, a cor do jogador que será observado os padrões das peças para saber em quem aplicar a pontuação
	public void verificarPadroes(String cor) {
		// boolean achou;
		// coluna esquerda
		// linha direita
		VitoriaRegia go;
		if (cor.equalsIgnoreCase("amarelo")) {
			go = new Vr_Clara();

		} else {

		}
		// padrao 2x2
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - 1; j++) {

				/*ver
				 * if (tabuleiro[j][i].getClass() != Agua.class) { // é p funcionar
				 * 
				 * go = tabuleiro[j][i]; if (tabuleiro[j][i].getClass() ==
				 * Vr_ClaraFlorAmarela.class && tabuleiro[j + 1][i].getClass() ==
				 * Vr_ClaraFlorAmarela.class && tabuleiro[j][i + 1].getClass() ==
				 * Vr_ClaraFlorAmarela.class && tabuleiro[j + 1][i + 1].getClass() ==
				 * Vr_ClaraFlorAmarela.class) { }
				 */
			}

		}

		// padrao 1x4
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				/*
				 * if (tabuleiro[j][i].getClass() == Vr_ClaraFlorAmarela.class && tabuleiro[j +
				 * 1][i].getClass() == Vr_ClaraFlorAmarela.class && tabuleiro[j +
				 * 2][i].getClass() == Vr_ClaraFlorAmarela.class && tabuleiro[j +
				 * 3][i].getClass() == Vr_ClaraFlorAmarela.class) { }
				 */
			}
		}

		// padrao diagonal 4

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
			}

		}

		// padrao diagonal 5

		/*
		 * if (tabuleiro[0][4].getClass() == Vr_ClaraFlorAmarela.class &&
		 * tabuleiro[1][3].getClass() == Vr_ClaraFlorAmarela.class &&
		 * tabuleiro[2][2].getClass() == Vr_ClaraFlorAmarela.class &&
		 * tabuleiro[3][1].getClass() == Vr_ClaraFlorAmarela.class &&
		 * tabuleiro[4][0].getClass() == Vr_ClaraFlorAmarela.class) {
		 * 
		 * }
		 */

	}

	@Override
	// remove a flor de uma vitória régia
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void removerFlor(String action) {
		validarUndo(action);
		Vr_Clara vr = (Vr_Clara) tabuleiro.getElementAt(indexY, indexX);
		vr.removerFlor();
		if (vr.getClass() == Vr_ClaraOvasAmarelas.class || vr.getClass() == Vr_ClaraOvasVermelhas.class) {
			if (corSapoRemovido.equalsIgnoreCase("vermelho")) {
				vr.adicionarSapoVermelho();
			} else {
				vr.adicionarSapoAmarelo();
			}
			for (Observador obs : observadores) {
				obs.notificarSapoAdicionado();
				
			}
		}

		for (Observador obs : observadores) {
			obs.notificarAdicionarFlorHabilitado();
			obs.notificarSelecaoTabuleiroAprovada();
			obs.notificarMudouTabuleiro();
			obs.notificarVentoIndisponivel();
		}
		notificarMudancaTabuleiro();
	}

	@Override
	// remove um sapo de uma vitória régia
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void removerSapo(String action) {
		validarUndo(action);
		VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(indexY, indexX);
		vr.removerSapo();
		for (Observador obs : observadores) {
			obs.notificarMudouTabuleiro();
			obs.notificarSapoHabilitado();
			obs.notificarVentoIndisponivel();
		}
	}

	@Override
	// desvira uma flor que inicialmente tinha a face virada para cima
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void desvirarFlor(String action) {
		validarUndo(action);
		VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(indexY, indexX);
		vr.desvirar();
		for (Observador obs : observadores) {
			obs.notificarMudouTabuleiro();
			obs.notificarVirarFlorHabilitada();
		}
	}

	@Override
	// muda o jogador que a view está apresentando ao usuário
	public void mudarJogador() {
		int[] valores = new int[3];

		if (jogadorDaRodada.equalsIgnoreCase("amarelo")) {
			jogadorDaRodada = "vermelho";
			for (int i = 0; i < maoVermelho.size(); i++) {
				valores[i] = maoVermelho.get(i).getNumero();
			}
		} else {
			jogadorDaRodada = "amarelo";
			for (int i = 0; i < maoAmarelo.size(); i++) {
				valores[i] = maoAmarelo.get(i).getNumero();
			}
		}

		if (jogadorDaRodada.equalsIgnoreCase("amarelo")) {
			for (Observador obs : observadores) {
				obs.notificarIconesAmarelos();
				obs.notificarJogadorPescou(valores);
			}
		} else {
			for (Observador obs : observadores) {
				obs.notificarIconesVermelhos();
				obs.notificarJogadorPescou(valores);
			}
		}

	}

	@Override
	// move a peça selecionada um quadrado para cima se estiver espaço
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void moverPecasTabuleiroParaCima(String action) {
		try {
			validarUndo(action);
			int coordX = -1;
			for (int i = indexX; i > 0; i--) {
				if (tabuleiro.getElementAt(indexY, i - 1).getClass() == Agua.class) {
					coordX = i - 1;
					break;
				}
			}

			if (coordX == -1) {

				throw new MovimentoInvalidoException();

			} else {
				GameObject aux = tabuleiro.getElementAt(indexY, coordX);
				for (int i = coordX; i < indexX; i++) {
					tabuleiro.setElementAt(tabuleiro.getElementAt(indexY, i + 1), indexY, i);
				}

				tabuleiro.setElementAt(aux, indexY, indexX);
				indexX = coordX;
				validarExecute(action);

			}
			validarAction(action);
			notificarMudancaTabuleiro();
			moveuVr = true;
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoHabilitada();
		}
	}

	@Override
	// move a peça selecionada um quadrado para baixo se estiver espaço
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void moverPecasTabuleiroParaBaixo(String action) {
		try {
			validarUndo(action);
			int coordX = -1;
			for (int i = indexX; i < 4; i++) {
				if (tabuleiro.getElementAt(indexY, i + 1).getClass() == Agua.class) {
					coordX = i + 1;
					break;
				}
			}

			if (coordX == -1) {

				throw new MovimentoInvalidoException();

			} else {
				GameObject aux = tabuleiro.getElementAt(indexY, coordX);
				for (int i = coordX; i > indexX; i--) {
					tabuleiro.setElementAt(tabuleiro.getElementAt(indexY, i - 1), indexY, i);
				}

				tabuleiro.setElementAt(aux, indexY, indexX);
				indexX = coordX;
				validarExecute(action);

			}
			validarAction(action);
			notificarMudancaTabuleiro();
			moveuVr = true;
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoHabilitada();
		}
	}

	@Override
	// move a peça selecionada um quadrado para a esquerda se estiver espaço
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void moverPecasTabuleiroParaEsquerda(String action) {
		try {
			validarUndo(action);
			int coordY = -1;
			for (int i = indexY; i > 0; i--) {
				if (tabuleiro.getElementAt(i - 1, indexX).getClass() == Agua.class) {
					coordY = i - 1;
					break;
				}
			}

			if (coordY == -1) {

				throw new MovimentoInvalidoException();

			} else {
				GameObject aux = tabuleiro.getElementAt(coordY, indexX);
				for (int i = coordY; i < indexY; i++) {
					tabuleiro.setElementAt(tabuleiro.getElementAt(i + 1, indexX), i, indexX);
				}

				tabuleiro.setElementAt(aux, indexY, indexX);
				indexY = coordY;
				validarExecute(action);
			}
			validarAction(action);
			notificarMudancaTabuleiro();
			moveuVr = true;
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoHabilitada();
		}
	}

	@Override
	// move a peça selecionada um quadrado para a direita se estiver espaço
	//@param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é tratado diferente
	public void moverPecasTabuleiroParaDireita(String action) {
		try {
			validarUndo(action);
			int coordY = -1;
			for (int i = indexY; i < 4; i++) {
				if (tabuleiro.getElementAt(i + 1, indexX).getClass() == Agua.class) {
					coordY = i + 1;
					break;
				}
			}

			if (coordY == -1) {

				throw new MovimentoInvalidoException();

			} else {
				GameObject aux = tabuleiro.getElementAt(coordY, indexX);
				for (int i = coordY; i > indexY; i--) {
					tabuleiro.setElementAt(tabuleiro.getElementAt(i - 1, indexX), i, indexX);
				}

				tabuleiro.setElementAt(aux, indexY, indexX);
				indexY = coordY;
				validarExecute(action);
			}

			validarAction(action);
			notificarMudancaTabuleiro();
			moveuVr = true;
			comecarNovaRodada();

		} catch (MovimentoInvalidoException e) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoHabilitada();
		}
	}

	/*
	 * verifica se o comando está sendo executado pela primeira vez ou é um
	 * undo/redo de outro comando e toma as devidas providências apenas utilizado
	 * nos métodos de movimento das vitórias régias
	 * 
	 * @param action, o tipo da ação, se é um execute, desfazer ou refazer cada um é
	 * tratado diferente
	 */
	private void validarAction(String action) {
		if (action.equalsIgnoreCase("undo")) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoHabilitada();
		} else if (action.equalsIgnoreCase("redo")) {
			for (Observador obs : observadores)
				obs.notificarMovimentacaoDesabilitada();
		} else if (action.equalsIgnoreCase("execute")) {
			for (Observador obs : observadores)
				obs.notificarExecute();
		}
	}

	@Override
	// guarda a coordenada da ação caso o usuário peça um undo
	//@param action, o tipo da ação, se é um execute ou refazer
	public void validarExecute(String action) {
		if (action.equalsIgnoreCase("execute") || action.equalsIgnoreCase("redo")) {
			executedX = indexX;
			executedY = indexY;
		}

	}

	@Override
	// caso o comando seja um undo ele utiliza as coordenadas da ultima ação do ultimo comando
	//@param action, o tipo da ação, se é um refazer
	public void validarUndo(String action) {
		if (action.equalsIgnoreCase("undo")) {
			indexX = executedX;
			indexY = executedY;
		}

	}

}
