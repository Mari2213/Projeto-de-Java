package Projeto.Control;

import Projeto.DB.conexao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticar {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean validar (String login, String senha){
        conn = conexao.getConnection();
        try {
            pst = conn.prepareStatement("SELECT * FROM usuarios WHERE login=? and senha=?");

            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            if (rs.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
        return false;
    }

    public ResultSet validarResultSet(String login, String senha){
        conn = conexao.getConnection();
        try {
            pst = conn.prepareStatement("SELECT * FROM usuarios WHERE login=? AND senha=?");

            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            if (rs.next()){
                return rs;
            }else {
                return null;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
        return null;
    }
}
