import ex.neskoro.Enigma;
import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;
import ex.neskoro.language.RuLanguage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnigmaTests {

    private final String HELLO = "Hello, world!";
    private final String HELLO_RUS = "Привет, мир!";

    private final Language EN = new EnLanguage();
    private final Language RU = new RuLanguage();
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
        encodeDecode(EN, HELLO);
    }

    @Test
    void encodeDecodeRussian() {
        encodeDecode(RU, HELLO_RUS);
    }

    void encodeDecode(Language language, String init) {
        enigma = new Enigma(language, 3);
        String initState = enigma.exportState();

        String out = enigma.processString(init);

        enigma.importState(initState);

        String decoded = enigma.processString(out);

        assertEquals(init, decoded);
    }


}
