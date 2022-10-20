package br.com.example.gamedealsapi.core.useCases

import br.com.example.gamedealsapi.client.CheapSharkClient
import br.com.example.gamedealsapi.core.dtos.DealsDTO
import org.springframework.stereotype.Component

@Component
class ListDealsUseCase(val cheapSharkClient: CheapSharkClient) {

    fun execute(
        storeID: String?,
        upperPrice: String?,
        title: String?,
        onSale: String?,
        pageNumber: Int?,
        metacritic: Int?,
        sortBy: String?

    ): List<DealsDTO> {
       return cheapSharkClient.getDeals(storeID, upperPrice, title, onSale, pageNumber, metacritic, sortBy)
    }
}