/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.line;

import com.asahikaseieng.dao.entity.master.line.Line;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産ライン詳細ロジック interface.
 * @author t0011036
 */
public interface LineDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param productionLine 生産ラインコード
	 * @throws NoDataException NoDataException
	 * @return Line
	 */
	Line getEntity(final String productionLine) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param productionLine 生産ラインコード
	 * @return LineDetail
	 */
	LineDetail getDetailEntity(final String productionLine);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Line bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Line bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Line bean) throws NoDataException;
}
