package com.golansoft.apps.computerbackup;

public enum HardCoded {
    CURRENT_VERSION ("v3.1"),
    ME              ("yargolan@gmail.com,   ירון גולן"),

    B_ADD            ("הוסף"),
    B_DELETE         ("מחק !"),
    B_CANCEL         ("ביטול"),
    B_SEARCH         ("עיון..."),
    B_CREATE         ("צור"),
    B_PROFILE_RUN    ("הרצת גיבוי"),
    B_TARGET_FOLDER  ("תיקיית היעד "),
    B_PROFILE_CREATE ("יצירת פרופיל חדש"),
    B_PROFILE_DELETE ( "מחיקת פרופיל"),

    F_MAIN              ("גיבוי מחשב"),
    F_CREATE_NEW_PROFILE(B_PROFILE_CREATE.getText()),

    L_PROFILE_NAME   ("שם הפרופיל"),
    L_PROFILE_DESC   ("תיאור"),
    L_PROFILE_USER   ("שם המשתמש"),
    L_PROFILE_HOST   ("שם המחשב"),
    L_PROFILE_ITEMS  ("פריטים לגיבוי"),

    M_ARE_YOU_SURE     ("האם אתה בטוח ?"),
    M_FILE_DELETED_GOOD("הקובץ נמחק בהצלחה"),
    M_FILE_DELETED_BAD ("הקובץ לא נמחק"),
    M_NO_PROFILES_FOUND("לא נמצאו קבצים מתאימים")
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

