package com.example.MyFirstSpringProject.repositories;

import com.example.MyFirstSpringProject.models.Customer;
import java.util.List;

//Generics
public interface CustomerRepository extends CrudRepository<Integer, Customer>{

    //1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
    //phone number and email.
    public void readCustomerInfo();

    //Custom method used to test.
    public void printCustomerInfoById(int customer_id);

    //3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
    public Customer getSpecificCustomerByName(String name);

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
    public List<Customer> getSetOfCustomers(int limit, int offset);

    //Getting last customer Id is just a method that we created to know where to insert the new customer with point 5.
    public int getLastCustomerId();

    //7. Return the country with the most customers.
    /*SELECT country
    FROM customer
    GROUP BY country
    HAVING COUNT(customer_id) > 0
    ORDER BY COUNT(customer_id) DESC
    LIMIT 1;
    */
    public String getCountryWithMostCustomers();

    //8. Customer who is the highest spender (total in invoice table is the largest)
    /*SELECT c.customer_id, c.first_name, c.last_name, SUM(total) as total_buys
      FROM customer c
      INNER JOIN invoice i
      ON i.customer_id = c.customer_id
      GROUP BY c.customer_id
      ORDER BY total_buys DESC
      LIMIT 1;
    */
    public String getHighestSpender();

    //9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context
    //means the genre that corresponds to the most tracks from invoices associated to that customer.
    /*SELECT t.genre_id, g.name, COUNT(*) as genre_count
        FROM customer AS c
        INNER JOIN invoice AS i ON c.customer_id = i.customer_id
        INNER JOIN invoice_line il ON i.invoice_id = il.invoice_id
        INNER JOIN track AS t ON il.track_id = t.track_id
        INNER JOIN genre AS g ON t.genre_id = g.genre_id
        WHERE c.customer_id = 8
        GROUP BY t.genre_id, g.name
        ORDER BY genre_count DESC
        LIMIT 1;
    * */
    public String getMostListenedGenreByCustomerId(int customer_id);
}
