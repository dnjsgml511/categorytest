package com.cagegory.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * @apiNote 카테고리 삭제 유닛테스트
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@SpringBootTest
@ContextConfiguration(classes = CategoryTestApplication.class)
@Sql({ "./sql/Setting.sql" })
class Delete extends DefaultDataSetting {

	@Autowired
	@Mock
	CategoryService service;
	@Autowired
	@Mock
	CategoryRepository repository;

	/**
	 * @apiNote 카테고리 삭제 성공시
	 * @throws Exception
	 */
	@Test
	@DisplayName("Delete Success TEST")
	public void deleteSuccess() throws Exception {
		// given
		Category vo = new Category(3, null, null, null);

		// when
		service.delete(vo);

		// then
		List<CategoryResult> reslist = defaultdata();
		reslist.get(0).getChildren().remove(1);
		Category searchvo = new Category(null, null, null);
		List<CategoryResult> search = service.search(searchvo);

		assertEquals(search.toString(), reslist.toString());
	}

	/**
	 * @apiNote 카테고리 삭제 실패시
	 * @throws Exception
	 */
	@Test
	@DisplayName("Delete Fail TEST")
	public void deleteFail() throws Exception {
		// given
		Category vo = new Category(100, null, null, null);

		// when
		String message = "";
		try {
			service.delete(vo);
		} catch (Exception e) {
			message = e.getMessage();
		}

		// then
		assertEquals(message, "Check the ID parameter");
	}
}
