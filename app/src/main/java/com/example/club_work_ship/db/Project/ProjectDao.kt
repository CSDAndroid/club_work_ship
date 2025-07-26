package com.example.club_work_ship.db.Project

import androidx.room.*
import java.sql.Date

@Dao
interface ProjectDao {
    
    // 插入项目
    @Insert
    suspend fun insertProject(project: ProjectEntity)
    
    // 批量插入项目
    @Insert
    suspend fun insertAllProjects(projects: List<ProjectEntity>)
    
    // 更新项目
    @Update
    suspend fun updateProject(project: ProjectEntity)
    
    // 删除项目
    @Delete
    suspend fun deleteProject(project: ProjectEntity)
    
    // 根据ID删除项目
    @Query("DELETE FROM Project WHERE id = :id")
    suspend fun deleteProjectById(id: Int)
    
    // 获取所有项目
    @Query("SELECT * FROM Project ORDER BY 创建日期 DESC")
    suspend fun getAllProjects(): List<ProjectEntity>
    
    // 根据ID获取项目
    @Query("SELECT * FROM Project WHERE id = :id")
    suspend fun getProjectById(id: Int): ProjectEntity?
    
    // 根据项目组ID获取项目
    @Query("SELECT * FROM Project WHERE 项目组id = :projectTeamId ORDER BY 创建日期 DESC")
    suspend fun getProjectsByTeam(projectTeamId: Int): List<ProjectEntity>
    
    // 根据负责人ID获取项目
    @Query("SELECT * FROM Project WHERE 负责人id = :accountId ORDER BY 创建日期 DESC")
    suspend fun getProjectsByLeader(accountId: Int): List<ProjectEntity>
    
    // 根据状态获取项目
    @Query("SELECT * FROM Project WHERE 状态 = :status ORDER BY 创建日期 DESC")
    suspend fun getProjectsByStatus(status: String): List<ProjectEntity>
    
    // 根据优先级获取项目
    @Query("SELECT * FROM Project WHERE 优先级 = :priority ORDER BY 创建日期 DESC")
    suspend fun getProjectsByPriority(priority: Int): List<ProjectEntity>
    
    // 根据分类获取项目
    @Query("SELECT * FROM Project WHERE 分类 = :category ORDER BY 创建日期 DESC")
    suspend fun getProjectsByCategory(category: Int): List<ProjectEntity>
    
    // 根据位置获取项目
    @Query("SELECT * FROM Project WHERE 位置 = :location ORDER BY 创建日期 DESC")
    suspend fun getProjectsByLocation(location: String): List<ProjectEntity>
    
    // 根据创建日期范围获取项目
    @Query("SELECT * FROM Project WHERE 创建日期 BETWEEN :startDate AND :endDate ORDER BY 创建日期 DESC")
    suspend fun getProjectsByDateRange(startDate: Date, endDate: Date): List<ProjectEntity>
    
    // 根据截止日期获取即将到期的项目
    @Query("SELECT * FROM Project WHERE 截止日期 <= :deadline AND 状态 != '已完成' ORDER BY 截止日期 ASC")
    suspend fun getProjectsByDeadline(deadline: Date): List<ProjectEntity>
    
    // 搜索项目（根据标题、描述、位置）
    @Query("SELECT * FROM Project WHERE 标题 LIKE '%' || :keyword || '%' OR 描述 LIKE '%' || :keyword || '%' OR 位置 LIKE '%' || :keyword || '%' ORDER BY 创建日期 DESC")
    suspend fun searchProjects(keyword: String): List<ProjectEntity>
    
    // 获取高优先级项目
    @Query("SELECT * FROM Project WHERE 优先级 >= :minPriority ORDER BY 优先级 DESC, 创建日期 DESC")
    suspend fun getHighPriorityProjects(minPriority: Int): List<ProjectEntity>
    
    // 获取进行中的项目
    @Query("SELECT * FROM Project WHERE 状态 = '进行中' ORDER BY 创建日期 DESC")
    suspend fun getOngoingProjects(): List<ProjectEntity>
    
    // 获取已完成的项目
    @Query("SELECT * FROM Project WHERE 状态 = '已完成' ORDER BY 创建日期 DESC")
    suspend fun getCompletedProjects(): List<ProjectEntity>
    
    // 更新项目状态
    @Query("UPDATE Project SET 状态 = :status WHERE id = :projectId")
    suspend fun updateProjectStatus(projectId: Int, status: String)
    
    // 更新项目优先级
    @Query("UPDATE Project SET 优先级 = :priority WHERE id = :projectId")
    suspend fun updateProjectPriority(projectId: Int, priority: Int)
    
    // 更新项目位置
    @Query("UPDATE Project SET 位置 = :location WHERE id = :projectId")
    suspend fun updateProjectLocation(projectId: Int, location: String)
    
    // 统计项目数量
    @Query("SELECT COUNT(*) FROM Project")
    suspend fun getProjectCount(): Int
    
    // 统计某个状态的项目数量
    @Query("SELECT COUNT(*) FROM Project WHERE 状态 = :status")
    suspend fun getProjectCountByStatus(status: String): Int
    
    // 统计某个分类的项目数量
    @Query("SELECT COUNT(*) FROM Project WHERE 分类 = :category")
    suspend fun getProjectCountByCategory(category: Int): Int
    
    // 获取最近创建的项目
    @Query("SELECT * FROM Project ORDER BY 创建日期 DESC LIMIT :limit")
    suspend fun getRecentProjects(limit: Int): List<ProjectEntity>
}