package ex.neskoro.language;

public enum LanguageAlphabet {
    RU("абвгдеёжзиклмнопрстуфхцчшщъыьэюя"),
    EN("abcdefghijklmnopqrstuvwxyz"),

    ;

    public String alphabet;

    LanguageAlphabet(String alphabet) {
        this.alphabet = alphabet.toLowerCase();
    }
}
