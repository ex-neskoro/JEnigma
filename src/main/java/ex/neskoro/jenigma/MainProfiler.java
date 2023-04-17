package ex.neskoro.jenigma;

public class MainProfiler {
    public static void main(String[] args) {
        Enigma enigma = new Enigma();
        String result = "";

        for (int i = 0; i < 1_000_000; i++) {
            result = enigma.processString("Hello, world!");
        }

        System.out.println(result);
    }
}
