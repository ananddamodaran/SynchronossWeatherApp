package dev.anand.synchronossweatherapp.data.db

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class CurrentWeatherEntity(
    @PrimaryKey
    val id:Long,
    val name:String,
    val dt: Long,
    val description:String

    )
