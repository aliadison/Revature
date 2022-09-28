package com.revature.services;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import com.revature.utils.BucketUtil;

@Service
public class ProductService {

	@Autowired
    private final ProductRepository productRepository;
    
    @Autowired
    private BucketUtil bucketUtil;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
    	return productRepository.saveAll(productList);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

	public String updatePicture(int id, InputStream in, HttpServletRequest request) {
		Optional<Product> p=this.productRepository.findById(id);
		if(p==null)return "product-does-not-exist";
		String key="key"+id+".png";
		Product product = p.get();
		bucketUtil.uploadInputStream(in, key);
		product.setImage(key);
		save(product);
		return "success";
	}
}
