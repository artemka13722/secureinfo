package lab4;

import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numPlayers = 24;
        List<BigInteger> uiDeck;
        List<BigInteger> buyIn;
        Player.generateBigPrimeP();

        Cards cards = new Cards();
        cards.initDeck(Player.getP());

        Player[] players = new Player[numPlayers];
        Croupier croupier = new Croupier(cards, Player.getP());

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(String.valueOf(i + 1), cards);
        }

        List<BigInteger> uDeck = cards.getDeck();

        for (int i = 0; i < numPlayers; i++) {
            uDeck = players[i].encryptCards(uDeck);
        }

        for (int i = 0; i < numPlayers; i++) {
            uiDeck = croupier.chooseCard(uDeck, 2);
            for (int j = (i + 1) % numPlayers; j != i; j = (j + 1) % numPlayers) {
                uiDeck = players[j].decryptCards(uiDeck);
            }
            players[i].seeCards(players[i].decryptCards(uiDeck));
        }

        buyIn = croupier.chooseCard(uDeck, 5);
        for (int i = 0; i < numPlayers; i++) {
            buyIn = players[i].decryptCards(buyIn);
        }
        croupier.seeBuyIN(buyIn);
    }
}
