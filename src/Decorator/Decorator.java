
package Decorator;

import cardgame.Card;
import java.util.LinkedList;


public class Decorator {
    public Card c;
    public LinkedList<decoratorToken> deck = new LinkedList<>();
    
    //aggiungere un token alla lista
    public void addToken(){
        
    }
    
    //applicare i token alla carta 
    public void resolve(Card c){
        
    }
    
    //ritoernare la carta modificata
    public Card returnCard(){
        return this.c;
    }
    
    //controllo che la carta non sia stata distrutta
    public boolean isDead(){
        return false;
    }
    
    //rimuove gli effetti che finisco alla fine del turno
    public void banishFleeting(){
        
    }
    
    
}
