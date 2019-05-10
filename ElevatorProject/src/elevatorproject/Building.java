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
    
    public static void main(String[] args) {
        /*สร้างอา่คาร*/
        Building b = new Building(6,3,LocalTime.of(7, 0));
        
        /*สร้างชั้น*/
        Floor[] f = b.getFloor();
        System.out.println("test1");
        for(int i=0;i<b.getNumFloor();i++){
            f[i] = new Floor(i+1);
            System.out.println("Floor "+f[i].getNumOfFloor()+" : "+f[i].getPassengerInFloor());
        }
        /*สร้างคน*/
        for(int i=0;i<b.getNumFloor();i++){
            f[i].generatePassenger(b);
            System.out.println("Floor "+f[i]+" : "+f[i].getPassengerInFloor());
        }
        /*สร้างลิฟต์*/
        System.out.println("test1");
        Elevator[] e = b.getElevator();
        for(int j=0;j<b.getNumElevator();j++){
            e[j] = new Elevator(j+1);
            System.out.println("Elevator "+e[j].getNumOfElevator()+" : "+e[j].getPassenger());
        }
        
        /*แสดงข้อมูล*/
        System.out.println(b.nFloor+" "+b.nElevator+" "+b.time);
        
        /* loop เปิดตึก */
        for(int i=0;i<b.getNumFloor();i++){
            Passenger p = (Passenger) f[i].getPassengerQueue().peek();
            System.out.println((i+1)+" ID : "+p.getID()+" ,FloorCall at "+p.getFloorCall().getCallFloor()+" Floor"+" ,CarCall at "+p.getCarCall().getCallFloor()+" Floor");
            
        }
        
        for(int k=0;k<b.getNumElevator();k++){
            int distance = b.getController().findDistance(b.getElevator()[k], f[5]);
            System.out.println("Distance from elevator no."+(k+1)+" to Floor 5 is "+distance);
        }
        
        int minDistance = b.getController().findMinDistance(b.getController(),f[4]);
        System.out.println("Min distance is from Elevator "+b.getController().getSelectedElevator()+", Distance is "+minDistance);
        
//        int[] list = new int[b.getNumFloor()];
//        list = b.getController().checkFloorCall(); 
//        
//        for(int i=0;i<list.length;i++){
//            System.out.println(i+" : "+list[i]);

        //}
        
        
        
    }
    
}
