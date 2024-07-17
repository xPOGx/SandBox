package idp.android.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ItemDao {
    @Query("SELECT * FROM item WHERE name LIKE :name ORDER BY RANDOM()")
    fun getItemByName(name: String): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Transaction
    suspend fun insertNewDeleteOld(newItem: Item, oldItem: Item?) {
        insertItem(newItem)
        oldItem?.let {
            deleteItem(oldItem)
        }
    }
}