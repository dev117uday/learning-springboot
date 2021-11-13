package edu.adgitm.java.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
	private Long customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	private String customerEmail;
}
