/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {
    public GameStateController() {
        super("Games for Education");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new LogoState());
        addState(new StartScreenState());
        addState(new LevelSelectionState());
        addState(new GameState());
    }

}
