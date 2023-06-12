package at.moritzmusel.gwent.model;


import java.io.Serializable;

public class Card implements Serializable {
    String name;
    Type type;
    Row row;
    int strength;
    Ability ability;
    String filename;
    int count;
    String flavor_txt;

    public Card(String name, Type type, Row row, int strength, Ability ability, String filename, int count, String flavor_txt) {
        this.name = name;
        this.type = type;
        this.row = row;
        this.strength = strength;
        this.ability = ability;
        this.filename = filename;
        this.count = count;
        this.flavor_txt = flavor_txt;
    }

    public Card() {

    }

    public void applyWeather() {
        this.strength = 1;
    }

    public void applyHorn() {
        this.strength *= 2;
    }

    public void changeType(String txt) {
        for (Type t : Type.values()) {
            if (t.toString().equals(txt)) {
                this.type = t;
            }
        }
    }

    public void changeRow(String txt) {
        if (txt.isEmpty()) {
            this.row = Row.NONE;
        } else {
            for (Row r : Row.values()) {
                if (r.toString().equals(txt)) {
                    this.row = r;
                }
            }
        }

    }

    public void changeAbility(String txt) {
        if (txt.isEmpty()) {
            this.ability = Ability.NONE;
        } else {
            //can have multiple abilities thats why contains
            for (Ability a : Ability.values()) {
                if ((txt).contains(a.toString())) {
                    this.ability = a;
                }
            }
        }
    }



    //getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFlavor_txt() {
        return flavor_txt;
    }

    public void setFlavor_txt(String flavor_txt) {
        this.flavor_txt = flavor_txt;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", row=" + row +
                ", strength=" + strength +
                ", ability=" + ability +
                ", filename='" + filename + '\'' +
                ", count=" + count +
                ", flavor_txt='" + flavor_txt + '\'' +
                '}';
    }
}
