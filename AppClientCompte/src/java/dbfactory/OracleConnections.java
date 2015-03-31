/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Gaë Oberson
 */
public class OracleConnections {
    private static OracleDataSource ods = null;
    
    private static final String USER = "prof_francillon";
    private static final String PASSWORD = "prof_francillon";
    private static final String HOST = "ne-ege-leto.ig.he-arc.ch";
    private static final String PORT = "1521";
    private static final String SID = "ens2";
    
    static ComboPooledDataSource cpds;
    static{
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass( "oracle.jdbc.OracleDriver" ); //loads the jdbc driver            
        } catch (PropertyVetoException ex) {
            Logger.getLogger(OracleConnections.class.getName()).log(Level.SEVERE, null, ex);
        }
        cpds.setJdbcUrl( "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID);
        cpds.setUser(USER);                                  
        cpds.setPassword(PASSWORD); 
        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);                                     
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setAutoCommitOnClose(false);
    
    }
    
    public static Connection getConnection() throws SQLException{
        Connection c = cpds.getConnection();
        System.out.println(c);
        return c;
    }
}
