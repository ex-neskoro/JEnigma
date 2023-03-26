package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.*;

public final class Reflector extends AbstractRotor {

    public Reflector(Language language, int initState) {
        super(language, initState);
    }

    public Reflector(Language language) {
        super(language);
    }

    public Reflector(String movableList) {
        super(movableList);
    }

    // TODO add random initialization -> any Language init
    // Reflector A - https://en.wikipedia.org/wiki/Enigma_rotor_details
    @Override
    protected void movableListInit() {
        movableList = new ArrayList<>();
        movableList.add("e");
        movableList.add("j");
        movableList.add("m");
        movableList.add("z");
        movableList.add("a");
        movableList.add("l");
        movableList.add("y");
        movableList.add("x");
        movableList.add("v");
        movableList.add("b");
        movableList.add("w");
        movableList.add("f");
        movableList.add("c");
        movableList.add("r");
        movableList.add("q");
        movableList.add("u");
        movableList.add("o");
        movableList.add("n");
        movableList.add("t");
        movableList.add("s");
        movableList.add("p");
        movableList.add("i");
        movableList.add("k");
        movableList.add("h");
        movableList.add("g");
        movableList.add("d");
    }

    @Override
    public void setTurnState(int turnState) {
        if (turnState != 0) {
            throw new IllegalArgumentException("Reflector state must be 0");
        }
        super.setTurnState(turnState);
    }
}