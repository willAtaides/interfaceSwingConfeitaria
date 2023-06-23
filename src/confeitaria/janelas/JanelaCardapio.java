package confeitaria.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import confeitaria.modelo.Cardapio;

public class JanelaCardapio {
	public static JFrame criarJanelaCardapio() {
		// Define a janela
		JFrame janelaCardapio = new JFrame("Atualização do cardapio"); // Janela Normal
		janelaCardapio.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaCardapio.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCardapio.setSize(400, 400); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaCardapio.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelNumeroDoPrato = new JLabel("N° do prato: ");
		JLabel labelNomeDoPrato = new JLabel("Prato: ");
		JLabel labelTipo = new JLabel("Tipo: ");
		JLabel labelPreco = new JLabel("Preço: ");
		// Posiciona os labels na janela
		labelNumeroDoPrato.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNomeDoPrato.setBounds(50, 80, 50, 20); // coluna, linha, largura, tamanho
		labelTipo.setBounds(50, 120, 50, 20); // coluna, linha, largura, tamanho
		// Define os input box
		labelPreco.setBounds(50, 160, 50, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextNumeroDoPrato = new JTextField();
		JTextField jTextNomeDoPrato = new JTextField();
		JTextField jTextTipo = new JTextField();
		JTextField jTextPreco = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextNumeroDoPrato.setEnabled(true);
		jTextNomeDoPrato.setEnabled(false);
		jTextTipo.setEnabled(false);
		jTextPreco.setEnabled(true);
		// Posiciona os input box
		jTextNumeroDoPrato.setBounds(180, 40, 50, 20);
		jTextNomeDoPrato.setBounds(180, 80, 150, 20);
		jTextTipo.setBounds(180, 120, 150, 20);
		jTextPreco.setBounds(180, 160, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaCardapio.add(labelNumeroDoPrato);
		janelaCardapio.add(labelNomeDoPrato);
		janelaCardapio.add(labelTipo);
		janelaCardapio.add(labelPreco);
		janelaCardapio.add(jTextNumeroDoPrato);
		janelaCardapio.add(jTextNomeDoPrato);
		janelaCardapio.add(jTextTipo);
		janelaCardapio.add(jTextPreco);
		// Define bot�es e a localiza��o deles na janela
		
//		BOTAO CONSULTAR
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaCardapio.add(botaoConsultar);
		
//		BOTAO ADICIONAR
		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(50, 200, 100, 20);
		botaoAdicionar.setEnabled(false);
		janelaCardapio.add(botaoAdicionar);

//		BOTAO EXCLUIR
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(150, 200, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaCardapio.add(botaoExcluir);
		
//		BOTAO LIMPAR
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janelaCardapio.add(botaoLimpar);
		
		
		// Define objeto conta para pesquisar no banco de dados
		Cardapio prato = new Cardapio();

//		DEFINE A ACOES DO BOTOES

		
//		ACOES BOTAO CONSULTAR
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int numeroDoPrato = Integer.parseInt(jTextNumeroDoPrato.getText());
					botaoAdicionar.setEnabled(true);
					botaoExcluir.setEnabled(true);
					String nomeDoPrato;
					String tipo;
					double preco;
					
					if (!prato.consultarCardapio(numeroDoPrato)) {
						nomeDoPrato = "";
						tipo = "";
						preco = 0;
						}
					
					else
					nomeDoPrato = prato.getNomeDoPrato();
					tipo = prato.getTipo();
					preco = prato.getPreco();
					jTextNomeDoPrato.setText(nomeDoPrato);
					jTextNomeDoPrato.setEnabled(true);
					jTextTipo.setText(tipo);
					jTextTipo.setEnabled(true);
					botaoConsultar.setEnabled(true);
					preco = Double.parseDouble(jTextPreco.getText());
					jTextPreco.setEnabled(true);
					jTextNomeDoPrato.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCardapio,
							"Preencha os campos N°do Prato do Cardápio corretamente!!");
				}
			}
		});
		
//		ACOES BOTAO ADICIONAR
			

		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaCardapio, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int numeroDoPrato = Integer.parseInt(jTextNumeroDoPrato.getText()) ;
					String nomeDoPrato = jTextNomeDoPrato.getText().trim(); // Retira os espa�os em branco
					String tipo = jTextTipo.getText();
					double preco = Double.parseDouble(jTextPreco.getText()) ;

					if (nomeDoPrato.length() == 0) {
						JOptionPane.showMessageDialog(janelaCardapio, "Preencha o campo tipo");
						jTextNomeDoPrato.requestFocus();
					} else {
						if (!prato.consultarCardapio(numeroDoPrato)) {
							if (!prato.cadastrarPrato(numeroDoPrato, nomeDoPrato, tipo, preco))
								JOptionPane.showMessageDialog(janelaCardapio, "Erro na inclusão do Prato!");
							else
								JOptionPane.showMessageDialog(janelaCardapio, "Inclusão realizada!");
						} else {
							if (!prato.atualizarCardapio(numeroDoPrato, nomeDoPrato, tipo, preco))
								JOptionPane.showMessageDialog(janelaCardapio, "Erro na atualização do Prato!");
							else
								JOptionPane.showMessageDialog(janelaCardapio, "Alteração realizada!");
						}

					}

				}
			}
		});

		
//		ACOES BOTAO EXCLUIR
		
		botaoExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int resposta = JOptionPane.showConfirmDialog(janelaCardapio, "Deseja excluir?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {

		            if (prato.removerPrato()) {
		                JOptionPane.showMessageDialog(janelaCardapio, "Exclusão realizada!");
		            } else {
		                JOptionPane.showMessageDialog(janelaCardapio, "Erro na exclusão do Prato!");
		            }
		        }
		    }
		});
		
		
//		ACOES BOTAO LIMPAR
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextNomeDoPrato.setText(""); // Limpar campo
				jTextTipo.setText(""); // Limpar campo
				jTextPreco.setText(""); // Limpar campo
				jTextNomeDoPrato.setEnabled(true);
				jTextTipo.setEnabled(true);
				jTextPreco.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoAdicionar.setEnabled(false);
				jTextNomeDoPrato.requestFocus(); // Colocar o foco em um campo
			}
		});
		return janelaCardapio;
	}
}
