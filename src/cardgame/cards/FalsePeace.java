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
public class FalsePeace implements Card{
    private class FalsePeaceEffect extends AbstractCardEffect {
        public FalsePeaceEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            System.out.println("Adversary will skip his next combat phase");
            CardGame.instance.getCurrentAdversary().setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
//            if(this.owner.equals(CardGame.instance.getCurrentPlayer()))
//                CardGame.instance.getCurrentAdversary().setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
//            else
//                CardGame.instance.getCurrentPlayer().setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new FalsePeaceEffect(owner, this); 
    }

    @Override
    public String name() { return "False Peace"; }

    @Override
    public String type() { return "Sorcery"; }

    @Override
    public String ruleText() { return name() + " has target player skip his next combat phase"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}

    @Override
    public boolean isInstant() { return false; }

}
