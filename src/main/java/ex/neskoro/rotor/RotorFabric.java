package ex.neskoro.rotor;

public class RotorFabric {
    public static Rotor getRotor(StandardRotorType type) {
        return new Rotor(type.movableListState, type.turnoverState);
    }

    public static EntryRotor getEntryRotor(StandardEntryRotorType type) {
        return new EntryRotor(type.movableListState);
    }

    public static Reflector getReflector(StandardReflectorType type) {
        return new Reflector(type.movableListState);
    }
}
