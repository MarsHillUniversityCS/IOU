package cs421.cs.mhu.edu.iou.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by marty on 12/1/17.
 */

@Database(entities={Debt.class, Transaction.class, TransactionDebt.class}, version = 1)
public abstract class IOUDb extends RoomDatabase {
    private static IOUDb INSTANCE;

    public static IOUDb getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            IOUDb.class,
                            "iou_db")
                            .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public abstract IOUDao iouDao();
}
