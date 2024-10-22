package mvvm.app.dragerhill.dbPrivateKey

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PrivateKeyMain(
    val target: String,
    val `data`: List<PrivateKey>
)

@Entity(tableName = "privatekey")
data class PrivateKey(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "relay") val relay: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "video") val video: String,
    @ColumnInfo(name = "eventId") val eventId: Int,
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "portid") val portid: Int
)