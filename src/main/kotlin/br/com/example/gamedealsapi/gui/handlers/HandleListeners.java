package br.com.example.gamedealsapi.gui.handlers;

import br.com.example.gamedealsapi.core.dtos.CheaperStoresDTO;
import br.com.example.gamedealsapi.core.dtos.DealLookUpDTO;
import br.com.example.gamedealsapi.core.dtos.DealsDTO;
import br.com.example.gamedealsapi.core.dtos.StoreDTO;
import br.com.example.gamedealsapi.gui.utils.SelectedStoreUtils;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HandleListeners {

    String onSale;

    String val;
    String sortBy;
    private int pageNumber;
    private final HandleUseCase handleUseCase;

    public HandleListeners(HandleUseCase handleUseCase) {
        this.handleUseCase = handleUseCase;
    }

    public void addListeners(
            JComboBox storesListField, JList gamesListField,
            JButton searchButton, JTextField gameTitle, JCheckBox onSaleCheckbox,
            JCheckBox sortByCheapestCheckBox, JSpinner pageFormattedTextField,
            JButton goToPageButton, JTextPane dealsTextPane, JButton viewDealsButton
    ) {
        this.searchButtonListener(
                storesListField, gamesListField, searchButton,
                gameTitle, onSaleCheckbox, sortByCheapestCheckBox,
                pageFormattedTextField
        );

        this.goToPageButtonListener(
                storesListField, gamesListField, goToPageButton,
                gameTitle, onSaleCheckbox, sortByCheapestCheckBox,
                pageFormattedTextField
        );

        this.viewDealsButtonListener(viewDealsButton, gamesListField, dealsTextPane);

        this.toggleEnableViewPageButtonListener(gamesListField, viewDealsButton);
    }

    private void searchButtonListener(
            JComboBox storesListField, JList gamesListField,
            JButton searchButton, JTextField gameTitle, JCheckBox onSaleCheckbox,
            JCheckBox sortByCheapestCheckBox, JSpinner pageFormattedTextField
    ) {
        searchButton.addActionListener(e -> {

            pageNumber = 0;

            searchButton.setEnabled(false);
            this.handleUseCase.handleDTO.setDealsList(new ArrayList<>());
            getStoresList(storesListField, onSaleCheckbox, sortByCheapestCheckBox);

            Optional<StoreDTO> storesStream = this.getStoresList(storesListField, onSaleCheckbox, sortByCheapestCheckBox);

            storesStream.ifPresent(storeDTO -> {
                this.getDealsList(gamesListField, gameTitle, storeDTO);

                searchButton.setEnabled(true);
                pageFormattedTextField.setValue(pageNumber);
            });
        });
    }

    private void goToPageButtonListener(
            JComboBox storesListField, JList gamesListField,
            JButton pageButton, JTextField gameTitle,
            JCheckBox onSaleCheckbox, JCheckBox sortByCheapestCheckBox,
            JSpinner pageFormattedTextField) {

        pageButton.addActionListener(e -> {
            this.handleUseCase.handleDTO.setDealsList(new ArrayList<>());

            pageNumber = Integer.parseInt(pageFormattedTextField.getValue().toString());

            getStoresList(storesListField, onSaleCheckbox, sortByCheapestCheckBox);

            Optional<StoreDTO> storesStream = this.getStoresList(storesListField, onSaleCheckbox, sortByCheapestCheckBox);

            storesStream.ifPresent(storeDTO -> {
                this.getDealsList(gamesListField, gameTitle, storeDTO);
            });
        });
    }

    private void viewDealsButtonListener(JButton viewDealsButton, JList gamesListField, JTextPane dealsTextPane) {
        viewDealsButton.addActionListener(e -> {
            List<DealsDTO> dealsList = this.handleUseCase.handleDTO.getDealsList();

            String selectedGame = gamesListField.getSelectedValue().toString();

            Pattern pattern = Pattern.compile("([0-9]+):(.+)?");

            Matcher matcher = pattern.matcher(selectedGame);

            if (matcher.find()) {
                val = matcher.group(1);
            }

            Optional<DealsDTO> dealFound = dealsList.stream()
                    .filter(dealsDTO -> Objects.equals(dealsDTO.getGameID(), val))
                    .findFirst();

            dealFound.ifPresent(dealDTO -> {
                DealLookUpDTO dealLookUp = this.handleUseCase.getDealLookUp(dealDTO.getDealID());

                String price = dealLookUp.getGameInfo().getSalePrice();

                List<CheaperStoresDTO> cheapestStoresList = dealLookUp.getCheaperStores();

                CheaperStoresDTO cheaperStoresDTO;

                StoreDTO storeDTO = null;

                String storeName = "None";

                List<StoreDTO> storesList = handleUseCase.handleDTO.getStoresList();

                if (cheapestStoresList.size() > 0) {
                    cheaperStoresDTO = cheapestStoresList
                            .parallelStream()
                            .min(Comparator.comparing(CheaperStoresDTO::getSalePrice))
                            .orElse(new CheaperStoresDTO("0", "", "", ""));

                    String storeId = cheaperStoresDTO.getStoreID();
                    storeDTO = storesList
                            .stream()
                            .filter(store -> store.getStoreID().equals(storeId))
                            .findAny()
                            .get();

                    storeName = storeDTO.getStoreName();
                }

                dealsTextPane.setText(String
                        .format("Sale price: USD %s", price)
                        .concat(String.format("\nCheaper store: %s", storeName)));
            });
        });
    }

    private Optional<StoreDTO> getStoresList(
            JComboBox storesListField, JCheckBox onSaleCheckbox,
            JCheckBox sortByCheapestCheckBox
    ) {
        List<StoreDTO> storesList = handleUseCase.handleDTO.getStoresList();

        if (sortByCheapestCheckBox.isSelected()) sortBy = "Price";
        else sortBy = "Title";
        if (onSaleCheckbox.isSelected()) onSale = "1";
        else onSale = "0";

        Optional<StoreDTO> storesStream = SelectedStoreUtils.selectedStore(storesList, storesListField);

        return storesStream;
    }

    private void getDealsList(JList gamesListField, JTextField gameTitle, StoreDTO storeDTO) {
        List<DealsDTO> dealsList = this.handleUseCase.listDeals(
                storeDTO.getStoreID(),
                null, gameTitle.getText(),
                onSale, pageNumber, 0, sortBy);

        this.handleUseCase.handleDTO.setDealsList(dealsList);
        gamesListField.setListData(dealsList.stream().map(dealsDTO ->
                        dealsDTO.getGameID() + ":" + dealsDTO.getTitle())
                .toArray(String[]::new)
        );
    }

    private void toggleEnableViewPageButtonListener(JList gamesListField, JButton viewDealsButton) {
        gamesListField.addListSelectionListener(e -> {
            if (gamesListField.getSelectedValue() == null) {
                viewDealsButton.setEnabled(false);
            } else {
                viewDealsButton.setEnabled(true);
            }
        });
    }
}
