package At2_Viewn;

import At2_Code.Conexao;
import At2_Code.inserirDados;
import At2_Code.tableDados;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author TheDe
 */
//Mecanismo de pesquisa no banco de dados Sql
public class searchFilmes extends javax.swing.JFrame {

    //conectando a classe de interação com banco de dados SQL
    Conexao conector = new Conexao();
    //criando uma lista para carregar os arquivos armazenados no banco de dados
    List<inserirDados> dbLoad = new ArrayList<>();

    //definindo o modelo que a tabela deve seguir
    private final String[] tableColumns = {"id", "Titulo", "Data de lançamento", "Categorias"};
    DefaultTableModel tableModel = new DefaultTableModel(tableColumns, 0);

    //funções que iniciam junto do sistema
    public searchFilmes() {
        initComponents();
        tblFilmes.setEnabled(false);
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFilmes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        setTitle("CenaflixApp");
        jLabel1 = new javax.swing.JLabel();
        _comboMode = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        _Pesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jScrollPane1.setViewportView(tblFilmes);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pesquisar filmes");

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CENAFLIX");

        _comboMode.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        _comboMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "Titulo", "Data de Lançamento", "Categorias" }));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Pesquisar filmes por:");

        txtPesquisa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Pesquisa:");
        jLabel5.setToolTipText("");

        _Pesquisar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        _Pesquisar.setText("Pesquisar");
        _Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _PesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(_Pesquisar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPesquisa)
                            .addComponent(_comboMode, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_comboMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_Pesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //mecanismo de pequisa, leva em conta a coluna selecionada pelo comboBox
    private void _PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__PesquisarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblFilmes.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);

        int categoria = _comboMode.getSelectedIndex();
        if (categoria == 0) {
            tblFilmes.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(txtPesquisa.getText(), 0));
        } else if (categoria == 1) {
            tblFilmes.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(txtPesquisa.getText(), 1));
        } else if (categoria == 2) {
            tblFilmes.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(txtPesquisa.getText(), 2));
        } else if (categoria == 3) {
            tblFilmes.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(txtPesquisa.getText(), 3));
        } else {
            System.err.println("Isso não deveria ocorrer, Erro: Not selected Index");
        }
    }//GEN-LAST:event__PesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(searchFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _Pesquisar;
    private javax.swing.JComboBox<String> _comboMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFilmes;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
