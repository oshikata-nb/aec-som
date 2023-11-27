/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.direction.DirectionUtil;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 包装実績－ロット検索画面ロジック 実装クラス.
 * @author tosco
 */
public class PkgRdirectionLotInventorySearchLogicImpl implements
		PkgRdirectionLotInventorySearchLogic {

	/** 包装実績－ロット検索画面Dao */
	private PkgRdirectionLotInventorySearchListDao pkgRdirectionLotInventorySearchListDao;

	/** 包装実績－製造指図フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionLotInventorySearchLogicImpl() {
	}

	/**
	 * ロット一覧検索処理
	 * @param frm 包装実績－ロット検索画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setList(final PkgRdirectionLotInventorySearchForm frm)
			throws NoDataException {

		/* 検索条件を取得 */
		PkgRdirectionLotInventorySearchListPagerCondition condition = (PkgRdirectionLotInventorySearchListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setItemCd(frm.getSrhItemCd());
		condition.setLotNo(frm.getSrhLotNo());
		condition.setDirectionDivision(new BigDecimal(frm
				.getDirectionDivision()));
		condition.setDirectionNo(frm.getDirectionNo());
		condition.setStepNo(new BigDecimal(frm.getStepNo()));
		condition.setLineNo(new BigDecimal(frm.getLineNo()));

		List<PkgRdirectionLotInventorySearchList> list = pkgRdirectionLotInventorySearchListDao
				.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		frm.setSearchList(list);

		BigDecimal sumQty = null;
		BigDecimal totalQty = BigDecimal.ZERO;
		String qty = null;
		String unitDivHaigo = PkgRdirectionConst.UNIT_DIVISION_HAIGO;
		String unitDivAdjust = PkgRdirectionConst.UNIT_DIVISION_ADJUST;
		for (PkgRdirectionLotInventorySearchList bean : list) {

			// 在庫数量フォーマット編集
			qty = checkDigitUtilsLogic.format(unitDivHaigo, bean
					.getInventoryQty());
			bean.setStrInventoryQty(qty);

			// 使用数フォーマット編集
			qty = checkDigitUtilsLogic
					.format(unitDivHaigo, bean.getResultQty());
			bean.setStrUseQty(qty);
			if (bean.getResultQty() != null) {
				if (sumQty == null) {
					sumQty = BigDecimal.ZERO;
				}
				sumQty = sumQty.add(bean.getResultQty());
			}

			// サンプルフォーマット編集
			qty = checkDigitUtilsLogic
					.format(unitDivHaigo, bean.getSampleQty());
			bean.setStrSampleQty(qty);
			if (bean.getSampleQty() != null) {
				if (sumQty == null) {
					sumQty = BigDecimal.ZERO;
				}
				sumQty = sumQty.add(bean.getSampleQty());
			}

			// ロスフォーマット編集
			qty = checkDigitUtilsLogic.format(unitDivHaigo, bean.getLossQty());
			bean.setStrLossQty(qty);
			if (bean.getLossQty() != null) {
				if (sumQty == null) {
					sumQty = BigDecimal.ZERO;
				}
				sumQty = sumQty.add(bean.getLossQty());
			}

			// 不良フォーマット編集
			qty = checkDigitUtilsLogic
					.format(unitDivHaigo, bean.getDefectQty());
			bean.setStrDefectQty(qty);
			// if (bean.getDefectQty() != null) {
			// if (sumQty == null) {
			// sumQty = BigDecimal.ZERO;
			// }
			// sumQty = sumQty.add(bean.getDefectQty());
			// }

			// 調整数量フォーマット編集
			qty = checkDigitUtilsLogic.format(unitDivAdjust, bean
					.getAdjustQty());
			bean.setStrAdjustQty(qty);
			if (bean.getAdjustQty() != null) {
				if (sumQty == null) {
					sumQty = BigDecimal.ZERO;
				}
				sumQty = sumQty.add(bean.getAdjustQty());
			}

			// 使用量フォーマット編集
			if (sumQty != null) {
				qty = checkDigitUtilsLogic.format(unitDivHaigo, sumQty);
				bean.setStrUseSumQty(qty);
				totalQty = totalQty.add(sumQty);
				sumQty = null;
			}
		}

		// 使用数を設定
		BigDecimal useQty = AecNumberUtils.convertBigDecimal(frm.getUseQty());
		qty = checkDigitUtilsLogic.format(unitDivHaigo, useQty);
		frm.setUseQty(qty);

		// total数を設定
		qty = checkDigitUtilsLogic.format(unitDivHaigo, totalQty);
		frm.setTotalQty(qty);
	}

	/**
	 * 在庫更新キャンセル処理
	 * @param bean 包装指図ヘッダー情報
	 * @param tantoCd 登録者
	 */
	private void cancelInventoryUpdate(
			final PkgRdirectionDirectionFormulaList bean, final String tantoCd) {
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.pkgrdirection.stock.update";

		try {
			/* 在庫更新－配合実績取消 */
			if (!stock.deResultFormula(bean.getStepNo(), bean.getLineNo(), bean
					.getDirectionDivision(), bean.getDirectionNo(), tantoCd)) {
				LogicExceptionEx ex = new LogicExceptionEx(errMsg);
				throw ex;
			}
		} catch (LogicExceptionEx e) {
			LogicExceptionEx ex = new LogicExceptionEx(errMsg);
			throw ex;
		}
	}

	/**
	 * 在庫更新を行う
	 * @param bean 包装指図ヘッダー情報
	 * @param tantoCd 登録者
	 */
	private void inventoryUpdate(final PkgRdirectionDirectionFormulaList bean,
			final String tantoCd) {
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.pkgrdirection.stock.update";

		try {
			/* 在庫更新－配合実績入力 */
			if (!stock.resultFormula(bean.getStepNo(), bean.getLineNo(), bean
					.getDirectionDivision(), bean.getDirectionNo(), tantoCd)) {
				LogicExceptionEx ex = new LogicExceptionEx(errMsg);
				throw ex;
			}

			// ステータスが、検査待ち在庫計上又は、製品検査入力済みの場合
			// if
			// (PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_WAIT.equals(bean.getDirectionStatus())
			// || PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT.equals
			// (bean.getDirectionStatus())) {
			/* 在庫更新－包装指図検査待ち移行 */
			// if (!stock.inspectionDirection
			// (bean.getDirectionDivision(), bean.getDirectionNo(), tantoCd)) {
			// LogicExceptionEx ex = new LogicExceptionEx(errMsg);
			// throw ex;
			// }
			// ステータスが、製品検査入力済みの場合
			// if (PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT.equals
			// (bean.getDirectionStatus())) {
			/* 在庫更新－包装指図製品完成 */
			// if (!stock.gradeDirection
			// (bean.getDirectionDivision(), bean.getDirectionNo(), tantoCd)) {
			// LogicExceptionEx ex = new LogicExceptionEx(errMsg);
			// throw ex;
			// }
			// }
			// }
		} catch (LogicExceptionEx e) {
			LogicExceptionEx ex = new LogicExceptionEx(errMsg);
			throw ex;
		}
	}

	/**
	 * 使用ロット在庫の情報を設定する
	 * @param bean 配合実績登録情報
	 * @param lotBean 使用ロット在庫情報
	 * @return PkgRdirectionDirectionFormulaList 使用ロット在庫情報設定データ
	 */
	private PkgRdirectionDirectionFormulaList setLotInventoryInfo(
			final PkgRdirectionDirectionFormulaList bean,
			final PkgRdirectionLotInventorySearchList lotBean) {

		PkgRdirectionDirectionFormulaList baseBean = bean;
		BigDecimal stockpdQty = null;

		// 入荷ロットを設定
		baseBean.setLotNo(lotBean.getLotNo());
		// メーカーロットを設定
		baseBean.setManufacturerLotNo(lotBean.getAliasLotNo());
		// ロケーション
		baseBean.setLocationCd(lotBean.getLocationCd());

		// 実績数量設定
		BigDecimal qty = AecNumberUtils.convertBigDecimal(lotBean
				.getStrUseQty());
		if (qty == null) {
			qty = BigDecimal.ZERO;
		}
		baseBean.setResultQty(qty);
		stockpdQty = baseBean.getResultQty();

		// ｻﾝﾌﾟﾙ設定
		baseBean.setSampleQty(AecNumberUtils.convertBigDecimal(lotBean
				.getStrSampleQty()));
		if (baseBean.getSampleQty() != null) {
			if (stockpdQty != null) {
				stockpdQty = stockpdQty.add(baseBean.getSampleQty());
			} else {
				stockpdQty = baseBean.getSampleQty();
			}
		}

		// ロス数量設定
		baseBean.setLossQty(AecNumberUtils.convertBigDecimal(lotBean
				.getStrLossQty()));
		if (baseBean.getLossQty() != null) {
			if (stockpdQty != null) {
				stockpdQty = stockpdQty.add(baseBean.getLossQty());
			} else {
				stockpdQty = baseBean.getLossQty();
			}
		}

		// 不良設定
		baseBean.setDefectQty(AecNumberUtils.convertBigDecimal(lotBean
				.getStrDefectQty()));
		// if (baseBean.getDefectQty() != null) {
		// if (stockpdQty != null) {
		// stockpdQty = stockpdQty.add(baseBean.getDefectQty());
		// } else {
		// stockpdQty = baseBean.getDefectQty();
		// }
		// }

		// 調整数量設定
		baseBean.setAdjustQty(AecNumberUtils.convertBigDecimal(lotBean
				.getStrAdjustQty()));
		if (baseBean.getAdjustQty() != null) {
			if (stockpdQty != null) {
				stockpdQty = stockpdQty.add(baseBean.getAdjustQty());
			} else {
				stockpdQty = baseBean.getAdjustQty();
			}
		}

		// 在庫引落量
		baseBean.setStockpdQty(stockpdQty);
		return baseBean;
	}

	/**
	 * 製造指図フォーミュラ登録処理
	 * @param frm 包装実績－ロット検索画面 Form
	 * @param tantoCd 登録者
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void regist(final PkgRdirectionLotInventorySearchForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		try {
			BigDecimal directionDivision = new BigDecimal(frm
					.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());
			BigDecimal seq = null;
			PkgRdirectionDirectionFormulaList baseBean = null;

			// 製造指図ヘッダー検索
			DirectionHeader headerBean = directionHeaderDao.getEntity(
				directionDivision, directionNo);
			if (headerBean == null) {

				// 更新データなし
				throw new OptimisticLockRuntimeException();
			}

			// 該当ステップNoの全てのデータを取得する
			List<PkgRdirectionDirectionFormulaList> list = pkgRdirectionDirectionFormulaListDao
					.getList(directionDivision, directionNo, stepNo);
			if (list.isEmpty()) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

			// 最終LINE_NO を取得
			PkgRdirectionDirectionFormulaList lineBean = pkgRdirectionDirectionFormulaListDao
					.getLastLineNo(directionDivision, directionNo, stepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			for (PkgRdirectionDirectionFormulaList bean : list) {

				// 該当の配合データを検索する
				if (bean.getLineNo().toString().equals(frm.getLineNo())) {
					seq = bean.getSeq();
				}
				if (seq != null) {
					if (baseBean == null) {

						// 登録用ベースを作成
						baseBean = new PkgRdirectionDirectionFormulaList();
						IgnoreCaseBeanUtils.copyProperties(baseBean, bean);
						baseBean.setInputDate(AecDateUtils
								.getCurrentTimestamp());
						baseBean.setInputorCd(tantoCd);
						baseBean.setUpdateDate(baseBean.getInputDate());
						baseBean.setUpdatorCd(tantoCd);

						// 指定されたロットの数分、配合データを複数行に分けて登録
						for (PkgRdirectionLotInventorySearchList lotBean : frm
								.getRegistList()) {

							// 使用したロット在庫の情報を設定する
							baseBean = setLotInventoryInfo(baseBean, lotBean);

							if (baseBean.getLineNo() != null) {
								// 在庫更新をキャンセルする
								cancelInventoryUpdate(bean, tantoCd);

								baseBean.setInputDate(bean.getInputDate());
								baseBean.setInputorCd(bean.getInputorCd());
								baseBean.setUpdateDate(bean.getUpdateDate());

								// 該当レコードを更新する
								if (pkgRdirectionDirectionFormulaListDao
										.update(baseBean) != 1) {
									/* 排他エラー */
									throw new OptimisticLockRuntimeException();
								}

								// 在庫更新を行う
								inventoryUpdate(baseBean, tantoCd);

								// 予定数量をゼロに設定
								baseBean.setQty(BigDecimal.ZERO);
								baseBean.setLineNo(null);

								// 投入順を加算
								seq = seq.add(BigDecimal.ONE);
							} else {
								// 行No設定
								baseBean.setLineNo(lastLineNo);
								// 投入順設定
								baseBean.setSeq(seq);

								// 該当レコードを登録する
								if (pkgRdirectionDirectionFormulaListDao
										.insert(baseBean) != 1) {
									/* 排他エラー */
									throw new OptimisticLockRuntimeException();
								}
								// 在庫更新を行う
								inventoryUpdate(baseBean, tantoCd);

								baseBean.setLineNo(null);
								// 行Noを加算
								lastLineNo = lastLineNo.add(BigDecimal.ONE);
								// 投入順を加算
								seq = seq.add(BigDecimal.ONE);
							}
						}
					} else {
						// 以降のレコードのSeq番号を更新する
						bean.setSeq(seq);
						bean.setUpdatorCd(tantoCd);
						if (pkgRdirectionDirectionFormulaListDao.update(bean) != 1) {
							/* 排他エラー */
							throw new OptimisticLockRuntimeException();
						}
						// 投入順を加算
						seq = seq.add(BigDecimal.ONE);
					}
				}
			}

			// 該当データなしの場合は、排他エラー
			if (seq == null) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
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
				"errors.pkgrdirection.result.date.null");
		}

		return errors;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－ロット検索画面Dao設定.
	 * @param pkgRdirectionLotInventorySearchListDao 包装実績－ロット検索画面Dao
	 */
	public void setPkgRdirectionLotInventorySearchListDao(
			final PkgRdirectionLotInventorySearchListDao pkgRdirectionLotInventorySearchListDao) {
		this.pkgRdirectionLotInventorySearchListDao = pkgRdirectionLotInventorySearchListDao;
	}

	/**
	 * 包装実績－製造指図フォーミュラDao設定
	 * @param pkgRdirectionDirectionFormulaListDao 包装実績－製造指図フォーミュラDao
	 */
	public void setPkgRdirectionDirectionFormulaListDao(
			final PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao) {
		this.pkgRdirectionDirectionFormulaListDao = pkgRdirectionDirectionFormulaListDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
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
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

}
