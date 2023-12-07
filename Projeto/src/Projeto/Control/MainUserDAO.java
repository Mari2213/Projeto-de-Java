package Projeto.Control;

import Projeto.DB.conexao;
import Projeto.Model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainUserDAO {

    public void createUser(Usuario u){
        Connection conn = null;
        PreparedStatement pst = null;

        conn = conexao.getConnection();

        try {
            pst = conn.prepareStatement("INSERT INTO usuarios (login,senha) VALUES (?,?)");
            pst.setString(1, u.getLogin());
            pst.setString(2, u.getSenha());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuario: "+ex);
        }
    }
}
