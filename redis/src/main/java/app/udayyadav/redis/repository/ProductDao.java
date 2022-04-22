package app.udayyadav.redis.repository;

import app.udayyadav.redis.entity.Product;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductDao {

    @Autowired
    private RedisTemplate<String, Object> template;
    private static final String HASH_KEY = "Product";

    public Product save(Product product) {
        Gson gson = new Gson();
        var productJson = gson.toJson(product);
        template.convertAndSend("dev117uday", productJson);
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Object> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id) {
        log.info("------ Called from DB ----------");
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public void deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
    }

}
