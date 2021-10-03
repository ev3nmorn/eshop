package ru.ev3nmorn.dto;

import ru.ev3nmorn.model.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDTO {

    private Integer id;

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Phone is mandatory")
    @Pattern(regexp = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "Phone is not valid")
    private String phone;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    public CustomerDTO(Integer id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
