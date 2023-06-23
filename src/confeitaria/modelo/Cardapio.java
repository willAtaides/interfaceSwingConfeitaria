package confeitaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
// METODO CONSULTAR O CARDAPIO
	public boolean consultarCardapio(int numeroDoPrato) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from cardapio where numeroDoPrato=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numeroDoPrato); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Prato não cadastrado!");
				return false; // Conta n�o cadastrada
			} else {
				// Efetua a leitura do registro da tabela
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
//METODO ATUALIZAR CARDAPIO
	public boolean atualizarCardapio(int numeroDoPrato,String nomeDoPrato,String tipo,
double preco) {
		if (!consultarCardapio(numeroDoPrato))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update cardapio set nomeDoPrato=?, tipo=?, preco=? where numeroDoPrato=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setString(1, nomeDoPrato);
				ps.setString(2, tipo);
				ps.setDouble(3, preco);
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

//	METODO CADASTRAR NO CARDAPIO
	public boolean cadastrarPrato(int numeroDoPrato, String nomeDoPrato, String tipo, double preco) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco(); // Lembrar de mudar conectarBanco
			// Define a consulta
			String sql = "insert into cardapio set numeroDoPrato=?, nomeDoPrato=?, tipo=?, preco=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numeroDoPrato); // Substitui o primeiro parâmetro da consulta pela ag�ncia informada
			ps.setString(2, nomeDoPrato); // Substitui o segundo parâmetro da consulta pela conta informada
			ps.setString(3, tipo); // Substitui o terceiro parâmetro da consulta pelo titular informado
			ps.setDouble(4, preco); // Substitui o quarto parâmetro da consulta pelo titular informado
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

	
//	METODO REMOVER DO CARDAPIO
	public boolean removerPrato() {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco(); // Lembrar de mudar conectarBanco
			// Define a consulta
			String sql = "DELETE FROM numeroDoPrato WHERE  = numeroDoPrato?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
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
