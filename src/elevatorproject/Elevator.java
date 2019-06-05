
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
    private boolean wait;
    
    public Elevator(int num, GroupElevatorController g){
        this.numOfElevator = num;
        this.nPassenger = 0;
        this.direction = 1;
        this.currentFloor = 1;
        this.idle = true;
        this.wait = false;
        this.carCall = new ArrayList<Call>();
        this.passenger = new ArrayList<Passenger>();
        this.controller = g;
    }
    
    public boolean isIdle(){
        return idle;
    }
    
    public void setIdle(boolean idle){
        this.idle = idle;
    }
    
    public boolean isFull(){
        if(this.getPassenger()>14){
            return true;
        }else{
            return false;
        }
    }
    
    public void setWaitPaseenger(boolean w){
        this.wait = w;
    }
    
    public boolean isWait(){
        return this.wait;
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
    
    public void stop(){
        /*หยุดลิฟต์*/
    }
    
    public void showPassenger(){
        /*แสดงจำนวนผู้โดยสารในลิฟต์*/
    }
    
    public void showDirection(){
        /*แสดงทิศทางของลิฟต์*/
    }
    
    public void setGoToElevator(int eFloor){ 
        Passenger p;
        ArrayList c = this.getPassengerQueue(); //รายชื่อคนในลิฟต์        
            if(this.getCurrentFloor() == this.getController().getFloor()[eFloor-1].getNumOfFloor()){ //ถ้าชั้นนั้นมีคน
                Queue q = this.getController().getFloor()[eFloor-1].getPassengerQueue(); //เอาคิวของผดส.ชั้นนั้นมาใช้
                for(int i=0;i<q.size();i++){ //ลูปคนในชั้น
                    p = (Passenger)q.peek(); //ดึงคนจาก floorcall มาทำงาน
                        c.add(p);
                        q.remove(p);
                      
                }
                System.out.println("c :"+c+"\nq : "+q+"\n");
            }
    }
    /* เอาคนออกจากลิฟต์เมื่อถึงชั้นที่ต้องการ */
    public void setGoOutElevator(int eFloor){
        Passenger p;
        ArrayList<Passenger> c = this.getPassengerQueue(); //รับข้อมูลคนในลิฟต์
        for(int i=0;i<c.size();i++){ //ลูปเช็คทีละคนว่าต้องการลงชั้นนี้หรือเปล่า ถ้าใช่ให้ลบออกจากลิฟต์
            p = c.get(i);
            if(p.getCarCall().getCallFloor()==eFloor){
                c.remove(p); //ลบคนออกจากลิฟต์
                System.out.println("p : "+p.getCarCall().getCallFloor()+",eFloor : "+eFloor);
            }
        }
    }
    
    public void elevatorGoUp(){ //ลิฟต์ขึ้น ถ้าชั้นปัจจุบันยังไม่ถึงขั้นสูงสุด
        int curFloor = this.getCurrentFloor();
        this.setIdle(false);
        while(curFloor<this.getController().getFloor().length){
            this.setDirection(1);
            if(this.isWait()){
                try{
                    Thread.sleep(4000);
                }catch(InterruptedException a){
                    System.out.println(a);
                };
            }
            System.out.println("Elevator : "+this.getNumOfElevator()+",CurFloor : "+curFloor);
            System.out.println("E : "+this.getPassengerQueue()+"\nF : "+this.getController().getFloor()[0].getPassengerQueue()+"\n");
            curFloor++;
            try{
                Thread.sleep(2000);
            }catch(InterruptedException a){
                System.out.println("Interrupted! "+a);
            }
        }
    }
    
    public void elevatorGoDown(){ //ลิฟต์ลงถ้าถึงชั้นสูงสุดแล้ว
        int curFloor = this.getController().getFloor().length;//e.getCurrentFloor();
        while(curFloor>1){
            System.out.println("Elevator : "+this.getNumOfElevator()+",CurFloor : "+curFloor);
            System.out.println("E : "+this.getPassengerQueue()+"\nF : "+this.getController().getFloor()[0].getPassengerQueue()+"\n");
            this.setDirection(0);
            if(this.isWait()){
                try{
                    Thread.sleep(4000);
                }catch(InterruptedException a){
                    System.out.println(a);
                };
            }
            curFloor--;
            try{
                Thread.sleep(2000);
            }catch(InterruptedException a){
                System.out.println("Interrupted! "+a);
            }
        }
        this.setIdle(true);
    }
    /* ลิฟต์วิ่ง */
    public void elevatorGo(){
        //if(e.getDirection()==1){ /* ถ้าลิฟต์มีทิศขึ้นให้ go up ต่อแล้วค่อย go down */
            this.elevatorGoUp();
            this.elevatorGoDown();
    }
    
    public void receivePassenger(Floor f){
        try{
            if(this.getCurrentFloor()==f.getNumOfFloor()){
            Thread.sleep(4000);
            this.setGoOutElevator(f.getNumOfFloor());
            this.setGoToElevator(f.getNumOfFloor());
            this.setWaitPaseenger(true);
            }
        }catch(InterruptedException a){
            System.out.println(a);
        };
        
    }

    /* คนเดินเข้าลิฟต์ */
    @Override
    public void run() {
        this.elevatorGo();
    }
    
    
    
    
        
        
    
    
}
