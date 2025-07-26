package com.example.club_work_ship.db.department

import androidx.room.*

@Dao
interface DepartmentDao {
    
    // 插入部门
    @Insert
    suspend fun insertDepartment(department: Department)
    
    // 批量插入部门
    @Insert
    suspend fun insertAllDepartments(departments: List<Department>)
    
    // 更新部门
    @Update
    suspend fun updateDepartment(department: Department)
    
    // 删除部门
    @Delete
    suspend fun deleteDepartment(department: Department)
    
    // 根据ID删除部门
    @Query("DELETE FROM department WHERE id = :id")
    suspend fun deleteDepartmentById(id: Int)
    
    // 获取所有部门
    @Query("SELECT * FROM department ORDER BY id")
    suspend fun getAllDepartments(): List<Department>
    
    // 根据ID获取部门
    @Query("SELECT * FROM department WHERE id = :id")
    suspend fun getDepartmentById(id: Int): Department?
    
    // 根据名称获取部门
    @Query("SELECT * FROM department WHERE 部门名称 = :name")
    suspend fun getDepartmentByName(name: String): Department?
    
    // 根据分类获取部门
    @Query("SELECT * FROM department WHERE 分类 = :category ORDER BY id")
    suspend fun getDepartmentsByCategory(category: String): List<Department>
    
    // 根据职能获取部门
    @Query("SELECT * FROM department WHERE 职能 = :ability ORDER BY id")
    suspend fun getDepartmentsByAbility(ability: String): List<Department>
    
    // 根据状态获取部门
    @Query("SELECT * FROM department WHERE 状态 = :status ORDER BY id")
    suspend fun getDepartmentsByStatus(status: String): List<Department>
    
    // 搜索部门（根据名称、分类、职能）
    @Query("SELECT * FROM department WHERE 部门名称 LIKE '%' || :keyword || '%' OR 分类 LIKE '%' || :keyword || '%' OR 职能 LIKE '%' || :keyword || '%'")
    suspend fun searchDepartments(keyword: String): List<Department>
    
    // 获取所有分类
    @Query("SELECT DISTINCT 分类 FROM department ORDER BY 分类")
    suspend fun getAllCategories(): List<String>
    
    // 获取所有职能
    @Query("SELECT DISTINCT 职能 FROM department ORDER BY 职能")
    suspend fun getAllAbilities(): List<String>
    
    // 获取活跃部门
    @Query("SELECT * FROM department WHERE 状态 = '活跃' ORDER BY 创建时间 DESC")
    suspend fun getActiveDepartments(): List<Department>
    
    // 更新部门状态
    @Query("UPDATE department SET 状态 = :status WHERE id = :departmentId")
    suspend fun updateDepartmentStatus(departmentId: Int, status: String)
    
    // 统计部门数量
    @Query("SELECT COUNT(*) FROM department")
    suspend fun getDepartmentCount(): Int
    
    // 统计某个状态的部门数量
    @Query("SELECT COUNT(*) FROM department WHERE 状态 = :status")
    suspend fun getDepartmentCountByStatus(status: String): Int
    
    // 统计某个分类的部门数量
    @Query("SELECT COUNT(*) FROM department WHERE 分类 = :category")
    suspend fun getDepartmentCountByCategory(category: String): Int
} 