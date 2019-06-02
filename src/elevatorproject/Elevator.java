
package elevatorproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Elevator implements Runnable {
    private int numOfElevator; // หมายเลขของลิฟต์
    private int nPassenger; // จำนวนผู้โดยสารในลิฟต์
    private int direction; // ทิศทาง 1-ขึ้น 0-ลง
    private int currentFloor; // ชั้นปัจจุบัน
    private boolean idle; // สถานะของลิฟต์ true=ว่าง false=ไม่ว่าง
    private ArrayList<Call> carCall; // ลิสต์ของลิฟต์ ว่าจะไปชั้นไหนบ้าง เก็บเป็น Call
    private ArrayList<Passenger> passenger; //คิวของผู้โดยสารในลิฟต์
    private GroupElevatorController controller; //ตัวควบคุมลิฟต์
    
    public Elevator(int num, GroupElevatorController g){
        this.numOfElevator = num;
        this.nPassenger = 0;
        this.direction = 1;
        this.currentFloor = 1;
        this.idle = true;
        this.carCall = new ArrayList<Call>();
        this.passenger = new ArrayList<Passenger>();
        this.controller = g;
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
    
    public GroupElevatorController getController(){
        return this.controller;
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

    /* คนเดินเข้าลิฟต์ */
    public void setGoToElevator(GroupElevatorController g, int eFloor){ 
        Passenger p;
        ArrayList c = this.getPassengerQueue(); //รายชื่อคนในลิฟต์
        //int eFloor = e.getCurrentFloor(); //ตรวจสอบชั้นปัจจุบัน
        //System.out.println("c :"+c+",eFloor : "+eFloor);
        Map<Integer,ArrayList<Passenger>> f = g.getFloorCallPassenger(); //เรียกใช้ floorcall เก็บไว้ใน f        
        if(f.containsKey(eFloor)){ //ถ้าชั้นนั้นมีคน
            /* เพิ่มคนเข้า คิวลิฟต์ */
            for(int i=0;i<f.get(eFloor).size();i++){ //ลูปคนในชั้น
              p = f.get(eFloor).get(i); //ดึงคนจาก floorcall มาทำงาน
              c.add(p);
            }
            /* ลบคนออกจากคิวชั้น */
            for(int i=f.get(eFloor).size();i>0;i--){ //ลูปคนในชั้น  
              p = f.get(eFloor).get(0); //ดึงคนจาก floorcall มาทำงาน
              f.get(eFloor).remove(p);
            }
//            System.out.println("Goto\nFloor : "+eFloor);
//            System.out.println("E passenger : "+e.getPassengerQueue());
//            System.out.println("F passenger : "+this.getFloorCallPassenger());
        }
    }
    /* เอาคนออกจากลิฟต์เมื่อถึงชั้นที่ต้องการ */
    public void setGoOutElevator(GroupElevatorController g, int eFloor){
        Passenger p;
        ArrayList<Passenger> c = this.getPassengerQueue(); //รับข้อมูลคนในลิฟต์
        ArrayList<Passenger> d = g.getFloor()[eFloor-1].getPeopleFloor();
        for(int i=0;i<c.size();i++){ //ลูปเช็คทีละคนว่าต้องการลงชั้นนี้หรือเปล่า ถ้าใช่ให้ลบออกจากลิฟต์
            p = c.get(i);
            if(p.getCarCall().getCallFloor()==eFloor){
                d.add(p); //คนที่ออกจากลิฟต์ เก็บไว้ในอาเรย์ลิสต์คนทั่วไป
                c.remove(p); //ลบคนออกจากลิฟต์
            }
            /*ถ้ามีคนอยู่ชั้นหนึ่ง และdirection เป็น0(ลง) ให้ลบคนออกไปเลย เพราะถือว่าเสร็จงานแล้ว*/
            if(p.getCarCall().getCallDirection()==0 && d.contains((Passenger)p)){
                d.remove(p); //ลบออกจากตึก
            }
        }
//        System.out.println("GoOut\nFloor : "+eFloor+"\nE passenger :"+c);
//        System.out.println("People : "+g.getFloor()[eFloor-1].getPeopleFloor());
//        System.out.println("c size :"+c.size()+"\n");
//        System.out.println("d : "+d);
    }
    
    public void elevatorGoUp(GroupElevatorController g){ //ลิฟต์ขึ้น ถ้าชั้นปัจจุบันยังไม่ถึงขั้นสูงสุด
        int curFloor = this.getCurrentFloor();
        while(curFloor<=g.getFloor().length){
            System.out.println("Elevator : "+this.getNumOfElevator()+",CurFloor : "+curFloor);
            this.setGoOutElevator(g, curFloor);
            this.setGoToElevator(g, curFloor);
            this.setDirection(1);
            curFloor++;
            try{
                Thread.sleep(2000);
            }catch(InterruptedException a){
                System.out.println("Interrupted! "+a);
            }
        }
    }
    
    public void elevatorGoDown(GroupElevatorController g){ //ลิฟต์ลงถ้าถึงชั้นสูงสุดแล้ว
        int curFloor = g.getFloor().length;//e.getCurrentFloor();
        while(curFloor>=1){
            System.out.println("Elevator : "+this.getNumOfElevator()+",CurFloor : "+curFloor);
            this.setGoOutElevator(g, curFloor);
            this.setGoToElevator(g, curFloor);
            this.setDirection(0);
            curFloor--;
            try{
                Thread.sleep(2000);
            }catch(InterruptedException a){
                System.out.println("Interrupted! "+a);
            }
        }
    }
    /* ลิฟต์วิ่ง */
    public void elevatorGo(GroupElevatorController g){
        if(this.getDirection()==1){ /* ถ้าลิฟต์มีทิศขึ้นให้ go up ต่อแล้วค่อย go down */
            this.elevatorGoUp(g);
            this.elevatorGoDown(g);
        }else{  /* ถ้าลิฟต์มีทิศลง ให้ go down แล้วค่อย go up  */
            this.elevatorGoDown(g);
            this.elevatorGoUp(g);
        }
    }

    @Override
    public void run() {
        //System.out.println("Elevator : "+this.getNumOfElevator());
        this.elevatorGo(this.getController());
    }
    
    
    
    
        
        
    
    
}
