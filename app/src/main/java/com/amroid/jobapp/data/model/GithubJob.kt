package com.amroid.jobapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomMasterTable


@Entity(tableName = "job")


data class GithubJob (
    @PrimaryKey
    val id : String,
    val type : String?,
    val url : String?,
    val created_at : String?,
    val company : String?,
    val company_url : String?,
    val location : String?,
    val title : String?,
    val description : String?,
    val how_to_apply : String?,
    val company_logo : String?,
    val isSaved: Boolean?=false

){
    companion object {
        const val TABLE_NAME = "github_jobs"
    }
}