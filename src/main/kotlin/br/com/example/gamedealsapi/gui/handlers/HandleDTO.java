package br.com.example.gamedealsapi.gui.handlers;

import br.com.example.gamedealsapi.core.dtos.*;

import java.util.List;

public class HandleDTO {
    private List<StoreDTO> storesList;

    private DealLookUpDTO dealLookUp;

    private List<DealsDTO> dealsList;

    public List<StoreDTO> getStoresList() {
        return this.storesList;
    }

    public void setStoresList(List<StoreDTO> storesList) {
        this.storesList = storesList;
    }

    public List<DealsDTO> getDealsList() {
        return dealsList;
    }

    public void setDealsList(List<DealsDTO> dealsList) {
        this.dealsList = dealsList;
    }

    public DealLookUpDTO getDealLookUp() {
        return dealLookUp;
    }

    public void setDealLookUp(DealLookUpDTO dealLookUp) {
        this.dealLookUp = dealLookUp;
    }
}
