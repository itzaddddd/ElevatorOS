  
package elevatorproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GroupElevatorController {
    private Elevator elevatorGroup[];
    private Floor floor[];
    private int SelectedElevator;
    private HashMap floorCallPassenger;
    private HashMap carCallList;
    
    public GroupElevatorController(Elevator e[], Floor f[]){
        this.elevatorGroup = e;
        this.floor = f;
        this.floorCallPassenger = new HashMap<>();
    }
    
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
    
    /* คนเดินเข้าลิฟต์ */
    public void setGoToElevator(Elevator e, int eFloor){ 
        Passenger p;
        ArrayList c = e.getPassengerQueue(); //รายชื่อคนในลิฟต์
        //int eFloor = e.getCurrentFloor(); //ตรวจสอบชั้นปัจจุบัน
        //System.out.println("c :"+c+",eFloor : "+eFloor);
        Map<Integer,ArrayList<Passenger>> f = this.getFloorCallPassenger(); //เรียกใช้ floorcall เก็บไว้ใน f
        //System.out.println("size : "+f.size());
        
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
    public void setGoOutElevator(Elevator e, int eFloor){
        Passenger p;
        ArrayList<Passenger> c = e.getPassengerQueue(); //รับข้อมูลคนในลิฟต์
        ArrayList<Passenger> d = this.getFloor()[eFloor-1].getPeopleFloor();
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
        System.out.println("GoOut\nFloor : "+eFloor+"\nE passenger :"+c);
        System.out.println("People : "+this.getFloor()[eFloor-1].getPeopleFloor());
        System.out.println("c size :"+c.size()+"\n");
        System.out.println("d : "+d);
    }
    
    public void elevatorGoUp(Elevator e){ //ลิฟต์ขึ้น ถ้าชั้นปัจจุบันยังไม่ถึงขั้นสูงสุด
        int curFloor = e.getCurrentFloor();
        while(curFloor<=this.getFloor().length){
            System.out.println("CurFloor : "+curFloor);
            this.setGoOutElevator(e, curFloor);
            this.setGoToElevator(e, curFloor);
            e.setDirection(1);
            curFloor++;
        }
    }
    
    public void elevatorGoDown(Elevator e){ //ลิฟต์ลงถ้าถึงชั้นสูงสุดแล้ว
        int curFloor = this.getFloor().length;//e.getCurrentFloor();
        while(curFloor>=1){
            System.out.println("CurFloor :"+curFloor);
            this.setGoOutElevator(e, curFloor);
            this.setGoToElevator(e, curFloor);
            e.setDirection(0);
            curFloor--;
        }
    }
    /* ลิฟต์วิ่ง */
    public void elevatorGo(Elevator e){
        if(e.getDirection()==1){ /* ถ้าลิฟต์มีทิศขึ้นให้ go up ต่อแล้วค่อย go down */
            this.elevatorGoUp(e);
            this.elevatorGoDown(e);
        }else{  /* ถ้าลิฟต์มีทิศลง ให้ go down แล้วค่อย go up  */
            this.elevatorGoDown(e);
            this.elevatorGoUp(e);
        }
    }
    
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
    
}
