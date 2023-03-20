package ex.neskoro;

import ex.neskoro.language.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflector {

    private Language language;
    private Map<String, Integer> map;
    private ArrayList<String> inList;

    public Reflector(Language language) {
        this.language = language;
        inListInit();
        initMap();
    }

    // TODO add random initialization -> any Language init
    // Reflector A - https://en.wikipedia.org/wiki/Enigma_rotor_details
    private void inListInit() {
        inList = new ArrayList<>();
        inList.add("e");
        inList.add("j");
        inList.add("m");
        inList.add("z");
        inList.add("a");
        inList.add("l");
        inList.add("y");
        inList.add("x");
        inList.add("v");
        inList.add("b");
        inList.add("w");
        inList.add("f");
        inList.add("c");
        inList.add("r");
        inList.add("q");
        inList.add("u");
        inList.add("o");
        inList.add("n");
        inList.add("t");
        inList.add("s");
        inList.add("p");
        inList.add("i");
        inList.add("k");
        inList.add("h");
        inList.add("g");
        inList.add("d");
    }

    private void initMap() {
        map = new HashMap<>();
        int i = 0;
        for (String s : language.getAlphabet().split("")) {
            map.put(s, i++);
        }
    }

    public String getCsvState() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : inList) {
            stringBuilder.append(str);
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public String process(String s, int lastRotorState) {
        int index = map.get(s);

        int tempIndex = index - lastRotorState;

        if (tempIndex < 0) {
            tempIndex += language.getSize();
        }

        return inList.get(tempIndex % language.getSize());
    }

    public void importState(String csvLine) {
        String[] state = csvLine.split(",");
        inList = new ArrayList<>(List.of(state));
    }
}