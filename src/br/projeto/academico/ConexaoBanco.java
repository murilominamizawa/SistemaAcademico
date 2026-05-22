package br.projeto.academico;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBanco {

	// Conexão com banco de dados

    private static final String URL_PADRAO    = "jdbc:mysql://localhost:3306/academico_unicid";
    private static final String USUARIO_PADRAO = "root";
    private static final String SENHA_PADRAO   = "Mashemulo1973@@";

    private static Properties carregarPropriedades() {
        Properties props = new Properties();

        File externo = new File("db.properties");
        if (externo.exists()) {
            try (FileInputStream fis = new FileInputStream(externo)) {
                props.load(fis);
                return props;
            } catch (IOException e) {
                System.err.println("Aviso: não foi possível ler db.properties externo: " + e.getMessage());
            }
        }

        try (InputStream is = ConexaoBanco.class.getClassLoader()
                                    .getResourceAsStream("db.properties")) {
            if (is != null) {
                props.load(is);
                return props;
            }
        } catch (IOException e) {
            System.err.println("Aviso: não foi possível ler db.properties do classpath: " + e.getMessage());
        }

        props.setProperty("db.url",     URL_PADRAO);
        props.setProperty("db.usuario", USUARIO_PADRAO);
        props.setProperty("db.senha",   SENHA_PADRAO);
        return props;
    }

    public static Connection obtenerConexao() throws SQLException {
        Properties props = carregarPropriedades();

        String url     = props.getProperty("db.url",     URL_PADRAO);
        String usuario = props.getProperty("db.usuario", USUARIO_PADRAO);
        String senha   = props.getProperty("db.senha",   SENHA_PADRAO);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do MySQL não encontrado: " + e.getMessage());
        }
    }
}