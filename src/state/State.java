package state;

import contracts.Updatable;
import game.GameEngine;

public abstract class State implements Updatable {
        protected GameEngine game;

        public State(GameEngine game){
            this.game = game;
        }

}
