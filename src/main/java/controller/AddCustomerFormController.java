package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import db.ThogakadePOS;
import model.Customer;

public class AddCustomerFormController implements Initializable {

    private String customerID;

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
    void btnAddCustomerOnAction(ActionEvent event) {
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
        ThogakadePOS.getInstance().addCustomer(new Customer(customerID, title, name, txtAddress.getText(), contactNumber, dateDateOfBirth.getValue()));
        new Alert(Alert.AlertType.INFORMATION, "Customer Added Successfully!").show();

        //Clear input fields
        clearFields();

        //Set new customer id to the label
        customerID = generateCustomerId();
        lblCustomerId.setText(customerID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Generate customer ID and set in to the label
        customerID = generateCustomerId();
        lblCustomerId.setText(customerID);

        //Set titles to the combo box
        ObservableList<String> titles = FXCollections.observableArrayList();

        titles.add("Mr.");
        titles.add("Mrs.");
        titles.add("Miss.");

        cmbTitle.setItems(titles);
    }

    private String generateCustomerId(){
        List<Customer> customerList = ThogakadePOS.getInstance().getCustomerList();

        if (customerList.isEmpty()) return "C0001";

        String lastId = customerList.get(customerList.size() - 1).getId();
        return String.format("C%1$04d", Integer.parseInt(lastId.substring(1)) + 1);
    }

    private void clearFields(){
        txtName.setText(null);
        txtAddress.setText(null);
        txtContactNumber.setText(null);
        dateDateOfBirth.setValue(null);
        cmbTitle.setValue(null);
    }
}
