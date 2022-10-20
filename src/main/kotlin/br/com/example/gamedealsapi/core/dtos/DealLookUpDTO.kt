package br.com.example.gamedealsapi.core.dtos

data class DealLookUpDTO(
    val gameInfo: InfoDTO,
    val cheaperStores: List<CheaperStoresDTO>,
    val cheapestPrice: CheapestPriceEverDTO
)