/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author ignaz
 */
public class BronzeSableCreature extends AbstractCreature {

    public BronzeSableCreature(Player owner) {
        super(owner);
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public int getToughness() {
        return 1;
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
        return "Bronze Sable";
    }
    
}
