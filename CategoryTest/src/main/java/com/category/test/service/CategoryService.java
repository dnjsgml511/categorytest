package com.category.test.service;

import java.util.List;

import com.category.test.entitiy.Category;
import com.category.test.entitiy.CategoryResult;

/**
 * @apiNote ī�װ� Service �������̽�
 * @since 2022-01-31
 * @author Lee Won Hee
 */
public interface CategoryService {

	/**
	 * @apiNote ī�װ� ����
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category update(Category vo) throws Exception;

	/**
	 * @apiNote ī�װ� ����
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category delete(Category vo) throws Exception;

	/**
	 * @apiNote ī�װ� ���
	 * @return Category
	 * @param Category
	 * @throws Exception
	 */
	public Category insert(Category vo) throws Exception;

	/**
	 * @apiNote ī�װ� �˻�
	 * @return List<CategoryResult>
	 * @param Category
	 * @throws Exception
	 */
	public List<CategoryResult> search(Category vo) throws Exception;
}
