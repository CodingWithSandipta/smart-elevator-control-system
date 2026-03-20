package state;

import model.Elevator;
import model.Request;

public interface ElevatorState {

    void handleRequest(Elevator elevator, Request request);

    void move(Elevator elevator);

    String getStateName();
}