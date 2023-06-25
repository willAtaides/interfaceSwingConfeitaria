package confeitaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Willian
 * @author Daniel Brito
 * @author Rafael Martins
 */

public class Cliente {
		private String cpf;
		private String nome;
		protected String contato;
		protected String endereco;
		
		public Cliente() {
			this.cpf = "";
		}
		public Cliente(String cpf, String nome, String contato, String endereco) {
			this.cpf = cpf;
			this.nome = nome;
			this.contato = contato;
			this.endereco = endereco;
		}


		public String getCpf() {
			return cpf;
		}


		public void setCpf(String cpf) {
			this.cpf = cpf;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getContato() {
			return contato;
		}


		public void setContato(String contato) {
			this.contato = contato;
		}


		public String getEndereco() {
			return endereco;
		}


		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}	
		
		/**
		 * Método utilizado para verificar se o cliente já está cadastrado no sistema.
		 * @param cpf chave utilizada para ser feita a verificação do cliente (PRIMARY KEY).
		 * @return	informações do usuário.
		 */
		
		public boolean consultarCliente(String cpf) {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "select * from cliente where cpf=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { 
					System.out.println("Cliente não cadastrado!");
					return false; 
				} else {
					while (rs.next()) {
						this.cpf = rs.getString("cpf");
						this.nome = rs.getString("nome");
						this.contato = rs.getString("contato");
						this.endereco = rs.getString("endereco");
					}
					return true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao consultar o cliente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
		/**
		 * Método utilizado para atualizar o cliente já cadastrado.
		 * @param cpf      número único de cada cliente (PRIMARY KEY).
		 * @param nome	   nome do cliente.
		 * @param contato  contato do cliente. 
		 * @param endereco endereço do cliente.
		 * @return         cliente atualizado.
		 */
		
		public boolean atualizarCliente(String cpf, String nome, String contato, String endereco) {
		    if (!consultarCliente(cpf)) {
		        System.out.println("Cliente não cadastrado!");
		        return false;
		    }

		    Connection conexao = null;
		    try {
		        conexao = Conexao.conectaBanco();
		        String sql = "UPDATE cliente SET nome=?, contato=?, endereco=? WHERE cpf=?";
		        PreparedStatement ps = conexao.prepareStatement(sql);
		        ps.setString(1, nome);
		        ps.setString(2, contato);
		        ps.setString(3, endereco);
		        ps.setString(4, cpf);

		        int totalRegistrosAfetados = ps.executeUpdate();
		        if (totalRegistrosAfetados == 0) {
		            System.out.println("Não foi feita a atualização!");
		        } else {
		            System.out.println("Atualização realizada!");
		        }
		        return true;
		    } catch (SQLException erro) {
		        System.out.println("Erro ao atualizar o cliente: " + erro.toString());
		        return false;
		    } finally {
		        Conexao.fechaConexao(conexao);
		    }
		}
		
		/**
		 * Método utilizado para cadastrar um cliente novo.
		 * @param cpf número único de cada cliente (PRIMARY KEY).
		 * @param nome	   nome do cliente.
		 * @param contato  contato do cliente. 
		 * @param endereco endereço do cliente.
		 * @return		cliente cadastrado.
		 */
		
		public boolean cadastrarCliente(String cpf, String nome, String contato, String endereco) {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco(); 
				String sql = "insert into cliente set cpf=?, nome=?, contato=?, endereco=?;";
				PreparedStatement ps = conexao.prepareStatement(sql);		
				ps.setString(1, cpf); 
				ps.setString(2, nome); 
				ps.setString(3, contato); 
				ps.setString(4, endereco); 
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
		 * Método utilizado para remover cliente já cadastrado. 
		 * @return  Cliente removido.
		 */
		
		  public boolean removerCliente() {
		        Connection conexao = null;
		        try {
		            conexao = Conexao.conectaBanco(); 
		            String sql = "DELETE FROM cliente WHERE cpf = ?";
		            PreparedStatement ps = conexao.prepareStatement(sql);
		            ps.setString(1, cpf);
		            int totalRegistrosAfetados = ps.executeUpdate();
		            if (totalRegistrosAfetados == 0) {
		                System.out.println("Cliente não encontrado.");
		                return false;
		            }
		            System.out.println("Cliente removido com sucesso!");
		            return true;
		        } catch (SQLException erro) {
		            System.out.println("Erro ao remover o cliente: " + erro.toString());
		            return false;
		        } finally {
		            Conexao.fechaConexao(conexao);
		        }
		    }
		}

