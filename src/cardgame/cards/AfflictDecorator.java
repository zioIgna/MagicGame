/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.Decorator;
import cardgame.Creature;

/**
 *
 * @author ignaz
 */
public class AfflictDecorator extends Decorator {

    public AfflictDecorator(Creature c) {
        super(c);
    }

    public AfflictDecorator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getPower(){
        return super.getPower()-1;
    }
    
    @Override
    public int getToughness(){
        return super.getToughness()-1;
    }
    
}
