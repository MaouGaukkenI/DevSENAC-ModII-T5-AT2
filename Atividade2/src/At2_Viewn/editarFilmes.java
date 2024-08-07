/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package At2_Viewn;

import At2_Code.Conexao;
import At2_Code.inserirDados;
import At2_Code.tableDados;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TheDe
 */
//JFrame responsavel pela edição dos filmes do Banco de Dados
public class editarFilmes extends javax.swing.JFrame {

    //conectando a classe de interação com banco de dados SQL
    Conexao conector = new Conexao();
    //criando uma lista para carregar os arquivos armazenados no banco de dados
    List<inserirDados> dbLoad = new ArrayList<>(); 

    //definindo o modelo que a tabela deve seguir
    private final String[] tableColumns = {"id", "Titulo", "Data de lançamento", "Categorias"};
    DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0);

    //conectando a Classe de interação com a tabela modelo
    tableDados tbd = new tableDados();

    //funções que iniciam junto do sistema
    public editarFilmes() {
        initComponents();
        loadDados();
    }

    //Lista os filmes dentro da tabela modelo de acordo com as situações abaixo
    public void listar() {
        if (dbLoad.isEmpty()) {
            dbLoad.addAll(tableDados.listar());
            System.err.println("Ocorreu a opção a");
        } else if (tblFilmes.getRowCount() != dbLoad.size()) {
            dbLoad.addAll(tableDados.listar());
            System.err.println("Ocorreu a opção b: \nnumero de linhas na tabela: " + tblFilmes.getRowCount() + "\nnumero de linhas no banco de dados: " + dbLoad.size());
        } else {
            System.err.println("Deu certo aqui!!");
        }
    }

    //Limpa a listagem aterior e realiza uma nova para atualização dos dados após modificações
    public void loadDados() {
        dbLoad.clear();
        System.err.println(tableDados.listar());
        listar();
        inserirDados con;
        if (dbLoad != null) {
            for (int i = 0; i < dbLoad.size(); i++) {
                con = dbLoad.get(i);
                String id = String.valueOf(con.getId());
                String[] rowData = {id, con.getTitulo(), con.getDataLancamento(), con.getCategoria()};
                tableModel.addRow(rowData);
            }
            tblFilmes.setModel(tableModel);
        }
    }

    //Capita o id referente ao banco de dados da linha selecionada na tabela
    public int getIdRow() {
        if (tblFilmes.getSelectedRow() != -1) {
            int idLinha = tblFilmes.getSelectedRow();
            int idDB;
            try {
                idDB = (int) tbd.getValueAt(idLinha, 0);
            } catch (IndexOutOfBoundsException ex) {
                idDB = -1;
                System.err.println("Erro ao deletar linha, Erro: " + ex.getMessage());
            }
            return idDB;
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
            return -1;
        }
    }

    //Atualiza os dados na tabela modelo
    public void atualizarDados(String a, String b, String c) {
        tbd.setValueAt(a, tblFilmes.getSelectedRow(), 1);
        tbd.setValueAt(b, tblFilmes.getSelectedRow(), 2);
        tbd.setValueAt(c, tblFilmes.getSelectedRow(), 3);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        setTitle("CenaflixApp");
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFilmes = new javax.swing.JTable();
        txtData = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Categorias:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Data de lançamento:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Titulo:");

        txtCategoria.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtTitulo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnEditar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnEditar.setText("Atualizar Dados");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnDeletar.setText("Deletar Linha");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CENAFLIX");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pesquisar filmes");

        tblFilmes.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Titulo", "Data de lançamento", "Categorias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblFilmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFilmesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblFilmesMouseReleased(evt);
            }
        });
        tblFilmes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFilmesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblFilmes);

        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeletar)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtData))
                .addContainerGap(216, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnDeletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Mecanismo de deletar filmes do Banco de dados
    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        //se o id da linha do banco de dados for valido ele realiza o Mecanismo de exclusão
        if (getIdRow() >= 0) {
            conector.deletarLinha(getIdRow(), tblFilmes.getSelectedRow());
            //Atualizando a tabela apos deletar uma linha
            int selectedRow = tblFilmes.getSelectedRow();
            if (selectedRow != -1) {
                ((DefaultTableModel) tblFilmes.getModel()).removeRow(selectedRow);
                ((DefaultTableModel) tblFilmes.getModel()).fireTableRowsDeleted(selectedRow, selectedRow);
            }
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    //Mecanismo de edição dos dados
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        //Definindo as variaveis enviadas pro SQL
        String a = "'" + txtTitulo.getText() + "'";
        String b;
        String c = "'" + txtCategoria.getText() + "'";

        //Definindo as variaveis enviadas para a tabela modelo
        String ab = txtTitulo.getText();
        String bb = txtData.getText();
        String cb = txtCategoria.getText();

        //Pegando o id da linha no SQL
        int idRow = getIdRow();

        try {
            //Analizando se é uma linha valida selecionada na tabela
            if (tblFilmes.getSelectedRow() != -1) {
                //verificando a variavel data, para ver se ela contem os digitos nescessarios para entrar na formatação
                String data = txtData.getText();
                String dataN = data.replaceAll("[^0-9]", "");
                //se a linha selecionada na tabela for valida, os dados enviados não podem estar fora da formatação
                if ("".equals(txtTitulo.getText()) || "".equals(txtCategoria.getText()) || dataN.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Existe problema com os dados enviados, confira e tente novamente");
                } else {
                    //Após a verificação, aplicamos a formatação final a data para que ela seja enviada sem erros ao SQL
                    String dataF = "";
                    if (dataN.length() == 8) {
                        String x = dataN.substring(0, 2) + "/";
                        String y = dataN.substring(2, 4) + "/";
                        String z = dataN.substring(4);
                        dataF = x + y + z;
                    }

                    //fazendo o envio das variaveis atualizadas ao SQL
                    b = "STR_TO_DATE('" + dataF + "', '%d/%m/%Y')";
                    conector.editarFilme("nome", a, idRow);
                    conector.editarFilme("datalancamento", b, idRow);
                    conector.editarFilme("categoria", c, idRow);
                    atualizarDados(ab, bb, cb);

                    //Atualizando a tabela "tblFilmes"
                    DefaultTableModel model = (DefaultTableModel) tblFilmes.getModel();
                    model.setRowCount(0);

                    loadDados();
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao editar dados, Erro: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    //Mecanismos de capitação dos dados da linha para a caixa de texto
    private void tblFilmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFilmesMouseClicked
        int linha = tblFilmes.getSelectedRow();
        txtTitulo.setText((String) tbd.getValueAt(linha, 1));
        txtData.setText((String) tbd.getValueAt(linha, 2));
        txtCategoria.setText((String) tbd.getValueAt(linha, 3));
    }//GEN-LAST:event_tblFilmesMouseClicked

    private void tblFilmesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFilmesKeyReleased
        int linha = tblFilmes.getSelectedRow();
        txtTitulo.setText((String) tbd.getValueAt(linha, 1));
        txtData.setText((String) tbd.getValueAt(linha, 2));
        txtCategoria.setText((String) tbd.getValueAt(linha, 3));
    }//GEN-LAST:event_tblFilmesKeyReleased

    private void tblFilmesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFilmesMouseReleased
        int linha = tblFilmes.getSelectedRow();
        txtTitulo.setText((String) tbd.getValueAt(linha, 1));
        txtData.setText((String) tbd.getValueAt(linha, 2));
        txtCategoria.setText((String) tbd.getValueAt(linha, 3));
    }//GEN-LAST:event_tblFilmesMouseReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editarFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editarFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editarFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editarFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editarFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFilmes;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
