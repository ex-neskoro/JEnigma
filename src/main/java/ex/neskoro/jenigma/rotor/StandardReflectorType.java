package ex.neskoro.jenigma.rotor;

public enum StandardReflectorType {
    UKW_GR("QYHOGNECVPUZTFDJAXWMKISRBL"),
    UKW_K("IMETCGFRAYSQBZXWLHKDVUPOJN"),
    REFLECTOR_A("EJMZALYXVBWFCRQUONTSPIKHGD"),
    REFLECTOR_B("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
    REFLECTOR_C("FVPJIAOYEDRZXWGCTKUQSBNMHL"),
    REFLECTOR_B_THIN("ENKQAUYWJICOPBLMDXZVFTHRGS"),
    REFLECTOR_C_THIN("RDOBJNTKVEHMLFCWZAXGYIPSUQ"),
    ;

    public final String movableListState;

    StandardReflectorType(String str) {
        movableListState = str.toLowerCase();
    }
}
