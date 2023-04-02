package ex.neskoro;

import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;
import ex.neskoro.rotor.EntryRotor;
import ex.neskoro.rotor.Reflector;
import ex.neskoro.rotor.Rotor;

import java.util.Iterator;
import java.util.LinkedList;

public class Enigma {
    private final Language language;
    private final Commutator commutator;
    private final EntryRotor entryRotor;
    private final LinkedList<Rotor> rotors;
    private final Reflector reflector;

    public Enigma() {
        this(new Language(LanguageAlphabet.EN), 3);
    }

    public Enigma(Language language, int rotorCount) {
        if (rotorCount > 100) {
            rotorCount = 100;
        }
        this.language = language;
        rotors = new LinkedList<>();

        commutator = new Commutator(language);

        entryRotor = new EntryRotor(language);

        for (int i = 0; i < rotorCount; i++) {
            rotors.add(new Rotor(language));
        }

        reflector = new Reflector(language);
    }

    public String processString(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean lowerCase;

        for (String letter : string.split("")) {
            lowerCase = letter.equals(letter.toLowerCase());

            letter = letter.toLowerCase();

            if (language.getAlphabet().contains(letter)) {
                letter = processLetter(letter);
                if (!lowerCase) {
                    letter = letter.toUpperCase();
                }
            }
            stringBuilder.append(letter);
        }

        return stringBuilder.toString();
    }

    private String processLetter(String letter) {
        letter = commutator.processLetter(letter);

        letter = processLetterIn(letter);

        int lastRotorTurnState = rotors.getLast().getTurnState();
        letter = reflector.processLetterIn(letter, lastRotorTurnState);

        letter = processLetterOut(letter);

        letter = commutator.processLetter(letter);

        return letter;
    }

    private String processLetterIn(String letter) {
        int prevRotorState = 0;

        letter = entryRotor.processLetterIn(letter, prevRotorState);

        boolean turnNextRotor = true;
        for (Rotor rotor : rotors) {
            if (turnNextRotor) {
                turnNextRotor = rotor.turn();
            }
            letter = rotor.processLetterIn(letter, prevRotorState);
            prevRotorState = rotor.getTurnState();
        }
        return letter;
    }

    private String processLetterOut(String letter) {
        Iterator<Rotor> iterator = rotors.descendingIterator();

        int prevRotorState = 0;

        Rotor currentRotor;
        while (iterator.hasNext()) {
            currentRotor = iterator.next();
            letter = currentRotor.processLetterOut(letter, prevRotorState);
            prevRotorState = currentRotor.getTurnState();
        }

        letter = entryRotor.processLetterOut(letter, prevRotorState);

        return letter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int rotorCount = 1;

        for (Rotor rotor : rotors) {
            builder.append("Rotor ").append(rotorCount++).append(System.lineSeparator());
            builder.append(rotor);
            builder.append(System.lineSeparator()).append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String exportState() {
        StringBuilder sb = new StringBuilder();

        sb.append(language.getAlphabetType().name());
        sb.append(",");
        sb.append(rotors.size());
        sb.append(System.lineSeparator());

        sb.append(commutator.exportState());
        sb.append(System.lineSeparator());

        sb.append(entryRotor.exportState());
        sb.append(System.lineSeparator());


        for (Rotor rotor : rotors) {
            sb.append(rotor.exportState());
            sb.append(System.lineSeparator());
        }

        sb.append(reflector.exportState());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public void importState(String state) {
        String[] lines = state.split(System.lineSeparator());

        commutator.importState(lines[1]);

        entryRotor.importState(lines[2]);

        int i = 3;
        for (Rotor rotor : rotors) {
            rotor.importState(lines[i++]);
        }

        reflector.importState(lines[i]);
    }
}
