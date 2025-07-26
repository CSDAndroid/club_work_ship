package com.example.club_work_ship.db.Team

import androidx.room.*

@Dao
interface TeamDao {
    
    // 插入团队
    @Insert
    suspend fun insertTeam(team: TeamEntity)
    
    // 批量插入团队
    @Insert
    suspend fun insertAllTeams(teams: List<TeamEntity>)
    
    // 更新团队
    @Update
    suspend fun updateTeam(team: TeamEntity)
    
    // 删除团队
    @Delete
    suspend fun deleteTeam(team: TeamEntity)
    
    // 根据ID删除团队
    @Query("DELETE FROM Team WHERE id = :id")
    suspend fun deleteTeamById(id: Int)
    
    // 获取所有团队
    @Query("SELECT * FROM Team ORDER BY id")
    suspend fun getAllTeams(): List<TeamEntity>
    
    // 根据ID获取团队
    @Query("SELECT * FROM Team WHERE id = :id")
    suspend fun getTeamById(id: Int): TeamEntity?
    
    // 根据名称获取团队
    @Query("SELECT * FROM Team WHERE 名称 = :title")
    suspend fun getTeamByName(title: String): TeamEntity?
    
    // 根据部门ID获取团队
    @Query("SELECT * FROM Team WHERE 所属部门 = :departmentId ORDER BY id")
    suspend fun getTeamsByDepartment(departmentId: Int): List<TeamEntity>
    
    // 根据组长ID获取团队
    @Query("SELECT * FROM Team WHERE 组长 = :leaderId ORDER BY id")
    suspend fun getTeamsByLeader(leaderId: Int): List<TeamEntity>
    
    // 根据状态获取团队
    @Query("SELECT * FROM Team WHERE 状态 = :status ORDER BY id")
    suspend fun getTeamsByStatus(status: String): List<TeamEntity>
    
    // 搜索团队（根据名称、描述）
    @Query("SELECT * FROM Team WHERE 名称 LIKE '%' || :keyword || '%' OR 描述 LIKE '%' || :keyword || '%' ORDER BY id")
    suspend fun searchTeams(keyword: String): List<TeamEntity>
    
    // 更新团队名称
    @Query("UPDATE Team SET 名称 = :title WHERE id = :teamId")
    suspend fun updateTeamName(teamId: Int, title: String)
    
    // 更新团队描述
    @Query("UPDATE Team SET 描述 = :description WHERE id = :teamId")
    suspend fun updateTeamDescription(teamId: Int, description: String)
    
    // 更新团队组长
    @Query("UPDATE Team SET 组长 = :leaderId WHERE id = :teamId")
    suspend fun updateTeamLeader(teamId: Int, leaderId: Int)
    
    // 更新团队成员
    @Query("UPDATE Team SET 组员 = :memberIds WHERE id = :teamId")
    suspend fun updateTeamMembers(teamId: Int, memberIds: List<Int>)
    
    // 更新QQ群
    @Query("UPDATE Team SET Q群 = :qqConnection WHERE id = :teamId")
    suspend fun updateTeamQQConnection(teamId: Int, qqConnection: String?)
    
    // 更新资料连接
    @Query("UPDATE Team SET 资料连接 = :informationConnection WHERE id = :teamId")
    suspend fun updateTeamInformationConnection(teamId: Int, informationConnection: String?)
    
    // 更新团队状态
    @Query("UPDATE Team SET 状态 = :status WHERE id = :teamId")
    suspend fun updateTeamStatus(teamId: Int, status: String)
    
    // 统计团队数量
    @Query("SELECT COUNT(*) FROM Team")
    suspend fun getTeamCount(): Int
    
    // 统计某个部门的团队数量
    @Query("SELECT COUNT(*) FROM Team WHERE 所属部门 = :departmentId")
    suspend fun getTeamCountByDepartment(departmentId: Int): Int
    
    // 统计某个状态的团队数量
    @Query("SELECT COUNT(*) FROM Team WHERE 状态 = :status")
    suspend fun getTeamCountByStatus(status: String): Int
    
    // 检查用户是否为某个团队的组长
    @Query("SELECT COUNT(*) FROM Team WHERE 组长 = :userId")
    suspend fun isUserTeamLeader(userId: Int): Int
    
    // 获取某个部门的所有团队及其组长信息
    @Query("SELECT * FROM Team WHERE 所属部门 = :departmentId ORDER BY id")
    suspend fun getTeamsWithLeadersByDepartment(departmentId: Int): List<TeamEntity>
    
    // 获取活跃团队
    @Query("SELECT * FROM Team WHERE 状态 = '活跃' ORDER BY 创建时间 DESC")
    suspend fun getActiveTeams(): List<TeamEntity>
    
    // 获取最近创建的团队
    @Query("SELECT * FROM Team ORDER BY 创建时间 DESC LIMIT :limit")
    suspend fun getRecentTeams(limit: Int): List<TeamEntity>
}