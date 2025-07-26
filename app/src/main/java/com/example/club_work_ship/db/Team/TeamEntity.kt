package com.example.club_work_ship.db.Team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.User.UserEntity
import com.example.club_work_ship.db.department.Department

@Entity(tableName = "Team",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["leader_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["department_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "名称")
    val title: String,

    @ColumnInfo(name = "描述")
    val description: String?,

    @ColumnInfo(name = "所属部门")
    val department_id: Int,

    @ColumnInfo(name = "组长")
    val leader_id: Int,

    @ColumnInfo(name = "组员")
    val member_ids: List<Int>,

    @ColumnInfo(name = "Q群")
    val qq_connection: String?,

    @ColumnInfo(name = "资料连接")
    val information_connection: String?,

    @ColumnInfo(name = "状态")
    val status: String = "活跃", // 活跃/暂停/解散

    @ColumnInfo(name = "创建时间")
    val create_time: Long = System.currentTimeMillis()
)
