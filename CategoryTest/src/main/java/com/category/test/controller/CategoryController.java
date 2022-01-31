package com.category.test.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.category.test.entitiy.Category;
import com.category.test.service.CategoryService;

/**
 * @apiNote Controller
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@RestController
public class CategoryController {

	@Resource
	CategoryService service;

	// 성공 메세지
	String success = "Done";

	/**
	 * @apiNote 카테고리 등록
	 * @param vo
	 * @return Category
	 * @throws Exception
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<?> insert(@RequestBody Category vo) throws Exception {
		service.insert(vo);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	/**
	 * @apiNote 카테고리 검색
	 * @return List<Category>
	 * @throws Exception
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<?> lowcategory(@RequestBody Category vo) throws Exception {
		return new ResponseEntity<>(service.search(vo), HttpStatus.OK);
	}

	/**
	 * @apiNote 카테고리 수정
	 * @param vo
	 * @return Category
	 * @throws Exception
	 */
	@GetMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody Category vo) throws Exception {
		service.update(vo);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

	/**
	 * @apiNote 카테고리 삭제
	 * @param vo
	 * @return Category
	 * @throws Exception
	 */
	@GetMapping(value = "/delete")
	public ResponseEntity<?> delete(@RequestBody Category vo) throws Exception {
		service.delete(vo);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
}
