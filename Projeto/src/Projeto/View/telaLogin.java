package Projeto.View;

import Projeto.Control.Autenticar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Projeto.Control.ControlDate.SincronizarData;

public class telaLogin extends JFrame {

    private JTextField textLogin;
    private JLabel lbPass;
    private JLabel lblogin;
    private JPasswordField password;
    private JButton btnLogin;
    private JPanel panel;
    private JButton fazerCadastroButton;
    private JButton limparButton;
    private JCheckBox verSenhaCheckBox;

    Autenticar controle = new Autenticar();


    public telaLogin() {
        setTitle("Tela login");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textLogin.getText();
                char[] pass = password.getPassword();

                try {
                    try (ResultSet rsLogin = controle.validarResultSet(login, new String(pass))) {
                        if (rsLogin != null) {
                            int resp = JOptionPane.showConfirmDialog(null, "User: " + rsLogin.getString("login"), "Confirmação: ", JOptionPane.YES_NO_OPTION);

                            if (resp == JOptionPane.YES_OPTION) {
                                telaPrincipal tP = new telaPrincipal();
                                tP.setVisible(true);
                                tP.textUser.setText(rsLogin.getString("login"));
                                tP.textData.setText(SincronizarData());
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro login/senha");
                        }
                    }
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Erro: "+ex);
                }
            }
        });

        verSenhaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verSenhaCheckBox.isSelected()){
                    password.setEchoChar((char) 0);
                }else {
                    password.setEchoChar('*');
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLogin.setText("");
                password.setText("");
                textLogin.requestFocus();
            }
        });

        fazerCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastro tc = new telaCadastro();

                tc.setVisible(true);
            }
        });
    }

    public void initComponents(){
        textLogin.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            telaLogin tl = new telaLogin();
            tl.setContentPane(tl.panel);
            tl.setVisible(true);
        });
    }
}
