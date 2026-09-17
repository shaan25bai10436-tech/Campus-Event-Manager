# Campus-Event-Manager
A Core Java console application for managing campus events, participant registrations, event points, and house-wise leaderboards.

## Features
- Participant management
- Event management
- Registration with capacity and duplicate checks
- Points assignment
- House-wise leaderboard
- CSV file storage
- Input validation and exception handling

## Java Concepts Demonstrated
- Classes and objects
- Encapsulation
- Collections (`Map`, `List`)
- Streams and lambda expressions
- Exception handling
- File I/O
- Modular package structure
- Input validation

## Requirements
- JDK 17 or newer
- Command Prompt / Terminal

## Run
From the project root:

```bash
javac -d out src/com/campus/eventmanager/model/*.java src/com/campus/eventmanager/service/*.java src/com/campus/eventmanager/util/*.java src/com/campus/eventmanager/Main.java
java -cp out com.campus.eventmanager.Main
```

## Testing
Try:
1. Add a participant.
2. Add an event.
3. Register the participant.
4. Register the same participant again to test validation.
5. Assign points.
6. View the house leaderboard.
7. Save data and inspect the `data` folder.
