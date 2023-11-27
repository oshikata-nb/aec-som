/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 包装指図－配合詳細画面 ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionFormulaDetailLogicImpl implements PkgDirectionFormulaDetailLogic {

	/** 製造指図－フォーミュラDao */
	private DirectionFormulaDao directionFormulaDao;

	/** 包装指図－製造指図フォーミュラDao */
	private PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionFormulaDetailLogicImpl() {
	}

	/**
	 * 配合詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装指図－配合詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setFormulaDetailForm(final HttpServletRequest request, final PkgDirectionFormulaDetailForm frm)
		throws NoDataException {
		PkgDirectionDirectionHeaderDetail header = null;
		PkgDirectionDirectionFormulaList bean = null;

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());
		BigDecimal lineNo = new BigDecimal(frm.getLineNo());

		// 製造指図ヘッダーを検索
		header = pkgDirectionCommonsLogic.getEntity(frm.getDirectionDivision(), directionNo);
		if (header == null) {
			throw new NoDataException();
		}
		setHeaderInfo(request, frm, header);

		// 製造指図プロシージャ検索
		bean = pkgDirectionDirectionFormulaListDao.getEntity(directionDivision, directionNo, stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}
		setDetailInfo(request, frm, bean);
	}
	/**
	 * 製造指図ヘッダーの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装指図-共通抽象 Form
	 * @param bean 包装指図－製造指図ヘッダー情報データ
	 */
	private void setHeaderInfo(final HttpServletRequest request, final AbstractPkgDirectionForm frm,
		final PkgDirectionDirectionHeaderDetail bean) {

		//数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		frm.setDirectionDivision(bean.getDirectionDivision().toString());
		frm.setDirectionNo(bean.getDirectionNo());
		frm.setItemCd(bean.getItemCd());
		frm.setItemName(bean.getItemName());
		frm.setUnitName(bean.getUnitName());
		frm.setHeaderUpdateDate(bean.getUpdateDate());
		frm.setJissekiFlag(bean.getJissekiFlag());
		String plandQty = checker.format(PkgDirectionConst.UNIT_DIVISION_HAIGO, bean.getPlanedQty());
		frm.setPlanedQty(plandQty);
	}

	/**
	 * 製造指図フォーミュラの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装指図－配合詳細画面 Form
	 * @param bean 包装指図－製造指図フォーミュラデータ
	 */
	private void setDetailInfo(final HttpServletRequest request, final PkgDirectionFormulaDetailForm frm,
		final PkgDirectionDirectionFormulaList bean) {

		// ステップNO.
		frm.setStepNo(bean.getStrStepNo());
		// 行NO.
		frm.setLineNo(bean.getStrLineNo());
		// 工程コード
		frm.setOperationCd(bean.getOperationCd());
		// 工程名称
		frm.setOperationName(bean.getOperationName());
		// 製造指図プロシージャ更新日時
		frm.setFormulaUpdateDate(bean.getUpdateDate());
		// 品目コード
		frm.setFormulaItemCd(bean.getItemCd());
		// 品目名称
		frm.setFormulaItemName(bean.getItemName());

		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail
			= checker.getCheckDigit(PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null);
		// 小数点桁数
		if (checkDetail.getSmallnumLength() != null) {
			frm.setQtyDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		// 端数区分
		if (checkDetail.getRoundDivision() != null) {
			frm.setQtyRoundDivision(checkDetail.getRoundDivision().toString());
		}
		// 単位
		frm.setQtyUnitName(bean.getUnitName());
		// 予定生産量
		frm.setQty(checker.format(PkgDirectionConst.UNIT_DIVISION_HAIGO, bean.getQty()));
		// 入荷ロットNo
		frm.setLotNo(bean.getLotNo());
		// 条件
		frm.setStepCondition(bean.getStepCondition());
		// 備考
		frm.setRemark(bean.getRemark());
		// 注釈
		frm.setNotes(bean.getNotes());
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * @param frm 包装指図－配合詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final PkgDirectionFormulaDetailForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}

		// 品目マスタを検索
		Item itemBean = itemDao.getEntity(frm.getFormulaItemCd());
		if (itemBean == null) {
			// データが存在しない場合
			errMsg = new ActionMessage("errors.pkgdirection.no.item");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			frm.setFormulaItemName(null);
			frm.setQtyUnitName(null);
		} else {
			frm.setFormulaItemName(itemBean.getItemName());
		}
		return errMsgs;
	}

	/**
	 * 製造指図フォーミュラ更新処理を行う.
	 * @param frm 包装指図－配合詳細画面 Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	public void update(final PkgDirectionFormulaDetailForm frm, final String tantoCd)
		throws PkgDirectionLogicException {
		try {

			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());
			BigDecimal lineNo = new BigDecimal(frm.getLineNo());

			// 製造指図ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, frm.getDirectionNo(),
				frm.getHeaderUpdateDate(), tantoCd);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			String errMsg = "errors.pkgdirection.stock.update";
			try {
				/* 在庫更新－配合指図取消 */
				if (!stock.cancelFormula(stepNo, lineNo, directionDivision, directionNo, tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

			// 製造指図プロシージャを検索
			DirectionFormula updBean = directionFormulaDao.getEntity
				(directionDivision, directionNo, lineNo, stepNo);
			if (updBean == null) {
				throw new OptimisticLockRuntimeException();
			}

			// 画面の入力内容を反映
			setDirectionFormula(frm, updBean);

			// 更新前の更新日付を設定（排他用）
			updBean.setUpdateDate(frm.getFormulaUpdateDate());

			// 更新者を設定
			updBean.setUpdatorCd(tantoCd);

			// 更新処理
			int updNum = directionFormulaDao.update(updBean);
			if (updNum != 1) {
				throw new OptimisticLockRuntimeException();
			}

			try {
				/* 在庫更新－配合指図入力 */
				if (!stock.entryFormula(stepNo, lineNo, directionDivision, directionNo, tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}
	/**
	 * 画面の内容をDirectionFormulaに設定
	 * @param frm 包装指図－配合詳細画面 Form
	 * @param bean 包装指図フォーミュラBean
	 */
	private void setDirectionFormula(final PkgDirectionFormulaDetailForm frm, final DirectionFormula bean) {

		// 品目コード
		bean.setItemCd(frm.getFormulaItemCd());
		// 予定生産量
		bean.setQty(AecNumberUtils.convertBigDecimal(frm.getQty()));
		// 入荷ロットNo
		bean.setLotNo(frm.getLotNo());
		// 条件
		bean.setStepCondition(frm.getStepCondition());
		// 備考
		bean.setRemark(frm.getRemark());
		// 注釈
		bean.setNotes(frm.getNotes());
	}

	/**
	 * パラメータチェック
	 * @param frm 包装指図－工程詳細画面 Form
	 */
	private void checkParams(final PkgDirectionFormulaDetailForm frm) {

		if (frm.getDirectionDivision() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : directionDivision is empty.パラメータチェック.getEntity");
		}
		if (frm.getDirectionNo() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : directionNo is empty.パラメータチェック.getEntity");
		}
		if (frm.getStepNo() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : stepNo is empty.パラメータチェック.getEntity");
		}
		if (frm.getLineNo() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : lineNo is empty.パラメータチェック.getEntity");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図フォーミュラDaoを設定します。
	 * @param directionFormulaDao 製造指図－フォーミュラDao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * 包装指図－製造指図フォーミュラDaoを設定します。
	 * @param pkgDirectionDirectionFormulaListDao 包装指図－製造指図フォーミュラDao
	 */
	public void setPkgDirectionDirectionFormulaListDao(
			final PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao) {
		this.pkgDirectionDirectionFormulaListDao = pkgDirectionDirectionFormulaListDao;
	}

	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 包装指図共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 包装指図共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

}
