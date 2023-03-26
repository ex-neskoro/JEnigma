package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.*;

public final class Rotor extends AbstractRotor implements Turnable {

    private String turnoverState;

    private Rotor(Language language, int initState) {
        super(language, initState);
    }

    public Rotor(Language language) {
        super(language);
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

    protected void setTurnoverState(String turnoverState) {
        if (turnoverState.length() > language.getSize()) {
            throw new IllegalArgumentException("Turnover letters size must be less than language size");
        }

        this.turnoverState = turnoverState;
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
        String[] stateArr = state.split(",");

        this.turnState = Integer.parseInt(stateArr[0]);

        movableList = new ArrayList<>(Arrays.asList(stateArr[1].split("")));

        turnoverState = stateArr[3].toLowerCase();
    }
}
