package lab4;

import lab1.Lab1;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {

    private final String[] cards;
    private final String name;

    @Getter
    private static BigInteger p;

    private BigInteger c;
    private BigInteger d;

    private final Cards chargedDeck;

    public static void generateBigPrimeP() {
        do {
            p = getRandomBigInteger(30);
        } while (!p.isProbablePrime(100));
    }

    public Player(String name, Cards deck) {
        cards = new String[2];
        this.name = name;
        chargedDeck = deck;
        long[] euclidResult;
        BigInteger tempP = p.subtract(BigInteger.valueOf(1));
        do {
            do {
                c = getRandomBigInteger(30);
                euclidResult = Lab1.gcd(c.longValue(), tempP.longValue());
            } while (euclidResult[0] != 1);
            d = new BigInteger((String.valueOf(euclidResult[2] + tempP.longValue())));
        } while (!c.multiply(d).mod(BigInteger.valueOf(tempP.longValue())).equals(BigInteger.ONE));
    }

    public List<BigInteger> encryptCards(List<BigInteger> cards) {
        List<BigInteger> uDeck = new ArrayList<>(cards.size());
        for (BigInteger card : cards) {
            uDeck.add(card.modPow(c, p));
        }
        Collections.shuffle(uDeck);
        return uDeck;
    }

    public List<BigInteger> decryptCards(List<BigInteger> cards) {
        List<BigInteger> uDeck = new ArrayList<>(cards.size());
        for (BigInteger card : cards) {
            uDeck.add(card.modPow(d, p));
        }
        Collections.shuffle(uDeck);
        return uDeck;
    }

    public void seeCards(List<BigInteger> cards) {
        System.out.println("\tPlayer " + name + ": key = " + d);
        for (int i = 0; i < cards.size(); i++) {
            this.cards[i] = chargedDeck.deckInterpreter(chargedDeck.getDeck(), cards.get(i));
            System.out.print(" < " + this.cards[i] + " >");
        }
        System.out.println("\n");
    }

    public static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = new BigInteger(numBits, new Random());
        return number.setBit(0);
    }
}