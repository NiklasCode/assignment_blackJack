package blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to blackJack 3000");
        int bet;


        int saldo = 1000;
        int dOs = 0;
        boolean second = false;
        boolean correctBet = false;
        boolean done = false;
        int draw = 1;
        List<Integer> playerHand = new java.util.ArrayList<>(List.of());
        List<Integer> houseHand = new java.util.ArrayList<>(List.of());
        while (!correctBet) {
            System.out.println("Please place a bet between 2 and 500");
            bet = scan.nextInt();
            if (bet >= 2 && bet < 500) {
                correctBet = true;
                int balance = saldo - bet;
                System.out.println("your balance is now " + balance);
            }
        }
        Card card1 = new Card();
        //System.out.println(card1.toString());
        List<Integer> sourceList = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,10,10,10,10,10,10,10,10,10,10,10,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //Collections.shuffle(sourceList);
        playerHand.add(sourceList.get(0));
        System.out.println("The players first card is " + playerHand.get(0));


        houseHand.add(sourceList.get(1));
        System.out.println("The house's first card is " + houseHand.get(0));

        System.out.println("do you want to draw (press 1) or stand (press 2)?");
        dOs = scan.nextInt();



        while(!done){
            if (dOs == 1) {


                playerHand.add(sourceList.get(1));

                System.out.println("The players second card is " + playerHand.get(1) + " and the total is " +
                playerHand);

                System.out.println("do you want to draw (press 1) or stand (press 2)?");
                dOs = scan.nextInt();

            } else if (dOs == 2) {
                System.out.println("The player stands at " + playerHand);
                houseHand.get(0);
                done = true;

                System.out.println("the houses hand is "+houseHand);
            } else {
                System.out.println("do you want to draw (press 1) or stand (press 2)");
                dOs = scan.nextInt();
            }
        }




        //houseHand.add(sourceList.get(3));



       /* while (!second) {

                //System.out.println("press 1 to draw another card and 2 to stand");
                secNd = scan.nextInt();
                if (secNd ){
                    second = true;
                    playerHand.add(sourceList.get(3));


                    System.out.println("player hans is now " + playerHand.get(3) + (1));

                }
                else{
                    System.out.println("try again");
                }
            }
        */


    }
}
