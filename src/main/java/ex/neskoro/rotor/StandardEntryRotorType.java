package ex.neskoro.rotor;

public enum StandardEntryRotorType {
    ETW_GR("QWERTZUIOASDFGHJKPYXCVBNML"),
    ETW_K("QWERTZUIOASDFGHJKPYXCVBNML"),
    // one to one
    ETW_E_I("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    ;

    public final String movableListState;

    StandardEntryRotorType(String str) {
        movableListState = str.toLowerCase();
    }
}
