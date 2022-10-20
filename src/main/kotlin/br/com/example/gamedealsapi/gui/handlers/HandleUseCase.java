package br.com.example.gamedealsapi.gui.handlers;

import br.com.example.gamedealsapi.client.CheapSharkClient;
import br.com.example.gamedealsapi.core.dtos.DealLookUpDTO;
import br.com.example.gamedealsapi.core.dtos.DealsDTO;
import br.com.example.gamedealsapi.core.dtos.StoreDTO;
import br.com.example.gamedealsapi.core.useCases.GameLookUpUseCase;
import br.com.example.gamedealsapi.core.useCases.ListDealsUseCase;
import br.com.example.gamedealsapi.core.useCases.ListStoresUseCase;

import javax.swing.*;
import java.util.List;

public class HandleUseCase {
    public final HandleDTO handleDTO;
    private final ListDealsUseCase listDealsUseCase;
    private final ListStoresUseCase listStoresUseCase;

    private final GameLookUpUseCase gameLookUpUseCase;

    public HandleUseCase(CheapSharkClient cheapSharkClient) {
        this.handleDTO = new HandleDTO();
        this.listDealsUseCase = new ListDealsUseCase(cheapSharkClient);
        this.listStoresUseCase = new ListStoresUseCase(cheapSharkClient);
        this.gameLookUpUseCase = new GameLookUpUseCase(cheapSharkClient);
    }

    public void listStores(JComboBox storesListField) {
        List<StoreDTO> availableStores = this.listStoresUseCase.execute();

        this.handleDTO.setStoresList(availableStores);
        List<StoreDTO> storesList = this.handleDTO.getStoresList();

        for (int i = 0; i < storesList.size(); i++) {
            if (Boolean.FALSE.equals(storesList.get(i).isActive())) {
                storesList.remove(i);
            }
        }

        storesList.forEach(storeDTO -> storesListField.addItem(storeDTO.getStoreName()));
    }

    public List<DealsDTO> listDeals(String storeID, String upperPrice,
                                    String title, String onSale, int pageNumber,
                                    int metacritic, String sortBy
    ) {
        this.handleDTO.setDealsList(this.listDealsUseCase.execute(
                storeID, upperPrice, title,
                onSale, pageNumber, metacritic, sortBy)
        );
        List<DealsDTO> dealsList = this.handleDTO.getDealsList();
        return dealsList;
    }

    public DealLookUpDTO getDealLookUp(String dealId) {
        this.handleDTO.setDealLookUp(this.gameLookUpUseCase.execute(dealId));
        DealLookUpDTO dealLookUp = this.handleDTO.getDealLookUp();
        return dealLookUp;
    }

}
