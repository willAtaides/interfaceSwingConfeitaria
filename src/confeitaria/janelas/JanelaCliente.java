
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

import confeitaria.modelo.Cliente;

public class JanelaCliente {
	public static JFrame criarJanelaCliente() {
		// Define a janela
		JFrame janelaCliente = new JFrame("Atualização do Cliente"); // Janela Normal
		janelaCliente.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCliente.setSize(400, 400); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaCliente.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCpf = new JLabel("CPF: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelContato = new JLabel("Contato: ");
		JLabel labelEndereco = new JLabel("Endereço: ");
		// Posiciona os labels na janela
		labelCpf.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 50, 20); // coluna, linha, largura, tamanho
		labelContato.setBounds(50, 120, 50, 20); // coluna, linha, largura, tamanho
		labelEndereco.setBounds(50, 160, 60, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextCpf = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextContato = new JTextField();
		JTextField jTextEndereco = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCpf.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextContato.setEnabled(true);
		jTextEndereco.setEnabled(true);
		// Posiciona os input box
		jTextCpf.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextContato.setBounds(180, 120, 150, 20);
		jTextEndereco.setBounds(180, 160, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaCliente.add(labelCpf);
		janelaCliente.add(labelNome);
		janelaCliente.add(labelContato);
		janelaCliente.add(labelEndereco);
		janelaCliente.add(jTextCpf);
		janelaCliente.add(jTextNome);
		janelaCliente.add(jTextContato);
		janelaCliente.add(jTextEndereco);
		// Define bot�es e a localiza��o deles na janela

//		BOTAO CONSULTAR
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaCliente.add(botaoConsultar);

//		BOTAO ADICIONAR
		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(50, 200, 100, 20);
		botaoAdicionar.setEnabled(false);
		janelaCliente.add(botaoAdicionar);

//		BOTAO EXCLUIR
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(150, 200, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaCliente.add(botaoExcluir);

//		BOTAO LIMPAR
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janelaCliente.add(botaoLimpar);

		Cliente cliente = new Cliente();

//		DEFINE A ACOES DO BOTOES

//		ACOES BOTAO CONSULTAR
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = jTextCpf.getText();
					String nome;
					String contato;
					String endereco;
					botaoAdicionar.setEnabled(true);
					botaoExcluir.setEnabled(true);

					if (!cliente.consultarCliente(cpf)) {
						contato = "";
						nome = "";
						endereco = "";
					} else {
						nome = cliente.getNome();
						contato = cliente.getContato();
						endereco = cliente.getEndereco();
					}
					jTextEndereco.setText(endereco);
					jTextNome.setText(nome);
					jTextContato.setText(contato);
					jTextCpf.setEnabled(true);
					jTextContato.setEnabled(true);
					botaoConsultar.setEnabled(false);
					jTextEndereco.setEnabled(true);
					jTextNome.setEnabled(true);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCliente, "Preencha os campos do CPF do Cliente corretamente!!");
				}
			}
		});

//		ACOES BOTAO ADICIONAR

		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaCliente, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					String cpf = jTextCpf.getText();
					String nome = jTextNome.getText().trim(); // Retira os espa�os em branco
					String contato = jTextContato.getText();
					String endereco = jTextEndereco.getText();

					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaCliente, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else {
						if (!cliente.consultarCliente(cpf)) {
							if (!cliente.cadastrarCliente(cpf, nome, contato, endereco))
								JOptionPane.showMessageDialog(janelaCliente, "Erro na inclusão do Cliente!");
							else
								JOptionPane.showMessageDialog(janelaCliente, "Inclusão realizada!");
						} else {
							if (!cliente.atualizarCliente(cpf, nome, contato, endereco))
								JOptionPane.showMessageDialog(janelaCliente, "Erro na atualização do Cliente!");
							else
								JOptionPane.showMessageDialog(janelaCliente, "Alteração realizada!");
						}

					}

				}
			}
		});

//		ACOES BOTAO EXCLUIR

		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaCliente, "Deseja excluir?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {

					if (cliente.removerCliente()) {
						JOptionPane.showMessageDialog(janelaCliente, "Exclusão realizada!");
					} else {
						JOptionPane.showMessageDialog(janelaCliente, "Erro na exclusão do Cliente!");
					}
				}
			}
		});

//		ACOES BOTAO LIMPAR

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextNome.setText(""); // Limpar campo
				jTextContato.setText(""); // Limpar campo
				jTextEndereco.setText(""); // Limpar campo
				jTextNome.setEnabled(true);
				jTextContato.setEnabled(true);
				jTextEndereco.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoAdicionar.setEnabled(false);
				jTextNome.requestFocus(); // Colocar o foco em um campo
			}
		});

		return janelaCliente;
	}
}
