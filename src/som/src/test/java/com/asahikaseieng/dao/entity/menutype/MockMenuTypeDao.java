/*
 * Created on 2007/04/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menutype;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asahikaseieng.Constants;

/**
 * MenuTypeDaoのMockクラス
 * @author jbd
 */
public class MockMenuTypeDao implements MenuTypeDao {

	/**
	 * コンストラクタ
	 */
	public MockMenuTypeDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(final MenuType bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(final MenuType bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final MenuType bean) {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	public MenuType getEntity(final BigDecimal id) {
		if (Constants.TEST_PARAMETER_NODATA.equals(id)) {
			return null;
		}
		return createBean(1);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<MenuType> getList() {
		List<MenuType> list = new ArrayList<MenuType>();
		for (int i = 1; i <= 10; i++) {
			list.add(createBean(i));
		}
		return list;
	}

	/**
	 * MenuType生成
	 * @param index インデックス
	 * @return MenuType
	 */
	private MenuType createBean(final int index) {
		MenuType bean = new MenuType();
		bean.setMenuTypeId(new BigDecimal(index + 1));
		bean.setMenuTypeName("ユーザー名" + index);
		bean.setImgName("image" + index);
		bean.setOpenImgName("ImgOpen" + index);
		bean.setCloseImgName("ImgClose" + index);
		bean.setFileKbn("" + ((index + 1) % 2));
		bean.setRegistedAt(new Timestamp(new Date().getTime()));
		bean.setUpdatedAt(new Timestamp(new Date().getTime()));
		return bean;
	}
}
