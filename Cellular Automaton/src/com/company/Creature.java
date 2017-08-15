package com.company;

import java.awt.*;
import java.util.Random;

import static com.company.CreatureType.*;

/**
 * Creature class (no creature, prey or predator)
 * enum type included
 */
enum CreatureType{
    nothing,prey,predator
}

public class Creature {
    //variables
    private CreatureType creatureType;
    private int health;
    private boolean moved;

    Creature(Random rng) {
        int tryOuts = rng.nextInt(5000);

        if(tryOuts > 100) {
            creatureType = nothing;
            health = 0;
        }
        else if(tryOuts >= 20) {
            creatureType = prey;
            health = 1;
        }
        else {
            creatureType = CreatureType.predator;
            health = 10;
        }
        moved = false;
    }

    Creature(CreatureType creatureType) {
        if(creatureType == nothing) {
            this.creatureType = nothing;
            health = 0;
        }
        else if (creatureType == prey) {
            this.creatureType = prey;
            health = 1;
        }
        else if(creatureType == predator) {
            this.creatureType = predator;
            health = 10;
        }
    }


    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    int getColor() {
        switch(creatureType){
            case nothing:
                return (new Color(0,0,0)).getRGB();
            case prey:
                return (new Color(0,255,0)).getRGB();
            case predator:
                return (new Color(255,0,0)).getRGB();
            default:
                return (new Color(0,0,0)).getRGB();
        }
    }

    void updateHealth(int difference){
        health += difference;
    }

    void death() {
        creatureType = nothing;
        health = 0;
    }

    void TurnPredator() {
        creatureType = predator;
        health = 10;
    }

    void populate() {
        creatureType = prey;
        health = 1;
    }

}
