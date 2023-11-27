/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import com.asahikaseieng.dao.entity.master.component.Component;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 成分詳細ロジック interface.
 * @author t0011036
 */
public interface ComponentDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param componentCd 成分コード
	 * @throws NoDataException NoDataException
	 * @return Component
	 */
	Component getEntity(final String componentCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param componentCd 成分コード
	 * @return ComponentDetail
	 */
	ComponentDetail getDetailEntity(final String componentCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Component bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Component bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Component bean) throws NoDataException;
}
