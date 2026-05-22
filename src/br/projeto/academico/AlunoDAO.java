package br.projeto.academico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos "
                   + "(rgm, nome, data_nascimento, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,  aluno.getRgm());
            stmt.setString(2,  aluno.getNome());
            stmt.setString(3,  aluno.getDataNascimento());
            stmt.setString(4,  aluno.getCpf());
            stmt.setString(5,  aluno.getEmail());
            stmt.setString(6,  aluno.getEndereco());
            stmt.setString(7,  aluno.getMunicipio());
            stmt.setString(8,  aluno.getUf());
            stmt.setString(9,  aluno.getCelular());
            stmt.setString(10, aluno.getCurso());
            stmt.setString(11, aluno.getCampus());
            stmt.setString(12, aluno.getPeriodo());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null,
                    "Erro: já existe um aluno cadastrado com o RGM informado.",
                    "Duplicidade", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Erro ao salvar aluno:\n" + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // CONSULTAR
    public Aluno consultar(String rgm) {
        String sql = "SELECT * FROM alunos WHERE rgm = ?";

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rgm);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setRgm(rs.getString("rgm"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setDataNascimento(rs.getString("data_nascimento"));
                    aluno.setCpf(rs.getString("cpf"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setEndereco(rs.getString("endereco"));
                    aluno.setMunicipio(rs.getString("municipio"));
                    aluno.setUf(rs.getString("uf"));
                    aluno.setCelular(rs.getString("celular"));
                    aluno.setCurso(rs.getString("curso"));
                    aluno.setCampus(rs.getString("campus"));
                    aluno.setPeriodo(rs.getString("periodo"));
                    return aluno;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao consultar aluno:\n" + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // ALTERAR
    // atualizando dados de cadastro como curso, campus e período
    public void alterar(Aluno aluno) {
        String sql = "UPDATE alunos SET "
                   + "nome=?, data_nascimento=?, cpf=?, email=?, endereco=?, "
                   + "municipio=?, uf=?, celular=?, curso=?, campus=?, periodo=? "
                   + "WHERE rgm=?";

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,  aluno.getNome());
            stmt.setString(2,  aluno.getDataNascimento());
            stmt.setString(3,  aluno.getCpf());
            stmt.setString(4,  aluno.getEmail());
            stmt.setString(5,  aluno.getEndereco());
            stmt.setString(6,  aluno.getMunicipio());
            stmt.setString(7,  aluno.getUf());
            stmt.setString(8,  aluno.getCelular());
            stmt.setString(9,  aluno.getCurso());
            stmt.setString(10, aluno.getCampus());
            stmt.setString(11, aluno.getPeriodo());
            stmt.setString(12, aluno.getRgm());   

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Dados do aluno atualizados com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null,
                    "Nenhum aluno encontrado com o RGM informado.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao alterar aluno:\n" + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

        
    public void excluir(String rgm) {
        String sql = "DELETE FROM alunos WHERE rgm = ?";

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rgm);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null,
                    "Aluno e todos os registros vinculados excluídos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null,
                    "Nenhum aluno encontrado com o RGM: " + rgm,
                    "Não Encontrado", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao excluir aluno:\n" + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
