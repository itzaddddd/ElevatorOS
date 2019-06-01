
package elevatorproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    private int numOfElevator; // หมายเลขของลิฟต์
    private int nPassenger; // จำนวนผู้โดยสารในลิฟต์
    private int direction; // ทิศทาง 1-ขึ้น 0-ลง
    private int currentFloor; // ชั้นปัจจุบัน
    private boolean idle; // สถานะของลิฟต์ true=ว่าง false=ไม่ว่าง
    private ArrayList<Call> carCall; // ลิสต์ของลิฟต์ ว่าจะไปชั้นไหนบ้าง เก็บเป็น Call
    private ArrayList<Passenger> passenger; //คิวของผู้โดยสารในลิฟต์
    
    public Elevator(int num){
        this.numOfElevator = num;
        this.nPassenger = 0;
        this.direction = 1;
        this.currentFloor = 1;
        this.idle = true;
        this.carCall = new ArrayList<Call>();
        this.passenger = new ArrayList<Passenger>();
    }
    
    public boolean isIdle(){
        return idle;
    }
    
    public boolean isFull(){
        if(this.getPassenger()>14){
            return true;
        }else{
            return false;
        }
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public void setPassenger(int nPassenger){
        this.nPassenger = nPassenger;
    }
    
    public int getPassenger(){
        return nPassenger;
    }
    
    public void setCurrentFloor(int currentFloor){
        this.currentFloor = currentFloor;
    }
    
    public int getCurrentFloor(){
        return currentFloor;
    }
    
    public void setNumOfElevator(int num){
        this.numOfElevator = num;
    }
    
    public int getNumOfElevator(){
        return numOfElevator;
    }
    
    public ArrayList<Passenger> getPassengerQueue(){ //ผดส.ยืนคละกันอยู่ในลิฟต์ ไม่ได้เรียงคิว
        return passenger;
    }
    
    public void openDoor(){
        /*เปิดประตูลิฟต์*/
    }
    
    public void closeDoor(){
        /*ปิดประตูลิฟต์*/
    }
    
    public void goUp(){
        /*ลิฟต์ขึ้น*/
        currentFloor++;
    }
    
    public void goDown(){
        /*ลิฟต์ลง*/
        currentFloor--;
    }
    
    public void waitPassenger(){
        /*รอผู้โดยสารเข้า-ออกลิฟต์*/
    }
    
    public void stop(){
        /*หยุดลิฟต์*/
    }
    
    public void showPassenger(){
        /*แสดงจำนวนผู้โดยสารในลิฟต์*/
    }
    
    public void showDirection(){
        /*แสดงทิศทางของลิฟต์*/
    }
    
    public ArrayList<Call> getCarCall(){
        return carCall;
    }
    
    public void addCarCall(String ID){
       /*เพิ่ม call จาก passenger*/
    }
    
    public void removeCarCall(String ID){
        /*ลบ call ที่เสร็จแล้ว*/
    }
    
    public void checkCarCall(){
        /*ค้นหาใน carCall list*/
    }
    
    public void checkRelease(){
        /*เมื่อลิฟตืผ่านชั้นนั้น จะตรวจสอบว่า มีคนในลิฟต์จะลงหรือเปล่า*/
    }
    
    public void checkFull(){
        if(isFull()){
            int cur = this.getCurrentFloor();
            this.closeDoor();
            this.goUp();
            this.stop();
        }
    }
    
    
        
        
    
    
}
