package com.cagegory.test;

import java.util.ArrayList;
import java.util.List;

import com.category.test.entitiy.CategoryJoin;
import com.category.test.entitiy.CategoryResult;

/**
 * @apiNote 유닛테스트를 위한 기본 셋팅
 * @since 2022-01-31
 * @author Lee Won Hee
 */

public class DefaultDataSetting {

	public List<CategoryResult> defaultdata() throws Exception {
		List<CategoryJoin> list = new ArrayList<CategoryJoin>();
		CategoryJoin vo7 = new CategoryJoin(7, "data7", 1, null);
		CategoryJoin vo8 = new CategoryJoin(8, "data8", 2, null);
		CategoryJoin vo9 = new CategoryJoin(9, "data9", 3, null);
		list.add(vo7);
		list.add(vo8);
		list.add(vo9);
		CategoryJoin vo3 = new CategoryJoin(3, "data3", 2, null);
		vo3.setChildren(list);

		list = new ArrayList<CategoryJoin>();
		CategoryJoin vo4 = new CategoryJoin(4, "data4", 1, null);
		CategoryJoin vo5 = new CategoryJoin(5, "data5", 2, null);
		CategoryJoin vo6 = new CategoryJoin(6, "data6", 3, null);
		list.add(vo4);
		list.add(vo5);
		list.add(vo6);
		CategoryJoin vo2 = new CategoryJoin(2, "data2", 1, null);
		vo2.setChildren(list);

		list = new ArrayList<CategoryJoin>();
		CategoryJoin vo1 = new CategoryJoin(1, "data1", 1, null);
		list.add(vo2);
		list.add(vo3);
		vo1.setChildren(list);

		CategoryResult res = new CategoryResult(vo1);
		List<CategoryResult> reslist = new ArrayList<CategoryResult>();
		reslist.add(res);

		return reslist;
	}

}
