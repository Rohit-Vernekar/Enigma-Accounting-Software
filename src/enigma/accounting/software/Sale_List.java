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
public class Sale_List extends javax.swing.JFrame {

    String billno;
    public static int flag;
    /**
     * Creates new form Sale_List
     * @param f
     */
    public Sale_List(int f) {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        flag=f;
        if(flag==1)
            jLabel2.setText("SALE RETURN BILL (CREDIT NOTE)");
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
        if(flag==0)
            findSaleBill();
        if(flag==1)
            findSaleRetBill();
        
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
                    Sale_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(flag==0)
                    findSaleBill();
                if(flag==1)
                    findSaleRetBill();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    if(flag==0)
                        findSaleBill();
                    if(flag==1)
                        findSaleRetBill();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            if(flag==0)
                                findSaleBill();
                            if(flag==1)
                                findSaleRetBill();
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
                        Sale_Page sp=new Sale_Page(1);                    
                        sp.setVisible(true);
                        sp.setEnabled(false);
                        List_Button sl=new List_Button(sp);
                        sl.setVisible(true);
                        billno=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
                        List_Button.billno=billno;
                        List_Button.flag=0;
                        String sql="SELECT * FROM SALE WHERE BILLNO='" +billno +"';";
                        String sql2="SELECT * FROM SALEPROD WHERE BILLNO='" +billno +"';";
                        String sql3="SELECT * FROM PRODUCT WHERE PID=?;";
                        try{
                            Connection conn=MySqlConnect.ConnectDB();
                            Connection conn2=MySqlConnect.ConnectDB();
                            Connection conn3=MySqlConnect.ConnectDB();
                            Statement s=conn.createStatement();
                            Statement s2=conn.createStatement();
                            PreparedStatement ps=conn2.prepareStatement(sql3);
                            ResultSet rs=s.executeQuery(sql);
                            ResultSet rs2=s2.executeQuery(sql2);
                            rs.next();
                            Sale_Page.jTextField18.setText(billno);
                            Sale_Page.jXDatePicker1.setDate(rs.getDate("bdate"));
                            Sale_Page.jTextField1.setText(rs.getString("pname"));
                            Sale_Page.jTextField2.setText(rs.getString("address"));
                            Sale_Page.jTextField3.setText(rs.getString("dname"));
                            Sale_Page.jTextField4.setText(rs.getString("regno"));
                            Sale_Page.jTextField13.setText(String.valueOf(rs.getDouble("amount")));
                            Sale_Page.jTextField14.setText(String.valueOf(rs.getDouble("discount")));
                            Sale_Page.jTextField15.setText(String.valueOf(rs.getDouble("gst")/2));
                            Sale_Page.jTextField16.setText(String.valueOf(rs.getDouble("gst")/2));
                            Sale_Page.jTextField17.setText(String.valueOf(rs.getDouble("total")));

                            while(rs2.next()){
                                DefaultTableModel model=(DefaultTableModel) Sale_Page.jTable1.getModel();
                                ps.setInt(1,rs2.getInt("pid"));
                                ResultSet rs3=ps.executeQuery();
                                rs3.next();
                                String sql4="SELECT * FROM HSN WHERE HSN='" +rs3.getString("hsn") +"';";
                                Statement s4=conn3.createStatement();
                                ResultSet rs4=s4.executeQuery(sql4);
                                rs4.next();

                                double mrp = rs2.getDouble("mrp");
                                double qtys = mrp * rs2.getInt("qtystp");
                                double qtyp = mrp * rs2.getInt("qtypcs")/rs3.getInt("pkg");
                                double amt = qtys+qtyp;
                                model.addRow(new Object[]{rs3.getString("pname"), rs3.getInt("pkg"), rs2.getString("batchno"), rs2.getString("exp"), rs2.getInt("qtystp"), rs2.getInt("qtypcs"), rs2.getDouble("mrp"), rs2.getDouble("discount"), rs4.getDouble("sgst"), rs4.getDouble("cgst"),amt});
                            }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                        Sale_List.this.dispose(); 
                    }
                    if(flag==1){
                        Sale_Page sp=new Sale_Page(3);                    
                        sp.setVisible(true);
                        sp.setEnabled(false);
                        List_Button sl=new List_Button(sp);
                        List_Button.flag=2;
                        sl.setVisible(true);
                        billno=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
                        List_Button.billno=billno;
                        String sql="SELECT * FROM SALERETURN WHERE BILLNO='" +billno +"';";
                        String sql2="SELECT * FROM SALERETURNPROD WHERE BILLNO='" +billno +"';";
                        String sql3="SELECT * FROM PRODUCT WHERE PID=?;";
                        try{
                            Connection conn=MySqlConnect.ConnectDB();
                            Connection conn2=MySqlConnect.ConnectDB();
                            Connection conn3=MySqlConnect.ConnectDB();
                            Statement s=conn.createStatement();
                            Statement s2=conn.createStatement();
                            PreparedStatement ps=conn2.prepareStatement(sql3);
                            ResultSet rs=s.executeQuery(sql);
                            ResultSet rs2=s2.executeQuery(sql2);
                            rs.next();
                            Sale_Page.jTextField18.setText(billno);
                            Sale_Page.jXDatePicker1.setDate(rs.getDate("bdate"));
                            Sale_Page.jTextField1.setText(rs.getString("pname"));
                            Sale_Page.jTextField2.setText(rs.getString("address"));
                            Sale_Page.jTextField3.setText(rs.getString("dname"));
                            Sale_Page.jTextField4.setText(rs.getString("regno"));
                            Sale_Page.jTextField13.setText(String.valueOf(rs.getDouble("amount")));
                            Sale_Page.jTextField14.setText(String.valueOf(rs.getDouble("discount")));
                            Sale_Page.jTextField15.setText(String.valueOf(rs.getDouble("gst")/2));
                            Sale_Page.jTextField16.setText(String.valueOf(rs.getDouble("gst")/2));
                            Sale_Page.jTextField17.setText(String.valueOf(rs.getDouble("total")));

                            while(rs2.next()){
                                DefaultTableModel model=(DefaultTableModel) Sale_Page.jTable1.getModel();
                                ps.setInt(1,rs2.getInt("pid"));
                                ResultSet rs3=ps.executeQuery();
                                rs3.next();
                                String sql4="SELECT * FROM HSN WHERE HSN='" +rs3.getString("hsn") +"';";
                                Statement s4=conn3.createStatement();
                                ResultSet rs4=s4.executeQuery(sql4);
                                rs4.next();

                                double mrp = rs2.getDouble("mrp");
                                double qtys = mrp * rs2.getInt("qtystp");
                                double qtyp = mrp * rs2.getInt("qtypcs")/rs3.getInt("pkg");
                                double amt = qtys+qtyp;
                                model.addRow(new Object[]{rs3.getString("pname"), rs3.getInt("pkg"), rs2.getString("batchno"), rs2.getString("exp"), rs2.getInt("qtystp"), rs2.getInt("qtypcs"), rs2.getDouble("mrp"), rs2.getDouble("discount"), rs4.getDouble("sgst"), rs4.getDouble("cgst"),amt});
                            }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                        Sale_List.this.dispose(); 
                    }
                     
                    if( e.getKeyCode() == e.VK_ESCAPE){
                        Sale_List.this.dispose();
                    }
                }
            }
        });
    }

    private void findSaleBill(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BILL NO.","DATE","NAME","AMOUNT"});
        String sql="SELECT * FROM SALE WHERE BILLNO LIKE '%" +jTextField1.getText() +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            Object row[]=new Object[4];
            while(rs.next()){
                row[0]=rs.getString("billno");
                row[1]=new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("bdate"));
                row[2]=rs.getString("pname");
                row[3]=rs.getString("total");
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
    private void findSaleRetBill(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BILL NO.","DATE","NAME","AMOUNT"});
        String sql="SELECT * FROM SALERETURN WHERE BILLNO LIKE '%" +jTextField1.getText() +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        try{
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(sql);
            Object row[]=new Object[4];
            while(rs.next()){
                row[0]=rs.getString("BILLNO");
                row[1]=new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("BDATE"));
                row[2]=rs.getString("PNAME");
                row[3]=rs.getString("TOTAL");
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
        }
        ;
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BILL NO.", "DATE", "NAME", "AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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
        jLabel2.setText("SALE BILL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sale_List(flag).setVisible(true);
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
