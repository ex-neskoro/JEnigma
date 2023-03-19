package ex.neskoro;

import ex.neskoro.language.Language;

import java.util.HashMap;
import java.util.Map;

public class Reflector {

    private Language language;
    private Map<String, String> map;

    public Reflector(Language language) {
        this.language = language;
        initMap();
    }

    // TODO add random initialization -> any Language init
    // Reflector A - https://en.wikipedia.org/wiki/Enigma_rotor_details
    private void initMap() {
        map = new HashMap<>();
        map.put("a", "e");
        map.put("b", "j");
        map.put("c", "m");
        map.put("d", "z");
        map.put("e", "a");
        map.put("f", "l");
        map.put("g", "y");
        map.put("h", "x");
        map.put("i", "v");
        map.put("j", "b");
        map.put("k", "w");
        map.put("l", "f");
        map.put("m", "c");
        map.put("n", "r");
        map.put("o", "q");
        map.put("p", "u");
        map.put("q", "o");
        map.put("r", "n");
        map.put("s", "t");
        map.put("t", "s");
        map.put("u", "p");
        map.put("v", "i");
        map.put("w", "k");
        map.put("x", "h");
        map.put("y", "g");
        map.put("z", "d");
    }

    public String getCsvState() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getValue());
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public String process(String s) {
        return map.get(s);
    }

    public void importState(String csvLine) {
        String[] state = csvLine.split(",");

        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map.put(entry.getKey(), state[i++]);
        }
    }
}
