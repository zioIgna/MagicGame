/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.CardStack;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author ignaz
 */
public class Cancel implements Card {
    private class CancelEffect extends AbstractCardEffect {
        public CancelEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            CardGame.instance.getStack().removePrev();
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new CancelEffect(owner, this); 
    }

    @Override
    public String name() { return "Cancel"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " counter target spell"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
