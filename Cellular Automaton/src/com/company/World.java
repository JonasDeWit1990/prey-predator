package com.company;

import java.util.Random;

/**
 * World class that uses creature class
 * keeps track what field is a prey, predator or empty
 */
class World {
    private int width, height;
    private Creature[][] playField;

    //config
    private static int maxPreyHealth = 10;

    World(int width, int height) {
        this.width = width;
        this.height = height;
        playField = new Creature[width][height];

        Random rng = new Random();

        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                playField[column][row] = new Creature(rng);
            }
        }
    }

    int getCreatureColor(int x, int y) {
        if(x < width && y < height)
            return playField[x][y].getColor();
        else
            return -1;
    }


    private void updatePredator(Creature current, Creature other) {
        switch(other.getCreatureType()) {
            case nothing:
                other.TurnPredator();
                other.setHealth(current.getHealth());
                other.setMoved(true);
                current.death();
                break;
            case prey:
                current.updateHealth(other.getHealth());
                other.TurnPredator();
                other.setMoved(true);
                break;
            case predator:
                break;
            default:
                break;
        }
    }

    private void updatePrey(Creature current, Creature other) {
        switch(other.getCreatureType()) {
            case nothing:
                other.populate();
                other.setHealth(current.getHealth());
                other.setMoved(true);
                if(current.getHealth() > maxPreyHealth) {
                    current.populate();
                    other.setHealth(1);
                }
                else
                    current.death();
                break;
            case prey:
            case predator:
            default:
                if(current.getHealth() > maxPreyHealth)
                    current.updateHealth(-1);
                break;
        }
    }

    //represents 1 tick in the world
    void updateWorld() {
        Random rng = new Random();
        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                Creature currentCreature = playField[column][row];

                if (!currentCreature.isMoved()){
                    int Xdiff = rng.nextInt(3) - 1;
                    int Ydiff = rng.nextInt(3) - 1;

                    switch(currentCreature.getCreatureType()){
                        case predator:
                            currentCreature.updateHealth(-1);
                            if(currentCreature.getHealth() <= 0) {
                                currentCreature.death();
                            }
                            else{
                                if((column + Xdiff) < 0 || (column + Xdiff) >= width || (row + Ydiff) < 0 || (row + Ydiff) >= height)
                                    break;

                                Creature otherCreature = playField[column + Xdiff][row + Ydiff];
                                updatePredator(currentCreature,otherCreature);
                            }
                            break;
                        case prey:
                            currentCreature.updateHealth(1);
                            if((column + Xdiff) < 0 || (column + Xdiff) >= width || (row + Ydiff) < 0 || (row + Ydiff) >= height)
                                break;

                            Creature otherCreature = playField[column + Xdiff][row + Ydiff];
                            updatePrey(currentCreature,otherCreature);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        for(int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                playField[column][row].setMoved(false);
            }
        }
    }

}
