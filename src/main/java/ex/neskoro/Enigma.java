package ex.neskoro;

import ex.neskoro.language.Language;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Enigma {
    private Language language;
    private LinkedList<Rotor> rotors;
    private Reflector reflector;

    public Enigma(Language language, int rotorCount) {
        if (rotorCount > 15) {
            rotorCount = 15;
        }
        this.language = language;
        rotors = new LinkedList<>();

        for (; rotorCount > 0; rotorCount--) {
            rotors.add(new Rotor(language));
        }

        reflector = new Reflector(language);
    }

    public String processString(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean lowerCase;

        for (String str : string.split("")) {
            lowerCase = str.equals(str.toLowerCase());

            str = str.toLowerCase();

            if (language.getAlphabet().contains(str)) {
                str = lowerCase ? processLetter(str) : processLetter(str).toUpperCase();
            }
            stringBuilder.append(str);
        }

        return stringBuilder.toString();
    }

    // TODO add Commutator
    private String processLetter(String s) {
        s = processLetterIn(s);
        s = reflector.process(s);
        s = processLetterOut(s);

        return s;
    }

    private String processLetterIn(String s) {
        boolean turnNextRotor = true;
        String currentLetter = s;

        for (Rotor rotor : rotors) {
            if (turnNextRotor) {
                if (rotor.turn() != 0) {
                    turnNextRotor = false;
                }
            }
            currentLetter = rotor.processLetterIn(currentLetter);
        }
        return currentLetter;
    }

    private String processLetterOut(String s) {
        Iterator<Rotor> iterator = rotors.descendingIterator();

        while (iterator.hasNext()) {
            s = iterator.next().processLetterOut(s);
        }

        return s;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int rotorCount = 1;

        for (Rotor rotor : rotors) {
            builder.append("Rotor ").append(rotorCount++).append("\n");
            builder.append(rotor);
            builder.append("\n\n");
        }
        return builder.toString();
    }

    public String getRotorsState() {
        StringBuilder rotorBuilder = new StringBuilder("Rotors state:\n");
        int rotorCount = 1;
        StringBuilder delimiter = new StringBuilder();
        StringBuilder stateBuilder = new StringBuilder();
        for (Rotor rotor : rotors) {
            rotorBuilder.append(rotorCount++).append("|");
            delimiter.append("--");
            stateBuilder.append(rotor.getState()).append("|");
        }
        return rotorBuilder.append("\n")
                .append(delimiter.append("\n"))
                .append(stateBuilder).toString();
    }

    public void exportState() {
        StringBuilder sb = new StringBuilder();
        for (Rotor rotor : rotors) {
            sb.append(rotor.getCsvState());
            sb.append("\n");
        }

        sb.append(reflector.getCsvState());
        sb.append("\n");

        System.out.println(sb);
    }

    public void importState() {
        Scanner sc = new Scanner(System.in);

        for (Rotor rotor : rotors) {
            rotor.importState(sc.nextLine());
        }

        reflector.importState(sc.nextLine());
    }
}
