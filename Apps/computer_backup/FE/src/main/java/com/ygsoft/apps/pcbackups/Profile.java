package com.ygsoft.apps.pcbackups;

import java.util.List;




public class Profile {

    private final String host_name, profile_name, target_dir;
    private final List<String> items;


    public Profile(String _profileName, String _hostName, String _targetDir, List<String> _items) {
        this.items       = _items;
        this.host_name = _hostName;
        this.target_dir = _targetDir;
        this.profile_name = _profileName;
    }


    public String getHostName() {
        return host_name;
    }


    public String getTargetDir() {
        return target_dir;
    }


    public List<String> getItems() {
        return items;
    }


    public String getProfileName() {
        return profile_name;
    }


    public String _dump() {

        String line = "+--------------------------------\n";

        String message =  String.format(
                "| ZzProfile's name   : %s\n| Target folder    : %s\n| ZzProfile hostname : %s\n| Items to backup  :\n",
                getProfileName(),
                getTargetDir(),
                getHostName()
        );

        StringBuilder items = new StringBuilder();
        for (String item : getItems()) {
            items.append(String.format("|\t%s", item)).append("\n");
        }

        return String.format("%s%s%s%s", line, message, items.toString(), line);
    }



    public boolean compareTo(Profile anotherProfile) {
        return anotherProfile.getProfileName().equals(this.getProfileName());
    }
}
