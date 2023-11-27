/*
 * Created on 2014/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.batchwait;

import java.util.List;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.common.batchwait.BatchWaitNamesArray;

/**
 * 
 * バッチ待ちLogic
 * @author atts
 */
public interface BatchWaitLogic {

	/**
	 * 各種名称マスタを取得する。
	 * @param srhNameCd 名称コード
	 * @return Names 検索結果データ
	 */
	Names getNames(final String srhNameCd);

	/**
	 * 各種名称リストを取得する。
	 * @param menuId メニューID
	 * @param num 枝番
	 * @param nameDiv 名称区分
	 * @return List<BatchWaitNamesArray> 検索結果データリスト
	 */
	List<BatchWaitNamesArray> getBatchNames(final String menuId,
			final String num, final String nameDiv);

	/**
	 * バッチパラメータ情報を取得する。
	 * @param srhProcCd プロシージャ名
	 * @return ProcParam 検索結果データ
	 */
	ProcParam getProcParam(final String srhProcCd);
}
