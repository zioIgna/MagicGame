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
        Creature attacker = null;
        List<Creature> att = new LinkedList();
        List<Creature> def = new LinkedList();
        List<List<Creature>> defall = new LinkedList();
        
        
        System.out.println(currentPlayer.name() + ": combat phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        
        currentPlayer.printUntapped(currentPlayer.getUntappedCreatures());   
        
        
        //dichiarazione attaccanti
        att = ChooseAttackersCreature(currentPlayer);
        List<Creature> attb = att;
        System.out.println(currentPlayer.toString() + " has chosen which creatures will attack.");
        playAvailableEffect(opponent, false);
        
        //dichiarazione difensori
        while(!attb.isEmpty()){
            def = ChooseDefendersCreature(opponent);
            defall.add(def);    
        }
        System.out.println(opponent.toString() + " has chosen which creatures will defend.");
        playAvailableEffect(currentPlayer, false);
        
        //viene gestito il combattimento
        while(!att.isEmpty()){
            attacker = att.remove(0);
            def = defall.remove(0);
            Conflict(attacker, def);
        }
        
        System.out.println(currentPlayer.toString() + "'s Combat phase ends.");
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
    
    private void Conflict(Creature attacker, List<Creature> def){
        int remAtt = attacker.getPower();
        int remDef = attacker.getToughness();
        Creature currDef = def.remove(0);
        int da = currDef.getPower();
        int dd = currDef.getToughness();
        int dest = 0;
        
        while(true){
            currDef.defend(attacker); 
            currDef = def.remove(0);
        }
        
        
        
        
        
    }
    
    
    
    
    
    
}
