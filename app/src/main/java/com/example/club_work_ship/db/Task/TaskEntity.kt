package com.example.club_work_ship.db.Task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.User.UserEntity
import java.sql.Date

@Entity(tableName = "Task",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    @ColumnInfo(name = "用户id")
    val account_id: Int,

    @ColumnInfo(name = "标题")
    val title: String,

    @ColumnInfo(name = "任务描述")
    val description: String,

    @ColumnInfo(name = "创建日期")
    val create_time: Date,

    @ColumnInfo(name = "开始时间")
    val start_time: Date,

    @ColumnInfo(name = "结束时间")
    val end_time: Date,

    @ColumnInfo(name = "优先级")
    val priority: Int, // 1-低, 2-中, 3-高, 4-紧急

    @ColumnInfo(name = "状态")
    val status: String // 待办/进行中/已完成/已取消
)
