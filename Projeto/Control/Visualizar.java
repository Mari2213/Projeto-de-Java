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
import Projeto.Model.Projetos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Visualizar {
    
    public List<Projetos> read(){
        
       Connection conn=null;
       PreparedStatement pst=null;
       ResultSet rs = null;

       conn = conexao.getConnection();
       List<Projetos> prod = new ArrayList();
       
        try {
            pst = conn.prepareStatement("SELECT * FROM projetos");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Projetos p = new Projetos();
                
                p.setCodigo(rs.getInt("codigo"));
                p.setEvento(rs.getString("evento"));
                p.setCoordenador(rs.getString("coordenador"));
                p.setCampus(rs.getString("campus"));
                p.setTitulo(rs.getString("titulo"));
                p.setEstudante(rs.getString("estudante"));
                p.setMatricula(rs.getString("matricula"));
                p.setCpf(rs.getString("cpf"));
                p.setBanco(rs.getString("banco"));
                p.setContaCorrente(rs.getString("conta"));
                p.setAgencia(rs.getString("agencia"));
                p.setCelular(rs.getString("celular"));
                p.setEmail(rs.getString("email"));
                
                prod.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Visualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }
    
    public List<Projetos> readForDesc(String desc){
        
       Connection conn=null;
       PreparedStatement pst=null;
       ResultSet rs = null;

       conn = conexao.getConnection();
       List<Projetos> prod = new ArrayList();
       
        try {
            pst = conn.prepareStatement("SELECT * FROM projetos WHERE evento LIKE ?");
            pst.setString(1, "%"+desc+"%");
            rs = pst.executeQuery();
            
            while(rs.next()){
                Projetos p = new Projetos();
                
                p.setCodigo(rs.getInt("codigo"));
                p.setEvento(rs.getString("evento"));
                p.setCoordenador(rs.getString("coordenador"));
                p.setCampus(rs.getString("campus"));
                p.setTitulo(rs.getString("titulo"));
                p.setEstudante(rs.getString("estudante"));
                p.setMatricula(rs.getString("matricula"));
                p.setCpf(rs.getString("cpf"));
                p.setBanco(rs.getString("banco"));
                p.setContaCorrente(rs.getString("conta"));
                p.setAgencia(rs.getString("agencia"));
                p.setCelular(rs.getString("celular"));
                p.setEmail(rs.getString("email"));
                
                prod.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Visualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }
    
}
