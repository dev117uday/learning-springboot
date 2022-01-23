package com.demo.redis;

import java.util.List;

import com.demo.redis.entity.Product;
import com.demo.redis.repository.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class RedisApplication {

	@Autowired
	private ProductDao productDao;

	@GetMapping
	public Product getSample() {
		return new Product();
	}

	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productDao.saveProduct(product);
	}

	@GetMapping("/all")
	public List<Object> getAllProducts() {
		return productDao.findAllProducts();
	}

	@GetMapping("/{id}")
	@Cacheable( key = "#id", value = "Product", unless = "#result.price>1000" )
	public Object findProduct(@PathVariable int id) {
		return productDao.findProductById(id);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id", value = "Product")
	public Boolean removeProduct(@PathVariable int id) {
		return productDao.deleteProduct(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
