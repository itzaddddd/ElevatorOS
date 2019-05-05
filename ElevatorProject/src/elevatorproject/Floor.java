package elevatorproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Floor {
    private Queue<Passenger> passenger; // ผู้โดยสารต่อคิวขึ้นลิฟต์
    private ArrayList<Call> floorCall; // เก็บการเรียกลิฟต์
    private int numOfFloor; // ชั้นที่
    private int passengerInFloor; //จำนวนผู้โดยสารในชั้น
    
    public Floor(int numOfFloor){
        this.numOfFloor = numOfFloor;
        this.passengerInFloor = 0;
        this.passenger = new LinkedList<Passenger>();
        this.floorCall = new ArrayList<Call>();
    }
    
    public void generatePassenger(){
        /* สร้างคนในชั้น */
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
    
    public void setPassengerInFloor(int pass){
        this.passengerInFloor = pass;
    }
    
    public int getPassengerInFloor(){
        return passengerInFloor;
    }
    
    public void removePassengerInFloor(){
        /*ลบผู้โดยสารหลังเข้าลิฟต์ไปแล้ว*/
    }
    
    
  
    
}
