/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 製造指図－配合タブ ロジック実装クラス
 * @author tosco
 */
public class DirectionFormulaListLogicImpl implements DirectionFormulaListLogic {

	/** 製造指図－フォーミュラDao */
	private DirectionDirectionFormulaListDao directionFormulaListDao;
	/** 品目マスタ検索Dao */
	private ItemDao itemDao;
	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public DirectionFormulaListLogicImpl() {
	}

	/**
	 * 配合タブ－初期表示用.
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return DirectionFormulaListForm データ
	 */
	public DirectionDirectionFormulaList getSumQty(final String directionNo, final BigDecimal procStepNo) {

		checkParams(directionNo);
		checkParams(procStepNo);

		DirectionDirectionFormulaList record = directionFormulaListDao.getSumQty(directionNo, procStepNo);

		if (record == null) {
			return new DirectionDirectionFormulaList();
		}

		return record;
	}

	/**
	 * 配合タブ－一覧検索処理
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return List<DirectionFormulaListForm> データ
	 * @throws NoDataException 例外
	 */
	public List<DirectionDirectionFormulaList> getSearchList(
		final String directionNo, final BigDecimal procStepNo) throws NoDataException {

		checkParams(directionNo);
		checkParams(procStepNo);

		List<DirectionDirectionFormulaList> list
			= directionFormulaListDao.getSearchList(directionNo, procStepNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 基本処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddDelList(final DirectionFormulaListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getSeqCombo().size() == 1) {
			errors = DirectionUtil.addError(errors, "errors.direction.no.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除・行追加処理はエラー
			if (form.getSearchFormList() == null) {
				errors = DirectionUtil.addError(errors, "errors.direction.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final List<DirectionDirectionFormulaList> searchFormList) {
		ActionMessages errors = new ActionMessages();

		if (searchFormList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchFormList.size(); i++) {
			DirectionDirectionFormulaList bean = searchFormList.get(i);

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
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 配合タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final DirectionFormulaListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception {
		String errMsg = "errors.direction.stock.update";
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

		try {
			//処方ヘッダ更新
			directionCommonsLogic.update(header);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			try {
				/* 在庫更新－配合指図取消 */
				if (!stock.cancelFormula
					(directionDivision, frm.getDirectionNo(), tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			// 削除処理
			if (frm.getDelFormList() != null) {
				for (DirectionDirectionFormulaList bean : frm.getDelFormList()) {
					// 削除
					if (bean.getStrSeq() != null && !bean.getStrSeq().equals("")) {
						int delNum = directionFormulaListDao.delete(bean);
						if (delNum == 0) {
							// 対象データ無し
							throw new NoDataException();
						}
					}
				}
			}

			Map<String, List<DirectionDirectionFormulaList>> stepNoBeanMap
				= Collections.synchronizedMap(
					new HashMap<String, List<DirectionDirectionFormulaList>>());

			// STEP_NOとSTEP_NOごとのリストのマップを作成する
			for (DirectionDirectionFormulaList bean : frm.getSearchFormList()) {

				// 工程順序でマップを検索
				List<DirectionDirectionFormulaList> tempList = stepNoBeanMap.get(bean.getStrStepNo());

				if (tempList == null) {

					// リストを作成
					List<DirectionDirectionFormulaList> stepNoBeanList
						= new ArrayList<DirectionDirectionFormulaList>();
					stepNoBeanList.add(bean);
					// STEP_NOごとにマッピングする
					stepNoBeanMap.put(bean.getStrStepNo(), stepNoBeanList);

				} else {

					// STEP_NOごとにマッピングする
					tempList.add(bean);
					stepNoBeanMap.put(bean.getStrStepNo(), tempList);
				}

			}

			// STEP_NOとSTEP_NOごとに更新処理を行う
			for (Map.Entry<String, List<DirectionDirectionFormulaList>> stepNoBeanList
					: stepNoBeanMap.entrySet()) {

				// キー（STEP_NO） を取得
				String stepNo = stepNoBeanList.getKey();
				// 値（List<DirectionDirectionFormulaList>）を取得
				List<DirectionDirectionFormulaList> beanList = stepNoBeanList.getValue();

				String directionNo = frm.getDirectionNo();
				checkParams(directionNo);
				checkParams(new BigDecimal(stepNo));

				// 最終LINE_NO を取得
				DirectionDirectionFormulaList lineBean
					= directionFormulaListDao.getLastLineNo(
						directionNo, new BigDecimal(stepNo));
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				DirectionDirectionFormulaList seqBean
				 	= directionFormulaListDao.getLastSeq(
				 		directionNo, new BigDecimal(stepNo));
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < beanList.size(); i++) {

					DirectionDirectionFormulaList bean = beanList.get(i);

					// ｻﾌﾞｽﾃｯﾌﾟ（表示順）の振り直し
					if ((!frm.getTempProcStepNo().equals("0"))
							&& (!frm.getTempProcStepNo().equals(bean.getStrStepNo()))) {
						bean.setSeq(lastSeqNo);
						lastSeqNo = lastSeqNo.add(BigDecimal.ONE);
					} else {
						bean.setSeq(new BigDecimal(i + 1));
					}

					// STEP_NO, LINE_NO, LINE_TYPE 更新
					if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {
						bean.setLineNo(lastLineNo);
						lastLineNo = lastLineNo.add(BigDecimal.ONE);
					}
				}

				// 更新・登録処理
				insertBean(tantoCd, beanList);
			}
			try {
				/* 在庫更新－配合指図入力 */
				if (!stock.entryFormula
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
	 * 更新・登録処理
	 * @param tantoCd
	 * @param beanList
	 */
	private void insertBean(final String tantoCd, final List<DirectionDirectionFormulaList> beanList) {

		// 更新処理
		for (DirectionDirectionFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {

				// 登録情報
				bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
				bean.setLineType(new BigDecimal(-1));
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時

				// 登録処理
				int insertNum = directionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			} else {

				// 更新処理
				int updateNum = directionFormulaListDao.updateUnlessNull(bean);
				if (updateNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * パラメータチェック
	 * @param stepNo STEP_NO
	 */
	private void checkParams(final BigDecimal stepNo) {

		if (stepNo == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック");
		}
	}

	/**
	 * パラメータチェック
	 * @param directionNo 指図区分
	 */
	private void checkParams(final String directionNo) {

		if (directionNo == null || directionNo.equals("")) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param directionFormulaListDao 処方フォーミュラDao
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
