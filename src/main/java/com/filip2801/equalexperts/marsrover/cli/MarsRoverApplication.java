package com.filip2801.equalexperts.marsrover.cli;

public class MarsRoverApplication {

    public static void main(String[] args) {
        RoverController roverController = new RoverController(new CliService());
        roverController.doMission();
    }

}
