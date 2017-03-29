package com.example.administrator.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by Administrator on 2017/3/18.
 */

public final class PetContract {

    private PetContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.administrator.pets";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_PETS = "pets";

    public static final class PetEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static final String TABLE_NAME = "pets";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int UNKNOWN =0;
        public static final int MALE = 1;
        public static final int FEMALE =2;

        /**
         * Returns whether or not the given gender is {@link #UNKNOWN}, {@link #MALE},
         * or {@link #FEMALE}.
         */
        public static boolean isValidGender(int gender) {
            if (gender == UNKNOWN || gender == MALE || gender == FEMALE) {
                return true;
            }
            return false;
        }

    }

}
