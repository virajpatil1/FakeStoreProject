package com.shoppingcart.productserviceshoppingcart.service;

import com.shoppingcart.productserviceshoppingcart.configs.RedisConfig;
import com.shoppingcart.productserviceshoppingcart.dtos.FakeStoreProductDto;
import com.shoppingcart.productserviceshoppingcart.dtos.ProductUpdateRequest;
import com.shoppingcart.productserviceshoppingcart.exceptions.ProductNotFoundException;
import com.shoppingcart.productserviceshoppingcart.models.Category;
import com.shoppingcart.productserviceshoppingcart.models.Product;
import com.shoppingcart.productserviceshoppingcart.thridpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;


@Primary
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements  ProductService{


    private FakeStoreClient fakeStoreClient;

    private RedisTemplate<Long,Object> redisTemplate;
    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient , RedisTemplate<Long,Object> redisTemplate) {
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        if(redisTemplate.opsForHash().get(id,"PRODUCT") != null){

            return (Product) redisTemplate.opsForHash().get(id,"PRODUCT");
        }

        Product product = getProductFromFakeStoreProductDto(fakeStoreClient.getProductById(id));
        redisTemplate.opsForHash().put(id,"PRODUCT",product);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new LinkedList<>();

        for (FakeStoreProductDto fake: fakeStoreClient.getAllProducts()) {

            productList.add(getProductFromFakeStoreProductDto(fake));
        }

        return productList;
    }

    @Override
    public Product deleteProductById(Long id) {

        return getProductFromFakeStoreProductDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {

        return getProductFromFakeStoreProductDto(fakeStoreClient.addProduct(getFakeStoreProductDtoFromProduct(product)));
    }

    @Override
    public Product updateProductById(Long id, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

//    @Override
//    public Product updateProductById(Long id) {
//
//        return getProductFromFakeStoreProductDto(fakeStoreClient.updateProductById(id));
//    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }
}
