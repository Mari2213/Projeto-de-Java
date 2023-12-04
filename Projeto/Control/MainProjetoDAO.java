package Projeto.Control;

import Projeto.DB.conexao;
import Projeto.Model.Projeto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainProjetoDAO {

    public void upadate(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;

        conn = conexao.getConnection();

        try{
            pst = conn.prepareStatement("UPDATE projetos SET evento = ?, coordenador = ?,campus = ?,titulo = ?," +
                    "estudante = ?, matricula = ?, cpf = ?,banco = ?,conta = ?, agencia = ?, celular = ?,email = ? WHERE codigo = ?");
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

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }
    }

    public void create(Projeto p){
        Connection conn = null;
        PreparedStatement pst = null;

        conn = conexao.getConnection();

        try{
            pst = conn.prepareStatement("INSERT INTO projetos (evento,coordenador,campus,titulo,estudante,matricula,cpf,banco,conta,agencia,celular,email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
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

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }
    }

    public List<Projeto> read(){

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        conn = conexao.getConnection();
        List<Projeto> prod = new ArrayList<>();

        try {
            pst = conn.prepareStatement("SELECT * FROM projetos");
            rs = pst.executeQuery();

            while (rs.next()){
                Projeto p = new Projeto();

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
        }catch (SQLException ex){
            Logger.getLogger(MainProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    public List<Projeto> readForDesc(String desc){

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        conn = conexao.getConnection();
        List<Projeto> prod = new ArrayList<>();

        try {
            pst = conn.prepareStatement("SELECT * FROM projetos WHERE evento LIKE ?");
            pst.setString(1,"%"+desc+"%");
            rs = pst.executeQuery();

            while (rs.next()){
                Projeto p = new Projeto();

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
        }catch (SQLException ex){
            Logger.getLogger(MainProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    public void delete(Projeto p){

        Connection conn = null;
        PreparedStatement pst = null;

        conn = conexao.getConnection();

        try {
            pst = conn.prepareStatement("DELETE FROM projetos WHERE codigo = ?");

            pst.setInt(1, p.getCodigo());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao excluir: "+ex);
        }

    }

}
