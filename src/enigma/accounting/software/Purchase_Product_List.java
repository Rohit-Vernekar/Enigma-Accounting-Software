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
import java.awt.HeadlessException;
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
public class Purchase_Product_List extends javax.swing.JFrame {

    /**
     * Creates new form Product_List
     */
    public Purchase_Product_List() {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jTextField1.setText(Purchase_Page.jTextField12.getText());
        
        findProduct();
        
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
                if( e.getKeyCode() == e.VK_F2){
                    new Product_Page(0).setVisible(true);
                }
                if( e.getKeyCode() == e.VK_F3){
                    new Purchase_Product_List(1).setVisible(true);
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Page.jTextField12.setText("");
                    Purchase_Page.jTextField3.setText("");
                    Purchase_Page.jTextField5.setText("00");
                    Purchase_Page.jTextField6.setText("00");
                    Purchase_Page.jTextField4.setText("");
                    Purchase_Page.jTextField13.setText("");
                    Purchase_Page.jTextField14.setText("");
                    Purchase_Page.jTextField15.setText("");
                    Purchase_Page.jTextField16.setText("");
                    Purchase_Page.jTextField12.requestFocus();
                    Purchase_Product_List.this.dispose();
                }
            }
            public void keyReleased(KeyEvent e) {
                findProduct();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE)){
                    jTextField1.requestFocus();
                    findProduct();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            findProduct();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
            public void keyPressed(KeyEvent e) {  
                if( e.getKeyCode() == e.VK_F2){
                    new Product_Page(0).setVisible(true);
                }
                if( e.getKeyCode() == e.VK_F3){
                    new Purchase_Product_List(1).setVisible(true);
                }
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_DELETE){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    int pk=(int) Double.parseDouble(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString());
                    int pid;
                    Connection conn=MySqlConnect.ConnectDB();
                    try {
                        String sql="SELECT PID FROM PRODUCT WHERE PNAME=? AND PKG=?;";
                        PreparedStatement ps=conn.prepareStatement(sql);
                        ps.setString(1, pd);
                        ps.setInt(2, pk);
                        ResultSet rs=ps.executeQuery();
                        rs.next();
                        pid=rs.getInt("pid");
                        String sql2="SELECT * FROM PURCPROD WHERE PID=" +pid +";";
                        String sql3="SELECT * FROM SALEPROD WHERE PID=" +pid +";";
                        Statement s=conn.createStatement();
                        Statement s2=conn.createStatement();
                        ResultSet rs2=s.executeQuery(sql2);
                        ResultSet rs3=s2.executeQuery(sql3);
                        if(rs2.next() || rs3.next()){
                            JOptionPane.showMessageDialog(null,"Some Data is Associated with this Product");
                        }
                        else{
                            String ObjButtons[] = {"Yes","No"};
                            int PromptResult = JOptionPane.showOptionDialog(null,"Are You Sure?","DELETE?",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                            if(PromptResult==JOptionPane.YES_OPTION){
                                String sql4="DELETE FROM PRODUCT WHERE PID=" +pid +";";
                                Statement s4=conn.createStatement();
                                s4.executeUpdate(sql4);
                                Purchase_Product_List pp=Purchase_Product_List.this; 
                                Purchase_Product_List npp=new Purchase_Product_List();
                                npp.jTextField1.setText(pp.jTextField1.getText());
                                npp.findProduct();
                                npp.setVisible(true);
                                pp.dispose();
                            }
                        }
                    } 
                    catch (SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null,ex);
                    }
                }
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    String pd=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    String pk=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString();
                    
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PNAME,PKG,HSN,MRP,TRP,DISCOUNT FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        st2=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        String hsncode=rs.getString("hsn");
                        String sql2="SELECT CODE,SGST,CGST FROM HSN WHERE HSN='" +hsncode +"';";
                        rs2=st2.executeQuery(sql2);
                        rs2.next();
                        
                        Purchase_Page.jTextField12.setText(rs.getString("pname"));
                        Purchase_Page.jTextField13.setText(String.valueOf(rs.getDouble("trp")));
                        Purchase_Page.jTextField4.setText(String.valueOf(rs.getDouble("mrp")));
                        Purchase_Page.pkg=rs.getString("pkg");
                        Purchase_Page.hsn=rs2.getString("code");
                        Purchase_Page.cgst=String.valueOf(rs2.getDouble("cgst"));
                        Purchase_Page.sgst=String.valueOf(rs2.getDouble("sgst"));
                        Purchase_Page.jTextField16.setText(String.valueOf(rs.getDouble("discount")));
                        Purchase_Page.jTextField3.requestFocus();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Purchase_Product_List.this.dispose(); 
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Page.jTextField12.setText("");
                    Purchase_Page.jTextField3.setText("");
                    Purchase_Page.jTextField5.setText("00");
                    Purchase_Page.jTextField6.setText("00");
                    Purchase_Page.jTextField4.setText("");
                    Purchase_Page.jTextField13.setText("");
                    Purchase_Page.jTextField14.setText("");
                    Purchase_Page.jTextField15.setText("");
                    Purchase_Page.jTextField16.setText("");
                    Purchase_Page.jTextField12.requestFocus();
                }
            }
        });
        
        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
    }
    public Purchase_Product_List(int i){
        initComponents();
        
        jLabel2.setText("SELECT PRODUCT TO BE MODIFIED");
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
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
                if(e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Product_List.this.dispose();
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
                if((!jTextField1.isFocusOwner()) && (e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE)){
                    jTextField1.requestFocus();
                    findProduct();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            findProduct();
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
                    new Product_Page(1).setVisible(true);
                    String pd= Product_Page.pname=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    int pk= Product_Page.pkg=(int)Double.parseDouble(jTable1.getModel().getValueAt(jTable1.getSelectedRow(),1).toString());
                    Product_Page.jTextField1.setText(pd);
                    Product_Page.jTextField1.requestFocus();
                    Product_Page.jTextField1.selectAll();
                    Product_Page.jTextField2.setText(String.valueOf(pk));
                    
                    Connection conn=MySqlConnect.ConnectDB();
                    Statement st,st2;
                    ResultSet rs,rs2;
                    String sql="SELECT PNAME,PKG,HSN,MRP,TRP,DISCOUNT,MFG,SALT,NARCOTIC,SCHH,SCHH1 FROM PRODUCT WHERE PNAME='" +pd +"' AND PKG='" +pk +"';";
                    try{
                        st=conn.createStatement();
                        st2=conn.createStatement();
                        rs=st.executeQuery(sql);
                        rs.next();
                        String hsncode=rs.getString("hsn");
                        String sql2="SELECT CODE,SGST,CGST,GST FROM HSN WHERE HSN='" +hsncode +"';";
                        rs2=st2.executeQuery(sql2);
                        rs2.next();
                        
                        Product_Page.jTextField3.setText(rs.getString("mfg"));
                        Product_Page.jTextField4.setText(rs.getString("salt"));
                        Product_Page.jTextField5.setText(rs2.getString("code"));
                        Product_Page.jTextField6.setText(String.valueOf(rs2.getDouble("sgst")));
                        Product_Page.jTextField7.setText(String.valueOf(rs2.getDouble("cgst")));
                        Product_Page.jTextField10.setText(String.valueOf(rs2.getDouble("gst")));
                        Product_Page.jTextField8.setText(String.valueOf(rs.getDouble("mrp")));
                        Product_Page.jTextField9.setText(String.valueOf(rs.getDouble("trp")));
                        if(rs.getBoolean("narcotic"))
                            Product_Page.jComboBox1.setSelectedIndex(1);
                        else
                            Product_Page.jComboBox1.setSelectedIndex(0);
                        if(rs.getBoolean("schh"))
                            Product_Page.jComboBox2.setSelectedIndex(1);
                        else
                            Product_Page.jComboBox2.setSelectedIndex(0);
                        if(rs.getBoolean("schh1"))
                            Product_Page.jComboBox3.setSelectedIndex(1);
                        else
                            Product_Page.jComboBox3.setSelectedIndex(0);
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }                    
                    Purchase_Product_List.this.dispose(); 
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Product_List.this.dispose();
                }
            }
        });
        
        this.addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
        
        
    }
    private ArrayList<ProductList> Products(String ValToSearch){
        
        ArrayList<ProductList> prod=new ArrayList<ProductList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT PNAME,PKG,QTYSTP,QTYPCS,MRP FROM PRODUCT WHERE PNAME LIKE '" +ValToSearch +"%' ORDER BY PNAME;";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            ProductList pl;
            
            while(rs.next()){
                pl=new ProductList(rs.getString("pname"), String.valueOf(rs.getInt("pkg")), String.valueOf(rs.getInt("qtystp"))+" : "+String.valueOf(rs.getInt("qtypcs")),String.valueOf(rs.getDouble("mrp")));
                prod.add(pl);
            }
        }
        catch(SQLException e){
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
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
                    g2d.drawString("NO PRODUCT FOUND.",10,20);
                }
            }
        };
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("SEARCH : ");

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("F2-ADD PRODUCT,     F3-MODIFY PRODUCT,     DELETE-DELETE PRODUCT");
        jLabel2.setToolTipText("");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PRODUCT LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)))
                .addGap(24, 24, 24))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        findProduct();
    }//GEN-LAST:event_jTextField1ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Purchase_Product_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase_Product_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase_Product_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase_Product_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase_Product_List().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
