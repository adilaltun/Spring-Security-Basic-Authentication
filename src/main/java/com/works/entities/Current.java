package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity
public class Current extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @NotBlank(message = "Current name cannot be blank.")
    @Length(min = 2 , max = 75 ,message = "Current name must be minimum 2 and maximum 75 character.")
    private String name;

    @NotBlank(message = "Current detail cannot be blank.")
    @Length(min = 2 , max = 250 ,message = "Current detail must be minimum 2 and maximum 250 character.")
    private String detail;

    @Min(value = 1,message = "Amount must be greater than zero.")
    private Double amount;

}
