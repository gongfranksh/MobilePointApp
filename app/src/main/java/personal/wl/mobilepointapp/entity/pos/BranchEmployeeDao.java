package personal.wl.mobilepointapp.entity.pos;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "BRANCH_EMPLOYEE".
*/
public class BranchEmployeeDao extends AbstractDao<BranchEmployee, Long> {

    public static final String TABLENAME = "BRANCH_EMPLOYEE";

    /**
     * Properties of entity BranchEmployee.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Braid = new Property(1, String.class, "Braid", false, "BRAID");
        public final static Property Empid = new Property(2, String.class, "Empid", false, "EMPID");
        public final static Property EmpName = new Property(3, String.class, "EmpName", false, "EMP_NAME");
        public final static Property Password = new Property(4, String.class, "Password", false, "PASSWORD");
        public final static Property Discount = new Property(5, Double.class, "Discount", false, "DISCOUNT");
        public final static Property Status = new Property(6, String.class, "Status", false, "STATUS");
        public final static Property Timestamp = new Property(7, Long.class, "timestamp", false, "TIMESTAMP");
    }


    public BranchEmployeeDao(DaoConfig config) {
        super(config);
    }
    
    public BranchEmployeeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BRANCH_EMPLOYEE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BRAID\" TEXT," + // 1: Braid
                "\"EMPID\" TEXT," + // 2: Empid
                "\"EMP_NAME\" TEXT," + // 3: EmpName
                "\"PASSWORD\" TEXT," + // 4: Password
                "\"DISCOUNT\" REAL," + // 5: Discount
                "\"STATUS\" TEXT," + // 6: Status
                "\"TIMESTAMP\" INTEGER);"); // 7: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BRANCH_EMPLOYEE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BranchEmployee entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Braid = entity.getBraid();
        if (Braid != null) {
            stmt.bindString(2, Braid);
        }
 
        String Empid = entity.getEmpid();
        if (Empid != null) {
            stmt.bindString(3, Empid);
        }
 
        String EmpName = entity.getEmpName();
        if (EmpName != null) {
            stmt.bindString(4, EmpName);
        }
 
        String Password = entity.getPassword();
        if (Password != null) {
            stmt.bindString(5, Password);
        }
 
        Double Discount = entity.getDiscount();
        if (Discount != null) {
            stmt.bindDouble(6, Discount);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(7, Status);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(8, timestamp);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BranchEmployee entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Braid = entity.getBraid();
        if (Braid != null) {
            stmt.bindString(2, Braid);
        }
 
        String Empid = entity.getEmpid();
        if (Empid != null) {
            stmt.bindString(3, Empid);
        }
 
        String EmpName = entity.getEmpName();
        if (EmpName != null) {
            stmt.bindString(4, EmpName);
        }
 
        String Password = entity.getPassword();
        if (Password != null) {
            stmt.bindString(5, Password);
        }
 
        Double Discount = entity.getDiscount();
        if (Discount != null) {
            stmt.bindDouble(6, Discount);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(7, Status);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(8, timestamp);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BranchEmployee readEntity(Cursor cursor, int offset) {
        BranchEmployee entity = new BranchEmployee( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Braid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Empid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // EmpName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Password
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // Discount
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Status
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // timestamp
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BranchEmployee entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBraid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEmpid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmpName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassword(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDiscount(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setStatus(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTimestamp(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BranchEmployee entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BranchEmployee entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BranchEmployee entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
