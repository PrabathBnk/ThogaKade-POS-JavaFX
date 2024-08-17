package controller;

import db.ThogakadePOS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Customer;

public class SearchCustomerFormController {

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
    void btnSearchOnAction(ActionEvent event) {
        Customer customer = ThogakadePOS.getInstance().findCustomer(txtSearch.getText());

        if(customer == null){
            new Alert(Alert.AlertType.ERROR, "There is no customer found").show();
            clearLabels();
            return;
        }

        lblName.setText(customer.getNameWithTitle());
        lblAddress.setText(customer.getAddress());
        lblContactNumber.setText(customer.getContactNumber());
        lblDateOfBirth.setText(customer.getDateOfBirth().toString());
    }

    public void txtSearchOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")){
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
