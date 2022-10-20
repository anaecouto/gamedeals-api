package br.com.example.gamedealsapi.client

import br.com.example.gamedealsapi.core.dtos.DealLookUpDTO
import br.com.example.gamedealsapi.core.dtos.DealsDTO
import br.com.example.gamedealsapi.core.dtos.StoreDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "cheapshark", url = "https://www.cheapshark.com/api/1.0")
interface CheapSharkClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/stores"], produces = ["application/json"])
    fun getStores(): List<StoreDTO>

    @RequestMapping(method = [RequestMethod.GET], value = ["/deals"], produces = ["application/json"])
    fun getDeals(
        @RequestParam storeID: String?,
        @RequestParam upperPrice: String?,
        @RequestParam title: String?,
        @RequestParam onSale: String?,
        @RequestParam pageNumber: Int?,
        @RequestParam metacritic: Int?,
        @RequestParam sortBy: String?
    ): List<DealsDTO>

    @RequestMapping(method = [RequestMethod.GET], value = ["/deals"], produces = ["application/json"])
    fun dealLookup(
        @RequestParam id: String
    ): DealLookUpDTO
}