package state;

import model.Elevator;
import model.Request;

public class DoorOpenState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.addRequest(request);
        System.out.println("Request added while door is open.");
    }

    @Override
    public void move(Elevator elevator) {

        System.out.println("Doors opening at floor " + elevator.getCurrentFloor());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Passengers entering/exiting...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Doors closing.");

        if (!elevator.getPriorityRequests().isEmpty()) {

            int nextFloor = elevator.getPriorityRequests().peek().getFloorNumber();

            if (nextFloor > elevator.getCurrentFloor()) {
                elevator.setCurrentState(new MovingUpState());
            } else {
                elevator.setCurrentState(new MovingDownState());
            }

        } 
        else if (!elevator.getNormalRequests().isEmpty()) {

            int nextFloor = elevator.getNormalRequests().peek().getFloorNumber();

            if (nextFloor > elevator.getCurrentFloor()) {
                elevator.setCurrentState(new MovingUpState());
            } else {
                elevator.setCurrentState(new MovingDownState());
            }

        } 
        else {

            elevator.setCurrentState(new IdleState());
        }
    }

    @Override
    public String getStateName() {
        return "Door Open";
    }
}