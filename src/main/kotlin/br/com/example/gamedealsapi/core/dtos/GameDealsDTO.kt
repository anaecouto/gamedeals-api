package br.com.example.gamedealsapi.core.dtos

data class GameDealsDTO(
    val storeID: String?,
    val dealID: String?,
    val price: String?,
    val retailPrice: String?,
    val savings: String?
)
