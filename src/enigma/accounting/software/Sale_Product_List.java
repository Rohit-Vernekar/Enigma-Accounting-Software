/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;

import java.awt.AWTException;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
public class Sale_Product_List extends javax.swing.JFrame {

    class soldProd{
        int pid,qts,qtp,pkg;
        double mrp;
        String product;
    }
    class soldProdList{
        String product,pkg,qty,mrp;
    }
    ArrayList<soldProd> sparray=new ArrayList<soldProd>();
    /**
     * Creates new form Sale_Product_List
     */
    public Sale_Product_List() {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jTextField1.setText(Sale_Page.jTextField6.getText());
        
        findProduct();
        
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
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Page.jTextField6.requestFocus();
                    Sale_Page.jTextField6.setText("");
                    Sale_Page.jTextField7.setText("");
                    Sale_Page.jTextField8.setText("");
                    Sale_Page.jTextField9.setText("");
                    Sale_Page.jTextField10.setText("");
                    Sale_Page.jTextField11.setText("");
                    Sale_Page.jTextField12.setText("");
                    Sale_Product_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findProduct();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findProduct();
                }              
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    String pk=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PID,PNAME,PKG,HSN FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        st2=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        String hsncode=rs.getString("hsn");
                        String sql2="SELECT CODE,SGST,CGST FROM HSN WHERE HSN='" +hsncode +"';";
                        rs2=st2.executeQuery(sql2);
                        rs2.next();
                        
                        int pid=rs.getInt("pid");
                        Sale_Page.jTextField6.setText(rs.getString("pname"));
                        Sale_Page.jTextField9.setText("0");
                        Sale_Page.jTextField10.setText("0");
                        Sale_Page.jTextField11.setText("0.0");
                        Sale_Page.jTextField7.setText(String.valueOf(rs.getInt("pkg")));
                        Sale_Page.hsn=rs2.getString("code");
                        Sale_Page.cgst=rs2.getDouble("cgst");
                        Sale_Page.sgst=rs2.getDouble("sgst");
                        
                        new Sale_Batch_List(pid,0).setVisible(true);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Sale_Product_List.this.dispose(); 
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Page.jTextField6.requestFocus();
                    Sale_Product_List.this.dispose();
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
    public Sale_Product_List(int f) {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        soldProducts();
        
        jTextField1.setText(Sale_Page.jTextField6.getText());
        
        findReturnProduct();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN){
                    jTable1.requestFocus();
                    try{
                        Robot r=new Robot();
                        r.keyPress(KeyEvent.VK_DOWN);
                    }
                    catch(AWTException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Page.jTextField6.requestFocus();
                    Sale_Page.jTextField6.setText("");
                    Sale_Page.jTextField7.setText("");
                    Sale_Page.jTextField8.setText("");
                    Sale_Page.jTextField9.setText("");
                    Sale_Page.jTextField10.setText("");
                    Sale_Page.jTextField11.setText("");
                    Sale_Page.jTextField12.setText("");
                    Sale_Product_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findReturnProduct();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findReturnProduct();
                }              
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    String pk=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PID,PNAME,PKG,HSN FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        st2=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        String hsncode=rs.getString("hsn");
                        String sql2="SELECT CODE,SGST,CGST FROM HSN WHERE HSN='" +hsncode +"';";
                        rs2=st2.executeQuery(sql2);
                        rs2.next();
                        
                        int pid=rs.getInt("pid");
                        Sale_Page.jTextField6.setText(rs.getString("pname"));
                        Sale_Page.jTextField9.setText("0");
                        Sale_Page.jTextField10.setText("0");
                        Sale_Page.jTextField11.setText("0.0");
                        Sale_Page.jTextField7.setText(String.valueOf(rs.getInt("pkg")));
                        Sale_Page.hsn=rs2.getString("code");
                        Sale_Page.cgst=rs2.getDouble("cgst");
                        Sale_Page.sgst=rs2.getDouble("sgst");
                        
                        new Sale_Batch_List(pid,1).setVisible(true);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Sale_Product_List.this.dispose(); 
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Page.jTextField6.requestFocus();
                    Sale_Product_List.this.dispose();
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
    public Sale_Product_List(char a) {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jTextField1.setText(Purchase_Page.jTextField12.getText());
        
        findPurcProduct();
        
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
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Purchase_Page.jTextField12.requestFocus();
                    Purchase_Page.jTextField12.setText("");
                    Purchase_Page.jTextField3.setText("");
                    Purchase_Page.jTextField5.setText("");
                    Purchase_Page.jTextField6.setText("");
                    Purchase_Page.jTextField4.setText("");
                    Purchase_Page.jTextField13.setText("");
                    Purchase_Page.jTextField14.setText("");
                    Purchase_Page.jTextField15.setText("");
                    Purchase_Page.jTextField16.setText("");
                    Sale_Product_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findPurcProduct();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findPurcProduct();
                }              
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    String pk=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PID,PNAME,PKG,HSN,MRP,TRP,DISCOUNT FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        st2=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        String hsncode=rs.getString("hsn");
                        String sql2="SELECT CODE,SGST,CGST FROM HSN WHERE HSN='" +hsncode +"';";
                        rs2=st2.executeQuery(sql2);
                        rs2.next();
                        
                        int pid=rs.getInt("pid");
                        Purchase_Page.p=pid;
                        Purchase_Page.jTextField12.setText(rs.getString("pname"));
                        Purchase_Page.pkg=rs.getString("pkg");
                        Purchase_Page.hsn=rs2.getString("code");
                        Purchase_Page.cgst=String.valueOf(rs2.getDouble("cgst"));
                        Purchase_Page.sgst=String.valueOf(rs2.getDouble("sgst"));
                        Purchase_Page.jTextField3.requestFocus();
                        
                        new Sale_Batch_List(pid,'a').setVisible(true);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Sale_Product_List.this.dispose(); 
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Purchase_Page.jTextField12.setText("");
                    Purchase_Page.jTextField3.setText("");
                    Purchase_Page.jTextField5.setText("");
                    Purchase_Page.jTextField6.setText("");
                    Purchase_Page.jTextField4.setText("");
                    Purchase_Page.jTextField13.setText("");
                    Purchase_Page.jTextField14.setText("");
                    Purchase_Page.jTextField15.setText("");
                    Purchase_Page.jTextField16.setText("");
                    Purchase_Page.jTextField12.requestFocus();
                    Sale_Product_List.this.dispose();
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
    public Sale_Product_List(int a, int b) {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        findPurcProductDetails();
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN){
                    jTable1.requestFocus();
                    try{
                        Robot r=new Robot();
                        r.keyPress(KeyEvent.VK_DOWN);
                    }
                    catch(AWTException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Product_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findPurcProductDetails();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findPurcProductDetails();
                }              
            }
            @Override
            public void keyPressed(KeyEvent e) {   
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    String pk=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PID FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        int pid=rs.getInt("pid");
                        
                        new Sale_Batch_List(pid, a, b).setVisible(true);
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Sale_Product_List.this.dispose(); 
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    Sale_Product_List.this.dispose();
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
    private ArrayList<ProductList> Products(String ValToSearch){
        
        ArrayList<ProductList> prod=new ArrayList<>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT PNAME,PKG,QTYSTP,QTYPCS,MRP FROM PRODUCT WHERE PID NOT IN (SELECT PID FROM PRODUCT WHERE QTYSTP=0 AND QTYPCS=0) AND PNAME LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            ProductList pl;
            
            while(rs.next()){
                int pk=rs.getInt("pkg");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                
                for(int i=0;i<Sale_Page.jTable1.getRowCount();i++){
                    if(Sale_Page.jTable1.getValueAt(i, 0).toString().equalsIgnoreCase(rs.getString("pname")) && Sale_Page.jTable1.getValueAt(i, 1).toString().equalsIgnoreCase(rs.getString("pkg"))){
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
                pl=new ProductList(rs.getString("pname"), String.valueOf(rs.getInt("pkg")), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                prod.add(pl);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return prod;
    }

    
    public void findProduct(){
        ArrayList<ProductList> prod=Products(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"PRODUCT","PACKING","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getProduct();
            row[1]=prod.get(i).getPacking();
            row[2]=prod.get(i).getQuantity();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
    }
    private void soldProducts(){
        Connection conn=MySqlConnect.ConnectDB();
        String sql="SELECT BILLNO FROM SALE;";
        String sql2="SELECT * FROM SALEPROD WHERE BILLNO=?;";
        String sql3="SELECT * FROM PRODUCT WHERE PID=?; ";
        try{
            Statement s=conn.createStatement();
            ResultSet bn=s.executeQuery(sql);
            while(bn.next()){
                PreparedStatement ps=conn.prepareStatement(sql2);
                ps.setString(1, bn.getString("BILLNO"));
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    int f=0;
                    for(int i=0;i<sparray.size();i++){
                        if(sparray.get(i).pid==rs.getInt("PID")){
                            f=1;
                            sparray.get(i).qts+=rs.getInt("QTYSTP");
                            sparray.get(i).qtp+=rs.getInt("QTYPCS");
                            sparray.get(i).mrp=rs.getDouble("MRP");
                        }
                    }
                    if(f==0){
                        PreparedStatement ps2=conn.prepareStatement(sql3);
                        ps2.setInt(1, rs.getInt("PID"));
                        ResultSet rs2=ps2.executeQuery();
                        rs2.next();
                        soldProd sp=new soldProd();
                        sp.pid=rs.getInt("PID");
                        sp.qts=rs.getInt("QTYSTP");
                        sp.qtp=rs.getInt("QTYPCS");
                        sp.mrp=rs.getDouble("MRP");
                        sp.product=rs2.getString("PNAME");
                        sp.pkg=rs2.getInt("PKG");
                        sparray.add(sp);
                    }
                }
                for(int i=0;i<sparray.size();i++){
                    String sql4="SELECT * FROM SALERETURNPROD;";
                    Statement s4=conn.createStatement();
                    ResultSet rs4=s4.executeQuery(sql4);
                    while(rs4.next()){
                        if(rs4.getInt("PID") == sparray.get(i).pid){
                            sparray.get(i).qts -= rs4.getInt("QTYSTP");
                            sparray.get(i).qtp -= rs4.getInt("QTYPCS");
                        }
                    }
                    if(sparray.get(i).qtp < 0){
                        sparray.get(i).qts -=1;
                        sparray.get(i).qtp += sparray.get(i).pkg;
                    }
                    if(sparray.get(i).qtp > sparray.get(i).pkg){
                        sparray.get(i).qts += ((int)(sparray.get(i).qtp / sparray.get(i).pkg));
                        sparray.get(i).qtp = sparray.get(i).qtp % sparray.get(i).pkg;
                    }
                    
                    if(sparray.get(i).qts<0 || (sparray.get(i).qts==0 && sparray.get(i).qtp<=0))
                        sparray.remove(i);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private ArrayList<soldProdList> soldProducts(String ValToSearch){
        String sql="SELECT PID FROM PRODUCT WHERE PNAME LIKE '" +ValToSearch +"%';";
        Connection conn=MySqlConnect.ConnectDB();
        ArrayList<soldProdList> sprod=new ArrayList<soldProdList>();
        try{
            for(int k=0;k<sparray.size();k++){
                Statement s=conn.createStatement();
                ResultSet rs=s.executeQuery(sql);
                int f=0;
                while(rs.next()){
                    if(sparray.get(k).pid==rs.getInt("PID")){
                        f=1;
                    }
                }
                if(f==1){
                    int qs=sparray.get(k).qts;
                    int qp=sparray.get(k).qtp;
                    for(int i=0;i<Sale_Page.jTable1.getRowCount();i++){
                        if(Sale_Page.jTable1.getValueAt(i, 0).toString().equalsIgnoreCase(sparray.get(k).product) && Sale_Page.jTable1.getValueAt(i, 1).toString().equalsIgnoreCase(String.valueOf(sparray.get(k).pkg))){
                            int tqs=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,4).toString());
                            int tqp=Integer.parseInt(Sale_Page.jTable1.getValueAt(i,5).toString());
                            if(qp-tqp < 0){
                                qs=qs-tqs-1;
                                qp=qp + sparray.get(k).pkg - tqp;
                            }
                            else{
                                qs-=tqs;
                                qp-=tqp;                            
                            }
                        }
                    }
                    soldProdList spl=new soldProdList();
                    spl.product=sparray.get(k).product;
                    spl.pkg=String.valueOf(sparray.get(k).pkg);
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
    private void findReturnProduct(){
        ArrayList<soldProdList> sprod=soldProducts(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"PRODUCT","PACKING","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<sprod.size();i++){
            row[0]=sprod.get(i).product;
            row[1]=sprod.get(i).pkg;
            row[2]=sprod.get(i).qty;
            row[3]=sprod.get(i).mrp;
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
    }
    
    private ArrayList<ProductList> PurcProducts(String ValToSearch){
        
        ArrayList<ProductList> prod=new ArrayList<ProductList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT PNAME,PKG,QTYSTP,QTYPCS,MRP FROM PRODUCT WHERE PID NOT IN (SELECT PID FROM PRODUCT WHERE QTYSTP=0 AND QTYPCS=0) AND PNAME LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            ProductList pl;
            
            while(rs.next()){
                int pk=rs.getInt("pkg");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                
                for(int i=0;i<Purchase_Page.jTable1.getRowCount();i++){
                    if(Purchase_Page.jTable1.getValueAt(i, 0).toString().equalsIgnoreCase(rs.getString("pname")) && Purchase_Page.jTable1.getValueAt(i, 1).toString().equalsIgnoreCase(rs.getString("pkg"))){
                        int tqs=Integer.parseInt(Purchase_Page.jTable1.getValueAt(i,5).toString())+Integer.parseInt(Purchase_Page.jTable1.getValueAt(i,6).toString());
                        int tqp=0;
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
                pl=new ProductList(rs.getString("pname"), String.valueOf(rs.getInt("pkg")), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                prod.add(pl);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return prod;
    }

    
    public void findPurcProduct(){
        ArrayList<ProductList> prod=PurcProducts(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"PRODUCT","PACKING","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getProduct();
            row[1]=prod.get(i).getPacking();
            row[2]=prod.get(i).getQuantity();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
    }
    private ArrayList<ProductList> PurcProductsDetails(String ValToSearch){
        
        ArrayList<ProductList> prod=new ArrayList<ProductList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT PNAME,PKG,QTYSTP,QTYPCS,MRP FROM PRODUCT WHERE PID NOT IN (SELECT PID FROM PRODUCT WHERE QTYSTP=0 AND QTYPCS=0) AND PNAME LIKE '" +ValToSearch +"%';";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            ProductList pl;
            
            while(rs.next()){
                int pk=rs.getInt("pkg");
                int qs=rs.getInt("qtystp");
                int qp=rs.getInt("qtypcs");
                pl=new ProductList(rs.getString("pname"), String.valueOf(rs.getInt("pkg")), String.valueOf(qs)+" : "+String.valueOf(qp),String.valueOf(rs.getDouble("mrp")));
                prod.add(pl);
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return prod;
    }

    
    public void findPurcProductDetails(){
        ArrayList<ProductList> prod=PurcProductsDetails(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"PRODUCT","PACKING","QUANTITY","MRP"});
        Object row[]=new Object[4];
        for(int i=0;i<prod.size();i++){
            row[0]=prod.get(i).getProduct();
            row[1]=prod.get(i).getPacking();
            row[2]=prod.get(i).getQuantity();
            row[3]=prod.get(i).getMrp();
            model.addRow(row);
        }
        jTable1.setModel(model);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
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
                    g2d.drawString("PRODUCT NOT AVAILABLE.",10,20);
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
                "PRODUCT", "PACKING", "QUANTITY", "MRP"
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
        jLabel2.setText("PRODUCT LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Sale_Product_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sale_Product_List().setVisible(true);
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
