package ex.neskoro;

import ex.neskoro.language.EnLanguage;
import ex.neskoro.language.Language;

import java.util.ArrayList;
import java.util.List;

public class OneToOneRotor extends Rotor {

    public OneToOneRotor(Language language) {
        super(language, 0);
    }

    public OneToOneRotor() {
        this(new EnLanguage());
    }

    @Override
    public final int turn() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void inListInit() {
        inList = new ArrayList<>(List.of(language.getAlphabet().split("")));
    }
}
