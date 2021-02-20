package com.ygsoft.tools.ini;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IniFilesWrapper {


    private File configFile;
    private HashMap<String, IniSection> data = new HashMap<>();


    public IniFilesWrapper(File iniFile) {

        this.configFile = iniFile;

        // Parse the file.
        parseFile();
    }



    private void parseFile() {

        List<String> list = new ArrayList<>();

        if (configFile.exists()) {

            try (Stream<String> stream = Files.lines(Paths.get(configFile.getAbsolutePath()))) {
                list = stream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Pattern pSection = Pattern.compile(IniConstants.REGEX_SECTION.getRegex());
            Pattern pKeyValue = Pattern.compile(IniConstants.REGEX_KEY_VALUE.getRegex());

            String sectionName    = "";
            boolean isInSection   = false;
            IniSection iniSection = null;


            for (String currentLine : list) {

                Matcher mSectionName = pSection.matcher(currentLine);
                Matcher mKeyValue = pKeyValue.matcher(currentLine);

                if (mSectionName.matches()) {

                    sectionName = mSectionName.group(1);

                    if (isInSection) {

                        isInSection = false;

                        // Set the section in the main data.
                        data.put(iniSection.getSectionName(), iniSection);

                    }

                    isInSection = true;

                    iniSection = new IniSection(sectionName);
                }


                if (mKeyValue.matches()) {
                    if (iniSection != null) {
                        iniSection.setContent(mKeyValue.group(1), mKeyValue.group(2));
                    }
                }
            }

            // Add the last section.
            data.put(sectionName, iniSection);
        }
    }



    public File getConfigFile() {
        return configFile;
    }



    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }



    public List<IniSection> getSections() {
        return new ArrayList<>(data.values());
    }



    public List<String> getSectionNames() {
        return new ArrayList<>(data.keySet());
    }



    public void dump() throws IOException {

        // Backup the current config file.
        if (configFile.exists()) {
            File tempFile = File.createTempFile("Temp", ".tmp");

            if (!configFile.renameTo(tempFile)) {
                throw new IOException("Cannot backup the config file.");
            }
        }

        // Create a String builder from the config.
        StringBuilder stringBuilder = new StringBuilder();

        for (IniSection section : getSections()) {

            stringBuilder.append("[");
            stringBuilder.append(section.getSectionName());
            stringBuilder.append("]");
            stringBuilder.append("\n");

            for (String key : section.getKeys()) {
                stringBuilder.append(String.format("%s=%s", key, section.getValue(key)));
                stringBuilder.append("\n");
            }
        }

        FileUtils.writeStringToFile(configFile, stringBuilder.toString(), Charset.defaultCharset());
    }



    public String getValue (String sectionName, String key) {

        String temp = data.get(sectionName).getValue(key);

        return (temp == null) ? "" : temp;
    }



    public List<String> getFilteredSectionNames(String filter) {

        List<String> filteredList = new ArrayList<>();

        List<String> allSections = getSectionNames();

        if (allSections != null && allSections.size() > 0) {

            for (String s : allSections) {
                if (s.contains(filter)) {
                    filteredList.add(s);
                }
            }
        }

        return filteredList;
    }


    public void addSection(String sectionName) {
        IniSection newSection = new IniSection(sectionName);
        data.put(sectionName, newSection);
    }



    public void setValue(String sectionName, String key, String value) throws Exception {
        if (sectionExists(sectionName)) {
            IniSection section = data.get(sectionName);
            section.setContent(key, value);
            data.put(sectionName, section);
        }
        else {
            throw new Exception(String.format("The '%s' section does not exist.", sectionName));
        }
    }


    public boolean sectionExists(String sectionName) {
        return data.containsKey(sectionName);
    }
}


