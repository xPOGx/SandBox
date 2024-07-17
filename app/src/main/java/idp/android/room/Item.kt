package idp.android.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "Default",
    @ColumnInfo(name = "price")
    val price: Double = 0.0,
)
