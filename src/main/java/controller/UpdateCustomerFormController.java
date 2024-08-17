package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {

    private Customer resultCustomer;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private DatePicker dateDateOfBirth;

    @FXML
    private Label lblCustomerId;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        resultCustomer = ThogakadePOS.getInstance().findCustomer(txtSearch.getText());

        if(resultCustomer == null){
            new Alert(Alert.AlertType.ERROR, "There is no customer found").show();
            clearFields();
            return;
        }

        txtName.setText(resultCustomer.getName());
        txtAddress.setText(resultCustomer.getAddress());
        txtContactNumber.setText(resultCustomer.getContactNumber());
        dateDateOfBirth.setValue(resultCustomer.getDateOfBirth());
        cmbTitle.setValue(resultCustomer.getTitle());
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        if(resultCustomer == null) return;

        String name = txtName.getText();
        String contactNumber = txtContactNumber.getText();
        String title = cmbTitle.getValue();
        LocalDate dateOfBirth = dateDateOfBirth.getValue();

        //Validate mandatory fields are filled
        if(name.isBlank() || contactNumber.isBlank() || title == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Make Sure to fill all mandatory fields!");
            alert.show();
            return;
        }

        //Validate the contact number
        if (contactNumber.length() != 10 || contactNumber.charAt(0) != '0') {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid contact number!");
            alert.show();
            return;
        }

        //Validate date of birth
        if(dateOfBirth.compareTo(LocalDate.now()) >= 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid date of birth!");
            alert.show();
            return;
        }

        //Add customer object to the customer list
        ThogakadePOS.getInstance().updateCustomer(resultCustomer, new Customer(resultCustomer.getId(), title, name, txtAddress.getText(), contactNumber, dateOfBirth));
        new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully!").show();

        //Clear input fields
        clearFields();

        resultCustomer = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set titles to the combo box
        ObservableList<String> titles = FXCollections.observableArrayList();

        titles.add("Mr.");
        titles.add("Mrs.");
        titles.add("Miss.");

        cmbTitle.setItems(titles);
    }

    private void clearFields(){
        txtName.setText(null);
        txtAddress.setText(null);
        txtContactNumber.setText(null);
        dateDateOfBirth.setValue(null);
        cmbTitle.setValue(null);
    }

    public void txtSearchOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")){
            btnSearchOnAction(null);
        }
    }
}
