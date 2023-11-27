/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

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
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造実績－配合タブ ロジック実装クラス
 * @author tosco
 */
public class RdirectionFormulaListLogicImpl implements
		RdirectionFormulaListLogic {

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

	/** 配合データ取得用Dao * */
	private DirectionFormulaDao directionFormulaDao;

	/** ﾛｯﾄ番号を元にﾛｯﾄ在庫のﾛｹｰｼｮﾝを取得用Dao * */
	private RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public RdirectionFormulaListLogicImpl() {
	}

	/**
	 * 配合タブ－一覧検索処理
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return List<DirectionFormulaListForm> データ
	 * @throws NoDataException 例外
	 */
	public List<RdirectionDirectionFormulaList> getSearchList(
			final String directionNo, final BigDecimal procStepNo)
			throws NoDataException {

		checkParams(directionNo);
		checkParams(procStepNo);

		List<RdirectionDirectionFormulaList> list = rdirectionFormulaListDao
				.getSearchList(directionNo, procStepNo);

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
	public ActionMessages checkForAddDelList(
			final RdirectionFormulaListForm form) {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getSeqCombo().size() == 1) {
			errors = RdirectionUtil.addError(errors,
				"errors.direction.no.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除・行追加処理はエラー
			if (form.getSearchFormList() == null) {
				errors = RdirectionUtil.addError(errors,
					"errors.direction.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(
			final List<RdirectionDirectionFormulaList> searchFormList) {
		ActionMessages errors = new ActionMessages();

		if (searchFormList == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchFormList.size(); i++) {
			RdirectionDirectionFormulaList bean = searchFormList.get(i);

			// 品目マスタを検索
			Item opeBean = itemDao.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = DirectionUtil.addError(errors,
					"errors.direction.no.item.row", i + 1);
			}
			BigDecimal dd = opeBean.getLotDivision();
			if (dd != null && dd.equals(new BigDecimal(1))) {
				// 在庫引落量を設定
				BigDecimal stockpdQty = bean.getResultQty();
				if (bean.getLossQty() != null) {
					stockpdQty = stockpdQty.add(bean.getLossQty());
				}
				if (bean.getAdjustQty() != null) {
					stockpdQty = stockpdQty.add(bean.getAdjustQty());
				}
				bean.setStockpdQty(stockpdQty);
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
	public void regist(final RdirectionFormulaListForm frm,
			final RdirectionDirectionHeaderList header, final String tantoCd)
			throws Exception {
		String errMsg = "errors.rdirection.stock.update";
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());

		try {
			// 製造指図ヘッダー更新
			rdirectionCommonsLogic.updateDirectionHeader(header);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);

			// 削除処理
			if (frm.getDelFormList() != null) {
				for (RdirectionDirectionFormulaList bean : frm.getDelFormList()) {
					// 削除
					if (bean.getStrSeq() != null
							&& !bean.getStrSeq().equals("")) {
						try {
							if (bean.getStockpdQty() != null) {
								/* 在庫更新－配合実績取消(一投入) */
								if (!stock.deResultFormula(bean.getStepNo(),
									bean.getLineNo(), directionDivision, bean
											.getDirectionNo(), tantoCd)) {
									throw new LogicExceptionEx(errMsg);
								}
							}
							int delNum = rdirectionFormulaListDao.delete(bean);
							if (delNum != 1) {
								// 排他エラー
								throw new OptimisticLockRuntimeException();
							}
						} catch (LogicExceptionEx e) {
							throw new LogicExceptionEx(errMsg);
						}
					}
				}
			}

			Map<String, List<RdirectionDirectionFormulaList>> stepNoBeanMap = Collections
					.synchronizedMap(new HashMap<String, List<RdirectionDirectionFormulaList>>());

			// STEP_NOとSTEP_NOごとのリストのマップを作成する
			for (RdirectionDirectionFormulaList bean : frm.getSearchFormList()) {

				// 工程順序でマップを検索
				List<RdirectionDirectionFormulaList> tempList = stepNoBeanMap
						.get(bean.getStrStepNo());

				if (tempList == null) {

					// リストを作成
					List<RdirectionDirectionFormulaList> stepNoBeanList = new ArrayList<RdirectionDirectionFormulaList>();
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
			for (Map.Entry<String, List<RdirectionDirectionFormulaList>> stepNoBeanList : stepNoBeanMap
					.entrySet()) {

				// キー（STEP_NO） を取得
				String stepNo = stepNoBeanList.getKey();
				// 値（List<DirectionDirectionFormulaList>）を取得
				List<RdirectionDirectionFormulaList> beanList = stepNoBeanList
						.getValue();

				checkParams(frm.getDirectionNo());
				checkParams(new BigDecimal(stepNo));

				// 最終LINE_NO を取得
				RdirectionDirectionFormulaList lineBean = rdirectionFormulaListDao
						.getLastLineNo(frm.getDirectionNo(), new BigDecimal(
								stepNo));
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				RdirectionDirectionFormulaList seqBean = rdirectionFormulaListDao
						.getLastSeq(frm.getDirectionNo(),
							new BigDecimal(stepNo));
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < beanList.size(); i++) {

					RdirectionDirectionFormulaList bean = beanList.get(i);

					// ｻﾌﾞｽﾃｯﾌﾟ（表示順）の振り直し
					if ((!frm.getTempProcStepNo().equals("0"))
							&& (!frm.getTempProcStepNo().equals(
								bean.getStrStepNo()))) {
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
	private void insertBean(final String tantoCd,
			final List<RdirectionDirectionFormulaList> beanList) {

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.rdirection.stock.update";

		// 更新処理
		for (RdirectionDirectionFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			// 在庫引落量を設定
			BigDecimal stockpdQty = null;
			if (bean.getLossQty() != null) {
				stockpdQty = bean.getResultQty().add(bean.getLossQty());
			}
			if (bean.getAdjustQty() != null) {
				if (stockpdQty != null) {
					stockpdQty = stockpdQty.add(bean.getAdjustQty());
				} else {
					stockpdQty = bean.getResultQty().add(bean.getAdjustQty());
				}
			}
			if (stockpdQty == null) {
				stockpdQty = bean.getResultQty();
			}

			// 在庫引落ありの場合のみ再設定
			if (bean.getStockpdQty() != null) {
				bean.setStockpdQty(stockpdQty);
			}

			if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {
				try {

					// 登録情報
					bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
					bean.setLineType(new BigDecimal(-1));
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時

					// 登録処理
					int insertNum = rdirectionFormulaListDao.insert(bean);
					if (insertNum != 1) {
						// 一意制約エラー
						throw new DuplicateRuntimeException(0);
					}
					if (bean.getStockpdQty() != null) {
						/* 在庫更新－配合実績入力 */
						if (!stock.resultFormula(bean.getStepNo(), bean
								.getLineNo(), bean.getDirectionDivision(), bean
								.getDirectionNo(), tantoCd)) {
							throw new LogicExceptionEx(errMsg);
						}
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
			} else {
				try {
					RdirectionDirectionFormulaList oBean = rdirectionFormulaListDao
							.getByPrimaryKey(bean.getDirectionNo(), bean
									.getStepNo(), bean.getLineNo());
					StockinoutForUpdateChecker checker = new StockinoutForUpdateChecker(
							oBean);
					boolean flg = checker
							.notEqual(new StockinoutForUpdateChecker(bean));
					if (bean.getStockpdQty() != null && flg) {
						/* 在庫更新－配合実績取消(一投入) */
						if (!stock.deResultFormula(bean.getStepNo(), bean
								.getLineNo(), bean.getDirectionDivision(), bean
								.getDirectionNo(), tantoCd)) {
							throw new LogicExceptionEx(errMsg);
						}
					}
					// 更新処理 UnlessNullを外す
					int updateNum = rdirectionFormulaListDao.update(bean);
					if (updateNum != 1) {
						// 一意制約エラー
						throw new DuplicateRuntimeException(0);
					}
					if (bean.getStockpdQty() != null && flg) {
						/* 在庫更新－配合実績入力 */
						if (!stock.resultFormula(bean.getStepNo(), bean
								.getLineNo(), bean.getDirectionDivision(), bean
								.getDirectionNo(), tantoCd)) {
							throw new LogicExceptionEx(errMsg);
						}
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
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
	public boolean checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) {
		boolean ret = true;

		// ライン番号とステップ番号が無い場合はチェックを行わない
		if (lineNo == null || stepNo == null) {
			return ret;
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

						ret = false;

					}
				}
			}
		}
		return ret;
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
	 * フォーミュラDaoを設定します。
	 * @param rdirectionFormulaListDao 処方フォーミュラDao
	 */
	public void setDirectionFormulaListDao(
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
