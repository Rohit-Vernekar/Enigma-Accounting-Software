/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
public class HSN_List extends javax.swing.JFrame {

    /**
     * Creates new form HSN_List
     */
    public HSN_List() {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jTextField1.requestFocus();
        jTextField1.setText(Product_Page.jTextField5.getText());
        jTextField1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN){
                    jTable1.requestFocus();
                    try{
                        Robot r=new Robot();
                        r.keyPress(KeyEvent.VK_DOWN);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    new Salt_List().setVisible(true);
                    Product_Page.jTextField5.setText("");
                    Product_Page.jTextField6.setText("");
                    Product_Page.jTextField7.setText("");
                    Product_Page.jTextField10.setText("");
                    HSN_List.this.dispose();
                }
            }
            public void keyReleased(KeyEvent e) {
                findHSN();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE)){
                    jTextField1.requestFocus();
                    findHSN();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            findHSN();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    Product_Page.jTextField5.setText(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
                    Product_Page.jTextField6.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
                    Product_Page.jTextField7.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
                    Product_Page.jTextField10.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
                    Product_Page.hsn=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString() +"-" +String.valueOf((int) Double.parseDouble(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(),3))));
                    Product_Page.jTextField8.requestFocus();
                    Product_Page.jTextField8.selectAll();
                    HSN_List.this.dispose();
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    new Salt_List().setVisible(true);
                    Product_Page.jTextField5.setText("");
                    Product_Page.jTextField6.setText("");
                    Product_Page.jTextField7.setText("");
                    Product_Page.jTextField10.setText("");
                    HSN_List.this.dispose();
                }
            }
        });
        
        findHSN();
        
        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
    }

    private void findHSN(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"HSN CODE","SGST","CGST","GST"});
        String sql="SELECT CODE,CGST,SGST,GST FROM HSN WHERE CODE LIKE '" +jTextField1.getText() +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            Object row[]=new Object[4];
            while(rs.next()){
                row[0]=rs.getString("code");
                row[1]=rs.getString("sgst");
                row[2]=rs.getString("cgst");
                row[3]=rs.getString("gst");
                model.addRow(row);
            }
            jTable1.setModel(model);
            jTable1.setDefaultEditor(Object.class, null);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HSN CODE", "SGST", "CGST", "GST"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("SEARCH : ");

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HSN-CODE LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(HSN_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HSN_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HSN_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HSN_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HSN_List().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
