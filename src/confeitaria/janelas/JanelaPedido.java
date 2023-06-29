
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

import confeitaria.modelo.Pedido;

public class JanelaPedido {
	public static JFrame criarJanelaPedido() {
		// Define a janela
		JFrame janelaPedido = new JFrame("Atualização do pedido"); // Janela Normal
		janelaPedido.setResizable(true); // A janela n�o poder� ter o tamanho ajustado
		janelaPedido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPedido.setSize(400, 400); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaPedido.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCpf = new JLabel("CPF: ");
		JLabel labelNumeroDoPrato = new JLabel("N° do Prato: ");
		JLabel labelNomeDoPrato = new JLabel("Nome do Prato: ");
		JLabel labelQtde = new JLabel("Qtde: ");
		JLabel labelNumeroDoPedido = new JLabel("N° do Pedido: ");
		// Posiciona os labels na janela
		labelCpf.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNumeroDoPrato.setBounds(50, 80, 100, 20); // coluna, linha, largura, tamanho
		labelNomeDoPrato.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelQtde.setBounds(50, 160, 50, 20); // coluna, linha, largura, tamanho
		labelNumeroDoPedido.setBounds(50, 200, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextCpf = new JTextField();
		JTextField jTextNumeroDoPrato = new JTextField();
		JTextField jTextNomeDoPrato = new JTextField();
		JTextField jTextQtde = new JTextField();
		JTextField jTextNumeroDoPedido = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCpf.setEnabled(true);
		jTextNumeroDoPrato.setEnabled(false);
		jTextNomeDoPrato.setEnabled(false);
		jTextQtde.setEnabled(false);
		jTextNumeroDoPedido.setEnabled(false);
		// Posiciona os input box
		jTextCpf.setBounds(180, 40, 50, 20);
		jTextNumeroDoPrato.setBounds(180, 80, 50, 20);
		jTextNomeDoPrato.setBounds(180, 120, 150, 20);
		jTextQtde.setBounds(180, 160, 150, 20);
		jTextNumeroDoPedido.setBounds(180, 200, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaPedido.add(labelCpf);
		janelaPedido.add(labelNumeroDoPrato);
		janelaPedido.add(labelNomeDoPrato);
		janelaPedido.add(labelQtde);
		janelaPedido.add(labelNumeroDoPedido);
		
		janelaPedido.add(jTextCpf);
		janelaPedido.add(jTextNumeroDoPrato);
		janelaPedido.add(jTextNomeDoPrato);
		janelaPedido.add(jTextQtde);
		janelaPedido.add(jTextNumeroDoPedido);
		// Define botões e a localização deles na janela
		
//		BOTAO CONSULTAR
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaPedido.add(botaoConsultar);
		
		
//		BOTAO ADICIONAR
		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(50, 250, 100, 20);
		botaoAdicionar.setEnabled(false);
		janelaPedido.add(botaoAdicionar);
		
		//		BOTAO ATUALIZAR
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(50, 300, 100, 20);
		botaoAtualizar.setEnabled(false);
		janelaPedido.add(botaoAtualizar);

//		BOTAO EXCLUIR
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(150, 250, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaPedido.add(botaoExcluir);
		
//		BOTAO LIMPAR
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 250, 100, 20);
		janelaPedido.add(botaoLimpar);
		

		Pedido pedido = new Pedido();


//		DEFINE A ACOES DO BOTOES

		

		// ACOES BOTAO CONSULTAR
		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String cpf = jTextCpf.getText();
		            botaoAdicionar.setEnabled(true);
		            botaoExcluir.setEnabled(true);

		            if (!pedido.consultarPedido(cpf)) {
		                jTextNumeroDoPedido.setText("");
		                jTextNumeroDoPrato.setText("");
		                jTextNomeDoPrato.setText("");
		                jTextQtde.setText("");
		                
		                
		                jTextNumeroDoPedido.setEnabled(true);
		                jTextNumeroDoPrato.setEnabled(true);
		                jTextNomeDoPrato.setEnabled(false);
		                jTextQtde.setEnabled(true);
		                
		                botaoExcluir.setEnabled(false);
		                botaoAdicionar.setEnabled(true);
		                botaoAtualizar.setEnabled(false);
		                jTextCpf.requestFocus();
		            } else {
		                int numeroDoPedido = pedido.getNumeroDoPedido();
		                int numeroDoPrato = pedido.getNumeroDoPrato();
		                String nomeDoPrato = pedido.getNomeDoPrato();
		                int qtde = pedido.getQtde();

		                jTextNumeroDoPedido.setText(String.valueOf(numeroDoPedido));
		                jTextNumeroDoPrato.setText(String.valueOf(numeroDoPrato));
		                jTextNomeDoPrato.setText(nomeDoPrato);
		                jTextQtde.setText(String.valueOf(qtde));
		                jTextNumeroDoPedido.setEnabled(true);
		                jTextNumeroDoPrato.setEnabled(true);
		                jTextNomeDoPrato.setEnabled(true);
		                jTextQtde.setEnabled(true);

		                botaoExcluir.setEnabled(true);
		                botaoAdicionar.setEnabled(false);
		                botaoAtualizar.setEnabled(true);
		            }
		            
		        } catch (NumberFormatException erro) {
		            JOptionPane.showMessageDialog(janelaPedido, "Erro ao converter número!");
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaPedido, "Preencha o campo CPF do Cliente corretamente!");
		        }
		    }
		});
		
//		ACOES BOTAO ADICIONAR
		
		// ACOES BOTAO ADICIONAR
		botaoAdicionar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaPedido, "Deseja atualizar?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            String cpf = jTextCpf.getText();
		            int numeroDoPrato = Integer.parseInt(jTextNumeroDoPrato.getText());
		            
		          
		            String nomeDoPrato = pedido.buscarNomeDoPrato(numeroDoPrato);
		            
		            int qtde = Integer.parseInt(jTextQtde.getText());
		            int numeroDoPedido = Integer.parseInt(jTextNumeroDoPedido.getText());

		            if (!pedido.consultarPedido(cpf)) {
		                if (!pedido.cadastrarPedido(cpf,numeroDoPedido, numeroDoPrato, qtde))
		                    JOptionPane.showMessageDialog(janelaPedido, "Erro na inclusão do Pedido!");
		                else
		                    JOptionPane.showMessageDialog(janelaPedido, "Inclusão realizada!");
		            } else {
		                if (!pedido.atualizarPedido(cpf, numeroDoPedido, numeroDoPrato, nomeDoPrato, qtde))
		                    JOptionPane.showMessageDialog(janelaPedido, "Erro na atualização do Pedido!");
		                else
		                    JOptionPane.showMessageDialog(janelaPedido, "Alteração realizada!");
		            }
		        }
		    }
		});
//      ACOES BOTAO ATUALIZAR

		botaoAtualizar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String cpf = jTextCpf.getText();
		        int numeroDoPedido = Integer.parseInt(jTextNumeroDoPedido.getText());
		        int numeroDoPrato = Integer.parseInt(jTextNumeroDoPrato.getText());
		        String nomeDoPrato = jTextNomeDoPrato.getText();
		        int qtde = Integer.parseInt(jTextQtde.getText());

		        if (nomeDoPrato.length() == 0) {
		            JOptionPane.showMessageDialog(janelaPedido, "Preencha o campo nome do prato");
		            jTextNomeDoPrato.requestFocus();
		        } else {
		            if (pedido.atualizarPedido(cpf, numeroDoPedido, numeroDoPrato, nomeDoPrato, qtde)) {
		                JOptionPane.showMessageDialog(janelaPedido, "Pedido atualizado com sucesso!");
		            } else {
		                JOptionPane.showMessageDialog(janelaPedido, "Erro na atualização do Pedido!");
		            }
		        }
		    }
		});
//		ACOES BOTAO EXCLUIR
		
		botaoExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int resposta = JOptionPane.showConfirmDialog(janelaPedido, "Deseja excluir?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {

		            if (pedido.removerPedido()) {
		                JOptionPane.showMessageDialog(janelaPedido, "Exclusão realizada!");
		            } else {
		                JOptionPane.showMessageDialog(janelaPedido, "Erro na exclusão do Pedido!");
		            }
		        }
		    }
		});
		
		
//		ACOES BOTAO LIMPAR
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCpf.setText("");
				jTextNumeroDoPrato.setText("");
				jTextNomeDoPrato.setText(""); 
				jTextNumeroDoPedido.setText("");
				jTextNumeroDoPrato.setEnabled(false);
				jTextNomeDoPrato.setEnabled(false);
				jTextQtde.setText("");
				jTextQtde.setEnabled(false);
				jTextNumeroDoPedido.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoAdicionar.setEnabled(false);
				jTextCpf.requestFocus(); 
			}
		});
		
		
		return janelaPedido;
	}
}
