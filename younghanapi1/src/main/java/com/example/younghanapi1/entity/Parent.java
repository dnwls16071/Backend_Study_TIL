package com.example.younghanapi1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Child> children = new ArrayList<>();

	public void addChild(Child child) {
		children.add(child);
		child.setParent(this);
	}
}
