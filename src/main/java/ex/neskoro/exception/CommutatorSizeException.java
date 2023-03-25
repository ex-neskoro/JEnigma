package ex.neskoro.exception;

public class CommutatorSizeException extends RuntimeException {
    public CommutatorSizeException() {
        super("Commutator can't take more letters than size of language");
    }
}
