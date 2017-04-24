
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
import cardgame.Enchantment;
import cardgame.Player;
import java.util.List;

/**
 *
 * @author ignaz
 */
public class CalmingVerse implements Card {
    private class CalmingVerseEffect extends AbstractCardEffect {
        public CalmingVerseEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
            System.out.println("All enemy enchantments destroyed");
            List<Enchantment> yours = CardGame.instance.getCurrentAdversary().getEnchantments();
            for(int i=0; i<yours.size(); i++){
                CardGame.instance.getCurrentAdversary().destroy(yours.get(i));

            }
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new CalmingVerseEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Calming Verse"; }
    @Override
    public String type() { return "Sorcery"; }
    @Override


    public String ruleText() { return name() + " destroys all enchantments you don't control"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
