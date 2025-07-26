package com.example.club_work_ship.db.Task

import androidx.room.*
import java.sql.Date

@Dao
interface TaskDao {
    
    // 插入任务
    @Insert
    suspend fun insertTask(task: TaskEntity)
    
    // 批量插入任务
    @Insert
    suspend fun insertAllTasks(tasks: List<TaskEntity>)
    
    // 更新任务
    @Update
    suspend fun updateTask(task: TaskEntity)
    
    // 删除任务
    @Delete
    suspend fun deleteTask(task: TaskEntity)
    
    // 根据ID删除任务
    @Query("DELETE FROM Task WHERE id = :id")
    suspend fun deleteTaskById(id: Int)
    
    // 获取所有任务
    @Query("SELECT * FROM Task ORDER BY 创建日期 DESC")
    suspend fun getAllTasks(): List<TaskEntity>
    
    // 根据ID获取任务
    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?
    
    // 根据用户ID获取任务
    @Query("SELECT * FROM Task WHERE 用户id = :accountId ORDER BY 创建日期 DESC")
    suspend fun getTasksByUser(accountId: Int): List<TaskEntity>
    
    // 根据状态获取任务
    @Query("SELECT * FROM Task WHERE 状态 = :status ORDER BY 创建日期 DESC")
    suspend fun getTasksByStatus(status: String): List<TaskEntity>
    
    // 根据优先级获取任务
    @Query("SELECT * FROM Task WHERE 优先级 = :priority ORDER BY 创建日期 DESC")
    suspend fun getTasksByPriority(priority: Int): List<TaskEntity>
    
    // 根据创建日期范围获取任务
    @Query("SELECT * FROM Task WHERE 创建日期 BETWEEN :startDate AND :endDate ORDER BY 创建日期 DESC")
    suspend fun getTasksByDateRange(startDate: Date, endDate: Date): List<TaskEntity>
    
    // 根据开始时间范围获取任务
    @Query("SELECT * FROM Task WHERE 开始时间 BETWEEN :startTime AND :endTime ORDER BY 开始时间 ASC")
    suspend fun getTasksByStartTimeRange(startTime: Date, endTime: Date): List<TaskEntity>
    
    // 根据结束时间获取即将到期的任务
    @Query("SELECT * FROM Task WHERE 结束时间 <= :deadline AND 状态 != '已完成' ORDER BY 结束时间 ASC")
    suspend fun getTasksByDeadline(deadline: Date): List<TaskEntity>
    
    // 搜索任务（根据标题、描述、任务类型）
    @Query("SELECT * FROM Task WHERE 标题 LIKE '%' || :keyword || '%' OR 任务描述 LIKE '%' || :keyword || '%' ORDER BY 创建日期 DESC")
    suspend fun searchTasks(keyword: String): List<TaskEntity>
    
    // 获取高优先级任务
    @Query("SELECT * FROM Task WHERE 优先级 >= :minPriority ORDER BY 优先级 DESC, 创建日期 DESC")
    suspend fun getHighPriorityTasks(minPriority: Int): List<TaskEntity>
    
    // 获取待办任务
    @Query("SELECT * FROM Task WHERE 状态 IN ('待办', '进行中') ORDER BY 优先级 DESC, 创建日期 DESC")
    suspend fun getPendingTasks(): List<TaskEntity>
    
    // 获取已完成任务
    @Query("SELECT * FROM Task WHERE 状态 = '已完成' ORDER BY 创建日期 DESC")
    suspend fun getCompletedTasks(): List<TaskEntity>
    
    // 更新任务状态
    @Query("UPDATE Task SET 状态 = :status WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: Int, status: String)
    
    // 更新任务优先级
    @Query("UPDATE Task SET 优先级 = :priority WHERE id = :taskId")
    suspend fun updateTaskPriority(taskId: Int, priority: Int)
    
    // 更新任务结束时间
    @Query("UPDATE Task SET 结束时间 = :endTime WHERE id = :taskId")
    suspend fun updateTaskEndTime(taskId: Int, endTime: Date)
    
    // 统计任务数量
    @Query("SELECT COUNT(*) FROM Task")
    suspend fun getTaskCount(): Int
    
    // 统计某个状态的任务数量
    @Query("SELECT COUNT(*) FROM Task WHERE 状态 = :status")
    suspend fun getTaskCountByStatus(status: String): Int
    
    // 统计某个用户的任务数量
    @Query("SELECT COUNT(*) FROM Task WHERE 用户id = :accountId")
    suspend fun getTaskCountByUser(accountId: Int): Int
    
    // 获取最近创建的任务
    @Query("SELECT * FROM Task ORDER BY 创建日期 DESC LIMIT :limit")
    suspend fun getRecentTasks(limit: Int): List<TaskEntity>
    
    // 获取某个用户的高优先级任务
    @Query("SELECT * FROM Task WHERE 用户id = :accountId AND 优先级 >= :minPriority ORDER BY 优先级 DESC, 创建日期 DESC")
    suspend fun getUserHighPriorityTasks(accountId: Int, minPriority: Int): List<TaskEntity>
}