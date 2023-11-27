/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import com.asahikaseieng.dao.entity.master.area.Area;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 地区詳細ロジック interface.
 * @author t0011036
 */
public interface AreaDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param areaCd 地区コード
	 * @throws NoDataException NoDataException
	 * @return Area
	 */
	Area getEntity(final String areaCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param areaCd 地区コード
	 * @return AreaDetail
	 */
	AreaDetail getDetailEntity(final String areaCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Area bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Area bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Area bean) throws NoDataException;
}
