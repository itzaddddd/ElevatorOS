/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;

import javax.swing.Timer;

/**
 *
 * @author isara
 */
public class Time implements Runnable{
    private static int minutes = 0;
    private static int hours = 0;
    private GroupElevatorController controller;
    
    public Time(GroupElevatorController g){
        this.controller = g;
    }
    
    public GroupElevatorController getController(){
        return this.controller;
    }

    public int getMinutes(){
        return minutes;
    }
    
    public int getHours(){
        return hours;
    }
    
    private void timerTick(){
        minutes++;
        if(minutes == 60){
            minutes =0;
            hours++;
        }
        if(hours == 24){
            hours = 0;
        }
        
        if(hours<10)System.out.print("0");
        System.out.print(hours+":");
        if(minutes<10)System.out.print("0");
        System.out.print(minutes);
        System.out.print("\n");
    }
    
    @Override
    public void run() {
        while(getHours()<23){
            try{
                timerTick();
                Thread.sleep(100);
            }catch(InterruptedException a){
                System.out.println(a);
            }
        }
    }
    
}
