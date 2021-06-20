package com.golansoft.apps.computerbackup;

public enum HardCoded {
    CURRENT_VERSION ("v3.1"),
    ME              ("yargolan@gmail.com,   ירון גולן"),

    B_ADD            ("הוסף פריט"),
    B_DELETE         ("מחק !"),
    B_CANCEL         ("ביטול"),
    B_SEARCH         ("עיון..."),
    B_SAVE           ("שמור פרופיל"),
    B_RUN_BACKUP     ( "הרצת גיבוי"),
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

    M_ARE_YOU_SURE          ("האם אתה בטוח ?"),
    M_FILE_DELETED_BAD      ("הקובץ לא נמחק"),
    M_FILE_DELETED_GOOD     ("הקובץ נמחק בהצלחה"),

    M_ERR_ALREADY_EXISTS    ("הפריט כבר קיים ברשימה"),
    M_ERR_INTERNAL_ERROR    ("שגיאה פנימית במערכת"),
    M_ERR_NO_PROFILE_NAME   ("לא הוגדר שם הפרופיל"),
    M_ERR_NO_PROFILE_DESC   ("לא הוגדר תיאור הפרופיל"),
    M_ERR_NO_TARGET_FOLDER  ("לא הוגדרה תיקיית היעד"),
    M_ERR_NO_PROFILES_FOUND ("לא נמצאו קבצים מתאימים"),
    M_ERR_NO_ITEMS_TO_BACKUP("לא הוגדרו פריטים לגיבוי"),

    M_INF_PROFILE_CREATED   ("הפרופיל נוצר בהצלחה"),
    M_INF_BACKUP_SUCCEEDED  ("הגיבוי הושלם בהצלחה"),
    M_INF_BACKUP_IN_PROGRESS("הגיבוי מתבצע"),
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

