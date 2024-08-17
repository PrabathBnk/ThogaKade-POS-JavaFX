package controller;

import db.ThogakadePOS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import model.Customer;


public class DeleteCustomerFormController {

    private Customer resultCustomer;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContactNumber;

    @FXML
    private Label lblDateOfBirth;

    @FXML
    private Label lblName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if(resultCustomer == null) return;

        //Delete Confirmation Alert
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?");
        confirmAlert.showAndWait().ifPresent(response -> {
            if(response.getButtonData().isDefaultButton()){
                ThogakadePOS.getInstance().deleteCustomer(resultCustomer);
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully!").show();

                //Clear labels
                clearLabels();

                resultCustomer = null;
            }
        });


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        resultCustomer = ThogakadePOS.getInstance().findCustomer(txtSearch.getText());

        if(resultCustomer == null){
            new Alert(Alert.AlertType.ERROR, "There is no customer found").show();
            clearLabels();
            return;
        }

        lblName.setText(resultCustomer.getNameWithTitle());
        lblAddress.setText(resultCustomer.getAddress());
        lblContactNumber.setText(resultCustomer.getContactNumber());
        lblDateOfBirth.setText(resultCustomer.getDateOfBirth().toString());
    }

    @FXML
    void txtSearchOnKeyPressed(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            btnSearchOnAction(null);
        }
    }

    private void clearLabels(){
        lblName.setText(null);
        lblAddress.setText(null);
        lblContactNumber.setText(null);
        lblDateOfBirth.setText(null);
    }

}
