package com.ygsoft.apps;

import java.io.File;

public class DirStatus {

    private String status;



    public DirStatus() {
    }



    public void setOnlyAt (File differentFile) {
        this.status = "Found only at : " + differentFile.getAbsolutePath();
    }



    public void setCopyExisting (File existingFile) {
        this.status = "Copying an existing file : " + existingFile.getAbsolutePath();
    }



    public void setCopyNew (File existingFile) {
        this.status = "Copying a new file : " + existingFile.getAbsolutePath();
    }



    public void setDifferent (File sourceFile, File targetFile) {
        this.status = String.format(
                "Files differ  : '%s' '%s'",
                sourceFile.getAbsolutePath(),
                targetFile.getAbsolutePath()
        );
    }


    public String getStatus() {
        return status;
    }
}
