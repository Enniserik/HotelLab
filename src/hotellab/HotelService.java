/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotellab;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eennis
 */
public class HotelService {
    
    private HotelDAO dao;
    
    public HotelService(){
        dao = new HotelDAOStrategy();
    }
    
    public void displayAllHotels(){
        try{
            List<Hotel> hotels = dao.findAllHotels();
            for(Hotel h : hotels){
                System.out.println(h);
            }
        } catch(SQLException sqle){
            System.out.println("Something went wrong with SQL.");
        } catch(ClassNotFoundException cnfe){
            System.out.println("Something went wrong with the Driver.");
        }
        
    }
    
}
