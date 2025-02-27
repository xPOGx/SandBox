package idp.android.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Item::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class ItemDb : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}