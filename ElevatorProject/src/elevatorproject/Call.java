/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorproject;
class Call {
    private int type; // 0-floorCall, 1-carCall
    private int floor; // floorCall -> ชั้นที่อยู่,carCall -> ชั้นที่จะไป
    private int direction; // 1-ขึ้น, 0-ลง
    private String ID;
    
    public Call(int type, int floor, int direction, String ID){
        this.type = type;
        this.floor = floor;
        this.direction = direction;
        this.ID = ID;
    }
    
    public void setCallType(int type){
        this.type = type;
    }
    
    public int getCallType(){
        return type;
    }
    
    public void setCallFloor(int floor){
        this.floor = floor;
    }
    
    public int getCallFloor(){
        return floor;
    }
    
    public void setCallDirection(int direction){
        this.direction = direction;
    }
    
    public int getCallDirection(){
        return direction;
    }
    
    public void setCallID(String ID){
        this.ID = ID;
    }
    
    public String getCallID(){
        return ID;
    }
    
    
    
}
