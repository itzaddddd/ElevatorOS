
package elevatorproject;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private String ID; // ID ของลิฟต์
    private int nPassenger; // จำนวนผู้โดยสารในลิฟต์
    private int direction; // ทิศทาง 1-ขึ้น 0-ลง
    private int currentFloor; // ชั้นปัจจุบัน
    private boolean idle; // สถานะของลิฟต์ true=ว่าง false=ไม่ว่าง
    private ArrayList<Call> carCall; // ลิสต์ของลิฟต์ ว่าจะไปชั้นไหนบ้าง เก็บเป็น Call
    
    public Elevator(String ID){
        this.ID = ID;
        this.nPassenger = 0;
        this.direction = 1;
        this.currentFloor = 1;
        this.idle = true;
        this.carCall = new ArrayList<Call>();
    }
    
    public boolean isIdle(){
        return idle;
    }
    
    public boolean isFull(){
        if(nPassenger>14){
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
    
    public void setID(String ID){
        this.ID = ID;
    }
    
    public String getID(){
        return ID;
    }
    
    public void openDoor(){
        /*เปิดประตูลิฟต์*/
    }
    
    public void closeDoor(){
        /*ปิดประตูลิฟต์*/
    }
    
    public void goUp(){
        /*ลิฟต์ขึ้น*/
    }
    
    public void goDown(){
        /*ลิฟต์ลง*/
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
    
    public void runElevator(){
        /*สั่งให้ลิฟต์ทำงาน*/
        
    }
    
}
