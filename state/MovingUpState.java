package state;

import model.Elevator;
import model.Request;

public class MovingUpState implements ElevatorState {

    @Override
    public void handleRequest(Elevator elevator, Request request) {
        elevator.addRequest(request);
        System.out.println("Request added while elevator moving up: Floor " + request.getFloorNumber());
    }

    @Override
    public void move(Elevator elevator) {

        if (!elevator.getPriorityRequests().isEmpty()) {

            Request req = elevator.getPriorityRequests().poll();
            int targetFloor = req.getFloorNumber();

            while (elevator.getCurrentFloor() < targetFloor) {
                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                System.out.println("Moving up... Current floor: " + elevator.getCurrentFloor());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Reached priority floor " + targetFloor);

        } else if (!elevator.getNormalRequests().isEmpty()) {

            Request req = elevator.getNormalRequests().poll();
            int targetFloor = req.getFloorNumber();

            while (elevator.getCurrentFloor() < targetFloor) {
                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                System.out.println("Moving up... Current floor: " + elevator.getCurrentFloor());

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
    }

    @Override
    public String getStateName() {
        return "Moving Up";
    }
}