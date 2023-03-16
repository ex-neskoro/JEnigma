package ex.neskoro;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Enigma!\n");
//        Enigma enigma = new Enigma(new RuLanguage(), 4);
        Enigma enigma = new Enigma(new EnLanguage(), 3);
    }
}