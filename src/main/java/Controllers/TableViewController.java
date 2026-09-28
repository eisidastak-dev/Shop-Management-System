package Controllers;
import LinkedList.StoreList;
import Objects.Aisle;
import Objects.FloorArea;
import Objects.Item;
import Objects.Shelf;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


import javax.swing.*;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.*;

//BELOW ARE ALL THE COLUMNS FOR THE TABLE VIEW WHICH CORRESPOND WITH ITS FXID.

public class TableViewController implements Initializable {
    @FXML
    private TableColumn<Shelf, String> aisleDesignation;

    @FXML
    private TableColumn<Aisle, String> floorAreaDesignation;

    @FXML
    private TableColumn<Item, String> shelfDesignation;

    @FXML
    private TableView<Item> ItemTable;

    @FXML
    private TableColumn<Aisle, Integer> aisleHeight;

    @FXML
    private TableColumn<Aisle, Integer> aisleLength;

    @FXML
    private TableColumn<Aisle, String> aisleName;

    @FXML
    private TableView<Aisle> aisleTable;

    @FXML
    private TableColumn<Aisle, Integer> aisleWidth;

    @FXML
    private TableColumn<Item, String> description;

    @FXML
    private TableView<FloorArea> floorAreaTable;

    @FXML
    private TableColumn<FloorArea, String> floorAreaTitle;

    @FXML
    private TableColumn<FloorArea, Integer> level;

    @FXML
    private TableColumn<Item, String> photoUrl;

    @FXML
    private TableColumn<Item, Integer> quantity;

    @FXML
    private TableColumn<Shelf, Integer> shelfNumber;

    @FXML
    private TableView<Shelf> shelfTable;

    @FXML
    private TableColumn<Aisle, String> aisleTemperature;

    @FXML
    private TableColumn<Item, String> storageTemperature;

    @FXML
    private TableColumn<Item, Integer> unitPrice;

    @FXML
    private TableColumn<Item, Integer> weight;


    // METHOD FOR FLOOR AREA -------------------------------------------
    @FXML
    public TextField textFieldFloorAreaTitle;

    @FXML
    public TextField textFieldFloorAreaLevel;

    @FXML
    public Button addButton;

    public void clearAllFloorAreaTextFields() {
        textFieldFloorAreaLevel.clear();
        textFieldFloorAreaTitle.clear();
        floorAreaTitleUpdate.clear();
    }


    StoreList<FloorArea> floorAreaList = new StoreList<>();

    //ADDS A FLOOR AREA AND ALSO REFRESHES FLOOR AREA CHOICES CHOICEBOX.
    private void addFloorArea() {
        String floorTitle = textFieldFloorAreaTitle.getText();
        String levelText = textFieldFloorAreaLevel.getText();

        int level = Integer.parseInt(levelText);
        FloorArea newFloorArea = new FloorArea(floorTitle, level);
        floorAreaList.add(newFloorArea);
        getAllFloorChoices();
    }

    @FXML
    private void addFloorAreaButton(ActionEvent event) {
        addFloorArea();
        refreshFloorArea();
        clearAllFloorAreaTextFields();
    }

    //REFRESHES THE FLOOR AREA TABLE BY LOOPING THROUGH THE LIST AND SETS ITEMS.
    //NOTE: THE TABLE MUST USE .REFRESH OR THE DISPLAYED ITEMS WILL NOT SHOW.
    private void refreshFloorArea() {
        ObservableList<FloorArea> tableList = FXCollections.observableArrayList();
        for (int i = 0; i < floorAreaList.size(); i++) {
            tableList.add(floorAreaList.get(i));
        }
        floorAreaTable.setItems(tableList);
        floorAreaTable.refresh();
    }

    @FXML
    public Button updateButton;
    @FXML
    public TextField floorAreaTitleUpdate;

    private void updateFloorArea(String titleToUpdate) {
        for (int i = 0; i < floorAreaList.size(); i++) {
            FloorArea floorArea = floorAreaList.get(i);
            String title = floorArea.getFloorAreaTitle();
            if (title != null && title.equals(titleToUpdate)) {

                String updatedTitle = textFieldFloorAreaTitle.getText();
                int updatedFloorLevel = Integer.parseInt(textFieldFloorAreaLevel.getText());

                floorArea.setFloorAreaTitle(updatedTitle);
                floorArea.setLevel(updatedFloorLevel);
                refreshFloorArea();
            }
        }
    }

    @FXML
    private void updateFloorAreaButton(ActionEvent event) {
        updateFloorArea(floorAreaTitleUpdate.getText());
        getAllFloorChoices();
        clearAllFloorAreaTextFields();
    }

    public void removeFloorArea(String nameToRemove) {
        for (int i = 0; i < floorAreaList.size(); i++) {
            FloorArea floorArea = floorAreaList.get(i);
            String title = floorArea.getFloorAreaTitle();
            if (title.equals(nameToRemove)) {
                floorAreaList.remove(i);
                floorAreaTable.refresh();
                refreshFloorArea();
            }
        }
    }

    public Button deleteButton1;

    public void deleteFloorAreaButton() {
        removeFloorArea(floorAreaTitleUpdate.getText());
    }


    //-----------------------------------------------------------

    //METHODS FOR AISLE -----------------------------------------
    public TextField aisleNameTextField;

    public TextField aisleWidthTextField;

    public ChoiceBox<String> aisleTemperatureChoiceBox;
    public String[] temperature = {"Refrigerated", "Unrefrigerated", "Frozen"};

    public ChoiceBox<String> floorAreaSelectionForAisle;

    public TextField aisleLengthTextField;

    public TextField aisleHeightTextField;

    public Button addButton2;

    public void clearAllAisleTextFields() {
        aisleNameTextField.clear();
        aisleWidthTextField.clear();
        aisleHeightTextField.clear();
        aisleLengthTextField.clear();
        titleOfAisleToUpdate.clear();
    }

    StoreList<Aisle> aisleList = new StoreList<>();

    public Boolean checkForRepeatedAisle(String nameToCheck) {
        for (int i = 0; i < aisleList.size(); i++) {
            Aisle aisle = aisleList.get(i);
            String name = aisle.getAisleName();
            if (name.equals(nameToCheck))
                return false;
        }
        return true;
    }

    private void addAisle() {
        String aisleName = aisleNameTextField.getText();
        String aisleWidth = aisleWidthTextField.getText();
        String aisleHeight = aisleHeightTextField.getText();
        String aisleTemperature = aisleTemperatureChoiceBox.getValue();
        String aisleLength = aisleLengthTextField.getText();
        String floorAreaDesignation = floorAreaSelectionForAisle.getValue();

        int aisleLengthInt = Integer.parseInt(aisleLength);
        int aisleWidthInt = Integer.parseInt(aisleWidth);
        int aisleHeightInt = Integer.parseInt(aisleHeight);
        if (checkForRepeatedAisle(aisleName)) {
            Aisle newAisle = new Aisle(aisleName, aisleLengthInt, aisleWidthInt, aisleHeightInt, aisleTemperature, floorAreaDesignation);
            aisleList.add(newAisle);

            getAllAisleChoices();
        }
    }


    public void addAisleButton(ActionEvent event) {
        addAisle();
        refreshAisleTable();
        clearAllAisleTextFields();
    }

    public void refreshAisleTable() {
        ObservableList<Aisle> tableList = FXCollections.observableArrayList();
        for (int i = 0; i < aisleList.size(); i++) {
            tableList.add(aisleList.get(i));
        }
        aisleTable.setItems(tableList);
        aisleTable.refresh();
    }

    private void getAllFloorChoices() {
        ObservableList<String> choices = FXCollections.observableArrayList();

        for (int i = 0; i < floorAreaList.size(); i++) {
            FloorArea areaNode = floorAreaList.get(i);
            choices.add(areaNode.getFloorAreaTitle());
        }
        floorAreaSelectionForAisle.setItems(choices);
    }

    public Button updateButton2;

    public TextField titleOfAisleToUpdate;

    public void updateAisle(String titleToUpdate) {
        for (int i = 0; i < aisleList.size(); i++) {
            Aisle aisle = aisleList.get(i);
            String aisleTitle = aisle.getAisleName();
            if (titleToUpdate.equals(aisleTitle)) {
                String updatedName = aisleNameTextField.getText();
                int updatedLength = Integer.parseInt(aisleLengthTextField.getText());
                int updatedWidth = Integer.parseInt(aisleWidthTextField.getText());
                int updatedHeight = Integer.parseInt(aisleHeightTextField.getText());
                String updatedAisleTemperature = aisleTemperatureChoiceBox.getValue();
                String updatedFloorAreaSelection = floorAreaSelectionForAisle.getValue();

                aisle.setAisleName(updatedName);
                aisle.setAisleLenght(updatedLength);
                aisle.setAisleWidth(updatedWidth);
                aisle.setAisleHeight(updatedHeight);
                aisle.setTemperature(updatedAisleTemperature);
                aisle.setFloorAreaDesignation(updatedFloorAreaSelection);

                refreshAisleTable();
            }
        }
    }

    public void updateAisleButton() {
        updateAisle(titleOfAisleToUpdate.getText());
        clearAllAisleTextFields();
        getAllAisleChoices();
    }

    public Button deleteButton2;

    public void removeAisle(String nameOfShelf) {
        for (int i = 0; i < aisleList.size(); i++) {
            Aisle aisle = aisleList.get(i);
            String title = aisle.getAisleName();
            if (title.equals(nameOfShelf)) {
                aisleList.remove(i);
                refreshAisleTable();
            }
        }
    }

    public void deleteAisleButton() {
        removeAisle(titleOfAisleToUpdate.getText());
    }


    //-----------------------------------------------------------

    //METHODS For Shelf ----------------------------------------

    public TextField shelfNumberTextField;

    public Button addButton3;

    public ChoiceBox<String> aisleSelectionForShelf;

    StoreList<Shelf> newShelfList = new StoreList<>();


    private void addShelf() {
        String shelfNumber = shelfNumberTextField.getText();
        int shelfNumberInt = Integer.parseInt(shelfNumber);
        String shelfDesignation = aisleSelectionForShelf.getValue();
        Shelf newShelf = new Shelf(shelfNumberInt, shelfDesignation);
        newShelfList.add(newShelf);
        getAllShelves();
    }

    private void refreshShelfTable() {
        ObservableList<Shelf> tableList = FXCollections.observableArrayList();
        for (int i = 0; i < newShelfList.size(); i++) {
            tableList.add(newShelfList.get(i));
        }
        shelfTable.setItems(tableList);
        shelfTable.refresh();
    }

    public void addShelfButton() {
        addShelf();
        refreshShelfTable();

        shelfNumberTextField.clear();
    }

    public void getAllAisleChoices() {
        ObservableList<String> choice = FXCollections.observableArrayList();

        for (int i = 0; i < aisleList.size(); i++) {
            Aisle aisleNode = aisleList.get(i);
            choice.add(aisleNode.getAisleName());
        }
        aisleSelectionForShelf.setItems(choice);
    }

    @FXML
    public Button updateButton3;
    @FXML
    public TextField updateShelfNumber;

    public void updateShelf(int shelfNumber) {
        for (int i = 0; i < newShelfList.size(); i++) {
            Shelf shelf = newShelfList.get(i);
            int shelfNo = shelf.getShelfNumber();
            if (shelfNo == shelfNumber) {
                int updatedShelfNumber = Integer.parseInt(shelfNumberTextField.getText());
                String updatedAisleAllocation = aisleSelectionForShelf.getValue();

                shelf.setShelfNumber(updatedShelfNumber);
                shelf.setShelfDesignation(updatedAisleAllocation);

                refreshShelfTable();
            }
        }
    }

    public void updateShelfButton() {
        updateShelf(Integer.parseInt(updateShelfNumber.getText()));
        getAllShelves();
    }

    public Button deleteButton3;

    public void removeShelf() {
        for (int i = 0; i < newShelfList.size(); i++) {
            Shelf shelf = newShelfList.get(i);
            int shelfNumber = shelf.getShelfNumber();
            int shelfToRemove = Integer.parseInt(updateShelfNumber.getText());
            if (shelfNumber % shelfToRemove == 0) {
                newShelfList.remove(i);
            }
        }
    }

    public void deleteShelfButton() {
        removeShelf();
        refreshShelfTable();
        getAllShelves();
        updateShelfNumber.clear();
        shelfNumberTextField.clear();
    }


    //------------------------------------------------------------
    //METHODS for Item ------------------------------------------
    public TextField itemNameTextField;

    public TextField itemPriceTextField;

    public ChoiceBox<String> itemStorageTempChoiceBox;

    public TextField itemWeightTextField;

    public TextField itemQuantityTextField;

    public TextField itemPhotoUrlTextField;

    public Button addButton4;

    public ChoiceBox<Integer> shelfDesignationChoiceBox;

    public void clearAllItemField() {
        itemNameTextField.clear();
        itemPriceTextField.clear();
        itemWeightTextField.clear();
        itemQuantityTextField.clear();
        itemPhotoUrlTextField.clear();
    }

    StoreList<Item> itemList = new StoreList<>();

    //RETURNS TRUE OR FALSE DEPENDING IF THE DESCRIPTION EXISTS.

    public Boolean checkIfItemDescriptionExists(String descriptionToCheck) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            String descriptionOfItem = item.getDescription();
            if (descriptionToCheck.equals(descriptionOfItem))
                return true;
        }
        return false;
    }

    //CHECKS IF THE WEIGHT ENTERED MATCHES ANY OTHER WEIGHT IN THE LIST
    //THE METHOD BELOW AND ABOVE WORK IN COMBINATION WITH ONE ANOTHER.

    public Boolean checkWeightOfItem(int weightToCheck) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            int itemWeight = item.getWeight();
            if (itemWeight % weightToCheck == 0)
                return true;
        }
        return false;
    }

    //THE ADD ITEM METHOD UTILISES THE TWO METHODS ABOVE
    //WHAT THIS DOES IS THAT IF AN ITEM  HAVE THE EXACT SAME DESCRIPTION AND WEIGHT
    //AS A ITEM PRESENT IN THE LIST ALREADY, SOME OF THE FIELDS ARE UPDATED INSTEAD OF MAKING A NEW NODE WITH
    //THE SAME I0TEM NAME
    //THIS ALSO CHECK IF THE NAME EXISTS BUT IF THE WEIGHT DOES NOT MATCH THE ITEM IS NOT ADDED
    //THIS IS SO NO ITEMS WITH THE SAME DESCRIPTION WOULD REPEAT EACH-OTHER AS THIS WOULD MAKE DUPLICATE ITEM
    //NAMES AND MAY CAUSE ISSUES WITH UPDATING AND REMOVING ITEMS IN THE WAY WHICH i HAVE SET IT UP

    public void addItem() {

        String itemName = itemNameTextField.getText();
        String itemTemp = itemStorageTempChoiceBox.getValue();
        String itemUrl = itemPhotoUrlTextField.getText();
        String itemPrice = itemPriceTextField.getText();
        String itemWeight = itemWeightTextField.getText();
        String itemQuantity = itemQuantityTextField.getText();
        int shelfDesignation = shelfDesignationChoiceBox.getValue();

        int itemQuantityInt = Integer.parseInt(itemQuantity);
        int itemPriceInt = Integer.parseInt(itemPrice);
        int itemWeightInt = Integer.parseInt(itemWeight);

        if (!checkIfItemDescriptionExists(itemName) || !checkWeightOfItem(itemWeightInt)) {
            if (!checkIfItemDescriptionExists(itemName)) {
                Item newItem = new Item(itemName, itemWeightInt, itemPriceInt, itemQuantityInt, itemTemp, itemUrl, shelfDesignation);
                itemList.add(newItem);
            }
        }
        if (checkIfItemDescriptionExists(itemName) && checkWeightOfItem(itemWeightInt)) {
            for (int i = 0; i < itemList.size(); i++) {
                Item item = itemList.get(i);
                String name = item.getDescription();
                int weight = item.getWeight();
                int shelf = item.getShelfDesignation();
                if (name.equals(itemName) && weight == itemWeightInt && shelf == shelfDesignation) {

                    int quantity = item.getQuantity();
                    int newQuantity = quantity + itemQuantityInt;

                    item.setUnitPrice(itemPriceInt);
                    item.setQuantity(newQuantity);
                    item.setPhotoUrl(itemUrl);
                    getTotalValue();

                }
            }
        }


    }

    public void updateItemTable() {
        ObservableList<Item> tableList = FXCollections.observableArrayList();
        for (int i = 0; i < itemList.size(); i++) {
            tableList.add(itemList.get(i));
        }
        ItemTable.setItems(tableList);
        ItemTable.refresh();
    }

    public void addItemButton() {
        addItem();
        updateItemTable();
        clearAllItemField();
    }

    //SETS ITEMS FOR THE SHELF CHOICEBOX

    public void getAllShelves() {
        ObservableList<Integer> choice = FXCollections.observableArrayList();
        for (int i = 0; i < newShelfList.size(); i++) {
            Shelf shelfNode = newShelfList.get(i);
            choice.add(shelfNode.getShelfNumber());
        }
        shelfDesignationChoiceBox.setItems(choice);
    }


    public TextField itemNameToUpdateTextField;

    public Button updateButton4;

    public void updateItem(String itemToUpdate) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            String itemName = item.getDescription();
            if (itemName.equals(itemToUpdate)) {
                String updatedItemName = itemNameTextField.getText();
                int updatedItemWeight = Integer.parseInt(itemWeightTextField.getText());
                double updatedUnitPrice = Integer.parseInt(itemPriceTextField.getText());
                int updatedQuantity = Integer.parseInt(itemQuantityTextField.getText());
                String updatedURL = itemPhotoUrlTextField.getText();
                String updatedItemTemp = itemStorageTempChoiceBox.getValue();
                int updatedShelfDesignation = shelfDesignationChoiceBox.getValue();

                item.setDescription(updatedItemName);
                item.setWeight(updatedItemWeight);
                item.setUnitPrice(updatedUnitPrice);
                item.setQuantity(updatedQuantity);
                item.setPhotoUrl(updatedURL);
                item.setStorageTemperature(updatedItemTemp);
                item.setShelfDesignation(updatedShelfDesignation);

                updateItemTable();
                getTotalValue();
            }
        }
    }

    public void updateItemButton() {
        updateItem(itemNameToUpdateTextField.getText());
    }

    public Button deleteButton4;

    public void removeItem() {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            String title = item.getDescription();
            String itemToDelete = itemNameToUpdateTextField.getText();
            if (title.equals(itemToDelete)) {
                itemList.remove(i);
                updateItemTable();
                getTotalValue();
            }
        }
    }

    public void deleteItemButton() {
        removeItem();
    }

    public Text totalValueOFAllItems;

    //RETURNS THE TOTAL VALUE OF ALL THE ITEMS IN THE STORE

    public void getTotalValue() {
        double totalValue = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            double price = item.getUnitPrice() * item.getQuantity();
            totalValue += price;
        }
        totalValueOFAllItems.setText(String.valueOf(totalValue));
    }

    //SEARCHING METHODS -------------------------------------------------------------------------
    public TextField searchForAnItemTextField;

    public TextArea searchedItemReturnTextField;

    public Button searchButton;

    //RETURNS THE AISLE ON WHICH A PARTICULAR SHELF IS STORED ON

    public String getShelfAisleDesignation(int shelfNumber){
        for(int i = 0; i< newShelfList.size(); i++){
            Shelf shelf = newShelfList.get(i);
            String title = shelf.getShelfDesignation();
            if(shelf.getShelfNumber() == shelfNumber){
                return title;
            }
        }
        return "This Shelf Does Not Have a Designation";
    }

    //RETURNS THE FLOOR ARE WHERE A SPECIFIED SHELF IS STORED ON
    public String getFloorAreaByAisle(String aisleName){
        for(int i = 0; i < aisleList.size(); i++){
            Aisle aisle = aisleList.get(i);
            String title = aisle.getAisleName();
            String floorAreaForAisle = aisle.getFloorAreaDesignation();
            if(title.equals(aisleName)){
                return floorAreaForAisle;

            }
        }
        return "No FloorArea for this Aisle";
    }

    //THE METHOD BELOW IS THE MAIN METHOD FOR DISPLAYING THE STRING IN THE SEARCH TEXTFIELD
    //THE METHOD MAKES USE OF METHODS ABOVE WHICH DETERMINE WHICH SHELF IS STORED ON WHICH AISLE AND WHICH AISLE IS ON WHAT FLOOR AREA.


    public String returnSearchedItem() {
        String itemToSearch = searchForAnItemTextField.getText();
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            String itemTitle = item.getDescription();
            if (itemTitle.equals(itemToSearch)) {
                int weight = item.getWeight();
                double price = item.getUnitPrice();
                int quantity = item.getQuantity();
                String URL = item.getPhotoUrl();
                String temperature = item.getStorageTemperature();
                int shelfDesignationText = item.getShelfDesignation();

                String fullDescription =
                        "ITEM DESCRIPTION" + "\n" + "\n" +
                        "Name of Product: " + itemTitle + "\n"
                        + "Weight of Product: " + weight + "\n"
                        + "Price Of Product: " + price + "\n"
                        + "Quantity Of Product: " + quantity + "\n"
                        + "URL of Product: " + URL + "\n"
                        + "Temperature: " + temperature + "\n"
                        + "Shelf Number: " + shelfDesignationText + "\n"
                        + "Total Value of Good: " + quantity*price + "\n" + "\n"

                        + "Located on Aisle: " + getShelfAisleDesignation(shelfDesignationText) + "\n"

                        + "Located on FloorArea: " + getFloorAreaByAisle(getShelfAisleDesignation(shelfDesignationText));


                return fullDescription;

            }

        }
    return "No Product Found";
    }

    public void searchResults(){
        searchedItemReturnTextField.setText(returnSearchedItem());
        searchForAnItemTextField.clear();
    }

    //SIDE BUTTONS FOR SAVING LOADING AND CLEARING

    public Button resetButton;

    //RESETS THE LINKED LIST

    public void clearAllData(){
        floorAreaList.clear();
        aisleList.clear();
        newShelfList.clear();
        itemList.clear();
        refreshEverything();

    }
    //REFRESHES ALL TABLE AND ALSO ALL CHOICE BOXES

    public void refreshEverything(){
        refreshAisleTable();
        refreshFloorArea();
        refreshShelfTable();
        updateItemTable();
        getAllShelves();
        getAllAisleChoices();
        getAllFloorChoices();
    }

    public void save(){
        SavingLoadingManager.save(floorAreaList, aisleList, newShelfList, itemList);
    }

    public void load() {
        StoreList<?>[] data = SavingLoadingManager.load();
        if (data == null) return;

        floorAreaList = (StoreList<FloorArea>) data[0];
        aisleList     = (StoreList<Aisle>) data[1];
        newShelfList  = (StoreList<Shelf>) data[2];
        itemList      = (StoreList<Item>) data[3];

        refreshEverything();
        getTotalValue();
    }

    public void saveAction(){
        save();
    }

    public void loadAction(){
        load();
    }

    //-------------------------------------------------------------------


    //SETS CELL VALUES FOR ALL THE TABLES IN THE PROGRAM
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aisleTemperatureChoiceBox.getItems().addAll(temperature);
        itemStorageTempChoiceBox.getItems().addAll(temperature);


        floorAreaTitle.setCellValueFactory(new PropertyValueFactory<FloorArea,String>("floorAreaTitle"));
        level.setCellValueFactory(new PropertyValueFactory<FloorArea,Integer>("level"));
        refreshFloorArea();

        aisleName.setCellValueFactory(new PropertyValueFactory<Aisle, String>("aisleName"));
        aisleHeight.setCellValueFactory(new PropertyValueFactory<Aisle,Integer>("aisleHeight"));
        aisleWidth.setCellValueFactory(new PropertyValueFactory<Aisle, Integer>("aisleWidth"));
        aisleLength.setCellValueFactory(new PropertyValueFactory<Aisle, Integer>("aisleHeight"));
        aisleTemperature.setCellValueFactory(new PropertyValueFactory<Aisle, String>("temperature"));
        floorAreaDesignation.setCellValueFactory(new PropertyValueFactory<Aisle, String>("floorAreaDesignation"));

        shelfNumber.setCellValueFactory(new PropertyValueFactory<Shelf, Integer>("shelfNumber"));
        aisleDesignation.setCellValueFactory(new PropertyValueFactory<Shelf, String>("shelfDesignation"));

        description.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
        weight.setCellValueFactory(new PropertyValueFactory<Item, Integer>("weight"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<Item, Integer>("unitPrice"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        storageTemperature.setCellValueFactory(new PropertyValueFactory<Item, String>("storageTemperature"));
        photoUrl.setCellValueFactory(new PropertyValueFactory<Item, String>("photoUrl"));
        shelfDesignation.setCellValueFactory(new PropertyValueFactory<Item, String>("shelfDesignation"));
    }
}


