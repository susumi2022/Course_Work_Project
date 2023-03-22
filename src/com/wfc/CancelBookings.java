package com.wfc;

import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CancelBookings {
    public void cancelBooing(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Do you want to cancel your booking (Y/N)");

        if(reader.nextLine().toUpperCase().equals("Y")) {
            System.out.println("Please enter your booking number");
            String bookingNo = reader.nextLine();
            try{
                BufferedReader br = new BufferedReader(new FileReader("src/BookingDetails.txt"));
                File f = new File("src/BookingDetails.txt");
                File temp = new File("src/text.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                String removeID = bookingNo;
                String currentLine;
                while((currentLine = br.readLine()) != null){
                    String[] trimmedLine = currentLine.trim().split(",");
                    if(trimmedLine[0].equals(removeID)){
                        currentLine = "";
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));

                }
                bw.close();
                br.close();
                boolean delete = f.delete();
                boolean b = temp.renameTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }


//            try {
//                List<String> allLines = Files.readAllLines(Paths.get("src/BookingDetails.txt"));
//                if (allLines != null ){
//                    for (String line : allLines) {
//                        String[] tokens = line.split(",");
//                        System.out.println(tokens[0]);
//                    }
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        }

    }
}
