package at.moritzmusel.gwent.model;

import android.content.Context;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import at.moritzmusel.gwent.R;


public class Card2 {
    String name;
    Type type;
    Row row;
    int strength;
    Ability ability;
    String filename;
    int count;
    String flavor_txt;

    public void fill(String filename, Context context) throws JSONException, IOException {
        this.filename = filename;

        JSONObject jsonObject = new JSONObject(loadCardJSONFromAsset(context));
        JSONArray jsonArray = jsonObject.optJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);

            if (obj.optString("filename").toString().equals(this.filename)) {

                this.name = obj.optString("name").toString();
                this.strength = Integer.parseInt(obj.optString("strength").toString());
                this.count = Integer.parseInt(obj.optString("count").toString());
                this.flavor_txt = obj.optString("flavor_txt").toString();

                changeType(obj.optString("type").toString());
                changeRow(obj.optString("row").toString());
                changeAbility(obj.optString("ability").toString());

            }
        }

    }

    public void applyWeather() {
        this.strength = 1;
    }

    public void applyHorn() {
        this.strength *= 2;
    }

    private void changeType(String txt) {
        for (Type t : Type.values()) {
            if (t.toString().equals(txt)) {
                this.type = t;
            }
        }
    }

    private void changeRow(String txt) {
        if (txt.isEmpty()) {
            this.row = Row.none;
        } else {
            for (Row r : Row.values()) {
                if (r.toString().equals(txt)) {
                    this.row = r;
                }
            }
        }

    }

    private void changeAbility(String txt) {
        if (txt.isEmpty()) {
            this.ability = Ability.none;
        } else {
            //can have multiple abilities thats why contains
            for (Ability a : Ability.values()) {
                if ((txt).contains(a.toString())) {
                    this.ability = a;
                }
            }
        }
    }

    private String loadCardJSONFromAsset(Context context) throws IOException {
        String json = null;
        InputStream is = null;
        try {
            is = context.getAssets().open("cards.json");


            int size = is.available();
            byte[] buffer = new byte[size];
            int bytesRead = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }finally{
            is.close();
        }
        return json;

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
}
