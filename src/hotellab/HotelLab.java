package hotellab;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author eennis
 */
public class HotelLab {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        
        HotelService hs = new HotelService();
        
        hs.displayAllHotels();
        
        hs.updateHotelRecord();
        
        hs.displayAllHotels();
        
        
        
    }
    
}
