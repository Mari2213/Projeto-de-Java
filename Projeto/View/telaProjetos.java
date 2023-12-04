package Projeto.View;

import Projeto.Control.MainProjetoDAO;
import Projeto.Model.Projeto;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class telaProjetos extends JFrame{
    private JTextField textEve;
    private JTextField textCoor;
    private JTextField textCampus;
    private JTextField textTit;
    private JTextField textEst;
    private JTextField textMatr;
    private JTextField textCPF;
    private JTextField textBanco;
    private JTextField textConta;
    private JTextField textAge;
    private JTextField textCel;
    private JTextField textEmail;
    private JPanel panelMain;
    private JPanel panel1;
    private JPanel panel2;
    private JButton btnNovo;
    private JButton btnLimpar;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JTextField textBuscar;
    private JButton btnBuscar;
    private JTable tableProj;
    private JPanel panel3;
    public JTextField textUserProj;
    public JTextField textDataProj;
    private JButton btnImprimir;
    private JPanel panel4;
    private JPanel panel5;


    public telaProjetos(){
        setTitle("Tela de Projetos");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable();
        DefaultTableModel modelo = (DefaultTableModel) tableProj.getModel();
        tableProj.setRowSorter(new TableRowSorter<>(modelo));
        readTable();
        initComponents();
        btnImprimir.setEnabled(false);

        add(panelMain);
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnImprimir.setEnabled(false);
                if (camposPreenchidos()){
                    Projeto p = new Projeto();
                    MainProjetoDAO insert = new MainProjetoDAO();

                    p.setEvento(textEve.getText());
                    p.setCoordenador(textCoor.getText());
                    p.setCampus(textCampus.getText());
                    p.setTitulo(textTit.getText());
                    p.setEstudante(textEst.getText());
                    p.setMatricula(textMatr.getText());
                    p.setCpf(textCPF.getText());
                    p.setBanco(textBanco.getText());
                    p.setContaCorrente(textConta.getText());
                    p.setAgencia(textAge.getText());
                    p.setCelular(textCel.getText());
                    p.setEmail(textEmail.getText());

                    insert.create(p);

                    readTable();

                    limparCampos();
                }else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campus!");
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnImprimir.setEnabled(false);
                if (tableProj.getSelectedRow() != -1){
                    Projeto p = new Projeto();
                    MainProjetoDAO alt = new MainProjetoDAO();

                    p.setEvento(textEve.getText());
                    p.setCoordenador(textCoor.getText());
                    p.setCampus(textCampus.getText());
                    p.setTitulo(textTit.getText());
                    p.setEstudante(textEst.getText());
                    p.setMatricula(textMatr.getText());
                    p.setCpf(textCPF.getText());
                    p.setBanco(textBanco.getText());
                    p.setContaCorrente(textConta.getText());
                    p.setAgencia(textAge.getText());
                    p.setCelular(textCel.getText());
                    p.setEmail(textEmail.getText());

                    p.setCodigo((int) tableProj.getValueAt(tableProj.getSelectedRow(), 0));

                    alt.upadate(p);

                    readTable();

                    limparCampos();
                }else {
                    JOptionPane.showMessageDialog(null, "Selecione um projeto para editar!");
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnImprimir.setEnabled(false);
                if (tableProj.getSelectedRow() != -1){
                    int resp = JOptionPane.showConfirmDialog(null, "Deseja excluir o projeto", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if  (resp == JOptionPane.YES_OPTION){
                        Projeto p = new Projeto();
                        MainProjetoDAO exc = new MainProjetoDAO();

                        p.setCodigo((int) tableProj.getValueAt(tableProj.getSelectedRow(),0));

                        exc.delete(p);

                        readTable();

                        limparCampos();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Selecione um projeto para excluir!");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();

                btnEditar.setEnabled(true);
                btnNovo.setEnabled(true);
                btnImprimir.setEnabled(false);

                readTable();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditar.setEnabled(false);
                btnNovo.setEnabled(false);
                btnImprimir.setEnabled(true);

                readTableForDesc(textBuscar.getText());
            }
        });

        tableProj.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tableProj.getSelectedRow() != -1){
                    textEve.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 1).toString());
                    textCoor.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 2).toString());
                    textCampus.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 3).toString());
                    textTit.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 4).toString());
                    textEst.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 5).toString());
                    textMatr.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 6).toString());
                    textCPF.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 7).toString());
                    textBanco.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 8).toString());
                    textConta.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 9).toString());
                    textAge.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 10).toString());
                    textCel.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 11).toString());
                    textEmail.setText(tableProj.getValueAt(tableProj.getSelectedRow(), 12).toString());
                }
            }
        });

        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarPDF();
            }
        });
    }

    private void readTable(){
        DefaultTableModel modelo = (DefaultTableModel) tableProj.getModel();
        modelo.setNumRows(0);

        MainProjetoDAO mpd = new MainProjetoDAO();

        for (Projeto p : mpd.read()){

            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getEvento(),
                    p.getCoordenador(),
                    p.getCampus(),
                    p.getTitulo(),
                    p.getEstudante(),
                    p.getMatricula(),
                    p.getCpf(),
                    p.getBanco(),
                    p.getContaCorrente(),
                    p.getAgencia(),
                    p.getCelular(),
                    p.getEmail(),
            });
        }
    }

    private void readTableForDesc(String desc){
        DefaultTableModel modelo = (DefaultTableModel) tableProj.getModel();
        modelo.setNumRows(0);

        MainProjetoDAO mpd = new MainProjetoDAO();

        for (Projeto p : mpd.readForDesc(desc)){

            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getEvento(),
                    p.getCoordenador(),
                    p.getCampus(),
                    p.getTitulo(),
                    p.getEstudante(),
                    p.getMatricula(),
                    p.getCpf(),
                    p.getBanco(),
                    p.getContaCorrente(),
                    p.getAgencia(),
                    p.getCelular(),
                    p.getEmail(),
            });
        }
    }

    private boolean camposPreenchidos(){
        return !textEve.getText().isEmpty() && !textCoor.getText().isEmpty()
                && !textCampus.getText().isEmpty() && !textTit.getText().isEmpty()
                && !textEst.getText().isEmpty() && !textMatr.getText().isEmpty()
                && !textCPF.getText().isEmpty() && !textBanco.getText().isEmpty()
                && !textConta.getText().isEmpty() && !textAge.getText().isEmpty()
                && !textCel.getText().isEmpty() && !textEmail.getText().isEmpty();
    }

    private void limparCampos(){
        textEve.setText("");
        textCoor.setText("");
        textCampus.setText("");
        textTit.setText("");
        textEst.setText("");
        textMatr.setText("");
        textCPF.setText("");
        textBanco.setText("");
        textConta.setText("");
        textAge.setText("");
        textCel.setText("");
        textEmail.setText("");
        textBuscar.setText("");
    }

    private void createTable(){
        tableProj.setModel(new DefaultTableModel(
                null,
                new String[]{"Id", "Evento", "Coordenador", "Campus", "Título",
                        "Estudante", "Matrícula", "CPF", "Nº Banco", "Conta Corrente",
                        "Agência", "Celular", "Email"}
        ));
        TableColumnModel columns = tableProj.getColumnModel();
        columns.getColumn(0).setMinWidth(50);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
        columns.getColumn(4).setCellRenderer(centerRenderer);
        columns.getColumn(5).setCellRenderer(centerRenderer);
        columns.getColumn(6).setCellRenderer(centerRenderer);
        columns.getColumn(7).setCellRenderer(centerRenderer);
        columns.getColumn(8).setCellRenderer(centerRenderer);
        columns.getColumn(9).setCellRenderer(centerRenderer);
        columns.getColumn(10).setCellRenderer(centerRenderer);
        columns.getColumn(11).setCellRenderer(centerRenderer);
        columns.getColumn(12).setCellRenderer(centerRenderer);
    }

    private void gerarPDF(){
        // Criando o PDF e colocando o formato desejável
        Document document = new Document(PageSize.A4);
        document.setMargins(40f,40f,40f,40f);

        String use = textUserProj.getText();
        String date = textDataProj.getText();

        String dateUser = "User logado: "+use+" || Data: "+date;

        // Gerar pdf
        try {
            PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));

            document.open();

            Image img = Image.getInstance("C:\\Users\\User\\Documents\\Java teste\\src\\Projeto\\Img\\logo3.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(200,200);
            document.add(img);

            Font fontDateUser = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GRAY);
            Font fontTitleRel = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            document.add(new Paragraph(dateUser, fontDateUser));
            document.add(new Paragraph("Relatório", fontTitleRel));
            document.add(new Paragraph(" "));

            //tabela
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100); // largura da tabela

            Font fontHeaders = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
            String[] headers = {"Evento", "Coordenador", "Campus", "Título", "Estudante"};

            for (String header : headers){
                PdfPCell headerCell = new PdfPCell(new Phrase(header, fontHeaders));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // aliamento dos titulos no centro
                table.addCell(headerCell);
            }

            Font fontCell = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

            // Populando o relatório em PDF
            MainProjetoDAO Desc = new MainProjetoDAO();

            for (Projeto p : Desc.readForDesc(textBuscar.getText())){

                table.addCell(new Phrase(p.getEvento(), fontCell));
                table.addCell(new Phrase(p.getCoordenador(), fontCell));
                table.addCell(new Phrase(p.getCampus(), fontCell));
                table.addCell(new Phrase(p.getTitulo(), fontCell));
                table.addCell(new Phrase(p.getEstudante(), fontCell));
            }

            document.add(table);
        }catch (DocumentException | IOException e){
            e.printStackTrace();
        }finally {
            document.close();
        }

        //abrir pdf no leitor de pdf do sistema
        try {
            Desktop.getDesktop().open(new File("relatorio.pdf"));
        }catch (Exception e2){
            System.out.println(e2);
        }
    }

    private void initComponents(){
        textEve.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textCoor.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textCampus.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textTit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textEst.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textMatr.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textCPF.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textBanco.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textConta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textAge.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textCel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textEmail.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        textBuscar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        textUserProj.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        textDataProj.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            telaProjetos tp = new telaProjetos();
            tp.setContentPane(tp.panelMain);
            tp.setVisible(true);
        });
    }
}
