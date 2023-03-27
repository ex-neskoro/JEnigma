import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;
import ex.neskoro.language.RuLanguage;
import ex.neskoro.rotor.Rotor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RotorTests {
    Rotor rotor;

    private final Language EN = new EnLanguage();
    private final Language RUS = new RuLanguage();
    private final Language DEFAULT_LAN = EN;

    private final String HELLO_EN = "Hello, world!";
    private final String LETTER_A = "a";
    private final String LETTER_GA = "GA";

    private final String DEF_STATE = "0,xlhibzwjnrseqaftvkodpmycug,a";
    private final String DEF_STATE_3 = "0,xlhibzwjnrseqaftvkodpmycug,azt";
    private final String DEF_STATE_15 = "0,ilntoyrajspmfbwzcxhdqugvke,awertyuioxcvbnm";

    @BeforeEach
    void setRotor() {
        rotor = new Rotor(DEFAULT_LAN);
    }


    @Test
    void importExportState() {
        String initState = rotor.exportState();

        rotor = new Rotor(DEFAULT_LAN);

        rotor.importState(initState);

        String exportedState = rotor.exportState();

        assertEquals(initState, exportedState);
    }

    @Test
    void sameStateSameOutputDefault() {
        rotor.importState(DEF_STATE);

        String output = rotor.processLetterIn(LETTER_A, 0);

        assertEquals(output, "x");
    }

    @Test
    void turnRotor() {
        rotor.importState(DEF_STATE);

        boolean turnNext = rotor.turn();

        assertFalse(turnNext);
    }
}
