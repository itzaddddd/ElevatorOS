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
    private int minutes;
    private int hours;
    private GroupElevatorController controller;
    
    public Time(GroupElevatorController g){
        this.controller = g;
        this.hours = 7;
        this.minutes = 0;
    }
    
    public GroupElevatorController getController(){
        return this.controller;
    }
    
    public void setTime(int h, int m){
        this.minutes = m;
        this.hours = h;
    }

    public int getMinutes(){
        return minutes;
    }
    
    public int getHours(){
        return hours;
    }
    
    public void timerTick(){
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
        while(this.getHours()<19){
            try{
                this.timerTick();
                Thread.sleep(500);
            }catch(InterruptedException a){
                System.out.println(a);
            }
        }
    }
    
}
