package com.ygsoft.apps.camping;

import com.google.gson.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





class ParticipantsData {

    private boolean isParsed = false;
    private HashMap<String, Participant> data = new HashMap<>();



    ParticipantsData() {}



    public List<Participant> getPreDefinedParticipant() {

        List<Participant> list = new ArrayList<>();

        if (isParsed) {

            for (String key : data.keySet()) {
                list.add(data.get(key));
            }
        }

        return list;
    }



    void parseData(File dataFile) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(dataFile.getAbsolutePath())) {

            // Convert JSON to JsonElement, and later to String
            JsonElement json = gson.fromJson(reader, JsonElement.class);

            JsonObject joAllData = json.getAsJsonObject();

            JsonArray jaAllParticipants = joAllData.getAsJsonArray("participants");

            for (int i = 0; i < jaAllParticipants.size(); i++) {

                JsonElement jeCurrentParticipant = jaAllParticipants.get(i);
                JsonObject  joCurrentParticipant = jeCurrentParticipant.getAsJsonObject();
                String name         = joCurrentParticipant.get("name").getAsString();
                String email        = joCurrentParticipant.get("email").getAsString();
                String initials     = joCurrentParticipant.get("initials").getAsString();
                int    vehicleIndex = joCurrentParticipant.get("vehicle_index").getAsInt();

                Participant participant = new Participant(initials, name, email, vehicleIndex);

                data.put(initials, participant);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        isParsed = true;
    }



    public Participant getParticipantByInitials (String initials) {
        return (isParsed) ?
                data.getOrDefault(String.valueOf(initials), null) :
                null;
    }



    public Participant getParticipantByName(String name) {

        Participant participant = null;

        if (isParsed) {

            for (String key : data.keySet()) {

                Participant p = data.get(key);

                if (p.getName().equals(name)) {
                    participant = p;
                    break;
                }
            }
        }

        return participant;
    }
}




