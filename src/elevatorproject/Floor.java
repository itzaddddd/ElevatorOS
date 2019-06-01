package elevatorproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;

public class Floor {
    private Queue<Passenger> passenger; // ผู้โดยสารต่อคิวขึ้นลิฟต์
    private ArrayList<Passenger> people; // คนในชั้นเฉยๆ
    private ArrayList<Call> floorCall; // เก็บการเรียกลิฟต์
    private ArrayList<Call> carCall;
    private int numOfFloor; // ชั้นที่
    private int passengerInFloor; //จำนวนผู้โดยสารในชั้น
    
    public Floor(int numOfFloor){
        this.numOfFloor = numOfFloor;
        this.passengerInFloor = 0;
        this.passenger = new LinkedList<Passenger>();
        this.people = new ArrayList<Passenger>();
        this.floorCall = new ArrayList<Call>();
        this.carCall = new ArrayList<Call>();
    }
    
    public void generatePassenger(Building b){
        /* สร้างคนในชั้น */
        Random rand = new Random();
        int nFloor = b.getNumFloor();

        String ID = UUID.randomUUID().toString(); // Create passenger ID
        int randFloor = (rand.nextInt(nFloor))+1;
        System.out.println(this.numOfFloor);
        // If randFloor is the top floor, then the direction is always down
        // Else if randFloor is the main floor, then the direction is always up
        // Else the direction is chosen randomly
        int direction = 0;
        if(randFloor == 1) {
            direction = 1; // Direction is up
        }else if(randFloor == (nFloor)){
            direction = 0; // Direction is down
        }else {
            direction = rand.nextInt(2); // Randomly select direction
        }

        Call floorCall = new Call(1, randFloor, direction, ID);

        // Randomly generate an exitCall, based on randFloor
        int exitFloor = 1;
        if(direction == 1) {

            // Generate random number, until it's greater than randFloor
            exitFloor = (rand.nextInt(nFloor))+1;
            while (exitFloor <= randFloor){
                exitFloor = (rand.nextInt(nFloor))+1;
            }
        }else{

            // Generate random number, until it's smaller than randFloor
            exitFloor = (rand.nextInt(nFloor))+1;
            while (exitFloor >= (randFloor)+1){
                exitFloor = (rand.nextInt(nFloor))+1;
            }
        }

        Call carCall = new Call(0, exitFloor, direction, ID);
        this.passenger.add(new Passenger(floorCall, carCall, ID));
        this.passengerInFloor++;
        System.out.println("ID : "+ID+"\nFloorCall : "+randFloor+"\nExitFloor : "+exitFloor+"\nDirection : "+direction+"\n"); // Create a Passenger object and add it the to the passengers array 
        
    }
    
    public void addFloorCall(){
        /* เพิ่ม call เมื่อมีการกดลิฟต์ */
    }
    
    public void removeFloorCall(String ID){
        /* ลบ call เมื่อเข้าลิฟต์แล้ว */
    }
    
    public ArrayList<Call> getFloorCall(){
        return floorCall;
    }
    
    public ArrayList<Call> getCarCall(){
        return carCall;
    }
    
    public void setPassengerInFloor(int pass){
        this.passengerInFloor = pass;
    }
    
    public int getPassengerInFloor(){
        return passengerInFloor;
    }
    
    public void removePassengerInFloor(){
        /*ลบผู้โดยสารหลังเข้าลิฟต์ไปแล้ว*/
    }
    
    public int getNumOfFloor(){
        return numOfFloor;
    }
    
    public void setNumOfFloor(int nf){
        this.numOfFloor = nf;
    }
    
    public Queue getPassengerQueue(){
        return passenger;
    }
    
    public ArrayList<Passenger> getPeopleFloor(){
        return people;
    }    
    
  
    
}
