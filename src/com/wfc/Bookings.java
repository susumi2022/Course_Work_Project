package com.wfc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Bookings {
    Scanner reader = new Scanner(System.in);
    File fileBooking = new File("src/BookingDetails.txt");
    File fileRating = new File("src/CustomerRating.txt");
    public String Bookings(String bookingID,String name, String weekNo, String day,String type){
        return bookingID + "," + name + ",Week" + weekNo + "," + day + "," + type;
    }

    public String Bookings(String bookingID,String name, String weekNo, String day,String type,String attendance,String rating, String feedBack){
        return bookingID + "," + name + ",Week" + weekNo + "," + day + "," + type + "," + attendance + "," + rating + "," + feedBack;
    }
    public void setBookingsDetails() {

        System.out.println("Please enter the booking details correctly....");

        System.out.println("Name :");
        String name = reader.nextLine();

        System.out.println("Week : ");
        String week = reader.nextLine();
        if(!validateInputs(week,"WEEK")){
            System.out.println("Invalid week.");
            week = "";
        };

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();
        if(!validateInputs(day,"DAY")){
            System.out.println("Invalid day.");
            day = "";
        };

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();
        if(!validateInputs(f_Type,"TYPE")){
            System.out.println("Invalid fitness type.");
            f_Type="";
        };

        System.out.println("Please confirm the booking details (Y/N)");

        if(reader.nextLine().toUpperCase().equals("Y")){

            //System.out.println("Booking number : " + date);
            System.out.println("Name : " + name);
            System.out.println("Booking week : " + week);
            System.out.println("Booking day : " + day);
            System.out.println("Fitness type : " + getFitnessType(f_Type));

        }

        boolean status = saveBookingsDetails(Bookings("B_NO"+generateBookingNumber(),name,week,day,getFitnessType(f_Type)));

        if (status){
            System.out.println("Your booking completed successfully.");
        }

    }
    public String getFitnessType(String f_Type){
        String selectedType = "";
        switch (f_Type.toUpperCase()){
            case "S" :
                selectedType =  FitnessTypes.SPAIN.name();
                break;
            case "Y" :
                selectedType =  FitnessTypes.YOGA.name();
                break;
            case "Z" :
                selectedType =  FitnessTypes.ZUMBA.name();
                break;
            case "B" :
                selectedType =  FitnessTypes.BODYSCULPT.name();
                break;
        }

        return  selectedType;
    }
    public int generateBookingNumber(){

        int bookingId = 0;

        try{
            Scanner myReader = new Scanner(fileBooking);

            while (myReader.hasNextLine()) {
                String CurrentLine = myReader.nextLine();
                String[] data = CurrentLine.split(",");
                bookingId=Integer.parseInt(data[0].substring(4));
            }
            myReader.close();
            bookingId = (bookingId == 0)? 1 : (bookingId+1);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return bookingId;
    }
    public boolean saveBookingsDetails(String details) {
        try {
            FileWriter fileWriter = new FileWriter(fileBooking,true);

            //String details1  = generateBookingNumber()+",Jackson,01-02-2023,SPAIN,Lesson group 1";
            fileWriter.append(details);
            fileWriter.append("\n");
            fileWriter.close();

            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
    public void updateBookingsDetails() {
        System.out.println("Please enter your booking number");
        String bookingNo = reader.nextLine();

        System.out.println("Week : ");
        String week = reader.nextLine();
        if(!validateInputs(week,"WEEK")){
            System.out.println("Invalid week.");
            week = "";
        };

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();
        if(!validateInputs(day,"DAY")){
            System.out.println("Invalid day.");
            day = "";
        };

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();
        if(!validateInputs(f_Type,"TYPE")){
            System.out.println("Invalid fitness type.");
            f_Type="";
        };

        try{
            BufferedReader br = new BufferedReader(new FileReader("src/BookingDetails.txt"));
            File temp = new File("src/text.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            String removeID = bookingNo.toUpperCase();
            String currentLine;
            boolean findRecord = false;
            while((currentLine = br.readLine()) != null){
                String[] trimmedLine = currentLine.trim().split(",");
                if(trimmedLine[0].toUpperCase().equals(removeID)){
                    String name = trimmedLine[1];
                    currentLine = Bookings(bookingNo,name,week,day,getFitnessType(f_Type));
                    findRecord = true;
                }
                bw.write(currentLine + System.getProperty("line.separator"));

            }
            bw.close();
            br.close();
            boolean delete = fileBooking.delete();
            boolean b = temp.renameTo(fileBooking);

            if(!findRecord){
                System.out.println("Invalid booking no.Please enter valid booking no");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cancelBookingsDetails() {
        System.out.println("Do you want to cancel your booking (Y/N)");
        if(reader.nextLine().toUpperCase().equals("Y")) {
            System.out.println("Please enter your booking number");
            String bookingNo = reader.nextLine();

            try{
                BufferedReader br = new BufferedReader(new FileReader("src/BookingDetails.txt"));
                File temp = new File("src/text.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                String removeID = bookingNo.toUpperCase();
                String currentLine;
                boolean findRecord = false;
                while((currentLine = br.readLine()) != null){
                    String[] trimmedLine = currentLine.trim().split(",");
                    if(trimmedLine[0].toUpperCase().equals(removeID)){
                        currentLine = "";
                        findRecord = true;
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));

                }
                bw.close();
                br.close();
                boolean delete = fileBooking.delete();
                boolean b = temp.renameTo(fileBooking);

                if(!findRecord){
                    System.out.println("Invalid booking no.Please enter valid booking no");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!reader.nextLine().toUpperCase().equals("N")){
            System.out.println("Invalid");
        }
    }
    public boolean validateInputs(String inputValue,String ValidateType){
        boolean valid = true;
        switch (ValidateType.toUpperCase()){
            case "WEEK" :
                if(Integer.parseInt(inputValue) < 0 || Integer.parseInt(inputValue) > 0) {
                    valid =  false;
                }
                break;
            case "DAY" :
                if(inputValue.toUpperCase() != "SATURDAY" || inputValue.toUpperCase() != "SUNDAY") {
                    valid =  false;
                }
                break;
            case "TYPE" :
                if(inputValue.toUpperCase() != "S" || inputValue.toUpperCase() != "Y" ||
                        inputValue.toUpperCase() != "Z" || inputValue.toUpperCase() != "B") {
                    valid =  false;
                }
                break;
            case "RATING" :
            if(inputValue != "1" || inputValue != "2" || inputValue != "3" || inputValue != "4" || inputValue != "5") {
                valid =  false;
            }
            break;
        }
        return  valid;
    }
    public void rating(){
        System.out.println("Please enter your booking number");
        String bookingNo = reader.nextLine();

        System.out.println("Name : ");
        String name = reader.nextLine();

        System.out.println("Week : ");
        String week = reader.nextLine();
        if(!validateInputs(week,"WEEK")){
            System.out.println("Invalid week.");
            week = "";
        };

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();
        if(!validateInputs(day,"DAY")){
            System.out.println("Invalid day.");
            day = "";
        };

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();
        if(!validateInputs(f_Type,"TYPE")){
            System.out.println("Invalid fitness type.");
            f_Type="";
        };

        System.out.println("Did you attend the lesson? (Y/N)");
        String attendance = reader.nextLine().toUpperCase();

        if(attendance.toUpperCase() == "N"){
            System.out.println("You cannot rate or provide feedback");
        } else if (attendance.toUpperCase() == "Y") {
            System.out.println("Please enter rating (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied) : ");
            String rating = reader.nextLine();
            if(!validateInputs(rating,"RATING")){
                System.out.println("Invalid rating.");
                rating = "";
            };

            System.out.println("Please enter feedback :");
            String feedBack = reader.nextLine();

            boolean status = saveCustomerRatingDetails(Bookings(bookingNo,name,week,day,getFitnessType(f_Type),attendance,rating,feedBack));

            if (status){
                System.out.println("Rating/Feedback save successfully.");
            }
        } else {
            System.out.println("Invalid");
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
