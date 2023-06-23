package confeitaria.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import confeitaria.janelas.JanelaCliente;
import confeitaria.janelas.JanelaCardapio;
import confeitaria.janelas.JanelaPedido;

public class TesteSwing2 {

		public static void apresentarMenu() {
			
			// Define a janela
			JFrame janelaPrincipal = new JFrame("Cadastro de conta"); // Janela Normal
			janelaPrincipal.setTitle("Confeitaria");
			janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
			janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			janelaPrincipal.setSize(400, 600); // Define tamanho da janela
			UIManager.put("OptionPane.yesButtonText", "Sim"); 
			UIManager.put("OptionPane.noButtonText", "Não");
			
			
			
			
			// Cria uma barra de menu para a janela principal
			JMenuBar menuBar = new JMenuBar();
			
			// Adiciona a barra de menu ao frame
			janelaPrincipal.setJMenuBar(menuBar);
			
			// Define e adiciona menu na barra de menu
			JMenu menuAtualizar = new JMenu("Menu");
			menuBar.add(menuAtualizar);
			
			
			
			
//			CADASTRO CLIENTE		
			
			// Cria e adiciona um item simples para o menu
			JMenuItem menuCadastroCliente = new JMenuItem("Cadastro Cliente");
			menuAtualizar.add(menuCadastroCliente);
			
			// Criar a janela de atualiza��o da conta
			JFrame janelaCadastroCliente = JanelaCliente.criarJanelaCliente();
			
			// Adiciona ação para o item do menu
			menuCadastroCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					janelaCadastroCliente.setVisible(true);
				}
			});
			
			
//			CADASTRO CARDAPIO
			
			// Cria e adiciona um item simples para o menu
			JMenuItem menuCadastroCardapio = new JMenuItem("Cadastro Cardápio");
			menuAtualizar.add(menuCadastroCardapio);
			
			// Criar a janela de atualiza��o da conta
			JFrame janelaCadastroCardapio = JanelaCardapio.criarJanelaCardapio();
			// Adiciona ação para o item do menu
			menuCadastroCardapio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					janelaCadastroCardapio.setVisible(true);
				}
			});
			janelaPrincipal.setVisible(true);
		
		
//		CADASTRO PEDIDO
		
		// Cria e adiciona um item simples para o menu
		JMenuItem menuCadastroPedido = new JMenuItem("Cadastro Pedido");
		menuAtualizar.add(menuCadastroPedido);
		
		// Criar a janela de atualiza��o da conta
		JFrame janelaCadastroPedido = JanelaPedido.criarJanelaPedido();
		// Adiciona ação para o item do menu
		menuCadastroPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaCadastroPedido.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}

	
		
		public static void main(String[] args) {
			apresentarMenu();
		
	}

}
