package com.cagegory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import com.category.test.CategoryTestApplication;
import com.category.test.entitiy.Category;
import com.category.test.entitiy.CategoryResult;
import com.category.test.repository.CategoryRepository;
import com.category.test.service.CategoryService;

/**
 * @apiNote ī�װ� �˻� �����׽�Ʈ
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@SpringBootTest
@ContextConfiguration(classes = CategoryTestApplication.class)
@Sql({ "./sql/Setting.sql" })
class Search extends DefaultDataSetting {

	@Mock
	@Autowired
	CategoryService service;
	@Mock
	@Autowired
	CategoryRepository repository;

	/**
	 * @apiNote ���� ī�װ� �������� �ʾ��� ���
	 * @throws Exception
	 */
	@Test
	@DisplayName("Search all TEST")
	public void search() throws Exception {
		// given
		// ID �������
		Category vo = new Category(null, null, null);

		// when
		final List<CategoryResult> test = service.search(vo);

		// then
		List<CategoryResult> reslist = defaultdata();
		assertEquals(test.toString(), reslist.toString());
	}

	/**
	 * @apiNote ���� ī�װ� �������� ���
	 * @throws Exception
	 */
	@Test
	@DisplayName("Search ID TEST")
	public void searchID() throws Exception {
		// given
		// ID : 2
		Category vo = new Category(2, null, null, null);

		// when
		final List<CategoryResult> test = service.search(vo);

		// then
		CategoryResult res = defaultdata().get(0).getChildren().get(0);
		List<CategoryResult> reslist = new ArrayList<>();
		reslist.add(res);
		assertEquals(test.toString(), reslist.toString());
	}

}
