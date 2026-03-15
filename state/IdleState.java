package state;

import model.Elevator;
import model.Request;

public class IdleState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.addRequest(request);
        System.out.println("Request added while elevator is idle.");
    }

    @Override
    public void move(Elevator elevator) {
        System.out.println("Elevator is idle at floor " + elevator.getCurrentFloor());
    }

    @Override
    public String getStateName() {
        return "Idle";
    }
}