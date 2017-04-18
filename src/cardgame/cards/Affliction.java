package cardgame.cards;

import Decorator.*;
import cardgame.AbstractTargetedCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;


public class Affliction implements Card {
    
    /*bisogna fare in modo che l'effetto produca un token che può essere aggiunto ad una carta.
    trovare il modo di acquisire un target 
    */
    
    
    //da rifare
    private class AfflictEffect extends AbstractTargetedCardEffect {
        public AfflictEffect(Player p, Card c) { super(p,c); }
        
  
        @Override
        public void resolve(Card c) {
            c = new AfflictionDecorator(c);
        }

        @Override
        public void resolve() {
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new AfflictEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Affliction"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " -1-1"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
