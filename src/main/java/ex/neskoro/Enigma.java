package ex.neskoro;

import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;
import ex.neskoro.rotor.EntryRotor;
import ex.neskoro.rotor.Reflector;
import ex.neskoro.rotor.Rotor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Enigma {
    private Language language;
    private Commutator commutator;
    private EntryRotor entryRotor;
    private LinkedList<Rotor> rotors;
    private Reflector reflector;

    public Enigma(Language language, int rotorCount) {
        if (rotorCount > 15) {
            rotorCount = 15;
        }
        this.language = language;
        rotors = new LinkedList<>();

        commutator = new Commutator(language);

        entryRotor = new EntryRotor(language);

        for (int i = 0; i < rotorCount; i ++) {
            rotors.add(new Rotor(language));
        }

        reflector = new Reflector(language);
    }

    public Enigma() {
        this(new EnLanguage(), 3);
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

    // TODO add Commutator
    private String processLetter(String letter) {
        letter = commutator.processLetter(letter);

        letter = processLetterIn(letter);

        int lastRotorState = rotors.getLast().getState();
        letter = reflector.processLetterIn(letter, lastRotorState);

        letter = processLetterOut(letter);

        letter = commutator.processLetter(letter);

        return letter;
    }

    private String processLetterIn(String letter) {
        boolean turnNextRotor = true;

        int prevRotorState = 0;

        for (Rotor rotor : rotors) {
            if (turnNextRotor) {
                if (rotor.turn() != 0) {
                    turnNextRotor = false;
                }
            }
            letter = rotor.processLetterIn(letter, prevRotorState);
            prevRotorState = rotor.getState();
        }
        return letter;
    }

    private String processLetterOut(String letter) {
        Iterator<Rotor> iterator = rotors.descendingIterator();

        int prevRotorState = 0;

        while (iterator.hasNext()) {
            Rotor currentRotor = iterator.next();
            letter = currentRotor.processLetterOut(letter, prevRotorState);
            prevRotorState = currentRotor.getState();
        }

        letter = entryRotor.processLetterIn(letter, prevRotorState);

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

    public String getRotorsState() {
        StringBuilder rotorBuilder = new StringBuilder("Rotors state:" + System.lineSeparator());
        int rotorCount = 1;

        StringBuilder delimiter = new StringBuilder();
        StringBuilder stateBuilder = new StringBuilder();

        for (Rotor rotor : rotors) {
            rotorBuilder.append(rotorCount++).append("|");
            delimiter.append("--");
            stateBuilder.append(rotor.getState()).append("|");
        }

        return rotorBuilder.append(System.lineSeparator())
                .append(delimiter.append(System.lineSeparator()))
                .append(stateBuilder).toString();
    }

    public String exportState() {
        StringBuilder sb = new StringBuilder();
        for (Rotor rotor : rotors) {
            sb.append(rotor.exportState());
            sb.append(System.lineSeparator());
        }

        sb.append(reflector.exportState());
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    public void importState() {
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        while (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine());
        }

        importState(stringBuilder.toString());
    }

    public void importState(String state) {
        String[] lines = state.split(System.lineSeparator());
        int i = 0;

        for (Rotor rotor : rotors) {
            rotor.importState(lines[i++]);
        }

        reflector.importState(lines[i]);
    }
}
