/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－在庫確認ポップアップ画面ロジック 実装クラス.
 * @author tosco
 */
public class PkgDirectionLotInventorySearchLogicImpl implements PkgDirectionLotInventorySearchLogic {

	/** 包装指図－在庫確認ポップアップ画面Dao */
	private PkgDirectionLotInventorySearchListDao pkgDirectionLotInventorySearchListDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionLotInventorySearchLogicImpl() {
	}

	/**
	 * 在庫確認一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionLotInventorySearchList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgDirectionLotInventorySearchList> getSearchList
		(final PkgDirectionLotInventorySearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<PkgDirectionLotInventorySearchList> list
			= pkgDirectionLotInventorySearchListDao.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PkgDirectionLotInventorySearchList bean : list) {

			// 在庫数量フォーマット編集
			String strQty = checkDigitUtilsLogic.format(bean.getUnitDiv(), bean.getInventoryQty());
			bean.setStrInventoryQty(strQty);
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final PkgDirectionLotInventorySearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－在庫確認ポップアップ画面Dao設定.
	 * @param pkgDirectionLotInventorySearchListDao 包装指図－在庫確認ポップアップ画面Dao
	 */
	public void setPkgDirectionLotInventorySearchListDao
		(final PkgDirectionLotInventorySearchListDao pkgDirectionLotInventorySearchListDao) {
		this.pkgDirectionLotInventorySearchListDao = pkgDirectionLotInventorySearchListDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

}
