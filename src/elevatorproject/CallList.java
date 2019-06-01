/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;

import java.util.ArrayList;

/**
 *
 * @author isara
 */
public class CallList {
    private int type; // 0-floor List, 1-call List
    private int numOffloor; // กำหนดหมายเลขชั้น
    private int numOfPop; // กำหนดจำนวนคน
    private ArrayList<Passenger> passengerList;
    
    public CallList(){
        this.type = 0;
        this.numOffloor = 1;
        this.numOfPop = 0;
        this.passengerList = new ArrayList();
    }
    
    public CallList(int t,int f,int p, ArrayList<Passenger> l){
        this.type = t;
        this.numOffloor = f;
        this.numOfPop = p;
        this.passengerList = l;
    }
    
    public int getType(){
        return this.type;
    }
    
    public int getNumOfFloor(){
        return this.numOffloor;
    }
    
    public int getNumOfPop(){
        return this.numOfPop;
    }
    
    public ArrayList getPassengerList(){
        return this.passengerList;
    }
    
    public void setType(int t){
        this.type = t;
    }
    
    public void setNumOfFloor(int f){
        this.numOffloor = f;
    }
    
    public void setNumOfPop(int p){
        this.numOfPop = p;
    }
    
    public void setPassengerList(ArrayList<Passenger> l){
        this.passengerList = l;
    }
    
    
    
    
}
