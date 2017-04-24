/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Manuel
 */
public abstract class AbstractDecorator extends AbstractCreature {
      protected final Creature decoratedCreature;
      
      public AbstractDecorator(Player p, Creature c){
          super(p);
          this.decoratedCreature = c;
      }
      
     @Override
     public int getPower(){
         return decoratedCreature.getPower();
     }
     
     
     @Override
     public int getToughness(){
         return decoratedCreature.getToughness();
     }
      
}


