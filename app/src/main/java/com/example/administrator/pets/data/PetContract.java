package com.example.administrator.pets.data;

import android.provider.BaseColumns;


/**
 * Created by Administrator on 2017/3/18.
 */

public final class PetContract {

    private PetContract(){}

    public static final class PetEntry implements BaseColumns {
        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int UNKNOWN =0;
        public static final int MALE = 1;
        public static final int FEMALE =2;

    }
    public static final String TEXT_TYPE = " TEXT";
    public static final String NUMBER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+
            PetEntry.TABLE_NAME+"("+PetEntry._ID+NUMBER_TYPE+COMMA_SEP+PetEntry.COLUMN_PET_NAME+
            TEXT_TYPE+COMMA_SEP+PetEntry.COLUMN_BREED+TEXT_TYPE+COMMA_SEP+
            PetEntry.COLUMN_GENDER+NUMBER_TYPE+COMMA_SEP+PetEntry.COLUMN_WEIGHT+NUMBER_TYPE+")";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXIST"+PetEntry.TABLE_NAME;
}
