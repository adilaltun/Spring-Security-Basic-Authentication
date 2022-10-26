package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Product extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @NotBlank(message = "Product title cannot be blank.")
    @Length(min = 2 , max = 75 ,message = "Product title must be minimum 2 and maximum 75 character.")
    private String title;

    @Min(value = 1,message = "Price greater than zero.")
    private Double price;

}
