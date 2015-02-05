/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotellab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eennis
 */
public class HotelDAOStrategy implements HotelDAO {
    
    private DBAccess dba;
    
    public HotelDAOStrategy(){
        dba = new DBStrategyMySQL();
    }
    
    @Override
    public List<Hotel> findAllHotels() throws SQLException, ClassNotFoundException {
        
        dba.openConnection("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/hotel","root","admin");
        List<Map<String, Object>> records = dba.findAllRecords("hotel");
        List<Hotel> hotels = new ArrayList<>();
        Hotel h = null;
        for(Map m : records){
            h = new Hotel();
            h.setHotelId(Integer.valueOf(m.get("hotel_id").toString()));
            h.setHotelName(m.get("hotel_name").toString());
            h.setAddress(m.get("street_address").toString());
            h.setCity(m.get("city").toString());
            h.setState(m.get("state").toString());
            h.setPostalCode(m.get("postal_code").toString());
            String s = "";
            try{
                s = m.get("notes").toString();
            }catch(NullPointerException npe){
                s = "";
            }
            h.setNotes(s);
            hotels.add(h);
        }
        return hotels;
    }
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        HotelDAO db = new HotelDAOStrategy();
//        List<Hotel> hotels = db.findAllHotels();
//        for(Hotel h : hotels){
//            System.out.println(h);
//        }
//    }
    
}
