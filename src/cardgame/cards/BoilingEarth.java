/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ignaz
 */
public class BoilingEarth implements Card {
    private class BoilingEarthEffect extends AbstractCardEffect {
        public BoilingEarthEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {

            List<Creature> attackers = CardGame.instance.getCurrentPlayer().getCreatures();
            for(int i=0; i<attackers.size(); i++)
                attackers.get(i).inflictDamage(1);
            
            List<Creature> defenders = CardGame.instance.getCurrentAdversary().getCreatures();
            for(int i=0; i<defenders.size(); i++)
                defenders.get(i).inflictDamage(1);
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new BoilingEarthEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Boiling Earth"; }
    @Override
    public String type() { return "Sorcery"; }
    @Override
    public String ruleText() { return name() + " deals 1 damage to each creature"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
