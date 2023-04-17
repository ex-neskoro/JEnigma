package ex.neskoro.jenigma.language;

public enum LanguageAlphabet {
    // russian
    RU("абвгдеёжзиклмнопрстуфхцчшщъыьэюя"),
    // english
    EN("abcdefghijklmnopqrstuvwxyz"),
    // spanish
    SP("abcdefghijklmnñopqrstuvwxyz"),
    // turkish
    TR("abcçdefgğhıijklmnoöprsştuüvyz"),
    // serbian
    SR("абвгдђежзијклљмнњопрстћуфхцчџш"),

    ;

    public final String alphabet;

    LanguageAlphabet(String alphabet) {
        this.alphabet = alphabet.toLowerCase();
    }
}
