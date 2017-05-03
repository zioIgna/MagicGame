/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.AddPhase;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import java.util.List;

/**
 *
 * @author ignaz
 */
public class WorldAtWar implements Card{
    private class WorldAtWarEffect extends AbstractCardEffect {
        public WorldAtWarEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            List<Creature> attackers;
            System.out.println("Take and additional combat phase + additional main phase, untap all attacking creatures before the new combat phase");
            attackers = CardGame.instance.getCurrentPlayer().getTappedCreatures();
            for(Creature e:attackers)
                e.untap();
            CardGame.instance.getCurrentAdversary().setPhase(Phases.COMBAT, new AddPhase(Phases.COMBAT));
            CardGame.instance.getCurrentAdversary().setPhase(Phases.MAIN, new AddPhase(Phases.MAIN));
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new WorldAtWarEffect(owner, this); 
    }

    @Override
    public String name() { return "World At War"; }

    @Override
    public String type() { return "Sorcery"; }

    @Override
    public String ruleText() { return name() + " adds a combat phase and main phase after this turn main phase. At the beginning of that combat, untap all creatures that attacked this turn"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}

    @Override
    public boolean isInstant() { return false; }
}
