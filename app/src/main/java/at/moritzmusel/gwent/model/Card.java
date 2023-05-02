package at.moritzmusel.gwent.model;

public class Card {
    Integer number;
    Integer image;
    Boolean isDecoyCard;
    Boolean isUserCard;

    public Card(Integer number, Integer image, Boolean isDecoyCard, Boolean isUserCard) {
        this.number = number;
        this.image = image;
        this.isDecoyCard = isDecoyCard;
        this.isUserCard = isUserCard;
    }

    public Boolean getDecoyCard() {
        return isDecoyCard;
    }

    public Boolean isUserCard() {
        return isUserCard;
    }

    public void setUserCard(Boolean userCard) {
        isUserCard = userCard;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getImage() {
        return image;
    }

    public Boolean isDecoyCard() {
        return isDecoyCard;
    }

    public void setDecoyCard(Boolean decoyCard) {
        isDecoyCard = decoyCard;
    }
}
