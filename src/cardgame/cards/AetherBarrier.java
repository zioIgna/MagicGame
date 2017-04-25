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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ignaz
 */
public class AetherBarrier implements Card {
    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect {
            public AetherBarrierEffect(Player p, Card c) { super(p,c); }
            @Override
            protected Enchantment createEnchantment() { return new AetherBarrierEnchantment(owner); }
        }
        @Override
        public Effect getEffect(Player p) { return new AetherBarrierEffect(p,this); }

        private class AetherBarrierEnchantment extends AbstractEnchantment {
            public AetherBarrierEnchantment(Player owner) {
                super(owner);
            }

            private final TriggerAction AetherBarAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Creature) {
//                        Creature c = (Creature)args;
//                        c.inflictDamage(2);
//                        System.out.println( name() + " dealt 2 damages to " + c.name() );
                        Scanner reader = CardGame.instance.getScanner();
                        int idx;
                        Player curPlayer=CardGame.instance.getCurrentPlayer();
                        List<Creature> myCreatures = CardGame.instance.getCurrentPlayer().getCreatures();
                        List<Enchantment> myEnchantments = CardGame.instance.getCurrentPlayer().getEnchantments();
                        if(!myCreatures.isEmpty() || !myEnchantments.isEmpty()){
                            System.out.println("These are your available permanents:");
                            curPlayer.printCards(myCreatures, curPlayer);
                            curPlayer.printEnch(myEnchantments, curPlayer);
                            System.out.println("Do you want to sacrifice a permanent or a lifepoint? 0 for permanent, 1 for lifepoint");
                            do{
                                idx= reader.nextInt();
                            }
                            while(idx < 0 || idx > 1);
                            if(idx == 1)
                                curPlayer.inflictDamage(1);
                            else{
                                if(!myCreatures.isEmpty() && !myEnchantments.isEmpty()){
                                    System.out.println("Do you want to sacrifice a Creature or an Enchantment? 0 for Creature, 1 for enchantment");
                                    do{
                                        idx= reader.nextInt();
                                    }
                                    while(idx < 0 || idx > 1);
                                    if(idx == 0){
                                        System.out.println("Select the Creature to sacrifice");
                                        curPlayer.printCards(myCreatures, curPlayer);
                                        do{
                                            idx= reader.nextInt()-1;
                                        }
                                        while(idx < 0 || idx > myCreatures.size() -1);
                                        curPlayer.destroy(myCreatures.get(idx));
                                    }
                                    else{
                                        System.out.println("Select the Enchantment to sacrifice");
                                        curPlayer.printEnch(myEnchantments, curPlayer);
                                        do{
                                            idx= reader.nextInt()-1;
                                        }
                                        while(idx < 0 || idx > myEnchantments.size() -1);
                                        curPlayer.destroy(myEnchantments.get(idx));
                                    }
                                }
                                else{
                                    if(!myCreatures.isEmpty()){
                                        System.out.println("Select the Creature to sacrifice");
                                        curPlayer.printCards(myCreatures, curPlayer);
                                        do{
                                            idx= reader.nextInt()-1;
                                        }
                                        while(idx < 0 || idx > myCreatures.size() -1);
                                        curPlayer.destroy(myCreatures.get(idx));
                                    }
                                    else{
                                        System.out.println("Select the Enchantment to sacrifice");
                                        curPlayer.printEnch(myEnchantments, curPlayer);
                                        do{
                                            idx= reader.nextInt()-1;
                                        }
                                        while(idx < 0 || idx > myEnchantments.size() -1);
                                        curPlayer.destroy(myEnchantments.get(idx));
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("You have no permanents to sacrifice");
                            curPlayer.inflictDamage(1);
                        }
                    }
                }
            };

            @Override
            public void insert() {
                super.insert();
                CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherBarAction);
            }

            @Override
            public void remove() {
                super.remove();
                CardGame.instance.getTriggers().deregister(AetherBarAction);
            }

            @Override
            public String name() { return "Aether Barrier"; }
        }


        @Override
        public String name() { return "Aether Barrier"; }
        @Override
        public String type() { return "Enchantment"; }
        @Override
        public String ruleText() { return "Whenever a player plays a creature spell, that player sacrifices a permanent unless he or she pays 1"; }
        @Override
        public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
        @Override
        public boolean isInstant() { return false; }
}
