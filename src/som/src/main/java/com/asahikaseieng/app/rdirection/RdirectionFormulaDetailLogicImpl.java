/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.common.stockinout.StockinoutForUpdateChecker;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventoryListDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造実績－フォーミュラ検索 ロジック実装クラス
 * @author tosco
 */
public class RdirectionFormulaDetailLogicImpl implements
		RdirectionFormulaDetailLogic {

	/** 製造実績-共通ロジッククラス */
	private RdirectionCommonsLogic rdirectionCommonsLogic;

	/** 製造実績－プロシージャDao */
	private RdirectionDirectionProcedureListDao rdirectionProcedureDao;

	/** 製造実績－フォーミュラDao */
	private RdirectionDirectionFormulaListDao rdirectionFormulaListDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 在庫更新用Dao */
	private ZaiCtrlDao zaiCtrlDao;

	/** ロット在庫用Dao */
	private RdirectionLotInventoryListDao rdirectionLotInventoryListDao;

	/** ヘッダデータ取得用Dao * */
	private DirectionHeaderDao directionHeaderDao;

	/** 配合データ取得用Dao * */
	private DirectionFormulaDao directionFormulaDao;

	/** ﾛｯﾄ番号を元にﾛｯﾄ在庫のﾛｹｰｼｮﾝを取得用Dao * */
	private RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao;

	/**
	 * コンストラクタ.製造実績－フォーミュラ検索
	 */
	public RdirectionFormulaDetailLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 製造実績工程テーブルより取得する
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final String directionNo,
			final BigDecimal stepNo) {
		String[] res = {null, null};
		RdirectionDirectionProcedureList bean = rdirectionProcedureDao
				.getOperationName(directionNo, stepNo);
		if (bean != null) {
			res[0] = bean.getOperationCd(); // 工程コード
			res[1] = bean.getOperationName(); // 工程名称
		}
		return res;
	}

	/**
	 * 製造実績－フォーミュラ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return DirectionDirectionFormulaList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RdirectionDirectionFormulaList getByPrimaryKey(
			final String directionNo, final BigDecimal stepNo,
			final BigDecimal lineNo) throws NoDataException {
		checkParams(directionNo);
		checkParams(stepNo);
		checkParams(lineNo);

		RdirectionDirectionFormulaList bean = rdirectionFormulaListDao
				.getByPrimaryKey(directionNo, stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param bean 製造実績－フォーミュラBean
	 * @param initBean 初期検索データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(
			final RdirectionDirectionFormulaList bean,
			final RdirectionDirectionFormulaList initBean) {
		ActionMessages errors = new ActionMessages();
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		if (bean == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 品目マスタを検索
		Item opeBean = itemDao.getEntity(bean.getItemCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = RdirectionUtil
					.addError(errors, "errors.direction.no.item");
		}

		// 在庫更新対象の場合
		if (!RdirectionConst.STOCK_DIVISION_NOT_UPDATE_INVENTORY.equals(bean
				.getStrStockDivision())) {
			// ロット管理する場合
			if (RdirectionConst.LOT_DIVISION_ON
					.equals(bean.getStrLotDivision())) {
				if (StringUtils.isEmpty(bean.getLotNo())) {
					errors = RdirectionUtil.addError(errors, "errors.required",
						rb.getString("item.rdirection.formula.lot.no"));
				} else {
					// 在庫引当を一度もしていない場合、または、ロット番号を変更した場合にロット在庫が存在するか確認する
					if (initBean.getStockpdQty() == null
							|| !bean.getLotNo().equals(initBean.getLotNo())) {
						List<RdirectionLotInventoryList> list = null;
						list = rdirectionLotInventoryListDao.getLocationList(
							bean.getItemCd(), bean.getLotNo());
						if (list == null || list.isEmpty()) {
							errors = RdirectionUtil.addError(errors,
								"errors.nodata.master",
								rb.getString("item.rdirection.formula.lot.no"));
						} else {
							bean.setLocationCd(list.get(0).getLocationCd());
							bean.setManufacturerLotNo(list.get(0)
									.getAliasLotNo());
						}
					}
				}
			}
		}
		return errors;
	}

	/**
	 * 製造実績－フォーミュラ更新処理を行う.
	 * @param bean 製造実績－フォーミュラBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RdirectionDirectionFormulaList bean,
			final RdirectionDirectionHeaderList header) throws Exception {
		String errMsg = "errors.rdirection.stock.update";
		try {
			// 製造指図ヘッダー更新
			rdirectionCommonsLogic.updateDirectionHeader(header);

			RdirectionDirectionFormulaList oBean = rdirectionFormulaListDao
					.getByPrimaryKey(bean.getDirectionNo(), bean.getStepNo(),
						bean.getLineNo());
			StockinoutForUpdateChecker checker = new StockinoutForUpdateChecker(
					oBean);
			boolean flg = checker
					.notEqual(new StockinoutForUpdateChecker(bean));

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			if (flg) {
				try {
					/* 在庫更新－配合実績取消 */
					if (!stock.deResultFormula(bean.getStepNo(), bean
							.getLineNo(), bean.getDirectionDivision(), bean
							.getDirectionNo(), bean.getUpdatorCd())) {
						RdirectionLogicException ex = new RdirectionLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					RdirectionLogicException ex = new RdirectionLogicException(
							errMsg, "");
					ex.setModuleCd("StockinoutForDirection");
					ex.setInsideErrCd(bean.getDirectionNo());
					ex.setInsideErrMsg("StepNo=" + bean.getStepNo().toString()
							+ " LineNo=" + bean.getLineNo().toString()
							+ " Message=" + e.getMessage());
					throw ex;
				}
			}
			// 更新処理
			rdirectionFormulaListDao.update(bean);
			if (flg) {
				try {
					/* 在庫更新－配合実績入力 */
					if (!stock.resultFormula(bean.getStepNo(),
						bean.getLineNo(), bean.getDirectionDivision(), bean
								.getDirectionNo(), bean.getUpdatorCd())) {
						RdirectionLogicException ex = new RdirectionLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					RdirectionLogicException ex = new RdirectionLogicException(
							errMsg, "");
					ex.setModuleCd("StockinoutForDirection");
					ex.setInsideErrCd(bean.getDirectionNo());
					ex.setInsideErrMsg("StepNo=" + bean.getStepNo().toString()
							+ " LineNo=" + bean.getLineNo().toString()
							+ " Message=" + e.getMessage());
					throw ex;
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param id ID
	 */
	private void checkParams(final String id) {

		if (id == null || id.equals("")) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getByPrimaryKey");
		}
	}

	/**
	 * パラメータチェック
	 * @param no No.
	 */
	private void checkParams(final BigDecimal no) {

		if (no == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getByPrimaryKey");
		}
	}

	/**
	 * 更新時に開始、終了の実績日時が入っているかチェックする 開始、終了の実績日時がNullの場合エラー
	 * @param directionNo 製造指図No
	 * @param directionDivision 製造指図区分
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForResultDate(final String directionDivision,
			final String directionNo) {
		ActionMessages errors = new ActionMessages();

		if (directionNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 製造、包装用ヘッダを検索
		DirectionHeader opeBean = directionHeaderDao.getEntity(new BigDecimal(
				directionDivision), directionNo);

		if (opeBean.getResultSdate() == null
				|| opeBean.getResultEdate() == null) {
			// データが存在しない場合
			errors = DirectionUtil.addError(errors,
				"errors.rdirection.result.date.null");
		}

		return errors;
	}

	/**
	 * 実績を変更時、製造配合とロット在庫のロケーションが異なる場合エラーを表示
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) {
		ActionMessages errors = new ActionMessages();

		// ライン番号とステップ番号が無い場合はチェックを行わない
		if (lineNo == null || stepNo == null) {
			return errors;
		}

		DirectionFormula opeBean = directionFormulaDao.getEntity(
			RdirectionConst.DIRECTION_DIVISION, directionNo, lineNo, stepNo);

		// ﾛｯﾄとﾛｹがある場合戻しあり
		if (opeBean.getLotNo() != null && opeBean.getLocationCd() != null) {

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索
			List<RdirectionFormulaLotInventoryList> locationBean = rdirectionFormulaLotInventoryListDao
					.getLotList(opeBean.getLotNo());

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果がある場合
			if (!locationBean.isEmpty()) {

				// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果が１件の場合
				if (locationBean.size() == 1) {

					// 現在の配合のﾛｹとﾛｯﾄ在庫のﾛｹが異なる場合エラー
					if (!opeBean.getLocationCd().equals(
						locationBean.get(0).getLocationCd())) {
						// データが存在しない場合
						errors = DirectionUtil.addError(errors,
							"errors.rdirection.location.disagreement");

					}

				}

			}
		}
		return errors;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績-共通ロジッククラスを設定します。
	 * @param rdirectionCommonsLogic 製造実績-共通ロジッククラス
	 */
	public void setRdirectionCommonsLogic(
			final RdirectionCommonsLogic rdirectionCommonsLogic) {
		this.rdirectionCommonsLogic = rdirectionCommonsLogic;
	}

	/**
	 * 製造実績－プロシージャDaoを設定します。
	 * @param rdirectionProcedureDao 製造実績－プロシージャDao
	 */
	public void setRdirectionProcedureDao(
			final RdirectionDirectionProcedureListDao rdirectionProcedureDao) {
		this.rdirectionProcedureDao = rdirectionProcedureDao;
	}

	/**
	 * 製造実績－フォーミュラDaoを設定します。
	 * @param rdirectionFormulaListDao 製造実績－フォーミュラDao
	 */
	public void setRdirectionFormulaListDao(
			final RdirectionDirectionFormulaListDao rdirectionFormulaListDao) {
		this.rdirectionFormulaListDao = rdirectionFormulaListDao;
	}

	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * ロット在庫用Daoを設定します。
	 * @param rdirectionLotInventoryListDao ロット在庫用Dao
	 */
	public void setRdirectionLotInventoryListDao(
			final RdirectionLotInventoryListDao rdirectionLotInventoryListDao) {
		this.rdirectionLotInventoryListDao = rdirectionLotInventoryListDao;
	}

	/**
	 * 製造ヘッダ用Daoを設定します。
	 * @param directionHeaderDao 製造ヘッダ用Dao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 製造配合用Daoを設定します。
	 * @param directionFormulaDao 製造配合用Dao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * rdirectionFormulaLotInventoryListDaoを設定します。
	 * @param rdirectionFormulaLotInventoryListDao
	 *            rdirectionFormulaLotInventoryListDao
	 */
	public void setRdirectionFormulaLotInventoryListDao(
			final RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao) {
		this.rdirectionFormulaLotInventoryListDao = rdirectionFormulaLotInventoryListDao;
	}
}
