package de.hska.iwi.microservice.authentication.adaptor;

import de.hska.iwi.microservice.authentication.domian.CustomerDAO;
import de.hska.iwi.microservice.authentication.entity.Customer;
import de.hska.iwi.microservice.authentication.entity.Customer.Permission;
import org.apache.log4j.Logger;

/**
 * Created by ameo on 18.01.17.
 */
public class CustomerAdapter {

  private final Logger logger = Logger.getLogger(CustomerAdapter.class);

  public CustomerAdapter() {
    super();
  }

  public CustomerDAO convertCustomerToCustomerDAO(Customer customer) {
    if (customer instanceof Customer) {
      return new CustomerDAO(customer.getId(), customer.getName(), customer.getLastname(),
          customer.getUsername(), customer.getPassword(), customer.getRole().getValue());
    }
    return null;
  }

  public Customer convertCustomerDAOToCustomer(CustomerDAO customerDAO) {
    if (customerDAO instanceof CustomerDAO) {
      return new Customer(customerDAO.getId(), customerDAO.getName(), customerDAO.getLastname(),
          customerDAO.getUsername(), customerDAO.getPassword(),
          customerDAO.getRole() == 1 ? Permission.Admin : Permission.User);
    }
    return null;
  }
}
