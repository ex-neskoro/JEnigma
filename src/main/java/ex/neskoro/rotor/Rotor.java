package ex.neskoro.rotor;

import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;
import ex.neskoro.rotor.AbstractRotor;

import java.util.*;

public final class Rotor extends AbstractRotor implements Turnable {

    private Rotor(Language language, int initState) {
        super(language, initState);
    }

    public Rotor(Language language) {
        super(language);
    }

    public Rotor(String movableList) {
        super(movableList);
    }

    @Override
    public int turn() {
        state = ++state % language.getSize();
        return state;
    }

    @Override
    protected void movableListInit() {
        super.movableListInit();
        Collections.shuffle(movableList);
    }
}
