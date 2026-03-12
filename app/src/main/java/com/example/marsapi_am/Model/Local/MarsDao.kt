package com.example.marsapi_am.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marsapi_am.Model.Remote.MarsRealState


@Dao
interface MarsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertTerrain( terrain: MarsRealState)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTerrains( terrains: List<MarsRealState>)


    @Update
    suspend fun updateTerrains(terrain: MarsRealState)

    @Delete
    suspend fun deleteTerrain(terrain: MarsRealState)

    @Query( "DELETE FROM  mars_table")
    suspend fun deletAll()


    // traer todos los terrenos

    @Query( "SELECT * FROM mars_table ORDER BY ID DESC")
    fun getAllTerrains(): LiveData<List<MarsRealState>>


    // filtrar por titulo

    @Query("SELECT * FROM mars_table WHERE type = :type LIMIT 1")
    fun getTerrainByType(type: String): LiveData<MarsRealState>


    @Query("SELECT * FROM mars_table WHERE id = :id")
    fun getTerrainById(id: String): LiveData<MarsRealState>
}