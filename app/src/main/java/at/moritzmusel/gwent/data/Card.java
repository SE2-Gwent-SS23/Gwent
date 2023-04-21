package at.moritzmusel.gwent.data;

public class Card {
    String number;
    int image;

    public Card(String number, int image) {
        this.number = number;
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
