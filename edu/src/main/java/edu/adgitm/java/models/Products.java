package edu.adgitm.java.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	
	private Long productId;
	private String productName;
	private String productDescription;
	private Float productPrice;
	private Float productWeight;
	private Date createdDate;
	private Date expiryDate;
	private Integer quantity;

	public boolean setProductPrice(float price) {
		if (price > 0) {
			this.productPrice = price;
			return true;
		}
		return false;
	}


	public boolean setProductWeight(float weight) {
		if (weight > 0) {
			this.productWeight = weight;
			return true;
		}
		return false;
	}

}
