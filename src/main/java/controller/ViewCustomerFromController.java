package controller;

import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomerFromController implements Initializable {

    public TableView tblCustomers;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nameWithTitle"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(ThogakadePOS.getInstance().getCustomerList());
        tblCustomers.setItems(customerObservableList);
    }
}
