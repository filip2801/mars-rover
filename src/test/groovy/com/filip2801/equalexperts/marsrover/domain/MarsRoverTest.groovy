package com.filip2801.equalexperts.marsrover.domain

import spock.lang.Specification

class MarsRoverTest extends Specification {

    def "should move forward when directed to #direction"() {
        given:
        def startingPosition = new Position(new Coordinates(0, 0), direction)
        def marsRover = new MarsRover(startingPosition)

        when:
        marsRover.moveForward()

        then:
        def finalPosition = marsRover.getPosition()
        finalPosition.direction == direction
        finalPosition.coordinates == expectedCoordinates

        where:
        direction       || expectedCoordinates
        Direction.NORTH || new Coordinates(0, 1)
        Direction.SOUTH || new Coordinates(0, -1)
        Direction.EAST  || new Coordinates(1, 0)
        Direction.WEST  || new Coordinates(-1, 0)
    }

    def "should move backwards when directed to #direction"() {
        given:
        def startingPosition = new Position(new Coordinates(0, 0), direction)
        def marsRover = new MarsRover(startingPosition)

        when:
        marsRover.moveBackwards()

        then:
        def finalPosition = marsRover.getPosition()
        finalPosition.direction == direction
        finalPosition.coordinates == expectedCoordinates

        where:
        direction       || expectedCoordinates
        Direction.NORTH || new Coordinates(0, -1)
        Direction.SOUTH || new Coordinates(0, 1)
        Direction.EAST  || new Coordinates(-1, 0)
        Direction.WEST  || new Coordinates(1, 0)
    }

    def "should rotate right by 90 degrees when directed to #initialDirection"() {
        given:
        def startingPosition = new Position(new Coordinates(0, 0), initialDirection)
        def marsRover = new MarsRover(startingPosition)

        when:
        marsRover.rotateRightBy90Degrees()

        then:
        def finalPosition = marsRover.getPosition()
        finalPosition.coordinates == startingPosition.coordinates
        finalPosition.direction == expectedDirection

        where:
        initialDirection || expectedDirection
        Direction.NORTH  || Direction.EAST
        Direction.EAST   || Direction.SOUTH
        Direction.SOUTH  || Direction.WEST
        Direction.WEST   || Direction.NORTH
    }

    def "should rotate left by 90 degrees when directed to #initialDirection"() {
        given:
        def startingPosition = new Position(new Coordinates(0, 0), initialDirection)
        def marsRover = new MarsRover(startingPosition)

        when:
        marsRover.rotateLeftBy90Degrees()

        then:
        def finalPosition = marsRover.getPosition()
        finalPosition.coordinates == startingPosition.coordinates
        finalPosition.direction == expectedDirection

        where:
        initialDirection || expectedDirection
        Direction.NORTH  || Direction.WEST
        Direction.WEST   || Direction.SOUTH
        Direction.SOUTH  || Direction.EAST
        Direction.EAST   || Direction.NORTH
    }

    def "should throw exception when rover cannot go further to the #direction"() {
        given:
        def startingPosition = new Position(coordinates, direction)
        def marsRover = new MarsRover(startingPosition)

        when:
        marsRover.moveForward()

        then:
        def exception = thrown(CannotGoFurtherException)
        exception.message == "Cannot go further. Current position: " + startingPosition

        and:
        marsRover.position.coordinates == coordinates

        where:
        direction       | coordinates
        Direction.NORTH | new Coordinates(0, Integer.MAX_VALUE)
        Direction.SOUTH | new Coordinates(0, Integer.MIN_VALUE)
        Direction.EAST  | new Coordinates(Integer.MAX_VALUE, 0)
        Direction.WEST  | new Coordinates(Integer.MIN_VALUE, 0)
    }
}
