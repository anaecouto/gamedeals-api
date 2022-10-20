package br.com.example.gamedealsapi.core.useCases

import br.com.example.gamedealsapi.client.CheapSharkClient
import br.com.example.gamedealsapi.core.dtos.StoreDTO
import org.springframework.stereotype.Component

@Component
class ListStoresUseCase(val cheapSharkClient: CheapSharkClient) {

    fun execute(): List<StoreDTO> {
        return cheapSharkClient.getStores()
    }
}