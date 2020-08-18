/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;

import java.awt.Color;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
class saleprod{
    String billno,batchno,exp;
    int pid,qts,qtp;
    double mrp,disc;
}
public class Sale_Page extends javax.swing.JFrame {
    public ArrayList<saleprod> spdarray;
    public static String hsn,exp;
    public static double cgst,sgst;
    public static int qs,qp,pid,billno,flag;

    public Sale_Page(int f) {
        initComponents();
        flag=f;
        Image img = new ImageIcon(getClass().getResource("/Enigma_Logo_Rectangle.png")).getImage();
        Image newimg = img.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH);
        jLabel5.setIcon(new ImageIcon(newimg));
        
        if(flag==2 || flag==3)
            jLabel4.setText("SALE RETURN");
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","EXIT?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {   
                    if(flag==1){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALEPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2,spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALERETURNPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2,spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                    System.exit(0);
                }
            }
        });
        
        jTextField1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField3.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField4.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField6.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField7.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField8.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField9.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField10.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField11.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField12.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField13.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField14.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField15.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField16.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField17.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jTextField18.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jXDatePicker1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Enigma Accounting Software");
        getContentPane().setBackground(Color.WHITE);
        
        jXDatePicker1.setDate(new Date());
        jXDatePicker1.requestFocus();
        
        jXDatePicker1.getEditor().addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    jTextField6.requestFocus();
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    if(flag==0 || flag==2){
                        new Start_Page().setVisible(true);
                        Sale_Page.this.dispose();
                    }
                    if(flag==1){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALEPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2, spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                            new Sale_List(0).setVisible(true);
                            Sale_Page.this.dispose();
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                    if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALERETURNPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2, spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                            new Sale_List(1).setVisible(true);
                            Sale_Page.this.dispose();
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jTextField13.setText("0.00");
        jTextField14.setText("0.00");
        jTextField15.setText("0.00");
        jTextField16.setText("0.00");
        jTextField17.setText("0.00");
        if(flag==0){
            try{
                Connection conn=MySqlConnect.ConnectDB();
                Statement s=conn.createStatement();
                ResultSet rs;
                String sql="SELECT BILLNO FROM DETAILS WHERE ID=1;";
                rs=s.executeQuery(sql);
                rs.next();
                billno=(int) Double.parseDouble(rs.getString("BILLNO"));
                jTextField18.setText(rs.getString("BILLNO"));
            }
            catch(SQLException | NumberFormatException e){
                JOptionPane.showMessageDialog(null,e );
            }
        }
        if(flag==2){
            try{
                Connection conn=MySqlConnect.ConnectDB();
                Statement s=conn.createStatement();
                ResultSet rs;
                String sql="SELECT SALERETURN FROM DETAILS WHERE ID=1;";
                rs=s.executeQuery(sql);
                rs.next();
                billno=(int) Double.parseDouble(rs.getString("SALERETURN"));
                jTextField18.setText("CN" +rs.getString("SALERETURN"));
            }
            catch(SQLException | NumberFormatException e){
                JOptionPane.showMessageDialog(null,e );
            }
        }
    }
    public void deleteData(){
        if(flag==1){   
            spdarray = new ArrayList<saleprod>();
            String bill=jTextField18.getText();
            String sql="SELECT * FROM SALEPROD WHERE BILLNO=?;";
            String sql2="DELETE FROM SALEPROD WHERE BILLNO=?;";
            try {
                Connection conn=MySqlConnect.ConnectDB();                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1, bill);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    saleprod spd=new saleprod();
                    spd.billno=rs.getString("billno");
                    spd.pid=rs.getInt("pid");
                    spd.batchno=rs.getString("batchno");
                    spd.exp=rs.getString("exp");
                    spd.qts=rs.getInt("qtystp");
                    spd.qtp=rs.getInt("qtypcs");
                    spd.mrp=rs.getDouble("mrp");
                    spd.disc=rs.getDouble("discount");
                    spdarray.add(spd);
                }
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1, bill);
                ps2.executeUpdate();
                
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if(flag==3){
            spdarray = new ArrayList<saleprod>();
            String bill=jTextField18.getText();
            String sql="SELECT * FROM SALERETURNPROD WHERE BILLNO=?;";
            String sql2="DELETE FROM SALERETURNPROD WHERE BILLNO=?;";
            try {
                Connection conn=MySqlConnect.ConnectDB();                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1, bill);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    saleprod spd=new saleprod();
                    spd.billno=rs.getString("billno");
                    spd.pid=rs.getInt("pid");
                    spd.batchno=rs.getString("batchno");
                    spd.exp=rs.getString("exp");
                    spd.qts=rs.getInt("qtystp");
                    spd.qtp=rs.getInt("qtypcs");
                    spd.mrp=rs.getDouble("mrp");
                    spd.disc=rs.getDouble("discount");
                    spdarray.add(spd);
                }
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1, bill);
                ps2.executeUpdate();
                
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
    private void getamt(){
        double g=2*Double.parseDouble(jTextField15.getText());
        double d=Double.parseDouble(jTextField14.getText());
        double t=Double.parseDouble(jTextField17.getText());
        double amt=t-g+d;
        amt = Math.round(amt * 100D) / 100D;
        jTextField13.setText(String.valueOf(amt));
    }
    private void getdisc(){
        double disc=0;
        for(int i=0;i<jTable1.getRowCount();i++)
            disc+=Double.parseDouble(jTable1.getValueAt(i, 10).toString())*Double.parseDouble(jTable1.getValueAt(i, 7).toString())/100;
        disc = Math.round(disc * 100D) / 100D;
        jTextField14.setText(String.valueOf(disc));
    }
    private void getgst(){
        double gst=0;
        for(int i=0;i<jTable1.getRowCount();i++){
            double amt=Double.parseDouble(jTable1.getValueAt(i, 10).toString());
            double g=Double.parseDouble(jTable1.getValueAt(i, 9).toString());
            double d=Double.parseDouble(jTable1.getValueAt(i, 7).toString());
            double damt=amt-(amt*d/100);
            double taxamt = (damt * g) / (100 + g);
            gst+=taxamt;
        }
        gst = Math.round(gst * 100D) / 100D;
        jTextField15.setText(String.valueOf(gst));
        jTextField16.setText(String.valueOf(gst));
    }
    private void gettamt(){
        double tamt = 0;
        for(int i=0;i<jTable1.getRowCount();i++){
            double amt= Double.parseDouble(jTable1.getValueAt(i, 10).toString());
            double d=Double.parseDouble(jTable1.getValueAt(i, 7).toString());
            double damt=amt-(amt*d/100);
            tamt+=damt;
        }
        tamt = Math.round(tamt * 100D) / 100D;
        jTextField17.setText(String.valueOf(tamt));
    }
    private void savecrbill(){
        Connection conn=MySqlConnect.ConnectDB();
        String sql="INSERT INTO SALERETURN VALUES(?,?,?,?,?,?,?,?,?,?);";
        try{
            if(flag==3){
                String sql2="DELETE FROM SALERETURN WHERE BILLNO='" +jTextField18.getText() +"';";
                Statement s=conn.createStatement();
                s.executeUpdate(sql2);
            }
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, jTextField18.getText());
            ps.setDate(2,new java.sql.Date(jXDatePicker1.getDate().getTime()));
            ps.setString(3,jTextField1.getText());
            ps.setString(4,jTextField2.getText());
            ps.setString(5,jTextField3.getText());
            ps.setString(6,jTextField4.getText());
            ps.setDouble(7,Double.parseDouble(jTextField13.getText()));
            double gst=2*Double.parseDouble(jTextField15.getText());
            ps.setDouble(8,gst);
            ps.setDouble(9,Double.parseDouble(jTextField14.getText()));
            ps.setDouble(10,Double.parseDouble(jTextField17.getText()));
            ps.executeUpdate();
            if(flag==2){
                String sql2="UPDATE DETAILS SET SALERETURN=? WHERE ID=1;";
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1,String.format("%05d", billno+1));
                ps2.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
        }
    }
    private void savecrprodbill(){
        try{
            Connection conn=MySqlConnect.ConnectDB();
            String sql="INSERT INTO SALERETURNPROD VALUES(?,?,?,?,?,?,?,?);";
            for(int i=0;i<jTable1.getRowCount();i++){
                String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1,jTable1.getValueAt(i,0).toString());
                ps2.setInt(2,Integer.parseInt(jTable1.getValueAt(i,1).toString()));
                ResultSet rs=ps2.executeQuery();
                rs.next();
                int p=rs.getInt("pid");
                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,jTextField18.getText());
                ps.setInt(2, p);
                ps.setString(3, jTable1.getValueAt(i,2).toString());
                ps.setString(4, jTable1.getValueAt(i,3).toString());
                ps.setInt(5, Integer.parseInt(jTable1.getValueAt(i,4).toString()));
                ps.setInt(6, Integer.parseInt(jTable1.getValueAt(i,5).toString()));
                ps.setDouble(7, Double.parseDouble(jTable1.getValueAt(i,6).toString()));
                ps.setDouble(8, Double.parseDouble(jTable1.getValueAt(i,7).toString()));
                ps.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
        }
    }
    private void savebill(){
        try{
            Connection conn=MySqlConnect.ConnectDB();
            if(flag==1){
                String sql2="DELETE FROM SALE WHERE BILLNO='" +jTextField18.getText() +"';";
                Statement s=conn.createStatement();
                s.executeUpdate(sql2);
            }
            String sql="INSERT INTO SALE VALUES(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, jTextField18.getText());
            ps.setDate(2,new java.sql.Date(jXDatePicker1.getDate().getTime()));
            ps.setString(3,jTextField1.getText());
            ps.setString(4,jTextField2.getText());
            ps.setString(5,jTextField3.getText());
            ps.setString(6,jTextField4.getText());
            ps.setDouble(7,Double.parseDouble(jTextField13.getText()));
            double gst=2*Double.parseDouble(jTextField15.getText());
            ps.setDouble(8,gst);
            ps.setDouble(9,Double.parseDouble(jTextField14.getText()));
            ps.setDouble(10,Double.parseDouble(jTextField17.getText()));
            ps.executeUpdate();
            if(flag==0){
                String sql2="UPDATE DETAILS SET BILLNO=? WHERE ID=1;";
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1,String.format("%06d", billno+1));
                ps2.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
        }
    }
    private void savebillprod(){
        try{
            Connection conn=MySqlConnect.ConnectDB();
            String sql="INSERT INTO SALEPROD VALUES(?,?,?,?,?,?,?,?);";
            for(int i=0;i<jTable1.getRowCount();i++){
                String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                PreparedStatement ps2=conn.prepareStatement(sql2);
                ps2.setString(1,jTable1.getValueAt(i,0).toString());
                ps2.setInt(2,Integer.parseInt(jTable1.getValueAt(i,1).toString()));
                ResultSet rs=ps2.executeQuery();
                rs.next();
                int p=rs.getInt("pid");
                
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,jTextField18.getText());
                ps.setInt(2, p);
                ps.setString(3, jTable1.getValueAt(i,2).toString());
                ps.setString(4, jTable1.getValueAt(i,3).toString());
                ps.setInt(5, Integer.parseInt(jTable1.getValueAt(i,4).toString()));
                ps.setInt(6, Integer.parseInt(jTable1.getValueAt(i,5).toString()));
                ps.setDouble(7, Double.parseDouble(jTable1.getValueAt(i,6).toString()));
                ps.setDouble(8, Double.parseDouble(jTable1.getValueAt(i,7).toString()));
                ps.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
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

        jPanel1 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jXDatePicker1.setFormats(new String[]{"dd-MM-yyyy"});
        jXDatePicker1.setBackground(new java.awt.Color(255, 255, 255));
        jXDatePicker1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("DATE : ");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("BILL NO. : ");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("SALE BILL");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel6.setText("NAME : ");

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel7.setText("ADDRESS : ");

        jTextField2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel8.setText("DOCTOR : ");

        jTextField3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel9.setText("REG. NO. : ");

        jTextField4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(77, 77, 77)
                                    .addComponent(jLabel7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("AMOUNT : ");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("DISCOUNT : ");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("SGST : ");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("CGST : ");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("TOTAL : ");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("PRODUCT");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("PKG");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("BATCH NO.");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("MRP");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        jTextField10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        jTextField11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("QTY (STP)");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("QTY (PCS)");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("DISCOUNT %");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT", "PKG", "BATCH NO.", "EXP", "QTY (STP)", "QTY (PCS)", "MRP", "DISCOUNT(%)", "SGST", "CGST", "AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setIntercellSpacing(new java.awt.Dimension(2, 2));
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 0));
        jButton3.setText("MODIFY");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 0, 0));
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel12)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField13)
                    .addComponent(jTextField17))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton1)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.requestFocus();
        jTextField3.selectAll();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        if(flag==0 || flag==1){
            Sale_Product_List s=new Sale_Product_List();
            s.setVisible(true);
        }
        else{
            new Sale_Product_List(1).setVisible(true);
        }
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        int pkg=Integer.parseInt(jTextField7.getText());
        int qtys=Integer.parseInt(jTextField9.getText());
        int qtyp=Integer.parseInt(jTextField10.getText());
        if(qtyp>=pkg){
            int r=qtyp/pkg;
            qtyp=qtyp%pkg;
            qtys+=r;
        }
        if(qtys>qs || (qtys==qs && qtyp>=qp)){
            jTextField9.setText(String.valueOf(qs));
            jTextField10.setText(String.valueOf(qp));
            jTextField11.requestFocus();
            jTextField11.selectAll();
        }
        else{
            jTextField9.setText(String.valueOf(qtys));
            jTextField10.setText(String.valueOf(qtyp));
            jTextField11.requestFocus();
            jTextField11.selectAll();
        }
        
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        try{
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            double mrp=Double.parseDouble(jTextField12.getText());
            double qts=Double.parseDouble(jTextField9.getText())*mrp;
            double qtp=(Double.parseDouble(jTextField10.getText())/Double.parseDouble(jTextField7.getText()))*mrp;
            double amount=qts+qtp;
            amount = Math.round(amount * 100D) / 100D;
            String amt=String.valueOf(amount);
            model.addRow(new Object[]{jTextField6.getText(),jTextField7.getText(),jTextField8.getText(),exp,jTextField9.getText(),jTextField10.getText(),jTextField12.getText(),jTextField11.getText(),cgst,sgst,amt});
            
            getdisc();
            getgst();
            gettamt();
            getamt();
        
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField12.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField6.requestFocus();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please Check the Input Data","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField10.requestFocus();
            jTextField10.selectAll();
        }
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            double mrp=Double.parseDouble(jTextField12.getText());
            double qts=Double.parseDouble(jTextField9.getText())*mrp;
            double qtp=(Double.parseDouble(jTextField10.getText())/Double.parseDouble(jTextField7.getText()))*mrp;
            double amount=qts+qtp;
            String amt=String.valueOf(amount);
            model.addRow(new Object[]{jTextField6.getText(),jTextField7.getText(),jTextField8.getText(),exp,jTextField9.getText(),jTextField10.getText(),jTextField12.getText(),jTextField11.getText(),cgst,sgst,amt});
            
            
            getdisc();
            getgst();
            gettamt();
            getamt();
        
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField12.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField6.requestFocus();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please Check the Input Data","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField10.requestFocus();
            jTextField10.selectAll();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
        
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();       
            getdisc();
            getgst();
            gettamt();
            getamt();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        int row =jTable1.getSelectedRow();
        
        jTextField6.setText(jTable1.getValueAt(row,0).toString());
        jTextField7.setText(jTable1.getValueAt(row,1).toString());
        jTextField8.setText(jTable1.getValueAt(row,2).toString());
        jTextField12.setText(jTable1.getValueAt(row,6).toString());
        jTextField9.setText(jTable1.getValueAt(row,4).toString());
        jTextField10.setText(jTable1.getValueAt(row,5).toString());
        jTextField11.setText(jTable1.getValueAt(row,7).toString());
        
        
        jTextField9.requestFocus();
        jTextField9.selectAll();
        
        model.removeRow(row);
        getdisc();
        getgst();
        gettamt();
        getamt();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_LEFT || evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField9.requestFocus();
            jTextField9.selectAll();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_LEFT || evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField10.requestFocus();
            jTextField10.selectAll();
        }
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(flag==0 || flag==1){
                Sale_Batch_List s=new Sale_Batch_List(pid,0);
                s.setVisible(true);
            }
            else{
                Sale_Batch_List s=new Sale_Batch_List(pid,1);
                s.setVisible(true);
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jTextField10.requestFocus();
            jTextField10.selectAll();
        }
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(jTable1.getRowCount()!=0){
                jTable1.requestFocus();
                try{
                    Robot r=new Robot();
                    r.keyPress(KeyEvent.VK_DOWN);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(jTable1.getRowCount()==0){
                jXDatePicker1.requestFocus();
                jXDatePicker1.getEditor().selectAll();
            }
            else{
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Exit Without Saving?","EXIT?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION){
                    if(flag==1){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALEPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2, spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        new Sale_List(0).setVisible(true);
                        this.dispose();
                    }
                    else if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO SALERETURNPROD VALUES(?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1, spdarray.get(i).billno);
                                ps.setInt(2, spdarray.get(i).pid);
                                ps.setString(3, spdarray.get(i).batchno);
                                ps.setString(4, spdarray.get(i).exp);
                                ps.setInt(5, spdarray.get(i).qts);
                                ps.setInt(6, spdarray.get(i).qtp);
                                ps.setDouble(7, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        new Sale_List(1).setVisible(true);
                        this.dispose();
                    }
                    else{
                        Start_Page s =new Start_Page();
                        s.setVisible(true);
                        this.dispose();
                    }
                }
                else
                    jTextField12.requestFocus();
            }
        }   
        if(evt.getKeyCode()==KeyEvent.VK_END){
            jTextField1.requestFocus();
            jTextField1.selectAll();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.requestFocus();
        jTextField2.selectAll();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField4.requestFocus();
        jTextField4.selectAll();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField3.requestFocus();
            jTextField3.selectAll();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField2.requestFocus();
            jTextField2.selectAll();
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField1.requestFocus();
            jTextField1.selectAll();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField6.requestFocus();
            jTextField6.selectAll();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField6.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        int row =jTable1.getSelectedRow();
        
        jTextField6.setText(jTable1.getValueAt(row,0).toString());
        jTextField7.setText(jTable1.getValueAt(row,1).toString());
        jTextField8.setText(jTable1.getValueAt(row,2).toString());
        jTextField12.setText(jTable1.getValueAt(row,6).toString());
        jTextField9.setText(jTable1.getValueAt(row,4).toString());
        jTextField10.setText(jTable1.getValueAt(row,5).toString());
        jTextField11.setText(jTable1.getValueAt(row,7).toString());
        
        
        jTextField9.requestFocus();
        jTextField9.selectAll();
        
        model.removeRow(row);
        getdisc();
        getgst();
        gettamt();
        getamt();
        }
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){        
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();       
            model.removeRow(jTable1.getSelectedRow());
            getdisc();
            getgst();
            gettamt();
            getamt();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        String ObjButtons[] = {"Yes","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure?","SAVE?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION)
        {
            double a=Double.parseDouble(jTextField17.getText()) + 0.50;
            int aa=(int)a;
            jTextField17.setText(String.valueOf(aa));
            if(flag==0){ 
                savebillprod();
                savebill();
                Sale_Page s=new Sale_Page(0);
                s.setVisible(true);
                this.dispose();
            }
            if(flag==1){
                savebillprod();
                savebill();
                new Sale_List(0).setVisible(true);
                this.dispose();
            }
            if(flag==2){
                savecrprodbill();
                savecrbill();
                Sale_Page s=new Sale_Page(2);
                s.setVisible(true);
                this.dispose();
            }
            if(flag==3){
                savecrprodbill();
                savecrbill();
                new Sale_List(1).setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        char c= evt.getKeyChar();
        if((Character.isLetter(c) && !evt.isAltDown()) || evt.getKeyCode()==KeyEvent.VK_PERIOD)
            evt.consume();
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed

    }//GEN-LAST:event_jTextField17ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sale_Page(flag).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField10;
    public static javax.swing.JTextField jTextField11;
    public static javax.swing.JTextField jTextField12;
    public static javax.swing.JTextField jTextField13;
    public static javax.swing.JTextField jTextField14;
    public static javax.swing.JTextField jTextField15;
    public static javax.swing.JTextField jTextField16;
    public static javax.swing.JTextField jTextField17;
    public static javax.swing.JTextField jTextField18;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    public static javax.swing.JTextField jTextField9;
    public static org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables
}
