package com.filip2801.equalexperts.marsrover.domain;

import static java.util.Objects.requireNonNull;

public class Position {

    private final Coordinates coordinates;
    private final Direction direction;

    public Position(Coordinates coordinates, Direction direction) {
        this.coordinates = requireNonNull(coordinates);
        this.direction = requireNonNull(direction);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

}
