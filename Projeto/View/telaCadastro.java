package Projeto.View;

import Projeto.Control.MainUserDAO;
import Projeto.Model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaCadastro extends JFrame {
    private JTextField textLogin;
    private JPasswordField password;
    private JCheckBox verSenhaCheckBox;
    private JButton limparButton;
    private JButton cadastrarButton;
    private JPanel panel1;
    private JPanel panelMain;
    private JPanel panel2;
    private JPanel panel3;


    public telaCadastro(){
        setTitle("Tela de Cadastro");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        add(panelMain);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textLogin.getText();
                char[] senha = password.getPassword();

                if (camposPreenchidos()){
                    Usuario u = new Usuario();
                    MainUserDAO insert = new MainUserDAO();

                    u.setLogin(login);
                    u.setSenha(new String(senha));

                    insert.createUser(u);

                    limparCampos();

                    dispose();

                }else {
                    JOptionPane.showMessageDialog(null, "Preencha os campos!");
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
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
    }

    public void initComponents(){

    }

    private boolean camposPreenchidos(){
        String login = textLogin.getText();
        char[] senha = password.getPassword();

        return !login.isEmpty() && senha.length > 0;
    }

    private void limparCampos(){
        textLogin.setText("");
        password.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            telaCadastro tc = new telaCadastro();
            tc.setContentPane(tc.panelMain);
            tc.setVisible(true);
        });
    }
}
