/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator;

import cardgame.Creature;

/**
 *
 * @author Manuel
 */
public abstract class Decorator implements Creature {   //perché non richiede di implementare tutti i metodi di Creature?
    
    protected final Creature decoratedCreature;


    public Decorator(Creature c) {
        this.decoratedCreature = c;
    }

    @Override
    public int getPower() { // Implementing methods of the interface
        return decoratedCreature.getPower();
    }

    @Override
    public int getToughness() {
        return decoratedCreature.getToughness();
    }
}

    

