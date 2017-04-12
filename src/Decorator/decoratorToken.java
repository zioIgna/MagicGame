
package Decorator;


public class decoratorToken {
    private boolean fleeting;
    
    //contiene l'effetto che modifica la carta
    public void effect(){
        
    }
    
    
    //un effetto è fleeting se finisce alla fine del turno
    public boolean isFleeting(){
        return false;
    }
    
    
}
