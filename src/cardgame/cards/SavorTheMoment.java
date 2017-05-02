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
public class SavorTheMoment implements Card{
    private class SavorTheMomentEffect extends AbstractCardEffect {
        public SavorTheMomentEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            System.out.println("Current player will take an extra turn and skip his or her next untap phase");
            CardGame.instance.nextPlayer();
            CardGame.instance.getCurrentAdversary().setPhase(Phases.UNTAP, new SkipPhase(Phases.UNTAP));
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new SavorTheMomentEffect(owner, this); 
    }

    @Override
    public String name() { return "Savor The Moment"; }

    @Override
    public String type() { return "Sorcery"; }

    @Override
    public String ruleText() { return name() + " has current player take an extra turn after this one. Skip the untap step of that turn"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}

    @Override
    public boolean isInstant() { return false; }       
}
