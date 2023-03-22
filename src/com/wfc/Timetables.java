package com.wfc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Timetables {
    Scanner reader = new Scanner(System.in);

    public boolean ViewTimetableMenu(int menuId) {
        System.out.println("1.1) Day wise timetable (1.1)");
        System.out.println("1.2) Type wise timetable (1.2)");

        System.out.println("Which timetable do you want to see?\n");
        double timeTableId = reader.nextDouble();

        if (timeTableId == 1.1) {
            System.out.println("Saturday (Sat) or Sunday (Sun)?\n");
            ViewTimetableByDays(reader.nextLine().toUpperCase());
            return false;
        } else if (timeTableId == 1.2) {
            System.out.println("Select which type - timetable? (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)\n");
            ViewTimetableByTypes(reader.nextLine().toUpperCase());
            return false;
        } else {
            System.out.println("Entered menu number is invalid.Please enter the correct manu number.....\n");
            timeTableId = reader.nextFloat();
            return true;
        }

    }
    public void ViewTimetableByDays(String day){
        try {
            String fileName = "";
            if(day.toUpperCase() == "SATURDAY"){
                fileName = "src/Saturday_Timetable.txt";
            }
            else if(day.toUpperCase() == "SATURDAY"){
                fileName = "src/Sunday_Timetable.txt";
            }
            else {
                System.out.println("Invalid.");
            }
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ViewTimetableByTypes(String type){
        try {
            String fileName = "";
            if(type.toUpperCase() == "S"){
                fileName = "src/Spain_timetable.txt";
            }else if(type.toUpperCase() == "Y"){
                fileName = "src/Yoga_timetable.txt";
            } else if(type.toUpperCase() == "Z"){
                fileName = "src/Zumba_timetable.txt";
            } else if(type.toUpperCase() == "B"){
                fileName = "src/BodySculpt_timetable.txt";
            }
            else {
                System.out.println("Invalid.");
            }

            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
