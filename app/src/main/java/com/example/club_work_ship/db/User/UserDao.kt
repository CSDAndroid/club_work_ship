package com.example.club_work_ship.db.User

import androidx.room.*

@Dao
interface UserDao {
    
    // 插入用户
    @Insert
    suspend fun insertUser(user: UserEntity)
    
    // 批量插入用户
    @Insert
    suspend fun insertAllUsers(users: List<UserEntity>)
    
    // 更新用户
    @Update
    suspend fun updateUser(user: UserEntity)
    
    // 删除用户
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    // 根据ID删除用户
    @Query("DELETE FROM User WHERE id = :id")
    suspend fun deleteUserById(id: Int)
    
    // 获取所有用户
    @Query("SELECT * FROM User ORDER BY id")
    suspend fun getAllUsers(): List<UserEntity>
    
    // 根据ID获取用户
    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity?
    
    // 根据账号获取用户
    @Query("SELECT * FROM User WHERE 账号 = :account")
    suspend fun getUserByAccount(account: String): UserEntity?
    
    // 根据姓名获取用户
    @Query("SELECT * FROM User WHERE 姓名 = :name")
    suspend fun getUserByName(name: String): UserEntity?
    
    // 根据手机号获取用户
    @Query("SELECT * FROM User WHERE 手机号 = :phone")
    suspend fun getUserByPhone(phone: String): UserEntity?
    
    // 根据邮箱获取用户
    @Query("SELECT * FROM User WHERE 邮箱 = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
    
    // 根据部门ID获取用户
    @Query("SELECT * FROM User WHERE 所属部门 = :departmentId ORDER BY id")
    suspend fun getUsersByDepartment(departmentId: Int): List<UserEntity>
    
    // 根据职位获取用户
    @Query("SELECT * FROM User WHERE 职位 = :post ORDER BY id")
    suspend fun getUsersByPost(post: String): List<UserEntity>
    
    // 根据状态获取用户
    @Query("SELECT * FROM User WHERE 状态 = :status ORDER BY id")
    suspend fun getUsersByStatus(status: String): List<UserEntity>
    
    // 用户登录验证
    @Query("SELECT * FROM User WHERE 账号 = :account AND 密码 = :password")
    suspend fun loginUser(account: String, password: String): UserEntity?
    
    // 检查账号是否存在
    @Query("SELECT COUNT(*) FROM User WHERE 账号 = :account")
    suspend fun isAccountExists(account: String): Int
    
    // 检查手机号是否存在
    @Query("SELECT COUNT(*) FROM User WHERE 手机号 = :phone")
    suspend fun isPhoneExists(phone: String): Int
    
    // 检查邮箱是否存在
    @Query("SELECT COUNT(*) FROM User WHERE 邮箱 = :email")
    suspend fun isEmailExists(email: String): Int
    
    // 更新用户状态
    @Query("UPDATE User SET 状态 = :status WHERE id = :userId")
    suspend fun updateUserStatus(userId: Int, status: String)
    
    // 更新用户密码
    @Query("UPDATE User SET 密码 = :password WHERE id = :userId")
    suspend fun updateUserPassword(userId: Int, password: String)
    
    // 更新用户头像
    @Query("UPDATE User SET 头像 = :avatar WHERE id = :userId")
    suspend fun updateUserAvatar(userId: Int, avatar: String)
    
    // 搜索用户（根据账号、姓名、手机号、邮箱、职位）
    @Query("SELECT * FROM User WHERE 账号 LIKE '%' || :keyword || '%' OR 姓名 LIKE '%' || :keyword || '%' OR 手机号 LIKE '%' || :keyword || '%' OR 邮箱 LIKE '%' || :keyword || '%' OR 职位 LIKE '%' || :keyword || '%'")
    suspend fun searchUsers(keyword: String): List<UserEntity>
    
    // 获取活跃用户
    @Query("SELECT * FROM User WHERE 状态 = '活跃' ORDER BY 创建时间 DESC")
    suspend fun getActiveUsers(): List<UserEntity>
    
    // 统计用户数量
    @Query("SELECT COUNT(*) FROM User")
    suspend fun getUserCount(): Int
    
    // 统计某个状态的用户数量
    @Query("SELECT COUNT(*) FROM User WHERE 状态 = :status")
    suspend fun getUserCountByStatus(status: String): Int
    
    // 统计某个部门的用户数量
    @Query("SELECT COUNT(*) FROM User WHERE 所属部门 = :departmentId")
    suspend fun getUserCountByDepartment(departmentId: Int): Int
}