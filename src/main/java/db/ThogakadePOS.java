package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class ThogakadePOS {

    private static ThogakadePOS instance = new ThogakadePOS();
    private List<Customer> customerList = new ArrayList<>();

    private ThogakadePOS(){
        if(null == customerList){
            customerList = new ArrayList<>();
        }
    }

    public static ThogakadePOS getInstance(){
        return null == instance ? instance = new ThogakadePOS(): instance;
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void deleteCustomer(Customer customer){
        customerList.remove(customer);
    }

    public Customer findCustomer(String id){
        for (int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getId().equalsIgnoreCase(id)){
                return customerList.get(i);
            }
        }
        return null;
    }

    public void updateCustomer(Customer customer, Customer updatedCustomer){
        customer.setId(updatedCustomer.getId());
        customer.setTitle(updatedCustomer.getTitle());
        customer.setName(updatedCustomer.getName());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setContactNumber(updatedCustomer.getContactNumber());
        customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        customer.setNameWithTitle(updatedCustomer.getTitle() + updatedCustomer.getName());
    }
}
