package by.bashlikovvv.heroes_compose.data.remote.response

import com.google.gson.annotations.SerializedName

data class HeroesDto(
    @SerializedName("data") val data: List<DataItem?>?,
    @SerializedName("info") val info: Info?
)