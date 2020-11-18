package lab4;

import lombok.Getter;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int NUM_OF_CARDS = 52;
    private static final int NUM_OF_SUITS = 4;
    private static final int NUM_OF_VALUES = 13;

    private final List<String> suitName = new ArrayList<>(NUM_OF_SUITS);
    private final List<String> valuesName = new ArrayList<>(NUM_OF_VALUES);

    @Getter
    private final List<BigInteger> deck = new ArrayList<>(NUM_OF_CARDS);

    public Cards() {
        initCard();
        initSuit();
    }

    public void initDeck(BigInteger P) {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            deck.add(new BigInteger(String.valueOf(srand.nextInt(P.intValue()-2)+1)));
        }
    }

    public String deckInterpreter(List<BigInteger> cardDeck, BigInteger cardValue) {
        int suit;
        int value;
        String cardName;
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            if (cardDeck.get(i).equals(cardValue)) {
                suit = i / NUM_OF_VALUES;
                value = i % NUM_OF_VALUES;
                cardName = valuesName.get(value)+" "+suitName.get(suit);
                return cardName;
            }
        }
        return null;
    }

    public void initSuit(){
        suitName.add("Черви");
        suitName.add("Буби");
        suitName.add("Пики");
        suitName.add("Крести");
    }

    public void initCard(){
        valuesName.add("2");
        valuesName.add("3");
        valuesName.add("4");
        valuesName.add("5");
        valuesName.add("6");
        valuesName.add("7");
        valuesName.add("8");
        valuesName.add("9");
        valuesName.add("10");
        valuesName.add("Валет");
        valuesName.add("Дама");
        valuesName.add("Король");
        valuesName.add("Туз");
    }
}