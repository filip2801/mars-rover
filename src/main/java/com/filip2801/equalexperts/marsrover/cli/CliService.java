package com.filip2801.equalexperts.marsrover.cli;

import java.util.Scanner;

class CliService {

    void print(String message) {
        System.out.println(message);
    }

    String readInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

}
