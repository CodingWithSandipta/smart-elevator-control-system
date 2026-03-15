package ui;

import controller.ElevatorController;
import model.Elevator;
import model.Request;
import state.EmergencyState;

import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {

        Elevator elevator = new Elevator(10, 600);
        ElevatorController controller = new ElevatorController(elevator);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Smart Elevator System ---");
            System.out.println("1. Request Floor");
            System.out.println("2. Request Priority Floor");
            System.out.println("3. Add Passenger Weight");
            System.out.println("4. Trigger Emergency");
            System.out.println("5. Run Elevator");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter floor number: ");
                    int floor = scanner.nextInt();
                    controller.submitRequest(new Request(floor, false));
                    break;

                case 2:
                    System.out.print("Enter priority floor: ");
                    int pFloor = scanner.nextInt();
                    controller.submitRequest(new Request(pFloor, true));
                    break;

                case 3:
                    System.out.print("Enter passenger weight: ");
                    double weight = scanner.nextDouble();

                    elevator.setCurrentWeight(
                        elevator.getCurrentWeight() + weight
                    );

                    System.out.println("Current weight: " + elevator.getCurrentWeight());
                    break;

                case 4:
                    elevator.setCurrentState(new EmergencyState());
                    System.out.println("Emergency mode activated!");
                    break;

                case 5:
                    controller.runElevator();
                    break;

                case 6:
                    System.out.println("System shutting down.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}