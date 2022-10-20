package br.com.example.gamedealsapi.gui.utils;

import br.com.example.gamedealsapi.core.dtos.StoreDTO;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class SelectedStoreUtils {

    public static Optional<StoreDTO> selectedStore(List<StoreDTO> storesList, JComboBox storesListField) {
        return storesList
                .stream()
                .filter(store ->
                        storesListField.getSelectedItem() != null
                                && Objects.equals
                                (store.getStoreName(), storesListField.getSelectedItem().toString())
                ).findFirst();
    }
}
