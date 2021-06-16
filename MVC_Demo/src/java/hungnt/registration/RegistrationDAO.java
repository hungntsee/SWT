/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.registration;

import hungnt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect Db
            con = DBHelper.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String 
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                //3. Create Statement to set SQL  
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    return true;
                }
            }//end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();;
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect Db
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement String 
                String sql = "Select username,password,lastname,isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Create Statement to set SQL  
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //create DTO 
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    //add to accounts list;
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }//end rs has more record
            }//end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();;
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect Db
            con = DBHelper.makeConnection();

            if (con != null) {
                //2. Create SQL Statement String 
                String sql = "Delete From Registration "
                        + "where username = ?";
                //3. Create Statement to set SQL  
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. Execute Query
                int row = stm.executeUpdate();
                //5. Process
                if (row > 0) {
                    return true;
                }
            }//end if connection is existed
        } finally {

            if (stm != null) {
                stm.close();;
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
