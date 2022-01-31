package com.category.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.category.test.entitiy.Category;

/**
 * @apiNote 카테고리 Repository
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Optional<List<Category>> findByParentidIsNull(Sort sort);

	public Optional<List<Category>> findByParentid(int pid, Sort sort);

}
