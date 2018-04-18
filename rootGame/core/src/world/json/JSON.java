package world.json;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;
import helpers.ItemType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO https://github.com/libgdx/libgdx/wiki/Reading-and-writing-JSON

/** The idea is that this gets called by the MapLoader and it ensures that
 * the jsonFIle is updated from/with the itemList.txt. */
public class JSON {

    private static final String itemListPath = "world/json/itemList.txt";
    private static final String itemJsonFile = "world/json/jsonFile";

    public static void main(String[] args) {

        JsonItem item = new JsonItem("The most epic sword", 1290, 1);

        Json json = new Json();
        System.out.println(json.prettyPrint(item));


    }

    /** Reads the itemList.txt and converts it into a JSON file. */
    public static void createJsonFile(){

        //Gets lines from txt file
        ArrayList<String> fileLines = getLinesFromFile(itemListPath);

        //Create an array of items from the lines
        ArrayList<JsonItem> jsonItems = new ArrayList<>();
        for(String line : fileLines)
            jsonItems.add(createJsonItemFromString(line));

        //Create JSON code from that array
        Json json = new Json();

        json.toJson(jsonItems.get(0));
        //Save to file
    }

    /** Takes a line and converts it to a JsonItem. */
    private static JsonItem createJsonItemFromString(String line){

        List<String> elementsList = Arrays.asList(line.split(":"));

        if(elementsList.size() != 4)
            throw new IllegalArgumentException(); //TODO ANOTHER

        return new JsonItem(Integer.valueOf(elementsList.get(0)), elementsList.get(1), ItemType.getItemTypeFromString(elementsList.get(2)), elementsList.get(3));
    }

    /** Reads a file and returns a String array with the lines. */
    private static ArrayList<String> getLinesFromFile(String pathToFile){

        ArrayList<String> linesArray = new ArrayList<>();

        File file = new File(pathToFile);

        //Fill array with items from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                linesArray.add(line);
            }
        }catch (IOException e) {
            System.out.println("Failed to open and read file:");
            e.printStackTrace();
        }

        linesArray.remove(0); //The "header" in the file

        return linesArray;
    }
}
