package com.example.MyFirstSpringProject.access;

import com.example.MyFirstSpringProject.models.Customer;
import com.example.MyFirstSpringProject.models.Student;
import com.example.MyFirstSpringProject.testing_methods.TestingMethods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class PostgradDAO {
    private String url;
    private String username;
    private String password;

    public PostgradDAO(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void testConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        2) Customer requirements:
                For customers in the database, the following functionality should be catered for:
                1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
                   phone number and email.
                2. Read a specific customer from the database (by Id), should display everything listed in the above point.
                3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
                4. Return a page of customers from the database. This should take in limit and offset as parameters and make use
                   of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above
                   should be reused.
                5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
                6. Update an existing customer.
                7. Return the country with the most customers.
                8. Customer who is the highest spender (total in invoice table is the largest).
                9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context
        means the genre that corresponds to the most tracks from invoices associated to that customer.
    */

    //1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
    //phone number and email.

    public void getAllCustomers() {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer;";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //TestingMethods.testGetAllCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2. Read a specific customer from the database (by Id), should display everything listed in the above point.
    public void getSpecificCustomer(int id) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            //TestingMethods.testGetAllCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
    public void getSpecificCustomerByName(String name) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE first_name LIKE ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            TestingMethods.testGetAllCustomers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //4. Return a page of customers from the database. This should take in limit and offset as parameters and make use
    //of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above
    //should be reused.
    //create a method that retrieves a specific number of customers from the database, starting at a specific point, by using the limit and offset parameters.
    //
    //What we have to do
    //The limit parameter specifies the maximum number of customers to be retrieved,
    //while the offset parameter specifies the starting point from where the customers should be retrieved.
    //The method should use the LIMIT and OFFSET keywords in the SQL query to retrieve the desired subset of customer data,
    //and it should use the customer model class that you have already created.
    public List<Customer> getSetOfCustomers(int limit, int offset) {
        String sql = "SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                customers.add(customer);
                //System.out.println(customer.first_name());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    //:Customer object: customer_id, first_name, last_name, country, postal_code, phone, email.
    //5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)

    public int getLastCustomerId() {
        String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";
        int customer_id = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            //Writing statement, Getting last customer id
            PreparedStatement lastCustomerId = connection.prepareStatement(sql);
            //Execute
            ResultSet resultSet = lastCustomerId.executeQuery();
            if (resultSet.next()) {
                customer_id = resultSet.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_id + 1;
    }

    public int insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?,?)";

        int result = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            //Write statement
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, customer.customer_id());
            statement.setString(2, customer.first_name());
            statement.setString(3, customer.last_name());
            statement.setString(4, customer.first_name());
            statement.setString(5, customer.postal_code());
            statement.setString(6, customer.phone());
            statement.setString(7, customer.email());

            //Execute statement
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

