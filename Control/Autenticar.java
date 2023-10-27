/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Projeto.BD.conexao;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import Projeto.View.*;

/**
 *
 * @author User
 */
public class Autenticar {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean validar(String login, String senha) {
        conn = conexao.getConnection();
        try {
            pst = conn.prepareStatement("select * from usuarios where "
                    + "login=? and senha=?");

            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        return false;
    }

    public ResultSet validarResultSet(String login, String senha) {
        conn = conexao.getConnection();
        try {
            pst = conn.prepareStatement("select * from usuarios where "
                    + "login=? and senha=?");

            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        return null;
    }
    
}
