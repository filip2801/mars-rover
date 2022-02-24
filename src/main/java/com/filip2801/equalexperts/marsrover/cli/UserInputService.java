package com.filip2801.equalexperts.marsrover.cli;

import com.filip2801.equalexperts.marsrover.domain.Coordinates;
import com.filip2801.equalexperts.marsrover.domain.Direction;
import com.filip2801.equalexperts.marsrover.domain.MarsRover;
import com.filip2801.equalexperts.marsrover.domain.Position;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserInputService {

    private static final Pattern POSITION_PATTERN = Pattern.compile("\\(([-]?\\d+), ([-]?\\d+), (NORTH|SOUTH|WEST|EAST)\\)");
    private static final Map<String, MarsRoverCommand> COMMANDS_DEFINITIONS = Map.of(
        "F", MarsRover::moveForward,
        "B", MarsRover::moveBackwards,
        "L", MarsRover::rotateLeftBy90Degrees,
        "R", MarsRover::rotateRightBy90Degrees);

    private final CliService cliService;

    UserInputService(CliService cliService) {
        this.cliService = cliService;
    }

    List<MarsRoverCommand> readCommands() {
        while (true) {
            var commandsLine = cliService.readInput();
            String[] commands = commandsLine.split("");

            var readCommands = Stream.of(commands)
                .map(COMMANDS_DEFINITIONS::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            if (readCommands.size() == commandsLine.length()) {
                return readCommands;
            } else {
                cliService.print("Incorrect input. Try again.");
            }
        }
    }

    Position readPosition() {
        while (true) {
            Optional<Position> possiblePosition = tryToReadPosition();

            if (possiblePosition.isPresent()) {
                return possiblePosition.get();
            } else {
                cliService.print("Incorrect input. Try again.");
            }
        }
    }

    private Optional<Position> tryToReadPosition() {
        String input = cliService.readInput();
        Matcher matcher = POSITION_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return Optional.empty();
        }

        try {
            var x = Integer.parseInt(matcher.group(1));
            var y = Integer.parseInt(matcher.group(2));
            var direction = Direction.valueOf(matcher.group(3));

            return Optional.of(new Position(new Coordinates(x, y), direction));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
