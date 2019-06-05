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
    private boolean reqGoUp;
    private boolean reqGoDown;
    
    public Floor(int numOfFloor){
        this.numOfFloor = numOfFloor;
        this.passengerInFloor = 0;
        this.passenger = new LinkedList<Passenger>();
        this.people = new ArrayList<Passenger>();
        this.floorCall = new ArrayList<Call>();
        this.carCall = new ArrayList<Call>();
        this.reqGoUp = false;
        this.reqGoDown = false;
    }
    
    public ArrayList<Call> getFloorCall(){
        return floorCall;
    }
    
    public ArrayList<Call> getCarCall(){
        return carCall;
    }
    
    public void setReqGoUp(boolean req){
        this.reqGoUp = req;
    }
 
    public void setReqGoDown(boolean req){
        this.reqGoDown = req;
    }
    
    public void setPassengerInFloor(int pass){
        this.passengerInFloor = pass;
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
    /*ต้องการขึ้นบนไหม*/
    public boolean isReqGoUp(){
        Queue<Passenger> q = this.getPassengerQueue(); //เรียกคิวผดส.ในชั้น
        for(Passenger p:q){
            if(p.getCarCall().getCallFloor()>this.getNumOfFloor() && p.getCarCall().getCallDirection()==1){ //ถ้ามีผู้โดยสารต้องการขึ้นให้ตอบว่าใช่
                this.reqGoUp = true;
                break; //หยุดการทำงาน
            }
        }
        return this.reqGoUp;
    }
    /*ต้องการลงล่างไหม*/
    public boolean isReqGoDown(){
        Queue<Passenger> q = this.getPassengerQueue();
        for(Passenger p:q){
            if(p.getCarCall().getCallFloor()<this.getNumOfFloor() && p.getCarCall().getCallDirection()==0){ //ถ้ามีผู้โดยสารต้องการลงให้ตอบว่าใช่ 
                this.reqGoDown = true;
                break;
            }
        }
        return this.reqGoDown;
    }
}
