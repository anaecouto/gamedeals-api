package br.com.example.gamedealsapi.core.dtos

data class InfoDTO(
    val storeID: String?,
    val gameId: String?,
    val name: String?,
    val steamAppID: String?,
    val salePrice: String?,
    val retailPrice: String?,
    val metacriticScore: String?,
    val releaseDate: Long?,
    val publisher: String?,
    val thumb: String?
)
