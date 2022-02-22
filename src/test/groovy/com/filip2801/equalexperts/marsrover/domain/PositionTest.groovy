package com.filip2801.equalexperts.marsrover.domain

import spock.lang.Specification

class PositionTest extends Specification {

    def "should create position"() {
        given:
        def coordinates = new Coordinates(1, 2)
        def direction = Direction.NORTH

        when:
        def position = new Position(coordinates, direction)

        then:
        position.coordinates == coordinates
        position.direction == direction
    }
}
