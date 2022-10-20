package br.com.example.gamedealsapi.core.dtos

data class DealsDTO(
    val internalName: String?,
    val title: String?,
    val metacriticLink: String?,
    val dealID: String?,
    val storeID: String?,
    val gameID: String?,
    val salePrice: String?,
    val normalPrice: String?,
    val savings: String?,
    val metacriticScore: String?,
    val dealRating: String?,
    val releaseDate: Long?,
    val thumb: String?
)