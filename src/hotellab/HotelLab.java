package hotellab;

/**
 *
 * @author eennis
 */
public class HotelLab {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        HotelService hs = new HotelService();
        
        //Display initial data
        hs.displayAllHotels(); 
        System.out.println("\n");
        
        //Add note to first hotel record
        hs.updateHotelRecord();
        
        //Display updated data
        hs.displayAllHotels(); 
        System.out.println("\n");
        
        //Add a new record
        hs.insertHotelRecord();
        
        //Display records again
        hs.displayAllHotels(); 
        System.out.println("\n");
        
        //Delete the new record
        hs.deleteHotelRecord();
        
        //Finally, display the data again
        hs.displayAllHotels(); 
        System.out.println("\n");
        
    }
    
}
