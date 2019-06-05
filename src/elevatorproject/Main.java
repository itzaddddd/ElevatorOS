/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;

import java.time.LocalTime;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*สร้างอาคาร*/
        Building b = new Building(6,3,LocalTime.of(7, 0));
        
        /*สร้างชั้น*/
        Floor[] f = b.getFloor();
       
        for(int i=0;i<b.getNumFloor();i++){
            f[i] = new Floor(i+1);
        }
        /*สร้างลิฟต์และthreadของลิฟต์*/
        Elevator[] e = b.getElevator();
        Thread[] t = new Thread[b.getNumElevator()]; //สร้าง thread ให้ลิฟต์แต่ละตัว
        for(int i=0;i<b.getNumElevator();i++){
            e[i] = new Elevator(i+1,b.getController());
            t[i] = new Thread(e[i]);
        }
        
        
        Time tt = new Time(b.getController());
        GeneratePassenger gen = new GeneratePassenger(b.getController(),tt);
        
        Thread time = new Thread(tt);
        Thread generate = new Thread(gen);
        Thread gControl = new Thread(b.getController());
        
        gControl.start();
        time.start();
        generate.start();
        try{
            Thread.sleep(100);
        }catch(InterruptedException a){
            System.out.println(a);
        }
    }
    
}
