/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
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

/**
 * 包装指図－配合一覧画面 ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionFormulaListLogicImpl implements PkgDirectionFormulaListLogic {

	/** 処方フォーミュラDao */
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
	public PkgDirectionFormulaListLogicImpl() {
	}

	/**
	 * 配合一覧検索処理
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return List<PkgDirectionDirectionFormulaList> データ
	 * @throws NoDataException データなし
	 */
	public List<PkgDirectionDirectionFormulaList> getSearchList(final PkgDirectionFormulaListForm frm)
		throws NoDataException {
		checkParams(frm);

		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getProcStepNo());

		List<PkgDirectionDirectionFormulaList> list
			= pkgDirectionDirectionFormulaListDao.getList(directionDivision, directionNo, stepNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddDelList(final PkgDirectionFormulaListForm frm)  {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		// 工程データ未登録の場合エラー
		if (frm.getSeqCombo().size() == 1) {
			errMsg = new ActionMessage("errors.pkgdirection.no.procedure");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			frm.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (frm.getSearchFormList() == null) {
				errMsg = new ActionMessage("errors.pkgdirection.no.search");
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				frm.setDirtyFlg(null);
			}
		}
		return errMsgs;
	}

	/**
	 * 登録時のチェックを行う.<br>
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgDirectionFormulaListForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm.getSearchFormList() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}
		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < frm.getSearchFormList().size(); i++) {
			PkgDirectionDirectionFormulaList bean = frm.getSearchFormList().get(i);

			// 品目マスタを検索
			Item itemBean = itemDao.getEntity(bean.getItemCd());
			if (itemBean == null) {
				// データが存在しない場合
				errMsg = new ActionMessage
				("errors.pkgdirection.no.item.row", String.valueOf(i + 1));
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}

			// 親品目チェック(詰替・貼替時のみ)
			if (PkgDirectionConst.DIRECTION_DIVISION_REPACK.toString()
										.equals(frm.getDirectionDivision())) {
				// 種別＝0:製品の場合
				if (PkgDirectionConst.TYPE_DIVISION_PRODUCT.equals(itemBean.getTypeDivision())) {
					// 親品目コードが異なる場合
					if (!convertNullToBlank(frm.getParentItemCd())
							.equals(convertNullToBlank(itemBean.getParentItemCd()))) {
						errMsg = new ActionMessage(
									"errors.pkgdirection.not.equal.parent.item.row"
									, String.valueOf(i + 1));
						errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					}
				}
			}
		}
		return errMsgs;
	}

	/**
	 * 製造指図フォーミュラ登録処理
	 * @param frm 包装指図－配合一覧画面 Form
	 * @param tantoCd 登録者
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgDirectionFormulaListForm frm, final String tantoCd) throws Exception {

		try {
			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
			String directionNo = frm.getDirectionNo();

			// 製造指図ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, directionNo,
				frm.getHeaderUpdateDate(), tantoCd);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			String errMsg = "errors.pkgdirection.stock.update";
			try {
				/* 在庫更新－配合指図取消 */
				if (!stock.cancelFormula(directionDivision, directionNo, tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

			// 行削除されたデータをテーブルより削除する
			if (frm.getDelFormList() != null) {
				for (PkgDirectionDirectionFormulaList bean : frm.getDelFormList()) {
					if (bean.getStrSeq() != null && !bean.getStrSeq().equals("")) {

						// 削除処理
						int delNum = pkgDirectionDirectionFormulaListDao.delete(bean);
						if (delNum != 1) {

							// 排他エラー
							throw new OptimisticLockRuntimeException();
						}
					}
				}
			}

			Map<String, List<PkgDirectionDirectionFormulaList>> stepNoBeanMap = Collections.synchronizedMap
					(new HashMap<String, List<PkgDirectionDirectionFormulaList>>());

			// STEP_NO単位のリストを格納するマップを作成する
			for (PkgDirectionDirectionFormulaList bean : frm.getSearchFormList()) {

				List<PkgDirectionDirectionFormulaList> tempList
					= stepNoBeanMap.get(bean.getStrStepNo());
				if (tempList == null) {
					// 該当のリストが存在しない場合は、新規にリストを作成しマップに登録する

					// リストを作成
					List<PkgDirectionDirectionFormulaList> stepNoBeanList
						= new ArrayList<PkgDirectionDirectionFormulaList>();

					// リストに追加
					stepNoBeanList.add(bean);

					// マップに登録
					stepNoBeanMap.put(bean.getStrStepNo(), stepNoBeanList);

				} else {
					// 該当のリストが存在する場合は、そのリストに追加する

					// リストに追加
					tempList.add(bean);
				}

			}
			PkgDirectionDirectionFormulaList lineBean = null;
			PkgDirectionDirectionFormulaList seqBean = null;

			// STEP_NOごとに更新処理を行う
			for (Map.Entry<String, List<PkgDirectionDirectionFormulaList>> stepNoBeanList
					: stepNoBeanMap.entrySet()) {

				// キー（STEP_NO）を取得
				BigDecimal stepNo = new BigDecimal(stepNoBeanList.getKey());
				// 値（List<PkgDirectionDirectionFormulaList>）を取得
				List<PkgDirectionDirectionFormulaList> beanList = stepNoBeanList.getValue();

				// 最終LINE_NO を取得
				lineBean = pkgDirectionDirectionFormulaListDao.getLastLineNo
					(directionDivision, directionNo, stepNo);
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				seqBean = pkgDirectionDirectionFormulaListDao.getLastSeq
					(directionDivision, directionNo, stepNo);
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < stepNoBeanList.getValue().size(); i++) {

					PkgDirectionDirectionFormulaList bean = stepNoBeanList.getValue().get(i);

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
				if (!stock.entryFormula(directionDivision, directionNo, tantoCd)) {
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
	 * 製造指図フォーミュラの更新・登録処理
	 * @param tantoCd 更新者
	 * @param beanList　配合一覧リスト
	 */
	private void insertBean(final String tantoCd, final List<PkgDirectionDirectionFormulaList> beanList) {

		// 更新処理
		for (PkgDirectionDirectionFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {

				// 登録情報
				Timestamp inputDate = AecDateUtils.getCurrentTimestamp();
				bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
				bean.setLineType(PkgDirectionConst.LINE_TYPE_COMBINE);
				bean.setUpdateDate(inputDate); // 更新日時
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(inputDate); // 登録日時

				// 登録処理
				int insertNum = pkgDirectionDirectionFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			} else {

				// 更新処理
				int updateNum = pkgDirectionDirectionFormulaListDao.update(bean);
				if (updateNum != 1) {
					// 排他エラー
					throw new OptimisticLockRuntimeException();
				}
			}
		}
	}

	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final PkgDirectionFormulaListForm frm) {
		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : frm is empty.パラメータチェック.getSearchList");
		}
		if (frm.getDirectionDivision() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : directionDivision is empty.パラメータチェック.getSearchList");
		}
		if (frm.getDirectionNo() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : directionNo is empty.パラメータチェック.getSearchList");
		}
		if (frm.getProcStepNo() == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : procStepNo is empty.パラメータチェック.getSearchList");
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
	 * 包装指図－製造指図フォーミュラDao設定
	 * @param pkgDirectionDirectionFormulaListDao 包装指図－製造指図フォーミュラDao
	 */
	public void setPkgDirectionDirectionFormulaListDao
		(final PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao) {
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
