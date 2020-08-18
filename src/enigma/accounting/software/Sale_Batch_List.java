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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */

public class Sale_Batch_List extends javax.swing.JFrame {
    class soldProd{
        String batch,exp;
        int qts,qtp;
        double mrp;
    }
    class soldProdList{
        String batch,exp,qty,mrp;
    }
    public static int pid,i;
    ArrayList<soldProd> sparray=new ArrayList<soldProd>();
    public Sale_Batch_List(int p,int m) {
        initComponents();
        
        pid=p;
        i=m;
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        if(i==1)
            soldBatch();
        jTextField1.setText(Sale_Page.jTextField8.getText());
        if(i==0)
            findBatch();
        else
            findSoldBatch();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
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
                    if(i==0){
                        Sale_Product_List s=new Sale_Product_List();
                        s.setVisible(true);
                    }
                    else
                        new Sale_Product_List(1).setVisible(true);
                    Sale_Page.jTextField8.setText("");
                    Sale_Batch_List.this.dispose(); 
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(i==0)
                    findBatch();
                else
                    findSoldBatch();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    if(i==0)
                        findBatch();
                    else
                        findSoldBatch();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String batch=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    Sale_Page.exp=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    String mrp=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),3).toString();
                    String qty=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),2).toString();
                    StringTokenizer st=new StringTokenizer(qty," : ");
                    String qtys=st.nextToken();
                    String qtyp=st.nextToken();
                    Sale_Page.qs=Integer.parseInt(qtys);
                    Sale_Page.qp=Integer.parseInt(qtyp);
                    
                    Sale_Page.jTextField8.setText(batch);
                    Sale_Page.jTextField12.setText(mrp);
                    Sale_Page.pid=pid;
                            
                    Sale_Page.jTextField10.requestFocus();
                    Sale_Page.jTextField10.selectAll();
                                            
                    Sale_Batch_List.this.dispose(); 
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    if(i==0){
                        Sale_Product_List s=new Sale_Product_List();
                        s.setVisible(true);
                    }
                    else
                        new Sale_Product_List(1).setVisible(true);
                    Sale_Page.jTextField8.setText("");
                    Sale_Batch_List.this.dispose(); 
                }
            }
        });
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
    }
    public Sale_Batch_List(int p,char a) {
        initComponents();
        
        pid=p;
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jTextField1.setText(Purchase_Page.jTextField3.getText());
        findPurcBatch();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
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
                    new Sale_Product_List('a').setVisible(true);            
                    Purchase_Page.jTextField3.setText("");
                    Sale_Batch_List.this.dispose(); 
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findPurcBatch();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findPurcBatch();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String batch=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    Purchase_Page.p=pid;
                    Purchase_Page.jTextField3.setText(batch);
                    
                    String exp=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    StringTokenizer st2=new StringTokenizer(exp,"/"); 
                    String m=st2.nextToken();
                    String y=st2.nextToken();
                    Purchase_Page.jTextField5.setText(m);
                    Purchase_Page.jTextField6.setText(y);
                    
                    String qty=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),2).toString();
                    StringTokenizer st=new StringTokenizer(qty," : ");
                    String qtys=st.nextToken();
                    Purchase_Page.qs=Integer.parseInt(qtys);
                    
                    String sql="SELECT * FROM BATCH WHERE PID=? AND BATCHNO=?;";
                    Connection conn=MySqlConnect.ConnectDB();
                    try {
                        PreparedStatement ps=conn.prepareStatement(sql);
                        ps.setInt(1, pid);
                        ps.setString(2, batch);
                        ResultSet rs=ps.executeQuery();
                        rs.next();
                        
                        Purchase_Page.jTextField4.setText(String.valueOf(rs.getDouble("MRP")));
                        Purchase_Page.jTextField13.setText(String.valueOf(rs.getDouble("TRP")));
                        Purchase_Page.jTextField16.setText("0.00");
                    } 
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    Purchase_Page.jTextField4.requestFocus();
                    Purchase_Page.jTextField4.selectAll();
                    Sale_Batch_List.this.dispose(); 
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    new Sale_Product_List('a').setVisible(true);
                    Purchase_Page.jTextField3.setText("");
                    Purchase_Page.jTextField4.setText("");
                    Purchase_Page.jTextField5.setText("");
                    Purchase_Page.jTextField6.setText("");
                    Purchase_Page.jTextField13.setText("");
                    Purchase_Page.jTextField14.setText("");
                    Purchase_Page.jTextField15.setText("");
                    Purchase_Page.jTextField16.setText("");
                    Sale_Batch_List.this.dispose(); 
                }
            }
        });
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
    }
    public Sale_Batch_List(int p,int a, int b) {
        initComponents();
        
        pid=p;
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
  
        findPurcBatchDetails();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
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
                    new Sale_Product_List(2,3).setVisible(true);            
                    Sale_Batch_List.this.dispose(); 
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findPurcBatchDetails();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findPurcBatchDetails();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String batch=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    new Purchase_Details(pid,batch).setVisible(true);
                    Sale_Batch_List.this.dispose(); 
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    new Sale_Product_List(1,2).setVisible(true);
                    Sale_Batch_List.this.dispose(); 
                }
            }
        });
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
    }
    
    private ArrayList<BatchList> Batch(String ValToSearch){
        
        ArrayList<BatchList> batch=new ArrayList<BatchList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT BATCHNO,QTYSTP,QTYPCS,EXP,MRP FROM BATCH WHERE PID=" +pid +" AND BATCHNO NOT IN(SELECT BATCHNO FROM BATCH WHERE PID=" +pid +" AND QTYSTP=0 AND QTYPCS=0) AND BATCHNO LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            BatchList bt;
            
            while(rs.next()){
                String b=rs.getString("batchno");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                int pk=(int) Double.parseDouble(Sale_Page.jTextField7.getText());
                    
                for(int i=0;i<Sale_Page.jTable1.getRowCount();i++){
                    String pname=Sale_Page.jTable1.getValueAt(i, 0).toString();
                    int ppk=(int) Double.parseDouble(Sale_Page.jTable1.getValueAt(i, 1).toString());
                    String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                    PreparedStatement ps=conn.prepareStatement(sql2);
                    ps.setString(1, pname);
                    ps.setInt(2, ppk);
                    ResultSet rs3=ps.executeQuery();
                    rs3.next();
                    int p=rs3.getInt("pid");
                    if(p==pid && Sale_Page.jTable1.getValueAt(i, 2).toString().equalsIgnoreCase(b)){
                        int tqs=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,4).toString());
                        int tqp=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,5).toString());
                        if(qp-tqp < 0){
                            qs=qs-tqs-1;
                            qp=qp+pk-tqp;
                        }
                        else{
                            qs-=tqs;
                            qp-=tqp;
                        }
                    }
                }
                bt=new BatchList(rs.getString("batchno"), rs.getString("exp"), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                batch.add(bt);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return batch;
    }
    
    public void findBatch(){
        ArrayList<BatchList> prod=Batch(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BATCH NO.","EXPIRY","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getBatch();
            row[1]=prod.get(i).getExp();
            row[2]=prod.get(i).getQty();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
    }
    private void soldBatch(){
        Connection conn=MySqlConnect.ConnectDB();
        String sql="SELECT BILLNO FROM SALE WHERE BDATE > CURRENT_DATE - INTERVAL 1 MONTH;";
        String sql2="SELECT * FROM SALEPROD WHERE BILLNO=? AND PID=?;";
        try{
            Statement s=conn.createStatement();
            ResultSet bn=s.executeQuery(sql);
            while(bn.next()){
                PreparedStatement ps=conn.prepareStatement(sql2);
                ps.setString(1, bn.getString("BILLNO"));
                ps.setInt(2, pid);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    int f=0;
                    for(int i=0;i<sparray.size();i++){
                        if(sparray.get(i).batch.equalsIgnoreCase(rs.getString("BATCHNO"))){
                            f=1;
                            sparray.get(i).qts+=rs.getInt("QTYSTP");
                            sparray.get(i).qtp+=rs.getInt("QTYPCS");
                            sparray.get(i).exp=rs.getString("EXP");
                            sparray.get(i).mrp=rs.getDouble("MRP");
                        }
                    }
                    if(f==0){
                        soldProd sp=new soldProd();
                        sp.batch=rs.getString("BATCHNO");
                        sp.exp=rs.getString("EXP");
                        sp.qts=rs.getInt("QTYSTP");
                        sp.qtp=rs.getInt("QTYPCS");
                        sp.mrp=rs.getDouble("MRP");
                        sparray.add(sp);
                    }
                }
                int pkg=(int) Double.parseDouble(Sale_Page.jTextField7.getText());
                for(int i=0;i<sparray.size();i++){
                    String sql4="SELECT * FROM SALERETURNPROD WHERE PID=" +pid +";";
                    Statement s4=conn.createStatement();
                    ResultSet rs4=s4.executeQuery(sql4);
                    while(rs4.next()){
                        if(rs4.getString("BATCHNO").equalsIgnoreCase(sparray.get(i).batch)){
                            sparray.get(i).qts -= rs4.getInt("QTYSTP");
                            sparray.get(i).qtp -= rs4.getInt("QTYPCS");
                        }
                    }
                    if(sparray.get(i).qtp < 0){
                        sparray.get(i).qts -=1;
                        sparray.get(i).qtp += pkg;
                    }
                    if(sparray.get(i).qtp > pkg){
                        sparray.get(i).qts += ((int)(sparray.get(i).qtp / pkg));
                        sparray.get(i).qtp = sparray.get(i).qtp % pkg;
                    }
                    if(sparray.get(i).qts<0 || (sparray.get(i).qts==0 && sparray.get(i).qtp<=0))
                        sparray.remove(i);
                }
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private ArrayList<soldProdList> soldProducts(String ValToSearch){
        int pkg=(int) Double.parseDouble(Sale_Page.jTextField7.getText());
        String sql="SELECT BATCHNO FROM BATCH WHERE PID=" +pid  +" AND BATCHNO LIKE '" +ValToSearch +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        ArrayList<soldProdList> sprod=new ArrayList<soldProdList>();
        try{
            for(int k=0;k<sparray.size();k++){
                Statement s=conn.createStatement();
                ResultSet rs=s.executeQuery(sql);
                int f=0;
                while(rs.next()){
                    if(sparray.get(k).batch.equalsIgnoreCase(rs.getString("BATCHNO"))){
                        f=1;
                    }
                }
                if(f==1){
                    int qs=sparray.get(k).qts;
                    int qp=sparray.get(k).qtp;
                    for(int i=0;i<Sale_Page.jTable1.getRowCount();i++){
                        String pname=Sale_Page.jTable1.getValueAt(i, 0).toString();
                        int ppk=(int) Double.parseDouble(Sale_Page.jTable1.getValueAt(i, 1).toString());
                        String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                        PreparedStatement ps=conn.prepareStatement(sql2);
                        ps.setString(1, pname);
                        ps.setInt(2, ppk);
                        ResultSet rs3=ps.executeQuery();
                        rs3.next();
                        int p=rs3.getInt("pid");
                        if(p==pid && Sale_Page.jTable1.getValueAt(i, 2).toString().equalsIgnoreCase(sparray.get(k).batch)){
                            int tqs=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,4).toString());
                            int tqp=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,5).toString());
                            if(qp-tqp < 0){
                                qs=qs-tqs-1;
                                qp=qp+pkg-tqp;
                            }
                            else{
                                qs-=tqs;
                                qp-=tqp;
                            }
                        }
                    }
                    soldProdList spl=new soldProdList();
                    spl.batch=sparray.get(k).batch;
                    spl.exp=sparray.get(k).exp;
                    spl.qty=String.valueOf(qs) +" : " +String.valueOf(qp);
                    spl.mrp=String.valueOf(sparray.get(k).mrp);
                    sprod.add(spl);
                }
            }
            return sprod;
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return sprod;
    }
    private void findSoldBatch(){
        ArrayList<soldProdList> sprod=soldProducts(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BATCH NO.","EXPIRY","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<sprod.size();i++){
            row[0]=sprod.get(i).batch;
            row[1]=sprod.get(i).exp;
            row[2]=sprod.get(i).qty;
            row[3]=sprod.get(i).mrp;
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
    }

    private ArrayList<BatchList> PurcBatch(String ValToSearch){
        
        ArrayList<BatchList> batch=new ArrayList<BatchList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT BATCHNO,QTYSTP,QTYPCS,EXP,MRP FROM BATCH WHERE PID=" +pid +" AND BATCHNO NOT IN(SELECT BATCHNO FROM BATCH WHERE PID=" +pid +" AND QTYSTP=0 AND QTYPCS=0) AND BATCHNO LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            BatchList bt;
            
            while(rs.next()){
                String b=rs.getString("batchno");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                    
                for(int i=0;i<Purchase_Page.jTable1.getRowCount();i++){
                    String pname=Purchase_Page.jTable1.getValueAt(i, 0).toString();
                    int ppk=(int) Double.parseDouble(Purchase_Page.jTable1.getValueAt(i, 1).toString());
                    String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                    PreparedStatement ps=conn.prepareStatement(sql2);
                    ps.setString(1, pname);
                    ps.setInt(2, ppk);
                    ResultSet rs3=ps.executeQuery();
                    rs3.next();
                    int p=rs3.getInt("pid");
                    if(p==pid && Purchase_Page.jTable1.getValueAt(i, 3).toString().equalsIgnoreCase(b)){
                        int tqs=Integer.parseInt(Purchase_Page.jTable1.getValueAt(i,5).toString())+Integer.parseInt(Purchase_Page.jTable1.getValueAt(i,6).toString());
                        qs-=tqs;
                    }
                }
                bt=new BatchList(rs.getString("batchno"), rs.getString("exp"), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                batch.add(bt);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return batch;
    }
    
    public void findPurcBatch(){
        ArrayList<BatchList> prod=PurcBatch(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BATCH NO.","EXPIRY","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getBatch();
            row[1]=prod.get(i).getExp();
            row[2]=prod.get(i).getQty();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
    }
    private ArrayList<BatchList> PurcBatchDetails(String ValToSearch){
        
        ArrayList<BatchList> batch=new ArrayList<>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT BATCHNO,QTYSTP,QTYPCS,EXP,MRP FROM BATCH WHERE PID=" +pid +" AND BATCHNO NOT IN(SELECT BATCHNO FROM BATCH WHERE PID=" +pid +" AND QTYSTP=0 AND QTYPCS=0) AND BATCHNO LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            BatchList bt;
            
            while(rs.next()){
                String b=rs.getString("batchno");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                bt=new BatchList(rs.getString("batchno"), rs.getString("exp"), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                batch.add(bt);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return batch;
    }
    
    public void findPurcBatchDetails(){
        ArrayList<BatchList> prod=PurcBatchDetails(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"BATCH NO.","EXPIRY","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getBatch();
            row[1]=prod.get(i).getExp();
            row[2]=prod.get(i).getQty();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BATCH NO.", "EXPIRY", "QUANTITY", "MRP"
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
        jLabel2.setText("BATCH LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            java.util.logging.Logger.getLogger(Sale_Batch_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sale_Batch_List(pid,i).setVisible(true);
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
