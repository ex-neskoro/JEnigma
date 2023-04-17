package ex.neskoro.jenigma.rotor;

public enum StandardEntryRotorType {
    ETW_GR("QWERTZUIOASDFGHJKPYXCVBNML"),
    ETW_K("QWERTZUIOASDFGHJKPYXCVBNML"),
    BETA("LEYJVCNIXWPBQMDRTAKZGFUHOS"),
    GAMMA("FSOKANUERHMBTIYCWLQPZXVGJD"),
    // one to one
    ETW_E_I("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    ;

    public final String movableListState;

    StandardEntryRotorType(String str) {
        movableListState = str.toLowerCase();
    }
}
