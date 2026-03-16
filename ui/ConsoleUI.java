package ui;

import controller.ElevatorController;
import model.Elevator;
import model.Request;
import state.EmergencyState;
import state.MaintenanceState;

import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {

        Elevator elevator = new Elevator(10, 600);
        ElevatorController controller = new ElevatorController(elevator);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Smart Elevator System ---");
            System.out.println("Current Floor: " + elevator.getCurrentFloor());
            System.out.println("Current State: " + elevator.getCurrentState().getStateName());

            System.out.println("1. Request Floor");
            System.out.println("2. Request Priority Floor");
            System.out.println("3. Add Passenger Weight");
            System.out.println("4. Trigger Emergency");
            System.out.println("5. Run Elevator");
            System.out.println("6. Exit");
            System.out.println("7. Enable Maintenance Mode");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter floor number: ");
                        int floor = scanner.nextInt();

                        if (floor < 1 || floor > elevator.getMaxFloor()) {
                            throw new RuntimeException("Invalid floor selected!");
                        }

                        controller.submitRequest(new Request(floor, false));

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter priority floor: ");
                        int pFloor = scanner.nextInt();

                        if (pFloor < 1 || pFloor > elevator.getMaxFloor()) {
                            throw new RuntimeException("Invalid floor selected!");
                        }

                        controller.submitRequest(new Request(pFloor, true));

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter passenger weight: ");
                    double weight = scanner.nextDouble();

                    double newWeight = elevator.getCurrentWeight() + weight;

                    if (newWeight > elevator.getMaxWeight()) {
                        System.out.println("Warning: Elevator overloaded!");
                    }

                    elevator.setCurrentWeight(newWeight);

                    System.out.println("Current weight: " + elevator.getCurrentWeight());
                    break;

                case 4:
                    elevator.setCurrentState(new EmergencyState());
                    System.out.println("Emergency mode activated!");
                    break;

                case 5:

                    if (elevator.getCurrentState().getStateName().equals("Emergency") ||
                        elevator.getCurrentState().getStateName().equals("Maintenance")) {

                        System.out.println("Elevator cannot run in current mode.");

                    } else {
                        controller.runElevator();
                    }

                    break;

                case 6:
                    System.out.println("System shutting down.");
                    return;

                case 7:
                    elevator.setCurrentState(new MaintenanceState());
                    System.out.println("Maintenance mode activated.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}