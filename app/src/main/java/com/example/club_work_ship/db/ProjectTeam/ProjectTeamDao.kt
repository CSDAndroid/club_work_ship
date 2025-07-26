package com.example.club_work_ship.db.ProjectTeam

import androidx.room.*

@Dao
interface ProjectTeamDao {
    
    // 插入项目团队
    @Insert
    suspend fun insertProjectTeam(projectTeam: ProjectTeamEntity)
    
    // 批量插入项目团队
    @Insert
    suspend fun insertAllProjectTeams(projectTeams: List<ProjectTeamEntity>)
    
    // 更新项目团队
    @Update
    suspend fun updateProjectTeam(projectTeam: ProjectTeamEntity)
    
    // 删除项目团队
    @Delete
    suspend fun deleteProjectTeam(projectTeam: ProjectTeamEntity)
    
    // 根据ID删除项目团队
    @Query("DELETE FROM ProjectTeam WHERE id = :id")
    suspend fun deleteProjectTeamById(id: Int)
    
    // 获取所有项目团队
    @Query("SELECT * FROM ProjectTeam ORDER BY id")
    suspend fun getAllProjectTeams(): List<ProjectTeamEntity>
    
    // 根据ID获取项目团队
    @Query("SELECT * FROM ProjectTeam WHERE id = :id")
    suspend fun getProjectTeamById(id: Int): ProjectTeamEntity?
    
    // 根据名称获取项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 名称 = :title")
    suspend fun getProjectTeamByName(title: String): ProjectTeamEntity?
    
    // 根据组长ID获取项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 组长 = :projectLeaderId ORDER BY id")
    suspend fun getProjectTeamsByLeader(projectLeaderId: Int): List<ProjectTeamEntity>
    
    // 根据状态获取项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 状态 = :status ORDER BY id")
    suspend fun getProjectTeamsByStatus(status: String): List<ProjectTeamEntity>
    
    // 搜索项目团队（根据名称、描述）
    @Query("SELECT * FROM ProjectTeam WHERE 名称 LIKE '%' || :keyword || '%' OR 描述 LIKE '%' || :keyword || '%' ORDER BY id")
    suspend fun searchProjectTeams(keyword: String): List<ProjectTeamEntity>
    
    // 更新项目团队名称
    @Query("UPDATE ProjectTeam SET 名称 = :title WHERE id = :projectTeamId")
    suspend fun updateProjectTeamName(projectTeamId: Int, title: String)
    
    // 更新项目团队描述
    @Query("UPDATE ProjectTeam SET 描述 = :description WHERE id = :projectTeamId")
    suspend fun updateProjectTeamDescription(projectTeamId: Int, description: String)
    
    // 更新项目团队组长
    @Query("UPDATE ProjectTeam SET 组长 = :projectLeaderId WHERE id = :projectTeamId")
    suspend fun updateProjectTeamLeader(projectTeamId: Int, projectLeaderId: Int)
    
    // 更新项目团队成员
    @Query("UPDATE ProjectTeam SET 成员 = :projectMemberIds WHERE id = :projectTeamId")
    suspend fun updateProjectTeamMembers(projectTeamId: Int, projectMemberIds: List<Int>)
    
    // 更新项目团队状态
    @Query("UPDATE ProjectTeam SET 状态 = :status WHERE id = :projectTeamId")
    suspend fun updateProjectTeamStatus(projectTeamId: Int, status: String)
    
    // 统计项目团队数量
    @Query("SELECT COUNT(*) FROM ProjectTeam")
    suspend fun getProjectTeamCount(): Int
    
    // 统计某个状态的项目团队数量
    @Query("SELECT COUNT(*) FROM ProjectTeam WHERE 状态 = :status")
    suspend fun getProjectTeamCountByStatus(status: String): Int
    
    // 检查用户是否为某个项目团队的组长
    @Query("SELECT COUNT(*) FROM ProjectTeam WHERE 组长 = :userId")
    suspend fun isUserProjectTeamLeader(userId: Int): Int
    
    // 检查用户是否为某个项目团队的成员
    @Query("SELECT COUNT(*) FROM ProjectTeam WHERE :userId IN 成员")
    suspend fun isUserProjectTeamMember(userId: Int): Int
    
    // 获取用户所在的所有项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 组长 = :userId OR :userId IN 成员 ORDER BY id")
    suspend fun getProjectTeamsByUser(userId: Int): List<ProjectTeamEntity>
    
    // 获取活跃的项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 状态 = '活跃' ORDER BY id")
    suspend fun getActiveProjectTeams(): List<ProjectTeamEntity>
    
    // 获取已完成的项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 状态 = '已完成' ORDER BY id")
    suspend fun getCompletedProjectTeams(): List<ProjectTeamEntity>
    
    // 获取暂停的项目团队
    @Query("SELECT * FROM ProjectTeam WHERE 状态 = '暂停' ORDER BY id")
    suspend fun getPausedProjectTeams(): List<ProjectTeamEntity>
    
    // 获取最近创建的项目团队
    @Query("SELECT * FROM ProjectTeam ORDER BY 创建时间 DESC LIMIT :limit")
    suspend fun getRecentProjectTeams(limit: Int): List<ProjectTeamEntity>
}