package personal.wl.mobilepointapp.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by asus on 2016/9/30.
 */

public class AllCityDbHelper extends SQLiteOpenHelper {

    private static final String TAG = AllCityDbHelper.class.getSimpleName();

//    private static String DB_PATH = "/data/data/personal.wl.mobilepointapp/databases/";
    private  String DB_PATH ;
    private static String DB_NAME = "all_cities.db";
    private static int DB_VERSION = 3;
    private Context mContext;

    public AllCityDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
        DB_PATH=context.getApplicationContext().getDatabasePath(DB_NAME).getAbsolutePath();

        String dd = context.getFilesDir().getPath();

    }

    public AllCityDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public AllCityDbHelper(Context context) {
        this(context,DB_NAME, null, DB_VERSION);
//        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建数据库
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean isExist = checkDataBase();
        if (!isExist) {
            Log.e(TAG, "createDataBase: 创建数据库" );
            File dir = new File(mContext.getDatabasePath(DB_NAME).getPath());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dbFile = new File(mContext.getDatabasePath(DB_NAME).getPath());
            if (dbFile.exists()) {
                dbFile.delete();
            }
            SQLiteDatabase.openOrCreateDatabase(dbFile,null);
            copyDataBase();
        }
    }

    /**
     * 检查数据库是否存在
     * @return
     */
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        try {
            checkDB = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            if (checkDB != null) {
                checkDB.close();
            }
        }
        return checkDB!=null ? true : false;
    }


    private void copyDataBase() throws IOException {
        InputStream inputStream = mContext.getAssets().open("cities.db");
        String filename =  mContext.getDatabasePath(DB_NAME).getPath() ;
        OutputStream outputStream = new FileOutputStream(filename);
        byte[] buffer = new byte[1024];
        int length;
        while ((length=inputStream.read(buffer)) > 0) {
            outputStream.write(buffer,0,length);
        }
        Log.e(TAG, "copyDataBase: 复制数据库完成");
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
