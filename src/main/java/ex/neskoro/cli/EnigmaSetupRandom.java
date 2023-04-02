package ex.neskoro.cli;

import ex.neskoro.Enigma;
import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "random",
        sortOptions = false,
        description = "Generate Enigma setup in random way - all it's components would have random state")
public class EnigmaSetupRandom implements Callable<Integer> {

    @Option(names = {"-r", "--rotors"},
            defaultValue = "3",
            description = "Rotors count to setup in Enigma [from 1 to 100] (default: ${DEFAULT-VALUE})")
    private int rotorsCount;

    @Option(names = {"-l", "--language"},
            defaultValue = "EN",
            description = "Language to setup in Enigma (default: ${DEFAULT-VALUE}). Valid values: ${COMPLETION-CANDIDATES}")
    private LanguageAlphabet languageAlphabet;

    @Override
    public Integer call() throws Exception {
        if (rotorsCount < 1) {
            throw new IllegalArgumentException("Rotors count must be more than 1");
        }

        Enigma enigma = new Enigma(new Language(languageAlphabet), rotorsCount);

        System.out.println(enigma.exportState());

        return 0;
    }
}
