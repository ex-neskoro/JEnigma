package ex.neskoro.rotor;

// reference - https://en.wikipedia.org/wiki/Enigma_rotor_details
public enum StandardRotorType {

    I_C("DMTWSILRUYQNKFEJCAZBPGXOHV", RotorType.ROTOR),
    II_C("HQZGPJTMOBLNCIFDYAWVEUSRKX", RotorType.ROTOR),
    III_C("UQNTLSZFMREHDPXKIBVYGJCWOA", RotorType.ROTOR),

    I_GR("JGDQOXUSCAMIFRVTPNEWKBLZYH", RotorType.ROTOR),
    II_GR("NTZPSFBOKMWRCJDIVLAEYUXHGQ", RotorType.ROTOR),
    III_GR("JVIUBHTCDYAKEQZPOSGXNRMWFL", RotorType.ROTOR),
    UKW_GR("QYHOGNECVPUZTFDJAXWMKISRBL", RotorType.REFLECTOR),
    ETW_GR("QWERTZUIOASDFGHJKPYXCVBNML", RotorType.ENTRY_ROTOR),

    I_K("PEZUOHXSCVFMTBGLRINQJWAYDK", RotorType.ROTOR),
    II_K("ZOUESYDKFWPCIQXHMVBLGNJRAT", RotorType.ROTOR),
    III_K("EHRVXGAOBQUSIMZFLYNWKTPDJC", RotorType.ROTOR),
    UKW_K("IMETCGFRAYSQBZXWLHKDVUPOJN", RotorType.REFLECTOR),
    ETW_K("QWERTZUIOASDFGHJKPYXCVBNML", RotorType.ENTRY_ROTOR),

    I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", RotorType.ROTOR),
    II("EKMFLGDQVZNTOWYHXUSPAIBRCJ", RotorType.ROTOR),
    III("BDFHJLCPRTXVZNYEIWGAKMUSQO", RotorType.ROTOR),
    IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", RotorType.ROTOR),
    V("VZBRGITYUPSDNHLXAWMJQOFECK", RotorType.ROTOR),
    VI("JPGVOUMFYQBENHZRDKASXLICTW", RotorType.ROTOR),
    VII("NZJHGRCXMYSWBOUFAIVLPEKQDT", RotorType.ROTOR),
    VIII("FKQHTLXOCBJSPDZRAMEWNIUYGV", RotorType.ROTOR),

    BETA("LEYJVCNIXWPBQMDRTAKZGFUHOS", RotorType.ROTOR),
    GAMMA("FSOKANUERHMBTIYCWLQPZXVGJD", RotorType.ROTOR),
    REFLECTOR_A("EJMZALYXVBWFCRQUONTSPIKHGD", RotorType.REFLECTOR),
    REFLECTOR_B("YRUHQSLDPXNGOKMIEBFZCWVJAT", RotorType.REFLECTOR),
    REFLECTOR_C("FVPJIAOYEDRZXWGCTKUQSBNMHL", RotorType.REFLECTOR),
    REFLECTOR_B_THIN("ENKQAUYWJICOPBLMDXZVFTHRGS", RotorType.REFLECTOR),
    REFLECTOR_C_THIN("RDOBJNTKVEHMLFCWZAXGYIPSUQ", RotorType.REFLECTOR),
    // one to one
    ETW_E_I("ABCDEFGHIJKLMNOPQRSTUVWXYZ", RotorType.ENTRY_ROTOR),
    ;

    public final String movableListState;
    public final RotorType rotorType;

    StandardRotorType(String str, RotorType rotorType) {
        movableListState = str.toLowerCase();
        this.rotorType = rotorType;
    }
}
