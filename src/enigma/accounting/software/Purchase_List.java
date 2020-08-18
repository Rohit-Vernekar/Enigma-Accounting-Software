/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
public class Purchase_List extends javax.swing.JFrame {
    public static int purchasebillid;
    public static int flag;
    /**
     * Creates new form Purchase_List
     * @param f
     */
    public Purchase_List(int f) {
        initComponents();
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        flag=f;
        if(flag==1)
            jLabel2.setText("PURCHASE RETURN BILL (DEBIT NOTE)");
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
        if(flag==0)
            findPurchaseBill();
        else
            findPurchaseRetBill();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN){
                    jTable1.requestFocus();
                    try{
                        Robot r=new Robot();
                        r.keyPress(KeyEvent.VK_DOWN);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }   
                if( e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(flag==0)
                    findPurchaseBill();
                else
                    findPurchaseRetBill();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    if(flag==0)
                        findPurchaseBill();
                    else
                        findPurchaseRetBill();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            if(flag==0)
                                findPurchaseBill();
                            else
                                findPurchaseRetBill();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    if(flag==0){
                        Purchase_Page pp=new Purchase_Page(1);
                        pp.setVisible(true);

                        String main="SELECT * FROM PURCHASE WHERE AGENCY=? AND INVNO=?;";
                        Connection conn=MySqlConnect.ConnectDB();
                        try {
                            PreparedStatement ps = conn.prepareStatement(main);
                            ps.setString(1, jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                            ps.setString(2, jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                            ResultSet rs=ps.executeQuery();
                            rs.next();
                            purchasebillid=rs.getInt("puchasebillid");
                            pp.pbid=purchasebillid;
                            pp.aname=rs.getString("agency");
                            if(rs.getBoolean("cash"))
                                pp.due=0;
                            else
                                pp.due=rs.getDouble("totalamt");

                            Purchase_Page.jXDatePicker1.setDate(rs.getDate("idate"));
                            Purchase_Page.jTextField1.setText(rs.getString("agency"));
                            Purchase_Page.jTextField2.setText(rs.getString("invno"));
                            if(rs.getBoolean("cash"))
                                Purchase_Page.jComboBox1.setSelectedIndex(0);
                            else
                                Purchase_Page.jComboBox1.setSelectedIndex(1);
                            Purchase_Page.jTextField7.setText(String.valueOf(rs.getDouble("puramt")));
                            Purchase_Page.jTextField8.setText(String.valueOf(rs.getDouble("discount")));
                            Purchase_Page.jTextField9.setText(String.valueOf(rs.getDouble("gst")/2));
                            Purchase_Page.jTextField10.setText(String.valueOf(rs.getDouble("gst")/2));
                            Purchase_Page.jTextField11.setText(String.valueOf(rs.getDouble("totalamt")));

                            String sql2="SELECT * FROM PURCPROD WHERE PURCHASEBILLID=" +purchasebillid +";";
                            Statement s=conn.createStatement();
                            ResultSet rs2=s.executeQuery(sql2);
                            while(rs2.next()){
                                String sql3="SELECT * FROM PRODUCT WHERE PID=" +rs2.getInt("pid") +";";
                                Statement s3=conn.createStatement();
                                ResultSet rs3=s3.executeQuery(sql3);
                                rs3.next();

                                String sql4="SELECT CODE,SGST,CGST,GST FROM HSN WHERE HSN='" +rs3.getString("hsn") +"';";
                                Statement s4=conn.createStatement();
                                ResultSet rs4=s4.executeQuery(sql4);
                                rs4.next();

                                DefaultTableModel model=(DefaultTableModel) Purchase_Page.jTable1.getModel();
                                model.addRow(new Object[]{rs3.getString("pname"), rs3.getInt("pkg"), rs4.getString("code"), rs2.getString("batchno"), rs2.getString("exp"), rs2.getInt("qty"), rs2.getInt("freeqty"), rs2.getDouble("mrp"), rs2.getDouble("trp"), rs2.getDouble("discount"), rs4.getDouble("sgst"), rs4.getDouble("cgst"), rs2.getDouble("trp") * rs2.getInt("qty")});
                            }
                        } 
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }

                        pp.setEnabled(false);
                        List_Button l=new List_Button(pp);
                        List_Button.pbid=purchasebillid;
                        List_Button.flag=1;
                        l.setVisible(true);
                        Purchase_List.this.dispose(); 
                    }
                    else{
                        Purchase_Page pp=new Purchase_Page(3);
                        pp.setVisible(true);

                        String main="SELECT * FROM PURCHASERETURN WHERE AGENCY=? AND INVNO=?;";
                        Connection conn=MySqlConnect.ConnectDB();
                        try {
                            PreparedStatement ps = conn.prepareStatement(main);
                            ps.setString(1, jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                            ps.setString(2, jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                            ResultSet rs=ps.executeQuery();
                            rs.next();
                            purchasebillid=rs.getInt("PURCHASERETURNBILLID");
                            pp.pbid=purchasebillid;
                            pp.aname=rs.getString("AGENCY");
                            if(rs.getBoolean("CASH"))
                                pp.due=0;
                            else
                                pp.due=rs.getDouble("TOTALAMT");

                            Purchase_Page.jXDatePicker1.setDate(rs.getDate("IDATE"));
                            Purchase_Page.jTextField1.setText(rs.getString("AGENCY"));
                            Purchase_Page.jTextField2.setText(rs.getString("INVNO"));
                            if(rs.getBoolean("CASH"))
                                Purchase_Page.jComboBox1.setSelectedIndex(0);
                            else
                                Purchase_Page.jComboBox1.setSelectedIndex(1);
                            Purchase_Page.jTextField7.setText(String.valueOf(rs.getDouble("PURAMT")));
                            Purchase_Page.jTextField8.setText(String.valueOf(rs.getDouble("DISCOUNT")));
                            Purchase_Page.jTextField9.setText(String.valueOf(rs.getDouble("GST")/2));
                            Purchase_Page.jTextField10.setText(String.valueOf(rs.getDouble("GST")/2));
                            Purchase_Page.jTextField11.setText(String.valueOf(rs.getDouble("TOTALAMT")));

                            String sql2="SELECT * FROM PURCRETURNPROD WHERE PURCHASERETURNBILLID=" +purchasebillid +";";
                            Statement s=conn.createStatement();
                            ResultSet rs2=s.executeQuery(sql2);
                            while(rs2.next()){
                                String sql3="SELECT * FROM PRODUCT WHERE PID=" +rs2.getInt("pid") +";";
                                Statement s3=conn.createStatement();
                                ResultSet rs3=s3.executeQuery(sql3);
                                rs3.next();

                                String sql4="SELECT CODE,SGST,CGST,GST FROM HSN WHERE HSN='" +rs3.getString("hsn") +"';";
                                Statement s4=conn.createStatement();
                                ResultSet rs4=s4.executeQuery(sql4);
                                rs4.next();

                                DefaultTableModel model=(DefaultTableModel) Purchase_Page.jTable1.getModel();
                                model.addRow(new Object[]{rs3.getString("pname"), rs3.getInt("pkg"), rs4.getString("code"), rs2.getString("batchno"), rs2.getString("exp"), rs2.getInt("qty"), rs2.getInt("freeqty"), rs2.getDouble("mrp"), rs2.getDouble("trp"), rs2.getDouble("discount"), rs4.getDouble("sgst"), rs4.getDouble("cgst"), rs2.getDouble("trp") * rs2.getInt("qty")});
                            }
                        } 
                        catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }

                        pp.setEnabled(false);
                        List_Button l=new List_Button(pp);
                        List_Button.pbid=purchasebillid;
                        List_Button.flag=3;
                        l.setVisible(true);
                        Purchase_List.this.dispose(); 
                    }
                }        
                if( e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_List.this.dispose();
                }
            }
        });
    }

    private void findPurchaseBill(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BILL NO.","DATE","AGENCY","TYPE","AMOUNT"});
        String sql="SELECT * FROM PURCHASE WHERE INVNO LIKE '%" +jTextField1.getText() +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            Object row[]=new Object[5];
            while(rs.next()){
                row[0]=rs.getString("invno");
                row[1]=new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("idate"));
                row[2]=rs.getString("agency");
                if(rs.getBoolean("cash"))
                    row[3]="CASH";
                else
                    row[3]="CREDIT";
                row[4]=rs.getDouble("totalamt");
                model.addRow(row);
            }
            jTable1.setModel(model);
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(400);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void findPurchaseRetBill(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BILL NO.","DATE","AGENCY","TYPE","AMOUNT"});
        String sql="SELECT * FROM PURCHASERETURN WHERE INVNO LIKE '%" +jTextField1.getText() +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            Object row[]=new Object[5];
            while(rs.next()){
                row[0]=rs.getString("INVNO");
                row[1]=new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("IDATE"));
                row[2]=rs.getString("AGENCY");
                if(rs.getBoolean("CASH"))
                    row[3]="CASH";
                else
                    row[3]="CREDIT";
                row[4]=rs.getDouble("TOTALAMT");
                model.addRow(row);
            }
            jTable1.setModel(model);
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(400);
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
        jTable1 = new javax.swing.JTable(){
            public boolean getScrollableTracksViewportHeight() {
                if(getParent() instanceof JViewport)
                return(((JViewport)getParent()).getHeight() > getPreferredSize().height);
                return super.getScrollableTracksViewportHeight();
            }
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(getRowCount() == 0) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
                    g2d.drawString("NO BILLS TO DISPLAY.",10,20);
                }
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BILL NO.", "DATE", "AGENCY", "TYPE", "AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jLabel2.setText("PURCHASE BILL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Purchase_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase_List(flag).setVisible(true);
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
