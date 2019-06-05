/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author isara
 */
public class GeneratePassenger implements Runnable {
    private Time time;
    private Passenger passenger;
    private GroupElevatorController controller;
    private double lambda;
    private int numberOfPassenger;
    private int randFloor;
    private int exitFloor;
    
    public GeneratePassenger(GroupElevatorController g, Time t){
        this.time = t;
        this.controller = g;
        this.lambda = 0;
        this.numberOfPassenger = 0;
        
    }
    
    public Time getTime(){
        return time;
    }
    
    public double getLambda(){
        return lambda;
    }
    
    public void setLambda(double lambda){
        this.lambda = lambda;
    }
    
    public GroupElevatorController getController(){
        return controller;
    }
    
    public static int getPoisson(double lambda){
      double L = Math.exp(-lambda);
      double p = 1.0;
      int k = 0;
      do {
        k++;
        p *= Math.random();
      } while (p > L);

      return k - 1;
    }
    
    public void passengerFormat() {
        
        Time t = this.getTime();
        int h = t.getHours();
        int m = t.getMinutes();
        int max = this.getController().getFloor().length;
        // ตั้งแต่ 7.30 ถึง  8.30 คนจะมาที่ชั้น1 เท่านั้น //เปิดตึก
        if(h >= 7 && m >=30) {
            lambda = 2.5;
            numberOfPassenger = getPoisson(lambda);
            //กำหนดว่า คนจะมาจากชั้น 1 เท่านั้น
                this.randFloor = 1;
                generateAtFirst(numberOfPassenger);
        }
        
        //8.30-10.30 สุ่มคนกระจายทุกชั้นเท่ากัน
        else if((h>=8&&m>=30)&&(h<=10&&m<=30)) {
            for (int numOfFloor =1 ; numOfFloor <= max ; numOfFloor++) {
                lambda = 4;
                numberOfPassenger = getPoisson(lambda);
                generate(numberOfPassenger);
                    
            }
        }
        //10.30 - 13.00 สุ่มคนกระจายทุกชั้น แต่ชั้นบนแลมด้าจะสูง เพราะคนจะลงมากินข้าว
        else if((h>=10&&m>=30)&&(h<=13&&m<=30)) {
            for (int numOfFloor =1 ; numOfFloor <= max ; numOfFloor++) {
                if((max-numOfFloor)<=3) {
                    lambda = 6;
                } else
                    lambda = 4;
                
                numberOfPassenger = getPoisson(lambda);
                generate(numberOfPassenger);
            }
        }
        //13.30-16.30 กระจายทุกชั้น
        else if((h>=13&&m>=30)&&(h<=16&&m<=30)) {
            for (int numOfFloor =1 ; numOfFloor <= max ; numOfFloor++) {
                lambda = 4;
                numberOfPassenger = getPoisson(lambda);
                generate(numberOfPassenger);
            }
        }
        //16.30-19.00 ลงมาที่ชั้น1ทั้งหมด lambdaชั้นบนจะสูง
        else if((h>=16&&m>=30)&&(h<=19&&m==0)) {
            for (int numOfFloor =1 ; numOfFloor <= max ; numOfFloor++) {
                if(numOfFloor==1||(max-numOfFloor)>=3) {
                    lambda = 5;
                } else
                    lambda = 3;
                
                numberOfPassenger = getPoisson(lambda);
                generateAtMax(numberOfPassenger);
            }
        }    
        else {
            lambda = 0;
            numberOfPassenger = 0;
        }
            

    }
    
    public void generate(int num){
        for(int i=1;i<=num;i++){
            /* สร้างคนในชั้น */
            Random rand = new Random();
            int nFloor = this.getController().getFloor().length;

            String ID = UUID.randomUUID().toString(); // Create passenger ID
        
            int randFloor = (rand.nextInt(nFloor))+1;
            //System.out.println(this.numOfFloor);
            // If randFloor is the top floor, then the direction is always down
            // Else if randFloor is the main floor, then the direction is always up
            // Else the direction is chosen randomly
            int direction = 0;
            if(randFloor == 1) {
                direction = 1; // Direction is up
            }else if(randFloor == (nFloor)){
                direction = 0; // Direction is down
            }else {
                direction = rand.nextInt(2); // Randomly select direction
            }

            Call floorCall = new Call(1, randFloor, direction, ID); 
        
            // Randomly generate an exitCall, based on randFloor
            int exitFloor = 1;
            if(direction == 1){
                // Generate random number, until it's greater than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor <= randFloor){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }else{

                // Generate random number, until it's smaller than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor >= (randFloor)+1){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }

            Call carCall = new Call(0, exitFloor, direction, ID);
            
            Passenger p = new Passenger(floorCall, carCall, ID);
            
            Floor[] f = this.getController().getFloor();
            f[p.getFloorCall().getCallFloor()-1].getPassengerQueue().add(p);
            System.out.println("ID : "+ID+" FloorCall : "+randFloor+" ExitFloor : "+exitFloor+" Direction : "+direction+"\n");
            // Create a Passenger object and add it the to the passengers array
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException a){
                System.out.println(a);
            }
        }
    }
    
    public void generateAtFirst(int num){
        for(int i=1;i<=num;i++){
            /* สร้างคนในชั้น */
            Random rand = new Random();
            int nFloor = this.getController().getFloor().length;

            String ID = UUID.randomUUID().toString(); // Create passenger ID
        
            int randFloor = (rand.nextInt(nFloor-3)+3)+1;
            //System.out.println(this.numOfFloor);
            // If randFloor is the top floor, then the direction is always down
            // Else if randFloor is the main floor, then the direction is always up
            // Else the direction is chosen randomly
            int direction = 0;
            if(randFloor == 1) {
                direction = 1; // Direction is up
            }else if(randFloor == (nFloor)){
                direction = 0; // Direction is down
            }else {
                direction = rand.nextInt(2); // Randomly select direction
            }

            Call floorCall = new Call(1, randFloor, direction, ID); 
        
            // Randomly generate an exitCall, based on randFloor
            int exitFloor = 1;
            if(direction == 1){
                // Generate random number, until it's greater than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor <= randFloor){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }else{

                // Generate random number, until it's smaller than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor >= (randFloor)+1){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }

            Call carCall = new Call(0, exitFloor, direction, ID);
            
            Passenger p = new Passenger(floorCall, carCall, ID);
            
            Floor[] f = this.getController().getFloor();
            f[p.getFloorCall().getCallFloor()-1].getPassengerQueue().add(p);
            System.out.println("ID : "+ID+" FloorCall : "+randFloor+" ExitFloor : "+exitFloor+" Direction : "+direction+"\n");
            System.out.println("f[0] : "+f[0].getPassengerQueue().size());
            // Create a Passenger object and add it the to the passengers array
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException a){
                System.out.println(a);
            }
        }
    }
    
    public void generateAtMax(int num){
        for(int i=1;i<=num;i++){
            /* สร้างคนในชั้น */
            Random rand = new Random();
            int nFloor = this.getController().getFloor().length;

            String ID = UUID.randomUUID().toString(); // Create passenger ID
        
            int randFloor = (rand.nextInt(nFloor)-3)+1;
            //System.out.println(this.numOfFloor);
            // If randFloor is the top floor, then the direction is always down
            // Else if randFloor is the main floor, then the direction is always up
            // Else the direction is chosen randomly
            int direction = 0;
            if(randFloor == 1) {
                direction = 1; // Direction is up
            }else if(randFloor == (nFloor)){
                direction = 0; // Direction is down
            }else {
                direction = rand.nextInt(2); // Randomly select direction
            }

            Call floorCall = new Call(1, randFloor, direction, ID); 
        
            // Randomly generate an exitCall, based on randFloor
            int exitFloor = 1;
            if(direction == 1){
                // Generate random number, until it's greater than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor <= randFloor){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }else{

                // Generate random number, until it's smaller than randFloor
                exitFloor = (rand.nextInt(nFloor))+1;
                while (exitFloor >= (randFloor)+1){
                    exitFloor = (rand.nextInt(nFloor))+1;
                }
            }

            Call carCall = new Call(0, exitFloor, direction, ID);
            
            Passenger p = new Passenger(floorCall, carCall, ID);
            
            Floor[] f = this.getController().getFloor();
            f[p.getFloorCall().getCallFloor()-1].getPassengerQueue().add(p);
            System.out.println("ID : "+ID+" FloorCall : "+randFloor+" ExitFloor : "+exitFloor+" Direction : "+direction+"\n");
            System.out.println("f[0] : "+f[0].getPassengerQueue().size());
            // Create a Passenger object and add it the to the passengers array
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException a){
                System.out.println(a);
            }
        }
    }
    
    @Override
    public void run() {
        while(this.getTime().getHours()<19){
            this.passengerFormat();
        }
    }
    
}
