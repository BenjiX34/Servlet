/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author pedago
 */
public class States extends DAO{
    
    public States(DataSource dataSource) {
        super(dataSource);
    }
    
    public  List<String> listOfStates() throws SQLException, DAOException{
        List<String> allStates = new ArrayList<>();
        
        String sql = "SELECT DISTINCT State FROM Customer";
        try(Connection connection = myDataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                allStates.add(rs.getString("State"));
            }
         
        return allStates; 
           
        }catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }
    }
   
}
