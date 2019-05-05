package elevatorproject;

import java.time.LocalTime;
/*class อาคาร เป็น class main*/
public class Building {
    private int nElevator; //จำนวนลิฟต์
    private int nFloor; // จำนวนชั้น
    private int speed; // ความเร็วการ่จำลอง
    private int nPop; // จำนวนคน
    private LocalTime time; // กำหนดเวลา
    private GroupElevatorController controller; // ควบคุมลิฟต์ทั้งหมดและชั้นทั้งหมด
    private Elevator elevatorGroup[]; // ลิฟต์ทุกตัวรวมเป็นกลุ่ม
    private Floor floor[]; // รวมชั้นทั้งหมดเป็นอาเรย์
    
    /*method กำหนดลักษณะอาคาร ใส่ชั้น, ลิฟต์, เวลา*/
    public Building(int nFloor, int nElevator, LocalTime time){
        this.nFloor = nFloor;
        this.nElevator = nElevator;
        this.time = time;
        this.elevatorGroup = new Elevator[nElevator];
        this.floor = new Floor[nFloor];
    }
    /*กำหนดจำนวนลิฟต์*/
    public void setElevator(int nElevator){
        this.nElevator = nElevator;
    }
    /*แสดงจำนวนลิฟต์*/
    public int getElevator(){
        return this.nElevator;
    }
    /*เพิ่มลิฟต์*/
    public void changeElevator(int n){
        nElevator = n; //กำหนดจำนวนลิฟต์ตาม n
        /*สร้างรูปลิฟต์ตามจำนวน n*/
    }
    
    public void setFloor(int nFloor){
        this.nFloor = nFloor;
    }
    
    public int getFloor(){
        return nFloor;
    }
    
    public void changeFloor(int f){
        nFloor = f;
    }
    
    public void setPopulation(int nPop){
        this.nPop = nPop;
    }
    
    public int getPopulation(){
        return nPop;
    }
    
    /* !! เพิ่มมา */
    public void applyRun(){
        /*ปรับรูปภาพก่อนจำลอง*/
    }
    
    public void startRun(){
        /*เริ่มการจำลอง*/
    }
    
    public void stopRun(){
        /*พักการจำลอง*/
    }
    
    public void resetRun(){
        /*ล้างการจำลอง*/
    }
    
    public void adjustTime(int speed){
        /*ปรับความเร็วการจำลอง*/
    }
    
    public void showTime(){
        /*แสดงเวลา*/
    }
    
    public static void main(String[] args) {
    }
    
}
