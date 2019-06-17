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
import control.state.SelecionarFloresState;
import control.strategy.Finder1x4;
import control.strategy.Finder2x2;
import control.strategy.FinderDiagonal4;
import control.strategy.FinderDiagonal5;
import control.visitor.Pattern_Visitor;
import exception.CampoInvalidoException;
import exception.MovimentoInvalidoException;
import exception.NenhumCampoSelecionadoException;
import exception.SemFloresNoMonteException;
import model.Agua;
import model.FlorAmarela;
import model.FlorVermelha;
import model.GameObject;
import model.Tabuleiro;
import model.VitoriaRegia;
import model.VitoriaRegiaComponent;
import model.Vr_Escura;
import model.composite.Flor_Composite;
import model.decorator.EscurecerDecorator;
import model.decorator.FlorAmarelaDecorator;
import model.decorator.FlorVermelhaDecorator;
import model.decorator.OvasAmarelasDecorator;
import model.decorator.OvasVermelhasDecorator;
import model.decorator.SapoAmareloDecorator;
import model.decorator.SapoVermelhoDecorator;
import model.decorator.VitoriaRegiaDecorator;

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
	private boolean sapoMovido, houveUndo, houvePontuador, houveEmpate;
	private String corSapoRemovido;
	private String jogadorDaRodada = "amarelo";
	private String vencedorDaRodada = "";
	private Random r = new Random();

	private int pontuacaoAmarelo = 0;
	private int pontuacaoVermelho = 0;

	private List<Observador> observadores = new ArrayList<>();
	private Pattern_Visitor visitor;
	private Flor_Composite monteAmarelo, monteVermelho, maoAmarelo, maoVermelho, ladoAmarelo, ladoVermelho;

	// singleton
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	// construtor
	private GameController() {
		this.spawner = new VersaoBasica();// se tivesse outra versão o usuario escolheria e atribuiria aqui
		this.builder = new TabuleiroPadraoBuilder();
		this.director = new Director(this.builder);
		this.visitor = new Pattern_Visitor();
		this.ladoAmarelo = new Flor_Composite();
		this.ladoVermelho = new Flor_Composite();
		this.maoAmarelo = new Flor_Composite();
		this.maoVermelho = new Flor_Composite();
		this.monteAmarelo = new Flor_Composite();
		this.monteVermelho = new Flor_Composite();

		this.ladoAmarelo.add(this.maoAmarelo);
		this.ladoAmarelo.add(this.monteAmarelo);
		this.ladoVermelho.add(this.maoVermelho);
		this.ladoVermelho.add(this.monteVermelho);
	}

	@Override
	// adiciona o observador da lista de observadores
	// @param Observador obs, é o observador que irá ser adicionado na lista de
	// observadores
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	@Override
	// cria o tabuleiro
	public void inicializarTabuleiro() {
		director.construir();
		tabuleiro = this.builder.getTabuleiro();
		indexX = -1;
		indexY = -1;

	}

	@Override
	// pega o icone de cada tile do tabuleiro
	// @param col,row os index da peça cujo o sistema quer o Icon
	// @returns Icon, o icone da peça do index escolhido
	public Icon getPeca(int col, int row) {
		return (tabuleiro.getElementAt(col, row) == null ? null
				: new ImageIcon(tabuleiro.getElementAt(col, row).getImagem()));
	}

	@Override
	// embaralha as 8 flores de cada monte de flores de cada jogador
	public void embaralharMontes() {
		monteAmarelo.clear();
		monteVermelho.clear();

		for (int i = 0; i < 8; i++) {
			monteAmarelo.add((FlorAmarela) spawner.spawnFlorAmarela(i + 1));
			monteVermelho.add((FlorVermelha) spawner.spawnFlorVermelha(i + 1));
		}
		maoAmarelo.clear();
		maoVermelho.clear();
		pescar(3);
	}

	@Override
	// pesca as flores do monte e adiciona na mao dos jogadores
	// @param quantidade, o número de flores que os jogadores vão pescar
	public void pescar(int quantidade) {
		int[] valoresAmarelo = new int[3];
		int[] valoresVermelho = new int[3];

		if (monteAmarelo.size() > 0 && monteVermelho.size() > 0) {
			for (int i = 0; i < quantidade; i++) {
				maoAmarelo.add(monteAmarelo.remove(r.nextInt(monteAmarelo.size())));
				maoVermelho.add(monteVermelho.remove(r.nextInt(monteVermelho.size())));
			}
		}
		for (int i = 0; i < maoAmarelo.size(); i++) {
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

	@Override
	// recebe as flores escolhidas de cada jogador e verifica quem ganhou
	// @param valor, o valor da flor que o usuário escolheu dentre as 3
	// disponíveis na mão de cada jogado
	public void escolherFlor(int valor) {

		if (jogadorDaRodada.equalsIgnoreCase("amarelo")) {
			valorAmarelo = valor;
		} else {
			valorVermelho = valor;
		}

		if (valorAmarelo == -1 || valorVermelho == -1) {
			mudarJogador();
		} else {
			sapoMovido = true;
			houveUndo = false;
			houveEmpate = false;
			VitoriaRegia vr;
			int[] pos = encontrarVrEscura();
			if (valorAmarelo > valorVermelho) {
				vencedorDaRodada = "amarelo";
				vr = (VitoriaRegia) tabuleiro.getElementAt(pos[0], pos[1]);
				tabuleiro.setElementAt(new FlorVermelhaDecorator(vr), pos[0], pos[1]);
			} else if (valorAmarelo < valorVermelho) {
				vencedorDaRodada = "vermelho";
				vr = (VitoriaRegia) tabuleiro.getElementAt(pos[0], pos[1]);
				tabuleiro.setElementAt(new FlorAmarelaDecorator(vr), pos[0], pos[1]);
			} else {// empate
				houveEmpate = true;
				for (Observador obs : observadores) {
					obs.notificarEmpateFlor();
				}
			}
			removerFlorDaMao();
			pescar(1);
			valorAmarelo = -1;
			valorVermelho = -1;
			if (!houveEmpate) {
				nextState();
			}
		}

	}

	@Override
	// vira a flor escolhida "de cabeça para baixo"
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void virarFlor() {

		try {
			if (conferirIndex()) {
				VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(indexY, indexX);
				if (!vr.isVirada() && !vr.isHasFlor()) {

					if (vr.isHasSapo()) {
						corSapoRemovido = vr.getSapo();
						vr = ((VitoriaRegiaDecorator) tabuleiro.getElementAt(indexY, indexX)).getVr();
						sapoMovido = false;
						sapoState(houveEmpate);
					}
					tabuleiro.setElementAt(new EscurecerDecorator(vr), indexY, indexX);
					executedX = indexX;
					executedY = indexY;
					notificarMudancaTabuleiro();
					comecarNovaRodada();

				} else {
					throw new CampoInvalidoException();
				}
			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			reloadState();
		}

	}

	// inicia uma nova rodada
	private void comecarNovaRodada() {
		if (sapoMovido && !houveUndo) {
			try {
				houvePontuador = false;
				verificarPadroes(1);
				if (!houvePontuador) {
					nextState();
					if (ladoAmarelo.size() == 0 && ladoVermelho.size() == 0) {
						limparMesa();
						throw new SemFloresNoMonteException();
					}
				}
			} catch (Exception e) {
			}
		} else if (houveUndo) {
			previousState();
		}
	}

	@Override
	// quando o usuario clicar em 1 tile do tabuleiro esse método atualiza o
	// ponteiro da coordenada x e y
	public void atualizarIndexFlor(int x, int y) {
		if (x >= 0 && y >= 0) {
			this.indexX = x;
			this.indexY = y;
			System.out.println(tabuleiro.getElementAt(y, x).getImagem());
		}
	}

	// confere o index para ver se ele é válido
	// @return retorna se o index selecionado pelo usuário é válido
	public boolean conferirIndex() throws NenhumCampoSelecionadoException, CampoInvalidoException {
		if (this.indexX == -1 || indexY == -1) {
			throw new NenhumCampoSelecionadoException();
		} else if (tabuleiro.getElementAt(indexY, indexX).getClass() == Agua.class) {
			throw new CampoInvalidoException();
		}
		return true;
	}

	@Override
	// adiciona uma flor em cima de uma vitória régia se ela estiver livre
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void adicionarFlor() {
		try {
			if (conferirIndex()) {
				VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(indexY, indexX);

				if (!vr.isHasFlor() && !vr.isVirada()) {
					if (vr.isHasSapo()) {
						corSapoRemovido = vr.getSapo();
						vr = ((VitoriaRegiaDecorator) tabuleiro.getElementAt(indexY, indexX)).getVr();
						sapoMovido = false;
						sapoState(houveEmpate);
					} else {
						nextState();
					}
					if (vencedorDaRodada.equalsIgnoreCase("amarelo")) {
						tabuleiro.setElementAt(new FlorAmarelaDecorator(vr), indexY, indexX);
					} else if (vencedorDaRodada.equalsIgnoreCase("vermelho")) {
						tabuleiro.setElementAt(new FlorVermelhaDecorator(vr), indexY, indexX);
					}
					executedX = indexX;
					executedY = indexY;
					houveUndo = false;
				} else {
					throw new CampoInvalidoException();
				}

				notificarMudancaTabuleiro();
			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			reloadState();
		}
	}

	@Override
	// adiciona um sapo em cima de uma vitória régia se ela estiver livre
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void adicionarSapo() {
		try {

			if (conferirIndex()) {
				VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(indexY, indexX);
				if (!vr.isHasSapo() && !vr.isHasFlor() && !vr.isVirada()) {
					if (corSapoRemovido.equalsIgnoreCase("amarelo")) {
						tabuleiro.setElementAt(new SapoAmareloDecorator(vr), indexY, indexX);
					} else if (corSapoRemovido.equalsIgnoreCase("vermelho")) {
						tabuleiro.setElementAt(new SapoVermelhoDecorator(vr), indexY, indexX);
					}
					System.out.println(houveEmpate);
					if (houveEmpate) {
						empate("vermelho");
					} else {
						sapoMovido = true;
						houveUndo = false;
						comecarNovaRodada();
						notificarMudancaTabuleiro();
					}

				} else {
					throw new CampoInvalidoException();
				}

			}
		} catch (NenhumCampoSelecionadoException | CampoInvalidoException e) {
			reloadState();
		}

	}

	@Override
	// limpa a mesa e deixa ela como no início do jogo, sem mudar as suas
	// posições
	public void limparMesa() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tabuleiro.getElementAt(j, i).getClass() != Agua.class) {
					VitoriaRegia vr = ((VitoriaRegia) tabuleiro.getElementAt(j, i)).reset();
					System.out.println(vr.getImagem());
					if (vr.getClass() == OvasAmarelasDecorator.class) {
						tabuleiro.setElementAt(new SapoAmareloDecorator(vr), j, i);
					} else if (vr.getClass() == OvasVermelhasDecorator.class) {
						tabuleiro.setElementAt(new SapoVermelhoDecorator(vr), j, i);
					} else {
						tabuleiro.setElementAt(vr, j, i);
					}
				}

			}
		}
		embaralharMontes();
		for (Observador obs : observadores) {
			obs.setState(new SelecionarFloresState(obs));
			obs.notificarMudouTabuleiro();
		}

	}

	@Override
	// notifica a view que começou o vento da primavera
	public void ventoDaPrimavera() {
		if (tabuleiroCheio()) {
			removerSaposTabuleiro();
		}
		houvePontuador = false;
		verificarPadroes(1);
		if (!houvePontuador) {
			nextState();
		}
	}

	@Override
	// remove a flor da mão de cada jogador depois de ambos escolherem 1 para a
	// disputa na rodada
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

	// encontra uma vitória régia escura vaga para adicionar a flor do perdedor da
	// rodada
	// @return retorna a posição x e y da vitória régia escura
	private int[] encontrarVrEscura() {
		VitoriaRegia vr;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tabuleiro.getElementAt(j, i).getClass() == Vr_Escura.class
						|| tabuleiro.getElementAt(j, i).getClass() == EscurecerDecorator.class) {
					vr = (VitoriaRegia) tabuleiro.getElementAt(j, i);
					if (!vr.isHasFlor()) {
						int[] pos = { j, i };
						return pos;
					}

				} else if (tabuleiro.getElementAt(j, i).getClass() == VitoriaRegiaComponent.class
						|| tabuleiro.getElementAt(j, i).getClass() == OvasAmarelasDecorator.class
						|| tabuleiro.getElementAt(j, i).getClass() == OvasVermelhasDecorator.class) {
					vr = (VitoriaRegia) tabuleiro.getElementAt(j, i);
					if (vr.isVirada() && !vr.isHasFlor()) {
						int[] pos = { j, i };
						return pos;
					}
				}
			}
		}

		return null;
	}

	// método utilizado para evitar que o botão do vento fique disponível depois
	// de um redo e quebre o jogo
//	private void validarVento() {
//		if (ventoIsPressed) {
//			for (Observador obs : observadores)
//				obs.notificarVentoIndisponivel();
//		} else {
//			for (Observador obs : observadores)
//				obs.notificarVentoDisponivel();
//		}
//	}

	@Override
	// método que verifica as posições das peças no tabuleiro para aplicar as
	// pontuações no fim de cada rodada
	public void verificarPadroes(int estrategia) {
		boolean done = false;
		switch (estrategia) {
		case 1:
			visitor.setStrategy(new FinderDiagonal5());
			break;
		case 2:
			visitor.setStrategy(new FinderDiagonal4());
			break;
		case 3:
			visitor.setStrategy(new Finder1x4());
			break;
		case 4:
			visitor.setStrategy(new Finder2x2());
			break;
		default:
			done = true;
			break;
		}
		tabuleiro.acceptVisitor(visitor);
		// TODO verificar se algu�m ganhou
		if (visitor.amareloPontuou()) {
			pontuacaoAmarelo += visitor.getPontuacao();
			houvePontuador = true;
			limparMesa();
		} else if (visitor.vermelhoPontuou()) {
			pontuacaoVermelho += visitor.getPontuacao();
			limparMesa();
			houvePontuador = true;
		}

		if (!done && !visitor.amareloPontuou() && !visitor.vermelhoPontuou()) {
			estrategia++;
			verificarPadroes(estrategia);

		} else {

			for (Observador obs : observadores) {
				obs.notificarGanhadorDaRodada(pontuacaoAmarelo, pontuacaoVermelho);
			}
		}
	}

	@Override
	// remove a flor de uma vitória régia
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void removerFlor() {

		VitoriaRegia vr = ((VitoriaRegia) tabuleiro.getElementAt(executedY, executedX)).getVr();

		if (vr.getClass() == OvasAmarelasDecorator.class || vr.getClass() == OvasVermelhasDecorator.class) {
			if (corSapoRemovido.equalsIgnoreCase("vermelho")) {
				System.out.println("vermelho");
				tabuleiro.setElementAt(new SapoVermelhoDecorator(vr), executedY, executedX);
			} else if (corSapoRemovido.equalsIgnoreCase("amarelo")) {
				System.out.println("amarelo");
				vr = new SapoAmareloDecorator(vr);
				tabuleiro.setElementAt(new SapoAmareloDecorator(vr), executedY, executedX);
			}

		} else {
			tabuleiro.setElementAt(vr, executedY, executedX);
		}
		houveUndo = true;
		notificarMudancaTabuleiro();
		previousState();

	}

	@Override
	// remove um sapo de uma vitória régia
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void removerSapo() {
		try {
			if (conferirIndex()) {
				VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(executedY, executedX);
				corSapoRemovido = vr.getSapo();
				vr = ((VitoriaRegiaDecorator) vr).getVr();
				tabuleiro.setElementAt(vr, executedY, executedX);
				notificarMudancaTabuleiro();
				houveUndo = true;
				previousState();
			}
		} catch (Exception e) {
			reloadState();
		}
	}

	@Override
	// desvira uma flor que inicialmente tinha a face virada para cima
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
	public void desvirarFlor() {
		try {
			if (conferirIndex()) {
				VitoriaRegia vr = (VitoriaRegia) tabuleiro.getElementAt(executedY, executedX);
				vr = ((VitoriaRegiaDecorator) vr).getVr();
				tabuleiro.setElementAt(vr, executedY, executedX);
				notificarMudancaTabuleiro();
				previousState();
			}
		} catch (Exception e) {
			reloadState();
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
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
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
			notificarMudancaTabuleiro();
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			reloadState();
		}
	}

	@Override
	// move a peça selecionada um quadrado para baixo se estiver espaço
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
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

			notificarMudancaTabuleiro();
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			reloadState();
		}
	}

	@Override
	// move a peça selecionada um quadrado para a esquerda se estiver espaço
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
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

			notificarMudancaTabuleiro();
			comecarNovaRodada();
		} catch (MovimentoInvalidoException e) {
			reloadState();
		}
	}

	@Override
	// move a peça selecionada um quadrado para a direita se estiver espaço
	// @param action, o tipo da ação, se é um execute, desfazer ou refazer cada
	// um é tratado diferente
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

			notificarMudancaTabuleiro();
			comecarNovaRodada();

		} catch (MovimentoInvalidoException e) {
			reloadState();
		}
	}

	@Override
	// caso o comando seja um undo ele utiliza as coordenadas da ultima a��o do
	// ultimo comando
	// @param action, o tipo da a��o, se � um refazer
	public void validarUndo(String action) {
		if (action.equalsIgnoreCase("undo")) {
			indexX = executedX;
			indexY = executedY;
			houveUndo = true;
		}

	}

	@Override
	// guarda a coordenada da a��o caso o usu�rio pe�a um undo
	// @param action, o tipo da a��o, se � um execute ou refazer
	public void validarExecute(String action) {
		if (action.equalsIgnoreCase("execute")) {
			executedX = indexX;
			executedY = indexY;
			houveUndo = false;
		}

	}

	private int[][] encontrarSapos() {
		int[][] pos = { { -1, -1 }, { -1, -1 } };
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tabuleiro.getElementAt(j, i).getClass() == SapoAmareloDecorator.class) {
					pos[0][0] = i;
					pos[0][1] = j;
				}
				if (tabuleiro.getElementAt(j, i).getClass() == SapoVermelhoDecorator.class) {
					pos[1][0] = i;
					pos[1][1] = j;
				}
			}
		}
		return pos;
	}

	@Override
	public boolean tabuleiroCheio() {
		return (ladoAmarelo.size() == 1 && ladoVermelho.size() == 1);
	}

	@Override
	public void removerSaposTabuleiro() {
		int[][] pos = encontrarSapos();
		VitoriaRegiaDecorator vr = (VitoriaRegiaDecorator) tabuleiro.getElementAt(pos[0][1], pos[0][0]);
		tabuleiro.setElementAt(vr.getVr(), pos[0][1], pos[0][0]);
		vr = (VitoriaRegiaDecorator) tabuleiro.getElementAt(pos[1][1], pos[1][0]);
		tabuleiro.setElementAt(vr.getVr(), pos[1][1], pos[1][0]);
		notificarMudancaTabuleiro();
	}

	@Override
	public void nextState() {
		for (Observador obs : observadores) {
			obs.nextState();
		}
	}

	@Override
	public void sapoState(boolean empate) {
		for (Observador obs : observadores) {
			obs.sapoState(empate);
		}
	}

	@Override
	public void previousState() {
		for (Observador obs : observadores) {
			obs.previousState();
		}
	}

	public void reloadState() {
		for (Observador obs : observadores) {
			obs.reloadState();
		}
	}

	@Override
	public void empate(String jogador) {
		try {
			int pos[][] = encontrarSapos();
			if (pos[0][0] != -1 && pos[1][0] != -1) {
				vencedorDaRodada = jogador;
				if (jogador.equalsIgnoreCase("amarelo")) {
					indexX = pos[0][0];
					indexY = pos[0][1];
				} else {
					indexX = pos[1][0];
					indexY = pos[1][1];
					houveEmpate = false;
				}
				adicionarFlor();

				notificarMudancaTabuleiro();
			}
		} catch (Exception e) {

		}
	}

}
