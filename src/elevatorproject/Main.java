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
    public static void main(String[] args) throws InterruptedException {
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
        //สร้างคน จริงๆ น่าจะต้องใช้ thread 
        /*สร้างลิฟต์*/
        System.out.println("test1");
        Elevator[] e = b.getElevator();
        Thread[] t = new Thread[b.getNumElevator()]; //สร้าง thread ให้ลิฟต์แต่ละตัว
        for(int i=0;i<b.getNumElevator();i++){
            e[i] = new Elevator(i+1,b.getController());
            t[i] = new Thread(e[i]);
            //Thread e[j] = new Thread(new Elevator(0, b.getController()));
            //System.out.println("Elevator "+e[j].getNumOfElevator()+" : "+e[j].getPassenger());
        }
        //สร้างลิฟต์ จริงๆ น่าจะใช้ thread
        
        /*แสดงข้อมูล*/
        
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
        //e[0].elevatorGo(b.getController());
        //b.getController().setCarCallList();
        //b.getController().showCarCallList();
        
        for(int i=0;i<b.getNumElevator();i++){
            try{
                t[i].start();
                Thread.sleep(1000);
            }catch(InterruptedException a){
                System.out.println("Interrupted! "+a);
            }
        }
        Time tt = new Time(b.getController());
        Thread time = new Thread(tt);
        //Thread controller = new Thread(b.getController());
        time.start();
        //controller.start();
        GeneratePassenger gen = new GeneratePassenger(b.getController(),tt);
        Thread generate = new Thread(gen);
        generate.start();
        
        
        
    }
    
}
