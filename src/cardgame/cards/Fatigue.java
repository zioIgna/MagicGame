/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;

/**
 *
 * @author ignaz
 */
public class Fatigue implements Card{
    private class FatigueEffect extends AbstractCardEffect {
        public FatigueEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            System.out.println("Adversary will skip his or her next draw phase");
            CardGame.instance.getCurrentAdversary().setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new FatigueEffect(owner, this); 
    }

    @Override
    public String name() { return "Fatigue"; }

    @Override
    public String type() { return "Sorcery"; }

    @Override
    public String ruleText() { return name() + " has target player skip his or her next draw step"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}

    @Override
    public boolean isInstant() { return false; }    
}
