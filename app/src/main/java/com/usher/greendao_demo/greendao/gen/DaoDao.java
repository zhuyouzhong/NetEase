package com.usher.greendao_demo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.netease.bean.Dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAO".
*/
public class DaoDao extends AbstractDao<Dao, Void> {

    public static final String TABLENAME = "DAO";

    /**
     * Properties of entity Dao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Json = new Property(0, String.class, "json", false, "JSON");
        public final static Property Type = new Property(1, String.class, "type", false, "TYPE");
    }


    public DaoDao(DaoConfig config) {
        super(config);
    }
    
    public DaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAO\" (" + //
                "\"JSON\" TEXT," + // 0: json
                "\"TYPE\" TEXT);"); // 1: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Dao entity) {
        stmt.clearBindings();
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(1, json);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Dao entity) {
        stmt.clearBindings();
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(1, json);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Dao readEntity(Cursor cursor, int offset) {
        Dao entity = new Dao( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // json
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Dao entity, int offset) {
        entity.setJson(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setType(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Dao entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Dao entity) {
        return null;
    }

    @Override
    public boolean hasKey(Dao entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
