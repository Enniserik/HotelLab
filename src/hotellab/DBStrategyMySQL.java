package hotellab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
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
    
    //UPDATE `hotel`.`hotel` SET `city`='Moors' WHERE `hotel_id`='1';
    @Override
    public int updateRecords(String tableName, String pkKey, int pk, String colName, Object value){
        
        int updates = 0;
        PreparedStatement pstmt = null;
        
        try{
            
            Object x = null;
            
            if(value instanceof String){
                x = (String)value;
            }else if(value instanceof Integer){
                x = (Integer)value;
            }else if(value instanceof Double){
                x = (Double)value;
            }else if(value instanceof Long){
                x = (Long)value;
            }else if(value instanceof Timestamp){
                x = (Timestamp)value;
            }else if(value instanceof Date){
                x = (Date)value;
            }else{
                x = (Boolean)value;
            }
            
            String sql = "UPDATE " + tableName + " SET " + colName + " = '" + x + "' WHERE " + pkKey + " = " + pk;
            pstmt = conn.prepareStatement(sql);
            updates = pstmt.executeUpdate();
            
            pstmt.close();
            closeConnection();
            
        }catch(SQLException sqle){
            
        }
        
        return updates;
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
        int x = db.updateRecords("hotel", "hotel_id", 1, "state", "Alabama");
        db.openConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/hotel","root","admin");
        List<Map<String, Object>> hotels = db.findAllRecords("hotel");
        for(Map m : hotels){
            System.out.println(m);
        }
        
    }
    
}
