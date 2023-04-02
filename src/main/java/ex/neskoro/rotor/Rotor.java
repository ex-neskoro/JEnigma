package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Rotor extends AbstractRotor implements Turnable {
    private String turnoverState;
    private static final int MIN_TURNOVER_STATE_COUNT = 1;

    public Rotor(Language language) {
        super(language);
        Collections.shuffle(movableList);
        initRandomTurnoverState();
    }

    public Rotor(String movableList, String turnoverState) {
        super(movableList);
        setTurnoverState(turnoverState);
    }

    @Override
    public boolean turn() {
        boolean turnNextRotor = false;

        turnState = ++turnState % language.getSize();
        if (turnoverState.contains(staticList.get(turnState))) {
            turnNextRotor = true;
        }

        return turnNextRotor;
    }

    @Override
    protected void movableListInit() {
        super.movableListInit();
        Collections.shuffle(movableList);
    }

    private void initRandomTurnoverState() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        int randomLetterCount = random.nextInt(MIN_TURNOVER_STATE_COUNT, language.getSize());

        ArrayList<Integer> lettersIndex = AbstractRotor.generateRandomLettersIndexes(language, randomLetterCount);

        for (Integer i : lettersIndex) {
            stringBuilder.append(staticList.get(i));
        }

        turnoverState = stringBuilder.toString();
    }

    private void setTurnoverState(String turnoverState) {
        if (turnoverState.length() > language.getSize()) {
            throw new IllegalArgumentException("Turnover letters size must be less than language size");
        }

        this.turnoverState = turnoverState.toLowerCase();
    }

    @Override
    public String exportState() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(turnState);
        stringBuilder.append(",");

        for (String letter : movableList) {
            stringBuilder.append(letter);
        }
        stringBuilder.append(",");

        stringBuilder.append(turnoverState);

        return stringBuilder.toString();
    }

    @Override
    public void importState(String state) {
        super.importState(state);

        String[] stateArr = state.split(",");

        turnoverState = stateArr[2].toLowerCase();
    }
}
