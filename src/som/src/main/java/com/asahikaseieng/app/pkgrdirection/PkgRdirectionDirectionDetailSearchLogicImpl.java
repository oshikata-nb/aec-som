/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－製造指図明細ポップアップ画面ロジック 実装クラス.
 * @author tosco
 */
public class PkgRdirectionDirectionDetailSearchLogicImpl implements PkgRdirectionDirectionDetailSearchLogic {

	/** 包装指図－製造指図詳細ポップアップ画面Dao */
	private PkgRdirectionDirectionDetailSearchListDao pkgRdirectionDirectionDetailSearchDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionDirectionDetailSearchLogicImpl() {
	}

	/**
	 * 製造指図詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDirectionDetailSearch> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgRdirectionDirectionDetailSearchList> getSearchList
		(final PkgRdirectionDirectionDetailSearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<PkgRdirectionDirectionDetailSearchList> list
			= pkgRdirectionDirectionDetailSearchDao.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PkgRdirectionDirectionDetailSearchList bean : list) {

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
	private void checkParams(final PkgRdirectionDirectionDetailSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－製造指図詳細ポップアップ画面Dao設定.
	 * @param pkgRdirectionDirectionDetailSearchDao 包装指図－製造指図詳細ポップアップ画面Dao
	 */
	public void setPkgRdirectionDirectionDetailSearchDao
		(final PkgRdirectionDirectionDetailSearchListDao pkgRdirectionDirectionDetailSearchDao) {
		this.pkgRdirectionDirectionDetailSearchDao = pkgRdirectionDirectionDetailSearchDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

}
