package ex.neskoro.language;

public class Language {
    protected int size;
    protected String alphabet;
    private final LanguageAlphabet languageAlphabet;

    public Language(LanguageAlphabet languageAlphabet) {
        this.languageAlphabet = languageAlphabet;
        this.alphabet = languageAlphabet.alphabet;
        this.size = alphabet.length();
    }

    public int getSize() {
        return size;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public LanguageAlphabet getAlphabetType() {
        return languageAlphabet;
    }
}
