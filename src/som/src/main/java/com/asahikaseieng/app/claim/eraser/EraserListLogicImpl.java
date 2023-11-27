/*
 * Created on 2008/08/07
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserListDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力一覧 ロジック実装クラス
 * @author tosco
 */
public class EraserListLogicImpl implements EraserListLogic {

	private ClaimEraserListDao eraserListDao;

	/**
	 * コンストラクタ.消込入力一覧
	 */
	public EraserListLogicImpl() {
	}

	/**
	 * 消込入力一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<EraserList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ClaimEraserList> getSearchList(final ClaimEraserListPagerCondition condition) throws NoDataException {
		checkParams(condition);
		List<ClaimEraserList> list = eraserListDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ClaimEraserListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserListDaoを設定します。
	 * @param eraserListDao EraserListDao
	 */
	public void setEraserListDao(final ClaimEraserListDao eraserListDao) {
		this.eraserListDao = eraserListDao;
	}

}
