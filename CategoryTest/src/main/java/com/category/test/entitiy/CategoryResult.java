package com.category.test.entitiy;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

/**
 * @apiNote 카테고리 Join 결과 매핑
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Getter
@ToString
public class CategoryResult {
	private int id;
	private String category;
	private int rownums;
	private List<CategoryResult> children;

	public CategoryResult(final CategoryJoin category) {
		this.id = category.getId();
		this.category = category.getCategory();
		this.rownums = category.getRownums();
		this.children = category.getChildren().stream().map(CategoryResult::new).collect(Collectors.toList());
	}
}
