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
    private Thread threadTime;
    
    public Time(){
        threadTime = new Thread(this);
    }
    
    public void start(){
        threadTime.start();
    }
    
    public static int getMinutes(){
        return minutes;
    }
    
    private static void timerTick(){
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
    }
    
    @Override
    public void run() {
        
        
        
        
    }
    
}
