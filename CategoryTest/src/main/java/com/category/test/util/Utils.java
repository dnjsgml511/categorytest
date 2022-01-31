package com.category.test.util;

import java.util.List;

import com.category.test.entitiy.Category;

/**
 * @apiNote ��ƿ���
 * @since 2022-01-31
 * @author Lee Won Hee
 */

public class Utils {

	/**
	 * @apiNote rownum ������� �ٽ� ����
	 * @param list
	 * @return List<Category>
	 * @throws Exception
	 */
	public List<Category> setRownum(List<Category> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setRownums(i + 1);
		}
		return list;
	}

	/**
	 * @apiNote ID null üũ
	 * @param Integer
	 * @throws Exception
	 */
	public void checkId(Integer id) throws Exception {
		if (id == null) {
			throw new IllegalArgumentException("Check the ID parameter");
		}
	}
}
