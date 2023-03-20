package ex.neskoro;

import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;

import java.util.*;

public class Rotor {

    // for en: 0 - 25
    // for ru: 0 - 32
    private int state;
    protected final Language language;
    protected List<String> movableList;
    protected List<String> staticList;

    public Rotor(Language language, int initState) {
        this.language = language;
        state = initState;
        staticListInit();
        movableListInit();
    }

    public Rotor(Language language) {
        this(language, new Random().nextInt(language.getSize()));
    }

    public Rotor() {
        this(new EnLanguage());
    }

    private void staticListInit() {
        staticList = new ArrayList<>(Arrays.asList(language.getAlphabet().split("")));
    }

    protected void movableListInit() {
        movableList = new ArrayList<>(List.of(language.getAlphabet().split("")));
        Collections.shuffle(movableList);
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
        return "State: %d\n".formatted(state) + in.append("\n").append(delimiter.append("\n")).append(out);
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

    public String getCsvState() {
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

    public int turn() {
        state = ++state % language.getSize();
        return state;
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

}
