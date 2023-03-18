package demo;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

    public void checkout(String status) {
        System.out.println("checkout method of shopping cart called");
    }

    public int quantity() {
        return 2;
    }
}
