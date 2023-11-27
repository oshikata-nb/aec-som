/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－製造指図明細ポップアップ画面ロジック 実装クラス.
 * @author tosco
 */
public class PkgDirectionDirectionDetailSearchLogicImpl implements PkgDirectionDirectionDetailSearchLogic {

	/** 包装指図－製造指図詳細ポップアップ画面Dao */
	private PkgDirectionDirectionDetailSearchListDao pkgDirectionDirectionDetailSearchDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionDetailSearchLogicImpl() {
	}

	/**
	 * 製造指図詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionDirectionDetailSearch> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgDirectionDirectionDetailSearchList> getSearchList
		(final PkgDirectionDirectionDetailSearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<PkgDirectionDirectionDetailSearchList> list
			= pkgDirectionDirectionDetailSearchDao.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PkgDirectionDirectionDetailSearchList bean : list) {

			// 予定生産量フォーマット編集
			String strPlanedQty = checkDigitUtilsLogic.format(bean.getUnitDiv(), bean.getPlanedQty());
			bean.setStrPlanedQty(strPlanedQty);

			// 実績生産量フォーマット編集
			String strResultQtyQty = checkDigitUtilsLogic.format(bean.getUnitDiv(), bean.getResultQty());
			bean.setStrResultQty(strResultQtyQty);
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final PkgDirectionDirectionDetailSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－製造指図詳細ポップアップ画面Dao設定.
	 * @param pkgDirectionDirectionDetailSearchDao 包装指図－製造指図詳細ポップアップ画面Dao
	 */
	public void setPkgDirectionDirectionDetailSearchDao
		(final PkgDirectionDirectionDetailSearchListDao pkgDirectionDirectionDetailSearchDao) {
		this.pkgDirectionDirectionDetailSearchDao = pkgDirectionDirectionDetailSearchDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

}
