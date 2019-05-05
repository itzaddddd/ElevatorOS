package elevatorproject;
public class Passenger {
    private String ID;
    private int destinationFloor;
    private int currentFloor;
    
    int rand;
    public Passenger(String ID,int cur, int des){
        this.ID = ID;
        this.currentFloor = cur;
        this.destinationFloor = des;
    }
    
    public void walkLeft(){
        /*เดินไปทางซ้าย*/
    }
    
    public void walkRight(){
        /*เดินไปทางขวา*/
    }
    
    public void walkIn(){
        /*เดินเข้าลิฟต์*/
    }
    
    public void walkOut(){
        /*เดินออกจากลิฟต์*/
    }
    
    public void walkStop(){
        /*หยุดเดิน*/
    }
    
    public void setCurrnetFloor(int cur){
        this.currentFloor = cur;
    }
    
    public int getCurrentFloor(){
        return currentFloor;
    }
    
    public void setDestinationFloor(int des){
        this.destinationFloor = des;
    }
    
    public int getDestinationFloor(){
        return destinationFloor;
    }
    
    public void addFloorCall(){
        /*ผู้โดยสารกดลิฟต์ คำสั่งเพิ่มไปใน floorCall*/
    }
    
    public void removeFloorCall(String ID){
        /*ผู้โดยสารเข้าลิฟต์ ลบ floorCall*/
    }
}
