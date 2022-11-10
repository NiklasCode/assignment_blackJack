package blackjack;

import java.util.stream.Collectors;

public class Count {
    protected static int countHand(String hand){
        int sumHand = 0;
        if(hand.equals("player")){
            sumHand = Main.playerHand.stream().collect(Collectors.summingInt(Integer::intValue));
        }else if(hand.equals("house")) {
            sumHand = Main.houseHand.stream().collect(Collectors.summingInt(Integer::intValue));
        }
        return sumHand;
    }

}
