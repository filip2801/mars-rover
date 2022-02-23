package com.filip2801.equalexperts.marsrover.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(coordinates, position.coordinates) && direction == position.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, direction);
    }

    @Override
    public String toString() {
        return "["+getCoordinates() + ", " +getDirection() +"]";
    }
}
