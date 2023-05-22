package at.moritzmusel.gwent.model;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import at.moritzmusel.gwent.ui.GameViewActivity;

public class CardGeneratorTestAndroid {
    private static CardGenerator cardGenerator;

    @Test
    void testFillCardSizeTest() throws JSONException, IOException {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cardGenerator = new CardGenerator(targetContext);
        JSONObject jsonObject = new JSONObject(cardGenerator.loadCardJSONFromAsset());
        cardGenerator.fillAllCardsIntoList(jsonObject);
        assertEquals(214, cardGenerator.getAllCardsList().size());
    }

    @Test
    void testLoadCardJSONFromAsset() throws IOException {
        String jsonStringAllCards = "{\n" +
                "  \"cards\": [\n" +
                "    {\n" +
                "      \"name\": \"Mysterious Elf\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"hero spy\",\n" +
                "      \"filename\": \"mysterious_elf\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"You humans have... unusual tastes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Decoy\",\n" +
                "      \"type\": \"special\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"decoy\",\n" +
                "      \"filename\": \"decoy\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"When you run out of peasants, decoys also make decent arrow fodder.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Biting Frost\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"frost\",\n" +
                "      \"filename\": \"frost\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Best part about frost - bodies of the fallen don't rot so quickly.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cirilla Fiona Elen Riannon\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"15\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"ciri\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Know when fairy tales cease to be tales? When people start believing in them.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clear Weather\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"clear\",\n" +
                "      \"filename\": \"clear\",\n" +
                "      \"count\": \"2\",\n" +
                "      \"flavor_txt\": \"The sun's shinin', Dromle! The sun's shinin'! Maybe there's hope left after all...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Commander's Horn\",\n" +
                "      \"type\": \"special\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"horn\",\n" +
                "      \"filename\": \"horn\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Plus one to morale, minus three to hearing.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dandelion\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"horn\",\n" +
                "      \"filename\": \"dandelion\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Dandelion, you're a cynic, a lecher, a whoremonger, a liar - and my best friend.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emiel Regis Rohellec Terzieff\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"emiel\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Men, the polite ones, at least would call me a monster. A blood-drinking freak.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Geralt of Rivia\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"15\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"geralt\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If that's what it takes to save the world, it's better to let that world die.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Impenetrable Fog\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"fog\",\n" +
                "      \"filename\": \"fog\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"A good commander's dream... a bad one's horror.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Scorch\",\n" +
                "      \"type\": \"special\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"scorch\",\n" +
                "      \"filename\": \"scorch\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Pillars of flame turn the mightiest to ash. All other tremble in shock and awe.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Torrential Rain\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"rain\",\n" +
                "      \"filename\": \"rain\",\n" +
                "      \"count\": \"2\",\n" +
                "      \"flavor_txt\": \"Even the rain in this land smells like piss.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Triss Merigold\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"7\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"triss\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I can take care of myself. Trust me.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vesemir\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vesemir\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If you're to be hanged, ask for water. Anything can happen before they fetch it.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Villentretenmerth\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"7\",\n" +
                "      \"ability\": \"scorch_c\",\n" +
                "      \"filename\": \"villen\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Also calls himself Borkh Three Jackdaws... he's not the best at names.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Yennefer of Vengerberg\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"7\",\n" +
                "      \"ability\": \"hero medic\",\n" +
                "      \"filename\": \"yennefer\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Magic is Chaos, Art and Science. It is a curse, a blessing and a progression.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Zoltan Chivay\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"zoltan\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Life without old mates and booze is like a woman without a rump.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Olgierd von Everec\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"olgierd\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"At least you now know I don't easily lose my head.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Gaunter O'Dimm\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"gaunter_odimm\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"He always grants exactly what you wish for. That's the problem.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Gaunter O'Dimm - Darkness\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"gaunter_odimm_darkness\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Fear not the shadows, but the light.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cow\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"avenger\",\n" +
                "      \"filename\": \"cow\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Mooo!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Bovine Defense Force\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"chort\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Grrrrr!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foltest - King of Temeria\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"foltest_king\",\n" +
                "      \"filename\": \"foltest_silver\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A well-aimed ballista razes not just the enemy's walls, but his morale as well.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foltest - Lord Commander of the North\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"foltest_lord\",\n" +
                "      \"filename\": \"foltest_gold\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A beautiful day for battle.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foltest - The Siegemaster\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"foltest_siegemaster\",\n" +
                "      \"filename\": \"foltest_copper\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"It is natural and beautiful that a man should love his sister.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foltest - The Steel-Forged\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"foltest_steelforged\",\n" +
                "      \"filename\": \"foltest_bronze\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Sod advisors and their schemes. I place my trust in my soldiers' blades.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foltest - Son of Medell\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"foltest_son\",\n" +
                "      \"filename\": \"foltest_son_of_medell\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Dammit, I rule this land and I refuse to creep around its corners.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ballista\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"ballista\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"'Usually we give 'em female names.' 'Like Jenny?' 'More like Bertha'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Blue Stripes Commando\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"blue_stripes\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"I'd do anything for Temeria. Mostly, though, I kill for her.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Catapult\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"catapult_1\",\n" +
                "      \"count\": \"2\",\n" +
                "      \"flavor_txt\": \"The gods help those who have better catapults.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Crinfrid Reavers Dragon Hunter\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"crinfrid\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Haven't had much luck with monsters of late, so we enlisted.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dethmold\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dethmold\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I once made a prisoner vomit his own entails... Ah, good times...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dun Banner Medic\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"banner_nurse\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Stitch red to red, white to white, and everything will be all right.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Esterad Thyssen\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"esterad\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Like all Thyssen men, he was tall, powerfully built and criminally handsome.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"John Natalis\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"natalis\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The square should bear the name of my soldiers, of the dead. Not mine.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Kaedweni Siege Expert\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"kaedwen_siege\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"'You gotta recalibrate the arm by five degrees.' 'Do what by the what now?'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Kaedweni Siege Expert\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"kaedwen_siege_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"'You gotta recalibrate the arm by five degrees.' 'Do what by the what now?'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Kaedweni Siege Expert\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"kaedwen_siege_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"'You gotta recalibrate the arm by five degrees.' 'Do what by the what now?'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Keira Metz\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"keira\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If I'm to die today, I wish to look smashing for the occasion.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Philippa Eilhart\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"philippa\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Soon the power of kings will wither, and the Lodge shall seize its rightful place.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Poor Fucking Infantry\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"poor_infantry\",\n" +
                "      \"count\": \"4\",\n" +
                "      \"flavor_txt\": \"I's a war veteran! ... spare me a crown?\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Prince Stennis\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"stennis\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"He ploughin' wears golden armor. Golden. 'course he's an arsehole.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Redanian Foot Soldier\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"redania\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I've bled for Redania! I've killed for Redania... Dammit, I've even raped for Redania.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Redanian Foot Soldier\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"redania_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I've bled for Redania! I've killed for Redania... Dammit, I've even raped for Redania.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Sheldon Skaggs\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"sheldon\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I was there, on the front lines! Right were the fightin' was the thickest!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Siege Tower\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"siege_tower\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I love the clamor of siege towers in the morning. Sounds like victory.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Siegfried of Denesle\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"siegfried\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"We're on the same side, witcher. You'll realize this one day.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Sigismund Dijkstra\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"dijkstra\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Gwent's like politics, just more honest.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Síle de Tansarville\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"sheala\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The Lodge lacks humility. Our lust for power may yet be our undoing.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Thaler\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"thaler\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Fuck off! We aren't all ploughin' philanderers. Some of us have depth...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Sabrina Glevissig\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"sabrina\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The Daughter of the Kaedweni Wilderness.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vernon Roche\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"vernon\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A patriot... and a real son of a bitch.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ves\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"ves\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Better to live one day as a king than a whole life as a beggar.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Yarpen Zigrin\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"yarpen\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The world belongs to whoever's best at crackin' skulls and impregnatin' lasses.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Trebuchet\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"trebuchet\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Castle won't batter itself down, now will it? Get them trebuchets rollin'!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Trebuchet\",\n" +
                "      \"type\": \"realms\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"trebuchet_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Castle won't batter itself down, now will it? Get them trebuchets rollin'!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emhyr var Emreis - His Imperial Majesty\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"emhyr_imperial\",\n" +
                "      \"filename\": \"emhyr_silver\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A sword is but one of many tools at a ruler's disposal.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emhyr var Emreis - Emperor of Nilfgaard\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"emhyr_emperor\",\n" +
                "      \"filename\": \"emhyr_copper\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The skies wept when my Pavetta died. They will not weep for me.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emhyr var Emreis - the White Flame\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"emhyr_whiteflame\",\n" +
                "      \"filename\": \"emhyr_bronze\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Your motives do not interest me. Only results.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emhyr var Emreis - The Relentless\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"emhyr_relentless\",\n" +
                "      \"filename\": \"emhyr_gold\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They do not call me the Patient. Take care they do not call you the headless.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Emhyr var Emreis - Invader of the North\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"emhyr_invader\",\n" +
                "      \"filename\": \"emhyr_invader_of_the_north\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Emperors command multitudes, yet cannot control two things: their time and their hearts.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Albrich\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"albrich\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A fireball? Of course. Whatever Your Imperial Majesty wishes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Assire var Anahid\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"assire\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Nilfgaardian mages do have a choice: servile submission, or the gallows.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Black Infantry Archer\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"black_archer\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I aim for the knee. Always.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Black Infantry Archer\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"black_archer_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I aim for the knee. Always.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cahir Mawr Dyffryn aep Ceallach\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"cahir\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"His eyes flashed under his winged helmet. Fire gleamed form his sword's blade.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cynthia\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"cynthia\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Cynthia's talents can be deadly. She needs a tight leash.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Etolian Auxiliary Archers\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"archer_support\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Double or nothing, aim for his cock.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Etolian Auxiliary Archers\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"archer_support_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Double or nothing, aim for his cock.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Fringilla Vigo\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"fringilla\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Magic is the highest good. It transcends all borders and divisions.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Heavy Zerrikanian Fire Scorpion\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"heavy_zerri\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Not the best for taking cities, but great for razing them to the ground.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Impera Brigade Guard\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"imperal_brigade\",\n" +
                "      \"count\": \"4\",\n" +
                "      \"flavor_txt\": \"The Impera Brigade never surrenders.Ever.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Letho of Gulet\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"letho\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Witcher never die in their beds.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Menno Coehoorn\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero medic\",\n" +
                "      \"filename\": \"menno\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'll take attentive reconnaissance unit over a fine cavalry brigade any day.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Morteisen\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"morteisen\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"No Nordling pikemen or dwarven spearbearers can hope to best trained cavalry.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Morvran Voorhis\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"moorvran\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Summer sun reflecting in the quiet waters of the Alba - that's Nilfgaard to me.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Nausicaa Cavalry Rider\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"nauzicaa_2\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"The Emperor will teach the North discipline.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Puttkammer\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"puttkammer\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Learned a lot at Braibant Military Academy. How to scrub potatoes, for instance.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Rainfarn\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"rainfarn\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"You'll die as painfully as that pathetic traitor Windhalm did.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Renuald aep Matsen\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"renuald\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They say the Impera fear nothing. Untrue. Renuald scares them shitless.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Rotten Mangonel\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"rotten\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The rotten smell brings back childhood memories.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Shilard Fitz-Oesterlen\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"7\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"shilard\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Warfare is mere sound and fury - diplomacy is what truly shapes history.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Siege Engineer\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"siege_engineer\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Wielded correctly, a protractor can be a deadly weapon.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Siege Technician\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"siege_support\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I never miss twice.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Stefan Skellen\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"9\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"stefan\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"My mark scars the face of our future empress. That is my proudest achievement.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Sweers\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"sweers\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"And hands off the girl! Whatever we may be, we're not savages.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Tibor Eggebracht\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"tibor\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Albaaa! Forward!! Alba! Long live the Emperor!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vanhemar\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vanhemar\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"For a fire mage, he's not very... flamboyant.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vattier de Rideaux\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"spy\",\n" +
                "      \"filename\": \"vattier\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"There's never been a problem a well-planned assassinations couldn't solve.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vreemde\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vreemde\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Discipline is the Empire's deadliest weapon.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Young Emissary\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"young_emissary\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If i acquit myself well, perhaps next they'll post me somewhere civilized.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Young Emissary\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"young_emissary_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If i acquit myself well, perhaps next they'll post me somewhere civilized.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Zerrikanian Fire Scorpion\",\n" +
                "      \"type\": \"nilfgaard\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"zerri\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The Zerrikanian Desert used to be a lush garden. Then these came along.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eredin - Commander of the Red Riders\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"eredin_commander\",\n" +
                "      \"filename\": \"eredin_silver\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"It is unavoidable.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eredin - Bringer of Death\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"eredin_bringer_of_death\",\n" +
                "      \"filename\": \"eredin_bronze\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Have some dignity. You know how this will end.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eredin - Destroyer of Worlds\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"eredin_destroyer\",\n" +
                "      \"filename\": \"eredin_gold\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I've long awaited this...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eredin - King of the Wild Hunt\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"eredin_king\",\n" +
                "      \"filename\": \"eredin_copper\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Go on. Show me your spins, pirouettes and faints. I want to watch.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eredin Bréacc Glas - The Treacherous\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"eredin_treacherous\",\n" +
                "      \"filename\": \"eredin_the_treacherous\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm enjoying this. You are my toy.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Arachas \",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"arachas\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ugly - nature's way of saying 'Stay the fuck away.'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Arachas \",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"arachas_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ugly - nature's way of saying 'Stay the fuck away.'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Arachas \",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"arachas_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ugly - nature's way of saying 'Stay the fuck away.'\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Arachas- Behemoth\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"arachas_behemoth\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Like a cross between a crab, a spider... and a ploughin' mountain.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Botchling\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"poroniec\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Admit your mistakes and bury them proper - else they'll come back to haunt you.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Celaeno Harpy\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"celaeno_harpy\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Common harpies feed on carrion. Celaeno harpies... they feed on dreams.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cockatrice\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"cockatrice\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Born of an egg laid by a cockerel... if you believe such peasant drivel.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Crone - Brewess\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"witch_velen\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"We'll cut you up, boy. A fine broth you will make.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Crone - Weavess\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"witch_velen_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I sense your pain. I see your fear...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Crone - Whispess\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"witch_velen_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'd be your best - and last.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Draug\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"draug\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Some men cannot admit defeat. Some keep fighting from beyond the grave.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Earth Elemental\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"earth_elemental\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"How to fight an earth elemental? You don't. You run. Fast as you can.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Endrega\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"endrega\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The nest! Take out the nest, or the bastards'll just keep coming!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Fiend\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"fiend\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A fiend looks a bit like a deer. An enormous, evil deer.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Fire Elemental\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"fire_elemental\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Fire is so delightful.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Foglet\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"fogling\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Fog creeps on little cat feet. Foglets creep over the bodies of their victims.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Forktail\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"forktail\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Fork tails... Bah! Fuckers' tails're more like cleavers.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Frightener\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"frightener\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"'What have I done?' the mage cried out, frightened of his own creation.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Gargoyle\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"gargoyle\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ancient sculptors' nightmarish fantasies, brought to life by bored mages.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ghoul\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"ghoul\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If ghouls are part of the Circle of Life... then it's a damn vicious circle.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ghoul\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"ghoul_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If ghouls are part of the Circle of Life... then it's a damn vicious circle.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ghoul\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"ghoul_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"If ghouls are part of the Circle of Life... then it's a damn vicious circle.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Grave Hag\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"gravehag\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Their long tongues're for slurping marrow - and whipping prey.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Griffin\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"gryffin\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Griffins like to toy with their prey. Eat 'em alive, piece by piece.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Harpy\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"harpy\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"There are many species of harpy, and all are kleptomaniacs.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ice Giant\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"frost_giant\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Fled one time in my life. From the Ice Giant. And I'm not a bit ashamed.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Imlerith\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"imlerith\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ladd nahw! Kill them! Litter the earth with their entrails!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Kayran\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"hero morale\",\n" +
                "      \"filename\": \"kayran\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Kill a kayran? Simple. Take your best sword... then sell it and hire a witcher.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Leshen\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"leshen\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"We never hunt in these woods. Not even if it means the whole village starves.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Nekker\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"nekker\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Damn things are almost cute, if you ignore the whole vicious killer aspect.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Nekker\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"nekker_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Damn things are almost cute, if you ignore the whole vicious killer aspect.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Nekker\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"nekker_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Damn things are almost cute, if you ignore the whole vicious killer aspect.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Plague Maiden\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mighty_maiden\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The sick rave about a boil-pocked woman surrounded by herds of rabid rats...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vampire - Bruxa\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"bruxa\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A vile, bloodthirsty, man-eating hag. Kind of like my mother-in-law.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vampire - Ekimmara\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"ekkima\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Who would think overgrown bats would have a taste for gaudy jewelry?\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vampire - Fleder\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"fleder\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Higher vampires embrace their victims. Fleders rip them to shreds.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vampire - Garkain\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"garkain\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Blood-drinkers and corpse-eaters so foul their very ugliness paralyses foes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vampire - Katakan\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"katakan\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Drinking the blood of the Continent since the Conjunction.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Werewolf\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"werewolf\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Wolves aren't as bad as they say. Werewolves, though - they're worse.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Wyvern\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"wyvern\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Imagine a cross between a winged snake and a nightmare. Wyverns are worse.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Toad\",\n" +
                "      \"type\": \"monsters\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"7\",\n" +
                "      \"ability\": \"scorch_r\",\n" +
                "      \"filename\": \"toad\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Big. Bad. Ugly. Squats in the sewers.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Francesca Findabair - Queen of Dol Blathanna\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"francesca_queen\",\n" +
                "      \"filename\": \"francesca_silver\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ash shall fertilize the soil. By spring, the valley shall bloom once more.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Francesca Findabair - the Beautiful\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"francesca_beautiful\",\n" +
                "      \"filename\": \"francesca_gold\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The Elder Races have forgotten more than humans can ever hope to know.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Francesca Findabair - Daisy of the Valley\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"francesca_daisy\",\n" +
                "      \"filename\": \"francesca_copper\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Do not let my beauty distract your aim.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Francesca Findabair - Pureblood Elf\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"francesca_pureblood\",\n" +
                "      \"filename\": \"francesca_bronze\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"To live in peace, we first must kill. This is human oppression's cruel finale.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Francesca Findabair - Hope of the Aen Seidhe\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"francesca_hope\",\n" +
                "      \"filename\": \"francesca_hope_of_the_aen_seidhe\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Daede sian caente, Aen Seidhe en'allane ael coeden...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ciaran aep Easnillien\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"ciaran\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The path to freedom is paved in blood, not ink.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Barclay Els\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"barclay\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Our mead smells like piss, do it? Easy to fix - I'll break your fuckin' nose!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dennis Cranmer\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dennis\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I know how to carry out orders, so you can shove you advice up your coal chute.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dol Blathanna Archer\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dol_archer\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Take another step, dh'oine. You'd look better with an arrow between your eyes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dol Blathanna Scout\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dol_infantry\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They track like hounds, run like deer and kill like cold-hearted bastards.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dol Blathanna Scout\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dol_infantry_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They track like hounds, run like deer and kill like cold-hearted bastards.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dol Blathanna Scout\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"dol_infantry_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They track like hounds, run like deer and kill like cold-hearted bastards.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dwarven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"dwarf\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Worked a pickaxe all me life. Battleaxe won't be any trouble.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dwarven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"dwarf_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Worked a pickaxe all me life. Battleaxe won't be any trouble.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dwarven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"3\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"dwarf_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Worked a pickaxe all me life. Battleaxe won't be any trouble.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Eithné\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"eithne\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The dryad queen has eyes of molten silver, and a heart of cold-forged steel.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Elven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"elf_skirmisher\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"No matter what you may have heard, elves don't take human scalps. Too much lice.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Elven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"elf_skirmisher_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"No matter what you may have heard, elves don't take human scalps. Too much lice.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Elven Skirmisher\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"elf_skirmisher_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"No matter what you may have heard, elves don't take human scalps. Too much lice.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Filavandrel aen Fidhail\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"filavandrel\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Though we are now few and scattered, our hearts burn brighter than ever.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Healer\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"havekar_nurse\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Sure, I'll patch you up. Gonna cost you, though.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Healer\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"havekar_nurse_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Sure, I'll patch you up. Gonna cost you, though.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Healer\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"havekar_nurse_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Sure, I'll patch you up. Gonna cost you, though.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Smuggler\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"havekar_support\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I fight for whoever's paying the best. Or whoever's easiest to rob.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Smuggler\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"havekar_support_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I fight for whoever's paying the best. Or whoever's easiest to rob.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Havekar Smuggler\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"havekar_support_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I fight for whoever's paying the best. Or whoever's easiest to rob.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ida Emean aep Sivney\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"ida\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I am a Sage. My power lies in possessing knowledge. Not sharing it.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Iorveth\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"iorveth\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"King or beggar, what's the difference? One dh'oine less.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Isengrim Faoiltiarna\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero morale\",\n" +
                "      \"filename\": \"isengrim\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"It dawns on them once they notice my scar: a realization of imminent death.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mahakaman Defender\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mahakam\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm telling ye, we're born fer battle - we slash straight at their knees!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mahakaman Defender\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mahakam_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm telling ye, we're born fer battle - we slash straight at their knees!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mahakaman Defender\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mahakam_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm telling ye, we're born fer battle - we slash straight at their knees!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mahakaman Defender\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mahakam_3\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm telling ye, we're born fer battle - we slash straight at their knees!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mahakaman Defender\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"mahakam_4\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm telling ye, we're born fer battle - we slash straight at their knees!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Milva\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"milva\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"With each arrow I loose, I think of my da. He'd be proud. I think.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Riordain\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"1\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"riordain\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Stare into their eyes, feast on their terror. Then go in for the kill.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Saesenthessis\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"saskia\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Beautiful, pure, fierce - the perfect icon for a rebellion.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Toruviel\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"toruviel\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'd gladly kill you from up close, stare in your eyes... But you reek, human.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vrihedd Brigade Recruit\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vrihedd_cadet\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Hatred burns brighter than any fire, and cuts deeper than any blade.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vrihedd Brigade Veteran\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vrihedd_brigade\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Vrihedd? What's that mean? Trouble.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Vrihedd Brigade Veteran\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"5\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"vrihedd_brigade_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Vrihedd? What's that mean? Trouble.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Yaevinn\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"yaevinn\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"We are the drops of rain that together make a ferocious storm.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Berserker\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"berserker\",\n" +
                "      \"filename\": \"berserker\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Kill, crush, cleave!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Birna Bran\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"medic\",\n" +
                "      \"filename\": \"birna\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Skellige must have a strong king. No matter what it takes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Blueboy Lugos\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"blueboy\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I'm damn near ready to puke from boredom.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cerys\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero muster\",\n" +
                "      \"filename\": \"cerys\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They call me Sparrowhawk, know why? Because I eat rats like you for breakfast.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clan Brokva Archer\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"brokva_archer\",\n" +
                "      \"count\": \"2\",\n" +
                "      \"flavor_txt\": \"So ye can hit a movin' target at two hundred paces. Me, too. In a storm.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clan Dimun Pirate\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"scorch\",\n" +
                "      \"filename\": \"dimun_pirate\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Can see the fear in their eyes. Fear o' me... fear o' Clan Dimun!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cerys - Clan Drummond Shield Maiden\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"shield_maiden\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They'll shatter on our shields like waves on a rocky shore.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cerys - Clan Drummond Shield Maiden\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"shield_maiden_1\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They'll shatter on our shields like waves on a rocky shore.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Cerys - Clan Drummond Shield Maiden\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"shield_maiden_2\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"They'll shatter on our shields like waves on a rocky shore.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clan Heymaey Skald\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"heymaey\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The deeds of Clan Heymaey will go down in history.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clan Tordarroch Armorsmith\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"tordarroch\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Ye're in for a pundin'.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clan an Craite Warrior\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"craite_warrior\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"I'll bring the an Craites such glory, bards'll go hoarse singin' me praises!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Donar an Hindar\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"donar\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"I've gathered all the jarls together. Now make your case.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Draig Bon-Dhu\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"horn\",\n" +
                "      \"filename\": \"draig\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Hear ye now the tale of the heroic deeds of Clan an Craite.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Ermion\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"hero mardroeme\",\n" +
                "      \"filename\": \"ermion\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Only the ignorant dismiss the importance of myths.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Hemdall\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"11\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"hemdall\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"When the time of the White Frost comes, Hemdall will sound the call for battle.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Hjalmar\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"10\",\n" +
                "      \"ability\": \"hero\",\n" +
                "      \"filename\": \"hjalmar\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Instead of mournin' the fallen, let's drink to their memory!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Holger Blackhand\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"holger\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Now let's drink to Emperor of Nilfgaard - may his prick forever stay limp!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Kambi\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"avenger_kambi\",\n" +
                "      \"filename\": \"kambi\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"When the time comes, the cockerel Kambi shall crow and awaken Hemdall.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Light Longship\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"muster\",\n" +
                "      \"filename\": \"light_longship\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Escape them? In the waters of Skellige? Good luck.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Madman Lugos\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"madmad_lugos\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"WAAAAAAAAAAGH!!!!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Mardroeme\",\n" +
                "      \"type\": \"special\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"mardroeme\",\n" +
                "      \"filename\": \"mardroeme\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Eat enough of them, and the world will never be the same...\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Olaf\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"agile\",\n" +
                "      \"strength\": \"12\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"olaf\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Many've tried to defeat Olaf. But won't hear about it from them - they're dead.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Skellige Storm\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"rain fog\",\n" +
                "      \"filename\": \"storm\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"This ain't no normal storm. This here's the wrath of the gods.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Svanrige\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"svanrige\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"The emperor also thought him an accidental king. At first.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Transformed Vildkaarl\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"14\",\n" +
                "      \"ability\": \"morale\",\n" +
                "      \"filename\": \"vildkaarl\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Saw them fight once in my life... and once was enough.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Transformed Young Vildkaarl\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"young_vildkaarl\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Rooaaaar!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Udalryk\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"4\",\n" +
                "      \"ability\": \"none\",\n" +
                "      \"filename\": \"udalryk\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Haaaargh! After me, if ye've the bollocks!\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"War Longship\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"6\",\n" +
                "      \"ability\": \"bond\",\n" +
                "      \"filename\": \"war_longship\",\n" +
                "      \"count\": \"2\",\n" +
                "      \"flavor_txt\": \"They say Hemdall's heart swells whenever the longships sail out on a raid.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Young Berserker\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"ranged\",\n" +
                "      \"strength\": \"2\",\n" +
                "      \"ability\": \"berserker\",\n" +
                "      \"filename\": \"young_berserker\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Want some?\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Crach an Craite\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"crach_an_craite\",\n" +
                "      \"filename\": \"crach_an_craite\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"A king's gotta be wise. A king's gotta command respect. A king's gotta have stones.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"King Bran\",\n" +
                "      \"type\": \"skellige\",\n" +
                "      \"row\": \"leader\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"king_bran\",\n" +
                "      \"filename\": \"king_bran\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"No one can replace Bran. Though they're sure to try.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Schirru\",\n" +
                "      \"type\": \"scoiatael\",\n" +
                "      \"row\": \"siege\",\n" +
                "      \"strength\": \"8\",\n" +
                "      \"ability\": \"scorch_s\",\n" +
                "      \"filename\": \"schirru\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"Time to look death in the face.\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cardGenerator = new CardGenerator(targetContext);
        cardGenerator.loadCardJSONFromAsset();
        assertEquals(cardGenerator.loadCardJSONFromAsset(), jsonStringAllCards);
    }
}
