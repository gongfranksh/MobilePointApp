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
 * DAO for table "FRONT_CHECK".
*/
public class FrontCheckDao extends AbstractDao<FrontCheck, Long> {

    public static final String TABLENAME = "FRONT_CHECK";

    /**
     * Properties of entity FrontCheck.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Braid = new Property(1, String.class, "Braid", false, "BRAID");
        public final static Property InputDate = new Property(2, java.util.Date.class, "InputDate", false, "INPUT_DATE");
        public final static Property PosNo = new Property(3, String.class, "PosNo", false, "POS_NO");
        public final static Property OperatorId = new Property(4, String.class, "OperatorId", false, "OPERATOR_ID");
        public final static Property ProId = new Property(5, String.class, "ProId", false, "PRO_ID");
        public final static Property BarCode = new Property(6, String.class, "BarCode", false, "BAR_CODE");
        public final static Property ClassId = new Property(7, String.class, "ClassId", false, "CLASS_ID");
        public final static Property CheckQty1 = new Property(8, Double.class, "CheckQty1", false, "CHECK_QTY1");
        public final static Property CheckQty2 = new Property(9, Double.class, "CheckQty2", false, "CHECK_QTY2");
        public final static Property ReceiptNo = new Property(10, String.class, "ReceiptNo", false, "RECEIPT_NO");
        public final static Property ShieldNo = new Property(11, String.class, "ShieldNo", false, "SHIELD_NO");
        public final static Property CheckInnerId = new Property(12, String.class, "CheckInnerId", false, "CHECK_INNER_ID");
        public final static Property SourceId = new Property(13, Long.class, "SourceId", false, "SOURCE_ID");
    }


    public FrontCheckDao(DaoConfig config) {
        super(config);
    }
    
    public FrontCheckDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FRONT_CHECK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BRAID\" TEXT," + // 1: Braid
                "\"INPUT_DATE\" INTEGER," + // 2: InputDate
                "\"POS_NO\" TEXT," + // 3: PosNo
                "\"OPERATOR_ID\" TEXT," + // 4: OperatorId
                "\"PRO_ID\" TEXT," + // 5: ProId
                "\"BAR_CODE\" TEXT," + // 6: BarCode
                "\"CLASS_ID\" TEXT," + // 7: ClassId
                "\"CHECK_QTY1\" REAL," + // 8: CheckQty1
                "\"CHECK_QTY2\" REAL," + // 9: CheckQty2
                "\"RECEIPT_NO\" TEXT," + // 10: ReceiptNo
                "\"SHIELD_NO\" TEXT," + // 11: ShieldNo
                "\"CHECK_INNER_ID\" TEXT," + // 12: CheckInnerId
                "\"SOURCE_ID\" INTEGER);"); // 13: SourceId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FRONT_CHECK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FrontCheck entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Braid = entity.getBraid();
        if (Braid != null) {
            stmt.bindString(2, Braid);
        }
 
        java.util.Date InputDate = entity.getInputDate();
        if (InputDate != null) {
            stmt.bindLong(3, InputDate.getTime());
        }
 
        String PosNo = entity.getPosNo();
        if (PosNo != null) {
            stmt.bindString(4, PosNo);
        }
 
        String OperatorId = entity.getOperatorId();
        if (OperatorId != null) {
            stmt.bindString(5, OperatorId);
        }
 
        String ProId = entity.getProId();
        if (ProId != null) {
            stmt.bindString(6, ProId);
        }
 
        String BarCode = entity.getBarCode();
        if (BarCode != null) {
            stmt.bindString(7, BarCode);
        }
 
        String ClassId = entity.getClassId();
        if (ClassId != null) {
            stmt.bindString(8, ClassId);
        }
 
        Double CheckQty1 = entity.getCheckQty1();
        if (CheckQty1 != null) {
            stmt.bindDouble(9, CheckQty1);
        }
 
        Double CheckQty2 = entity.getCheckQty2();
        if (CheckQty2 != null) {
            stmt.bindDouble(10, CheckQty2);
        }
 
        String ReceiptNo = entity.getReceiptNo();
        if (ReceiptNo != null) {
            stmt.bindString(11, ReceiptNo);
        }
 
        String ShieldNo = entity.getShieldNo();
        if (ShieldNo != null) {
            stmt.bindString(12, ShieldNo);
        }
 
        String CheckInnerId = entity.getCheckInnerId();
        if (CheckInnerId != null) {
            stmt.bindString(13, CheckInnerId);
        }
 
        Long SourceId = entity.getSourceId();
        if (SourceId != null) {
            stmt.bindLong(14, SourceId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FrontCheck entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Braid = entity.getBraid();
        if (Braid != null) {
            stmt.bindString(2, Braid);
        }
 
        java.util.Date InputDate = entity.getInputDate();
        if (InputDate != null) {
            stmt.bindLong(3, InputDate.getTime());
        }
 
        String PosNo = entity.getPosNo();
        if (PosNo != null) {
            stmt.bindString(4, PosNo);
        }
 
        String OperatorId = entity.getOperatorId();
        if (OperatorId != null) {
            stmt.bindString(5, OperatorId);
        }
 
        String ProId = entity.getProId();
        if (ProId != null) {
            stmt.bindString(6, ProId);
        }
 
        String BarCode = entity.getBarCode();
        if (BarCode != null) {
            stmt.bindString(7, BarCode);
        }
 
        String ClassId = entity.getClassId();
        if (ClassId != null) {
            stmt.bindString(8, ClassId);
        }
 
        Double CheckQty1 = entity.getCheckQty1();
        if (CheckQty1 != null) {
            stmt.bindDouble(9, CheckQty1);
        }
 
        Double CheckQty2 = entity.getCheckQty2();
        if (CheckQty2 != null) {
            stmt.bindDouble(10, CheckQty2);
        }
 
        String ReceiptNo = entity.getReceiptNo();
        if (ReceiptNo != null) {
            stmt.bindString(11, ReceiptNo);
        }
 
        String ShieldNo = entity.getShieldNo();
        if (ShieldNo != null) {
            stmt.bindString(12, ShieldNo);
        }
 
        String CheckInnerId = entity.getCheckInnerId();
        if (CheckInnerId != null) {
            stmt.bindString(13, CheckInnerId);
        }
 
        Long SourceId = entity.getSourceId();
        if (SourceId != null) {
            stmt.bindLong(14, SourceId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FrontCheck readEntity(Cursor cursor, int offset) {
        FrontCheck entity = new FrontCheck( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Braid
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // InputDate
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // PosNo
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // OperatorId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ProId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // BarCode
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ClassId
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // CheckQty1
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9), // CheckQty2
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // ReceiptNo
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // ShieldNo
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // CheckInnerId
            cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13) // SourceId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FrontCheck entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBraid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setInputDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setPosNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setOperatorId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBarCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setClassId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCheckQty1(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setCheckQty2(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
        entity.setReceiptNo(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setShieldNo(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCheckInnerId(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSourceId(cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FrontCheck entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FrontCheck entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FrontCheck entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}