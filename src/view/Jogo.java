package view;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import control.InterfaceController;
import control.GameController;
import control.Observador;
import control.command.AdicionarFlorCommand;
import control.command.CommandInvoker;
import control.command.MoverSapoCommand;
import control.command.MoverVitoriasRegiasParaBaixoCommand;
import control.command.MoverVitoriasRegiasParaCimaCommand;
import control.command.MoverVitoriasRegiasParaDireitaCommand;
import control.command.MoverVitoriasRegiasParaEsquerdaCommand;
import control.command.VirarFlorCommand;
import control.state.GameState;
import control.state.MoverSapoState;
import control.state.SelecionarFloresState;
import exception.SemFloresNoMonteException;

//classe da view do game
public class Jogo extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	class TableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		// retorna o numero de colunas do tabuleiro
		public int getColumnCount() {
			return 5;
		}

		@Override
		// retorna o numero de linhas do tabuleiro
		public int getRowCount() {
			return 5;
		}

		@Override
		// pega a imagem do objeto na posicao especificada
		public Object getValueAt(int row, int col) {
			try {
				return controle.getPeca(col, row);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString());
				return null;
			}
		}

	}

	// classe do renderer
	class TabuleiroRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		// metodo de renderizacao das imagens
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			
			
			
			// se a celula estiver selecionada ela recebe uma borda amarela para indicar ao
			// usuario
			if (isSelected) {
				addBorder();
			} else {
				removeBorder();
			}
			this.setIcon((ImageIcon) value);

			return this;
		}

		public void addBorder() {
			this.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		}

		public void removeBorder() {
			this.setBorder(null);
		}

	}

	private static CommandInvoker ci;
	private JButton jbVirar;
	private JButton jbMoverCima;
	private JButton jbMoverBaixo;
	private JButton jbMoverEsq;
	private JButton jbMoverDir;
	private JButton jbMoverSapo;
	private JButton jbAddFlor;
	private JButton jbVento;
	private JButton jbProx;
	private JPanel opcoesFlores;
	private JPanel pontuacao;
	private JLabel pontos;
	private JLabel flor1Text;
	private JLabel flor2Text;
	private JLabel flor3Text;
	private JLabel florIcone;
	private JLabel florIcone2;
	private JLabel florIcone3;
	private JButton btnFlor1;
	private JButton btnFlor2;
	private JButton btnFlor3;
	private GameState state;
	// se trata da imagem das flores na view somente
	private ImageIcon florAmarela;
	private ImageIcon florVermelha;

	private InterfaceController controle;
	private JTable tabuleiro;
	private boolean cellSelection;

	// construtor
	public Jogo() throws Exception {

		ci = CommandInvoker.getInstance();

		setTitle("Haru Ichiban");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		this.controle = GameController.getInstance();
		this.controle.addObservador(this);

		initComponents();

		controle.inicializarTabuleiro();
		controle.embaralharMontes();

		pack();

		this.setState(new SelecionarFloresState(this));
	}

	private void initComponents() {
		florAmarela = new ImageIcon("imagens/FlorAmarela.png");
		florVermelha = new ImageIcon("imagens/FlorVermelha.png");

		// cria o tabuleiro e instancia todos seus componentes
		tabuleiro = new JTable();
		tabuleiro.setModel(new TableModel());
		tabuleiro.setRowSelectionAllowed(false);
		tabuleiro.setCellSelectionEnabled(false);
		cellSelection = false;
		tabuleiro.setDragEnabled(false);
		for (int x = 0; x < tabuleiro.getColumnModel().getColumnCount(); x++) {
			tabuleiro.getColumnModel().getColumn(x).setWidth(100);
			tabuleiro.getColumnModel().getColumn(x).setMinWidth(100);
			tabuleiro.getColumnModel().getColumn(x).setMaxWidth(100);
		}
		tabuleiro.setRowHeight(100);
		tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabuleiro.setShowGrid(false);
		tabuleiro.setIntercellSpacing(new Dimension(0, 0));
		tabuleiro.setDefaultRenderer(Object.class, new TabuleiroRenderer());

		tabuleiro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cellSelection && !tabuleiro.getCellSelectionEnabled()) {
					tabuleiro.setCellSelectionEnabled(true);
				}

				if (e.getClickCount() == 1 && cellSelection) {
					controle.atualizarIndexFlor(tabuleiro.getSelectedRow(), tabuleiro.getSelectedColumn());
				}

			}
		});

		tabuleiro.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (tabuleiro.getCellSelectionEnabled()) {
					tabuleiro.setCellSelectionEnabled(false);
				}
			}
		});

		add(tabuleiro, CENTER);
		
		// Cria o painel principal e adiciona os botoes, imagens, textos etc..
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());

		JPanel jpOpcoes = new JPanel();
		JPanel jpOpcoes2 = new JPanel();

		jbVento = new JButton("Vento");
		jbVento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					jbVento.setEnabled(false);
					controle.ventoDaPrimavera();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes.add(jbVento);

		jbVirar = new JButton("Virar");
		jbVirar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {

					tabuleiro.setCellSelectionEnabled(false);
					jbVirar.setEnabled(false);
					ci.execute(new VirarFlorCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes.add(jbVirar);

		jbMoverSapo = new JButton("Mover o Sapo");
		jbMoverSapo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					jbMoverSapo.setEnabled(false);
					ci.execute(new MoverSapoCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes.add(jbMoverSapo);

		jbAddFlor = new JButton("Adicionar Flor");
		jbAddFlor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					tabuleiro.setCellSelectionEnabled(false);
					jbAddFlor.setEnabled(false);
					ci.execute(new AdicionarFlorCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes.add(jbAddFlor);

		jbMoverCima = new JButton("Mover Acima");
		jbMoverCima.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.execute(new MoverVitoriasRegiasParaCimaCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbMoverCima);

		jbMoverBaixo = new JButton("Mover Abaixo");
		jbMoverBaixo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.execute(new MoverVitoriasRegiasParaBaixoCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbMoverBaixo);

		jbMoverEsq = new JButton("Mover Esq");
		jbMoverEsq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.execute(new MoverVitoriasRegiasParaEsquerdaCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbMoverEsq);

		jbMoverDir = new JButton("Mover Dir");
		jbMoverDir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.execute(new MoverVitoriasRegiasParaDireitaCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbMoverDir);

		jbProx = new JButton("Trocar Jogador");
		jbProx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					 controle.mudarJogador();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbProx);

		jp.add(jpOpcoes, BorderLayout.NORTH);
		jp.add(jpOpcoes2, BorderLayout.SOUTH);
		add(jp, SOUTH);

		opcoesFlores = new JPanel();

		opcoesFlores.setLayout(new BorderLayout());
		JPanel op1 = new JPanel();
		JPanel op2 = new JPanel();
		JPanel op3 = new JPanel();

		flor1Text = new JLabel("0");

		florIcone = new JLabel();
		florIcone.setIcon(florAmarela);
		florIcone2 = new JLabel();
		florIcone2.setIcon(florAmarela);
		florIcone3 = new JLabel();
		florIcone3.setIcon(florAmarela);

		btnFlor1 = new JButton("Me escolha");
		btnFlor1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.escolherFlor(Integer.parseInt(flor1Text.getText()));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		flor2Text = new JLabel("0");
		btnFlor2 = new JButton("Me escolha 2");
		btnFlor2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.escolherFlor(Integer.parseInt(flor2Text.getText()));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		flor3Text = new JLabel("0");
		btnFlor3 = new JButton("Me escolha 3");
		btnFlor3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.escolherFlor(Integer.parseInt(flor3Text.getText()));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});
		opcoesFlores.setLayout(new BorderLayout());

		op1.add(flor1Text, BorderLayout.NORTH);
		op1.add(florIcone);
		op1.add(btnFlor1, BorderLayout.SOUTH);

		op2.add(flor2Text, BorderLayout.NORTH);
		op2.add(florIcone2);
		op2.add(btnFlor2, BorderLayout.SOUTH);

		op3.add(flor3Text, BorderLayout.NORTH);
		op3.add(florIcone3);
		op3.add(btnFlor3, BorderLayout.SOUTH);

		opcoesFlores.add(op1, BorderLayout.NORTH);
		opcoesFlores.add(op2, BorderLayout.CENTER);
		opcoesFlores.add(op3, BorderLayout.SOUTH);

		add(opcoesFlores, BorderLayout.LINE_END);

		pontuacao = new JPanel();
		pontuacao.setLayout(new BorderLayout());
		pontos = new JLabel();
		pontos.setIcon(new ImageIcon("imagens/pontuacao/pontuacao0x0.png"));
		pontuacao.add(pontos);
		add(pontuacao, BorderLayout.WEST);

	}

	// metodo main
	public static void main(String[] args) {
		try {
			Jogo d = new Jogo();
			d.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

	@Override
	// muda o tabuleiro
	public void notificarMudouTabuleiro() {
		tabuleiro.repaint();
		tabuleiro.setCellSelectionEnabled(false);
	}
	
	// atualiza o marcador da pontuacao com os novos valores
	@Override
	public void notificarGanhadorDaRodada(int pontosAmarelo, int pontosVermelho) {
		pontos.setIcon(new ImageIcon("imagens/pontuacao/pontuacao" + pontosAmarelo + "x" + pontosVermelho + ".png"));
	}

	@Override
	// atualiza os valores das flores da mao do jogador que o usuario esta
	// olhando
	// @param maoJogador Os 3 valores de cada flor da mao daquele jogador
	public void notificarJogadorPescou(int[] maoJogador) {
		flor1Text.setText(maoJogador[0] + "");
		flor2Text.setText(maoJogador[1] + "");
		flor3Text.setText(maoJogador[2] + "");
	}
	
	// Pede ao jogador que "Coache" pois ocorreu um empate entre as flores
	@Override
	public void notificarEmpateFlor() {
		JOptionPane.showMessageDialog(null, "Coachar");
		controle.empate("amarelo");
	}

	@Override
	// ativa os botoes de selecao das flores do painel direito
	public void notificarSelecaoFlorDisponivel(boolean disponivel) {
		btnFlor1.setEnabled(disponivel);
		btnFlor2.setEnabled(disponivel);
		btnFlor3.setEnabled(disponivel);
	}

	@Override
	// ativa o botao de virar a vitoria regia
	public void notificarVirarFlorHabilitada(boolean disponivel) {
		jbVirar.setEnabled(disponivel);
	}

	@Override
	// ativa os botoes de movimento das vitorias regias
	public void notificarMovimentacaoHabilitada(boolean disponivel) {
		jbMoverCima.setEnabled(disponivel);
		jbMoverBaixo.setEnabled(disponivel);
		jbMoverEsq.setEnabled(disponivel);
		jbMoverDir.setEnabled(disponivel);

	}

	@Override
	// permite o usuario selecionar a posicao da sua proxima jogada no tabuleiro
	public void notificarSelecaoTabuleiroAprovada(boolean disponivel) {
		tabuleiro.setCellSelectionEnabled(disponivel);
		cellSelection = disponivel;
	}

	@Override
	// ativa o botao de adicionar uma flor no campo selecionado
	public void notificarAdicionarFlorHabilitado(boolean disponivel) {
		jbAddFlor.setEnabled(disponivel);
	}

	@Override
	// ativa o botao do vento da primavera
	public void notificarVentoDisponivel(boolean disponivel) {
		jbVento.setEnabled(disponivel);
	}

	@Override
	// troca a imagem das flores do painel da direita por flores amarelas
	public void notificarIconesAmarelos() {
		florIcone.setIcon(florAmarela);
		florIcone2.setIcon(florAmarela);
		florIcone3.setIcon(florAmarela);
	}

	@Override
	// troca a imagem das flores do painel da direita por flores vermelhas
	public void notificarIconesVermelhos() {
		florIcone.setIcon(florVermelha);
		florIcone2.setIcon(florVermelha);
		florIcone3.setIcon(florVermelha);

	}
	
	//ativa/desativa o botao de mover sapo
	@Override
	public void notificarSapoHabilitado(boolean disponivel) {
		jbMoverSapo.setEnabled(disponivel);
	}
	
	// mostra quem ganhou o jogo
	@Override
	public void notificarGanhadorDoJogo(String vencedor) {
		pontos.setIcon(new ImageIcon("imagens/pontuacao/pontuacao0x0.png"));
		JOptionPane.showMessageDialog(null, "Acabou o jogo!\nVencedor: Jogador " + vencedor);
	}
	
	// recarrega o estado atual do jogo caso ocorrer alguma excessao
	@Override
	public void reloadState() {
		this.state.loadState();
	}
	
	// seta o estado atual do jogo
	@Override
	public void setState(GameState state) {
		this.state = state;
	}
	
	// avanca para o proximo estado
	@Override
	public void nextState() {
		this.state.nextState();
	}
	
	//chama o estado de movimentar sapo pelo tabuleiro
	@Override
	public void sapoState(boolean empate) {
		this.setState(new MoverSapoState(this, this.state));
	}

}
