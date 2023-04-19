package com.wfc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Timetables {
    Scanner reader = new Scanner(System.in);

    Bookings booking = new Bookings();
    public void ViewTimetableMenu(int menuId) {
        System.out.println("1.1) Day wise timetable (1.1)");
        System.out.println("1.2) Type wise timetable (1.2)");

        System.out.println("Which timetable do you want to see?\n");
        double timeTableId = reader.nextDouble();
        String timeTableType = "";

        if (timeTableId == 1.1) {
            System.out.println("Saturday (Sat) or Sunday (Sun)?\n");
            timeTableType = reader.nextLine().toUpperCase();
            if(timeTableType !=null || timeTableType != ""){
                ViewTimetableByDays(reader.nextLine().toUpperCase());
            } else {
                System.out.println("Invalid selection\n");
            }
            //return false;
        } else if (timeTableId == 1.2) {
            System.out.println("Select which type - timetable? (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)\n");
            timeTableType = reader.nextLine().toUpperCase();
            if(timeTableType !=null || timeTableType != ""){
                ViewTimetableByTypes(reader.nextLine().toUpperCase());
            } else {
                System.out.println("Invalid selection\n");
            }
            //return false;
        } else {
            System.out.println("Entered menu number is invalid.\n");
            MainMenu m = new MainMenu();
            m.getMainMenus();
        }

    }
    public void ViewTimetableByDays(String day){
        try {
            //System.out.println(day.toUpperCase());
            String fileName = "";
            if(day.toUpperCase().equals("SAT")){
                fileName = "src/Saturday_Timetable.txt";
            }
            else if(day.toUpperCase().equals("SUN")){
                fileName = "src/Sunday_Timetable.txt";
            }
            else {
                System.out.println("Invalid");
                MainMenu m = new MainMenu();
                m.getMainMenus();
            }

            if(fileName != ""){
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();

                System.out.println("Would you like to booking your lesson (Y/N)?");
                String result = reader.nextLine();
                if(result.toUpperCase().equals("Y")){
                    booking.setBookingsDetails();
                } else if (result.toUpperCase().equals("N")) {
                    MainMenu mainMenu1 = new MainMenu();
                    mainMenu1.getMainMenus();
                } else {
                    System.out.println("Invalid.");
                    MainMenu m = new MainMenu();
                    m.getMainMenus();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ViewTimetableByTypes(String type){
        try {
            String fileName = "";
            if(type.toUpperCase().equals("S")){
                fileName = "src/Spain_timetable.txt";
            }else if(type.toUpperCase().equals("Y")){
                fileName = "src/Yoga_timetable.txt";
            } else if(type.toUpperCase().equals("Z")){
                fileName = "src/Zumba_timetable.txt";
            } else if(type.toUpperCase().equals("B")){
                fileName = "src/BodySculpt_timetable.txt";
            }
            else {
                System.out.println("Invalid.");
                MainMenu m = new MainMenu();
                m.getMainMenus();
            }

            if(fileName != "") {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();

                System.out.println("Would you like to booking your lesson (Y/N)?");
                String responce = reader.nextLine();
                if(responce.toUpperCase().equals("Y")){
                    booking.setBookingsDetails();
                } else if (responce.toUpperCase().equals("N")) {
                    MainMenu mainMenu1 = new MainMenu();
                    mainMenu1.getMainMenus();
                } else {
                    System.out.println("Invalid.");
                    MainMenu m = new MainMenu();
                    m.getMainMenus();
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
