package com.amroid.jobapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amroid.jobapp.data.model.GithubJob
import kotlinx.coroutines.flow.Flow



@Dao
interface JobDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertjobs(jobs: List<GithubJob>)


    @Query("DELETE FROM job")
    fun deleteAlljobs()


    @Query("SELECT * FROM job WHERE id = :jobId")
    fun getJobById(jobId: String): Flow<GithubJob>


    @Query("SELECT * FROM job")
    fun getAlljobs(): Flow<List<GithubJob>>

    @Query("Update   job set isSaved=:isSaved where id=:id")
    fun updateJob( id:String,isSaved:Boolean)
}