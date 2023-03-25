package ex.neskoro.rotor;

public class RotorFabric {
    public static AbstractRotor getRotor(StandardRotorType type) {
        return switch (type.rotorType) {
            case ENTRY_ROTOR -> new EntryRotor(type.movableListState);
            case ROTOR -> new Rotor(type.movableListState);
            case REFLECTOR -> new Reflector(type.movableListState);
        };
    }
}
