package ex.neskoro;

import java.util.*;

public class Rotor {

    // for en: 0 - 25
    // for ru: 0 - 32
    private int state;
    private Language language;
    private HashMap<String, String> map;

    public Rotor(Language language) {
        this.language = language;
        map = new HashMap<>();
        mapInit(language);
    }

    private void mapInit(Language lan) {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(lan.getAlphabet().split("")));
        ArrayList<String> referenceArr = new ArrayList<>(arr);
        Collections.shuffle(referenceArr);

        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), referenceArr.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder in = new StringBuilder();
        StringBuilder out = new StringBuilder();
        StringBuilder delimiter = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            appendLetter(in, entry.getKey().toUpperCase());
            appendLetter(out, entry.getValue().toUpperCase());
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
}
