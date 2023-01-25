package com.example.MyFirstSpringProject.testing_methods;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingMethods {
    //1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
    //phone number and email.
    public static void testGetAllCustomers(ResultSet resultSet) throws SQLException {
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
}
