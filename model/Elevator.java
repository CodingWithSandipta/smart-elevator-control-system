package model;

import state.ElevatorState;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {

    private int currentFloor;
    private int maxFloor;
    private double currentWeight;
    private double maxWeight;

    private ElevatorState currentState;

    private Queue<Request> normalRequests;
    private Queue<Request> priorityRequests;

    public Elevator(int maxFloor, double maxWeight) {
        this.currentFloor = 1;
        this.maxFloor = maxFloor;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;

        normalRequests = new LinkedList<>();
        priorityRequests = new LinkedList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public Queue<Request> getNormalRequests() {
        return normalRequests;
    }

    public Queue<Request> getPriorityRequests() {
        return priorityRequests;
    }

    public void addRequest(Request request) {
        if (request.isPriority()) {
            priorityRequests.add(request);
        } else {
            normalRequests.add(request);
        }
    }
}
