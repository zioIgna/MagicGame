/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

public interface TargetedEffect {

public interface Effect {
    // pays for effect and places it in the stack
    boolean play();
    
    // caso in cui la spell funzioni su una creatura
    void resolve(Card c);
}
}