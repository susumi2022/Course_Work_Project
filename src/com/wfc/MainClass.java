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

            if (menuId == 1){
                running = timeTable.ViewTimetableMenu(menuId);

                System.out.println("Do you want to go back ? (Y/N)");
                String a = reader.nextLine();
                if(a.equals("Y")){
                    System.out.println(a);
                    menuId = mainMenu.getMainMenus();
                } else if (a.equals("N")) {
                    System.out.println("Please enter the booking details correctly....");
                    System.out.println("Date : (DD/MM)");
                    System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
                    System.out.println("Lesson time : ");
                }


            }

        }

    }
}
