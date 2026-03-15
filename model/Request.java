package model;

public class Request {

    private int floorNumber;
    private boolean priority;

    public Request(int floorNumber, boolean priority) {
        this.floorNumber = floorNumber;
        this.priority = priority;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Request{floor=" + floorNumber + ", priority=" + priority + "}";
    }
}