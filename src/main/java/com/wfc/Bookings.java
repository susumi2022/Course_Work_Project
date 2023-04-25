package com.wfc;

import java.io.*;
import java.util.Scanner;

public class Bookings {
    Scanner reader = new Scanner(System.in);
    private File spain_FileBooking = new File("src/Spain_BookingDetails.txt");
    private File yoga_FileBooking = new File("src/Yoga_BookingDetails.txt");
    private File zumba_FileBooking = new File("src/Zumba_BookingDetails.txt");
    private File bodySculpt_FileBooking = new File("src/BodySculpt_BookingDetails.txt");
    private File fileBooking = null;
    private String bookingNo  = "";
    public String Bookings(String bookingID,String name, String weekNo, String day,String type,String price){
        return bookingID + "," + name.toUpperCase() + "," + weekNo + "," + day.toUpperCase() + "," + type +","+price;
    }

    //Set Booking sDetails
    public void setBookingsDetails() {

        System.out.println("Please enter the booking details correctly....");

        System.out.println("Name :");
        String name = reader.nextLine();

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();
        String price = "";

        if(f_Type.toUpperCase().equals("S")){
            price = "£30";
        } else if(f_Type.toUpperCase().equals("Y")){
            price = "£20";
        } else if(f_Type.toUpperCase().equals("Z")){
            price = "£25";
        } else if(f_Type.toUpperCase().equals("B")){
            price = "£15";
        }

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();

        System.out.println("Week : (Check the time table : Week 01 - 08)");
        String week = reader.nextLine();


        if(!validateInputs(f_Type,day,week)){
            System.out.println("Entered details incorrect.");
            setBookingsDetails();
        } else if (!validateCustomer(name,f_Type,day,week)) {
            System.out.println("Cannot book same lesson twice.");
            setBookingsDetails();
        } else {
            System.out.println("Please confirm the booking (Y/N)");

            if(reader.nextLine().toUpperCase().equals("Y")){

                System.out.println("Name : " + name);
                System.out.println("Booking week : " + week);
                System.out.println("Booking day : " + day);
                System.out.println("Fitness type : " + getFitnessType(f_Type));

                bookingNo = generateBookingNumber(f_Type);

                boolean status = saveBookingsDetails(Bookings(bookingNo,name,"WEEK"+week,day,getFitnessType(f_Type),price),f_Type);

                if (status){
                    System.out.println("Your booking completed successfully. Booking No : "+ bookingNo + "\n");
                    MainMenu m = new MainMenu();
                    m.getMainMenus();
                }
            } else if(reader.nextLine().toUpperCase().equals("N")){
                MainMenu m1 = new MainMenu();
                m1.getMainMenus();
            }
            else {
                System.out.println("Invalid Input.");
                MainMenu m = new MainMenu();
                m.getMainMenus();
            }
        }
    }

    // Get Fitness Type
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

    // Generate Booking Number
    public String generateBookingNumber(String f_Type){

        int bookingId = 0;
        String booking_Prefix= "";

        try{

            if (f_Type.toUpperCase().equals("S")) {
                fileBooking = spain_FileBooking;
                booking_Prefix = "BS_NO";
            }else if (f_Type.toUpperCase().equals("Y")) {
                fileBooking = yoga_FileBooking;
                booking_Prefix = "BY_NO";
            }else if (f_Type.toUpperCase().equals("Z")) {
                fileBooking = zumba_FileBooking;
                booking_Prefix = "BZ_NO";
            }else if (f_Type.toUpperCase().equals("B")) {
                fileBooking = bodySculpt_FileBooking;
                booking_Prefix = "BB_NO";
            }

            Scanner myReader = new Scanner(fileBooking);

            while (myReader.hasNextLine()) {
                String CurrentLine = myReader.nextLine();
                String[] data = CurrentLine.split(",");
                bookingId=Integer.parseInt(data[0].substring(5));
            }
            myReader.close();
            bookingId = (bookingId == 0)? 1 : (bookingId+1);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return booking_Prefix + bookingId;
    }

    // Save Bookings Details
    public boolean saveBookingsDetails(String details,String f_Type) {
        try {
            if (f_Type.toUpperCase().equals("S")) {
                fileBooking = spain_FileBooking;

            }else if (f_Type.toUpperCase().equals("Y")) {
                fileBooking = yoga_FileBooking;

            }else if (f_Type.toUpperCase().equals("Z")) {
                fileBooking = zumba_FileBooking;

            }else if (f_Type.toUpperCase().equals("B")) {
                fileBooking = bodySculpt_FileBooking;

            }

            boolean valid = validateBookingDetails(fileBooking);
            if(valid){
                FileWriter fileWriter = new FileWriter(fileBooking,true);

                fileWriter.append(details + ",BOOKED");
                fileWriter.append("\n");
                fileWriter.close();

                return true;
            }
            else {
                System.out.println("Sorry! you entered lesson fully booked at the moment.\n");
                MainMenu m2 = new MainMenu();
                m2.getMainMenus();
                return false;
            }

        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    // Change Bookings Details
    public void updateBookingsDetails() {
        System.out.println("Please enter your booking number");
        String bookingNo = reader.nextLine();

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();

        System.out.println("Day (Saturday or Sunday) : ");
        String day = reader.nextLine();

        System.out.println("Week : (Check the time table : Week 01 - 08)");
        String week = reader.nextLine();

        String price = "";

        if(!validateInputs(f_Type,day,week)){
            System.out.println("Entered details incorrect.");
            bookingNo = "";
            updateBookingsDetails();
        } else {

            try {
                if (f_Type.toUpperCase().equals("S")) {
                    fileBooking = spain_FileBooking;
                    price = "£30";

                }else if (f_Type.toUpperCase().equals("Y")) {
                    fileBooking = yoga_FileBooking;
                    price = "£20";

                }else if (f_Type.toUpperCase().equals("Z")) {
                    fileBooking = zumba_FileBooking;
                    price = "£25";

                }else if (f_Type.toUpperCase().equals("B")) {
                    fileBooking = bodySculpt_FileBooking;
                    price = "£15";
                }

                BufferedReader br = new BufferedReader(new FileReader(fileBooking));
                File temp = new File("src/text.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                String removeID = bookingNo.toUpperCase();
                String currentLine;
                boolean findRecord = false;
                while ((currentLine = br.readLine()) != null) {
                    String[] trimmedLine = currentLine.trim().split(",");
                    if (trimmedLine[0].toUpperCase().equals(removeID)) {
                        String name = trimmedLine[1];
                        currentLine = (Bookings(bookingNo, name, "WEEK"+week, day, getFitnessType(f_Type),price))+",CHANGED";
                        findRecord = true;
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));

                }
                bw.close();
                br.close();
                boolean delete = fileBooking.delete();
                boolean b = temp.renameTo(fileBooking);

                if (!findRecord) {
                    System.out.println("Invalid booking no.Please enter valid booking no");
                    updateBookingsDetails();
                } else {
                    System.out.println("Booking No : " + bookingNo +" updated successfully.\n");
                    MainMenu m = new MainMenu();
                    m.getMainMenus();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Cancel Bookings Details
    public void cancelBookingsDetails() {
        System.out.println("Do you want to cancel your booking (Y/N)");
        if(reader.nextLine().toUpperCase().equals("Y")) {
            System.out.println("Please enter your booking number");
            String bookingNo = reader.nextLine();

            System.out.println("Please enter your booking lesson (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
            String f_Type = reader.nextLine();

            if (f_Type.toUpperCase().equals("S")) {
                fileBooking = spain_FileBooking;

            }else if (f_Type.toUpperCase().equals("Y")) {
                fileBooking = yoga_FileBooking;

            }else if (f_Type.toUpperCase().equals("Z")) {
                fileBooking = zumba_FileBooking;

            }else if (f_Type.toUpperCase().equals("B")) {
                fileBooking = bodySculpt_FileBooking;
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(fileBooking));
                File temp = new File("src/text.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                String removeID = bookingNo.toUpperCase();
                String currentLine;
                boolean findRecord = false;
                while ((currentLine = br.readLine()) != null) {
                    String[] trimmedLine = currentLine.trim().split(",");
                    if (trimmedLine[0].toUpperCase().equals(removeID)) {
                        String name = trimmedLine[1];
                        currentLine = (Bookings(bookingNo, name, trimmedLine[2], trimmedLine[3],trimmedLine[4],trimmedLine[5]))+",CANCELLED";
                        findRecord = true;
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));

                }
                bw.close();
                br.close();
                boolean delete = fileBooking.delete();
                boolean b = temp.renameTo(fileBooking);

                if (!findRecord) {
                    System.out.println("Invalid booking no.Please enter valid booking no");
                    cancelBookingsDetails();
                } else {
                    System.out.println("Booking No : " + bookingNo +" cancel successfully.\n");
                    MainMenu m = new MainMenu();
                    m.getMainMenus();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!reader.nextLine().toUpperCase().equals("N")){
            System.out.println("Invalid");
            MainMenu m = new MainMenu();
            m.getMainMenus();
        }

    }

    // attend Lesson
    public void attendLesson() {
        System.out.println("Please enter your booking number");
        String bookingNo = reader.nextLine();

        System.out.println("Please enter your booking lesson (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
        String f_Type = reader.nextLine();

        if (f_Type.toUpperCase().equals("S")) {
            fileBooking = spain_FileBooking;

        }else if (f_Type.toUpperCase().equals("Y")) {
            fileBooking = yoga_FileBooking;

        }else if (f_Type.toUpperCase().equals("Z")) {
            fileBooking = zumba_FileBooking;

        }else if (f_Type.toUpperCase().equals("B")) {
            fileBooking = bodySculpt_FileBooking;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileBooking));
            File temp = new File("src/text.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
            String removeID = bookingNo.toUpperCase();
            String currentLine;
            boolean findRecord = false;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if (trimmedLine[0].toUpperCase().equals(removeID)) {
                    String name = trimmedLine[1];
                    currentLine = (Bookings(bookingNo, name, trimmedLine[2], trimmedLine[3], trimmedLine[4],trimmedLine[5]))+",ATTENDED";
                    findRecord = true;
                }
                bw.write(currentLine + System.getProperty("line.separator"));

            }
            bw.close();
            br.close();
            boolean delete = fileBooking.delete();
            boolean b = temp.renameTo(fileBooking);

            if (!findRecord) {
                System.out.println("Invalid booking no.Please enter valid booking no");
                attendLesson();
            } else {
                System.out.println("Booking No : " + bookingNo +" updated successfully.\n");
                MainMenu m = new MainMenu();
                m.getMainMenus();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu m = new MainMenu();
        m.getMainMenus();

    }

    // Validate booking details
    public boolean validateInputs(String f_Type,String day,String week){

        if(!"S".equals(f_Type.toUpperCase())  && !"Y".equals(f_Type.toUpperCase()) &&
                !"Z".equals(f_Type.toUpperCase()) && !"B".equals(f_Type.toUpperCase())) {
            return false;
        }
        else if(Integer.parseInt(week) < 0 || Integer.parseInt(week) > 8) {
            return false;
        }
        else if(!"SATURDAY".equals(day.toUpperCase()) && !"SUNDAY".equals(day.toUpperCase())) {
            return  false;
        }
        else {
            return validateDate(day,f_Type);
        }
    }

    // Validate date
    public boolean validateDate(String inputDate,String type) {
        boolean valid = true;
        switch (inputDate.toUpperCase()) {
            case "SATURDAY":
                if("Z".equals(type.toUpperCase()) || "B".equals(type.toUpperCase())){
                    valid =  false;

                }
                break;
            case "SUNDAY":
                if("Y".equals(type.toUpperCase()) && "S".equals(type.toUpperCase())){
                    valid =  false;

                }
                break;
        }
        return  valid;
    }

    // Validate total customer Count
    public boolean validateBookingDetails(File fileBooking){
        int lines = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileBooking));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase())){
                    lines++;
                }
            }
            br.close();
            if (lines > 4){
                return false;
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // Validate repeat bookings
    public boolean validateCustomer(String name,String f_Type,String day,String week){
        if (f_Type.toUpperCase().equals("S")) {
            fileBooking = spain_FileBooking;

        }else if (f_Type.toUpperCase().equals("Y")) {
            fileBooking = yoga_FileBooking;

        }else if (f_Type.toUpperCase().equals("Z")) {
            fileBooking = zumba_FileBooking;

        }else if (f_Type.toUpperCase().equals("B")) {
            fileBooking = bodySculpt_FileBooking;
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileBooking));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(trimmedLine[1].equals(name) && trimmedLine[2].equals(week) && trimmedLine[3].equals(day)){
                    return false;
                }
            }
            br.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
