/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productinspectcomp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.pkgdirection.PkgDirectionConst;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp.ProductInspectCompLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp.ProductInspectCompLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompList;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompListDao;
import com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompDetailListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompFormulaListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompHeaderListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompInspectionListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompListConditionForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompProcedureListForReport;
import com.asahikaseieng.dao.nonentity.productinspectcompforreport.ProductInspectCompProcedureListForReportDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製品検査完了入力 ロジック実装クラス
 * @author tosco
 */
public class ProductInspectCompListLogicImpl implements
		ProductInspectCompListLogic {

	/** 製品検査完了入力画面用のDao * */
	private ProductInspectCompListDao productInspectCompListDao;

	/** 生産ラインコンボボックス用DAO */
	private ProductInspectCompLineForComboboxesDao productInspectCompLineForComboboxesDao;

	/** 製造指図ヘッダーテーブル用のDao * */
	private DirectionHeaderDao directionHeaderDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 指図ステータス 製品検査入力済 */
	private static final String INSPECTION_INPUTED = "6";

	/** 検査判定フラグ 合格 */
	private static final String PASSING = "1";

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	private ProductInspectCompHeaderListForReportDao productInspectCompHeaderListForReportDao;

	private ProductInspectCompDetailListForReportDao productInspectCompDetailListForReportDao;

	private ProductInspectCompProcedureListForReportDao productInspectCompProcedureListForReportDao;

	private ProductInspectCompFormulaListForReportDao productInspectCompFormulaListForReportDao;

	private ProductInspectCompInspectionListForReportDao productInspectCompInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ProductInspectCompListLogicImpl() {
	}

	/**
	 * 製品検査完了入力一覧 検索処理を行う
	 * @param condition 検索条件
	 * @return List<ProductInspectCompList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ProductInspectCompList> getSearchList(
			final ProductInspectCompPagerCondition condition)
			throws NoDataException {
		// パラメータチェック
		checkParams(condition);
		// 一覧検索
		List<ProductInspectCompList> list = productInspectCompListDao
				.getSearchList(condition);

		// 検索結果があるか？
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 検索結果分ループ
		for (ProductInspectCompList bean : list) {
			// ステータスが製品検査入力済 の場合はデフォルトチェック
			if (bean.getDirectionStatus().equals(
				new BigDecimal(INSPECTION_INPUTED))) {
				bean.setProductInspectCompCheckBox(Boolean.TRUE);
			}

			// 実績生産量をフォーマットを変換してセット
			String strResultQty = checkDigitUtilsLogic.format(bean
					.getUnitOfOperationManagement(), bean.getResultQty());
			bean.setStrResultQty(strResultQty);

			// 実績ロット在庫量をフォーマットを変換してセット
			String strLotInventoryQty = checkDigitUtilsLogic.format(bean
					.getUnitOfOperationManagement(), bean.getLotInventoryQty());
			bean.setStrLotInventoryQty(strLotInventoryQty);
		}

		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompListForReport> 検索結果リスト
	 */
	public List<ProductInspectCompHeaderListForReport> getHeaderReportList(
			final ProductInspectCompListConditionForReport condition) {
		List<ProductInspectCompHeaderListForReport> list = productInspectCompHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompDetailListForReport> 検索結果リスト
	 */
	public List<ProductInspectCompDetailListForReport> getDetailReportList(
			final ProductInspectCompListConditionForReport condition) {
		List<ProductInspectCompDetailListForReport> list = productInspectCompDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompDetailListForReport> 検索結果リスト
	 */
	public List<ProductInspectCompProcedureListForReport> getProcedureReportList(
			final ProductInspectCompListConditionForReport condition) {

		List<ProductInspectCompProcedureListForReport> list = productInspectCompProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompFormulaListForReport> 検索結果リスト
	 */
	public List<ProductInspectCompFormulaListForReport> getFormulaReportList(
			final ProductInspectCompListConditionForReport condition) {

		List<ProductInspectCompFormulaListForReport> list = productInspectCompFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductInspectCompInspectionListForReport> 検索結果リスト
	 */
	public List<ProductInspectCompInspectionListForReport> getInspectionReportList(
			final ProductInspectCompListConditionForReport condition) {

		List<ProductInspectCompInspectionListForReport> list = productInspectCompInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 製品検査完了入力の登録処理を行う
	 * @param searchList 検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	public void update(final List<ProductInspectCompList> searchList,
			final String loginUserId) throws Exception {

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.productinspectcomp.stock.update.direction.no";
		ProductInspectCompLogicException ex = null;

		// システム日時取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		// 検索結果分ループ
		for (ProductInspectCompList bean : searchList) {
			// チェックボックスにチェックが入っていたら
			if (bean.isProductInspectCompCheckBox()) {
				// 更新対象データを作成する
				DirectionHeader newBean = new DirectionHeader();
				// 更新対象データに検索取得時のデータのコピーを行う
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// 指図ステータスを設定
				newBean.setDirectionStatus(new BigDecimal(INSPECTION_INPUTED)); // 製品検査入力済を設定
				newBean.setCertificationFlag(bean.getCertificationFlag()); // 合格・不合格を設定
				// 合格だったら
				if (bean.getCertificationFlag().equals(new BigDecimal(PASSING))) {
					// 合格したシステム日時を設定
					newBean.setCertificationDate(now);
				}

				// 更新者(ログインユーザー)セット
				newBean.setUpdatorCd(loginUserId);
				try {
					// 製造指図ヘッダー更新
					int updateNum = directionHeaderDao.update(newBean);

					if (updateNum == 0) {
						// 更新対象無しエラー
						throw new NoDataException();
					}
					try {
						/* 在庫更新－包装指図製品完成 */
						if (!stock.gradeDirection(newBean
								.getDirectionDivision(), newBean
								.getDirectionNo(), loginUserId)) {
							ex = new ProductInspectCompLogicException(errMsg,
									newBean.getDirectionNo());
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						BigDecimal bd = bean.getLotInventoryQty();
						if (e.getMessage().equals("該当データがありません。")
								&& (bd == null || bd.compareTo(BigDecimal.ZERO) == 0)) {
							continue;
						} else {
							ex = new ProductInspectCompLogicException(errMsg,
									newBean.getDirectionNo());
							throw ex;
						}
					}

					// 更新時に、すでに更新されていた場合
				} catch (NotSingleRowUpdatedRuntimeException e) {
					throw new OptimisticLockRuntimeException();
				}
			}
		}
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<ProductInspectCompLineForComboboxes>
	 */
	public List<ProductInspectCompLineForComboboxes> getAllLines() {
		return productInspectCompLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox(final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(PkgDirectionConst.COMBO_ALL_VALUE.toString());
			item.setLabales(PkgDirectionConst.COMBO_ALL_LABEL);
			res.add(item);
		}

		// 生産ライン検索
		List<ProductInspectCompLineForComboboxes> lineList = getAllLines();
		for (ProductInspectCompLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}
		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final ProductInspectCompPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製品検査完了入力画面用のDaoを設定します。
	 * @param productInspectCompListDao 製品検査完了入力画面用のDao
	 */
	public void setProductInspectCompListDao(
			final ProductInspectCompListDao productInspectCompListDao) {
		this.productInspectCompListDao = productInspectCompListDao;

	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param productInspectCompLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setProductInspectCompLineForComboboxesDao(
			final ProductInspectCompLineForComboboxesDao productInspectCompLineForComboboxesDao) {
		this.productInspectCompLineForComboboxesDao = productInspectCompLineForComboboxesDao;
	}

	/**
	 * 製造指図ヘッダーテーブル用Daoを設定します。
	 * @param directionHeaderDao 製造指図ヘッダーテーブル用Dao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * productInspectCompHeaderListForReportDaoを設定します。
	 * @param productInspectCompHeaderListForReportDao
	 *            productInspectCompHeaderListForReportDao
	 */
	public void setProductInspectCompHeaderListForReportDao(
			final ProductInspectCompHeaderListForReportDao productInspectCompHeaderListForReportDao) {
		this.productInspectCompHeaderListForReportDao = productInspectCompHeaderListForReportDao;
	}

	/**
	 * productInspectCompDetailListForReportDaoを設定します。
	 * @param productInspectCompDetailListForReportDao
	 *            productInspectCompDetailListForReportDao
	 */
	public void setProductInspectCompDetailListForReportDao(
			final ProductInspectCompDetailListForReportDao productInspectCompDetailListForReportDao) {
		this.productInspectCompDetailListForReportDao = productInspectCompDetailListForReportDao;
	}

	/**
	 * productInspectCompProcedureListForReportDaoを設定します。
	 * @param productInspectCompProcedureListForReportDao
	 *            productInspectCompProcedureListForReportDao
	 */
	public void setProductInspectCompProcedureListForReportDao(
			final ProductInspectCompProcedureListForReportDao productInspectCompProcedureListForReportDao) {
		this.productInspectCompProcedureListForReportDao = productInspectCompProcedureListForReportDao;
	}

	/**
	 * productInspectCompFormulaListForReportDaoを設定します。
	 * @param productInspectCompFormulaListForReportDao
	 *            productInspectCompFormulaListForReportDao
	 */
	public void setProductInspectCompFormulaListForReportDao(
			final ProductInspectCompFormulaListForReportDao productInspectCompFormulaListForReportDao) {
		this.productInspectCompFormulaListForReportDao = productInspectCompFormulaListForReportDao;
	}

	/**
	 * productInspectCompInspectionListForReportDaoを設定します。
	 * @param productInspectCompInspectionListForReportDao
	 *            productInspectCompInspectionListForReportDao
	 */
	public void setProductInspectCompInspectionListForReportDao(
			final ProductInspectCompInspectionListForReportDao productInspectCompInspectionListForReportDao) {
		this.productInspectCompInspectionListForReportDao = productInspectCompInspectionListForReportDao;
	}
}
