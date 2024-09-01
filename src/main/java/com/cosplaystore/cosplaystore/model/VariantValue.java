package com.cosplaystore.cosplaystore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "variant_value")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VariantValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;
}
