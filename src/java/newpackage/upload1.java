package newpackage;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.smu.tspell.wordnet.*;

/**
 *
 * @author k
 */
public class upload1 extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String name;
        res.setContentType("text/html");
        name = req.getParameter("name");
         
        HttpSession session = req.getSession();
        String ssnId = session.getId();
        ArrayList previousQueries = (ArrayList) session.getAttribute("previousQueries");
        if (previousQueries == null) {
            previousQueries = new ArrayList();
            session.setAttribute("previousQueries", previousQueries);
        }
        res.setContentType("text/html");
        String query = req.getParameter("name");
       // query = this.rules(query);  // tha efarmozw sunarthsh sto query gia na paragontai protaseis reformulation analoga me ta statistika 
        String IP = req.getRemoteAddr();
        Date date = new Date();
       Timestamp ddate = new Timestamp(date.getTime());
       String connectionURL = "jdbc:mysql://127.0.0.1:3306/newData";// newData is the database  
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "");
            PreparedStatement pst = connection.prepareStatement("insert into mytable(query,IP,ddate,ssnId) values(?,?,?,?)");//try2 is the name of the table  
            pst.setString(1, query);                            //insert into try2(Username,Phone,Email) values(?,?,?)
            pst.setString(2, IP);
            pst.setTimestamp(3, ddate);
            pst.setString(4, ssnId);
            int i = pst.executeUpdate();
            if (i != 0) {
                // Record has been inserted 
 
            } else {
                // failed to insert the data 
            }
        } //            if (connection != null) {
        //                connection.close();
        //            }
        catch (Exception e) {
            //pw.println(e);
        }
        String url2 = "http://www.bing.com/search?q=";
        String url1 = URLEncoder.encode(query, "UTF-8");
        String url4 = url2 + url1;
        

                String str1 = "wordnet.database.dir";
                String str2;
                str2 = "C:\\Program Files\\WordNet\\2.1\\dict";
                System.setProperty(str1, str2);

                String wordForm = query;
                 ArrayList Syn=new ArrayList();
                //  Get the synsets containing the wrod form
             
                WordNetDatabase database = WordNetDatabase.getFileInstance();
                Synset[] synsets = database.getSynsets(wordForm);
                //  Display the word forms and definitions for synsets retrieved
                if (synsets.length > 0) {
                   
                    for (int l = 0; l < synsets.length; l++) {
                                            
                        String[] wordForms = synsets[l].getWordForms();
                        for (int j = 0; j < wordForms.length; j++) {
                            
                            if (wordForms[j] == null ? wordForm == null : wordForms[j].equals(wordForm))
                                               ; 
                            else {
                                Syn.add(wordForms[j]);
                               
                            }
                        }
                       
                    }
                } else {
                    System.err.println("No synsets exist that contain "
                            + "the word form '" + wordForm + "'");
                }
                synchronized (previousQueries) {
                    if (query != null) {
                        previousQueries.add(query);
                    }
      
        req.setAttribute("previousQueries",previousQueries);  
        req.setAttribute("Syn",Syn);  // einai oi protaseis 
        req.setAttribute("name2", name);
        req.setAttribute("url", url4);
        req.getRequestDispatcher("/response.jsp").forward(req, res);
        
    }
    }
    
}

 
