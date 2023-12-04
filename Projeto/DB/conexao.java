package Projeto.DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class conexao {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //conn = DriverManager.getConnection("jdbc:mysql://mysql247.umbler.com:41890/projeto","marianna","pereiraSILVA98");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/projeto","root","");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Erro: "+e);
            }
        }
        return conn;
    }

}
