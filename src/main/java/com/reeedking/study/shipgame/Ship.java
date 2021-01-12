package com.reeedking.study.shipgame;

public class Ship {

    private final Location location;

    private Planet planet;

    public Location getLocation() {
        return location;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

//    public Ship(Location location) {
//        this.location = location;
//    }

    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public boolean moveForward() {
        return location.forward(planet.getMax());
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
