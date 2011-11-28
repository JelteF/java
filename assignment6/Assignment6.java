/*
 * Name: Jelte Fennema
 * Studentnumber: 10183159
 * Study: Informatica
 *
 * Functionality:
 */

import java.util.*;

public class Assignment6 {
    static Scanner sc = new Scanner(System.in);
    static Hotel hotel = new Hotel(4);
    /*
     * Main method: 
     */
    public static void main(String[] args) {
        while(true){
            System.out.print(
                      "\nMENU:  [1] Statusreport\n"
                    + "       [2] Check-in\n"
                    + "       [3] Check-out\n"
                    + "       [4] Stop\n"
                    + "Enter choice: ");
            if(sc.hasNextInt()){
                int input = sc.nextInt();
                if(input == 1)
                    hotel.print();
                else if(input == 2)
                    hotel.checkIn();
                else if(input == 3)
                    hotel.checkOut();
                else if(input == 4)
                    break;
                else
                    System.out.println("Incorrect input.");
            }
            else{
                sc.next();
                System.out.println("Incorrect input.");
            }
        }
    }
}

class Hotel {
    Scanner sc = new Scanner(System.in);
    int amntRooms;
    Room[] rooms;
    int roomsTaken;
    Hotel() {}
    Hotel(int x){
        amntRooms = x;
        rooms = new Room[x];
        for (int i = 0; i<x; i++){
            rooms[i]=new Room();
        }
    }

    void checkIn(){
        if(roomsTaken<amntRooms){
            String fn = "", sn= "";
            System.out.print("First name of the guest: ");
            if(sc.hasNext())
                fn = sc.next();
            System.out.print("Surname of the guest: ");
            if(sc.hasNext())
                sn=sc.next();
            for(int i=0; i<amntRooms; i++){
                if(!rooms[i].occupied){
                    rooms[i].checkIn(fn, sn);
                    break;
                }
            }
            roomsTaken++;
        }
        else
            System.out.println("All rooms are full");
    }

    void checkOut(){
        int roomNumber = 0;
        System.out.print("Room number for checkout: ");
        if(sc.hasNextInt()){
            roomNumber = sc.nextInt()-1;
            if(roomNumber>-1 && roomNumber<=amntRooms){
                if(rooms[roomNumber].occupied){
                    rooms[roomNumber].checkOut();
                    roomsTaken--;
                }
                else
                    System.out.println("That room is not checked in.");
            }
            else{
                System.out.println("That room is nonexistent.");
            }

        }
        else{
            System.out.println("Use numbers next time, please.");
            sc.next();
        }
    }

    void print(){
        for (int i = 0; i<amntRooms; i++){
            System.out.print("Room " + (i+1) +": ");
            rooms[i].print();
        }
    }
}

class Room {
    boolean occupied;
    Guest guest;
    Room() {}
    void checkIn(String firstName, String surName) {
        occupied = true;
        guest= new Guest(firstName, surName);
    }

    void print(){
        if(occupied){
            guest.print();
        }
        else{
            System.out.println("Free");
        }
    }

    void checkOut(){
        occupied = false;
        guest = null;
    }

}

class Guest {
    String firstName, surName;
    Guest(){}
    Guest(String fName, String sName){
        firstName = fName;
        surName = sName;
    }
    void print(){
        System.out.println("Name: " + firstName + " " + surName);
    }
}