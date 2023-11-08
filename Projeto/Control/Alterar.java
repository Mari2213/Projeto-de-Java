/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Projeto.BD.conexao;
import Projeto.Model.Projetos;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author Marianna
 */
public class Alterar {
    
    public void update(Projetos p) {
        Connection conn=null;
        PreparedStatement pst=null;
        
        conn = conexao.getConnection();        
        
        try {
            pst = conn.prepareStatement("UPDATE projetos set evento = ?,coordenador = ?,campus = ?,titulo = ?,estudante = ?,matricula = ?,cpf = ?,banco = ?,conta = ?,agencia = ?,celular = ?,email = ? WHERE codigo = ?");
            pst.setString(1, p.getEvento());
            pst.setString(2, p.getCoordenador());
            pst.setString(3, p.getCampus());
            pst.setString(4, p.getTitulo());
            pst.setString(5, p.getEstudante());
            pst.setString(6, p.getMatricula());
            pst.setString(7, p.getCpf());
            pst.setString(8, p.getBanco());
            pst.setString(9, p.getContaCorrente());
            pst.setString(10, p.getAgencia());
            pst.setString(11, p.getCelular());
            pst.setString(12, p.getEmail());
            pst.setInt(13, p.getCodigo());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar:"+ ex);
        }     
    }
}
