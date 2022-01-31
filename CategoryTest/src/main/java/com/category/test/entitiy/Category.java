package com.category.test.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @apiNote 카테고리 VO
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String category;
	private Integer rownums;
	@Column(name = "parent_id")
	private Integer parentid;

	public Category(Integer id, String category, Integer rownums, Integer parentid) {
		this.id = id;
		this.category = category;
		this.rownums = rownums;
		this.parentid = parentid;
	}

	public Category(String category, Integer rownums, Integer parentid) {
		this.category = category;
		this.rownums = rownums;
		this.parentid = parentid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public void setRownums(Integer rownum) {
		this.rownums = rownum;
	}
}
