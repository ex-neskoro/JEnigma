import ex.neskoro.Enigma;
import ex.neskoro.language.Language;
import ex.neskoro.language.LanguageAlphabet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnigmaTests {

    private final String HELLO = "Hello, world!";
    private final String EAT_CAKES_RUS = "съешь ещё этих мягких французских булок, да выпей чаю!";

    private final Language EN = new Language(LanguageAlphabet.EN);
    private final Language RU = new Language(LanguageAlphabet.RU);
    private final Language DEFAULT_LAN = EN;

    Enigma enigma;

    @BeforeEach
    void setUp() {
        enigma = new Enigma();
    }

    @Test
    void stateExportImport() {
        String initState = enigma.exportState();
        enigma = new Enigma();

        enigma.importState(initState);
        String exportedState = enigma.exportState();

        assertEquals(initState, exportedState);
    }

    @Test
    @DisplayName("Same state produce same output")
    void testSameStateSameIO() {
        String initState = enigma.exportState();

        String firstOut = enigma.processString(HELLO);
        enigma.importState(initState);

        String secondOut = enigma.processString(HELLO);

        assertEquals(firstOut, secondOut);
    }

    @Test
    void encodeDecodeEnglish() {
        assertTrue(encodeDecode(EN, HELLO));
    }

    @Test
    void encodeDecodeRussian() {
        assertTrue(encodeDecode(RU, EAT_CAKES_RUS));
    }

    @Test
    void testAllLanguages() {
        LanguageAlphabet[] alphabets = LanguageAlphabet.values();

        List<Language> languages = new ArrayList<>();
        for (LanguageAlphabet alphabet : alphabets) {
            languages.add(new Language(alphabet));
        }

        List<Boolean> expected = Collections.nCopies(alphabets.length, true);

        List<Boolean> result = new ArrayList<>();

        for (Language language : languages) {
            result.add(encodeDecode(language, language.getAlphabet()));
        }

        assertEquals(expected, result);
    }

    boolean encodeDecode(Language language, String init) {
        enigma = new Enigma(language, 3);
        String initState = enigma.exportState();

        String out = enigma.processString(init);

        enigma.importState(initState);

        String decoded = enigma.processString(out);

        return init.equals(decoded);
    }


}
