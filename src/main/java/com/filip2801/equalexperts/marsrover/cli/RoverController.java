package com.filip2801.equalexperts.marsrover.cli;

import com.filip2801.equalexperts.marsrover.domain.MarsRover;
import com.filip2801.equalexperts.marsrover.domain.Position;

import java.util.List;

class RoverController {

    private final CliService cliService;
    private final UserInputService inputInterpreter;

    RoverController(CliService cliService) {
        this.cliService = cliService;
        this.inputInterpreter = new UserInputService(cliService);
    }

    void doMission() {
        print("Hello. It's Mars Rover mission control.");

        Position startingPosition = getStartingPosition();
        var marsRover = new MarsRover(startingPosition);

        List<MarsRoverCommand> commands = getCommands();
        commands.forEach(command -> command.accept(marsRover));
        printLastPosition(marsRover);

        print("Goodbye. See you on your next mission!!!");
    }

    private List<MarsRoverCommand> getCommands() {
        print("Available commands:");
        print("F -> Move forward on current heading");
        print("B -> Move backwards on current heading");
        print("L -> Rotate left by 90 degrees");
        print("R -> Rotate right by 90 degrees");
        print("Command example: FLFRB");
        print("Type commands for rover and press Enter.");

        return inputInterpreter.readCommands();
    }

    private Position getStartingPosition() {
        print("Type vehicle position and press Enter. Input format example: (1, 2, NORTH)");
        return inputInterpreter.readPosition();
    }

    private void printLastPosition(MarsRover marsRover) {
        var coordinates = marsRover.getPosition().getCoordinates();
        print("Mars rover moved to position (" +
            coordinates.getX() +
            ", " +
            coordinates.getY() +
            ", " +
            marsRover.getPosition().getDirection() +
            ")");
    }

    private void print(String s) {
        cliService.print(s);
    }

}
