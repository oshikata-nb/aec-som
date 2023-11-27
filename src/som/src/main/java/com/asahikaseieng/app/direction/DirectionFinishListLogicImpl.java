/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;


/**
 * 製造指図－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class DirectionFinishListLogicImpl implements DirectionFinishListLogic {

	/** 製造指図－フォーミュラDao */
	private DirectionDirectionFormulaListDao directionFormulaListDao;
	/** 品目マスタ検索Dao */
	private ItemDao itemDao;
	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.製造指図仕上タブ
	 */
	public DirectionFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	public List<DirectionDirectionFormulaList> getSearchList(final String directionNo) throws NoDataException {

		checkParams(directionNo);

		List<DirectionDirectionFormulaList> list
			= directionFormulaListDao.getSearchFinishList(directionNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final List<DirectionDirectionFormulaList> searchList) {
		ActionMessages errors = new ActionMessages();

		if (searchList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchList.size(); i++) {
			DirectionDirectionFormulaList bean = searchList.get(i);

			// 品目マスタを検索
			Item opeBean = itemDao.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = DirectionUtil.addError(errors
									, "errors.direction.no.item.row"
									, i + 1);
			}
		}
		return errors;
	}

	/**
	 * 製造指図－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final DirectionFinishListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception {
		int insertNum;
		String errMsg = "errors.direction.stock.update";
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

		try {
			//ヘッダ更新
			directionCommonsLogic.update(header);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			try {
				/* 在庫更新－包装・製造指図取消 */
				if (!stock.cancelDirection
					(directionDivision, frm.getDirectionNo(), tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0).getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (DirectionDirectionFormulaList bean : frm.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// 指図番号、LINE_TYPEで一括削除
			directionFormulaListDao.deleteFinishListByDirectionNo(frm.getDirectionNo());

			// 登録処理
			for (DirectionDirectionFormulaList bean : frm.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = directionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
			try {
				/* 在庫更新－包装・製造指図入力 */
				if (!stock.entryDirection
					(directionDivision, frm.getDirectionNo(), tantoCd)) {
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

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図－フォーミュラDaoを設定します。
	 * @param directionFormulaListDao 製造指図－フォーミュラDao
	 */
	public void setDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionFormulaListDao) {
		this.directionFormulaListDao = directionFormulaListDao;
	}
	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}


}
