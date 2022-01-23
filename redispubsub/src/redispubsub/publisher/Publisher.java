package com.demo.redispubsub.publisher;

import com.demo.redispubsub.dto.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pub")
public class Publisher {
	
	@Autowired
	private RedisTemplate<String,Object> template;

	@Autowired
	private ChannelTopic topic;

	@GetMapping
	public Product getProduct() {
		return new Product();
	}

	@PostMapping 
	public ResponseEntity<Void> publish (@RequestBody Product product) {
		template.convertAndSend(topic.getTopic(), product.toString());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
