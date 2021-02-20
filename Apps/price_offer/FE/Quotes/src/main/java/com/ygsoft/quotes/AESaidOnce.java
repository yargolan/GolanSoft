package com.ygsoft.quotes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AESaidOnce {

    int userIndex     = 0;
    List<String> list;


    public AESaidOnce(){}


    public static void main(String[] args) {

        // Set LNF.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Cannot set LNF.");
        }

        AESaidOnce aeSaidOnce = new AESaidOnce();

        // Read the quotes from the JSON file.
        try {
            aeSaidOnce.parseQuotes();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        aeSaidOnce.getUserIndex();

        aeSaidOnce.showIt();
    }


    private void parseQuotes() throws IOException {
        String data = IOUtils.resourceToString("/db.json", StandardCharsets.UTF_8);
        JsonObject joData = new Gson().fromJson(data, JsonObject.class);

        list = new ArrayList<>();
        for (int i = 1; i < joData.size(); i++) {
            list.add(joData.get(String.valueOf(i)).getAsString());
        }
    }



    private void getUserIndex() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter quote index: ");
        userIndex = scan.nextInt();
        scan.close();
    }



    private void showIt() {
        int size = list.size() - 1;

        if (userIndex < 1 || userIndex > size) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please pick a number between 1 and " + size,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        JOptionPane.showMessageDialog(
                null,
                list.get(userIndex),
                "A. Einstein once said ...",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
