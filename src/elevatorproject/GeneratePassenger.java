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
    private ArrayList<Passenger> passengerList;
    private GroupElevatorController controller;
    
    public GeneratePassenger(GroupElevatorController g, Time t){
        this.time = t;
        this.passengerList = new ArrayList();
        this.controller = g;
        
    }
    
    public GroupElevatorController getController(){
        return controller;
    }
    
    public ArrayList<Passenger> getPassengerList(){
        return passengerList; 
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

            Call floorCall = new Call(1, 1, direction, ID); 
        
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
            this.getPassengerList().add(new Passenger(floorCall, carCall, ID));
            System.out.println("ID : "+ID+"\nFloorCall : "+randFloor+"\nExitFloor : "+exitFloor+"\nDirection : "+direction+"\n"); 
            // Create a Passenger object and add it the to the passengers array 
        }
    }

    @Override
    public void run() {
        this.generate(30);
    }
    
}
