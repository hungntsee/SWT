/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws NamingException, SQLException {

//        //1 . get context system file
        Context context = new InitialContext();
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2 . get container context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
//        String url ="jdbc:sqlserver://localhost:1433;"
//            + "databasename=Login;user=sa;password=Hung0708";
//        //3 . 
        DataSource ds = (DataSource) tomcatContext.lookup("DSBlink");
        //4 get connection
        Connection con = ds.getConnection();
        return con;
//        Connection con = DriverManager.getConnection(url,"sa","Hung0708");
//        return con;
    }
}
