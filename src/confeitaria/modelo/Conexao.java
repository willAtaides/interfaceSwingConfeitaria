package confeitaria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Willian
 * @author Daniel Brito
 * @author Rafael Martins
 * 
 * Utilizada para fazer a conexão com o banco de dados.
 */

public class Conexao {
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/confeitaria"; 
			String user = "root";
			String password = ""; 
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conexão ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro não identificado: " + erro.toString());
		} 
		return conexao;
	}
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conexão: " + erro.toString());
		}
	}
}