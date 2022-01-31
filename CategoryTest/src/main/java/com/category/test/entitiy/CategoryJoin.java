package com.category.test.entitiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @apiNote 카테고리 Join
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Getter
@Setter
@Entity
@Table(name = "Category")
public class CategoryJoin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private CategoryJoin parent;

	private String category;
	private int rownums;

	@OrderBy("rownums asc")
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<CategoryJoin> children = new ArrayList<>();

	public CategoryJoin() {
	}

	public CategoryJoin(int id) {
		this.id = id;
	}

	public CategoryJoin(CategoryJoin parent) {
		this.parent = parent;
	}

	public CategoryJoin(String category, int rownum, CategoryJoin categoryJoin) {
		this.category = category;
		this.rownums = rownum;
		this.parent = categoryJoin;
	}

	public CategoryJoin(int id, String category, int rownum, CategoryJoin categoryJoin) {
		this.id = id;
		this.category = category;
		this.rownums = rownum;
		this.parent = categoryJoin;
	}

}
