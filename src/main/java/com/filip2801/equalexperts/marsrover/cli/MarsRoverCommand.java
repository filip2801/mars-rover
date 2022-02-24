package com.filip2801.equalexperts.marsrover.cli;

import com.filip2801.equalexperts.marsrover.domain.MarsRover;

import java.util.function.Consumer;

@FunctionalInterface
interface MarsRoverCommand extends Consumer<MarsRover> {
}
