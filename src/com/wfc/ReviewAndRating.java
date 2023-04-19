package com.wfc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReviewAndRating {
    Scanner reader = new Scanner(System.in);
    Bookings booking = new Bookings();
    File fileRating = new File("src/CustomerRating.txt");

    public String ReviewAndRating(String bookingID,String name, String weekNo, String day,String type,String attendance,String rating, String feedBack){
        return bookingID + "," + name + "," + weekNo + "," + day + "," + type + "," + attendance + "," + rating + "," + feedBack;
    }
    public void rating(){
        System.out.println("Please enter your booking number");
        String bookingNo = reader.nextLine();

        System.out.println("Name : ");
        String name = reader.nextLine();

        System.out.println("Week : ");
        String week = reader.nextLine();

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();

        System.out.println("Did you attend the lesson? (Y/N)");
        String attendance = reader.nextLine().toUpperCase();

        if(attendance.toUpperCase().equals("N")){
            System.out.println("You cannot rate or provide feedback without attend");
        } else if (attendance.toUpperCase().equals("Y")) {
            System.out.println("Please enter rating (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied) : ");
            String rating = reader.nextLine();

            System.out.println("Please enter feedback :");
            String feedBack = reader.nextLine();

            boolean status = saveCustomerRatingDetails(ReviewAndRating(bookingNo,name,week,day,booking.getFitnessType(f_Type),attendance,rating,feedBack));

            if (status){
                System.out.println("Rating/Feedback save successfully.\n");
            }
        } else {
            System.out.println("Invalid\n");
            MainMenu m = new MainMenu();
            m.getMainMenus();
        }

    }
    public boolean saveCustomerRatingDetails(String details) {
        try {
            FileWriter fileWriter = new FileWriter(fileRating,true);

            fileWriter.append(details);
            fileWriter.append("\n");
            fileWriter.close();

            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}
