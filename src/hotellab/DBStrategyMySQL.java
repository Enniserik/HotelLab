package hotellab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eennis
 */
public class DBStrategyMySQL implements DBAccess {
    
    private Connection conn;
    
    @Override
    public void openConnection(String driverName, String url, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }
    
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName){
        
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Map<String, Object> record = null;
        List<Map<String, Object>> records = new ArrayList<>();
        
        try{
            
            String sql = "Select * From " + tableName;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            
            while(rs.next()){
                record = new HashMap<>();
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    record.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                records.add(record);
            }
            
            stmt.close();
            closeConnection();
            
        }catch(SQLException sqle){
            
        }
  
        return records;
    }
    
    @Override
    public int updateRecords(){
        return 0;
    }
    
    @Override
    public int deleteRecords(){
        return 0;
    }
    
    @Override
    public int insertRecords(){
        return 0;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBAccess db = new DBStrategyMySQL();
        db.openConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/hotel","root","admin");
        List<Map<String, Object>> hotels = db.findAllRecords("hotel");
        for(Map m : hotels){
            System.out.println(m);
        }
    }
    
}
