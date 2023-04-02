package ex.neskoro.rotor;

import ex.neskoro.language.Language;

import java.util.*;

public final class Reflector extends AbstractRotor {
    public Reflector(Language language) {
        super(language);
    }

    public Reflector(String movableList) {
        super(movableList);
    }

    // TODO add random initialization -> any Language init
    // Reflector A - https://en.wikipedia.org/wiki/Enigma_rotor_details
    @Override
    protected void movableListInit() {
        movableList = new ArrayList<>(staticList);

        Collections.fill(movableList, null);

        List<String> lettersList = new ArrayList<>(staticList);
        Collections.shuffle(lettersList);
        Queue<String> queue = new ArrayDeque<>(lettersList);

        int i = 0;
        while (!queue.isEmpty()) {
            if (movableList.get(i) == null) {
                String letter = queue.poll();
                movableList.remove(i);
                movableList.add(i, letter);

                if (!(language.getSize() % 2 != 0 && i == language.getSize() - 1)) {
                    int currentLetterIndex = staticList.indexOf(letter);

                    movableList.remove(currentLetterIndex);
                    movableList.add(currentLetterIndex, staticList.get(i));

                    queue.remove(staticList.get(i));
                }
            }
            i++;
        }
    }

    @Override
    public void setTurnState(int turnState) {
        if (turnState != 0) {
            throw new IllegalArgumentException("Reflector state must be 0");
        }
        super.setTurnState(turnState);
    }
}