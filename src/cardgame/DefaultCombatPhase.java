/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        Player opponent = CardGame.instance.getCurrentAdversary();
        Creature attacker;
        List<Creature> att;
        List<Creature> def;
        List<List<Creature>> defall = new LinkedList();
        
        
        System.out.println(currentPlayer.name() + ": combat phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        
        //currentPlayer.printUntapped(currentPlayer.getUntappedCreatures());   
        List<Creature> untapped = opponent.getUntappedCreatures();
        
        //dichiarazione attaccanti
        att = ChooseAttackersCreature(currentPlayer);
        if(att.isEmpty()){
           //System.out.println(currentPlayer.toString() + " has chosen not to attack.");
           System.out.println(currentPlayer.toString() + "'s Combat phase ends.");
        }
        else{
//            List<Creature> attb = new LinkedList<>(att);

            System.out.println(currentPlayer.toString() + " has chosen which creatures will attack.");
            playAvailableEffect(opponent, false);
            int i = att.size() -1;
            int j = 0;
            Creature l;

            //dichiarazione difensori
            while(j <= i){
                l = att.get(j);
                System.out.println(currentPlayer.toString() + " will attack with " + l.toString() );
                def = ChooseDefendersCreature(opponent, untapped);
                
                for (Creature c:def) {
                untapped.remove(c);
                }
                
                defall.add(def);
                j++;
            }
            System.out.println(opponent.toString() + " has chosen which creatures will defend.");
            playAvailableEffect(currentPlayer, false);

            //viene gestito il combattimento
            while(!att.isEmpty()){
                attacker = att.remove(0);
                def = defall.remove(0);
                Conflict(attacker, def, opponent);
            }

            System.out.println(currentPlayer.toString() + "'s Combat phase ends.");
        }
    }
    
    private List<Creature> ChooseAttackersCreature(Player activePlayer) {

        List<Creature> attackers = new LinkedList();
        List<Creature> untapped = activePlayer.getUntappedCreatures();
//        activePlayer.printUntapped(untapped);
        Scanner reader = CardGame.instance.getScanner();
        int idx;
        if(untapped.isEmpty()){
            activePlayer.printUntapped(untapped);
            System.out.println(activePlayer.name() + " cannot attack this turn.");
            return attackers;
        }
        else{            
            do{
                System.out.println(activePlayer.name() + " select creatures to attack, 0 to pass");
                activePlayer.printUntapped(untapped);
                idx= reader.nextInt()-1;
                    if (idx >= 0){
                        attackers.add(untapped.remove(idx));
//                        activePlayer.printUntapped(untapped);
                    }
            } while(idx >= 0 && !untapped.isEmpty());
            return attackers;
        }
    }
    

    private List<Creature> ChooseDefendersCreature(Player oppPlayer, List<Creature> untapped) {

        List<Creature> defenders = new LinkedList();
        oppPlayer.printUntapped(untapped);
        Scanner reader = CardGame.instance.getScanner();
        int idx;
        if(untapped.isEmpty()){
           System.out.println(oppPlayer.name() + " cannot defend this turn.");
           return defenders;
        }
        else{
            do{
                System.out.println(oppPlayer.name() + " select creatures to defend, 0 to stop");
                oppPlayer.printUntapped(untapped);
                idx= reader.nextInt()-1;
                if (idx >= 0){
                    defenders.add(untapped.remove(idx));
//                    oppPlayer.printUntapped(untapped);
                }
            }
            while(idx >= 0 && !untapped.isEmpty());
        }
        return defenders;
    }
    
    private boolean playAvailableEffect(Player activePlayer, boolean isMain) {
        //collect and display available effects...
        ArrayList<Effect> availableEffects = new ArrayList<>();
        Scanner reader = CardGame.instance.getScanner();

        //...cards first
        System.out.println(activePlayer.name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:activePlayer.getHand() ) {
            if ( isMain || c.isInstant() ) {
                availableEffects.add( c.getEffect(activePlayer) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:activePlayer.getCreatures()) {
            for (Effect e:c.avaliableEffects()) {
                availableEffects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=availableEffects.size()) return false;

        availableEffects.get(idx).play();
        return true;
    }
    
    private void Conflict(Creature attacker, List<Creature> def, Player opponent){
        //attacco rimanente all'attaccante
        int remAtt = attacker.getPower();
       
        //somma dell'attaco cumulativo dei difensori
        int cumAttack = 0;
        System.out.println("Combat phase resolves...");
        if(def.isEmpty()){
            opponent.inflictDamage(remAtt);
            System.out.println(opponent.name() + " takes " + remAtt + " points of damage, total remaining health: " + opponent.getLife());
            
        }
        else{
        Creature currDef = def.remove(0);
        while(remAtt > 0){
             
             if(remAtt <= currDef.getToughness()){
                 cumAttack += currDef.getPower();
                 currDef.inflictDamage(remAtt);
                 remAtt = 0;
             }
             else{
                 remAtt -= currDef.getToughness();
                 cumAttack += currDef.getPower();
                 currDef.inflictDamage(currDef.getToughness());
                 if(def.isEmpty()){
                     remAtt = 0;
                 }
                 else{
                     currDef = def.remove(0);
                 }
                 
             }    
        }
        attacker.inflictDamage(cumAttack); 
        }
    }
    
    
    private List<Creature> cop(List<Creature> a){
        return a;
    }
    
    
    
}
