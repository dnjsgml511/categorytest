package com.category.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.category.test.entitiy.Category;
import com.category.test.entitiy.CategoryJoin;
import com.category.test.entitiy.CategoryResult;
import com.category.test.repository.CategoryJoinRepository;
import com.category.test.repository.CategoryRepository;
import com.category.test.util.Utils;

import lombok.RequiredArgsConstructor;

/**
 * @apiNote 카테고리 Service
 * @since 2022-01-31
 * @author Lee Won Hee
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private final CategoryRepository repository;
	@Autowired
	private final CategoryJoinRepository join;

	Utils utils = new Utils();

	// 카테고리 수정
	@Override
	@Transactional
	public Category update(Category vo) throws Exception {
		// id 체크
		utils.checkId(vo.getId());

		// 타겟
		Category target = repository.findById(vo.getId())
				.orElseThrow(() -> new IllegalArgumentException("Check the ID parameter"));
		// 부모 목록
		int beforeParent = target.getParentid() == null ? 0 : target.getParentid();
		int afterParent = vo.getParentid() == null ? beforeParent : vo.getParentid();

		// 움직일 부모 체크
		repository.findById(afterParent)
				.orElseThrow(() -> new IllegalArgumentException("Check the Parentid parameter"));

		// 형제 목록
		List<Category> beforeChidren = repository.findByParentid(beforeParent, Sort.by(Sort.Direction.ASC, "rownums"))
				.get();
		List<Category> afterChildren = repository.findByParentid(afterParent, Sort.by(Sort.Direction.ASC, "rownums"))
				.get();
		beforeChidren.remove(target);
		afterChildren.remove(target);

		// 카테고리 수정
		if (vo.getCategory() != null || vo.getCategory().equals("")) {
			target.setCategory(vo.getCategory());
		}

		// 부모 수정
		if (vo.getParentid() != null) {
			target.setParentid(vo.getParentid());
		}

		// rownum 수정
		if (vo.getRownums() == null && beforeChidren.containsAll(afterChildren)) {
			vo.setRownums(target.getRownums() - 1);
		} else if (vo.getRownums() != null) {
			int rownum = Math.min(vo.getRownums(), afterChildren.size() + 1);
			vo.setRownums(rownum - 1);
		} else {
			vo.setRownums(afterChildren.size());
		}
		beforeChidren = utils.setRownum(beforeChidren);
		afterChildren.add(vo.getRownums(), target);
		afterChildren = utils.setRownum(afterChildren);

		repository.saveAll(beforeChidren);
		repository.saveAll(afterChildren);

		return target;
	}

	// 카테고리 등록
	@Override
	@Transactional
	public Category insert(Category vo) throws Exception {

		// 부모id가 없을경우 throw exception
		if (vo.getParentid() == null) {
			throw new IllegalArgumentException("There's no parentid");
		}
		// 카테고리가 없거나 공란일 경우 throw exception
		if (vo.getCategory() == null || vo.getCategory().equals("")) {
			throw new IllegalArgumentException("There's no category");
		}

		// 부모가 있는지 확인
		boolean parentcheck = repository.findById(vo.getParentid()).isPresent();
		if (!parentcheck && (vo.getParentid() != 0)) {
			throw new IllegalArgumentException("There's no parent node");
		}

		// 형제 노드들 가져오기
		List<Category> rowdata;
		if (vo.getParentid() == 0) {
			rowdata = repository.findByParentidIsNull(Sort.by(Sort.Direction.DESC, "rownums")).get();
			vo.setParentid(null);
		} else {
			rowdata = repository.findByParentid(vo.getParentid(), Sort.by(Sort.Direction.ASC, "rownums")).get();
		}

		// rownum이 없을경우
		int rownum = vo.getRownums() == null ? rowdata.size() : vo.getRownums() - 1;
		// 형제노드에 추가하기
		rowdata.add(rownum, vo);
		rowdata = utils.setRownum(rowdata);

		// 저장
		List<Category> insert = repository.saveAll(rowdata);

		// 저장한 데이터 리턴
		return insert.get(vo.getRownums() - 1);
	}

	// 카테고리 검색
	@Override
	public List<CategoryResult> search(Category vo) throws Exception {
		List<CategoryJoin> list;

		if (vo.getId() == null) {
			// ID가 null일 경우 모두 가져오기
			list = join.findByParentIsNull(Sort.by(Sort.Direction.ASC, "rownums"));
		} else {
			// ID로 셀프조인하여 하위 목록 모두 가져오기, 만약 없을경우 throw exception
			CategoryJoin target = join.findById(vo.getId())
					.orElseThrow(() -> new IllegalArgumentException("Check the ID parameter"));
			// 목록의 가공을 위한 list형태로 변환
			list = new ArrayList<>();
			list.add(target);
		}

		// 목록의 가공
		return list.stream().map(CategoryResult::new).collect(Collectors.toList());
	}

	// 카테고리 삭제
	@Override
	@Transactional
	public Category delete(Category vo) throws Exception {
		// id 체크
		utils.checkId(vo.getId());
		// 해당 행 가져오기
		Category target = repository.findById(vo.getId())
				.orElseThrow(() -> new IllegalArgumentException("Check the ID parameter"));
		// 부모 id 가져오기
		Integer parentid = target.getParentid();
		// id값으로 행 지우기
		join.deleteById(vo.getId());
		// 형제노드 가져오기
		List<Category> children;
		if (parentid == null) {
			children = repository.findByParentidIsNull(Sort.by(Sort.Direction.ASC, "rownums"))
					.orElseThrow(() -> new IllegalArgumentException("There's no top node"));
		} else {
			children = repository.findByParentid(parentid, Sort.by(Sort.Direction.ASC, "rownums"))
					.orElseThrow(() -> new IllegalArgumentException("Check the parentid"));
		}
		// 형제노드 rownum 정렬
		utils.setRownum(children);

		return target;
	}
}
