package com.wfc;

import java.util.Scanner;

public class MainMenu {
    Scanner reader = new Scanner(System.in);
    public int getMainMenus(){
        System.out.println("****** The Weekend Fitness Club (WFC) ******");
        System.out.println("1) View Time table");
        System.out.println("2) Booking lessons");
        System.out.println("3) Cancel booking");
        System.out.println("4) Review / Rating");
        System.out.println("5) Monthly report");

        System.out.println("Enter number of the manu.....\n");
        return (reader.nextInt());
    }
}
