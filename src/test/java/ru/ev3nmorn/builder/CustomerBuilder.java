package ru.ev3nmorn.builder;

import ru.ev3nmorn.model.Customer;

public class CustomerBuilder {

    private Customer customer;

    public CustomerBuilder() {
        customer = new Customer();
    }

    public CustomerBuilder defaultCustomer() {
        customer.setId(1);
        customer.setName("test");
        customer.setEmail("test@mail.ru");
        customer.setPhone("8-999-999-99-99");

        return this;
    }

    public CustomerBuilder withId(Integer id) {
        customer.setId(id);

        return this;
    }

    public CustomerBuilder withName(String name) {
        customer.setName(name);

        return this;
    }

    public CustomerBuilder withEmail(String email) {
        customer.setEmail(email);

        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        customer.setPhone(phone);

        return this;
    }

    public Customer build() {
        return customer;
    }

}
