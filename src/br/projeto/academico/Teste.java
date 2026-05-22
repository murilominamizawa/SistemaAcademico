package br.projeto.academico;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) {
        System.out.println("Teste de conexão com o banco...");

        try {
            Connection con = ConexaoBanco.obtenerConexao();
            
            if (con != null) {
                System.out.println("CONECTADO!");
                con.close(); 
            }
            
        } catch (SQLException e) {
            System.out.println("ERRO AO CONECTAR:");
            System.out.println("Mensagem de erro: " + e.getMessage());
        }
    }
}