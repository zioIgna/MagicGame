
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
import java.util.Scanner;

/**
 *
 * @author ignaz
 */
public class VolcanicHammer implements Card {
    private class VolcanicHammerEffect extends AbstractCardEffect {
        public VolcanicHammerEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
           List<Creature> attackers = CardGame.instance.getCurrentPlayer().getCreatures();
           List<Creature> defenders = CardGame.instance.getCurrentAdversary().getCreatures();
           Scanner reader = CardGame.instance.getScanner();
           int choice;
           int idx;
           if(!attackers.isEmpty() && !defenders.isEmpty()){
           System.out.println("0 to target players, 1 to target creatures");
           
           do{
               idx= reader.nextInt();
           }while(idx != 0 && idx != 1);
           if(idx  == 0){
                System.out.println("0 to target yourself, 1 to target your opponent");
                do{
                    idx= reader.nextInt();
                }
                while(idx != 0 && idx != 1);
                if(idx == 0){
                    CardGame.instance.getCurrentPlayer().inflictDamage(3);
                }
                if(idx == 1){
                    CardGame.instance.getCurrentAdversary().inflictDamage(3);
                }
           }
           else{
               System.out.println("0 to target your side, 1 to target your opponent's");
               do{
                   idx= reader.nextInt();
               }
               while(idx != 0 && idx != 1);
               
               if(idx == 0){
                   System.out.println("choose a creature to deal 3 damage to ");
                   CardGame.instance.getCurrentPlayer().printCards(attackers, CardGame.instance.getCurrentPlayer());
                   do{
                       idx= reader.nextInt()-1;
                   }
                   while(idx < 0 || idx > attackers.size() -1);
                   attackers.get(idx).inflictDamage(3);    
               }
               else{
                   System.out.println("choose a creature to deal 3 damage to ");
                   CardGame.instance.getCurrentAdversary().printCards(defenders, CardGame.instance.getCurrentAdversary());
                   do{
                       idx= reader.nextInt()-1;
                   }
                   while(idx < 0 || idx > defenders.size() -1);
                   defenders.get(idx).inflictDamage(3);
               }
               }
           }
           else{
               if(!attackers.isEmpty() && defenders.isEmpty()){
                    System.out.println("0 to target players, 1 to target creatures");
           
                    do{
                    idx= reader.nextInt();
                    }while(idx != 0 && idx != 1);
                    if(idx  == 0){
                        System.out.println("0 to target yourself, 1 to target your opponent");
                        do{
                            idx= reader.nextInt();
                        }
                        while(idx != 0 && idx != 1);
                        if(idx == 0){
                            CardGame.instance.getCurrentPlayer().inflictDamage(3);
                        }
                        if(idx == 1){
                            CardGame.instance.getCurrentAdversary().inflictDamage(3);
                }   
               }
                    else{
                        System.out.println("choose a creature to deal 3 damage to ");
                        CardGame.instance.getCurrentPlayer().printCards(attackers, CardGame.instance.getCurrentPlayer());
                        do{
                            idx= reader.nextInt()-1;
                        }
                        while(idx < 0 || idx > attackers.size() -1);
                        attackers.get(idx).inflictDamage(3);  
                            }
                   
           }
               if(attackers.isEmpty() && !defenders.isEmpty()){
                    System.out.println("0 to target players, 1 to target creatures");
           
                    do{
                    idx= reader.nextInt();
                    }while(idx != 0 && idx != 1);
                    if(idx  == 0){
                        System.out.println("0 to target yourself, 1 to target your opponent");
                        do{
                            idx= reader.nextInt();
                        }
                        while(idx != 0 && idx != 1);
                        if(idx == 0){
                            CardGame.instance.getCurrentPlayer().inflictDamage(3);
                        }
                        if(idx == 1){
                            CardGame.instance.getCurrentAdversary().inflictDamage(3);
                }   
               }
                    else{
                        System.out.println("choose a creature to deal 3 damage to ");
                        CardGame.instance.getCurrentAdversary().printCards(defenders, CardGame.instance.getCurrentAdversary());
                        do{
                            idx= reader.nextInt()-1;
                        }
                        while(idx < 0 || idx > defenders.size() -1);
                        defenders.get(idx).inflictDamage(3);  
                            }
                   
           }
               if(attackers.isEmpty() && defenders.isEmpty()){
                System.out.println("0 to target yourself, 1 to target your opponent");
                do{
                    idx= reader.nextInt();
                }
                while(idx != 0 && idx != 1);
                if(idx == 0){
                    CardGame.instance.getCurrentPlayer().inflictDamage(3);
                }
                if(idx == 1){
                    CardGame.instance.getCurrentAdversary().inflictDamage(3);
                }
           }
           }
           
         
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new VolcanicHammerEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Volcanic Hammer"; }
    @Override
    public String type() { return "Sorcery"; }
    @Override


    public String ruleText() { return name() + " deals 3 damage to any one creature or player"; }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
