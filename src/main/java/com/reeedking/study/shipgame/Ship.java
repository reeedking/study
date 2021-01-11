package com.reeedking.study.shipgame;

public class Ship {

    private final Location location;

    public Location getLocation() {
        return location;
    }

    public Ship(Location location) {
        this.location = location;
    }

    public boolean moveForward() {
        return location.forward();
    }

    public boolean moveBackward() {
        return location.backward();
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public void receiveCommands(String commands) {
        for (Character ch : commands.toCharArray()) {
            switch (ch) {
                case 'f':
                    moveForward();
                    break;
                case 'b':
                    moveBackward();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
                default:
                    break;
            }
        }
    }
}
