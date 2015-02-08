package hotellab;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eennis
 */
public interface DBAccess {

    public abstract void openConnection(String driverName, String url, String userName, String password) throws SQLException, ClassNotFoundException;
    public abstract void closeConnection() throws SQLException;
    
    public abstract List<Map<String, Object>> findAllRecords(String tableName);
    public abstract int deleteRecords();  
    public abstract int insertRecords(); 
    public abstract int updateRecords(String tableName, String pkKey, int pk, String colName, Object value);
    
}
