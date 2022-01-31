package com.cagegory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import com.category.test.CategoryTestApplication;
import com.category.test.entitiy.Category;
import com.category.test.repository.CategoryRepository;
import com.category.test.service.CategoryService;

/**
 * @apiNote 카테고리 수정 유닛테스트
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@SpringBootTest
@ContextConfiguration(classes = CategoryTestApplication.class)
@Sql({ "./sql/Setting.sql" })
class Update extends DefaultDataSetting {

	@Autowired
	@Mock
	CategoryService service;
	@Autowired
	@Mock
	CategoryRepository repository;

	/**
	 * @apiNote 카테고리만 파라메터 있을 경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("Update only Category Success TEST")
	public void updateOnlyCategory() throws Exception {
		int target = 7;

		// given
		Category vo = new Category(target, "update category test", null, null);

		// when
		service.update(vo);

		// then
		Category search = repository.findById(target).get();
		assertEquals(search.getCategory(), "update category test");
		assertEquals((int) search.getId(), target);
	}

	/**
	 * @apiNote rownum 파라메터 있고 parentid 파라메터 없을경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("Update Rownums Success TEST")
	public void updateRownum() throws Exception {
		int target = 2;

		// given
		Category vo = new Category(target, "update rownum test", 2, null);

		// when
		service.update(vo);

		// then
		Category search = repository.findById(target).get();
		assertEquals(search.getCategory(), "update category test");
		assertEquals((int) search.getId(), target);
	}

	/**
	 * @apiNote parentid 파라메터 있고 rownum 파라메터 없을경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("Update Parent Success TEST")
	public void updateParentid() throws Exception {
		int target = 5;

		// given
		Category vo = new Category(target, "update parent test", null, 3);

		// when
		service.update(vo);

		// then
		Category search = repository.findById(target).get();
		assertEquals(search.getCategory(), "update category test");
		assertEquals((int) search.getId(), target);
	}

}
