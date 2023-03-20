package com.wfc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Timetables {
    Scanner reader = new Scanner(System.in);

    public boolean ViewTimetableMenu(int menuId) {
        System.out.println("1.1) Day wise timetable");
        System.out.println("1.2) Type wise timetable");

        System.out.println("Which timetable do you want to see?\n");
        double timeTableId = reader.nextDouble();

        if (timeTableId == 1.1) {
            ViewTimetableByDays();
            return false;
            //running = false;
        } else if (timeTableId == 1.2) {
            ViewTimetableByTypes();
           // running = false;
            return false;
        } else {
            System.out.println("Entered menu number is invalid.Please enter the correct manu number.....\n");
            timeTableId = reader.nextFloat();
            return true;
        }

    }
    public void ViewTimetableByDays(){
        try {
            File myObj = new File("src/Timetable_Day_wise.txt");
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

    public void ViewTimetableByTypes(){
        try {
            File myObj = new File("src/Spain_timetable.txt");
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
