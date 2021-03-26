package com.yg.apps.cars.data;

import com.yg.apps.cars.DataSingleton;



public class DbWrapperMaintenance extends DbWrapper {


    private final DataSingleton dataSingleton = DataSingleton.getInstance();


    public DbWrapperMaintenance(Maintenance maintenance) {
        super(maintenance);
    }


//
//
//    @Override
//    public void addToDb(Maintenance m) {
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    }

/*
        List<Garage> garagesList = new ArrayList<>();

        File garagesDb = dataSingleton.getParsedGaragesFile();

        if (garagesDb.exists()) {

            try {
                Reader reader = new FileReader(garagesDb);

                JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

                JsonArray jaAllGarages = jeAll.getAsJsonArray();

                for (int i = 0; i < jaAllGarages.size(); i++) {

                    currentGarageIndex = i+1;

                    JsonObject joCurrentGarage = jaAllGarages.get(i).getAsJsonObject();

                    Garage g = new Garage(
                            currentGarageIndex,
                            joCurrentGarage.get("name").getAsString(),
                            joCurrentGarage.get("address").getAsString(),
                            joCurrentGarage.get("contact").getAsString(),
                            joCurrentGarage.get("phone_number").getAsString()
                    );

                    garagesList.add(g);
                }
            }
            catch (IOException e) {
                if (AppHelper.is_under_dev()) {
                    e.printStackTrace();
                }
            }
        }


        // Add the current garage to the list.
        if (garagesList.size() == 0) {
            garageDetails.setId(1);
        }
        else {
            garageDetails.setId(garagesList.size() + 1);
        }

        garagesList.add(garageDetails);


        // dump the list into the file.
        try (final FileWriter fileWriter = new FileWriter(garagesDb)) {
            gson.toJson(garagesList, fileWriter);
        }
        catch (IOException e) {
            if (AppHelper.is_under_dev()) {
                e.printStackTrace();
            }

            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot save the new garage");
        }
    }
    */



//    public void updateDb(Garage editedGarage) {
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        List<Garage> garagesList = new ArrayList<>();
//
//        File garagesDb = dataSingleton.getParsedGaragesFile();
//
//        try {
//            Reader reader = new FileReader(garagesDb);
//
//            JsonElement jeAll = gson.fromJson(reader, JsonElement.class);
//
//            JsonArray jaAllGarages = jeAll.getAsJsonArray();
//
//            for (int i = 0; i < jaAllGarages.size(); i++) {
//                JsonObject joCurrentGarage = jaAllGarages.get(i).getAsJsonObject();
//                Garage g = new Garage(
//                        joCurrentGarage.get("id").getAsInt(),
//                        joCurrentGarage.get("name").getAsString(),
//                        joCurrentGarage.get("address").getAsString(),
//                        joCurrentGarage.get("contact").getAsString(),
//                        joCurrentGarage.get("phone_number").getAsString()
//                );
//
//                garagesList.add(g);
//            }
//        }
//        catch (IOException e) {
//            if (AppHelper.is_under_dev()) {
//                e.printStackTrace();
//            }
//        }
//
//
//        // Filter
//        List<Garage> newList = new ArrayList<>();
//
//        for (Garage g : garagesList) {
//
//            if (g.getId() == editedGarage.getId()) {
//
//                g = new Garage(
//                        editedGarage.getId(),
//                        editedGarage.getName(),
//                        editedGarage.getAddress(),
//                        editedGarage.getContact(),
//                        editedGarage.getPhoneNumber()
//                );
//            }
//
//            newList.add(g);
//        }
//
//
//        // dump the list into the file.
//        try (final FileWriter fileWriter = new FileWriter(garagesDb)) {
//            gson.toJson(newList, fileWriter);
//        }
//        catch (IOException e) {
//            if (AppHelper.is_under_dev()) {
//                e.printStackTrace();
//            }
//
//            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot save the new garage");
//        }
//    }
//
//
//
//    @Override
//    public void addToDb(Garage garage) {
//
//    }
}
