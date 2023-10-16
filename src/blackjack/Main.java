package blackjack;
import java.util.ArrayList;
import java.util.HashMap;

class Card{
    public String suit;
    public String value;
    public int intValue;

    public Card(String suit, String value, int intValue){
        this.suit = suit;
        this.value = value;
        this.intValue = intValue;
    }

    public String getCardString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }

}

class Deck{
    public ArrayList<Card> deck;
    
    public Deck(Table table){
        this.deck = generateDeck(table);
    }

    public static ArrayList<Card> generateDeck(Table table){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        HashMap<String, Integer> blackJack = new HashMap<>(){
            {
                put("A",1);
                put("J",10);
                put("Q",10);
                put("K",10);

            }
        };
        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j < values.length; j++){
                if(table.gameMode == "21"){
                    newDeck.add(new Card(suits[i], values[j], blackJack.get(values[j]) == null ? j + 1 : blackJack.get(values[j])));
                }else{
                    newDeck.add(new Card(suits[i], values[j], j + 1));
                }                     
            }
        }
        return newDeck;
    }

    public Card draw(){
        return this.deck.remove(this.deck.size()-1);
    }

    public void printDeck(){
        System.out.println("Displaying cards...");
        for (int i = 0; i < this.deck.size(); i++) {
            System.out.println(this.deck.get(i).getCardString());
        }
    }

    public void shuffleDeck() {
        for(int i = this.deck.size()-1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card tmp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, tmp);
        }
    }
}    

class Table{
    public int amountOfPlayers;
    public String gameMode;

    public Table(int amountOfPlayers, String gameMode){
        this.amountOfPlayers = amountOfPlayers;
        this.gameMode = gameMode;
    }
}

class Dealer{
    public static ArrayList<ArrayList<Card>> startGame(Table table) {

        Deck deck = new Deck(table);
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        
        for (int i = 0; i < table.amountOfPlayers; i++) { 
  
            ArrayList<Card> playerHand = new ArrayList<Card>(initialCards(table.gameMode));     
            for (int j = 0; j < initialCards(table.gameMode); j++) {
                Card card1 = deck.draw();
                playerHand.add(card1);
            }
            playerCards.add(playerHand);
        }
        
        return playerCards;
    }


    public static int initialCards(String gameMode) {
        if (gameMode == "poker") return 5;
        if (gameMode == "21") return 2;
        else return 0;
    }

    public static void printTableInformation(ArrayList<ArrayList<Card>> playerCards, Table table) {
        System.out.println("Amount of players: " + table.amountOfPlayers +"... Game mode: " + table.gameMode + ". At this table: ");
        
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.println("Player " + (i + 1) + " hand is: ");             
            for(int j = 0; j < playerCards.get(i).size(); j++) {
                System.out.println(playerCards.get(i).get(j).getCardString());
            }
            System.out.println();
        }            
    }

    public static int score21Individual(ArrayList<Card> cards) {
        int value = 0;
        for (int i = 0; i < cards.size(); i++) {
            value += cards.get(i).intValue;
        }
        if (value > 21) value = 0;
        return value;
    }

        public static String winnerOf21(ArrayList<ArrayList<Card>> playerCards) {
        int[] points = new int[playerCards.size()];
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < playerCards.size(); i++) {
            int point = score21Individual(playerCards.get(i));
            points[i] = point;

            if(cache.get(point) == null) cache.put(point,1);
            else cache.replace(point, cache.get(point)+1);
        }

        int winnerIndex = HelperFunctions.maxInArrayIndex(points);
        if (cache.get(points[winnerIndex]) > 1) return "It is a draw ";
        else if (cache.get(points[winnerIndex]) >= 0) return "player " + (winnerIndex + 1) + " is the winner";
        else return "No winners..";

    }

    // 卓のゲームの種類によって勝利条件を変更するcheckWinnerというメソッドを作成します。
    public static String checkWinner(ArrayList<ArrayList<Card>> playerCards, Table table) {
        if (table.gameMode == "21") return Dealer.winnerOf21(playerCards);
        else return "no game";
    }
}

class HelperFunctions {

    public static int maxInArrayIndex(int[] intArr) {
        int maxIndex = 0;
        int maxValue = intArr[0];

        for (int i = 1; i < intArr.length; i++) {
            if (intArr[i] > maxValue) {
                maxValue = intArr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

class Main{
        
    public static void main(String[] args){

        Table table1 = new Table(1,"poker");
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);

        Table table2 = new Table(3,"21");
        ArrayList<ArrayList<Card>> game2 = Dealer.startGame(table2);

        Dealer.printTableInformation(game1, table1);
        System.out.println(Dealer.checkWinner(game1, table1));
        
        Dealer.printTableInformation(game2, table2); 
        System.out.println(Dealer.checkWinner(game2, table2));
    }

}