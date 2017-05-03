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
public class AddPhase implements Phase{
    private final Phases phaseId;
    private int addNum;
    
    public AddPhase(Phases id) {
        phaseId=id;
        addNum=1;
    }
    public AddPhase(Phases id, int skip) {
        phaseId=id;
        addNum=skip;
    }
    
    
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        System.out.println(currentPlayer.name() + ": add " + phaseId.name() +" phase");
        --addNum;
        if (addNum==0) currentPlayer.setPhase(phaseId,this);
    }    
}
