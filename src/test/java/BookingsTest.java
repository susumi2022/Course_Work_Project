import com.wfc.Bookings;
import com.wfc.Report;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class BookingsTest {
    Bookings booking = new Bookings();
    Report report = new Report();
    @Test
    public void validateInputsPass(){
        String f_Type = "S";
        String day = "SATURDAY";
        String week = "5";
        boolean expected = true;
        boolean actual = booking.validateInputs(f_Type,day,week);
        assertEquals(expected,actual);
    }

    @Test
    public void validateInputsFail(){
        String f_Type = "A";
        String day = "SATURDAY";
        String week = "5";
        boolean expected = false;
        boolean actual = booking.validateInputs(f_Type,day,week);
        assertEquals(expected,actual);
    }

    @Test
    public void validateDatePass(){
        String inputDate = "SUNDAY";
        String type = "Y";
        boolean expected = true;
        boolean actual = booking.validateDate(inputDate,type);
        assertEquals(expected,actual);
    }

    @Test
    public void validateDateFail(){
        String inputDate = "SATURDAY";
        String type = "Z";
        boolean expected = false;
        boolean actual = booking.validateDate(inputDate,type);
        assertEquals(expected,actual);
    }

    @Test
    public void validateBookingDetailsPass(){
        File fileBooking = new File("src/main/java/Spain_BookingDetails.txt");
        boolean expected = true;
        boolean actual = booking.validateBookingDetails(fileBooking);
        assertEquals(expected,actual);
    }

    @Test
    public void getFitnessTypePass(){
        String f_Type = "Y";
        String expected = "YOGA";
        String actual = booking.getFitnessType(f_Type);
        assertEquals(expected,actual);
    }

    @Test
    public void getFitnessTypeFail(){
        String f_Type = "R";
        String expected = "";
        String actual = booking.getFitnessType(f_Type);
        assertEquals(expected,actual);
    }


}
