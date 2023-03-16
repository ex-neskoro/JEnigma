package ex.neskoro;

import java.util.LinkedList;
import java.util.Scanner;

public class Enigma {
    private Language language;
    private LinkedList<Rotor> rotors;

    public Enigma(Language language, int rotorCount) {
        if (rotorCount > 15) {
            rotorCount = 15;
        }
        this.language = language;
        rotors = new LinkedList<>();

        for (; rotorCount > 0; rotorCount--) {
            rotors.add(new Rotor(language));
        }
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
            sb.append(rotor.returnCsvState());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void importState() {
        Scanner sc = new Scanner(System.in);

        for (Rotor rotor : rotors) {
            rotor.importState(sc.nextLine());
        }
    }
}
