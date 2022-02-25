package com.filip2801.equalexperts.marsrover.cli

import spock.lang.Specification

class RoverControllerTest extends Specification {

    def "should do mars rover mission"() {
        given:
        CliService cliService = Mock()
        RoverController roverController = new RoverController(cliService)

        cliService.readInput() >>> [
                "(-1, 10, EAST)",
                "FRFRRLBB"
        ]

        when:
        roverController.doMission()

        then:
        1 * cliService.print("Mars rover moved to position (2, 9) WEST")
    }

    def "should not move rover further after moving to max coordinate"() {
        given:
        CliService cliService = Mock()
        RoverController roverController = new RoverController(cliService)
        int maxCoordinate = Integer.MAX_VALUE;

        cliService.readInput() >>> [
                "(${maxCoordinate-1}, 0, EAST)",
                "FFRF"
        ]

        when:
        roverController.doMission()

        then:
        1 * cliService.print("Mars rover cannot go further")
        1 * cliService.print("Mars rover moved to position ($maxCoordinate, 0) EAST")
    }

}
