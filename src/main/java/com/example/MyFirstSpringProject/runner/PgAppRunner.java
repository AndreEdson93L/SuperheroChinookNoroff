package com.example.MyFirstSpringProject.runner;

import com.example.MyFirstSpringProject.repositories.CustomerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerRepositoryImpl customerRepositoryImpl;

    @Autowired
    public PgAppRunner(CustomerRepositoryImpl customerRepositoryImpl) {
        this.customerRepositoryImpl = customerRepositoryImpl;
    }

    //Testing the methods
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //APPENDIX B
        //System.out.println("Hello World Runner!");
        //test connection
        //customerRepositoryImpl.testConnection();


        //1)
        //customerRepositoryImpl.readCustomerInfo();

        //2)
        //var customer = customerRepositoryImpl.getById(34).first_name();
        //System.out.println(customer);

        //3)
        //var customerByName = customerRepositoryImpl.getSpecificCustomerByName("ele");
        //var customer_id = customerByName.customer_id();
        //customerRepositoryImpl.printCustomerInfoById(customer_id);

        //4)
        //List<Customer> customerList = customerRepositoryImpl.getSetOfCustomers(10, 4);
        //for (Customer customer : customerList) System.out.println(customer);

        //5)
        //int last_customer_id = customerRepositoryImpl.getLastCustomerId();
        //System.out.println(last_customer_id);
        //customerRepositoryImpl.insert(new Customer(last_customer_id,"Andrea","Edson Lorenzoni","Italy","10134","+351 987 321 941","email@example.com"));

        //6)
        //customerRepositoryImpl.update(new Customer(4,"Pedro","Ferreira","Portugal","21345", "+43 123 142 123","pedroFerreiraEE@gmail.com"));
        //System.out.println(customerRepositoryImpl.getById(4));

        //7)
        //System.out.println(customerRepositoryImpl.getCountryWithMostCustomers());

        //8)
        //System.out.println(customerRepositoryImpl.getHighestSpender());

        //9)
        //System.out.println(customerRepositoryImpl.getMostListenedGenreByCustomerId(3));
    }
}