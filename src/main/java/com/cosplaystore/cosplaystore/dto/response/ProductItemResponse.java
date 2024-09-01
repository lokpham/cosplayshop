package com.cosplaystore.cosplaystore.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cosplaystore.cosplaystore.model.Product;
import com.cosplaystore.cosplaystore.model.ProductItemVariant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductItemResponse {
    @Id
    private int id;
    private String sku;
    private String image;

    private int price;
    private int stock;
    private int discount;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private List<String> variants;
}
