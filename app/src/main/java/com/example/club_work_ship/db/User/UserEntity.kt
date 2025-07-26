package com.example.club_work_ship.db.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.club_work_ship.db.department.Department

@Entity(tableName = "User",
    foreignKeys = [
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["department_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "账号")
    val account : String,

    @ColumnInfo(name = "密码")
    val password: String,

    @ColumnInfo(name = "姓名")
    val name: String,

    @ColumnInfo(name = "手机号")
    val phone: String?,

    @ColumnInfo(name = "邮箱")
    val email: String?,

    @ColumnInfo(name = "描述")
    val description: String?,

    @ColumnInfo(name = "头像")
    val avatar: String?,

    @ColumnInfo(name = "职位")
    val post: String,

    @ColumnInfo(name = "所属部门")
    val department_id: Int,

    @ColumnInfo(name = "状态")
    val status: String = "活跃", // 活跃/离职/暂停

    @ColumnInfo(name = "创建时间")
    val create_time: Long = System.currentTimeMillis()
)