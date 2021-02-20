package com.ygsoft.tools.ini;


import java.util.*;

public class IniSection {

    private String sectionName;
    private HashMap<String, String> data = new HashMap<>();



    public IniSection(String name) {
        this.sectionName = name;
    }



    public String getSectionName() {
        return sectionName;
    }



    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    public HashMap<String, String> getData() {
        return data;
    }



    public String getValue(String key) {
        return (data.getOrDefault(key, ""));
    }



    public boolean contains (String key) {
        return data.containsKey(key);
    }



    public List<String> getKeys() {

        Set<String> keys = data.keySet();

        return new ArrayList<>(keys);
    }



    public void setContent (String key, String value) {
        data.put(key, value);
    }
}
