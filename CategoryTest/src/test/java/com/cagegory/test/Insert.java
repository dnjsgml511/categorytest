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
 * @apiNote 카테고리 등록 유닛테스트
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@SpringBootTest
@ContextConfiguration(classes = CategoryTestApplication.class)
@Sql({ "./sql/Setting.sql" })
class Insert extends DefaultDataSetting {

	@Autowired
	@Mock
	CategoryService service;
	@Autowired
	@Mock
	CategoryRepository repository;

	/**
	 * @apiNote 카테고리 이름 없을경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("No categoryname Insert TEST")
	public void categorynameInsertFail() throws Exception {
		// given
		Category vo = new Category(null, 1, 1);

		// when
		String message = "";
		try {
			service.insert(vo);
		} catch (Exception e) {
			message = e.getMessage();
		}

		// then
		assertEquals(message, "There's no category");
	}

	/**
	 * @apiNote 카테고리 부모 없을경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("No Parent Insert TEST")
	public void parentInsertFail() throws Exception {
		// given
		Category vo = new Category("insert test", 1, null);

		// when
		String message = "";
		try {
			service.insert(vo);
		} catch (Exception e) {
			message = e.getMessage();
		}

		// then
		assertEquals(message, "There's no parentid");
	}

	/**
	 * @apiNote 카테고리 부모 없을경우
	 * @throws Exception
	 */
	@Test
	@DisplayName("Insert Success TEST")
	public void insertSuccess() throws Exception {
		// given
		Category vo = new Category("insert test", 1, 1);

		// when
		Category insert = service.insert(vo);

		// then
		assertEquals(insert.getCategory(), "insert test");
		assertEquals((int) insert.getId(), 10);
		assertEquals((int) insert.getRownums(), 1);
		assertEquals((int) insert.getParentid(), 1);
	}

}
