package ex.neskoro;

public abstract class Language {
    protected int size;
    protected String alphabet;

    public Language(String alphabet) {
        this.alphabet = alphabet;
        this.size = alphabet.length();
    }

    public int getSize() {
        return size;
    }

    public String getAlphabet() {
        return alphabet;
    }
}
