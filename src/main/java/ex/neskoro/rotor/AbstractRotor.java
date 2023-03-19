package ex.neskoro.rotor;

import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;

import java.util.*;

public abstract class AbstractRotor {
    protected int state;
    protected Language language;
    protected List<String> movableList;
    protected List<String> staticList;

    protected AbstractRotor(Language language, int initState) {
        this.language = language;
        setState(initState);
        staticListInit();
        movableListInit();
    }

    protected AbstractRotor(String movableList) {
        this(new EnLanguage());
        movableListInit(movableList);
    }

    protected AbstractRotor(Language language) {
        this(language, 0);
    }

    protected void staticListInit() {
        staticList = new ArrayList<>(List.of(language.getAlphabet().split("")));
    }

    protected void movableListInit() {
        movableList = new ArrayList<>(List.of(language.getAlphabet().split("")));
    }
    protected void movableListInit(String movableList) {
        this.movableList = new ArrayList<>(List.of(movableList.split("")));
    }

    private void appendLetter(StringBuilder builder, String s) {
        builder.append(s);
        builder.append(" ");
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state % language.getSize();
    }

    public String processLetterIn(String s, int prevRotorState) {
        int index = staticList.indexOf(s);

        int tempIndex = index + state - prevRotorState;
        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        s = movableList.get(tempIndex % language.getSize());

        return s;
    }

    public String processLetterOut(String s, int prevRotorState) {
        int index = staticList.indexOf(s);

        int tempIndex = index + state - prevRotorState;
        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        s = staticList.get(tempIndex % language.getSize());

        s = staticList.get(movableList.indexOf(s));

        return s;
    }

    public String exportState() {
        StringBuilder sb = new StringBuilder();
        sb.append(state);

        for (String s : movableList) {
            sb.append(",");
            sb.append(s);
        }

        return sb.toString();
    }

    public void importState(String csvState) {
        String[] stateArr = csvState.split(",");
        setState(Integer.parseInt(stateArr[0]));

        for (int i = 1; i < stateArr.length; i++) {
            movableList.remove(i - 1);
            movableList.add(i - 1, stateArr[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        StringBuilder delimiter = new StringBuilder();

        int i = 0;
        for (String str : staticList) {
            appendLetter(in, str.toUpperCase());
            appendLetter(out, movableList.get(i++).toUpperCase());
            delimiter.append("--");
        }
        return "State: %d".formatted(state) +
                System.lineSeparator() +
                in.append(System.lineSeparator()).append(delimiter.append(System.lineSeparator())).append(out);
    }

}
