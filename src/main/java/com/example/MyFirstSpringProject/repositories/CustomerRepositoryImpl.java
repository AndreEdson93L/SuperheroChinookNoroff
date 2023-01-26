/*
* 3) Repositories
        You are expected to implement the repository pattern in this assignment. It should have a generic CRUD parent, then
        one for customer. All the functionality should be contained in the single customer repository (there should be no other
        repositories aside from the generic and customer one).
        The repositories should be placed in a repository package. You are to also create a ApplicationRunner class to test the
        repository.
*/

package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Customer;
import com.example.MyFirstSpringProject.models.CustomerCountry;
import com.example.MyFirstSpringProject.models.CustomerGenre;
import com.example.MyFirstSpringProject.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    //Testing the connection.
    public void testConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Custom method to display a single or a set of customer info.
    private void displayCustomerInfo(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String country = resultSet.getString("country");
            String postalCode = resultSet.getString("postal_code");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");

            System.out.println("customerId: "
                    + customerId + " firstName: "
                    + firstName + " lastName: "
                    + lastName + " country: "
                    + country + " postalCode: "
                    + postalCode + " phone: "
                    + phone + " email: "
                    + email);
        }
    }

    //2. Read a specific customer from the database (by Id), should display everything listed in the above point.
    @Override
    public Customer getById(Integer customer_id) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE customer_id = ?";

        var customer = new Customer(0, "", "", "", "", "", "");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, customer_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    //CRUD getALL
    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM customer ORDER BY customer_id;";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    //:Customer object: customer_id, first_name, last_name, country, postal_code, phone, email.
    //5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
    @Override
    public void insert(Customer object) {
        String sql = "INSERT INTO customer (customer_id, first_name, last_name, country, postal_code, phone, email) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            //Write statement
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, object.customer_id());
            statement.setString(2, object.first_name());
            statement.setString(3, object.last_name());
            statement.setString(4, object.first_name());
            statement.setString(5, object.postal_code());
            statement.setString(6, object.phone());
            statement.setString(7, object.email());

            //Execute statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //6. Update an existing customer.
    @Override
    public void update(Customer object) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? WHERE customer_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, object.first_name());
            statement.setString(2, object.last_name());
            statement.setString(3, object.country());
            statement.setString(4, object.postal_code());
            statement.setString(5, object.phone());
            statement.setString(6, object.email());
            statement.setInt(7, object.customer_id());

            //Execute statement
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
    //phone number and email.
    @Override
    public void readCustomerInfo() {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer;";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            displayCustomerInfo(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Custom method created to verify some functionalities.
    @Override
    public void printCustomerInfoById(int customer_id) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE customer_id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer_id);

            ResultSet resultSet = statement.executeQuery();
            displayCustomerInfo(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
    @Override
    public Customer getSpecificCustomerByName(String name) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer WHERE first_name LIKE ?";

        var customer = new Customer(0, "", "", "", "", "", "");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    //4. Return a page of customers from the database. This should take in limit and offset as parameters and make use
    //of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above
    //should be reused.
    //create a method that retrieves a specific number of customers from the database,
    //starting at a specific point, by using the limit and offset parameters.
    @Override
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

    //Getting last customer Id is just a method that we created to know where to insert the new customer with point 5.
    @Override
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

    //7. Return the country with the most customers.
    @Override
    public String getCountryWithMostCustomers() {
        String sql = "SELECT country FROM customer GROUP BY country HAVING COUNT (customer_id) > 0 ORDER BY COUNT(customer_id) DESC LIMIT 1";

        CustomerCountry country = new CustomerCountry("");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                country = new CustomerCountry(resultSet.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country.country();
    }

    //8. Customer who is the highest spender (total in invoice table is the largest)
    @Override
    public String getHighestSpender() {
        String sql = "SELECT c.customer_id, c.first_name, c.last_name, SUM(total) as total_buys " +
                "FROM customer c " +
                "INNER JOIN invoice i " +
                "ON i.customer_id = c.customer_id " +
                "GROUP BY c.customer_id " +
                "ORDER BY total_buys DESC " +
                "LIMIT 1;";

        CustomerSpender highestSpender = new CustomerSpender("", "");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                highestSpender = new CustomerSpender(resultSet.getString("first_name"), resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highestSpender.first_name() + " " + highestSpender.last_name();
    }

    //9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context
    //means the genre that corresponds to the most tracks from invoices associated to that customer.
    @Override
    public String getMostListenedGenreByCustomerId(int customer_id) {
        String sql = "SELECT t.genre_id, g.name, COUNT(*) as genre_count " +
                "FROM customer AS c " +
                "INNER JOIN invoice AS i ON c.customer_id = i.customer_id " +
                "INNER JOIN invoice_line il ON i.invoice_id = il.invoice_id " +
                "INNER JOIN track AS t ON il.track_id = t.track_id " +
                "INNER JOIN genre AS g ON t.genre_id = g.genre_id " +
                "WHERE c.customer_id = ? " +
                "GROUP BY t.genre_id, g.name " +
                "ORDER BY genre_count DESC " +
                "LIMIT 1;";

        var customerGenre = new CustomerGenre("");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                customerGenre = new CustomerGenre(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerGenre.genre();
    }

    //@Override
    //public void delete(Integer integer) {}
}
