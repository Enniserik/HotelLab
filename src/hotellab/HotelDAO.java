/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotellab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eennis
 */
public interface HotelDAO {

    public abstract List<Hotel> findAllHotels() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException;
    public abstract int updateHotelRecord() throws SQLException, ClassNotFoundException;
    
}
