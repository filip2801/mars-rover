package com.filip2801.equalexperts.marsrover.cli

import com.filip2801.equalexperts.marsrover.domain.Coordinates
import com.filip2801.equalexperts.marsrover.domain.Direction
import spock.lang.Specification

class UserInputServiceTest extends Specification {

    CliService cliService = Mock()
    UserInputService userInputService = new UserInputService(cliService)

    def "should read position from input"() {
        given:
        cliService.readInput() >> "(-62, 123, $direction)"

        when:
        var position = userInputService.readPosition()

        then:
        position.coordinates == new Coordinates(-62, 123)
        position.direction == direction

        where:
        direction << Direction.values()
    }

    def "should not read position from first input and wait for second attempt when coordination too high"() {
        given:
        cliService.readInput() >>> [
                "(${Integer.MAX_VALUE}0, 123, WEST)",
                "(10, 10, WEST)"
        ]

        when:
        var position = userInputService.readPosition()

        then:
        position.coordinates == new Coordinates(10, 10)
        position.direction == Direction.WEST
    }

    def "should not read position from first input and wait for second attempt when coordination too low"() {
        given:
        cliService.readInput() >>> [
                "(${Integer.MAX_VALUE}0, 123, WEST)",
                "(10, 10, WEST)"
        ]

        when:
        var position = userInputService.readPosition()

        then:
        position.coordinates == new Coordinates(10, 10)
        position.direction == Direction.WEST
    }

    def "should not read position from first input and wait for second attempt when direction was not like supported"() {
        given:
        cliService.readInput() >>> [
                "(0, 0, XXX)",
                "(10, 10, WEST)"
        ]

        when:
        var position = userInputService.readPosition()

        then:
        position.coordinates == new Coordinates(10, 10)
        position.direction == Direction.WEST
    }

    def "should read commands"() {
        given:
        cliService.readInput() >> "FBRLF"

        when:
        var commands = userInputService.readCommands()

        then:
        commands.size() == 5
    }

    def "should not read position from first input and wait for second attempt when input contains not supported commands"() {
        given:
        cliService.readInput() >>> ["FX", "BBRL"]

        when:
        var commands = userInputService.readCommands()

        then:
        commands.size() == 4
    }

}
