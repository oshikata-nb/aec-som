/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;


/**
 * 包装指図－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionFinishListLogicImpl implements PkgDirectionFinishListLogic {

	/** 包装指図－フォーミュラDao */
	private PkgDirectionDirectionFormulaListDao pkgDirectionFormulaListDao;
	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.包装指図仕上タブ
	 */
	public PkgDirectionFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param directionDiv 区分
	 * @param directionNo 指図番号
	 * @return List<PkgDirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	public List<PkgDirectionDirectionFormulaList> getSearchList(
		final BigDecimal directionDiv, final String directionNo) throws NoDataException {

		checkParams(directionNo);

		List<PkgDirectionDirectionFormulaList> list	=
			pkgDirectionFormulaListDao.getSearchFinishList(directionDiv, directionNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param frm 仕上タブForm
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgDirectionFinishListForm frm) {
		ActionMessages errors = new ActionMessages();
		ActionMessage errMsg = null;


		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errors;
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < frm.getSearchFinishList().size(); i++) {
			PkgDirectionDirectionFormulaList bean = frm.getSearchFinishList().get(i);

			// 品目マスタを検索
			Item opeBean = itemDao.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = DirectionUtil.addError(errors
									, "errors.direction.no.item.row"
									, i + 1);
			}

			// 親品目チェック(詰替・貼替時のみ)
			if (PkgDirectionConst.DIRECTION_DIVISION_REPACK.toString()
										.equals(frm.getDirectionDivision())) {
				// 種別＝0:製品の場合
				if (PkgDirectionConst.TYPE_DIVISION_PRODUCT.equals(opeBean.getTypeDivision())) {
					// 親品目コードが異なる場合
					if (!convertNullToBlank(frm.getParentItemCd())
							.equals(convertNullToBlank(opeBean.getParentItemCd()))) {
						errors = DirectionUtil.addError(errors
							, "errors.pkgrdirection.not.equal.parent.item.row"
							, i + 1);
					}
				}
			}
		}
		return errors;
	}

	/**
	 * 包装指図－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgDirectionFinishListForm frm, final String tantoCd) throws Exception {
		int insertNum;

		try {
			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

			// ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, frm.getDirectionNo(),
				frm.getHeaderUpdateDate(), tantoCd);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			String errMsg = "errors.pkgdirection.stock.update";
			try {
				/* 在庫更新－包装・製造指図取消 */
				if (!stock.cancelDirection(directionDivision,
					frm.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

			// ヘッダーの生産予定数量を更新する
			updatePlanedQty(frm, directionDivision);

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0).getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (PkgDirectionDirectionFormulaList bean : frm.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// 指図番号、LINE_TYPEで一括削除
			pkgDirectionFormulaListDao
				.deleteFinishListByDirectionNo(directionDivision, frm.getDirectionNo());

			// 登録処理
			for (PkgDirectionDirectionFormulaList bean : frm.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 充填予定数量
				bean.setFillQty(AecNumberUtils.convertBigDecimal(bean.getStrFillQty()));
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = pkgDirectionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
			try {
				/* 在庫更新－包装・製造指図入力 */
				if (!stock.entryDirection(directionDivision,
					frm.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * ヘッダーの生産予定数量を更新する
	 * @param frm 仕上タブForm
	 * @param directionDivision 指図区分
	 */
	private void updatePlanedQty(final PkgDirectionFinishListForm frm, final BigDecimal directionDivision) {
		// 製造指図ヘッダー検索
		DirectionHeader headBean =
			directionHeaderDao.getEntity(directionDivision, frm.getDirectionNo());
		if (headBean == null) {
			// 更新データなし
			throw new OptimisticLockRuntimeException();
		}
		headBean.setPlanedQty(
			AecNumberUtils.convertBigDecimal(frm.getSearchFinishList().get(0).getStrQty()));
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
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}

	/**
	 * 
	 * 文字列がNULLの場合、ブランク("")に変換して返す。
	 * @param value 文字列
	 * @return　String 変換文字列
	 */
	private String convertNullToBlank(final String value) {
		String ret = "";
		if (StringUtils.isEmpty(value)) {
			return ret;
		}
		return value;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－フォーミュラDaoを設定します。
	 * @param pkgDirectionFormulaListDao 包装指図－フォーミュラDao
	 */
	public void setPkgDirectionFormulaListDao(
			final PkgDirectionDirectionFormulaListDao pkgDirectionFormulaListDao) {
		this.pkgDirectionFormulaListDao = pkgDirectionFormulaListDao;
	}
	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(final DirectionHeaderDao directionHeaderDao) {
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
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 基本処方共通ロジッククラス
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
