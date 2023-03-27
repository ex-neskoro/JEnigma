package ex.neskoro;

import ex.neskoro.exception.CommutatorSizeException;
import ex.neskoro.language.Language;
import ex.neskoro.rotor.AbstractRotor;

import java.util.*;

public class Commutator {
    private LinkedHashMap<String, String> map;
    private Language language;
    private List<String> staticList;

    private Commutator() {
        map = new LinkedHashMap<>();
    }

    public Commutator(Language language) {
        this();
        this.language = language;
        staticList = new ArrayList<>(Arrays.asList(language.getAlphabet().split("")));
        initRandomMap();
    }

    private void initRandomMap() {
        Random random = new Random();

        int randomLetterCount = random.nextInt(2, language.getSize());
        if (randomLetterCount % 2 != 0) {
            randomLetterCount--;
        }

        ArrayList<Integer> lettersIndex = AbstractRotor.generateRandomLettersIndexes(language, randomLetterCount);

        for (int i = 0; i < randomLetterCount; i += 2) {
            addPair(staticList.get(lettersIndex.get(i)), staticList.get(lettersIndex.get(i + 1)));
        }
    }

    public void addPair(String a, String b) {
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

    public String exportState() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey());
        }

        return stringBuilder.toString();
    }

    public void importState(String state) {
        map = new LinkedHashMap<>();
        String[] letters = state.split("");

        for (int i = 0; i < letters.length; i += 2) {
            addPair(letters[i], letters[i+1]);
        }
    }

}
