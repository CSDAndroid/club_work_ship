package com.example.club_work_ship.db.department

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department")
data class Department(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "部门名称")
    val name: String,

    @ColumnInfo(name = "分类")
    val category: String,

    @ColumnInfo(name = "职能")
    val ability: String,

    @ColumnInfo(name = "状态")
    val status: String = "活跃", // 活跃/停用

    @ColumnInfo(name = "创建时间")
    val create_time: Long = System.currentTimeMillis()
)
