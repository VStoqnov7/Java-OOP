package P02WorkingWithAbstractionExercise.P03CardsWithPower;

public class Card {
    private CardsSuit suit;
    private CardsRank rank;

    public Card(CardsSuit suit, CardsRank power) {
        this.rank = power;
        this.suit = suit;
    }

    public int getPower(){
        return rank.getPowerRank() + suit.getSuitPower();
    }


    public String getNameRank (){
        return rank.name();
    }
    public String getNameSuit(){
        return suit.name();
    }
}
