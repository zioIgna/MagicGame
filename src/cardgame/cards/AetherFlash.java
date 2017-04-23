/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;

/**
 *
 * @author ignaz
 */
public class AetherFlash implements Card {
    private class AetherFlashEffect extends AbstractEnchantmentCardEffect {
            public AetherFlashEffect(Player p, Card c) { super(p,c); }
            @Override
            protected Enchantment createEnchantment() { return new AetherFlashEnchantment(owner); }
        }
        @Override
        public Effect getEffect(Player p) { return new AetherFlashEffect(p,this); }

        private class AetherFlashEnchantment extends AbstractEnchantment {
            public AetherFlashEnchantment(Player owner) {
                super(owner);
            }

            private final TriggerAction AetherAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Creature) {
                        Creature c = (Creature)args;
                        c.inflictDamage(2);
                        System.out.println( name() + " dealt 2 damages to " + c.name() );
                    }
                }
            };

            @Override
            public void insert() {
                super.insert();
                CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherAction);
            }

            @Override
            public void remove() {
                super.remove();
                CardGame.instance.getTriggers().deregister(AetherAction);
            }

            @Override
            public String name() { return "Aether Flash"; }
        }


        @Override
        public String name() { return "Aether Flash"; }
        @Override
        public String type() { return "Enchantment"; }
        @Override
        public String ruleText() { return "Whenever a creature comes into play " + name() + " deals 2 damage to it"; }
        @Override
        public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
        @Override
        public boolean isInstant() { return false; }
}
