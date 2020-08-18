
package enigma.accounting.software;


import java.awt.Color;
import java.awt.HeadlessException;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
class purcprod{
    String batchno,exp;
    int pid,qty,fqty,pbid;
    double mrp,trp,disc;
}
public class Purchase_Page extends javax.swing.JFrame {
    public static String pkg,hsn,cgst,sgst,agency;
    public static int flag,pbid,p,qs;
    public static double due;
    public static String aname;
    public ArrayList<purcprod> spdarray;
    @SuppressWarnings("empty-statement")
    public Purchase_Page(int f) {
        initComponents();
        flag=f;
        if(flag==2 || flag==3){
            jLabel3.setText("PURCHASE RETURN");
            jTextField3.setEditable(false);
            jTextField5.setEditable(false);
            jTextField6.setEditable(false);
        }
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            { 
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","EXIT?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO PURCRETURNPROD VALUES(?,?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setInt(1,spdarray.get(i).pid);
                                ps.setString(2, spdarray.get(i).batchno);
                                ps.setString(3, spdarray.get(i).exp);
                                ps.setInt(4, spdarray.get(i).qty);
                                ps.setInt(7, spdarray.get(i).fqty);
                                ps.setDouble(5, spdarray.get(i).trp);
                                ps.setDouble(6, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.setInt(9, spdarray.get(i).pbid);
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
        jTextField5.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
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
        jXDatePicker1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        jComboBox1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Enigma Accounting Software");
        getContentPane().setBackground(Color.WHITE);
        
        Image img = new ImageIcon(getClass().getResource("/Enigma_Logo_Rectangle.png")).getImage();;
        Image newimg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
        jLabel2.setIcon(new ImageIcon(newimg));
        
        jXDatePicker1.setDate(new Date());
        jXDatePicker1.requestFocus();
        
        jXDatePicker1.getEditor().addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Agency_List a=new Agency_List();
                    a.setVisible(true);
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO PURCRETURNPROD VALUES(?,?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setInt(1,spdarray.get(i).pid);
                                ps.setString(2, spdarray.get(i).batchno);
                                ps.setString(3, spdarray.get(i).exp);
                                ps.setInt(4, spdarray.get(i).qty);
                                ps.setInt(7, spdarray.get(i).fqty);
                                ps.setDouble(5, spdarray.get(i).trp);
                                ps.setDouble(6, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.setInt(9, spdarray.get(i).pbid);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                        new Purchase_List(1).setVisible(true);
                        Purchase_Page.this.dispose();
                    }
                    if(flag==0  || flag==2){
                        new Start_Page().setVisible(true);
                        Purchase_Page.this.dispose();
                    }
                    if(flag==1){
                        new Purchase_List(0).setVisible(true);
                        Purchase_Page.this.dispose();
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
        jTextField7.setText("0.00");
        jTextField8.setText("0.00");
        jTextField9.setText("0.00");
        jTextField10.setText("0.00");
        jTextField11.setText("0.00");
    }
    private void getamt(){
        double amt=0;
        for(int i=0;i<jTable1.getRowCount();i++)
            amt+=Double.parseDouble(jTable1.getValueAt(i, 12).toString());
        amt = Math.round(amt * 100D) / 100D;
        jTextField7.setText(String.valueOf(amt));
    }
    private void getgst(){
        double gst=0;
        for(int i=0;i<jTable1.getRowCount();i++){
            double trp=Double.parseDouble(jTable1.getValueAt(i, 12).toString())-(Double.parseDouble(jTable1.getValueAt(i, 12).toString())*Double.parseDouble(jTable1.getValueAt(i, 9).toString())/100);
            gst+=Double.parseDouble(jTable1.getValueAt(i, 11).toString())*trp/100;
        }
        gst = Math.round(gst * 100D) / 100D;
        jTextField9.setText(String.valueOf(gst));
        jTextField10.setText(String.valueOf(gst));
    }
    private void getdisc(){
        double disc=0;
        for(int i=0;i<jTable1.getRowCount();i++)
            disc+=Double.parseDouble(jTable1.getValueAt(i, 9).toString())*Double.parseDouble(jTable1.getValueAt(i, 12).toString())/100;
        disc = Math.round(disc * 100D) / 100D;
        jTextField8.setText(String.valueOf(disc));
    }
    private void gettamt(){
        double tamt = Double.parseDouble(jTextField7.getText())+(2*Double.parseDouble(jTextField9.getText())) - Double.parseDouble(jTextField8.getText());
        tamt = Math.round(tamt * 100D) / 100D;
        jTextField11.setText(String.valueOf(tamt));
    }
    public void deleteData(){   
        spdarray = new ArrayList<purcprod>();
        int id;
        String agen=jTextField1.getText();
        String invno=jTextField2.getText();
        String sql="SELECT * FROM PURCRETURNPROD WHERE PURCHASERETURNBILLID=?;";
        String sql2="DELETE FROM PURCRETURNPROD WHERE PURCHASERETURNBILLID=?;";
        String sql3="SELECT PURCHASERETURNBILLID FROM PURCHASERETURN WHERE AGENCY=? AND INVNO=?;";
        try {
            Connection conn=MySqlConnect.ConnectDB();                
            PreparedStatement ps=conn.prepareStatement(sql3);
            ps.setString(1, agen);
            ps.setString(2, invno);
            ResultSet rs=ps.executeQuery();
            rs.next();
            id=rs.getInt("PURCHASERETURNBILLID");
            
            PreparedStatement ps2=conn.prepareStatement(sql);
            ps2.setInt(1, id);
            ResultSet rs2=ps2.executeQuery();
            while(rs2.next()){
                purcprod spd=new purcprod();
                spd.pid=rs2.getInt("PID");
                spd.batchno=rs2.getString("BATCHNO");
                spd.exp=rs2.getString("EXP");
                spd.qty=rs2.getInt("QTY");
                spd.fqty=rs2.getInt("FREEQTY");
                spd.mrp=rs2.getDouble("MRP");
                spd.trp=rs2.getDouble("TRP");
                spd.disc=rs2.getDouble("DISCOUNT");
                spd.pbid=rs2.getInt("PURCHASERETURNBILLID");
                spdarray.add(spd);
            }
            PreparedStatement ps3=conn.prepareStatement(sql2);
            ps3.setInt(1, id);
            ps3.executeUpdate();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
         }
    }
    private void setMargin(){
        if((int)Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString())==0){
            double cost=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString()) - (Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 8).toString()) * Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString())/100);
            double mrp=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
            double margin=100.0 -((cost/mrp)*100);
            
            double trp=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString())-(Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString())*Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString())/100);
            double gst=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 11).toString())*trp/100;
            
            margin = Math.round(margin * 100D) / 100D;
            gst = Math.round(gst * 100D) / 100D;
            
            jLabel24.setText(String.valueOf(margin) + "%");
            jLabel23.setText(String.valueOf(gst));
            jLabel25.setText(String.valueOf(gst));
        }
        else{
            double trp = Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString())/((int)Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString()) + (int)Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString()));
            double disc = trp * Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString())/100;
            double mrp=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
            double margin=100.0 -(((trp-disc)/mrp)*100);
            
            double trp2=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString())-(Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 12).toString())*Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 9).toString())/100);
            double gst=Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(), 11).toString())*trp2/100;
            
            margin = Math.round(margin * 100D) / 100D;
            gst = Math.round(gst * 100D) / 100D;
            
            jLabel24.setText(String.valueOf(margin) + "%");
            jLabel23.setText(String.valueOf(gst));
            jLabel25.setText(String.valueOf(gst));
        }
    }
    private void savepurcprod(){
        Connection conn=MySqlConnect.ConnectDB();
        Statement s,s2;
        PreparedStatement ps1,ps2,ps3;
        ResultSet rs;
        String sql1="SELECT PURCHASEBILLID FROM DETAILS WHERE ID=1;";
        
        try{
            s=conn.createStatement();
            rs=s.executeQuery(sql1);
            rs.next();
            int id=rs.getInt("PURCHASEBILLID");
            for(int i=0;i<jTable1.getRowCount();i++){
                String sql3="INSERT INTO PURCPROD VALUES(?,?,?,?,?,?,?,?,?);";                
                String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                
                ps1=conn.prepareStatement(sql2);
                ps1.setString(1, jTable1.getValueAt(i,0).toString());
                ps1.setInt(2, (int)Double.parseDouble(jTable1.getValueAt(i,1).toString()));
                rs=ps1.executeQuery();
                rs.next();
                int pid=rs.getInt("PID");
                
                ps2=conn.prepareStatement(sql3);
                if(flag==0)
                    ps2.setInt(9, id);
                else
                    ps2.setInt(9,pbid);
                ps2.setInt(1, pid);
                ps2.setString(2, jTable1.getValueAt(i,3).toString());
                ps2.setString(3, jTable1.getValueAt(i,4).toString());
                ps2.setInt(4, (int)Double.parseDouble(jTable1.getValueAt(i, 5).toString()));
                ps2.setDouble(5, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                ps2.setDouble(6, Double.parseDouble(jTable1.getValueAt(i, 7).toString())); 
                ps2.setInt(7, Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                ps2.setDouble(8, Double.parseDouble(jTable1.getValueAt(i, 9).toString()));
                ps2.executeUpdate();
                
                String sql4="SELECT BATCHNO FROM BATCH WHERE PID=" +pid +";";
                s=conn.createStatement();
                rs=s.executeQuery(sql4);
                int trip=0;
                while(rs.next()){
                    if(rs.getString("BATCHNO").equalsIgnoreCase(jTable1.getValueAt(i,3).toString())){
                        String sql5="UPDATE BATCH SET QTYSTP=QTYSTP+?+?,MRP=?,TRP=?,EXP=? WHERE PID=? AND BATCHNO=?;";
                        ps3=conn.prepareStatement(sql5);
                        ps3.setInt(1, Integer.parseInt(jTable1.getValueAt(i, 5).toString()));
                        ps3.setInt(2, Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                        ps3.setDouble(3, Double.parseDouble(jTable1.getValueAt(i, 7).toString()));
                        ps3.setDouble(4, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                        ps3.setString(5, jTable1.getValueAt(i, 4).toString());
                        ps3.setInt(6, pid);
                        ps3.setString(7, jTable1.getValueAt(i,3).toString());
                        ps3.executeUpdate();
                        trip=1;
                    }
                }
                if(trip==0){
                    String sql5="INSERT INTO BATCH VALUES(?,?,?,0,?,?,?)";
                    ps3=conn.prepareStatement(sql5);
                    ps3.setInt(1,pid);
                    ps3.setString(2,jTable1.getValueAt(i,3).toString());
                    ps3.setInt(3,Integer.parseInt(jTable1.getValueAt(i, 5).toString())+Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                    ps3.setString(4,jTable1.getValueAt(i,4).toString());
                    ps3.setDouble(5, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                    ps3.setDouble(6,Double.parseDouble(jTable1.getValueAt(i, 7).toString()));
                    ps3.executeUpdate();
                }
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
        }
    }
    
    
    
    private void savepurchase(){
        Connection conn=MySqlConnect.ConnectDB();
        Statement s;
        PreparedStatement ps1,ps2,ps3;
        ResultSet rs;
        String sql1="SELECT PURCHASEBILLID FROM DETAILS WHERE ID=1;";
        
        try{
            s=conn.createStatement();
            rs=s.executeQuery(sql1);
            rs.next();
            int id=rs.getInt("PURCHASEBILLID");
            
            double gst=Double.parseDouble(jTextField9.getText())+Double.parseDouble(jTextField10.getText());
            
            String sql2="INSERT INTO PURCHASE VALUES(?,?,?,?,?,?,?,?,?);";
            ps1=conn.prepareStatement(sql2);
            ps1.setString(1,jTextField1.getText());
            ps1.setString(2,jTextField2.getText());
            ps1.setDate(3,new java.sql.Date(jXDatePicker1.getDate().getTime()));
            if(flag==0)
                ps1.setInt(4,id);
            else
                ps1.setInt(4,pbid);
            ps1.setDouble(5, Double.parseDouble(jTextField7.getText()));
            ps1.setDouble(6,gst);
            ps1.setDouble(7,Double.parseDouble(jTextField11.getText()));
            ps1.setDouble(8,Double.parseDouble(jTextField8.getText()));
            if(jComboBox1.getSelectedIndex()==0)
                ps1.setBoolean(9, true);
            else{
                ps1.setBoolean(9, false);
                String sql4="UPDATE AGENCY SET DUE=DUE+" +Double.parseDouble(jTextField11.getText()) +" WHERE ANAME=?;";
                ps3=conn.prepareStatement(sql4);
                ps3.setString(1,jTextField1.getText());
                ps3.executeUpdate();
            }
            ps1.executeUpdate();
            
            
            if(flag==0){
                id++;
                String sql3="UPDATE DETAILS SET PURCHASEBILLID=? WHERE ID=1;";
                ps2=conn.prepareStatement(sql3);
                ps2.setInt(1,id);
                ps2.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void savePurcRetProd(){
        Connection conn=MySqlConnect.ConnectDB();
        Statement s,s2;
        PreparedStatement ps1,ps2,ps3;
        ResultSet rs;
        String sql1="SELECT PURCHASERETURN FROM DETAILS WHERE ID=1;";
        
        try{
            s=conn.createStatement();
            rs=s.executeQuery(sql1);
            rs.next();
            int id=rs.getInt("PURCHASERETURN");
            for(int i=0;i<jTable1.getRowCount();i++){
                String sql3="INSERT INTO PURCRETURNPROD VALUES(?,?,?,?,?,?,?,?,?);";                
                String sql2="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                
                ps1=conn.prepareStatement(sql2);
                ps1.setString(1, jTable1.getValueAt(i,0).toString());
                ps1.setInt(2, (int)Double.parseDouble(jTable1.getValueAt(i,1).toString()));
                rs=ps1.executeQuery();
                rs.next();
                int pid=rs.getInt("PID");
                
                ps2=conn.prepareStatement(sql3);
                if(flag==2)
                    ps2.setInt(9, id);
                if(flag==3)
                    ps2.setInt(9,pbid);
                ps2.setInt(1, pid);
                ps2.setString(2, jTable1.getValueAt(i,3).toString());
                ps2.setString(3, jTable1.getValueAt(i,4).toString());
                ps2.setInt(4, (int)Double.parseDouble(jTable1.getValueAt(i, 5).toString()));
                ps2.setDouble(5, Double.parseDouble(jTable1.getValueAt(i, 8).toString()));
                ps2.setDouble(6, Double.parseDouble(jTable1.getValueAt(i, 7).toString())); 
                ps2.setInt(7, Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                ps2.setDouble(8, Double.parseDouble(jTable1.getValueAt(i, 9).toString()));
                ps2.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e );
        }
    }
    
    
    
    private void savePurchaseRet(){
        Connection conn=MySqlConnect.ConnectDB();
        Statement s;
        PreparedStatement ps1,ps2,ps3;
        ResultSet rs;
        String sql1="SELECT PURCHASERETURN FROM DETAILS WHERE ID=1;";
        
        try{
            s=conn.createStatement();
            rs=s.executeQuery(sql1);
            rs.next();
            int id=rs.getInt("PURCHASERETURN");
            
            double gst=Double.parseDouble(jTextField9.getText())+Double.parseDouble(jTextField10.getText());
            
            String sql2="INSERT INTO PURCHASERETURN VALUES(?,?,?,?,?,?,?,?,?);";
            ps1=conn.prepareStatement(sql2);
            ps1.setString(1,jTextField1.getText());
            ps1.setString(2,jTextField2.getText());
            ps1.setDate(3,new java.sql.Date(jXDatePicker1.getDate().getTime()));
            if(flag==2)
                ps1.setInt(4,id);
            else
                ps1.setInt(4,pbid);
            ps1.setDouble(5, Double.parseDouble(jTextField7.getText()));
            ps1.setDouble(6,gst);
            ps1.setDouble(7,Double.parseDouble(jTextField11.getText()));
            ps1.setDouble(8,Double.parseDouble(jTextField8.getText()));
            if(jComboBox1.getSelectedIndex()==0)
                ps1.setBoolean(9, true);
            else{
                ps1.setBoolean(9, false);
                String sql4="UPDATE AGENCY SET DUE=DUE-" +Double.parseDouble(jTextField11.getText()) +" WHERE ANAME=?;";
                ps3=conn.prepareStatement(sql4);
                ps3.setString(1,jTextField1.getText());
                ps3.executeUpdate();
            }
            ps1.executeUpdate();
            
            
            if(flag==2){
                id++;
                String sql3="UPDATE DETAILS SET PURCHASERETURN=? WHERE ID=1;";
                ps2=conn.prepareStatement(sql3);
                ps2.setInt(1,id);
                ps2.executeUpdate();
            }
        }
        catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jXDatePicker1.setFormats(new String[]{"dd-MM-yyyy"});
        jXDatePicker1.setBackground(new java.awt.Color(255, 255, 255));
        jXDatePicker1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("DATE : ");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("PURCHASE ENTRY");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("SUPPLIER : ");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("INVOICE NO. : ");

        jTextField2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
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

        jComboBox1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(204, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CREDIT" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE))
                            .addComponent(jTextField1))))
                .addGap(190, 190, 190))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT", "PACKING", "HSN", "BATCH NO.", "EXP", "QTY", "FREE", "MRP", "TRP", "DISCOUNT", "SGST", "CGST", "AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("BATCH NO. : ");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("EXP. DATE : ");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("MRP : ");

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField5.setText("00");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel9.setText("/");
        jLabel9.setToolTipText("");
        jLabel9.setFocusable(false);

        jTextField6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField6.setText("00");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("AMOUNT : ");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("DISCOUNT : ");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("SGST : ");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("CGST : ");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("TOTAL : ");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N

        jTextField11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("PRODUCT : ");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12MouseClicked(evt);
            }
        });
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("TRP : ");

        jTextField13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("QTY : ");

        jTextField14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField14.setInheritsPopupMenu(true);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("FREE : ");

        jTextField15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("DISCOUNT (%) : ");

        jTextField16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 0));
        jButton3.setText("MODIFY");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("MARGIN : ");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("SGST : ");

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 0, 0));
        jLabel22.setText("CGST : ");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 0));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 0, 0));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField16)
                                        .addGap(37, 37, 37)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(30, 30, 30)))))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jTextField8)
                    .addComponent(jTextField9)
                    .addComponent(jTextField10)
                    .addComponent(jTextField11))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jButton1)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        if(jTextField2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Enter Valid Invoice No.","Access Denied",JOptionPane.WARNING_MESSAGE);
        }
        else
            jComboBox1.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        if(jTextField3.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Batch No. cannot be Empty","Access Denied",JOptionPane.WARNING_MESSAGE);
        }
        else{
            jTextField5.requestFocus();
            jTextField5.selectAll();
        }
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        SimpleDateFormat dfy=new SimpleDateFormat("yy");
        String y=dfy.format(jXDatePicker1.getDate());
        SimpleDateFormat dfm=new SimpleDateFormat("MM");
        String m=dfm.format(jXDatePicker1.getDate());
        try{
            if(jTextField6.getText().isEmpty() || (jTextField5.getText().equals(m) && jTextField6.getText().equals(y)) || Integer.parseInt(jTextField6.getText())<Integer.parseInt(y)){
                JOptionPane.showMessageDialog(null,"Invalid Year","Access Denied",JOptionPane.WARNING_MESSAGE);
                jTextField6.selectAll();
            }
            else{
                jTextField4.requestFocus();
                jTextField4.selectAll();
            }
        }
        catch(NumberFormatException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Invalid Year","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField6.selectAll();
        }
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        try{
            if(jTextField5.getText().isEmpty() || Integer.parseInt(jTextField5.getText())>12 || Integer.parseInt(jTextField5.getText())<1){
                JOptionPane.showMessageDialog(null,"Invalid Month","Access Denied",JOptionPane.WARNING_MESSAGE);
                jTextField5.selectAll();
            }
            else{
                jTextField6.requestFocus();
                jTextField6.selectAll();
            }
        }
        catch(NumberFormatException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Invalid Month","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField5.selectAll();
        }
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
            jTextField13.requestFocus();
            jTextField13.selectAll();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jTextField12.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField2.requestFocus();
        }
        
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        if(flag==0 || flag==1){
            Purchase_Product_List p=new Purchase_Product_List();
            p.setVisible(true);  
        }
        else{
            new Sale_Product_List('a').setVisible(true);
        }
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        if(flag==0 || flag==1){
            if(Double.parseDouble(jTextField4.getText())<Double.parseDouble(jTextField13.getText())){
                    JOptionPane.showMessageDialog(null,"TRP cannot be Greater than MRP","Access Denied",JOptionPane.WARNING_MESSAGE);
                    jTextField4.requestFocus();
                    jTextField4.selectAll();
            }
            else if(Double.parseDouble(jTextField4.getText())==Double.parseDouble(jTextField13.getText())){
                JOptionPane.showMessageDialog(null,"TRP cannot be Equal to MRP","Access Denied",JOptionPane.WARNING_MESSAGE);
                jTextField4.requestFocus();
                jTextField4.selectAll();
            }
            else if(Double.parseDouble(jTextField13.getText())==0.0){
                JOptionPane.showMessageDialog(null,"TRP cannot be Zero","Access Denied",JOptionPane.WARNING_MESSAGE);
                jTextField13.requestFocus();
                jTextField13.selectAll();
            }
            else{
                jTextField14.requestFocus();
                jTextField14.selectAll();
            }
        }
        else{
            if(Double.parseDouble(jTextField13.getText())==0.0){
                JOptionPane.showMessageDialog(null,"TRP cannot be Zero","Access Denied",JOptionPane.WARNING_MESSAGE);
                jTextField13.requestFocus();
                jTextField13.selectAll();
            }
            else{
                jTextField14.requestFocus();
                jTextField14.selectAll();
            }
        }
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        if(jTextField14.getText().isEmpty())
            jTextField14.setText("0");
        if(Integer.parseInt(jTextField14.getText())==0){
            JOptionPane.showMessageDialog(null,"Quantity cannot be Zero","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField14.requestFocus();
            jTextField14.selectAll();
        }
        else{    
            jTextField15.requestFocus();
            jTextField15.selectAll();
        }
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        if(jTextField15.getText().isEmpty())
            jTextField15.setText("0");
        if(flag==2 || flag==3){
            if(Integer.parseInt(jTextField14.getText())+Integer.parseInt(jTextField15.getText()) > qs){
                JOptionPane.showMessageDialog(null, "Total Quantity cannot be greater than Available Quantity");
                jTextField14.requestFocus();
                jTextField14.selectAll();
            }
            else{
                jTextField16.requestFocus();
                jTextField16.selectAll();
            }
        }
        else{
            jTextField16.requestFocus();
            jTextField16.selectAll();
        }
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            if(!jTextField12.getText().isEmpty()){
                DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{jTextField12.getText(),pkg,hsn,jTextField3.getText(),jTextField5.getText()+"/"+jTextField6.getText(),jTextField14.getText(),jTextField15.getText(),jTextField4.getText(),jTextField13.getText(),jTextField16.getText(),sgst,cgst,Integer.parseInt(jTextField14.getText())*Double.parseDouble(jTextField13.getText())});
        
                getamt();
                getdisc();
                getgst();
                gettamt();
        
                jTextField12.setText("");
                jTextField3.setText("");
                jTextField5.setText("00");
                jTextField6.setText("00");
                jTextField4.setText("");
                jTextField13.setText("");
                jTextField14.setText("");
                jTextField15.setText("");
                jTextField16.setText("");
                jTextField12.requestFocus();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Please Check the Input Data","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField3.requestFocus();
            jTextField3.selectAll();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        try{
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{jTextField12.getText(),pkg,hsn,jTextField3.getText(),jTextField5.getText()+"/"+jTextField6.getText(),jTextField14.getText(),jTextField15.getText(),jTextField4.getText(),jTextField13.getText(),jTextField16.getText(),sgst,cgst,String.valueOf(Integer.parseInt(jTextField14.getText())*Double.parseDouble(jTextField13.getText()))});
        
            getamt();
            getdisc();
            getgst();
            gettamt();
        
            jTextField12.setText("");
            jTextField3.setText("");
            jTextField5.setText("00");
            jTextField6.setText("00");
            jTextField4.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jTextField15.setText("");
            jTextField16.setText("");
            jTextField12.requestFocus();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Please Check the Input Data","Access Denied",JOptionPane.WARNING_MESSAGE);
            jTextField3.requestFocus();
            jTextField3.selectAll();
        }
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();       
        model.removeRow(jTable1.getSelectedRow());
        getamt();
        getdisc();
        getgst();
        gettamt();
        jLabel24.setText("");
        jLabel23.setText("");
        jLabel25.setText("");
        jTextField12.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        Agency_List a=new Agency_List();
        a.setVisible(true);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        int row =jTable1.getSelectedRow();
        StringTokenizer st=new StringTokenizer(jTable1.getValueAt(row,4).toString(),"/");
        
        jTextField12.setText(jTable1.getValueAt(row,0).toString());
        jTextField3.setText(jTable1.getValueAt(row,3).toString());
        jTextField5.setText(st.nextToken());
        jTextField6.setText(st.nextToken());
        jTextField4.setText(jTable1.getValueAt(row,7).toString());
        jTextField13.setText(jTable1.getValueAt(row,8).toString());
        jTextField14.setText(jTable1.getValueAt(row,5).toString());
        jTextField15.setText(jTable1.getValueAt(row,6).toString());
        jTextField16.setText(jTable1.getValueAt(row,9).toString());
        if(flag==0 || flag==1)
            new Purchase_Product_List().setVisible(true);
        else
            new Sale_Product_List('a').setVisible(true);
        
        model.removeRow(jTable1.getSelectedRow());
        getamt();
        getdisc();
        getgst();
        gettamt();
        jLabel24.setText("");
        jLabel23.setText("");
        jLabel25.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
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
        if(evt.getKeyCode() == KeyEvent.VK_END){
            if(jTable1.getRowCount()==0){
                JOptionPane.showMessageDialog(null,"No Data is Saved","",JOptionPane.WARNING_MESSAGE);
                new Start_Page().setVisible(true);
                this.dispose();
            }
            else{
                jTextField11.requestFocus();
                jTextField11.selectAll();
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(jTable1.getRowCount()==0)
                jComboBox1.requestFocus();
            else{
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Exit Without Saving?","EXIT?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION){
                    if(flag==0 || flag==2){
                        Start_Page s =new Start_Page();
                        s.setVisible(true);
                        this.dispose();
                    }
                    if(flag==1){
                        new Purchase_List(0).setVisible(true);
                        this.dispose();
                    }
                    if(flag==3){
                        Connection conn=MySqlConnect.ConnectDB();
                        String sql="INSERT INTO PURCRETURNPROD VALUES(?,?,?,?,?,?,?,?,?);";
                        try{
                            for(int i=0;i<spdarray.size();i++){
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setInt(1,spdarray.get(i).pid);
                                ps.setString(2, spdarray.get(i).batchno);
                                ps.setString(3, spdarray.get(i).exp);
                                ps.setInt(4, spdarray.get(i).qty);
                                ps.setInt(7, spdarray.get(i).fqty);
                                ps.setDouble(5, spdarray.get(i).trp);
                                ps.setDouble(6, spdarray.get(i).mrp);
                                ps.setDouble(8, spdarray.get(i).disc);
                                ps.setInt(9, spdarray.get(i).pbid);
                                ps.executeUpdate();
                            }
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                        new Purchase_List(1).setVisible(true);
                        Purchase_Page.this.dispose();
                    }
                }
                else
                    jTextField12.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        String ObjButtons[] = {"Yes","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure?","SAVE?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION){
            if(flag==1){
                Connection conn=MySqlConnect.ConnectDB();
                try {
                    String sql="DELETE FROM PURCPROD WHERE PURCHASEBILLID=" +pbid +";";
                    String sql2="DELETE FROM PURCHASE WHERE PUCHASEBILLID="+pbid +";";
                    String sql3="UPDATE AGENCY SET DUE=DUE-? WHERE ANAME=?";
                    PreparedStatement ps=conn.prepareStatement(sql3);
                    ps.setDouble(1, due);
                    ps.setString(2, aname);
                    ps.executeUpdate();
                    
                    Statement s=conn.createStatement();
                    s.executeUpdate(sql);
                    s.executeUpdate(sql2);
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if(flag==3){
                Connection conn=MySqlConnect.ConnectDB();
                try {
                    String sql="DELETE FROM PURCRETURNPROD WHERE PURCHASERETURNBILLID=" +pbid +";";
                    String sql2="DELETE FROM PURCHASERETURN WHERE PUCHASERETURNBILLID="+pbid +";";
                    String sql3="UPDATE AGENCY SET DUE=DUE+? WHERE ANAME=?";
                    PreparedStatement ps=conn.prepareStatement(sql3);
                    ps.setDouble(1, due);
                    ps.setString(2, aname);
                    ps.executeUpdate();
                    
                    Statement s=conn.createStatement();
                    s.executeUpdate(sql);
                    s.executeUpdate(sql2);
                } 
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if(flag==0 || flag==1){
                savepurcprod();
                savepurchase();
            }
            else{
                savePurcRetProd();
                savePurchaseRet();
            }
            
            if(flag==0)
                new Purchase_Page(0).setVisible(true);
            if(flag==1)
                new Purchase_List(0).setVisible(true);
            if(flag==2)
                new Purchase_Page(2).setVisible(true);
            if(flag==3)
                new Purchase_List(1).setVisible(true);
            this.dispose();
        }
        else
            jTextField12.requestFocus();
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12MouseClicked
        if(flag==0 || flag==1)    
            new Purchase_Product_List().setVisible(true);
        else
            new Sale_Product_List('a').setVisible(true);
                    
    }//GEN-LAST:event_jTextField12MouseClicked

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            Agency_List a=new Agency_List();
            a.setVisible(true);
            jXDatePicker1.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(flag==0 || flag==1)
                new Purchase_Product_List().setVisible(true);
            else
                new Sale_Product_List('a').setVisible(true);
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(flag==0 || flag==1){
                jTextField3.requestFocus();
                jTextField3.selectAll();
            }
            if(flag==2 || flag==3){
                new Sale_Batch_List(p, 'a').setVisible(true);
            }
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField5.requestFocus();
            jTextField5.selectAll();
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            new Sale_Batch_List(p,'a').setVisible(true);
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField4.requestFocus();
            jTextField4.selectAll();
        }
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField13.requestFocus();
            jTextField13.selectAll();
        }
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField14.requestFocus();
            jTextField14.selectAll();
        }
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            jTextField15.requestFocus();
            jTextField15.selectAll();
        }
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        setMargin();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char c= evt.getKeyChar();
        if(Character.isLetter(c) && !evt.isAltDown())
            evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        char c= evt.getKeyChar();
        if(Character.isLetter(c) && !evt.isAltDown())
            evt.consume();
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
       char c= evt.getKeyChar();
        if((Character.isLetter(c) && !evt.isAltDown()) || evt.getKeyCode()==KeyEvent.VK_PERIOD)
            evt.consume();
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
       if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();       
            model.removeRow(jTable1.getSelectedRow());
            getamt();
            getdisc();
            getgst();
            gettamt();
            jLabel24.setText("");
            jLabel23.setText("");
            jLabel25.setText("");
            jTextField12.requestFocus();
        }
        setMargin();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField12.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            int row =jTable1.getSelectedRow();
            StringTokenizer st=new StringTokenizer(jTable1.getValueAt(row,4).toString(),"/");
        
            jTextField12.setText(jTable1.getValueAt(row,0).toString());
            jTextField3.setText(jTable1.getValueAt(row,3).toString());
            jTextField5.setText(st.nextToken());
            jTextField6.setText(st.nextToken());
            jTextField4.setText(jTable1.getValueAt(row,7).toString());
            jTextField13.setText(jTable1.getValueAt(row,8).toString());
            jTextField14.setText(jTable1.getValueAt(row,5).toString());
            jTextField15.setText(jTable1.getValueAt(row,6).toString());
            jTextField16.setText(jTable1.getValueAt(row,9).toString());
            if(flag==0 || flag==1)
                new Purchase_Product_List().setVisible(true);
            else
                new Sale_Product_List('a').setVisible(true);
            model.removeRow(jTable1.getSelectedRow());
            getamt();
            getdisc();
            getgst();
            gettamt();
            jLabel24.setText("");
            jLabel23.setText("");
            jLabel25.setText("");
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jTextField12.requestFocus();
            gettamt();
        }
    }//GEN-LAST:event_jTextField11KeyPressed

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
            java.util.logging.Logger.getLogger(Purchase_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Purchase_Page(flag).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    public static javax.swing.JTextField jTextField9;
    public static org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables

    private String convertUtilDateToSqlDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
