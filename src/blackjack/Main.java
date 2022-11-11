package blackjack;



import java.util.*;


public class Main {
    private static Scanner scan = new Scanner( System.in);

    protected static List <Integer> playerHand = new ArrayList<>();

    protected static List <Integer> houseHand = new ArrayList<>();
    private static int usedCardObjects;
    private static int bet = 0;
    private static int balance = 1000;
    public static void main(String[] args) {
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello, what is your name?");
        name = scanner.nextLine();
        System.out.println("Hello " + name);

        boolean exitGame = false;
        String playerChoice;
        Card.createDeckWithCardObjects();
        Card.shuffle();
        greeting();
        while (!exitGame){
            Menu.menu();
            playerChoice = scan.next().toUpperCase();
            if(playerChoice.equals("P")){
                if(balance >0){
                    playGame();
                }else{
                    System.out.println("Sorry, the balance is 0");
                }
            }else if(playerChoice.equals("Q")){
                System.out.println("Program is ending....");
                System.exit(0);
            }else if(playerChoice.equals("B")){
                System.out.println("Your current balance is " + balance);
            }
        }
}
    private static void playGame(){
        clearHands();
        //houseHand.add(1);
       //playerHand.add(1);
        bet = getBet(balance);
        balance = getBalance(bet, balance);
        playerHand.add(getCardObjectValue());
        System.out.println(" ");
        System.out.println("The players card is " + getCardObjectName());
        usedCardObjects++;
        houseHand.add(getCardObjectValue());
        System.out.println("The house's card is " + getCardObjectName());
        usedCardObjects++;
        int playerTotalScore = thePlayerPlays();


        if(playerTotalScore < 22){
            System.out.println(Count.countHand("player"));
            int houseTotalScore = theHousePlays();
            winOrLose(playerTotalScore, houseTotalScore);
        }
    }

    private static void winOrLose(int playerTotalScore, int houseTotalScore) {
        if(playerTotalScore == houseTotalScore){
            balance += bet;
            System.out.println("Push, and your new balance is " + balance);
        }
        else if(houseTotalScore>21){
            balance += bet*2;
            System.out.println("house fat, and you balanace is " + balance);

        }else if(playerTotalScore > houseTotalScore){
            balance += bet*2;
            System.out.println("player wins, and your new balance is " + balance);
        }
        else{
            System.out.println("House wins");
        }
    }
    private static int getBalance(int bet, int balance) {
        balance -= bet;
        System.out.println("Your balance is now "+ balance);
        return balance;
    }

    private static int theHousePlays() {
        System.out.println("The house plays");
        int houseTotalScore = Count.countHand("house");
        while (houseTotalScore <17){
            houseHand.add(getCardObjectValue());
            System.out.println("The house's next card is " + getCardObjectName());
            usedCardObjects++;
            houseTotalScore = Count.countHand("house");
            System.out.println("the house hand is "+Count.countHand("house"));
            if(houseHand.contains(1)){
                if ((houseTotalScore+10) > 16 && ((houseTotalScore+10)<22)){
                    houseTotalScore += 10;
                }
            }else{
                System.out.println("the house hand is " + houseTotalScore);
            }
         }
        return houseTotalScore;
     }

    private static int thePlayerPlays() {
        boolean done = false;
        int playerTotalScore = Count.countHand("player");
        if (playerHand.contains(1)){
            if((playerTotalScore+10)<22){
                System.out.println("Total value player is " + playerTotalScore + " or " + (playerTotalScore + 10));
            } else {
                System.out.println("Total value player" + playerTotalScore);
            }

        }else{
            System.out.println("Total value player " + playerTotalScore);
        }
        System.out.println("Total value house" + Count.countHand("house"));
        while (!done){
            String drawOrStop = askForCard();
                if(drawOrStop.equals("Y")){
                    playerHand.add(getCardObjectValue());
                    playerTotalScore = Count.countHand("player");
                    System.out.println("Next player card is "+getCardObjectName());
                    if(playerHand.contains(1)){
                        if ((playerTotalScore+10 < 22)){
                            System.out.println("Total value player is "+ playerTotalScore + " or "+playerTotalScore + 10);
                        }else{
                            System.out.println("Total value player " + playerTotalScore);
                        }
                    }else {
                        System.out.println("Total value player "+playerTotalScore);
                    }
                    usedCardObjects++;

                    if(playerTotalScore>21){
                        done = true;
                        playerLose();
                    }

                }else {
                    done = true;
                    if (playerHand.contains(1) && (playerTotalScore + 10)<22){
                        playerTotalScore += 10;
                    }
                }

        }   return playerTotalScore;



    }

    private static void playerLose() {
        System.out.println("The player has lost");
        clearHands();

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
    private static void clearHands(){
        playerHand.clear();
        houseHand.clear();
    }



}
