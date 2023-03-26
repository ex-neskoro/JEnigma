package ex.neskoro;

import ex.neskoro.exception.CommutatorSizeException;
import ex.neskoro.language.Language;

import java.util.HashMap;
import java.util.Objects;

public class Commutator {
    private HashMap<String, String> map;
    private Language language;

    private Commutator() {
        map = new HashMap<>();
    }

    public Commutator(Language language) {
        this();
        this.language = language;
    }

    public void addPair(String a, String b) throws IllegalArgumentException {
        if (!(language.getAlphabet().contains(a) && language.getAlphabet().contains(b))) {
            throw new IllegalArgumentException("Commutator language do not contain letter");
        }

        if (map.size() <= language.getSize() - 2) {
            map.put(a, b);
            map.put(b, a);
        } else {
            throw new CommutatorSizeException();
        }
    }

    public String processLetter(String letter) {
        return Objects.requireNonNullElse(map.get(letter), letter);
    }

    // todo export/import methods
}
