/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author k
 */
class ScheduledTask2 implements Runnable {

    @Override
    public void run() {
         
		//now = new Date(); // initialize date
		//System.out.println("Time is :" + now); // Display current time
                
                 String connectionURL = "jdbc:mysql://127.0.0.1:3306/newData";// newData is the database  
        Connection connection2;
        try {

           String query = "sche";
           String IP = "sche";
           String ssnId = "sche";
           Date date = new Date();
        // Date ddate = new Date(date.getTime());
        Timestamp ddate = new Timestamp(date.getTime());
        // ELEGXOS GIA CLICK STO FRAME KAI META INPUT STH 

            Class.forName("com.mysql.jdbc.Driver");
            connection2 = DriverManager.getConnection(connectionURL, "root", "");
            PreparedStatement pst = connection2.prepareStatement("insert into mytable(query,IP,ddate,ssnId) values(?,?,?,?)");//try2 is the name of the table  
            pst.setString(1, query);                            //insert into try2(Username,Phone,Email) values(?,?,?)
            pst.setString(2, IP);
            // pst.setDate(3, (java.sql.Date) ddate);
            pst.setTimestamp(3, ddate);
            pst.setString(4, ssnId);

            int i = pst.executeUpdate();
            if (i != 0) {
               // pw.println("<br>Record has been inserted");
//create desktop object 
            } else {
                //pw.println("failed to insert the data");
            }
        } //            if (connection != null) {
        //                connection.close();
        //            }
        catch (Exception e) {
            //pw.println(e);

        }
                
                
                
	}
    }

   

    
