/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author Manuel
 */
public class AfflictDecorator extends AbstractDecorator {
    
    public AfflictDecorator(Player p, Creature c){
        super(p, c);
       
      }
    
    @Override
     public int getPower(){
         return decoratedCreature.getPower() -1;
     }
     
     
     @Override
     public int getToughness(){
         return decoratedCreature.getToughness() -1;
     }

    @Override
    public List<Effect> effects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Effect> avaliableEffects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    

    
}
