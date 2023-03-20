package ex.neskoro;

import ex.neskoro.language.Language;

import java.util.*;

public class Reflector {

    private Language language;
    private List<String> movableList;
    private List<String> staticList;

    public Reflector(Language language) {
        this.language = language;
        movableListInit();
        staticListInit();
    }

    // TODO add random initialization -> any Language init
    // Reflector A - https://en.wikipedia.org/wiki/Enigma_rotor_details
    private void movableListInit() {
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

    private void staticListInit() {
        staticList = new ArrayList<>();
        staticList.addAll(Arrays.asList(language.getAlphabet().split("")));
    }

    public String getCsvState() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : movableList) {
            stringBuilder.append(str);
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public String process(String s, int lastRotorState) {
        int index = staticList.indexOf(s);

        int tempIndex = index - lastRotorState;

        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        return movableList.get(tempIndex % language.getSize());
    }

    public void importState(String csvLine) {
        String[] state = csvLine.split(",");
        movableList = new ArrayList<>(List.of(state));
    }
}