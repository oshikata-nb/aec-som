/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 包装指図－配合詳細画面 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionFormulaDetailLogicImpl implements PkgRdirectionFormulaDetailLogic {

	/** 製造指図－フォーミュラDao */
	private DirectionFormulaDao directionFormulaDao;

	/** 包装実績－製造指図フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao;

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionFormulaDetailLogicImpl() {
	}

	/**
	 * 配合詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－配合詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setFormulaDetailForm(final HttpServletRequest request, final PkgRdirectionFormulaDetailForm frm)
		throws NoDataException {
		PkgRdirectionDirectionHeaderDetail header = null;
		PkgRdirectionDirectionFormulaList bean = null;

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());
		BigDecimal lineNo = new BigDecimal(frm.getLineNo());

		// 製造指図ヘッダーを検索
		header = pkgRdirectionCommonsLogic.getEntity(frm.getDirectionDivision(), directionNo);
		if (header == null) {
			throw new NoDataException();
		}
		setHeaderInfo(request, frm, header);

		// 製造指図プロシージャ検索
		bean = pkgRdirectionDirectionFormulaListDao.getEntity(directionDivision, directionNo, stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}
		setDetailInfo(request, frm, bean);
	}
	/**
	 * 製造指図ヘッダーの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装実績-共通抽象 Form
	 * @param bean 包装実績－製造指図ヘッダー情報データ
	 */
	private void setHeaderInfo(final HttpServletRequest request, final AbstractPkgRdirectionForm frm,
		final PkgRdirectionDirectionHeaderDetail bean) {

		//数値桁数チェック部品取得
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);

		frm.setDirectionDivision(bean.getDirectionDivision().toString());
		frm.setDirectionNo(bean.getDirectionNo());
		frm.setItemCd(bean.getItemCd());
		frm.setItemName(bean.getItemName());
		frm.setUnitName(bean.getUnitName());
		frm.setHeaderUpdateDate(bean.getUpdateDate());
		frm.setJissekiFlag(bean.getJissekiFlag());
		String plandQty = checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getPlanedQty());
		frm.setPlanedQty(plandQty);
		frm.setDirectionStatus(bean.getDirectionStatus().toString());
	}

	/**
	 * 製造指図フォーミュラの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装実績－配合詳細画面 Form
	 * @param bean 包装実績－製造指図フォーミュラデータ
	 */
	private void setDetailInfo(final HttpServletRequest request, final PkgRdirectionFormulaDetailForm frm,
		final PkgRdirectionDirectionFormulaList bean) {

		// ステップNO.
		frm.setStepNo(bean.getStrStepNo());
		// 行NO.
		frm.setLineNo(bean.getStrLineNo());
		// 工程コード
		frm.setOperationCd(bean.getOperationCd());
		// 工程名称
		frm.setOperationName(bean.getOperName());
		// 製造指図プロシージャ更新日時
		frm.setFormulaUpdateDate(bean.getUpdateDate());
		// 品目コード
		frm.setFormulaItemCd(bean.getItemCd());
		// 品目名称
		frm.setFormulaItemName(bean.getItemName());

		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		// 単位
		frm.setQtyUnitName(bean.getUnitName());
		// 予定数量
		frm.setStrQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getQty()));
		// 入荷ロットNo
		frm.setLotNo(bean.getLotNo());
		// 在庫引落量
		frm.setStrStockpdQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getStockpdQty()));
		// 使用数
		frm.setStrResultQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getResultQty()));
		// サンプル数
		frm.setStrSampleQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getSampleQty()));
		// 生産ロス数
		frm.setStrLossQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getLossQty()));
		// 不良品数
		frm.setStrDefectQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getDefectQty()));
		// 調整数量
		frm.setStrAdjustQty(checker.format(PkgRdirectionConst.UNIT_DIVISION_ADJUST, bean.getAdjustQty()));
		// 条件
		frm.setStepCondition(bean.getStepCondition());
		// 備考
		frm.setRemark(bean.getRemark());
		// 注釈
		frm.setNotes(bean.getNotes());
	}

	/**
	 * 製造指図フォーミュラ更新処理を行う.
	 * @param frm 包装実績－配合詳細画面 Form
	 * @param tantoCd 更新者
	 */
	public void update(final PkgRdirectionFormulaDetailForm frm, final String tantoCd) {
		try {

			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());
			BigDecimal lineNo = new BigDecimal(frm.getLineNo());

			// 製造指図ヘッダーを包装実績入力済に更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(frm.getDirectionStatus(),
				directionDivision, directionNo, frm.getHeaderUpdateDate(), tantoCd);

			// 製造指図プロシージャを検索
			DirectionFormula updBean = directionFormulaDao.getEntity
				(directionDivision, directionNo, lineNo, stepNo);
			if (updBean == null) {
				throw new OptimisticLockRuntimeException();
			}

			// 画面の入力内容を反映
			setRdirectionFormula(frm, updBean);

			// 更新前の更新日付を設定（排他用）
			updBean.setUpdateDate(frm.getFormulaUpdateDate());

			// 更新者を設定
			updBean.setUpdatorCd(tantoCd);

			// 更新処理
			int updNum = directionFormulaDao.update(updBean);
			if (updNum != 1) {
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}
	/**
	 * 画面の内容をDirectionFormulaに設定
	 * @param frm 包装実績－配合詳細画面 Form
	 * @param bean 製造指図フォーミュラBean
	 */
	private void setRdirectionFormula(final PkgRdirectionFormulaDetailForm frm, final DirectionFormula bean) {

		// 条件
		bean.setStepCondition(frm.getStepCondition());
		// 備考
		bean.setRemark(frm.getRemark());
		// 注釈
		bean.setNotes(frm.getNotes());
	}

	/**
	 * パラメータチェック
	 * @param frm 包装実績－工程詳細画面 Form
	 */
	private void checkParams(final PkgRdirectionFormulaDetailForm frm) {

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
	 * 包装実績－製造指図フォーミュラDaoを設定します。
	 * @param pkgRdirectionDirectionFormulaListDao 包装指図－製造指図フォーミュラDao
	 */
	public void setPkgRdirectionDirectionFormulaListDao(
			final PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao) {
		this.pkgRdirectionDirectionFormulaListDao = pkgRdirectionDirectionFormulaListDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}
}
