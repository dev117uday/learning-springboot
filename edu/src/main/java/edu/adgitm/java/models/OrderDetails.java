package edu.adgitm.java.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	private Long productId;
	private Long orderId;
	private Float productPrice;
	private Integer quantity;
	private Float totalPrice;

}
