package ex.neskoro.rotor;

import ex.neskoro.language.Language;

public class EntryRotor extends AbstractRotor {
    public EntryRotor(Language language, int initState) {
        super(language, initState);
    }

    public EntryRotor(Language language) {
        this(language, 0);
    }

    protected EntryRotor(String movableList) {
        super(movableList);
    }

    @Override
    public void setState(int state) {
        if (state != 0) {
            throw new IllegalArgumentException("Reflector state must be 0");
        }
        super.setState(state);
    }
}
