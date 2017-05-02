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
public abstract class Decorator implements Creature {
    private Creature decoratedCreature;
    
    public Decorator (Creature c){
        decoratedCreature=c;
        decoratedCreature.setDecorator(this);
    }
    
    @Override
    public void setDecorator (Creature c){
        decoratedCreature.setDecorator(c);
    }

    @Override
    public boolean tap(){
        return decoratedCreature.tap();
    }
    
    @Override
    public boolean untap(){
        return decoratedCreature.untap();
    }
    @Override
    public boolean isTapped(){
        return decoratedCreature.isTapped();
    }
    @Override
    public void attack(){
        decoratedCreature.attack();
    }
    @Override
    public void defend(Creature c){
        decoratedCreature.defend(c);
    }
    @Override
    public void inflictDamage(int dmg){
        decoratedCreature.inflictDamage(dmg);
    }
    @Override
    public void resetDamage(){
        decoratedCreature.resetDamage();
    }
    @Override
    public int getPower(){
        return decoratedCreature.getPower();
    }
    @Override
    public int getToughness(){
        return decoratedCreature.getToughness();
    }
    @Override
    public List<Effect> effects() {
        return decoratedCreature.effects();
    }

    @Override
    public List<Effect> avaliableEffects() {
        return decoratedCreature.avaliableEffects();
    }

    @Override
    public String name() {
        return decoratedCreature.name();
    }

    @Override
    public void insert() {
        decoratedCreature.insert();
    }

    @Override
    public void remove() {
        decoratedCreature.remove();
    }
}
