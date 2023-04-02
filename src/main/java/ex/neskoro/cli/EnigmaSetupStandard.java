package ex.neskoro.cli;

import ex.neskoro.Enigma;
import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;
import ex.neskoro.rotor.RotorFabric;
import ex.neskoro.rotor.StandardEntryRotorType;
import ex.neskoro.rotor.StandardReflectorType;
import ex.neskoro.rotor.StandardRotorType;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.concurrent.Callable;

@Command(name = "standard",
        sortOptions = false,
        description = "Generate Enigma setup from specified standard components - components which real-life enigmas had")
public class EnigmaSetupStandard implements Callable<Integer> {

    @Option(names = {"-c", "--commutator"},
            required = false,
            defaultValue = "",
            description = "Commutator to put in Enigma. Commutator state contains map of input letters the way, that all letters split into pairs: the first letter is replaced by second and second by first. Must contain even number of letters.")
    private String commutatorState;
    @Option(names = {"-er", "--entry-rotor"},
            required = true,
            description = "Standard entry rotor to put in Enigma. Valid values: ${COMPLETION-CANDIDATES}")
    private StandardEntryRotorType entryRotorType;
    @Option(names = {"-sr", "--standard-rotors"},
            required = true,
            split = ",",
            description = "Standard rotors to put in Enigma. Valid values: ${COMPLETION-CANDIDATES}")
    private ArrayList<StandardRotorType> rotorTypeList;
    @Option(names = {"-ref", "--reflector"},
            required = true,
            description = "Standard reflector to put in Enigma. Valid values: ${COMPLETION-CANDIDATES}")
    private StandardReflectorType reflectorType;

    @Override
    public Integer call() throws Exception {
        int rotorCount = rotorTypeList.size();

        if (rotorCount < 1) {
            throw new IllegalArgumentException("Rotors count must be more than 1");
        }

        Enigma enigma = new Enigma(new Language(LanguageAlphabet.EN), rotorCount);

        StringBuilder stateBuilder = new StringBuilder();
        stateBuilder.append(commutatorState);
        stateBuilder.append(System.lineSeparator());

        stateBuilder.append(RotorFabric.getEntryRotor(entryRotorType).exportState());
        stateBuilder.append(System.lineSeparator());

        for (StandardRotorType type : rotorTypeList) {
            stateBuilder.append(RotorFabric.getRotor(type).exportState());
            stateBuilder.append(System.lineSeparator());
        }

        stateBuilder.append(RotorFabric.getReflector(reflectorType).exportState());

        enigma.importState(stateBuilder.toString());

        System.out.println(enigma.exportState());

        return 0;
    }


}
