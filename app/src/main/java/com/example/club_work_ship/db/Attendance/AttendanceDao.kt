package com.example.club_work_ship.db.Attendance

import androidx.room.*
import java.sql.Date
import java.sql.Time

@Dao
interface AttendanceDao {
    
    // 插入打卡记录
    @Insert
    suspend fun insertAttendance(attendance: AttendanceRecord)
    
    // 批量插入打卡记录
    @Insert
    suspend fun insertAllAttendances(attendances: List<AttendanceRecord>)
    
    // 更新打卡记录
    @Update
    suspend fun updateAttendance(attendance: AttendanceRecord)
    
    // 删除打卡记录
    @Delete
    suspend fun deleteAttendance(attendance: AttendanceRecord)
    
    // 根据ID删除打卡记录
    @Query("DELETE FROM AttendanceRecord WHERE id = :id")
    suspend fun deleteAttendanceById(id: Int)
    
    // 获取所有打卡记录
    @Query("SELECT * FROM AttendanceRecord ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAllAttendances(): List<AttendanceRecord>
    
    // 根据ID获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE id = :id")
    suspend fun getAttendanceById(id: Int): AttendanceRecord?
    
    // 根据成员ID获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByMember(memberId: Int): List<AttendanceRecord>
    
    // 根据活动ID获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 项目ID = :projectID ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByActivity(projectID: Int): List<AttendanceRecord>
    
    // 根据日期获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 打卡日期 = :date ORDER BY 打卡时间 DESC")
    suspend fun getAttendancesByDate(date: Date): List<AttendanceRecord>
    
    // 根据成员ID和日期获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId AND 打卡日期 = :date ORDER BY 打卡时间 DESC")
    suspend fun getAttendancesByMemberAndDate(memberId: Int, date: Date): List<AttendanceRecord>
    
    // 根据成员ID和活动ID获取打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId AND 项目ID = :projectID ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByMemberAndActivity(memberId: Int, projectID: Int): List<AttendanceRecord>
    
    // 根据打卡类型获取记录
    @Query("SELECT * FROM AttendanceRecord WHERE 打卡类型 = :type ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByType(type: String): List<AttendanceRecord>
    
    // 根据打卡状态获取记录
    @Query("SELECT * FROM AttendanceRecord WHERE 打卡状态 = :status ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByStatus(status: String): List<AttendanceRecord>
    
    // 获取某个成员在某个活动中的签到记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId AND 项目ID = :projectID AND 打卡类型 = '签到' ORDER BY 打卡日期 DESC, 打卡时间 DESC LIMIT 1")
    suspend fun getLatestCheckInByMemberAndActivity(memberId: Int, projectID: Int): AttendanceRecord?
    
    // 获取某个成员在某个活动中的签退记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId AND 项目ID = :projectID AND 打卡类型 = '签退' ORDER BY 打卡日期 DESC, 打卡时间 DESC LIMIT 1")
    suspend fun getLatestCheckOutByMemberAndActivity(memberId: Int, projectID: Int): AttendanceRecord?
    
    // 统计某个活动的出勤情况
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 项目ID = :projectID AND 打卡类型 = '签到'")
    suspend fun getActivityCheckInCount(projectID: Int): Int
    
    // 统计某个成员的出勤次数
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 成员ID = :memberId AND 打卡类型 = '签到'")
    suspend fun getMemberCheckInCount(memberId: Int): Int
    
    // 获取某个成员在某个时间段内的打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 成员ID = :memberId AND 打卡日期 BETWEEN :startDate AND :endDate ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByMemberAndDateRange(memberId: Int, startDate: Date, endDate: Date): List<AttendanceRecord>
    
    // 获取某个活动在某个时间段内的打卡记录
    @Query("SELECT * FROM AttendanceRecord WHERE 项目ID = :projectID AND 打卡日期 BETWEEN :startDate AND :endDate ORDER BY 打卡日期 DESC, 打卡时间 DESC")
    suspend fun getAttendancesByActivityAndDateRange(projectID: Int, startDate: Date, endDate: Date): List<AttendanceRecord>
    
    // 获取最近的打卡记录
    @Query("SELECT * FROM AttendanceRecord ORDER BY 创建时间 DESC LIMIT :limit")
    suspend fun getRecentAttendances(limit: Int): List<AttendanceRecord>
    
    // 统计打卡记录总数
    @Query("SELECT COUNT(*) FROM AttendanceRecord")
    suspend fun getAttendanceCount(): Int
    
    // 统计某个成员的打卡记录数量
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 成员ID = :memberId")
    suspend fun getAttendanceCountByMember(memberId: Int): Int
    
    // 统计某个活动的打卡记录数量
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 项目ID = :projectID")
    suspend fun getAttendanceCountByActivity(projectID: Int): Int
    
    // 统计某个状态的打卡记录数量
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 打卡状态 = :status")
    suspend fun getAttendanceCountByStatus(status: String): Int
    
    // 统计某个类型的打卡记录数量
    @Query("SELECT COUNT(*) FROM AttendanceRecord WHERE 打卡类型 = :type")
    suspend fun getAttendanceCountByType(type: String): Int
} 