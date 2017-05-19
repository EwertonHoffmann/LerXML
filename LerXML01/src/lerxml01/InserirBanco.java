/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lerxml01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author labin
 */
public class InserirBanco {
    private Connection conexao;

    private final static String url = "jdbc:postgresql://localhost/";
    private final static String baseDados = "teste";
    private final static String usuario = "postgres"; //usuário
    private final static String senha = "postgres";//senha

    private void abreConexao() throws ClassNotFoundException, SQLException {
        if (conexao == null) {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url + baseDados, usuario, senha);
        }
    }

    private void fechaConexao() throws SQLException {
        conexao.close();
        conexao = null;
    }

    private void insereCidade(int codigo, String nome, String uf) throws SQLException {
        conexao.setAutoCommit(false);//iniciando a transação
        String sql = "INSERT INTO cidade (codigo, nome, uf) "
                + "VALUES (?, ?);";
        PreparedStatement ps = conexao.prepareStatement(sql); //inserindo
        ps.setInt(1, codigo);
        ps.setString(2, nome);
        ps.setString(3, uf);
        ps.execute();
        ps.close();
        conexao.commit();
    }

    public static void main(String[] args) {
        InserirBanco insere = new InserirBanco();
        try {
            System.out.println("Abrindo a conexão");
            insere.abreConexao();
            System.out.println("Conexão aberta");
        } catch (ClassNotFoundException ex) {
            System.out.println("Problemas ao abrir a conexão");
            Logger.getLogger(InserirBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Problemas ao abrir a conexão");
            Logger.getLogger(InserirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println("Fechando conexão");
            insere.fechaConexao();
            System.out.println("Conexão fechada!");
        } catch (SQLException ex) {
            System.out.println("Problemas ao fechar a conexão");
            Logger.getLogger(InserirBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
