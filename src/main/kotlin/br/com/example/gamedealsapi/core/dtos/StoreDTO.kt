package br.com.example.gamedealsapi.core.dtos

data class StoreDTO(
    val storeID: String?,
    val storeName: String?,
    val isActive: Boolean?,
    val images: ImageDTO?
)