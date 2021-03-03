package com.ygsoft.apps;

import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import com.google.gson.*;



public class BoundsWrapper {

    private final File boundsFile = new File("bounds.json");
    private final HashMap<String, String> boundsValues  = new HashMap<>();


    BoundsWrapper(){}



    public String getValue(String key) {
        return boundsValues.getOrDefault(key, null);
    }



    public void parse() {

        HashMap<String, String> shorts = new HashMap<>();
        shorts.put("label",      "L");
        shorts.put("button",     "B");
        shorts.put("check_box",  "CB");
        shorts.put("text_field", "TF");


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(boundsFile.getAbsolutePath())) {

            JsonElement json = gson.fromJson(reader, JsonElement.class);

            JsonObject joAllData = json.getAsJsonObject();

            List<String> uiComponents = List.of("label", "text_field", "button", "check_box");

            for (String uiType : uiComponents) {

                JsonArray jaAllTypes = joAllData.getAsJsonArray(uiType);

                for (int i = 0; i < jaAllTypes.size(); i++) {

                    JsonElement jeCurrentType = jaAllTypes.get(i);

                    JsonObject joCurrentType = jeCurrentType.getAsJsonObject();

                    boundsValues.put(
                            shorts.get(uiType) + "_" + joCurrentType.get("id").getAsString(),
                            joCurrentType.get("value").getAsString()
                    );
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
