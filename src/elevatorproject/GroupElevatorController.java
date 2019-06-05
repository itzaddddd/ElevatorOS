  
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
    private boolean isRequest;
    
    public GroupElevatorController(Elevator e[], Floor f[]){
        this.elevatorGroup = e;
        this.floor = f;
        this.floorCallPassenger = new HashMap<>();
        this.time = new Time(this);
        this.isRequest = false;
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

    
    public HashMap getFloorCallPassenger(){
        return this.floorCallPassenger;
    }
    
    public void showFloorCallList(){
        Map<Integer,ArrayList<Passenger>> fp = this.getFloorCallPassenger();
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
    
    public void selectElevator(){
        /*มอบหมายให้ลิฟต์ตัวไหนทำงาน*/
    }
    /*ยังไม่เสร็จ*/
    public void assignJob(Elevator e, Floor floor){
        /* ขาไป ผดส อยู่ในคิวแต่ละชั้น ตรวจสอบ request ว่าชั้นไหนบ้างที่ต้องการขึ้นหรือลง ถ้ามี ให้เก็บ request เป็นคิว เรียกแต่ละคิวมาหาลิฟต์ที่ดีที่สุดของแต่ละชั้น */
        /* ลิฟต์จะมีสถานะ opendoor ถ้าลิฟต์ตัวไหน opendoor ให้ผดส เดินเข้า  */
                
        /* ลูป ใน queue หา floorCall เดียวกัน 
           หา elevator ที่ currentFloor ใกล้ที่สุด ใช้ findDistance()
            ลิฟต์ตัวไหน findDistance() น้อยสุด ให้ elevator[i].assignJob(ชั้นที่ต้องไป)
        */
        Thread t = new Thread(e);
        if(e.isIdle()){ //ถ้าลิฟต์ว่าง ให้ลิฟต์ตัวนั้นทำงาน
            t.start();
        }else{ //ถ้าไม่ว่าง (กำลังทำงาน)
            e.receivePassenger(floor);
            e.setWaitPaseenger(false);//รับผดส.เสร็จให้เปลี่ยนเป็น false
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
                if(this.getElevatorGroup()[i].isIdle()){
                    minDistance = this.findDistance(controller.getElevatorGroup()[i], desFloor);
                    controller.setSelectedElevator(i);
                }else{
                    i=0;
                }
            }
        }
        //System.out.println("Selected Elevator : "+this.getSelectedElevator());
        return minDistance;
    }

    @Override
    public void run() {
        try{
            while(this.getTime().getHours()<19){
                /* วนแต่ละชั้นว่ามี req ไหม ถ้ามีให้ assign งานกับลิฟต์ */
                for(int i=0;i<this.getFloor().length;i++){
                    if(this.getFloor()[i].isReqGoUp()){ //ถ้าชั้นนั้นมีคนต้องการขึ้น
                        this.findMinDistance(this, this.getFloor()[i]); // หาลิฟต์ที่ทำให้มีระยะห่างสั้นสุด
                        int selected = this.getSelectedElevator(); //เลือกลิฟต์
                        this.assignJob(this.getElevatorGroup()[selected],this.getFloor()[i]); // กำหนดให้ลิฟต์ตัวนั้นเป็นคนทำงาน
                    }
                }
                /*ตรวจดูผดส.ในชั้น*/
                Thread.sleep(1000);
            }

            Thread.sleep(1000);
        }catch(InterruptedException a){
            System.out.println(a);
        }
    }
    
}
