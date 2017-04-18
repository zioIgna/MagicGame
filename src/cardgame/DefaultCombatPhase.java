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
        
        System.out.println(currentPlayer.name() + ": combat phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        
        currentPlayer.printUntapped(currentPlayer.getUntappedCreatures());   
        
        //att = currentPlayer.chooseAttackers;
        //stack
        /*while(att){
        def = opponent.chooseDefenders;
        stack
        currentplayer.attack(def);
        }
        
        
        */
    }
    
    private List<Creature> ChooseAttackersCreature(Player activePlayer) {

        List<Creature> attackers = new LinkedList();
        List<Creature> untapped = activePlayer.getUntappedCreatures();
        activePlayer.printUntapped(untapped);
        Scanner reader = CardGame.instance.getScanner();
        int idx;
        
        do{ 
        System.out.println(activePlayer.name() + " select creatures to attack, 0 to pass");
        int i=0;
        idx= reader.nextInt()-1;
        

        attackers.add(untapped.remove(idx));
        }
        while(idx >= 0 || !untapped.isEmpty());
        return attackers;
    
}
    private List<Creature> ChooseDefendersCreature(Player oppPlayer) {
     
        List<Creature> defenders = new LinkedList();
        List<Creature> untapped = oppPlayer.getUntappedCreatures();
        oppPlayer.printUntapped(untapped);
        Scanner reader = CardGame.instance.getScanner();
        int idx;
        
        do{ 
        System.out.println(oppPlayer.name() + " select creatures to defend, 0 to stop");
        int i=0;
        idx= reader.nextInt()-1;
        

        defenders.add(untapped.remove(idx));
        }
        while(idx >= 0 || !untapped.isEmpty());
        return defenders;
    
}
    
    
    
}
