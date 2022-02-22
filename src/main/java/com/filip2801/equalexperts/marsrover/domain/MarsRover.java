package com.filip2801.equalexperts.marsrover.domain;

import static java.util.Objects.requireNonNull;

public final class MarsRover {

    private Position position;

    public MarsRover(Position position) {
        this.position = requireNonNull(position);
    }

    public void moveForward() {
        switch (position.getDirection()) {
            case NORTH:
                moveYPlusOne();
                break;
            case SOUTH:
                moveYMinusOne();
                break;
            case EAST:
                moveXPlusOne();
                break;
            case WEST:
                moveXMinusOne();
                break;
        }
    }

    public void moveBackwards() {
        switch (position.getDirection()) {
            case NORTH:
                moveYMinusOne();
                break;
            case SOUTH:
                moveYPlusOne();
                break;
            case EAST:
                moveXMinusOne();
                break;
            case WEST:
                moveXPlusOne();
                break;
        }
    }

    public void rotateRightBy90Degrees() {
        switch (position.getDirection()) {
            case NORTH:
                changeDirection(Direction.EAST);
                break;
            case EAST:
                changeDirection(Direction.SOUTH);
                break;
            case SOUTH:
                changeDirection(Direction.WEST);
                break;
            case WEST:
                changeDirection(Direction.NORTH);
                break;
        }
    }

    public void rotateLeftBy90Degrees() {
        switch (position.getDirection()) {
            case NORTH:
                changeDirection(Direction.WEST);
                break;
            case EAST:
                changeDirection(Direction.NORTH);
                break;
            case SOUTH:
                changeDirection(Direction.EAST);
                break;
            case WEST:
                changeDirection(Direction.SOUTH);
                break;
        }
    }

    private void moveYPlusOne() {
        changeYCoordinate(getPosition().getCoordinates().getY() + 1);
    }

    private void moveYMinusOne() {
        changeYCoordinate(getPosition().getCoordinates().getY() - 1);
    }

    private void moveXPlusOne() {
        changeXCoordinate(getPosition().getCoordinates().getX() + 1);
    }

    private void moveXMinusOne() {
        changeXCoordinate(getPosition().getCoordinates().getX() - 1);
    }

    private void changeYCoordinate(int newYCoordinate) {
        var newCoordinates = new Coordinates(getPosition().getCoordinates().getX(), newYCoordinate);
        changeCoordinates(newCoordinates);
    }

    private void changeXCoordinate(int newXCoordinate) {
        var newCoordinates = new Coordinates(newXCoordinate, getPosition().getCoordinates().getY());
        changeCoordinates(newCoordinates);
    }

    private void changeCoordinates(Coordinates newCoordinates) {
        this.position = new Position(newCoordinates, getPosition().getDirection());
    }

    private void changeDirection(Direction newDirection) {
        this.position = new Position(getPosition().getCoordinates(), newDirection);
    }

    public Position getPosition() {
        return position;
    }
}
