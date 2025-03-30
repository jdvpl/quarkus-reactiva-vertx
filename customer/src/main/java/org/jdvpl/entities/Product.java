package org.jdvpl.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"customer","product"})
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Customer customer;

    @Column
    private Long product;

    @Transient
    private String name;

    @Transient
    private String code;

    @Transient
    private String description;

}
