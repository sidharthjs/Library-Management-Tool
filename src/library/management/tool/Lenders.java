/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library.management.tool;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author devac
 */
public class Lenders extends javax.swing.JFrame {

    /** Creates new form Lenders */
    String JDBCdriver = "com.mysql.jdbc.Driver";
    String dbserver = "jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull";
    String dbusername = "root";
    String dbpassword = "root";
    public Lenders() {
        initComponents();
        display();
    }

    public void display(){
        ResultSet rs = null;
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
        DefaultTableModel model = new DefaultTableModel();
        
        
        try{
            Class.forName(JDBCdriver);
        Connection con = DriverManager.getConnection(dbserver,dbusername,dbpassword);
        
        if(con!=null)
            System.out.print("Success");
        
        Statement stmt = con.createStatement();
        String sql = "select * from lend;";
        rs = stmt.executeQuery(sql);
        
        ResultSetMetaData md = rs.getMetaData();
        model.addColumn("S.no");
        model.addColumn("Staff id");
        model.addColumn("Staff name");
        model.addColumn("Accession no.");
        model.addColumn("Book title");
        /*for(int i=1;i<=md.getColumnCount();i++){
            model.addColumn(md.getColumnName(i));
        }*/
        
        
        
        int slno=1;
        while(rs.next()){
            
            String title="",name="";
            String acno = "",sid = "";
            acno = rs.getString("acno");
            sid = rs.getString("sid");
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            ResultSet rs2= stmt2.executeQuery("select * from book where acno = '"+acno+"';");
            rs2.next();
            title = rs2.getString("name");
            ResultSet rs3= stmt3.executeQuery("select * from staff where sid = '"+sid+"';");
            rs3.next();
            name = rs3.getString("sname");
            System.out.print("Title = "+rs2.getString("name"));
            System.out.print("Name = "+rs3.getString("sname"));
            model.addRow(new Object[]{slno,sid,name,acno,title});
            slno++;
        }
        this.jTable1.setModel(model);
        this.jTable1.setRowHeight(this.jTable1.getRowHeight() + 9);
        this.jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        this.jTable1.setFont(new Font("Serif",Font.PLAIN, 18));
        con.close();
        }catch(SQLException err){
            System.out.print("SQL exception - "+err.getMessage());
        }catch(ClassNotFoundException exp){
            System.out.print("Class not found exception - "+exp+"\n");
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Standing Lenders list :");

        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jLabel1.setFont (jLabel1.getFont().deriveFont (22.0f));
        jButton1.setFont (jButton1.getFont().deriveFont (22.0f));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Dashboard db = new Dashboard();
        db.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Lenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lenders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lenders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
