package ex.neskoro;

import ex.neskoro.language.Language;

import java.util.*;
import java.util.Map.Entry;

public class Rotor {

    // for en: 0 - 25
    // for ru: 0 - 32
    private int state;
    private final Language language;
    private final HashMap<String, Integer> map;
    private ArrayList<String> inList;
    private ArrayList<String> outList;

    public Rotor(Language language) {
        this.language = language;

        state = new Random().nextInt(language.getSize());

        map = new HashMap<>();
        mapInit();

        inListInit();

        outListInit();
    }

    private void mapInit() {
        int i = 0;
        for (String s : language.getAlphabet().split("")) {
            map.put(s, i++);
        }
    }

    private void inListInit() {
        inList = new ArrayList<>(List.of(language.getAlphabet().split("")));
        Collections.shuffle(inList);
    }

    private void outListInit() {
        outList = new ArrayList<>(inList);

        String[] alphabet = language.getAlphabet().split("");

        for (int i = 0; i < language.getSize(); i++) {
            int index = map.get(inList.get(i));
            outList.remove(index);
            outList.add(index, alphabet[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        StringBuilder delimiter = new StringBuilder();

        int i = 0;
        for (Entry<String, Integer> entry : map.entrySet()) {
            appendLetter(in, entry.getKey().toUpperCase());
            appendLetter(out, inList.get(i++).toUpperCase());
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

        for (String s : inList) {
            sb.append(",");
            sb.append(s);
        }

        return sb.toString();
    }

    public void importState(String csvState) {
        String[] stateArr = csvState.split(",");
        setState(Integer.parseInt(stateArr[0]));

        for (int i = 1; i < stateArr.length; i++) {
            inList.remove(i - 1);
            inList.add(i - 1, stateArr[i]);
        }

        outListInit();
    }

    public int turn() {
        state = ++state % language.getSize();
        return state;
    }

    public String processLetterIn(String s) {
        int index = map.get(s);
        s = inList.get((index + state) % language.getSize());

        return s;
    }

    public String processLetterOut(String s) {
        int index = map.get(s);
        s = outList.get((index + state) % language.getSize());

        return s;
    }

}
