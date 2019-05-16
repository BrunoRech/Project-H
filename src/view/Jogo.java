package view;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.annotation.Documented;

import javax.management.ConstructorParameters;
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

//classe da view do game
public class Jogo extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

	class TableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		// retorna o número de colunas do tabuleiro
		public int getColumnCount() {
			return 5;
		}

		@Override
		// retorna o número de linhas do tabuleiro
		public int getRowCount() {
			return 5;
		}

		@Override
		// pega a imagem do objeto na posição especificada
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

		// método de renderização das imagens
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			// se a célula estiver selecionada ela recebe uma borda amarela para indicar ao usuário
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
	private JButton jbDesfazer;
	private JButton jbRefazer;
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
	//se trata da imagem das flores na view somente
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

		jbVento.setEnabled(false);
		jbMoverSapo.setEnabled(false);
		jbMoverCima.setEnabled(false);
		jbMoverBaixo.setEnabled(false);
		jbMoverEsq.setEnabled(false);
		jbMoverDir.setEnabled(false);
		jbVirar.setEnabled(false);
		jbAddFlor.setEnabled(false);
		jbRefazer.setEnabled(false);
		jbDesfazer.setEnabled(false);

	}

	private void initComponents() {
		
		florAmarela = new ImageIcon("imagens/FlorAmarela.png");
		florVermelha =  new ImageIcon("imagens/FlorVermelha.png");
		
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

		jbRefazer = new JButton("Refazer");
		jbRefazer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.redo();
					jbRefazer.setEnabled(false);
					jbDesfazer.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes.add(jbRefazer);

		jbMoverCima = new JButton("Mover Acima");
		jbMoverCima.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					desativarMovimento();
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
					desativarMovimento();
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
					desativarMovimento();
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
					desativarMovimento();
					ci.execute(new MoverVitoriasRegiasParaDireitaCommand(controle));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbMoverDir);

		jbDesfazer = new JButton("Desfazer");
		jbDesfazer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					ci.undo();
					jbRefazer.setEnabled(true);
					jbDesfazer.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		jpOpcoes2.add(jbDesfazer);

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
		/* Temporário...
		 * Apenas até a segunda entrega quando a pontuação será calculada vou arrumar isso
		 * de forma que seja algo que mude de acordo com a pontuação*/
		pontuacao = new JPanel();
		pontuacao.setLayout(new BorderLayout());
		pontos = new JLabel();
		pontos.setIcon(new ImageIcon("imagens/Pontuacao.png"));
		pontuacao.add(pontos);
		add(pontuacao, BorderLayout.WEST);

	}
	// método main
	public static void main(String[] args) {
		try {
			Jogo d = new Jogo();
			d.setVisible(true);
			// d.escolhaCor();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

	//desativa os botões de movimento das vitórias régias
	private void desativarMovimento() {
		jbMoverCima.setEnabled(false);
		jbMoverBaixo.setEnabled(false);
		jbMoverEsq.setEnabled(false);
		jbMoverDir.setEnabled(false);
	}
	//ativa o botão de desfazer
	//desativa o botão de refazer
	private void acao() {
		jbDesfazer.setEnabled(true);
		jbRefazer.setEnabled(false);
	}

	@Override
	//muda o tabuleiro
	public void notificarMudouTabuleiro() {
		tabuleiro.repaint();
	}

	@Override
	public void notificarGanhadorDaRodada(String ganhador, int pontos) {
		// TODO apenas na segunda entrega
	}

	@Override
	//atualiza os valores das flores da mão do jogador que o usuário está olhando
	//@param maoJogador Os 3 valores de cada flor da mão daquele jogador
	public void notificarJogadorPescou(int[] maoJogador) {
		flor1Text.setText(maoJogador[0] + "");
		flor2Text.setText(maoJogador[1] + "");
		flor3Text.setText(maoJogador[2] + "");
	}

	@Override
	//ativa o botão de mover o sapo
	public void notificarSapoHabilitado() {
		jbMoverSapo.setEnabled(true);
	}

	@Override
	public void notificarEmpateFlor() {
		// TODO tela do coachar depois na segunda entrega
		
		// JOptionPane.showMessageDialog(null, "O tal do coachar vai aqui depois");
		jbAddFlor.setEnabled(false);;
	}

	@Override
	//desativa os botões de seleção das flores do painel direito 
	public void notificarSelecaoFlorIndisponivel() {
		btnFlor1.setEnabled(false);
		btnFlor2.setEnabled(false);
		btnFlor3.setEnabled(false);
	}

	@Override
	//ativa  os botões de seleção das flores do painel direito 
	public void notificarSelecaoFlorDisponivel() {
		btnFlor1.setEnabled(true);
		btnFlor2.setEnabled(true);
		btnFlor3.setEnabled(true);

	}

	@Override
	//desativa o botão de adicionar flor no campo
	public void notificarFlorAdicionada() {
		jbAddFlor.setEnabled(false);
		acao();
	}

	@Override
	//desativa o botão de mover o sapo pelo campo
	public void notificarSapoAdicionado() {
		jbMoverSapo.setEnabled(false);
		acao();
	}

	@Override
	//desativa o botão de virar uma vitória régia
	public void notificarFlorVirada() {
		jbVirar.setEnabled(false);
		acao();
	}

	@Override
	//ativa o botão de virar a vitória régia
	public void notificarVirarFlorHabilitada() {
		jbVirar.setEnabled(true);
	}

	@Override
	//ativa os botões de movimento das vitórias régias
	public void notificarMovimentacaoHabilitada() {
		jbMoverCima.setEnabled(true);
		jbMoverBaixo.setEnabled(true);
		jbMoverEsq.setEnabled(true);
		jbMoverDir.setEnabled(true);

	}

	@Override
	//permite o usuário selecionar a posição da sua próxima jogada no tabuleiro
	public void notificarSelecaoTabuleiroAprovada() {
		tabuleiro.setCellSelectionEnabled(true);
		cellSelection = true;
	}

	@Override
	//não permite o usuário selecionar a posição da sua próxima jogada no tabuleiro
	public void notificarSelecaoTabuleiroReprovada() {
		tabuleiro.setCellSelectionEnabled(false);
		cellSelection = false;
	}

	@Override
	//ativa o botão de adicionar uma flor no campo selecionado
	public void notificarAdicionarFlorHabilitado() {
		jbAddFlor.setEnabled(true);
	}
	
	@Override
	//desativa o botão do vento da primavera
	public void notificarVentoIndisponivel() {
		jbVento.setEnabled(false);
	}

	@Override
	//ativa o botão do vento da primavera
	public void notificarVentoDisponivel() {
		jbVento.setEnabled(true);
	}

	@Override
	//desativa o movimento das vitórias régias
	public void notificarMovimentacaoDesabilitada() {
		desativarMovimento();	
	}

	@Override
	//desativa o botão de refazer e ativa o botão de desfazer
	public void notificarExecute() {
		acao();	
	}

	@Override
	//troca a imagem das flores do painel da direita por flores amarelas
	public void notificarIconesAmarelos() {
		florIcone.setIcon(florAmarela);
		florIcone2.setIcon(florAmarela);
		florIcone3.setIcon(florAmarela);
	}

	@Override
	//troca a imagem das flores do painel da direita por flores vermelhas
	public void notificarIconesVermelhos() {
		florIcone.setIcon(florVermelha);
		florIcone2.setIcon(florVermelha);
		florIcone3.setIcon(florVermelha);
		
	}

}
