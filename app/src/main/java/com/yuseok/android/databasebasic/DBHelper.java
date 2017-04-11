package com.yuseok.android.databasebasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yuseok.android.databasebasic.domain.Bbs;
import com.yuseok.android.databasebasic.domain.Memo;

import java.sql.SQLException;


/**
 * Created by YS on 2017-02-14.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper{

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 생성자에서 호출되는 super(context... 에서 database.db 파일이 생성되어 있지 않으면 호출된다.
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            // Bbs,class 파일에 정의된 테이블을 생성한다.
            TableUtils.createTable(connectionSource, Bbs.class);
            TableUtils.createTable(connectionSource, Memo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 생성자에서 호출되는 super(context... 에서 database.db 파일이 존재하지만 DB_VERSION이 증가되면 호출된다.
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
//            // Bbs.class에 정의된 테이블을 삭제
//            TableUtils.dropTable(connectionSource, Bbs.class, false); // 이부분에서 삭제하지 않는다면 같은 이름의 테이블을 생성하는것이 되므로 오류가 발생하고 다음버전이 생성되지 않는다.
//            // 데이터를 보존해야 될 필요성이 있으면 중간처리를 해줘야만 한다.
//            // TODO : 임시테이블을 생성한 후 데이터를 먼저 저장하고 onCreate이후에 데이터를 입력해준다.
//            // onCreate를 호출해서 테이블을 생성해준다.

            // 기존 데이터베이스 버전이 1일때 설치한 유저는 Memo테이블만 생성해준다.
            if(oldVersion == 1) {
                TableUtils.createTable(connectionSource,Bbs.class);
            } else {
                onCreate(database, connectionSource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


////    public Dao<Bbs, Long> getDao() throws SQLException {
////        return getDao(Bbs.class);
////    }
////
//    public Dao<Bbs, Long> getDao() throws SQLException {
//        Dao<Bbs, Long> bbsDao = null; // 두개의 타입이 동시에 들어가는 컬렉션. putExtra와 유사? 같음?.
//        if(bbsDao == null) {
//            bbsDao = getDao(Bbs.class);
//        }
//        return bbsDao;
//    }
//
//    public Dao<Bbs,Long> getBbsDao() throws SQLException {
//        return getDao(Bbs.class);
//    }

    private Dao<Bbs, Long> bbsDao = null;
    public Dao<Bbs,Long> getBbsDao() throws SQLException {
        if(bbsDao == null) {
            bbsDao = getDao(Bbs.class);
        }
        return bbsDao;
    }

    public void releaseBbsDao() {
        if(bbsDao != null) {
            bbsDao = null;
        }
    }
}
