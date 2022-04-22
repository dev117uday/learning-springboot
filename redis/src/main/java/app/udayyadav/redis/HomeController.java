package app.udayyadav.redis;

import app.udayyadav.redis.entity.Product;
import app.udayyadav.redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@EnableCaching
public class HomeController {

    @Autowired
    private ProductDao productDao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productDao.save(product);
    }

    @GetMapping
    public List<Object> findAll() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product", unless = "#result.price > 1000")
    public Product findById(@PathVariable Integer id) {
        return productDao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        productDao.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
