/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.Creature;
import cardgame.Player;

/**
 *
 * @author ignaz
 */
public class Afflict implements Card {
    private class AfflictEffect extends AbstractCreatureCardEffect{

        public AfflictEffect (Player p, Card c) { super(p,c); }

        @Override
        protected Creature createCreature() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
