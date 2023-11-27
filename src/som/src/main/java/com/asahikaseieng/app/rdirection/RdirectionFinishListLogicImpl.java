/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造実績－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class RdirectionFinishListLogicImpl implements RdirectionFinishListLogic {

	/** 製造実績-共通ロジッククラス */
	private RdirectionCommonsLogic rdirectionCommonsLogic;

	/** 製造実績－フォーミュラDao */
	private RdirectionDirectionFormulaListDao rdirectionFormulaListDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** ヘッダデータ取得用用Dao * */
	private DirectionHeaderDao directionHeaderDao;

	/**
	 * コンストラクタ.製造指図仕上タブ
	 */
	public RdirectionFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	public List<RdirectionDirectionFormulaList> getSearchList(
			final String directionNo) throws NoDataException {

		checkParams(directionNo);

		List<RdirectionDirectionFormulaList> list = rdirectionFormulaListDao
				.getSearchFinishList(directionNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(
			final List<RdirectionDirectionFormulaList> searchList) {
		ActionMessages errors = new ActionMessages();

		if (searchList == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchList.size(); i++) {
			RdirectionDirectionFormulaList bean = searchList.get(i);

			// 品目マスタを検索
			Item opeBean = itemDao.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = DirectionUtil.addError(errors,
					"errors.direction.no.item.row", i + 1);
			}
		}
		return errors;
	}

	/**
	 * 製造実績－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final RdirectionFinishListForm frm,
			final RdirectionDirectionHeaderList header, final String tantoCd)
			throws Exception {
		int insertNum;

		try {
			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			BigDecimal directionDiv = new BigDecimal(frm.getDirectionDivision());
			String errMsg = "errors.rdirection.stock.update";
			try {
				/* 在庫更新－包装・製造指図仕上取消 */
				if (!stock.deFinishDirection(directionDiv,
					frm.getDirectionNo(), tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			// ヘッダ更新
			rdirectionCommonsLogic.updateDirectionHeader(header);

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0)
					.getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (RdirectionDirectionFormulaList bean : frm
					.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// 指図番号、LINE_TYPEで一括削除
			rdirectionFormulaListDao.deleteFinishListByDirectionNo(frm
					.getDirectionNo());

			// 登録処理
			for (RdirectionDirectionFormulaList bean : frm
					.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 仕上の先頭レコードの場合、実績数量を在庫引落量に設定する
				if (RdirectionConst.LINE_NO_FINISH_START_NO.equals(bean
						.getLineNo())) {
					bean.setStockpdQty(AecNumberUtils.convertBigDecimal(bean
							.getStrResultQty()));
				}

				// 製造指図フォーミュラ登録
				insertNum = rdirectionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
			try {
				/* 在庫更新－包装・製造指図仕上入力 */
				if (!stock.finishDirection(directionDiv, frm.getDirectionNo(),
					tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
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

	/* -------------------- setter -------------------- */

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
	 * 製造実績-共通ロジッククラスを設定します。
	 * @param rdirectionCommonsLogic 製造実績-共通ロジッククラス
	 */
	public void setRdirectionCommonsLogic(
			final RdirectionCommonsLogic rdirectionCommonsLogic) {
		this.rdirectionCommonsLogic = rdirectionCommonsLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 製造ヘッダ用Daoを設定します。
	 * @param directionHeaderDao 製造ヘッダ用Dao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}
}
