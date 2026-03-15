package model;

import state.ElevatorState;
import state.IdleState;

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

        this.currentState = new IdleState();

        normalRequests = new LinkedList<>();
        priorityRequests = new LinkedList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double weight) {
        this.currentWeight = weight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public Queue<Request> getNormalRequests() {
        return normalRequests;
    }

    public Queue<Request> getPriorityRequests() {
        return priorityRequests;
    }

    public ElevatorState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ElevatorState state) {
        this.currentState = state;
    }

    public void addRequest(Request request) {
        if (request.isPriority()) {
            priorityRequests.add(request);
        } else {
            normalRequests.add(request);
        }
    }
}