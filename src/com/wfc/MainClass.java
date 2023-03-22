package com.wfc;

import java.util.Locale;
import java.util.Scanner;

public class MainClass {
    public static void main (String[] args){

        boolean running = true;
        while (running){
            Scanner reader = new Scanner(System.in);

            Bookings booking = new Bookings();
            CancelBookings cancelBooking = new CancelBookings();
            PreRegisteredCustomers customer = new PreRegisteredCustomers();
            Timetables timeTable = new Timetables();
            MainMenu mainMenu = new MainMenu();

            int menuId = mainMenu.getMainMenus();

//            if (menuId == 1) {
//                booking.setBookingsDetails();
//            } else if (menuId == 2){
//                booking.updateBookingsDetails();
//            } else if (menuId == 3){
//                booking.cancelBookingsDetails();
//            }
//
            if (menuId == 1){
                running =  timeTable.ViewTimetableMenu(menuId);

                System.out.println("Do you want to go back ? (Y/N)");
                if((reader.nextLine()).toUpperCase().equals("Y")){
                    menuId = mainMenu.getMainMenus();
                } else if ((reader.nextLine()).toUpperCase().equals("N")) {
                    booking.setBookingsDetails();
                }
            } else  if (menuId == 2){
                cancelBooking.cancelBooing();
            } else  if (menuId == 3){
                cancelBooking.cancelBooing();
            } else  if (menuId == 4){
                cancelBooking.cancelBooing();
            } else  if (menuId == 5){
                cancelBooking.cancelBooing();
            } else {
                System.out.println("Invalid menu.");
            }

        }

    }
}
