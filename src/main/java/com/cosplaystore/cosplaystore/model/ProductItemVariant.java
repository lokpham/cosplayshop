package com.cosplaystore.cosplaystore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "product_item_variant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "value")
    private VariantValue variantValue;
    @ManyToOne
    @JoinColumn(name = "product_item_id")
    @JsonIgnore
    private ProductItem productItem;
}
