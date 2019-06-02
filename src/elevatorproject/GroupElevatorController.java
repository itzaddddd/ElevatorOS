  
package elevatorproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GroupElevatorController implements Runnable {
    private Elevator elevatorGroup[];
    private Floor floor[];
    private int SelectedElevator;
    private HashMap floorCallPassenger;
    private HashMap carCallList;
    private Time time;
    
    public GroupElevatorController(Elevator e[], Floor f[]){
        this.elevatorGroup = e;
        this.floor = f;
        this.floorCallPassenger = new HashMap<>();
        this.time = new Time(this);
    }
    
    public Elevator[] getElevatorGroup(){
        return elevatorGroup;
    }
    
    public Floor[] getFloor(){
        return floor;
    }
    
    public Time getTime(){
        return time;
    }
       
    public void setSelectedElevator(int e){
        this.SelectedElevator = e;
    }
    
    public int getSelectedElevator(){
        return SelectedElevator;
    }
    /* 4th update - ย้าย การทำงานของลิฟต์ไปไว้ class elevator */
    public void setFloorCallList(){
        /*ค้นหาใน floorCall ของแต่ละชั้น ว่ามีชั้นไหนต้องการลิฟต์บ้าง และแต่ละชั้นมีกี่คน*/
        Passenger p; // เอาไว้เก็บค่าผู้โดยสารที่เราสนใจ
        
        Map<Integer,ArrayList<Passenger>> fp = new HashMap<>(); //เก็บ hash map รูปแบบ key : value ในที่นี้เก็บเป็น ชั้น(int) : รายชื่อผู้โดยสารในชั้น(arraylist)
        ArrayList<Passenger>[] plist = new ArrayList[this.getFloor().length+1]; //สร้าง array เก็บ arraylist เก็บผดส.ในแต่ละชั้น
        /*สร้างพท.ในหน่วยความจำให้แต่ละarraylist*/
        for(int i=0;i<=this.getFloor().length;i++){
            plist[i] = new ArrayList<>();
        }
        /*เอาผดส.ใน passengerqueue ของแต่ละชั้นมาตรวจสอบชั้นที่จะไป ถ้าจะไปชั้นไหนก็เอาpassengerไปใส่ในarraylistของชั้นนั้น*/
        for(int i=0;i<getFloor().length;i++){ // loop เพื่อเรียกดู passengerqueue ของแต่ละชั้น 
            for(int j=0;j<getFloor()[i].getPassengerQueue().size();j++){ // loop เพื่อดึง passenger แต่ละคนในคิวมาทำงาน
                p = (Passenger)getFloor()[i].getPassengerQueue().peek(); // กำหนด passenger ที่ถูกดึงมาเป็น p
                //นับว่าชั้นนึงมีกี่คน
                int numOfFloor = p.getFloorCall().getCallFloor(); // เรียกดูชั้นที่ผู้โดยสารอยู่
                //ตรวจว่าชั้นนั้นมีข้อมูลอยู่แล้วไหม ถ้าไม่ก็เพิ่มชั้นเข้าไปใหม่ แต่ถ้ามีอยู่แล้วก็เพิ่มคนแค่ในarraylist ของชั้นนั้น
                if(!fp.containsKey(numOfFloor)){
                    plist[numOfFloor].add(p);
                    fp.put(numOfFloor, plist[numOfFloor]);
                }else{
                    plist[numOfFloor].add(p);
                }
            }
        }
        this.floorCallPassenger = (HashMap)fp; // set ค่าให้ attribute floorCallPassenger
    } 
    
    public HashMap getFloorCallPassenger(){
        return this.floorCallPassenger;
    }
    
    public void showFloorCallList(){
        Map<Integer,ArrayList<Passenger>> fp = this.getFloorCallPassenger();
        
        System.out.println("Show Passenger");
        for(int i=0;i<=this.getFloor().length;i++){
            if(i==0)continue;
            if(fp.get(i)==null){
                System.out.println(i+" ID : none");
            }
            else{
                System.out.print(i+" ID : ");
                for(int j=0;j<fp.get(i).size();j++){
                    System.out.print(fp.get(i).get(j).getID()+" ");
                }
                System.out.print("\n");
            }
        }
    }
    
    public void setCarCallList(Elevator e){
        /*set ให้คนเข้ามาในลิฟต์ มีใครบ้าง กี่คน*/
    }
    
    public HashMap getCarCallList(){
        return carCallList;
    }
    
    public void selectElevator(){
        /*มอบหมายให้ลิฟต์ตัวไหนทำงาน*/
    }
    /*ยังไม่เสร็จ*/
    public void assignJob(){
        /* ลูป ใน queue หา floorCall เดียวกัน 
           หา elevator ที่ currentFloor ใกล้ที่สุด ใช้ findDistance()
            ลิฟต์ตัวไหน findDistance() น้อยสุด ให้ elevator[i].assignJob(ชั้นที่ต้องไป)
        */
        HashMap f = this.getFloorCallPassenger();
        int[] min = new int[this.getFloor().length];
        int[] s = new int[this.getFloor().length];
        
        for(int k=0;k<getFloor().length;k++){
            min[k] = 0; 
            s[k] = 0;
        }
        
        for(int i=0;i<this.getFloor().length;i++){
            System.out.println((f.size()));
            if(this.getFloorCallPassenger().size()>0&&!this.getFloorCallPassenger().isEmpty()){
                min[i] = this.findMinDistance(this, this.getFloor()[i]);
                s[i] = this.getSelectedElevator();
            }
        }
        
        for(int i=0;i<this.getFloor().length;i++){
            System.out.println("Min "+(i+1)+" : "+min[i]+",Selected "+s[i]);
        }
    }
    
    public int findDistance(Elevator e, Floor desFloor/*ไป*/){ //หาระยะทางจาก่ชั้นที่ลิฟต์อยู่ปัจจุบันถึงชั้นที่ลิฟต์จะไป
        int cur = e.getCurrentFloor(); //อ่านชั้นที่อยู่ของลิฟต์
        int des = desFloor.getNumOfFloor(); //รับค่าชั้นที่จะไป
        int di = e.getDirection(); //อ่านค่าทิศทางของลิฟต์
        int max = this.getFloor().length; //กำหนดชั้นสูงสุดของอาคาร
        int distance = 0; //กำหนดค่าเริ่มต้นของระยะทางเป็น 0
        if(des-cur>0){//ระยะห่างเป้นบวก
            if(di==1){//ทิศขึ้น
                distance = des-cur;
            }else{//ทิศลง
                distance = (cur-1)+(des-1);
            }
        }else if(des-cur<0){//ระยะห่างเป็นลบ
            if(di==1){//ทิศขึ้น
                distance = (max-cur)+(max-des);
            }else{//ทิศลง
                distance = cur-des;
            }
        }else{
            distance = 0;
        }
        
        return distance;//ส่งค่า distance ออกไป
    }
    
    public int findMinDistance(GroupElevatorController controller,Floor desFloor){ //หาระยะทางที่สั้นที่สุด
        int minDistance = getFloor().length;
        for(int i=0;i<getElevatorGroup().length;i++){
            if(controller.findDistance(controller.getElevatorGroup()[i], desFloor) < minDistance){
                minDistance = this.findDistance(controller.getElevatorGroup()[i], desFloor);
                controller.setSelectedElevator(i);
            }
        }
        System.out.println("Selected Elevator : "+this.getSelectedElevator());
        return minDistance;
    }
    
    public void runElevator(Elevator e){
        /*สั่งให้ลิฟต์ทำงาน*/
        
        /*กำหนดชั้นปัจจุบันเป็น cur*/
        int cur = e.getCurrentFloor();
        Floor[] f = this.getFloor();
        Queue<Passenger> q;
        Passenger p;
        /*ตรวจสอบ Floor call ว่ามีที่ชั้นนี้หรือเปล่า*/
         // *ถ้ามีคนต้องการเข้า เดินเข้าทีละคนและเช็ตคว่าเต็มไหม
        while(cur<6){
            q = f[cur].getPassengerQueue();
            System.out.println("Show queue : "+q.size());
            System.out.println("Cur : "+cur);
            /*if(this.getFloorCallList()[cur]>0){
                for(int i=0;i<q.size();i++){  
                    p = q.peek();
                    // walkin คือ เพิ่มใน ลิสต์ลิฟต์ ลบใน ลิสต์ชั้น ลบคนในชั้น เพิ่มคนในลิฟต์
                    this.getFloor()[cur].getPassengerQueue().remove(p);
                    int r = this.getFloor()[cur].getPassengerInFloor();
                    r--;
                    e.getPassengerQueue().add(p);
                    int s = e.getPassenger();
                    s++;
                    System.out.println("Cur : "+cur+", ");
                    cur++;
                }
            }else{
                cur++;
            }
            */
            cur++;
        }
    }

    @Override
    public void run() {
        try{
            while(this.getTime().getHours()<23){
//                this.setFloorCallList();
//                this.showFloorCallList();
//                this.assignJob();
                Thread.sleep(1000);
            }
        }catch(InterruptedException a){
            System.out.println(a);
        }
    }
    
}
