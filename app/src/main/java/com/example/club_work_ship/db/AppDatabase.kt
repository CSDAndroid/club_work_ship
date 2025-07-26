package com.example.club_work_ship.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.club_work_ship.db.Attendance.AttendanceDao
import com.example.club_work_ship.db.Attendance.AttendanceRecord
import com.example.club_work_ship.db.Project.ProjectDao
import com.example.club_work_ship.db.Project.ProjectEntity
import com.example.club_work_ship.db.ProjectTeam.ProjectTeamDao
import com.example.club_work_ship.db.ProjectTeam.ProjectTeamEntity
import com.example.club_work_ship.db.Task.TaskDao
import com.example.club_work_ship.db.Task.TaskEntity
import com.example.club_work_ship.db.Team.TeamDao
import com.example.club_work_ship.db.Team.TeamEntity
import com.example.club_work_ship.db.User.UserDao
import com.example.club_work_ship.db.User.UserEntity
import com.example.club_work_ship.db.department.Department
import com.example.club_work_ship.db.department.DepartmentDao

@Database(
    entities = [
        UserEntity::class,
        Department::class,
        ProjectEntity::class,
        TaskEntity::class,
        TeamEntity::class,
        ProjectTeamEntity::class,
        AttendanceRecord::class
    ], 
    version = 1, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    
    // DAO接口
    abstract fun getUserDao(): UserDao
    abstract fun getDepartmentDao(): DepartmentDao
    abstract fun getProjectDao(): ProjectDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getTeamDao(): TeamDao
    abstract fun getProjectTeamDao(): ProjectTeamDao
    abstract fun getAttendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "club_work_ship_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}