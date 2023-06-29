package confeitaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author Willian Ataides
 * @author Daniel Brito
 * @author Rafael Martins
 * 
 */
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
	
	/**
	 * Método utilizado para consultar se o pedido existe.
	 * @param cpf  chave única do cliente (FOREIGN KEY)
	 * @return	pedido cadastrado.
	 */
	
	public boolean consultarPedido(String cpf) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();
	        String sql = "SELECT * FROM pedido INNER JOIN cliente ON pedido.cpf = cliente.cpf WHERE cliente.cpf = ?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setString(1, cpf);
	        ResultSet rs = ps.executeQuery();
	        if (!rs.isBeforeFirst()) { 
	            System.out.println("Pedido não cadastrado!");
	            return false; 
	        } else {
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
	
	/**
	 * Método utilizado para atualizar pedido existente.
	 * @param cpf número único de cada cliente (FOREIGN KEY).
	 * @param numeroDoPedido define o número do pedido de forma crescente. (PRIMARY KEY)
	 * @param numeroDoPrato define o número do prato no cardápio. (FOREIGN KEY)
	 * @param nomeDoPrato define o nome do prato do cardápio. (FOREIGN KEY)
	 * @param qtde define a quantidade que o cliente comprou.
	 * @return retorna o pedido atualizado.
	 */
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

			/**
			 * Método utilizado para cadastrar o pedido.
	 * @param cpf número único de cada cliente (FOREIGN KEY).
	 * @param numeroDoPedido define o número do pedido de forma crescente. (PRIMARY KEY)
	 * @param numeroDoPrato define o número do prato no cardápio. (FOREIGN KEY)
	 * @param nomeDoPrato define o nome do prato do cardápio. (FOREIGN KEY)
	 * @param qtde define a quantidade que o cliente comprou.
			 * @return O pedido atualizado.
			 */
			public boolean cadastrarPedido(String cpf, int numeroDoPedido, int numeroDoPrato, int qtde) {
			    Connection conexao = null;
			    try {
			        conexao = Conexao.conectaBanco();
			        String sqlCliente = "SELECT cpf FROM cliente WHERE cpf=?";
			        PreparedStatement psCliente = conexao.prepareStatement(sqlCliente);
			        psCliente.setString(1, cpf);
			        ResultSet rsCliente = psCliente.executeQuery();
			        if (!rsCliente.isBeforeFirst()) { 
			            System.out.println("Cliente não encontrado!");
			            return false;
			        }
			        String sqlPedido = "INSERT INTO pedido (cpf, numeroDoPedido, numeroDoPrato, qtde) VALUES (?, ?, ?, ?)";
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
			
	/**
	 * Método utilizado para buscar o nome do prato da classe cardápio.
	 * @param numeroDoPrato busca pelo número do prato no cardápio.
	 * @return nome do prato.
	 */
			
			public String buscarNomeDoPrato(int numeroDoPrato) {
			    String nomeDoPrato = "";
			    Connection conexao = null;

			    try {
			        conexao = Conexao.conectaBanco();
			        String sql = "SELECT nomeDoPrato FROM cardapio WHERE numeroDoPrato = ?";
			        PreparedStatement ps = conexao.prepareStatement(sql);
			        ps.setInt(1, numeroDoPrato);
			        ResultSet resultSet = ps.executeQuery();

			        if (resultSet.next()) {
			            nomeDoPrato = resultSet.getString("nomeDoPrato");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        Conexao.fechaConexao(conexao);
			    }

			    return nomeDoPrato;
			}

	/**
	 * Método utilizado para remover o pedido cadastrado.
	 * @return pedido removido.
	 */
	  public boolean removerPedido() {
	        Connection conexao = null;
	        try {
	            conexao = Conexao.conectaBanco(); 
	            String sql = "DELETE FROM pedido WHERE numeroDoPedido = ?";
	            PreparedStatement ps = conexao.prepareStatement(sql);
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
