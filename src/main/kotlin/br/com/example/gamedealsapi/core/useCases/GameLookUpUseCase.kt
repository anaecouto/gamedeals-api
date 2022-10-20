package br.com.example.gamedealsapi.core.useCases

import br.com.example.gamedealsapi.client.CheapSharkClient
import br.com.example.gamedealsapi.core.dtos.DealLookUpDTO
import org.springframework.stereotype.Component

@Component
class GameLookUpUseCase(val cheapSharkClient: CheapSharkClient) {

    fun execute(gameId: String) : DealLookUpDTO {
        return cheapSharkClient.dealLookup(gameId)
    }
}