package dev.anand.synchronossweatherapp.data.db

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Immutable
@Parcelize
data class CurrentWeatherEntity(
    @PrimaryKey
    val id:Long,
    val name:String,
    val dt: Long,
    val description:String

    ): Parcelable
