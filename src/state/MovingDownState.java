package state;

import model.Elevator;
import model.Request;
import exception.OverloadException;
import state.IdleState;

public class MovingDownState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.addRequest(request);
        System.out.println("Request added while elevator moving down: Floor " + request.getFloorNumber());
    }

    @Override
    public void move(Elevator elevator) {

        try {

            if (elevator.isOverloaded()) {
                throw new OverloadException("Elevator overloaded! Cannot move.");
            }

            if (!elevator.getPriorityRequests().isEmpty()) {

                Request req = elevator.getPriorityRequests().poll();
                int targetFloor = req.getFloorNumber();

                while (elevator.getCurrentFloor() > targetFloor) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    System.out.println("Moving down... Current floor: " + elevator.getCurrentFloor());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Reached priority floor " + targetFloor);
                elevator.setCurrentState(new DoorOpenState());

            } else if (!elevator.getNormalRequests().isEmpty()) {

                Request req = elevator.getNormalRequests().poll();
                int targetFloor = req.getFloorNumber();

                while (elevator.getCurrentFloor() > targetFloor) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    System.out.println("Moving down... Current floor: " + elevator.getCurrentFloor());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Reached floor " + targetFloor);
                elevator.setCurrentState(new DoorOpenState());

            } else {
                elevator.setCurrentState(new IdleState());
                System.out.println("No more requests. Elevator idle.");
            }

        } catch (OverloadException e) {
            System.out.println(e.getMessage());
            System.out.println("Please reduce weight and try again.");
            elevator.setCurrentState(new IdleState());
        }
    }

    @Override
    public String getStateName() {
        return "Moving Down";
    }
}