package ex.neskoro.rotor;

import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class AbstractRotor {
    protected int turnState;
    protected Language language;
    protected List<String> movableList;
    protected List<String> staticList;

    protected AbstractRotor(Language language, int initTurnState) {
        this.language = language;
        setTurnState(initTurnState);
        listsInit();
    }

    protected AbstractRotor(Language language) {
        this(language, 0);
    }

    protected AbstractRotor(String movableList) {
        this(new Language(LanguageAlphabet.EN));
        movableListInit(movableList);
    }

    protected void listsInit() {
        staticListInit();
        movableListInit();
    }

    protected void staticListInit() {
        staticList = new ArrayList<>(List.of(language.getAlphabet().split("")));
    }

    protected void movableListInit() {
        movableList = new ArrayList<>(List.of(language.getAlphabet().split("")));
    }

    protected void movableListInit(String movableList) {
        String[] letters = movableList.split("");
        for (int i = 0; i < letters.length; i++) {
            letters[i] = letters[i].toLowerCase();
        }
        this.movableList = new ArrayList<>(List.of(letters));
    }

    public int getTurnState() {
        return turnState;
    }

    public void setTurnState(int turnState) {
        this.turnState = turnState % language.getSize();
    }

    public String processLetterIn(String s, int prevRotorState) {
        int index = staticList.indexOf(s);

        int tempIndex = index + turnState - prevRotorState;
        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        s = movableList.get(tempIndex % language.getSize());

        return s;
    }

    public String processLetterOut(String s, int prevRotorState) {
        int index = staticList.indexOf(s);

        int tempIndex = index + turnState - prevRotorState;
        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        s = staticList.get(tempIndex % language.getSize());

        s = staticList.get(movableList.indexOf(s));

        return s;
    }

    public String exportState() {
        StringBuilder sb = new StringBuilder();
        sb.append(turnState);
        sb.append(",");

        for (String s : movableList) {
            sb.append(s);
        }

        return sb.toString();
    }

    public void importState(String state) {
        String[] stateArr = state.split(",");
        setTurnState(Integer.parseInt(stateArr[0]));

        movableList = new ArrayList<>(Arrays.asList(stateArr[1].split("")));
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
        return "State: %d".formatted(turnState) +
                System.lineSeparator() +
                in.append(System.lineSeparator()).append(delimiter.append(System.lineSeparator())).append(out);
    }

    private void appendLetter(StringBuilder builder, String s) {
        builder.append(s);
        builder.append(" ");
    }

    public static ArrayList<Integer> generateRandomLettersIndexes(Language language, int lettersCount) {
        Random random = new Random();

        ArrayList<Integer> lettersIndexes = new ArrayList<>(lettersCount);
        for (int i = 0; i < lettersCount; i++) {
            lettersIndexes.add(Integer.MAX_VALUE);
        }

        for (int i = 0; i < lettersCount; i++) {
            Integer randomIndex;
            do {
                randomIndex = random.nextInt(language.getSize());
                lettersIndexes.set(i, randomIndex);
            } while (lettersIndexes.indexOf(randomIndex) != lettersIndexes.lastIndexOf(randomIndex));
        }

        return lettersIndexes;
    }
}
