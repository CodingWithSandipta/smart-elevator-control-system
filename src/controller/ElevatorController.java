package controller;

import exception.InvalidFloorException;
import model.Elevator;
import model.Request;
import state.MovingDownState;
import state.MovingUpState;

public class ElevatorController {

    private Elevator elevator;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    public void submitRequest(Request request) {

        try {

            if (request.getFloorNumber() > elevator.getMaxFloor() || request.getFloorNumber() < 1) {
                throw new InvalidFloorException("Invalid floor requested.");
            }

            System.out.println("New request received for floor " + request.getFloorNumber());

            elevator.getCurrentState().handleRequest(elevator, request);

            decideDirection();

        } catch (InvalidFloorException e) {
            System.out.println(e.getMessage());
        }
    }

    private void decideDirection() {

        String state = elevator.getCurrentState().getStateName();

        // Do not override Emergency or Maintenance modes
        if (state.equals("Emergency") || state.equals("Maintenance")) {
            return;
        }

        if (!elevator.getPriorityRequests().isEmpty()) {

            int target = elevator.getPriorityRequests().peek().getFloorNumber();

            if (target > elevator.getCurrentFloor()) {
                elevator.setCurrentState(new MovingUpState());
            } else {
                elevator.setCurrentState(new MovingDownState());
            }

        } else if (!elevator.getNormalRequests().isEmpty()) {

            int target = elevator.getNormalRequests().peek().getFloorNumber();

            if (target > elevator.getCurrentFloor()) {
                elevator.setCurrentState(new MovingUpState());
            } else {
                elevator.setCurrentState(new MovingDownState());
            }
        }
    }

    public void runElevator() {

        while (!elevator.getCurrentState().getStateName().equals("Idle")) {
            elevator.getCurrentState().move(elevator);
        }
    }
}
