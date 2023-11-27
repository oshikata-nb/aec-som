/*
 * Created on 2009/03/26
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.materialrinput;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputListDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputListPagerCondition;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListConditionForReport;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListForReport;
import com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 外注原材料投入実績一覧 ロジック実装クラス
 * @author tosco
 */
public class MaterialRinputListLogicImpl implements MaterialRinputListLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 外注原材料投入実績一覧画面用Dao */
	private MaterialRinputListDao materialRinputListDao;

	/** 帳票Excel用Dao */
	private MaterialRinputListForReportDao materialRinputListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputListLogicImpl() {
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<MaterialRinputList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<MaterialRinputList> getSearchList(
			final MaterialRinputListPagerCondition condition)
			throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;

		checkParams(condition);
		List<MaterialRinputList> list = materialRinputListDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (MaterialRinputList bean : list) {
			// 数量
			strOrderQuantity = checkDigitUtilsLogic.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = checkDigitUtilsLogic.format(
				UNIT_DIVISION_KG, VENDER_DIV_SI, bean.getVenderCd(), bean
						.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

		}

		return list;
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<MaterialRinputListForReport> 検索結果リスト
	 */
	public List<MaterialRinputListForReport> getReportList(
			final MaterialRinputListConditionForReport condition) {

		List<MaterialRinputListForReport> list = materialRinputListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final MaterialRinputListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 外注原材料投入実績一覧画面用Daoを設定します。
	 * @param materialRinputListDao 外注原材料投入実績一覧画面用Dao
	 */
	public void setMaterialRinputListDao(
			final MaterialRinputListDao materialRinputListDao) {
		this.materialRinputListDao = materialRinputListDao;
	}

	/**
	 * materialRinputListForReportDaoを設定します。
	 * @param materialRinputListForReportDao materialRinputListForReportDao
	 */
	public void setMaterialRinputListForReportDao(
			final MaterialRinputListForReportDao materialRinputListForReportDao) {
		this.materialRinputListForReportDao = materialRinputListForReportDao;
	}

}
