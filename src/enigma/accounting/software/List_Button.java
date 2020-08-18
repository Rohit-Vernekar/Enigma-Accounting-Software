/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell1
 */
public class List_Button extends javax.swing.JFrame {
    public static int flag,pbid;
    public static Sale_Page spage;
    public static Purchase_Page ppage;
    public static String billno;
    /**
     * Creates new form Sale_List_Button
     * @param s
     */
    public List_Button(Sale_Page s) {
        initComponents();
        spage=s;
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int)rect.getCenterX()-this.getWidth()/2;
        int y = (int)rect.getMaxY()-this.getHeight();
        this.setLocation(x,y);
        
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jButton1.requestFocus();
            }
        }); 
    }
    public List_Button(Purchase_Page p) {
        initComponents();
        ppage=p;
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int)rect.getCenterX()-this.getWidth()/2;
        int y = (int)rect.getMaxY()-this.getHeight();
        this.setLocation(x,y);
        
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jButton1.requestFocus();
            }
        }); 
    }

    private void deleteSaleBill(){
        String sql="DELETE FROM SALE WHERE BILLNO='" +billno +"';";
        String sql2="DELETE FROM SALEPROD WHERE BILLNO='" +billno +"';";
        String sql3="SELECT BILLNO FROM DETAILS WHERE ID=1;";
        try{
            Connection conn=MySqlConnect.ConnectDB();
            Statement s=conn.createStatement();
            Statement s2=conn.createStatement();
            Statement s3=conn.createStatement();
            
            ResultSet rs=s3.executeQuery(sql3);
            rs.next();
            if(Integer.parseInt(billno)+1 == Integer.parseInt(rs.getString("billno"))){
                String sql4="UPDATE DETAILS SET BILLNO=? WHERE ID=1;";
                PreparedStatement ps=conn.prepareCall(sql4);
                ps.setString(1, billno);
                ps.executeUpdate();
            }
            
            s.executeUpdate(sql);
            s.executeUpdate(sql2);
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deleteSaleRetBill(){
        String sql="DELETE FROM SALERETURN WHERE BILLNO='" +billno +"';";
        String sql2="DELETE FROM SALERETURNPROD WHERE BILLNO='" +billno +"';";
        String sql3="SELECT SALERETURN FROM DETAILS WHERE ID=1;";
        try{
            Connection conn=MySqlConnect.ConnectDB();
            Statement s=conn.createStatement();
            Statement s2=conn.createStatement();
            Statement s3=conn.createStatement();
            
            ResultSet rs=s3.executeQuery(sql3);
            rs.next();
            if(billno.equalsIgnoreCase("CN"+String.format("%05d",Integer.parseInt(rs.getString("SALERETURN"))-1))){
                String sql4="UPDATE DETAILS SET SALERETURN=? WHERE ID=1;";
                PreparedStatement ps=conn.prepareCall(sql4);
                String t="";
                for(int i=2;i<billno.length();i++)
                    t+=billno.charAt(i);
                ps.setString(1, t);
                ps.executeUpdate();
            }
            
            s.executeUpdate(sql);
            s.executeUpdate(sql2);
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletePurchaseBill(){
        String sql="DELETE FROM PURCHASE WHERE PUCHASEBILLID=" +pbid +";";
        String sql2="DELETE FROM PURCPROD WHERE PURCHASEBILLID=" +pbid +";";
        String sql3="SELECT PURCHASEBILLID FROM DETAILS WHERE ID=1;";
        String sql4="SELECT * FROM PURCHASE WHERE PUCHASEBILLID=" +pbid +";";
        try{
            Connection conn=MySqlConnect.ConnectDB();
            Statement s=conn.createStatement();
            Statement s2=conn.createStatement();
            Statement s3=conn.createStatement();
            Statement s4=conn.createStatement();
            
            ResultSet rs4=s4.executeQuery(sql4);
            rs4.next();
            if(!rs4.getBoolean("CASH")){
                String sql6="UPDATE AGENCY SET DUE=DUE-? WHERE ANAME=?;";
                PreparedStatement ps6=conn.prepareStatement(sql6);
                ps6.setDouble(1, rs4.getDouble("TOTALAMT"));
                ps6.setString(2, rs4.getString("AGENCY"));
                ps6.executeUpdate();
            }
            
            ResultSet rs=s3.executeQuery(sql3);
            rs.next();
            if(pbid+1 == rs.getInt("purchasebillid")){
                String sql5="UPDATE DETAILS SET PURCHASEBILLID=? WHERE ID=1;";
                PreparedStatement ps=conn.prepareCall(sql5);
                ps.setInt(1, pbid);
                ps.executeUpdate();
            }
            
            s.executeUpdate(sql);
            s.executeUpdate(sql2);
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void deletePurchaseRetBill(){
        String sql="DELETE FROM PURCHASERETURN WHERE PURCHASERETURNBILLID='" +pbid +"';";
        String sql2="DELETE FROM PURCRETURNPROD WHERE PURCHASERETURNBILLID='" +pbid +"';";
        String sql3="SELECT PURCHASERETURN FROM DETAILS WHERE ID=1;";
        String sql4="SELECT * FROM PURCHASERETURN WHERE PURCHASERETURNBILLID=" +pbid +";";
        try{
            Connection conn=MySqlConnect.ConnectDB();
            Statement s=conn.createStatement();
            Statement s2=conn.createStatement();
            Statement s3=conn.createStatement();
            Statement s4=conn.createStatement();
            
            ResultSet rs4=s4.executeQuery(sql4);
            rs4.next();
            if(!rs4.getBoolean("CASH")){
                String sql6="UPDATE AGENCY SET DUE=DUE+? WHERE ANAME=?;";
                PreparedStatement ps6=conn.prepareStatement(sql6);
                ps6.setDouble(1, rs4.getDouble("TOTALAMT"));
                ps6.setString(2, rs4.getString("AGENCY"));
                ps6.executeUpdate();
            }
            
            ResultSet rs=s3.executeQuery(sql3);
            rs.next();
            if(pbid+1 == rs.getInt("PURCHASERETURN")){
                String sql5="UPDATE DETAILS SET PURCHASERETURN=? WHERE ID=1;";
                PreparedStatement ps=conn.prepareCall(sql5);
                ps.setInt(1, pbid);
                ps.executeUpdate();
            }
            
            s.executeUpdate(sql);
            s.executeUpdate(sql2);
        }
        catch(SQLException | NumberFormatException e){
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("MODIFY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 0));
        jButton3.setText("EXIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_LEFT || evt.getKeyCode()==KeyEvent.VK_UP)
            jButton3.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jButton2.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(flag==0){
                spage.setVisible(true);
                spage.setEnabled(true);
                spage.deleteData();
                this.dispose();
            }
            if(flag==1){
                ppage.setVisible(true);
                ppage.setEnabled(true);
                this.dispose();
            }
            if(flag==2){
                spage.setVisible(true);
                spage.setEnabled(true);
                spage.deleteData();
                this.dispose();
            }
            if(flag==3){
                ppage.setVisible(true);
                ppage.setEnabled(true);
                ppage.deleteData();
                this.dispose();
            }
            
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_LEFT || evt.getKeyCode()==KeyEvent.VK_UP)
            jButton1.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jButton3.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to Delete?","DELETE?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
            if(PromptResult==JOptionPane.YES_OPTION)
            {
                if(flag==0){
                    deleteSaleBill();
                    new Sale_List(0).setVisible(true);
                    spage.dispose();
                    this.dispose();
                }
                if(flag==1){
                    deletePurchaseBill();
                    new Purchase_List(0).setVisible(true);
                    ppage.dispose();
                    this.dispose();
                }
                if(flag==2){
                    deleteSaleRetBill();
                    new Sale_List(1).setVisible(true);
                    spage.dispose();
                    this.dispose();
                }
                if(flag==3){
                    deletePurchaseRetBill();
                    new Purchase_List(1).setVisible(true);
                    ppage.dispose();
                    this.dispose();
                }
            }
            else{
                jButton2.requestFocus();
            }
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_LEFT || evt.getKeyCode()==KeyEvent.VK_UP)
            jButton2.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jButton1.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(flag==0){
                new Sale_List(0).setVisible(true);
                spage.dispose();
                this.dispose();
            }
            if(flag==1){
                new Purchase_List(0).setVisible(true);
                ppage.dispose();
                this.dispose();
            }
            if(flag==2){
                new Sale_List(1).setVisible(true);
                spage.dispose();
                this.dispose();
            }
            if(flag==3){
                new Purchase_List(1).setVisible(true);
                ppage.dispose();
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(flag==0){
            spage.setVisible(true);
            spage.setEnabled(true);
            spage.deleteData();
            this.dispose();
        }
        if(flag==1){
            ppage.setVisible(true);
            ppage.setEnabled(true);
            this.dispose();
        }
        if(flag==2){
            spage.setVisible(true);
            spage.setEnabled(true);
            spage.deleteData();
            this.dispose();
        }
        if(flag==3){
            ppage.setVisible(true);
            ppage.setEnabled(true);
            ppage.deleteData();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String ObjButtons[] = {"Yes","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to Delete?","DELETE?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION)
        {
            if(flag==0){
                deleteSaleBill();
                new Sale_List(0).setVisible(true);
                spage.dispose();
                this.dispose();
            }
            if(flag==1){
                deletePurchaseBill();
                new Purchase_List(0).setVisible(true);
                ppage.dispose();
                this.dispose();
            }
            if(flag==2){
                deleteSaleRetBill();
                new Sale_List(1).setVisible(true);
                spage.dispose();
                this.dispose();
            }
            if(flag==3){
                deletePurchaseRetBill();
                new Purchase_List(1).setVisible(true);
                ppage.dispose();
                this.dispose();
            }
        }
        else{
            jButton2.requestFocus();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(flag==0){
            new Sale_List(0).setVisible(true);
            spage.dispose();
            this.dispose();
        }
        if(flag==1){
            new Purchase_List(0).setVisible(true);
            ppage.dispose();
            this.dispose();
        }
        if(flag==2){
            new Sale_List(1).setVisible(true);
            spage.dispose();
            this.dispose();
        }
        if(flag==3){
            new Purchase_List(1).setVisible(true);
            ppage.dispose();
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(List_Button.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List_Button.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List_Button.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List_Button.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(flag==0 || flag==2)
                    new List_Button(spage).setVisible(true);
                if(flag==1 || flag==4)
                    new List_Button(ppage).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
