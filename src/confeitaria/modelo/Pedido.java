package confeitaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pedido {
	private String cpf;
	private int numeroDoPedido;
	private int numeroDoPrato;
	private String nomeDoPrato;
	private int qtde;
	
	public Pedido() {
		this.cpf="";
	}
	public Pedido(String cpf, int numeroDoPedido, int numeroDoPrato, String nomeDoPrato, int qtde) {
		this.cpf = cpf;
		this.numeroDoPedido= numeroDoPedido;
		this.numeroDoPrato = numeroDoPrato;
		this.nomeDoPrato = nomeDoPrato;
		this.qtde = qtde;
	}


	public int getNumeroDoPedido() {
		return numeroDoPedido;
	}


	public void setNumeroDoPedido(int numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public int getNumeroDoPrato() {
		return numeroDoPrato;
	}


	public void setNumeroDoPrato(int numeroDoPrato) {
		this.numeroDoPrato = numeroDoPrato;
	}


	public String getNomeDoPrato() {
		return nomeDoPrato;
	}


	public void setNomeDoPrato(String nomeDoPrato) {
		this.nomeDoPrato = nomeDoPrato;
	}


	public int getQtde() {
		return qtde;
	}


	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
// METODO CONSULTAR O PEDIDO
			public boolean consultarPedido(String cpf) {
				// Define a conexão
				Connection conexao = null;
				try {
					conexao = Conexao.conectaBanco();
					// Define a consulta
					String sql = "SELECT * FROM pedido INNER JOIN cliente ON pedido.cpf = cliente.cpf WHERE pedido.cpf = ?";
					// Prepara a consulta
					PreparedStatement ps = conexao.prepareStatement(sql);
					// Define os parâmetros da consulta
					ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela agência informada
					// Executa a consulta, resultando em um objeto da classe ResultSet
					ResultSet rs = ps.executeQuery();
					if (!rs.isBeforeFirst()) { // Verifica se não estão antes do primeiro registro
						System.out.println("Pedido não cadastrado!");
						return false; // Conta n�o cadastrada
					} else {
						// Efetua a leitura do registro da tabela
						while (rs.next()) {
							this.numeroDoPedido = rs.getInt("numeroDoPedido");
							this.cpf = rs.getString("cpf");
							this.numeroDoPrato = rs.getInt("numeroDoPrato");
							this.nomeDoPrato = rs.getString("nomeDoPrato");
							this.qtde= rs.getInt("qtde");
						}
						return true;
					}
				} catch (SQLException erro) {
					System.out.println("Erro ao consultar o pedido: " + erro.toString());
					return false;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
//METODO ATUALIZAR CLIENTE
			public boolean atualizarPedido(String cpf,int numeroDoPedido,int numeroDoPrato, String nomeDoPrato, int qtde) {
				if (!consultarPedido(cpf))
					return false;
				else {
					// Define a conexão
					Connection conexao = null;
					try {
						// Define a conex�o
						conexao = Conexao.conectaBanco();
						// Define a consulta
						String sql = "update cliente set cpf=?, nomeDoPrato=?, qtde=? where numeroDoPedido=?";
						// Prepara a consulta
						PreparedStatement ps = conexao.prepareStatement(sql);
						// Define os par�metros da atualiza��o
						ps.setInt(1, numeroDoPrato);
						ps.setString(2, nomeDoPrato);
						ps.setInt(3, qtde);
						int totalRegistrosAfetados = ps.executeUpdate();
						if (totalRegistrosAfetados == 0)
							System.out.println("Não foi feita a atualização!");
						else
							System.out.println("Atualização realizada!");
						return true;
					} catch (SQLException erro) {
						System.out.println("Erro ao atualizar o pedido: " + erro.toString());
						return false;
					} finally {
						Conexao.fechaConexao(conexao);
					}
				}
			}

	public boolean cadastrarPedido(String cpf, int numeroDoPedido, int numeroDoPrato, String nomeDoPrato, int qtde) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco(); //Lembrar de mudar conectarBanco
			// Define a consulta
			String sql = "INSERT INTO pedido (cpf, numeroDoPedido, numeroDoPrato, nomeDoPrato, qtde) VALUES (?, ?, ?, ?, ?)";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela ag�ncia informada
			ps.setInt(2, numeroDoPedido); // Substitui o segundo parâmetro da consulta pela conta informada
			ps.setInt(3, numeroDoPrato); // Substitui o segundo parâmetro da consulta pela conta informada
			ps.setString(4, nomeDoPrato); // Substitui o terceiro parâmetro da consulta pelo titular informado
			ps.setInt(5, qtde); // Substitui o quarto parâmetro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	  public boolean removerPedido() {
	        Connection conexao = null;
	        try {
	            conexao = Conexao.conectaBanco(); // Lembrar de mudar conectarBanco
	         // Define a consulta
	            String sql = "DELETE FROM pedido WHERE numeroDoPedido = ?";
	         // Prepara a consulta
	            PreparedStatement ps = conexao.prepareStatement(sql);
	         // Define os par�metros da consulta
	            ps.setInt(2, numeroDoPedido);
	            int totalRegistrosAfetados = ps.executeUpdate();
	            if (totalRegistrosAfetados == 0) {
	                System.out.println("Pedido não encontrado.");
	                return false;
	            }
	            System.out.println("Pedido removido com sucesso!");
	            return true;
	        } catch (SQLException erro) {
	            System.out.println("Erro ao remover o pedido: " + erro.toString());
	            return false;
	        } finally {
	            Conexao.fechaConexao(conexao);
	        }
	    }
}
