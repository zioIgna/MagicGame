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
import java.util.List;

/**
 *
 * @author ignaz
 */
public class DayOfJudgement implements Card {
    private class DayOfJudgementEffect extends AbstractCardEffect {
        public DayOfJudgementEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            List<Creature> attackers = CardGame.instance.getCurrentPlayer().getCreatures();
            for(int i=0; i<attackers.size(); i++){
                int damage = attackers.get(i).getToughness();
                attackers.get(i).inflictDamage(damage);
            }
            
            List<Creature> defenders = CardGame.instance.getCurrentAdversary().getCreatures();
            for(int i=0; i<defenders.size(); i++){
                int damage = defenders.get(i).getToughness();
                defenders.get(i).inflictDamage(damage);
            }
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new DayOfJudgementEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Day Of Judgement"; }
    @Override
    public String type() { return "Sorcery"; }
    @Override
    public String ruleText() { return name() + " destroys all creatures"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
