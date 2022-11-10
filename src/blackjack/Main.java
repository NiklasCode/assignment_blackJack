package blackjack;

import org.w3c.dom.ls.LSOutput;

import java.util.*;


public class Main {
    private static Scanner scan = new Scanner( System.in);

    protected static List <Integer> playerHand = new ArrayList<>();

    protected static List <Integer> houseHand = new ArrayList<>();
    private static int usedCardObjects;
    private static int bet = 0;
    private static int balance = 1000;
    public static void main(String[] args) {





        Card.createDeckWithCardObjects();
        greeting();
        while (balance > 0){
            bet = getBet(balance);
            balance = getBalance(bet, balance);
            playerHand.add(getCardObjectValue());
            System.out.println("The players card is " + getCardObjectName());
            usedCardObjects++;
            houseHand.add(getCardObjectValue());
            System.out.println("The house's card is " + getCardObjectName());
            usedCardObjects++;
            thePlayerPlays();
            if(Count.countHand("player")> 22){
                System.out.println(Count.countHand("player"));
                theHousePlays();
                winOrLose();
            }
        }


       
    }

    private static void winOrLose() {
        int playerTotalScore = Count.countHand("player");
        int houseTotalScore = Count.countHand("house");
        if(playerTotalScore>22){
            System.out.println("player lose");
            System.out.println("And your new balance is "+balance);
        }
        else if(houseTotalScore>22){
            System.out.println("house fat, and you balanace is "+balance);

        }else if(playerTotalScore > houseTotalScore && playerTotalScore < 22){
            balance += bet*2;
            System.out.println("player wins, and your new balance is "+balance);


        }
    }

    private static int getBalance(int bet, int balance) {
        balance -= bet;
        System.out.println("Your balance is now "+ balance);
        return balance;
    }

    private static void theHousePlays() {
        System.out.println("huset spelar");
        while (Count.countHand("house")<17){
            houseHand.add(getCardObjectValue());
            System.out.println(getCardObjectName());
            usedCardObjects++;
            System.out.println("the house hand is "+Count.countHand("house"));
        }

    }

    private static void thePlayerPlays() {
        boolean done = false;
        System.out.println("Total value player " + Count.countHand("player"));
        System.out.println("Total value house " + Count.countHand("house"));
        while (!done){
            String drawOrStop = askForCard();
                if(drawOrStop.equals("Y")){
                    playerHand.add(getCardObjectValue());
                    System.out.println("Next player card is "+getCardObjectName());
                    System.out.println("the total value is " + Count.countHand("player"));
                    usedCardObjects++;
                    if(Count.countHand("player")>21){
                        done = true;
                        playerLose();
                    }

                }else {
                    done = true;
                }

        }



    }

    private static void playerLose() {
        System.out.println("The player has lost");
        playerHand.clear();
        houseHand.clear();
    }

    private static String askForCard() {
        System.out.println("Do you want to draw? press Y, or do you want to stand press N");
        String drawOrStop = scan.next().toUpperCase();
        return drawOrStop;
    }

    private static int getBet(int balance) {
        boolean correctBet = false;
        int bet = 0;
        while (!correctBet){
            int maxBet = 500;
            if (balance > 500){
                maxBet = 500;
            }else {
                maxBet = balance;
            }
            bet = checkInt("please place a bet between 2 and " + maxBet);

            if (bet >=2  && bet <= 500 && bet <= balance){
                correctBet = true;
                return bet;
            }
        }
        return 0;
    }
    private static int getCardObjectValue(){
        return Card.cards.get(usedCardObjects).getCardValue();
    }
    private static String getCardObjectName(){
        return Card.cards.get(usedCardObjects).getCardName();
    }
    static int checkInt(String message) {
        Scanner scan = new Scanner(System.in);
        int number = 0;
        boolean isNumber = false;
        System.out.println(message);

        do {
            if (scan.hasNextInt()) {
                number = scan.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please input whole number");
                scan.next();
            }
        } while (!(isNumber));
        return number;
        }



    private static void greeting() {
        System.out.println("Welcome to blackjack 3000!");
    }
}
