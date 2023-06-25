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

public class Cardapio {
	private int numeroDoPrato;
	private String nomeDoPrato;
	private String tipo;
	private double preco;

	public Cardapio() {
		this.numeroDoPrato = 0;
	}

	public Cardapio(int numeroDoPrato, String nomeDoPrato, String tipo, Double preco) {
		this.numeroDoPrato = numeroDoPrato;
		this.nomeDoPrato = nomeDoPrato;
		this.tipo = tipo;
		this.preco = preco;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * Método utilizado para consultar o cardápio,verifica se o prato existe no banco de dados.
	 * @param numeroDoPrato        É utilizado o numeroDoPrato como parâmetro para saber se o prato existe.
	 * @return     retorna o prato caso ele exista.
	 */
	
	public boolean consultarCardapio(int numeroDoPrato) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from cardapio where numeroDoPrato=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numeroDoPrato); 
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				System.out.println("Prato não cadastrado!");
				return false; 
			} else {
				while (rs.next()) {
					this.numeroDoPrato = rs.getInt("numeroDoPrato");
					this.nomeDoPrato = rs.getString("nomeDoPrato");
					this.tipo = rs.getString("tipo");
					this.preco = rs.getDouble("preco");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o prato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
/**
 * Método utilizado para atualizar o Cardápio, caso ele já exista . 	
 * @param numeroDoPrato número que será organizado no cardápio de forma crescente. (PRIMARY KEY).
 * @param nomeDoPrato   nome específico do prato (ex: Pudim, Torta de limão).
 * @param tipo          específica o tipo do prato (ex:sobremesa, principal).
 * @param preco			declara o valor do prato.
 * @return              cardápio atualizado.
 */
	
	public boolean atualizarCardapio(int numeroDoPrato,String nomeDoPrato,String tipo, double preco) {
		if (!consultarCardapio(numeroDoPrato))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "update cardapio set nomeDoPrato=?, tipo=?, preco=? where numeroDoPrato=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nomeDoPrato);
				ps.setString(2, tipo);
				ps.setDouble(3, preco);
				ps.setInt(4, numeroDoPrato);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o cardápio: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Método utilizado para cadastrar um novo prato ao cardápio
	* @param numeroDoPrato número que será organizado no cardápio de forma crescente. (PRIMARY KEY).
	* @param nomeDoPrato   nome específico do prato (ex: Pudim, Torta de limão).
	* @param tipo          específica o tipo do prato (ex:sobremesa, principal).
	* @param preco			declara o valor do prato.
	 * @return             novo prato cadastrado.          
	 */
	
	public boolean cadastrarPrato(int numeroDoPrato, String nomeDoPrato, String tipo, double preco) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco(); 
			String sql = "insert into cardapio set numeroDoPrato=?, nomeDoPrato=?, tipo=?, preco=?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numeroDoPrato); 
			ps.setString(2, nomeDoPrato); 
			ps.setString(3, tipo); 
			ps.setDouble(4, preco); 
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
	
	/** 
	 * Método utilizado para remover algum prato existente do cardápio.
	 * @return Prato excluído do cardápio.
	 */
	public boolean removerPrato() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "DELETE FROM cardapio WHERE numeroDoPrato=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numeroDoPrato);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Prato não encontrado.");
				return false;
			}
			System.out.println("Prato removido com sucesso!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao remover o Prato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
}
