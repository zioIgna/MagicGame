/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author ignaz
 */
public abstract class AbstractInstantCardEffect extends AbstractCardEffect{

    protected AbstractInstantCardEffect(Player p, Card c) {
        super(p, c);
    }
    
    protected abstract Instant createInstant();

    @Override
    public void resolve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
