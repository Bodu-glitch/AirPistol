/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.me.airpistol;

import com.me.airpistol.athletic.Athlete;
import com.me.airpistol.athletic.AthleteBuilder;
import com.me.airpistol.athletic.AthleteManager;
import com.me.airpistol.athletic.LatestRecord;

import java.util.Scanner;

/**
 * @author PC
 */
public class Airpistol {
    static Scanner input = new Scanner(System.in);
    static AthleteManager AthleteManager = new AthleteManager();

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("---STUDENT MANAGEMENT SYSTEM---");
            System.out.println(
                    " 1. Add new athlete\n"
                            + " 2. Delete athlete by id\n"
                            + " 3. Print all athletes\n"
                            + " 4. Add new relay\n"
                            + " 5. Add athlete to relay\n"
                            + " 6. Remove relay\n"
                            + " 7. Remove athlete from relay\n"
                            + " 8. Print group of athletes in relay\n"
                            + "9. Start Relays\n"
                            + " 0. Exit");
            System.out.print("Enter your choice (0-21): ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addNewAthlete();
                    break;
                case 2:
                    deleteAthlete();
                    break;
                case 3:
                    printAllAthletes();
                    break;
//                case 4:
//                    menu4();
//                    break;
                case 5:
//                    menu5();
                    break;

                case 0:
//                    menu0();
                    return;
            }

        } while (choice != 10);

        do {
            System.out.println("---RELAY MANAGEMENT SYSTEM---");
            System.out.println(
                    " 1. Show result of relay\n"
                            + " 2. Show 8 athletes advancing to the final round\n"
                            + " 3. Print relay record\n"
                            + " 4. Start final round\n"
                            + " 0. Exit");
            choice = input.nextInt();

            switch (choice) {
                case 4:
                    break;
                case 0:
                    return;
            }
        } while (choice != 4);


        do {
            System.out.println("---FINAL ROUND SYSTEM---");
            System.out.println("1. Start a Shooting Series"
                    + "0. Exit");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
            }
        } while (true); // khi chỉ con 2 vận động viên trong vòng chung kết sẽ kết thúc

//        do {
//            System.out.println("---SHOW RANKING---");
//            System.out.println("1. Show ranking of athletes"
//                    + "Show athletes is given a medal"
//                    + "0. Exit");
//            choice = input.nextInt();
//        } while (choice != 0);

    }

    public static void addNewAthlete() {
        int id;
        String fname;
        String lname;
        String nationality;
        float lastScore;
        int lastRank;

        System.out.println("Enter Athlete's first name");
        fname = input.next();

        System.out.println("Enter Athlete's lastname");
        lname = input.next();

        System.out.println("Enter Athlete's number");
        id = input.nextInt();

        input.nextLine();
        System.out.println("Enter Athlete's nationality");
        nationality = input.next();

        System.out.println("Last Score");
        lastScore = input.nextFloat();

        System.out.println("last Rank");
        lastRank = input.nextInt();

        Athlete newAthlete = new AthleteBuilder(fname, lname)
                .setId(id)
                .setNationality(nationality)
                .setLatestRecord(new LatestRecord(lastScore, lastRank))
                .build();
        AthleteManager.addAthlete(newAthlete);
    }

    public static void deleteAthlete() {
        System.out.println("Enter Athlete's number");
        int id = input.nextInt();
        AthleteManager.removeAthlete(id);
    }

    public static void printAllAthletes() {
//        AthleteManager.printAthletes();
    }

}
