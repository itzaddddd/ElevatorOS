/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;

import java.time.LocalTime;

/**
 *
 * @author isara
 */
public class Main {
    public static void main(String[] args) {
        /*สร้างอา่คาร*/
        Building b = new Building(6,3,LocalTime.of(7, 0));
        
        /*สร้างชั้น*/
        Floor[] f = b.getFloor();
       
        for(int i=0;i<b.getNumFloor();i++){
            f[i] = new Floor(i+1);
        }
        /*สร้างคน*/
        for(int i=0;i<b.getNumFloor();i++){
            f[i].generatePassenger(b);
        }
        /*สร้างลิฟต์*/
        System.out.println("test1");
        Elevator[] e = b.getElevator();
        for(int j=0;j<b.getNumElevator();j++){
            e[j] = new Elevator(j+1);
            //System.out.println("Elevator "+e[j].getNumOfElevator()+" : "+e[j].getPassenger());
        }
        
        /*แสดงข้อมูล*/
        System.out.println(b.getNumFloor()+" "+b.getNumElevator()+" "+b.getTime());
        
        /* loop เปิดตึก */
        for(int i=0;i<b.getNumFloor();i++){
            Passenger p = (Passenger) f[i].getPassengerQueue().peek();
            //System.out.println((i+1)+" ID : "+p.getID()+" ,FloorCall at "+p.getFloorCall().getCallFloor()+" Floor"+" ,CarCall at "+p.getCarCall().getCallFloor()+" Floor");
            
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
        b.getController().setFloorCallList();
        b.getController().showFloorCallList();
        b.getController().assignJob();
        b.getController().elevatorGo(b.getElevator()[0]);
        //b.getController().setCarCallList();
        //b.getController().showCarCallList();
  
        
        
        
        
    }
    
}
