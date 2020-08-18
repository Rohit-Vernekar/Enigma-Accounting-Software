package enigma.accounting.software;
import java.sql.*;
import javax.swing.*;
public class MySqlConnect {
    Connection conn=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/enigma?autoReconnect=true&useSSL=false","root","quartz007");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enigma?zeroDateTimeBehavior=convertToNull","root","Quartz007#");
            return conn;
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
