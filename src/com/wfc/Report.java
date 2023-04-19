package com.wfc;

import java.io.*;
import java.util.Scanner;

public class Report {
    Scanner reader = new Scanner(System.in);
    private File spain_FileBooking = new File("src/Spain_BookingDetails.txt");
    private File yoga_FileBooking = new File("src/Yoga_BookingDetails.txt");
    private File zumba_FileBooking = new File("src/Zumba_BookingDetails.txt");
    private File bodySculpt_FileBooking = new File("src/BodySculpt_BookingDetails.txt");
    private File fileRating = new File("src/CustomerRating.txt");
    private File fileBooking = null;
    private int customer_Count = 0;
    private float spainAVG_Rate = 0;
    private float yogaAVG_Rate = 0;
    private float zumbaAVG_Rate = 0;
    private float bodySculptAVG_Rate = 0;
    public void generateMonthlyReport(){
//        System.out.println("WEEK - 01 (01st of April && 02nd of April)");
//        System.out.println("WEEK - 02 (08th of April && 09th of April)");
//        System.out.println("WEEK - 03 (15th of April && 16th of April)");
//        System.out.println("WEEK - 04 (22nd of April && 23rd of April)");
//        System.out.println("WEEK - 05 (29th of April && 30th of April)");
//        System.out.println("WEEK - 06 (06th of May && 07th of May)");
//        System.out.println("WEEK - 07 (13th of May && 14th of May )");
//        System.out.println("WEEK - 08 (20th of May && 21st of May)");

        System.out.println("1 - April");
        System.out.println("2 - May");

        System.out.println("Enter the number of month?");
        String month = reader.nextLine();

        System.out.println("Fitness type : (S-SPAIN / Y-YOGA / Z-ZUMBA / B-BODY SCULPT)");
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

        getCustomerCount(month);
        getAverageRating(f_Type);
        displayReport(month);

        MainMenu m = new MainMenu();
        m.getMainMenus();

    }

    //Get the customer count according to requested lesson
    public void getCustomerCount(String month){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileBooking));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase()) && trimmedLine[6].toUpperCase().equals("ATTENDED")){
                    if(month.equals("1")){
                        if((trimmedLine[2].toUpperCase().equals("WEEK1")) || (trimmedLine[2].toUpperCase().equals("WEEK2")) ||
                                (trimmedLine[2].toUpperCase().equals("WEEK3")) || (trimmedLine[2].toUpperCase().equals("WEEK4")) ||
                                (trimmedLine[2].toUpperCase().equals("WEEK5")))
                            customer_Count++;
                    } else if (month.equals("2")) {
                        if((trimmedLine[2].toUpperCase().equals("WEEK6")) || (trimmedLine[2].toUpperCase().equals("WEEK7")) ||
                                (trimmedLine[2].toUpperCase().equals("WEEK8"))){
                            customer_Count++;
                        }
                    }
                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Get the Average Rating of all lessons
    public void getAverageRating(String f_Type){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileRating));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase()) && trimmedLine[7].toUpperCase().equals("Y")){
                    if((trimmedLine[2].toUpperCase().equals("1")) || (trimmedLine[2].toUpperCase().equals("2")) ||
                            (trimmedLine[2].toUpperCase().equals("3")) || (trimmedLine[2].toUpperCase().equals("4")) ||
                            (trimmedLine[2].toUpperCase().equals("5"))){
                        if(trimmedLine[4].toUpperCase().equals("SPAIN")){
                            spainAVG_Rate = spainAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("YOGA")){
                            yogaAVG_Rate = yogaAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("ZUMBA")){
                            zumbaAVG_Rate = zumbaAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("BODYSCULPT")){
                            bodySculptAVG_Rate = bodySculptAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        }
                    } else if((trimmedLine[2].toUpperCase().equals("6")) || (trimmedLine[2].toUpperCase().equals("7")) ||
                                (trimmedLine[2].toUpperCase().equals("8"))){
                        if(trimmedLine[4].toUpperCase().equals("SPAIN")){
                            spainAVG_Rate = spainAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("YOGA")){
                            yogaAVG_Rate = yogaAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("ZUMBA")){
                            zumbaAVG_Rate = zumbaAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        } else if(trimmedLine[4].toUpperCase().equals("BODYSCULPT")){
                            bodySculptAVG_Rate = bodySculptAVG_Rate + Float.parseFloat(trimmedLine[6]);
                        }
                    }
                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayReport(String month){
        if(month.equals("1")){
            month = "April";
        } else  if(month.equals("2")){
            month = "May";
        }
        System.out.println("Monthly lesson report");
        System.out.println("Report of "+month+"                       Total Customers : "+ customer_Count);
        System.out.println("----------------------------------------------------------");
        System.out.println("SPAIN          YOGA            ZUMBA           BODY-SCULPT");
        System.out.println((spainAVG_Rate/customer_Count) +          (yogaAVG_Rate/customer_Count) +              (zumbaAVG_Rate/customer_Count) +                (bodySculptAVG_Rate/customer_Count));
        System.out.println("----------------------------------------------------------\n");
    }

    public void generateMonthlyChampionFitnessReport(){
        System.out.println("1 - April");
        System.out.println("2 - May");

        System.out.println("Enter the number of month?");
        String month = reader.nextLine();

        spain_MonthlyIncome(month);
        System.out.println("Monthly champion fitness type report");
        System.out.println("Total Income of " + month );
        System.out.println("----------------------------------------------------------");
        System.out.println("SPAIN          YOGA            ZUMBA           BODY-SCULPT");
        System.out.println(spain_MonthlyIncome(month) +          yoga_MonthlyIncome(month) +              zumba_MonthlyIncome(month) +                bodySculpt_MonthlyIncome(month));
        System.out.println("----------------------------------------------------------\n");
    }

    public double spain_MonthlyIncome(String month){
        double spain_MonthlyIncome = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/Spain_BookingDetails.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase())){
                    if((trimmedLine[2].toUpperCase().equals("WEEK1")) || (trimmedLine[2].toUpperCase().equals("WEEK2")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK3")) || (trimmedLine[2].toUpperCase().equals("WEEK4")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK5"))){
                                spain_MonthlyIncome  = spain_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    } else if((trimmedLine[2].toUpperCase().equals("WEEK6")) || (trimmedLine[2].toUpperCase().equals("WEEK7")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK8"))) {
                                spain_MonthlyIncome  = spain_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    }

                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return spain_MonthlyIncome;
    }
    public double yoga_MonthlyIncome(String month){
        double yoga_MonthlyIncome = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/Yoga_BookingDetails.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase())){
                    if((trimmedLine[2].toUpperCase().equals("WEEK1")) || (trimmedLine[2].toUpperCase().equals("WEEK2")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK3")) || (trimmedLine[2].toUpperCase().equals("WEEK4")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK5"))) {
                                yoga_MonthlyIncome = yoga_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    } else if((trimmedLine[2].toUpperCase().equals("WEEK6")) || (trimmedLine[2].toUpperCase().equals("WEEK7")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK8"))) {
                                yoga_MonthlyIncome  = yoga_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    }
                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return yoga_MonthlyIncome;
    }
    public double zumba_MonthlyIncome(String month){
        double zumba_MonthlyIncome = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/Zumba_BookingDetails.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase())){
                    if((trimmedLine[2].toUpperCase().equals("WEEK1")) || (trimmedLine[2].toUpperCase().equals("WEEK2")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK3")) || (trimmedLine[2].toUpperCase().equals("WEEK4")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK5"))) {
                                zumba_MonthlyIncome = zumba_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    } else if((trimmedLine[2].toUpperCase().equals("WEEK6")) || (trimmedLine[2].toUpperCase().equals("WEEK7")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK8"))) {
                                zumba_MonthlyIncome  = zumba_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    }
                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return zumba_MonthlyIncome;
    }
    public double bodySculpt_MonthlyIncome(String month){
        double bodySculpt_MonthlyIncome = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/BodySculpt_BookingDetails.txt"));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] trimmedLine = currentLine.trim().split(",");
                if(!"CANCELLED".equals(trimmedLine[6].toUpperCase())){
                    if((trimmedLine[2].toUpperCase().equals("WEEK1")) || (trimmedLine[2].toUpperCase().equals("WEEK2")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK3")) || (trimmedLine[2].toUpperCase().equals("WEEK4")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK5"))) {
                                bodySculpt_MonthlyIncome = bodySculpt_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    } else if((trimmedLine[2].toUpperCase().equals("WEEK6")) || (trimmedLine[2].toUpperCase().equals("WEEK7")) ||
                            (trimmedLine[2].toUpperCase().equals("WEEK8"))) {
                                bodySculpt_MonthlyIncome  = bodySculpt_MonthlyIncome + Double.parseDouble(trimmedLine[5].substring(1));
                    }
                }
            }
            br.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bodySculpt_MonthlyIncome;
    }
}
