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

    private final String DEFAULT_STATE = """
            24,a,x,o,d,l,p,n,m,i,e,q,r,v,f,c,z,h,y,g,k,j,w,s,u,b,t
            17,g,m,f,o,r,x,c,e,k,h,z,p,b,s,l,v,u,d,i,j,a,t,n,q,y,w
            12,e,i,m,c,x,w,a,k,d,n,g,b,v,f,z,p,q,o,t,h,u,s,j,l,y,r
            e,j,m,z,a,l,y,x,v,b,w,f,c,r,q,u,o,n,t,s,p,i,k,h,g,d""";

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

        assertEquals(initState, enigma.exportState());
    }

    @Test
    @DisplayName("Same state produce same outString")
    void testSameStateSameIO() {
        String initState = enigma.exportState();

        String firstOut = enigma.processString(HELLO);
        enigma.importState(initState);

        String secondOut = enigma.processString(HELLO);

        assertEquals(firstOut, secondOut);
    }

    void encodeDecode(Language language, String init) {
        enigma = new Enigma(language, 3);
        String initState = enigma.exportState();

        String out = enigma.processString(init);

        enigma.importState(initState);

        String decoded = enigma.processString(out);

        System.out.println(init);
        System.out.println(out);
        System.out.println(decoded);

        assertEquals(init, decoded);
    }

//    @Test
//    void encodeDecodeRussian() {
//        encodeDecode(new RuLanguage(), HELLO_RUS);
//    }

    @Test
    void encodeDecodeEnglish() {
        encodeDecode(new EnLanguage(), HELLO);
    }


}
