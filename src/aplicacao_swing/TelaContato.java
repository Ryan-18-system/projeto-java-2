/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package aplicacao_swing;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Contato;
import modelo.Telefone;

public class TelaContato {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button;
	private JButton button_1;
	private JLabel label_4;
	private JTextField textField_3;
	private JLabel label_5;
	private JTextField textField;
	private JButton button_4;



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaTelefone window = new TelaTelefone();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaContato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Contato");
		frame.setBounds(100, 100, 851, 377);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 788, 174);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"nome", "nascimento", "idade", "endereco", "numeros"}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 304, 711, 14);
		frame.getContentPane().add(label);

		label_3 = new JLabel("selecione");
		label_3.setBounds(21, 213, 711, 14);
		frame.getContentPane().add(label_3);

		label_1 = new JLabel("nome:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 238, 71, 14);
		frame.getContentPane().add(label_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(72, 237, 134, 20);
		frame.getContentPane().add(textField_1);

		label_2 = new JLabel("nascimento:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(221, 240, 71, 14);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(297, 237, 89, 20);
		frame.getContentPane().add(textField_2);

		button_1 = new JButton("Criar contato");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String nascimento = textField_2.getText();
					String endereco = textField_3.getText();
					Fachada.criarContato(nome, nascimento,endereco);
					label.setText("contato criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(221, 265, 116, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(324, 10, 89, 23);
		frame.getContentPane().add(button);

		label_4 = new JLabel("endereco:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(420, 240, 71, 14);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(481, 237, 303, 20);
		frame.getContentPane().add(textField_3);

		label_5 = new JLabel("caracteres do nome:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(21, 14, 134, 14);
		frame.getContentPane().add(label_5);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(165, 13, 134, 20);
		frame.getContentPane().add(textField);

		button_4 = new JButton("Ver endereco no mapa");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String endereco = (String) table.getValueAt( table.getSelectedRow(), 3);
						
						//abrir navegador
						String link = endereco.replaceAll(" ", "+"); // substitui espaço por "+"
						URI uri = new URI("https://www.google.com/maps/search/?api=1&query=" + link);
						Desktop.getDesktop().browse(uri);
					}
					else
						label.setText("selecione um contato");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(368, 265, 161, 23);
		frame.getContentPane().add(button_4);

		frame.setVisible(true);

	}

	public void listagem() {
		try{
			String caracteres = textField.getText().trim();
			ArrayList<Contato> lista = Fachada.listarContatos(caracteres);
		
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//criar as colunas (0,1,2) da tabela
			model.addColumn("nome");
			model.addColumn("nascimento");
			model.addColumn("idade");
			model.addColumn("endereco");
			model.addColumn("numeros");

			//criar as linhas da tabela
			String texto;
			ArrayList<String> numeros;
			for(Contato c : lista) {
				if(c.getTelefones().isEmpty())
					texto = "sem telefone";
				else {
					numeros = new ArrayList<>();
					for(Telefone t : c.getTelefones()) 
						numeros.add(t.getNumero());
					texto = String.join(",", numeros);
				}
				model.addRow(new Object[]{c.getNome(),c.getNascimentoStr(),c.getIdade(),c.getEndereco(), texto});
			}
			table.setModel(model);
			label_3.setText("resultados: "+lista.size()+ " contatos");
			
			//redimensionar a coluna 2
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(1).setMinWidth(50); //nascimento	
			table.getColumnModel().getColumn(2).setMinWidth(50); //idade
			table.getColumnModel().getColumn(3).setMinWidth(200);//endereco
			table.getColumnModel().getColumn(4).setMinWidth(300);//telefones
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
