package com.filip2801.equalexperts.marsrover.cli;

import com.filip2801.equalexperts.marsrover.domain.CannotGoFurtherException;
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
        move(marsRover);

        printLastPosition(marsRover);
        print("Goodbye. See you on your next mission!!!");
    }

    private Position getStartingPosition() {
        print("Type vehicle position and press Enter. Input format example: (1, 2, NORTH)");
        return inputInterpreter.readPosition();
    }

    private void move(MarsRover marsRover) {
        List<MarsRoverCommand> commands = readCommands();
        try {
            commands.forEach(command -> command.accept(marsRover));
        } catch (CannotGoFurtherException e) {
            print("Mars rover cannot go further");
        }
    }

    private List<MarsRoverCommand> readCommands() {
        print("Available commands:");
        print("F -> Move forward on current heading");
        print("B -> Move backwards on current heading");
        print("L -> Rotate left by 90 degrees");
        print("R -> Rotate right by 90 degrees");
        print("Command example: FLFRB");
        print("Type commands for rover and press Enter.");

        return inputInterpreter.readCommands();
    }

    private void printLastPosition(MarsRover marsRover) {
        print("Mars rover moved to position " +
            marsRover.getPosition().getCoordinates() +
            " " +
            marsRover.getPosition().getDirection());
    }

    private void print(String s) {
        cliService.print(s);
    }

}
