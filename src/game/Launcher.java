package game;

import state.MenuState;

public class Launcher {
    public static void main(String[] args) {
        callMenu();
    }

    public static void callMenu(){new MenuState();}
}
