package com.demo.redis.repository;

import java.util.List;

import com.demo.redis.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	private static final String HASH_KEY = "Product";

	public Product saveProduct(Product product) {
		redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}

	public List<Object> findAllProducts() {
		return redisTemplate.opsForHash().values(HASH_KEY);
	}

	public Object findProductById(int id) {
		System.out.println("called findProductById() from DB");
		return redisTemplate.opsForHash().get(HASH_KEY, id);
	}

	public Boolean deleteProduct(int id) {
		redisTemplate.opsForHash().delete(HASH_KEY, id);
		return true;
	}

}
