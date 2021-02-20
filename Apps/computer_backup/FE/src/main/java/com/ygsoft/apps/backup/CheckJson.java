package com.ygsoft.apps.backup;

import com.google.gson.Gson;
import com.ygsoft.apps.backup.data.ProfileObject;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class CheckJson {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        try {
            // create user object
            ProfileObject po = new ProfileObject("ProfileName", "/var/tmp/backup", "", list);

            // create Gson instance
            Gson gson = new Gson();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("j.json"));

            // convert user object to JSON file
            gson.toJson(po, writer);

            // close writer
            writer.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
