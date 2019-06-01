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
        this.controller = new GroupElevatorController(this.elevatorGroup,this.floor);
    }
    
    public GroupElevatorController getController(){
        return this.controller;
    }
    
    public void setElevator(Elevator e[]){
        this.elevatorGroup = e;
    }
    
    public Elevator[] getElevator(){
        return elevatorGroup;
    }
    
    public void setFloor(Floor f[]){
        this.floor = f;
    }
    
    public Floor[] getFloor(){
        return floor;
    }
    
    
    /*กำหนดจำนวนลิฟต์*/
    public void setNumElevator(int nElevator){
        this.nElevator = nElevator;
    }
    /*แสดงจำนวนลิฟต์*/
    public int getNumElevator(){
        return this.nElevator;
    }
    /*เพิ่มลิฟต์*/
    public void changeNumElevator(int n){
        nElevator = n; //กำหนดจำนวนลิฟต์ตาม n
        /*สร้างรูปลิฟต์ตามจำนวน n*/
    }
    
    public void setNumFloor(int nFloor){
        this.nFloor = nFloor;
    }
    
    public int getNumFloor(){
        return nFloor;
    }
    
    public void changeNumFloor(int f){
        nFloor = f;
    }
    
    public void setNumPopulation(int nPop){
        this.nPop = nPop;
    }
    
    public int getNumPopulation(){
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
    
    public void adjustSpeed(int speed){
        /*ปรับความเร็วการจำลอง*/
    }
    
    public void plusTime(int t){
        this.time.plusMinutes(t);
    }
    
    public void setTime(int hh, int mm){
        this.time = LocalTime.of(hh, mm);
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    public void showTime(){
        /*แสดงเวลา*/
        System.out.println(time);
    }
     
    
}
