package com.wfc;

import java.util.Scanner;

public class MainMenu {
    Scanner reader = new Scanner(System.in);

    public void getMainMenus(){
        System.out.println("****** The Weekend Fitness Club (WFC) ******");
        System.out.println("1) View Time table");
        System.out.println("2) Booking lessons");
        System.out.println("3) Update booking");
        System.out.println("4) Cancel booking");
        System.out.println("5) Attend lesson");
        System.out.println("6) Review / Rating");
        System.out.println("7) Monthly lesson report");
        System.out.println("8) Monthly champion fitness type report");

        System.out.println("Enter the menu number here.....\n");
        showEntredMenus(reader.nextInt());
        //return (reader.nextInt());
    }

    public void showEntredMenus(int menuId){
        Bookings booking = new Bookings();
        ReviewAndRating rating = new ReviewAndRating();
        Report report = new Report();

        Timetables timeTable = new Timetables();
        if (menuId == 1){
            timeTable.ViewTimetableMenu(menuId);
        } else  if (menuId == 2){
            booking.setBookingsDetails();
        } else  if (menuId == 3){
            booking.updateBookingsDetails();
        } else  if (menuId == 4){
            booking.cancelBookingsDetails();
        } else  if (menuId == 5){
            booking.attendLesson();
        } else  if (menuId == 6){
            rating.rating();
        } else  if (menuId == 7){
            report.generateMonthlyReport();
        } else  if (menuId == 8){
            report.generateMonthlyChampionFitnessReport();
        }
        else {
            System.out.println("Invalid menu number.");
        }
    }
}
