package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.Collections;

public class EntryRotor extends AbstractRotor {
    public EntryRotor(Language language, int initState) {
        super(language, initState);
    }

    public EntryRotor(Language language) {
        this(language, 0);
        Collections.shuffle(movableList);
    }

    protected EntryRotor(String movableList) {
        super(movableList);
    }

    @Override
    public void setTurnState(int turnState) {
        if (turnState != 0) {
            throw new IllegalArgumentException("Reflector state must be 0");
        }
        super.setTurnState(turnState);
    }
}
