import ex.neskoro.Commutator;
import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;
import ex.neskoro.language.RuLanguage;
import ex.neskoro.rotor.EntryRotor;
import ex.neskoro.rotor.Rotor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RotorTests {
    Rotor rotor;
    Commutator commutator;
    EntryRotor entryRotor;

    private final Language EN = new EnLanguage();
    private final Language RUS = new RuLanguage();
    private final Language DEFAULT_LAN = EN;

    private final String HELLO_EN = "Hello, world!";
    private final String LETTER_A = "a";
    private final String LETTER_GA = "GA";

    private final String DEF_ROTOR_STATE = "0,xlhibzwjnrseqaftvkodpmycug,a";
    private final String DEF_ROTOR_STATE_3 = "0,xlhibzwjnrseqaftvkodpmycug,azt";
    private final String DEF_ROTOR_STATE_15 = "0,ilntoyrajspmfbwzcxhdqugvke,awertyuioxcvbnm";

    @BeforeEach
    void setRotor() {
        rotor = new Rotor(DEFAULT_LAN);
        commutator = new Commutator(DEFAULT_LAN);
        entryRotor = new EntryRotor(DEFAULT_LAN);
    }


    @Test
    void rotorImportExportState() {
        String initState = rotor.exportState();

        rotor = new Rotor(DEFAULT_LAN);

        rotor.importState(initState);

        String exportedState = rotor.exportState();

        assertEquals(initState, exportedState);
    }

    @Test
    void rotorSameStateSameOutputDefault() {
        rotor.importState(DEF_ROTOR_STATE);

        String output = rotor.processLetterIn(LETTER_A, 0);

        assertEquals(output, "x");
    }

    @Test
    void turnRotor() {
        rotor.importState(DEF_ROTOR_STATE);

        boolean turnNext = rotor.turn();

        assertFalse(turnNext);
    }

    @Test
    void commutatorImportExportState() {
        String initState = commutator.exportState();

        commutator = new Commutator(DEFAULT_LAN);

        commutator.importState(initState);
        String exportedState = commutator.exportState();

        assertEquals(initState, exportedState);
    }

    @Test
    void commutatorRandomMapSizeIsEven() {
        List<Boolean> expectedList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            expectedList.add(true);
        }

        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            resultList.add(commutator.exportState().length() % 2 == 0);
            commutator = new Commutator(DEFAULT_LAN);
        }

        assertEquals(expectedList, resultList);
    }

    @Test
    void commutatorPairLetters() {
        commutator.importState(commutator.exportState());

        String[] letters = commutator.exportState().split("");

        List<Boolean> expectedList = new ArrayList<>();
        for (int i = 0; i < letters.length; i++) {
            expectedList.add(true);
        }

        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < letters.length; i += 2) {
            resultList.add(commutator.processLetter(letters[i]).equals(letters[i+1]));
            resultList.add(commutator.processLetter(letters[i+1]).equals(letters[i]));
        }

        assertEquals(expectedList, resultList);
    }
}
