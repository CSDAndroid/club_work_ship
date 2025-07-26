package com.example.club_work_ship.db.Attendance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.Project.ProjectEntity
import com.example.club_work_ship.db.Task.TaskEntity
import com.example.club_work_ship.db.User.UserEntity
import java.sql.Date
import java.sql.Time

@Entity(tableName = "AttendanceRecord",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["member_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class AttendanceRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "成员ID")
    val member_id: Int,

    @ColumnInfo(name = "项目ID")
    val project_id: Int?,

    @ColumnInfo(name = "任务ID")
    val task_id: Int?,

    @ColumnInfo(name = "打卡日期")
    val date: Date,

    @ColumnInfo(name = "打卡时间")
    val time: Time,

    @ColumnInfo(name = "打卡类型")
    val type: String,

    @ColumnInfo(name = "打卡状态")
    val status: String,

    @ColumnInfo(name = "备注")
    val remark: String?,

    @ColumnInfo(name = "地理位置")
    val location: String?,

    @ColumnInfo(name = "创建时间")
    val create_time: Long = System.currentTimeMillis()
)