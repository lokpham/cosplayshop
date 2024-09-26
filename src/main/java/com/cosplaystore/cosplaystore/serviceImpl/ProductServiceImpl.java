package com.cosplaystore.cosplaystore.serviceImpl;

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.request.RateRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.dto.response.ProductFullInforResponse;
import com.cosplaystore.cosplaystore.dto.response.ProductItemResponse;
import com.cosplaystore.cosplaystore.dto.response.ProductResponse;
import com.cosplaystore.cosplaystore.dto.response.RateDTO;
import com.cosplaystore.cosplaystore.dto.response.VariantDTO;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.ProductMapper;
import com.cosplaystore.cosplaystore.model.Catetory;
import com.cosplaystore.cosplaystore.model.Product;
import com.cosplaystore.cosplaystore.model.ProductItem;
import com.cosplaystore.cosplaystore.model.ProductItemVariant;
import com.cosplaystore.cosplaystore.model.ProductRate;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.model.Variant;
import com.cosplaystore.cosplaystore.repository.ProductRateRepo;
import com.cosplaystore.cosplaystore.repository.ProductRepo;
import com.cosplaystore.cosplaystore.repository.UserRepo;
import com.cosplaystore.cosplaystore.service.CatetoryService;
import com.cosplaystore.cosplaystore.service.ProductItemService;
import com.cosplaystore.cosplaystore.service.ProductService;

import lombok.experimental.var;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRateRepo productRateRepo;
    @Autowired
    CatetoryService catetoryService;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductItemService productItemService;

    @Override
    public List<ProductResponse> getAllProduct(int start, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.getAuthorities());
        }
        Pageable page = PageRequest.of((start - 1) * size, size);
        Page<Product> products = productRepo.findAll(page);
        List<ProductResponse> productResponses = new ArrayList<>();
        if (products.hasContent()) {
            for (Product product : products) {
                List<ProductItemResponse> productItems = productItemService
                        .getProductItemsViaProductId(product.getId());
                ProductItemResponse minProductItem = null;
                int min_price = productItems.get(0).getPrice();
                for (ProductItemResponse productItem : productItems) {
                    if (productItem.getPrice() <= min_price) {
                        minProductItem = productItem;
                        min_price = minProductItem.getPrice();
                    }
                }
                ProductResponse productResponse = productMapper.toProductResponse(product);
                productResponse.setPrice(minProductItem.getPrice());
                productResponse.setDiscount(minProductItem.getDiscount());
                RateDTO rateDTO = new RateDTO();
                Integer avg_rate = productRepo.getAverageRateRoundedDown(product.getId());
                if (avg_rate == null) {
                    avg_rate = 0;
                }
                rateDTO.setAvg_rate(avg_rate);
                rateDTO.setTotal(productRepo.getRateCount(product.getId()));
                productResponse.setRate(rateDTO);
                productResponses.add(productResponse);
            }
            return productResponses;
        } else {
            return null;
        }
    }

    @Override
    public ProductResponse updateProduct(int id, ProductRequest p) {
        Product product = getProductById(id);
        product.setDescription(p.getDescription());
        product.setName(p.getName());
        Catetory catetory = catetoryService.getCatetoryById(p.getCatetory_id());
        product.setCatetory(catetory);
        return productMapper.toProductResponse(productRepo.save(product));

    }

    @Override
    public void disableProduct(int id) {
        Product product = getProductById(id);
        product.setDisable(true);
        productRepo.save(product);
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new GeneralException(Message.PRODUCT_NOT_FOUND);
        }
    }

    @SuppressWarnings("unused")
    public ProductFullInforResponse getFullInfor(int id) {
        ProductFullInforResponse productFullInforResponse = new ProductFullInforResponse();
        Product product = getProductById(id);
        ProductResponse productResponse = productMapper.toProductResponse(product);
        RateDTO rateDTO = new RateDTO();
        Integer avg_rate = productRepo.getAverageRateRoundedDown(id);
        if (avg_rate == null) {
            rateDTO.setAvg_rate(0);
        } else {
            rateDTO.setAvg_rate(avg_rate);

        }

        rateDTO.setTotal(productRepo.getRateCount(id));
        productResponse.setRate(rateDTO);
        productFullInforResponse.setProductResponse(productResponse);
        List<ProductItemResponse> productItems = productItemService.getProductItemsViaProductId(id);

        List<Object[]> objects = productRepo.getProductVariant(id);
        List<VariantDTO> variantDTOs = new ArrayList<>();
        for (Object[] rs : objects) {
            VariantDTO variantDTO = new VariantDTO();
            variantDTO.setId(Integer.parseInt(rs[4].toString()));
            variantDTO.setName(rs[5].toString());
            variantDTO.setPriority(Integer.parseInt(rs[3].toString()));
            variantDTOs.add(variantDTO);
        }
        for (VariantDTO variantDTO : variantDTOs) {
            List<String> values = new ArrayList<>();

            List<Object[]> listVariantValue = productRepo.getProductVariantValue(variantDTO.getId(), id);
            for (Object[] value : listVariantValue) {
                values.add(value[0].toString());
            }
            variantDTO.setValues(values);
        }
        productFullInforResponse.setVariants(variantDTOs);
        productFullInforResponse.setProduct_items(productItems);
        return productFullInforResponse;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Catetory catetory = catetoryService.getCatetoryById(productRequest.getCatetory_id());
        product.setCatetory(catetory);
        return productMapper.toProductResponse(productRepo.save(product));

    }

    @Override
    public void unDisableProduct(int id) {
        Product product = getProductById(id);
        product.setDisable(false);
        productRepo.save(product);
    }

    @Override
    public ProductResponse getProduct(int id) {
        Product product = getProductById(id);
        return productMapper.toProductResponse(product);
    }

    @Override
    public void rateProduct(RateRequest rateRequest) {
        Optional<ProductRate> proOptional = productRateRepo.findByUserIdAndProductId(rateRequest.getUser_id(),
                rateRequest.getProduct_id());
        if (proOptional.isPresent()) {
            ProductRate productRate = proOptional.get();
            productRate.setValue(rateRequest.getValue());
            productRateRepo.save(productRate);
        } else {
            ProductRate productRate = new ProductRate();
            productRate.setValue(rateRequest.getValue());

            Product product = getProductById(rateRequest.getProduct_id());
            productRate.setProduct(product);

            Optional<User> userOptional = userRepo.findById(rateRequest.getUser_id());
            if (userOptional.isPresent()) {
                productRate.setUser(userOptional.get());
            } else {
                throw new GeneralException(Message.USERNAME_NOTFOUND);
            }
            productRateRepo.save(productRate);

        }
    }

}
