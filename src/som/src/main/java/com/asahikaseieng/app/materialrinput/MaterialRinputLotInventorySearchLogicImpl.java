/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.materialrinput;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.app.rdirection.RdirectionConst;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjection;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjectionDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchListDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputLotInventorySearchListPagerCondition;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面ロジック 実装クラス.
 * @author tosco
 */
public class MaterialRinputLotInventorySearchLogicImpl implements
		MaterialRinputLotInventorySearchLogic {

	/** 外注原材料投入実績入力画面_ロット検索ポップアップ画面Dao */
	private MaterialRinputLotInventorySearchListDao lotInventorySearchListDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 在庫数量換算ロジッククラス */
	private ConvInventoryLogic convInventoryLogic;

	/** 購買外注原材料投入実績用Dao */
	private PurchaseMateInjectionDao purchaseMateInjectionDao;

	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputLotInventorySearchLogicImpl() {
	}

	/**
	 * ロット検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<RdirectionLotInventorySearchList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<MaterialRinputLotInventorySearchList> getSearchList(
			final MaterialRinputLotInventorySearchListPagerCondition condition)
			throws NoDataException {

		checkParams(condition);

		List<MaterialRinputLotInventorySearchList> list = lotInventorySearchListDao
				.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (MaterialRinputLotInventorySearchList bean : list) {
			BigDecimal inventoryQty = bean.getInventoryQty();

			// 在庫数量
			bean.setStrInventoryQty(checkDigitUtilsLogic.format(
				MaterialRinputLotInventorySearchForm.UNIT_DIVISION_HAIGO,
				inventoryQty));

			// 荷姿、端数算出
			ConvInventoryResult result = convInventoryLogic.inventoryToPack(
				bean.getItemCd(), inventoryQty);
			if (BigDecimal.ONE.compareTo(result.getCode()) == 0) {
				bean.setStrInoutQty(null);
				bean.setStrFraction(null);
			} else {
				// 荷姿数
				bean.setStrInoutQty(checkDigitUtilsLogic.format(bean
						.getUnitDiv(), result.getPackQty()));
				// 端数(重量)
				bean.setStrFraction(checkDigitUtilsLogic.format(
					RdirectionConst.UNIT_DIV_HAIGO, result.getFractionWeight()));
			}
		}
		return list;
	}

	/**
	 * 在庫引落情報登録処理
	 * 
	 * @param frm ロット検索画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws Exception 例外
	 */
	public void insert(final MaterialRinputLotInventorySearchForm frm, final String tantoCd)
		throws Exception {

		PurchaseMateInjection baseBean = null;
		PurchaseMateInjection addBean = null;
		BigDecimal seq = BigDecimal.ONE;
		BigDecimal stepNo = new BigDecimal(frm.getSrhStepNo());
		BigDecimal lineNo = new BigDecimal(frm.getSrhLineNo());
		BigDecimal recipeId = new BigDecimal(frm.getSrhRecipeId());

		try {
			baseBean = purchaseMateInjectionDao.getEntity
				(frm.getSrhBuySubcontractOrderNo(), lineNo, recipeId, BigDecimal.ZERO, stepNo);
			if (baseBean == null) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
			addBean = new PurchaseMateInjection();

			// 値を登録用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(addBean, baseBean);
			addBean.setInputorCd(tantoCd);
			addBean.setInputDate(AecDateUtils.getCurrentTimestamp());
			addBean.setUpdatorCd(tantoCd);
			addBean.setUpdateDate(addBean.getInputDate());
			addBean.setQty(null);
			addBean.setResultQty(null);
			addBean.setSampleQty(null);
			addBean.setLossQty(null);
			addBean.setDefectQty(null);
			addBean.setAdjustQty(null);

			for (MaterialRinputLotInventorySearchList bean : frm.getRegistList()) {
				addBean.setSeq(seq);
				addBean.setLocationCd(bean.getLocationCd());
				addBean.setLotNo(bean.getLotNo());
				addBean.setManufacturerLotNo(bean.getAliasLotNo());
				addBean.setStockpdQty(AecNumberUtils.convertBigDecimal(bean.getStrResultQty()));

				// 購買外注原材料投入実績に登録
				purchaseMateInjectionDao.insert(addBean);

				// SEQを加算
				seq = seq.add(BigDecimal.ONE);
			}

			// 使用量を全て在庫から引落
			baseBean.setStockpdQty(baseBean.getQty());
			// 購買外注原材料投入実績更新処理
			purchaseMateInjectionDao.update(baseBean);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			String errMsg = "errors.materialrinput.move.stock.update";
			try {
				/* 在庫更新－外注投入実績入力（一投入）*/
				if (!stock.resultInjection(baseBean.getStepNo(), baseBean.getLineNo(),
					baseBean.getRecipeId(), baseBean.getBuySubcontractOrderNo(), tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final MaterialRinputLotInventorySearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面Dao設定.
	 * @param lotInventorySearchListDao 外注原材料投入実績入力画面_ロット検索ポップアップ画面Dao
	 */
	public void setLotInventorySearchListDao(
			final MaterialRinputLotInventorySearchListDao lotInventorySearchListDao) {
		this.lotInventorySearchListDao = lotInventorySearchListDao;
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
	 * 在庫数量換算ロジッククラスを設定します。
	 * @param convInventoryLogic 在庫数量換算ロジッククラス
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}

	/**
	 * 購買外注原材料投入実績用Dao設定.
	 * @param purchaseMateInjectionDao 購買外注原材料投入実績用Dao
	 */
	public void setPurchaseMateInjectionDao(
			final PurchaseMateInjectionDao purchaseMateInjectionDao) {
		this.purchaseMateInjectionDao = purchaseMateInjectionDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
