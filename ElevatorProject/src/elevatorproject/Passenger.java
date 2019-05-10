package elevatorproject;
public class Passenger {
    private Call floorCall;
    private Call carCall;
    private String ID;
    
    public Passenger(Call floorCall, Call carCall, String ID){
        this.floorCall = floorCall;
        this.carCall = carCall;
        this.ID = ID;
    }
    
    public Call getFloorCall(){
        return floorCall;
    }
    
    public Call getCarCall(){
        return carCall;
    }
    
    public String getID(){
        return ID;
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
    
    /*public void setCurrnetFloor(int cur){
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
    /*}
    
    public void removeFloorCall(String ID){
        /*ผู้โดยสารเข้าลิฟต์ ลบ floorCall*/
    /*}*/
    
    
}
