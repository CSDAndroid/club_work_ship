package com.example.club_work_ship.db.ProjectTeam

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.User.UserEntity

@Entity(tableName = "ProjectTeam",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_leader_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class ProjectTeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "名称")
    val title: String,

    @ColumnInfo(name = "描述")
    val description: String?,

    @ColumnInfo(name = "组长")
    val project_leader_id: Int,

    @ColumnInfo(name = "成员")
    val project_member_ids: List<Int>,

    @ColumnInfo(name = "状态")
    val status: String, // 活跃/暂停/已完成/已解散

    @ColumnInfo(name = "创建时间")
    val create_time: Long = System.currentTimeMillis()
)