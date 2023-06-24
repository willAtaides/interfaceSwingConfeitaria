package confeitaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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
		
// METODO CONSULTAR O CLIENTE
		public boolean consultarCliente(String cpf) {
			// Define a conexão
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "select * from cliente where cpf=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da consulta
				ps.setString(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
				// Executa a consulta, resultando em um objeto da classe ResultSet
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
					System.out.println("Cliente não cadastrado!");
					return false; // Conta n�o cadastrada
				} else {
					// Efetua a leitura do registro da tabela
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
//METODO ATUALIZAR CLIENTE
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
// METODO CADASTRAR CLIENTE		
		public boolean cadastrarCliente(String cpf, String nome, String contato, String endereco) {
			// Define a conexão
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco(); //Lembrar de mudar conectarBanco
				// Define a consulta
				String sql = "insert into cliente set cpf=?, nome=?, contato=?, endereco=?;";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da consulta
				ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela ag�ncia informada
				ps.setString(2, nome); // Substitui o segundo parâmetro da consulta pela conta informada
				ps.setString(3, contato); // Substitui o terceiro parâmetro da consulta pelo titular informado
				ps.setString(4, endereco); // Substitui o quarto parâmetro da consulta pelo titular informado
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
// METODO REMOVER CLIENTE
		  public boolean removerCliente() {
		        Connection conexao = null;
		        try {
		            conexao = Conexao.conectaBanco(); 
		         // Define a consulta
		            String sql = "DELETE FROM cliente WHERE cpf = ?";
		         // Prepara a consulta
		            PreparedStatement ps = conexao.prepareStatement(sql);
		         // Define os par�metros da consulta
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

