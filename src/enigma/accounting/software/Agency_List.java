/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma.accounting.software;


import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell1
 */
public class Agency_List extends javax.swing.JFrame {

    /**
     * Creates new form Agency_List
     */
    public Agency_List() {
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
        
        findAgency();
        
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
                if( e.getKeyCode() == e.VK_F2){
                    new Agency_Page(0).setVisible(true);
                }
                if( e.getKeyCode() == e.VK_F3){
                    new Agency_List(1).setVisible(true);
                }
                if( e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Page.jTextField1.setText("");
                    Purchase_Page.jTextField2.setText("");
                    Agency_List.this.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findAgency();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findAgency();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            findAgency();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == e.VK_F2){
                    new Agency_Page(0).setVisible(true);
                }
                if( e.getKeyCode() == e.VK_F3){
                    new Agency_List(1).setVisible(true);
                }
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_ENTER){
                    Purchase_Page.agency=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    Purchase_Page.jTextField1.setText(Purchase_Page.agency);
                    Purchase_Page.jTextField2.requestFocus();
                    Agency_List.this.dispose(); 
                }
                if( e.getKeyCode() == e.VK_ESCAPE){
                    Purchase_Page.jTextField1.setText("");
                    Purchase_Page.jTextField2.setText("");
                    Agency_List.this.dispose();
                }
            }
        });
    }
    public Agency_List(int i){
        initComponents();
        
        this.dispose();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        jLabel1.setText("SELECT AGENCY TO BE MODIFIED");
        
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Purchase_Page.jTextField1.setText("");
                dispose();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowOpened( WindowEvent e ){
                jTextField1.requestFocus();
            }
        }); 
        
        findAgency();
        
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
            }
            @Override
            public void keyReleased(KeyEvent e) {
                findAgency();
            }
        });
        
        jTable1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!jTextField1.isFocusOwner()) && e.getKeyCode() == e.VK_LEFT || e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_BACK_SPACE){
                    jTextField1.requestFocus();
                    findAgency();
                }
                else{
                    if(e.getKeyCode() != e.VK_UP && e.getKeyCode() != e.VK_DOWN){
                        jTextField1.requestFocus();
                        try{
                            Robot r=new Robot();
                            r.keyPress(e.getKeyCode());
                            r.keyRelease(e.getKeyCode());
                            findAgency();
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
                    new Agency_Page(1).setVisible(true);
                    String a= Agency_Page.aname=jTable1.getModel().getValueAt(jTable1.getSelectedRow(),0).toString();
                    
                    String sql="SELECT * FROM AGENCY WHERE ANAME='" +a +"';";
                    try{
                        Connection conn=MySqlConnect.ConnectDB();
                        Statement s=conn.createStatement();
                        ResultSet rs=s.executeQuery(sql);
                        rs.next();
                        Agency_Page.jTextField1.setText(a);
                        Agency_Page.jTextField2.setText(rs.getString("address"));
                        Agency_Page.jTextField3.setText(rs.getString("address2"));
                        Agency_Page.jTextField4.setText(rs.getString("address3"));
                        Agency_Page.jTextField5.setText(rs.getString("phone"));
                        Agency_Page.jTextField6.setText(rs.getString("phone2"));
                        Agency_Page.jTextField7.setText(rs.getString("gstin"));
                        
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    Agency_List.this.dispose();
                }
                if(e.getKeyCode() == e.VK_ESCAPE){
                    Agency_List.this.dispose();
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
    
    private ArrayList<AgencyList> Agency(String ValToSearch){
        
        ArrayList<AgencyList> agen=new ArrayList<AgencyList>();
        Connection conn=MySqlConnect.ConnectDB();
        Statement st;
        ResultSet rs;
        
        String sql="SELECT DISTINCT ANAME,ADDRESS,DUE FROM AGENCY WHERE ANAME LIKE '" +ValToSearch +"%' ORDER BY ANAME;";
        
        try{
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            AgencyList al;
            
            while(rs.next()){
                al=new AgencyList(rs.getString("aname"), rs.getString("address"), String.valueOf(rs.getDouble("due")));
                agen.add(al);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return agen;
    }
    
    public void findAgency(){
        ArrayList<AgencyList> agen=Agency(jTextField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {"SUPPLIER","LOCATION","DUE"});
        Object row[]=new Object[3];
        for(int i=0;i<agen.size();i++){
            row[0]=agen.get(i).getAgency();
            row[1]=agen.get(i).getLocation();
            row[2]=agen.get(i).getDue();
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
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SUPPLIER", "LOCATION", "DUE AMOUNT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("SEARCH : ");

        jTextField1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("F2-ADD AGENCY,     F3-MODIFY AGENCY");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("AGENCY LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        findAgency();
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
            java.util.logging.Logger.getLogger(Agency_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agency_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agency_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agency_List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Agency_List().setVisible(true);
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
