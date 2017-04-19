/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator;

import cardgame.AbstractCreature;
import cardgame.Creature;
import cardgame.Player;

/**
 *
 * @author Manuel
 */
public abstract class Decorator extends AbstractCreature {   //perché non richiede di implementare tutti i metodi di Creature?
    
    protected final Creature decoratedCreature;
    

    public Decorator(Player p, Creature c){
        super(p);
        this.decoratedCreature=c;
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

    

