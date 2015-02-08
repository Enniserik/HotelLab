/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotellab;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private String driver;
    private String url;
    private String username;
    private String password;
    
    public HotelDAOStrategy(){
        dba = new DBStrategyMySQL();
        driver = HotelDataAccessFactory.getDriver();
        url = HotelDataAccessFactory.getUrl();
        username = HotelDataAccessFactory.getUsername();
        password = HotelDataAccessFactory.getPassword();
    }
    
    @Override
    public List<Hotel> findAllHotels() throws SQLException, ClassNotFoundException{
        
        dba.openConnection(driver, url, username, password);
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
    
    @Override
    public int updateHotelRecord() throws SQLException, ClassNotFoundException {
        
        dba.openConnection(driver, url, username, password);
        int updates = dba.updateRecords("hotel", "hotel_id", 1, "state", "Alabama");
    
        return updates;
        
    }
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        HotelDAO db = new HotelDAOStrategy();
//        List<Hotel> hotels = db.findAllHotels();
//        for(Hotel h : hotels){
//            System.out.println(h);
//        }
//    }
    
}
