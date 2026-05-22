package br.projeto.academico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NotaFaltaDAO {

    // Método para salvar nota/falta vinculada ao RGM do Aluno
    public void salvar(NotaFalta nf) {
        String sql = "INSERT INTO notas_faltas (rgm_aluno, disciplina, semestre, nota, faltas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nf.getRgmAluno());
            stmt.setString(2, nf.getDisciplina());
            stmt.setString(3, nf.getSemestre());
            stmt.setDouble(4, nf.getNota());
            stmt.setInt(5, nf.getFaltas());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Notas e faltas lançadas com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: Verifique se o RGM do aluno está cadastrado.\n" + e.getMessage());
        }
    }
    
    public List<NotaFalta> listarNotasPorRgm(String rgm) {
        String sql = "SELECT * FROM notas_faltas WHERE rgm_aluno = ?";
        List<NotaFalta> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.obtenerConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, rgm);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NotaFalta nf = new NotaFalta();
                    nf.setId(rs.getInt("id"));
                    nf.setRgmAluno(rs.getString("rgm_aluno"));
                    nf.setDisciplina(rs.getString("disciplina"));
                    nf.setSemestre(rs.getString("semestre"));
                    nf.setNota(rs.getDouble("nota"));
                    nf.setFaltas(rs.getInt("faltas"));
                    
                    lista.add(nf); 
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar histórico de notas para o boletim: " + e.getMessage());
        }
        return lista;
    }
}