package com.campus.eventmanager;

import com.campus.eventmanager.model.Event;
import com.campus.eventmanager.model.Participant;
import com.campus.eventmanager.service.EventManager;
import com.campus.eventmanager.util.FileStorage;
import com.campus.eventmanager.util.InputValidator;

import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final EventManager manager = new EventManager();

    public static void main(String[] args) {
        seedData();
        while (true) {
            System.out.println("\total===== CAMPUS EVENT MANAGER =====");
            System.out.println("1. Add Participant");
            System.out.println("2. Add Event");
            System.out.println("3. Register Participant");
            System.out.println("4. Assign Points");
            System.out.println("5. View Participants");
            System.out.println("6. View Events");
            System.out.println("7. View House Leaderboard");
            System.out.println("8. Save Data");
            System.out.println("9. Exit");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addParticipant();
                    case 2 -> addEvent();
                    case 3 -> register();
                    case 4 -> assignPoints();
                    case 5 -> manager.getParticipants().forEach(System.out::println);
                    case 6 -> manager.getEvents().forEach(System.out::println);
                    case 7 -> leaderboard();
                    case 8 -> save();
                    case 9 -> { System.out.println("Goodbye!"); return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void seedData() {
        manager.addParticipant(new Participant("P001", "Aarav", "aarav@example.com", "Lions"));
        manager.addParticipant(new Participant("P002", "Riya", "riya@example.com", "Tigers"));
        manager.addEvent(new Event("E001", "Badminton", 40));
        manager.addEvent(new Event("E002", "Chess", 30));
        manager.register("P001", "E001");
        manager.register("P002", "E002");
        manager.assignPoints("P001", "E001", 6);
        manager.assignPoints("P002", "E002", 4);
    }

    private static void addParticipant() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("House: "); String house = sc.nextLine();
        InputValidator.requireNonBlank(id, "ID");
        InputValidator.requireNonBlank(name, "Name");
        InputValidator.requireEmail(email);
        InputValidator.requireNonBlank(house, "House");
        manager.addParticipant(new Participant(id, name, email, house));
        System.out.println("Participant added.");
    }

    private static void addEvent() {
        System.out.print("Event ID: "); String id = sc.nextLine();
        System.out.print("Event name: "); String name = sc.nextLine();
        System.out.print("Capacity: "); int cap = InputValidator.positiveInt(sc.nextLine(), "Capacity");
        manager.addEvent(new Event(id, name, cap));
        System.out.println("Event added.");
    }

    private static void register() {
        System.out.print("Participant ID: "); String pid = sc.nextLine();
        System.out.print("Event ID: "); String eid = sc.nextLine();
        manager.register(pid, eid);
        System.out.println("Registration successful.");
    }

    private static void assignPoints() {
        System.out.print("Participant ID: "); String pid = sc.nextLine();
        System.out.print("Event ID: "); String eid = sc.nextLine();
        System.out.print("Points: "); int pts = Integer.parseInt(sc.nextLine());
        manager.assignPoints(pid, eid, pts);
        System.out.println("Points updated.");
    }

    private static void leaderboard() {
        manager.houseLeaderboard().entrySet().stream()
                .sorted((a,b) -> Integer.compare(b.getValue(), a.getValue()))
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue() + " points"));
    }

    private static void save() {
        try {
            FileStorage.saveParticipants(manager.getParticipants(), "data/participants.csv");
            FileStorage.saveEvents(manager.getEvents(), "data/events.csv");
            FileStorage.saveRegistrations(manager.getRegistrations(), "data/registrations.csv");
            System.out.println("Data saved to data/.");
        } catch (Exception e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }
}
