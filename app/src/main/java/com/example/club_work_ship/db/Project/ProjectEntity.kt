package com.example.club_work_ship.db.Project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.ProjectTeam.ProjectTeamEntity
import com.example.club_work_ship.db.User.UserEntity
import java.sql.Date

@Entity(tableName = "Project",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProjectTeamEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_team_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "项目组id")
    val project_team_id: Int,

    @ColumnInfo(name = "负责人id")
    val account_id: Int,

    @ColumnInfo(name = "标题")
    val title: String,

    @ColumnInfo(name = "描述")
    val description: String?,

    @ColumnInfo(name = "位置")
    val location: String?,

    @ColumnInfo(name = "创建日期")
    val create_time: Date,

    @ColumnInfo(name = "开始日期")
    val start_date: Date,

    @ColumnInfo(name = "截止日期")
    val end_date: Date,

    @ColumnInfo(name = "状态")
    val status: String, // 未开始/进行中/已完成/已暂停/已取消

    @ColumnInfo(name = "优先级")
    val priority: Int, // 1-低, 2-中, 3-高, 4-紧急

    @ColumnInfo(name = "分类")
    val category: Int // 1-例会, 2-培训, 3-活动, 4-会议, 5-其他
)
