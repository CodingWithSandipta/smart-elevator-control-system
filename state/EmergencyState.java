package state;

import model.Elevator;
import model.Request;

public class EmergencyState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        System.out.println("Emergency mode active. Requests cannot be processed.");
    }

    @Override
    public void move(Elevator elevator) {
        System.out.println("Emergency! Elevator stopped at floor " + elevator.getCurrentFloor());
    }

    @Override
    public String getStateName() {
        return "Emergency";
    }
}