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
	        String sql = "SELECT * FROM pedido INNER JOIN cliente ON pedido.cpf = cliente.cpf WHERE cliente.cpf = ?";
	        // Prepara a consulta
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        // Define o parâmetro da consulta
	        ps.setString(1, cpf);
	        // Executa a consulta, resultando em um objeto da classe ResultSet
	        ResultSet rs = ps.executeQuery();
	        if (!rs.isBeforeFirst()) { // Verifica se não estão antes do primeiro registro
	            System.out.println("Pedido não cadastrado!");
	            return false; // Pedido não cadastrado
	        } else {
	            // Efetua a leitura do registro da tabela
	            while (rs.next()) {
	                this.numeroDoPedido = rs.getInt("numeroDoPedido");
	                this.cpf = rs.getString("cpf");
	                this.numeroDoPrato = rs.getInt("numeroDoPrato");
	                this.nomeDoPrato = rs.getString("nomeDoPrato");
	                this.qtde = rs.getInt("qtde");
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
	
			public boolean atualizarPedido(String cpf, int numeroDoPedido, int numeroDoPrato, String nomeDoPrato, int qtde) {
			    if (!consultarPedido(cpf))
			        return false;
			    else {
			        Connection conexao = null;
			        try {
			            conexao = Conexao.conectaBanco();
			            String sql = "UPDATE pedido SET cpf=?, numeroDoPedido=?, numeroDoPrato=?, nomeDoPrato=?, qtde=? WHERE cpf=?";
			            PreparedStatement ps = conexao.prepareStatement(sql);
			            ps.setString(1, cpf);
			            ps.setInt(2, numeroDoPedido);
			            ps.setInt(3, numeroDoPrato);
			            ps.setString(4, nomeDoPrato);
			            ps.setInt(5, qtde);
			            ps.setString(6, cpf);

			            int totalRegistrosAfetados = ps.executeUpdate();
			            if (totalRegistrosAfetados == 0) {
			                System.out.println("Não foi feita a atualização!");
			            } else {
			                System.out.println("Atualização realizada!");
			            }
			            return true;
			        } catch (SQLException erro) {
			            System.out.println("Erro ao atualizar o pedido: " + erro.toString());
			            return false;
			        } finally {
			            Conexao.fechaConexao(conexao);
			        }
			    }
			}

			public boolean cadastrarPedido(String cpf, int numeroDoPedido, int numeroDoPrato, int qtde) {
			    // Define a conexão
			    Connection conexao = null;
			    try {
			        conexao = Conexao.conectaBanco();

			        // Define a consulta para verificar se o cliente existe
			        String sqlCliente = "SELECT cpf FROM cliente WHERE cpf=?";

			        // Prepara a consulta para verificar se o cliente existe
			        PreparedStatement psCliente = conexao.prepareStatement(sqlCliente);
			        psCliente.setString(1, cpf);

			        // Executa a consulta para verificar se o cliente existe
			        ResultSet rsCliente = psCliente.executeQuery();

			        if (!rsCliente.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
			            System.out.println("Cliente não encontrado!");
			            return false;
			        }

			        // Define a consulta para cadastrar o pedido
			        String sqlPedido = "INSERT INTO pedido (cpf, numeroDoPedido, numeroDoPrato, qtde) VALUES (?, ?, ?, ?)";

			        // Prepara a consulta para cadastrar o pedido
			        PreparedStatement psPedido = conexao.prepareStatement(sqlPedido);
			        psPedido.setString(1, cpf);
			        psPedido.setInt(2, numeroDoPedido);
			        psPedido.setInt(3, numeroDoPrato);
			        psPedido.setInt(4, qtde);

			        int totalRegistrosAfetados = psPedido.executeUpdate();
			        if (totalRegistrosAfetados == 0) {
			            System.out.println("Não foi feito o cadastro!");
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
	
	public String buscarNomeDoPrato(int numeroDoPrato) {
	    String nomeDoPrato = "";

	    try (Connection conexao = Conexao.conectaBanco()) {
	        String sql = "SELECT nomeDoPrato FROM cardapio WHERE numeroDoPrato = ?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, numeroDoPrato);
	        ResultSet resultSet = ps.executeQuery();

	        if (resultSet.next()) {
	            nomeDoPrato = resultSet.getString("nomeDoPrato");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nomeDoPrato;
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
	            ps.setInt(1, numeroDoPedido);
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
