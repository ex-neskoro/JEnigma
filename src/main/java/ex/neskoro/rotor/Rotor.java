package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.*;

public final class Rotor extends AbstractRotor implements Turnable {

    private int[] turnoverState;

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
    public boolean turn() {
        boolean turnNextRotor = false;

        state = ++state % language.getSize();
        for (int state : turnoverState) {
            if (state == this.state) {
                turnNextRotor = true;
                break;
            }
        }

        return turnNextRotor;
    }

    @Override
    protected void movableListInit() {
        super.movableListInit();
        Collections.shuffle(movableList);
    }

    protected void setTurnoverState(String[] turnoverLetters) {
        if (turnoverLetters.length > language.getSize()) {
            throw new IllegalArgumentException("Turnover letters size must be less than language size");
        }
        turnoverState = new int[turnoverLetters.length];
        for (int i = 0; i < turnoverLetters.length; i++) {
            turnoverState[i] = staticList.indexOf(turnoverLetters[i]);
        }
    }
}
