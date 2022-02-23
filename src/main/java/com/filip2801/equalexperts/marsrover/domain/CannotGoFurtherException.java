package com.filip2801.equalexperts.marsrover.domain;

public class CannotGoFurtherException extends RuntimeException {

    CannotGoFurtherException(Position position) {
        super("Cannot go further. Current position: " + position);
    }
}
