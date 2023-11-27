/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 包装実績－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionFinishListLogicImpl implements
		PkgRdirectionFinishListLogic {

	/** 包装実績－フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionFormulaListDao;

	/** 包装実績－共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 製造指図フォーミュラDao */
	private DirectionFormulaDao directionFormulaDao;

	/**
	 * コンストラクタ.包装実績仕上タブ
	 */
	public PkgRdirectionFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	public List<PkgRdirectionDirectionFormulaList> getSearchList(
			final BigDecimal directionDivision, final String directionNo)
			throws NoDataException {

		checkParams(directionNo);

		List<PkgRdirectionDirectionFormulaList> list = pkgRdirectionFormulaListDao
				.getSearchFinishList(directionDivision, directionNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 包装実績－仕上一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgRdirectionFinishListForm frm) {
		ActionMessages errors = new ActionMessages();

		if (frm.getSearchFinishList() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < frm.getSearchFinishList().size(); i++) {
			PkgRdirectionDirectionFormulaList bean = frm.getSearchFinishList()
					.get(i);

			// 品目マスタを検索
			Item opeBean = itemDao.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = DirectionUtil.addError(errors,
					"errors.pkgrdirection.no.item.row", i + 1);
			}

			// 親品目チェック(詰替・貼替時のみ)
			if (PkgRdirectionConst.DIRECTION_DIVISION_REPACK.toString().equals(
				frm.getDirectionDivision())) {
				// 種別＝0:製品の場合
				if (PkgRdirectionConst.TYPE_DIVISION_PRODUCT.equals(opeBean
						.getTypeDivision())) {
					// 親品目コードが異なる場合
					if (!convertNullToBlank(frm.getParentItemCd()).equals(
						convertNullToBlank(opeBean.getParentItemCd()))) {
						errors = DirectionUtil.addError(errors,
							"errors.pkgrdirection.not.equal.parent.item.row",
							i + 1);
					}
				}
			}
		}
		return errors;
	}

	/**
	 * 包装実績－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgRdirectionFinishListForm frm,
			final String tantoCd) throws Exception {
		int insertNum;

		try {
			BigDecimal directionDiv = new BigDecimal(frm.getDirectionDivision());
			String directionStatus = frm.getDirectionStatus();
			BigDecimal decStatus = new BigDecimal(directionStatus);
			Timestamp updateDate = frm.getHeaderUpdateDate();

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			String errMsg = "errors.pkgrdirection.stock.update";

			// ステータスが、検査待ち在庫計上又は、製品検査入力済み以外の場合
			if (!PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_WAIT
					.equals(decStatus)
					&& !PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT
							.equals(decStatus)) {
				try {
					/* 在庫更新－包装・製造指図仕上取消 */
					if (!stock.deFinishDirection(directionDiv, frm
							.getDirectionNo(), tantoCd)) {
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
			}

			// ヘッダのステータスを「包装実績入力済」に更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(directionStatus,
				directionDiv, frm.getDirectionNo(), updateDate, tantoCd);

			// ヘッダの実績生産量を更新する
			updatePlanedQty(frm, directionDiv);

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0)
					.getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (PkgRdirectionDirectionFormulaList bean : frm
					.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// 指図番号、LINE_TYPEで一括削除
			pkgRdirectionFormulaListDao.deleteFinishListByDirectionNo(
				directionDiv, frm.getDirectionNo());

			// 登録処理
			for (PkgRdirectionDirectionFormulaList bean : frm
					.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 生産実績数量
				bean.setResultQty(AecNumberUtils.convertBigDecimal(bean
						.getStrResultQty()));
				// 在庫引落量にも生産実績数量を設定する（在庫更新のため）
				bean.setStockpdQty(AecNumberUtils.convertBigDecimal(bean
						.getStrResultQty()));
				// 充填実績数量
				bean.setFillResultQty(AecNumberUtils.convertBigDecimal(bean
						.getStrResultFillQty()));
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = pkgRdirectionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
				// ステータスが、検査待ち在庫計上又は、製品検査入力済み以外の場合 Lone_no >= 10001も更新する。
				if (!PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_WAIT
						.equals(decStatus)
						&& !PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT
								.equals(decStatus)) {
					try {
						DirectionFormula fbean = directionFormulaDao.getEntity(
							bean.getDirectionDivision(), bean.getDirectionNo(),
							bean.getLineNo().add(new BigDecimal("9000")), bean
									.getStepNo());
						if (fbean != null) {
							fbean.setResultQty(bean.getResultQty());
							fbean.setStockpdQty(bean.getStockpdQty());
							fbean.setFillResultQty(bean.getFillResultQty());
							fbean.setUpdatorCd(bean.getUpdatorCd());

							directionFormulaDao.update(fbean);
						}
					} catch (LogicExceptionEx e) {
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				}
			}

			// ステータスが、検査待ち在庫計上又は、製品検査入力済み以外の場合
			if (!PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_WAIT
					.equals(decStatus)
					&& !PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT
							.equals(decStatus)) {
				try {
					/* 在庫更新－包装・製造指図仕上入力 */
					if (!stock.finishDirection(directionDiv, frm
							.getDirectionNo(), tantoCd)) {
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * ヘッダーの実績生産量を更新する
	 * @param frm 仕上タブForm
	 * @param directionDivision 指図区分
	 */
	private void updatePlanedQty(final PkgRdirectionFinishListForm frm,
			final BigDecimal directionDivision) {
		// ヘッダー検索
		DirectionHeader headBean = directionHeaderDao.getEntity(
			directionDivision, frm.getDirectionNo());
		if (headBean == null) {
			// 更新データなし
			throw new OptimisticLockRuntimeException();
		}
		// 実績生産量を設定
		headBean.setResultQty(AecNumberUtils.convertBigDecimal(frm
				.getSearchFinishList().get(0).getStrResultQty()));
		// 更新処理
		int updateNum = directionHeaderDao.update(headBean);
		if (updateNum != 1) {
			// 更新データなし
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param directionNo 指図番号
	 */
	private void checkParams(final String directionNo) {

		if (directionNo == null || directionNo.equals("")) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 
	 * 文字列がNULLの場合、ブランク("")に変換して返す。
	 * @param value 文字列
	 * @return String 変換文字列
	 */
	private String convertNullToBlank(final String value) {
		String ret = "";
		if (StringUtils.isEmpty(value)) {
			return ret;
		}
		return value;
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
				"errors.pkgrdirection.result.date.null");
		}

		return errors;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－フォーミュラDaoを設定します。
	 * @param pkgRdirectionFormulaListDao 包装実績－フォーミュラDao
	 */
	public void setPkgRdirectionFormulaListDao(
			final PkgRdirectionDirectionFormulaListDao pkgRdirectionFormulaListDao) {
		this.pkgRdirectionFormulaListDao = pkgRdirectionFormulaListDao;
	}

	/**
	 * directionFormulaDaoを設定します。
	 * @param directionFormulaDao directionFormulaDao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * 包装実績－共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績－共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}

	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
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
}
