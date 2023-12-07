package Projeto.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaPrincipal extends JFrame {


    private JPanel panel1;
    public JTextField textUser;
    public JTextField textData;
    private JLabel lbData;
    private JLabel lbUser;
    private JLabel img;
    private JPanel panel2;
    private JPanel panel3;

    public telaPrincipal(){
        setTitle("Tela Principal");
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();

        JMenuBar menuBar = new JMenuBar();
        JMenu gestaoMenu = new JMenu("Gestão");
        JMenu projetosMenu = new JMenu("Projetos");
        JMenuItem cadastrarItem = new JMenuItem("Cadastrar");

        gestaoMenu.add(projetosMenu);
        projetosMenu.add(cadastrarItem);
        menuBar.add(gestaoMenu);
        setJMenuBar(menuBar);

        cadastrarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaProjetos tp = new telaProjetos();
                tp.textUserProj.setText(textUser.getText());
                tp.textDataProj.setText(textData.getText());
                tp.setVisible(true);
                dispose();
            }
        });

        add(panel1);
    }

    private void initComponents(){
        textUser.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        textData.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            telaPrincipal tp = new telaPrincipal();
            tp.setContentPane(tp.panel1);
            tp.setVisible(true);
        });
    }
}
