package ex.neskoro.cli;

import ex.neskoro.Enigma;
import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(name = "run",
        sortOptions = false,
        description = "Command for encode/decode with Enigma, it have one algorithm to encode and decode text.")
public class EnigmaRun implements Callable<Integer> {
    @Option(names = {"-out", "--file-output"},
            description = "")
    private Optional<Path> fileOut;

    @Option(names = {"-in", "--file-input"},
            required = true,
            description = "File with text to encode/decode through Enigma.")
    private Path fileInput;

    @Option(names = {"-s", "--state"},
            required = true,
            description = "")
    private Optional<Path> enigmaState;

    @Override
    public Integer call() throws Exception {
        Enigma enigma;

        if (enigmaState.isEmpty()) {
            enigma = getEnigmaFromSysIn();
        } else {
            enigma = getEnigmaFromFile(enigmaState.get());
        }

        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(fileInput, StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(enigma.processString(s)).append(System.lineSeparator()));
        } catch (IOException e) {
            throw new IOException("Cannot find file to output text");
        }

        if (fileOut.isEmpty()) {
            System.out.print(contentBuilder);
        } else {
            Files.writeString(fileOut.get(), contentBuilder.toString());
        }

        return 0;
    }

    private Enigma getEnigmaFromSysIn() {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        String metaInfo = sc.nextLine();
        String[] infoArr = metaInfo.split(",");

        LanguageAlphabet languageAlphabet = LanguageAlphabet.valueOf(infoArr[0]);
        int rotorCount = Integer.parseInt(infoArr[1]);

        String state;
        while (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine());
            stringBuilder.append(System.lineSeparator());
        }

        state = stringBuilder.toString();

        Enigma enigma = new Enigma(new Language(languageAlphabet), rotorCount);
        enigma.importState(state);

        return enigma;
    }

    private Enigma getEnigmaFromFile(Path path) throws IOException {
        String state;
        try {
            state = Files.readString(path);
        } catch (IOException e) {
            throw new IOException("Unable to read file path with Enigma state");
        }
        String[] stateLines = state.split(System.lineSeparator());
        String metaInfo = stateLines[0];
        String[] infoArr = metaInfo.split(",");

        LanguageAlphabet languageAlphabet = LanguageAlphabet.valueOf(infoArr[0]);
        int rotorCount = Integer.parseInt(infoArr[1]);

        Enigma enigma = new Enigma(new Language(languageAlphabet), rotorCount);
        enigma.importState(state);

        return enigma;
    }
}
