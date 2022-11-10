package blackjack;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
    String cardName = "";
    int cardValue = 0;

    public Card(String s,int i) {
        this.cardName = s;
        this.cardValue = i;
    }

    public String getCardName() {
        return cardName;
    }
    public int getCardValue() {
        return cardValue;
    }

    private static ArrayList<Integer> deck = new ArrayList<>();
    private static List<String> cardNames = Arrays.asList("ace","two","three","four","five", "six","seven",
            "eight", "nine","ten", "jack", "queen", "king");
    private static List<Integer> cardValues = Arrays.asList(1,2,3,4,5,6,7,8,9,10,10,10,10);

    protected static ArrayList<Card> cards = new ArrayList<>();

    static void createDeckWithCardObjects(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(j, new Card(cardNames.get(j),cardValues.get(j)));
            }
        }
    }

}
