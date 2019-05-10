
package elevatorproject;

import java.util.ArrayList;
import java.util.Queue;

public class GroupElevatorController {
    private Elevator elevatorGroup[];
    private Floor floor[];
    private int SelectedElevator;
    private int[] floorList;
    
    public Elevator[] getElevatorGroup(){
        return elevatorGroup;
    }
    
    public Floor[] getFloor(){
        return floor;
    }
    
    public void setSelectedElevator(int e){
        this.SelectedElevator = e;
    }
    
    public int getSelectedElevator(){
        return SelectedElevator;
    }
    
    public GroupElevatorController(Elevator e[], Floor f[]){
        this.elevatorGroup = e;
        this.floor = f;
    }
    
    public int[] checkFloorCall(){
        /*ค้นหาใน floorCall ของแต่ละชั้น ว่ามีชั้นไหนต้องการลิฟต์บ้าง และแต่ละชั้นมีกี่คน*/
        Passenger p;
        int[] floorList = new int[getFloor().length];
        for(int i=0;i<getFloor().length;i++){
            for(int j=0;j<getFloor()[i].getPassengerQueue().size();j++){
                p = (Passenger)getFloor()[i].getPassengerQueue().peek();
                floorList[p.getFloorCall().getCallFloor()]++;
                }
            }
        return floorList;
    }
    
    public void selectElevator(){
        /*มอบหมายให้ลิฟต์ตัวไหนทำงาน*/
    }
    
    public void assignJob(){
        /* ลูป ใน queue หา floorCall เดียวกัน 
           หา elevator ที่ currentFloor ใกล้ที่สุด ใช้ findDistance()
            ลิฟต์ตัวไหน findDistance() น้อยสุด ให้ elevator[i].assignJob(ชั้นที่ต้องไป)
        */
    }
    
    public int findDistance(Elevator e, Floor desFloor/*ไป*/){ //หาระยะทางจาก่ชั้นที่ลิฟต์อยู่ปัจจุบันถึงชั้นที่ลิฟต์จะไป
        int cur = e.getCurrentFloor(); //อ่านชั้นที่อยู่ของลิฟต์
        int des = desFloor.getNumOfFloor(); //รับค่าชั้นที่จะไป
        int di = e.getDirection(); //อ่านค่าทิศทางของลิฟต์
        int max = floor.length; //กำหนดชั้นสูงสุดของอาคาร
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
        int minDistance = getElevatorGroup().length;
        for(int i=0;i<getElevatorGroup().length;i++){
            System.out.println("Elevator"+i+" "+controller.findDistance(controller.getElevatorGroup()[i], desFloor));
            if(controller.findDistance(controller.getElevatorGroup()[i], desFloor) < minDistance){
                minDistance = this.findDistance(controller.getElevatorGroup()[i], desFloor);
                controller.setSelectedElevator(i);
            }
        }
        
        return minDistance;
    }
    
}
