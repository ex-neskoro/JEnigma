package ex.neskoro.rotor;

// reference - https://en.wikipedia.org/wiki/Enigma_rotor_details
public enum StandardRotorType {
    I_C("DMTWSILRUYQNKFEJCAZBPGXOHV", "A"),
    II_C("HQZGPJTMOBLNCIFDYAWVEUSRKX", "A"),
    III_C("UQNTLSZFMREHDPXKIBVYGJCWOA", "A"),

    I_GR("JGDQOXUSCAMIFRVTPNEWKBLZYH", "N"),
    II_GR("NTZPSFBOKMWRCJDIVLAEYUXHGQ", "E"),
    III_GR("JVIUBHTCDYAKEQZPOSGXNRMWFL", "Y"),

    I_K("PEZUOHXSCVFMTBGLRINQJWAYDK", "Y"),
    II_K("ZOUESYDKFWPCIQXHMVBLGNJRAT", "E"),
    III_K("EHRVXGAOBQUSIMZFLYNWKTPDJC", "N"),

    I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"),
    II("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "E"),
    III("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"),
    IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", "J"),
    V("VZBRGITYUPSDNHLXAWMJQOFECK", "Z"),
    VI("JPGVOUMFYQBENHZRDKASXLICTW", "ZM"),
    VII("NZJHGRCXMYSWBOUFAIVLPEKQDT", "ZM"),
    VIII("FKQHTLXOCBJSPDZRAMEWNIUYGV", "ZM"),

    ;

    public final String movableListState;
    public final String turnoverState;

    StandardRotorType(String listState, String turnoverState) {
        movableListState = listState.toLowerCase();
        this.turnoverState = turnoverState.toLowerCase();
    }
}
