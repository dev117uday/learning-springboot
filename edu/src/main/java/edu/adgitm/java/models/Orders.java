package edu.adgitm.java.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	private Long orderId;
	private Long customerId;
	private String shippingAddress;
	private String orderEmail;
	private Date orderDate;
	private String orderStatus;
}
