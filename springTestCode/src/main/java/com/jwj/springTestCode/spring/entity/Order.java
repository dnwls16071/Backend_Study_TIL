package com.jwj.springTestCode.spring.entity;

import com.jwj.springTestCode.spring.BaseEntity;
import com.jwj.springTestCode.spring.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
@Getter
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private int totalPrice;

	private LocalDateTime registeredDateTime;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();

	public Order(List<Product> products, LocalDateTime registeredDateTime) {
		this.orderStatus = OrderStatus.INIT;
		this.totalPrice = calculateTotalPrice(products);
		this.registeredDateTime = registeredDateTime;
		this.orderProducts = products.stream()
				.map(product -> new OrderProduct(this, product))
				.collect(Collectors.toList());
	}

	public static Order createOrder(List<Product> products, LocalDateTime registeredDateTime) {
		return new Order(products, registeredDateTime);
	}

	private int calculateTotalPrice(List<Product> products) {
		return products.stream()
				.mapToInt(Product::getPrice)
				.sum();
	}
}