package com.category.test.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.category.test.entitiy.CategoryJoin;

/**
 * @apiNote 카테고리 Join Repository
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Repository
public interface CategoryJoinRepository extends JpaRepository<CategoryJoin, Integer> {
	public List<CategoryJoin> findByParentIsNull(Sort sort);
}
