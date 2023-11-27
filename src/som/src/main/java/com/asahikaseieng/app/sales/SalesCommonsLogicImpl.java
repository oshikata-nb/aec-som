/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.buying.BuyingDetailAction;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesCategoryDivisionForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesCategoryDivisionForComboboxesDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売上 共通ロジック実装クラス
 * @author tosco
 */
public class SalesCommonsLogicImpl implements SalesCommonsLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 売上－売上区分コンボボックス用DAO */
	private SalesCategoryDivisionForComboboxesDao salesCategoryForComboboxesDao;

	private VenderDao venderDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/**
	 * コンストラクタ
	 */
	public SalesCommonsLogicImpl() {
	}

	/**
	 * 得意先コードから請求先コードを取得
	 * @param venderCd 得意先コード
	 * @return venderCd venderCd
	 */
	public BigDecimal getArDivision(final String venderCd) {

		Vender vender = venderDao.getEntity(venderCd, "TS");

		if (vender != null) {

			Vender invoice = venderDao.getEntity(venderCd, "TS");

			if (invoice != null) {
				return getDivision(invoice.getAdvanceDivision());
			} else {
				// 請求先コードで前受け金区分が存在しなかった場合
				return getDivision(vender.getAdvanceDivision());
			}
		} else {
			// 得意先マスタに存在しなかった場合
			return BigDecimal.ONE;
		}
	}

	/**
	 * 得意先マスタの前受金区分→分類マスタの前受け金区分に変換
	 * @param advanceDivision advanceDivision
	 * @return arDivision arDivision
	 */
	private BigDecimal getDivision(final BigDecimal advanceDivision) {

		if (advanceDivision == null) {
			return BigDecimal.ONE;
		} else {
			if (advanceDivision.equals(BigDecimal.ONE)) {
				return BigDecimal.ONE;
			} else {
				return BigDecimal.ZERO;
			}
		}

	}

	/**
	 * 売上区分コンボボックス作成（一覧画面）
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCategoryDivisionCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		BigDecimal arDivision = BigDecimal.ONE;

		// 売上区分検索
		List<SalesCategoryDivisionForComboboxes> lineList = getCategoryDivisionList(arDivision);
		for (SalesCategoryDivisionForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCategoryDivision());
			item.setLabales(bean.getCategoryName());
			res.add(item);
		}
		return res;
	}

	/**
	 * 売上区分一覧取得
	 * @param arDivision arDivision
	 * @return List<ClassificationForComboboxes>
	 */
	public List<SalesCategoryDivisionForComboboxes> getCategoryDivisionList(
			final BigDecimal arDivision) {
		return salesCategoryForComboboxesDao
				.getCategoryDivisionList(arDivision);
	}

	/**
	 * 売上区分コンボボックス(すべて有)作成（一覧画面）
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCategoryDivisionAllCombobox() {
		// 分類マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createCategoryDivisionCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(SalesConst.COMBO_ALL_VALUE);
		allItem.setLabales(SalesConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 売上区分コンボボックス作成、仕訳反転関連項目取得
	 * @param frm 売上詳細画面フォーム
	 */
	public void createCategoryDivisionComboboxRelated(
			final SalesDetailStandardForm frm) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		String[] categoryDivisionList = null;
		String[] reversalFlgList = null;

		if (frm.getArDivision() == null) {
			frm.setArDivision(BigDecimal.ONE);
		}

		// 売上区分検索
		List<SalesCategoryDivisionForComboboxes> lineList = getCategoryDivisionList(frm
				.getArDivision());
		if (lineList != null) {
			categoryDivisionList = new String[lineList.size()];
			reversalFlgList = new String[lineList.size()];
			int index = 0;
			for (SalesCategoryDivisionForComboboxes bean : lineList) {
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(bean.getCategoryDivision());
				item.setLabales(bean.getCategoryName());
				res.add(item);

				// 分類コードリスト
				categoryDivisionList[index] = bean.getCategoryDivision();
				// 仕分反転フラグリスト
				if (BuyingDetailAction.DATA_TOTAL_DIVISION_HENPIN.equals(bean
						.getDataTotalDivision())
						|| BuyingDetailAction.DATA_TOTAL_DIVISION_NEBIKI
								.equals(bean.getDataTotalDivision())) {
					// ﾃﾞｰﾀ集計区分=2:返品,3:値引の場合
					reversalFlgList[index] = "1";
				} else {
					reversalFlgList[index] = "0";
				}

				index++;
			}
		}

		frm.setCategoryCombo(res);
		frm.setCategoryDivisionList(categoryDivisionList);
		frm.setReversalFlgList(reversalFlgList);
	}

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 売上-売上区分コンボボックス用DAOを設定します。
	 * @param salesCategoryForComboboxesDao 売上-売上区分コンボボックス用DAO
	 */
	public void setClassificationForComboboxesDao(
			final SalesCategoryDivisionForComboboxesDao salesCategoryForComboboxesDao) {
		this.salesCategoryForComboboxesDao = salesCategoryForComboboxesDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 得意先用daoを設定します。
	 * @param venderDao 得意先用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}
}
