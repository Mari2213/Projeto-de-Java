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
public class Excluir {
    
    public void delete(Projetos p) {
        Connection conn=null;
        PreparedStatement pst=null;
        
        conn = conexao.getConnection();        
        
        try {
            pst = conn.prepareStatement("DELETE FROM projetos WHERE codigo = ?");
            
            pst.setInt(1, p.getCodigo());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir:"+ ex);
        }     
    }
    
}
