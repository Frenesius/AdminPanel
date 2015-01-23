package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import price.Price;

public class MySqlManager {
	 String DATABASE_URL = "jdbc:mysql://localhost:3306/";  
     String DATABASE_USER = "adminpanel";  
     String DATABASE_PRICE = "hardwareprice";
     String DATABASE_USERNAME = "user1";  
     String DATABASE_PASSWORD = "user1"; 
     
     public boolean validateLogin(String name, String pass) {          
         boolean status = false;  
         Connection conn = null;  
         PreparedStatement pst = null;  
         ResultSet rs = null;  
   

         try {  
             Class.forName("com.mysql.jdbc.Driver");  
             conn = DriverManager.getConnection(DATABASE_URL + DATABASE_USER, DATABASE_USERNAME, DATABASE_PASSWORD);  
   
             pst = conn.prepareStatement("select * from user where username=? and password=?");  
             pst.setString(1, name);  
             pst.setString(2, pass);  
   
             rs = pst.executeQuery();  
             status = rs.next();  
   
         } catch (Exception e) {  
             System.out.println(e);  
         } finally {  
             if (conn != null) {  
                 try {  
                     conn.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
             if (pst != null) {  
                 try {  
                     pst.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
             if (rs != null) {  
                 try {  
                     rs.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
         }  
         return status;  
     } 
     public ArrayList<Price> getPricesByEan(String ean){
         boolean status = false;  
         Connection conn = null;  
         PreparedStatement pst = null;  
         ResultSet rs = null;  
         ArrayList<Price> priceArr = new ArrayList<Price>();

 
         try {  
             Class.forName("com.mysql.jdbc.Driver");  
             conn = DriverManager.getConnection(DATABASE_URL + DATABASE_PRICE, DATABASE_USERNAME, DATABASE_PASSWORD);  
   
             pst = conn.prepareStatement("select * from hardwareprice where ean=?");  
             pst.setString(1, ean);
             	
             rs = pst.executeQuery();  
             while(rs.next()){
            	 Price p = new Price();
            	 p.setEan(rs.getString("ean"));
            	 p.setLinkshop(rs.getString("linkshop"));
            	 p.setPriceex(rs.getString("priceex"));
            	 p.setPriceinc(rs.getString("priceinc"));
            	 p.setShopname(rs.getString("shopname"));
            	 p.setTimestamp(rs.getString("timestamp"));
            	 priceArr.add(p);
             }
   
         } catch (Exception e) {  
             System.out.println(e);  
         } finally {  
             if (conn != null) {  
                 try {  
                     conn.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
             if (pst != null) {  
                 try {  
                     pst.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
             if (rs != null) {  
                 try {  
                     rs.close();  
                 } catch (SQLException e) {  
                     e.printStackTrace();  
                 }  
             }  
         }  
         return priceArr; 
     }
}
