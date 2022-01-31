package com.category.test.service;

import java.util.List;

import com.category.test.entitiy.Category;
import com.category.test.entitiy.CategoryResult;

/**
 * @apiNote 카테고리 Service 인터페이스
 * @since 2022-01-31
 * @author Lee Won Hee
 */
public interface CategoryService {

	/**
	 * @apiNote 카테고리 수정
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category update(Category vo) throws Exception;

	/**
	 * @apiNote 카테고리 삭제
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category delete(Category vo) throws Exception;

	/**
	 * @apiNote 카테고리 등록
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category insert(Category vo) throws Exception;

	/**
	 * @apiNote 카테고리 검색
	 * @return List<CategoryResult>
	 * @param Category
	 * @throws Exception
	 */
	public List<CategoryResult> search(Category vo) throws Exception;
}
