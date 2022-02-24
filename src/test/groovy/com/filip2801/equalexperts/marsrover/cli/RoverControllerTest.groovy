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
        1 * cliService.print("Mars rover moved to position (2, 9, WEST)")
    }

}
