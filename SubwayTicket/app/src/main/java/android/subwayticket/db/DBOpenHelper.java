package android.subwayticket.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zach on 2016/8/8.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="subwayticket.db";
    private static final int VERSION=1;
    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_notice(_id integer primary key autoincrement," +
                                            "noticeTitle varchar(255)," +
                                            "noticeContent varchar(255) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
