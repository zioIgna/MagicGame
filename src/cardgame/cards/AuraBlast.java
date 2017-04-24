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
import cardgame.Triggers;
import java.util.List;
import java.util.Scanner;



public class AuraBlast implements Card {
    
    private class AuraBlastEffect extends AbstractCardEffect {
        public AuraBlastEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
           int idx;
           Scanner reader = CardGame.instance.getScanner();
           List<Enchantment> mine = CardGame.instance.getCurrentPlayer().getEnchantments();
           List<Enchantment> yours = CardGame.instance.getCurrentAdversary().getEnchantments();
           if(mine.isEmpty() && yours.isEmpty()){
               System.out.println("there are no enchantments to destroy");
               CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
               owner.draw();
        
                while(owner.getHand().size() > owner.getMaxHandSize())
                owner.selectDiscard();
            }
           else{
               if(mine.isEmpty() && !yours.isEmpty()){
                    System.out.println("choose an enchantment to destroy ");
                    CardGame.instance.getCurrentAdversary().printEnch(yours, CardGame.instance.getCurrentAdversary());
                    do{
                        idx= reader.nextInt()-1;
                    }
                    while(idx < 0 || idx > yours.size() -1);
                    CardGame.instance.getCurrentAdversary().destroy(yours.get(idx)); 
                    CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
                    owner.draw();
        
                    while(owner.getHand().size() > owner.getMaxHandSize())
                    owner.selectDiscard();
               }
               
               if(!mine.isEmpty() && yours.isEmpty()){
                    System.out.println("choose an enchantment to destroy ");
                    CardGame.instance.getCurrentPlayer().printEnch(mine, CardGame.instance.getCurrentPlayer());
                    do{
                        idx= reader.nextInt()-1;
                    }
                    while(idx < 0 || idx > yours.size() -1);
                    CardGame.instance.getCurrentPlayer().destroy(mine.get(idx));  
                    CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
                    owner.draw();
        
                    while(owner.getHand().size() > owner.getMaxHandSize())
                    owner.selectDiscard();
                }
               
               if(mine.isEmpty() && yours.isEmpty()){
                    System.out.println("0 to target your side, 1 to target your opponent's");
                    do{
                        idx= reader.nextInt();
                    }
                    while(idx != 0 && idx != 1);
               
                    if(idx == 0){
                        System.out.println("choose an enchantment to destroy ");
                        CardGame.instance.getCurrentPlayer().printEnch(mine, CardGame.instance.getCurrentPlayer());
                        do{
                            idx= reader.nextInt()-1;
                        }
                        while(idx < 0 || idx > yours.size() -1);
                        CardGame.instance.getCurrentPlayer().destroy(mine.get(idx));  
                        CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
                        owner.draw();
        
                        while(owner.getHand().size() > owner.getMaxHandSize())
                        owner.selectDiscard();
                    }
                    else{
                        System.out.println("choose an enchantment to destroy ");
                        CardGame.instance.getCurrentAdversary().printEnch(yours, CardGame.instance.getCurrentAdversary());
                        do{
                            idx= reader.nextInt()-1;
                        }
                        while(idx < 0 || idx > yours.size() -1);
                        CardGame.instance.getCurrentAdversary().destroy(yours.get(idx)); 
                        CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
                        owner.draw();
                    
                        while(owner.getHand().size() > owner.getMaxHandSize())
                        owner.selectDiscard();
                    }
                        
                    }
                        
                    }
                   
                }
           
            
            
        }
    

    @Override
    public Effect getEffect(Player owner) { 
        return new AuraBlastEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Aura Blast"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " destroy target enchantment. Draw a card."; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
