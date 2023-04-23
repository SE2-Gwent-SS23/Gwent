package at.moritzmusel.gwent.model;

public class Card {
    Integer number;
    Integer image;

    public Card(Integer number, Integer image) {
        this.number = number;
        this.image = image;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getImage() {
        return image;
    }

}
