package state;

import model.Elevator;
import model.Request;

public class MaintenanceState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        System.out.println("Elevator under maintenance. Requests cannot be processed.");
    }

    @Override
    public void move(Elevator elevator) {
        System.out.println("Elevator is currently under maintenance.");
    }

    @Override
    public String getStateName() {
        return "Maintenance";
    }
}