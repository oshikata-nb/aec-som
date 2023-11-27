/*
 * Created on 2009/03/26
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.materialrinput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjection;
import com.asahikaseieng.dao.entity.purchasemateinjection.PurchaseMateInjectionDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetail;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputDetailListDao;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 外注原材料投入実績入力 ロジック実装クラス
 * @author tosco
 */
public class MaterialRinputDetailLogicImpl implements MaterialRinputDetailLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 区分 配合 */
	public static final String UNIT_DIVISION_HAIGO = "HAIGO";

	/** 区分 配合(調整数量) */
	public static final String UNIT_DIVISION_HAIGO_ADJ = "HAIGO_ADJ";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 用途 3:製造 */
	public static final BigDecimal RECIPE_USE_SEIZO = new BigDecimal(3);

	/** 単位 Kg */
	public static final String UNIT_KG = "Kg";

	/** レシピコードとレシピバージョンの分離文字 */
	protected static final String RECIPE_CD_VERSION_SEPARATOR = ",";

	/** 外注原材料投入実績入力 ヘッダ部用Dao */
	private MaterialRinputDetailDao materialRinputDetailDao;

	/** 外注原材料投入実績入力 明細部用Dao */
	private MaterialRinputDetailListDao materialRinputDetailListDao;

	/** 外注原材料投入実績入力 処方フォーミュラ用Dao */
	private MaterialRinputRecipeFormulaListDao materialRinputRecipeFormulaListDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 購買外注原材料投入実績更新用Dao */
	private PurchaseMateInjectionDao purchaseMateInjectionDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputDetailLogicImpl() {
	}

	/**
	 * 購買外注オーダー検索処理を行う.
	 * @param buySubcontractOrderNo 発注番号
	 * @param check 数値項目用表示ロジッククラス
	 * @return MaterialRinputDetail ヘッダ部データ
	 * @throws NoDataException データが存在しない例外
	 */
	public MaterialRinputDetail getHeader(final String buySubcontractOrderNo,
			final CheckDigitUtilsLogic check) throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strGoodHousingSum = null;

		MaterialRinputDetail bean = materialRinputDetailDao
				.getHeader(buySubcontractOrderNo);
		if (bean == null) {
			throw new NoDataException();
		}

		// 数値フォーマット
		// 数量
		strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI, bean
				.getVenderCd(), bean.getOrderQuantity());
		bean.setStrOrderQuantity(strOrderQuantity);

		// 重量
		strOrderConvertQuantity = check.format(UNIT_DIVISION_KG, VENDER_DIV_SI,
			bean.getVenderCd(), bean.getOrderConvertQuantity());
		bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

		// 生産出来高
		strGoodHousingSum = check.format(bean.getUnitDiv(), VENDER_DIV_SI, bean
				.getVenderCd(), bean.getGoodHousingSum());
		bean.setStrGoodHousingSum(strGoodHousingSum);

		return bean;
	}

	/**
	 * 購買外注原材料投入実績検索処理を行う.
	 * @param frm 外注原材料投入実績入力 Formクラス
	 * @param check 数値項目用表示ロジッククラス
	 * @param venderCd 仕入先コード
	 * @return List<MaterialRinputDetailList> 明細部データ
	 */
	public List<MaterialRinputDetailList> getDetailList(
			final MaterialRinputDetailForm frm,
			final CheckDigitUtilsLogic check, final String venderCd) {
		String strQty = null;
		String strResultQty = null;
		String strSampleQty = null;
		String strLossQty = null;
		String strDefectQty = null;
		String strAdjustQty = null;
		int count = 0;

		List<MaterialRinputDetailList> list = materialRinputDetailListDao
				.getDetailList(frm.getBuySubcontractOrderNo());

		// 数値フォーマット
		for (MaterialRinputDetailList bean : list) {
			bean.setVenderCd(venderCd); // 仕入先コード

			// 使用数(数量)
			strQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI, venderCd,
				bean.getQty());
			bean.setStrQty(strQty);

			// 実績数量
			strResultQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI,
				venderCd, bean.getResultQty());
			bean.setStrResultQty(strResultQty);

			// サンプル
			strSampleQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI,
				venderCd, bean.getSampleQty());
			bean.setStrSampleQty(strSampleQty);

			// ロス数量
			strLossQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI,
				venderCd, bean.getLossQty());
			bean.setStrLossQty(strLossQty);

			// 不良
			strDefectQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI,
				venderCd, bean.getDefectQty());
			bean.setStrDefectQty(strDefectQty);

			// 調整数量
			strAdjustQty = check.format(UNIT_DIVISION_HAIGO_ADJ, VENDER_DIV_SI,
				venderCd, bean.getAdjustQty());
			bean.setStrAdjustQty(strAdjustQty);

			// 単位
			if (RECIPE_USE_SEIZO.equals(bean.getRecipeUse())) {
				// 製造の場合はKg固定
				bean.setUnit(UNIT_KG);
			}

			// 在庫引落済数を設定
			if (bean.getStockpdFlg() != null) {
				count = count + Integer.parseInt(bean.getStockpdFlg());
			}
		}

		// 在庫引落状態設定
		if (count == 0) {
			frm.setStockpdStateFlg("0"); // 在庫引落なし
		} else {
			if (list != null) {
				if (list.size() == count) {
					frm.setStockpdStateFlg("2"); // 在庫引落完了
				} else {
					frm.setStockpdStateFlg("1"); // 在庫引落入力中
				}
			} else {
				frm.setStockpdStateFlg("0"); // 在庫引落なし
			}
		}
		return list;
	}

	/**
	 * 基本処方の存在チェック
	 * @param frm 購買外注原材料投入実績入力用画面Form
	 * @return ActionMessages エラーメッセージ
	 */
	public ActionMessages checkRecipe(final MaterialRinputDetailForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessages errMsgs = new ActionMessages();

		// 初期化
		frm.setRecipeId(null);
		frm.setRecipeVersion(null);
		frm.setRecipeCd(null);

		// 入力された基本処方コード＋バージョンを分離する
		String[] strArray = StringUtils.split(frm.getRecipeCdVersion(),
			MaterialRinputDetailLogicImpl.RECIPE_CD_VERSION_SEPARATOR);
		if (strArray.length != 2) {
			// 入力フォーマット不正
			errMsgs = addError(errMsgs, "errors.materialrinput.nodata.master",
				rb.getString("item.materialrinput.recipe.cd.version"));
			return errMsgs;
		}

		String recipeCd = strArray[0];
		String strVersion = strArray[1];

		if (!AecStringUtils.isNumeric(strVersion)) {
			// バージョンの数値エラー
			errMsgs = addError(errMsgs, "errors.number", rb
					.getString("item.materialrinput.recipe.cd.version"));
			return errMsgs;
		}
		BigDecimal version = new BigDecimal(strVersion);

		// 処方ヘッダー検索
		MaterialRinputRecipeFormulaList headerBean = materialRinputRecipeFormulaListDao
				.getRecipeHeader(recipeCd, version, frm.getItemCd());
		if (headerBean == null) {
			// 基本処方なし
			errMsgs = addError(errMsgs, "errors.materialrinput.nodata.master",
				rb.getString("item.materialrinput.recipe.cd.version"));
			return errMsgs;
		} else {
			frm.setRecipeId(convertString(headerBean.getRecipeId()));
			frm.setRecipeCd(headerBean.getRecipeCd());
			frm.setRecipeVersion(convertString(headerBean.getRecipeVersion()));
			frm.setRecipeCdVersion(headerBean.getRecipeCd()
					+ MaterialRinputDetailLogicImpl.RECIPE_CD_VERSION_SEPARATOR
					+ headerBean.getRecipeVersion());
			frm.setRecipeName(headerBean.getRecipeName());
		}

		return errMsgs;
	}

	/**
	 * 指定した基本処方と生産出来高を元に、原材料の実績数量を計算したリストを作成する.
	 * @param frm 購買外注原材料投入実績入力用画面Form
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<MaterialRinputDetailList> 明細部データ
	 */
	public List<MaterialRinputDetailList> calculate(
			final MaterialRinputDetailForm frm, final CheckDigitUtilsLogic check) {

		String strResultQty = null;

		List<MaterialRinputRecipeFormulaList> formulaList = getRecipeFormulaList(frm
				.getRecipeId());
		BigDecimal stdQty = getFormul1001Qty(formulaList); // 標準生産量

		// 端数区分からBigDecimalの丸めモードに変換
		RoundingMode round = check.getRoundingMode(Integer.parseInt(frm
				.getRoundHaigo()));
		int decimalPoint = Integer.parseInt(frm.getDecimalPointHaigo());
		// 仕込数量÷標準生産量の比で数量を倍する
		BigDecimal multiple = null;
		if (stdQty != null) {
			BigDecimal goodHousingSum = AecNumberUtils.convertBigDecimal(frm
					.getStrGoodHousingSum());
			multiple = goodHousingSum.divide(stdQty, Constants.SYOSU_KETA,
				round);
		} else {
			multiple = new BigDecimal(1);
		}

		List<MaterialRinputDetailList> list = new ArrayList<MaterialRinputDetailList>();
		for (MaterialRinputRecipeFormulaList formula : formulaList) {
			if (MaterialRinputRecipeFormulaListDao.LINE_NO_INT == formula
					.getLineNo().intValue()) {
				// 1001なので読み飛ばし
				continue;
			}

			MaterialRinputDetailList detailBean = new MaterialRinputDetailList();
			detailBean.setBuySubcontractOrderNo(frm.getBuySubcontractOrderNo()); // 発注番号
			detailBean.setRecipeId(formula.getRecipeId()); // レシピインデックス
			detailBean.setRecipeCd(formula.getRecipeCd()); // レシピコード
			detailBean.setRecipeVersion(formula.getRecipeVersion()); // レシピバージョン
			detailBean.setStepNo(formula.getStepNo()); // STEP_NO
			detailBean.setLineNo(formula.getLineNo()); // LINE_NO
			detailBean.setSeq(BigDecimal.ZERO); // SEQ
			detailBean.setItemCd(formula.getItemCd()); // 品目コード
			detailBean.setItemName(formula.getItemName()); // 品目名称
			detailBean.setUnit(formula.getUnit()); // 単位
			detailBean.setVenderCd(frm.getVenderCd()); // 仕入先コード
			detailBean.setRecipeName(formula.getRecipeName()); // 基本処方名称
			detailBean.setStockpdFlg("0"); // 在庫引落なし

			// 仕込数量÷標準生産量の比で数量を倍する
			BigDecimal calc = formula.getQty().multiply(multiple).setScale(
				decimalPoint, round);
			detailBean.setResultQty(calc);

			// 実績数量
			strResultQty = check.format(UNIT_DIVISION_HAIGO, VENDER_DIV_SI, frm
					.getVenderCd(), calc);
			detailBean.setStrResultQty(strResultQty);
			detailBean.setStrQty(strResultQty); // 使用量

			list.add(detailBean);
		}
		return list;
	}

	/**
	 * 処方フォーミュラを取得する
	 * @param recipeId レシピインデックス
	 * @return List<MaterialRinputRecipeFormulaList> 処方フォーミュラリスト
	 */
	private List<MaterialRinputRecipeFormulaList> getRecipeFormulaList(
			final String recipeId) {
		List<MaterialRinputRecipeFormulaList> result = materialRinputRecipeFormulaListDao
				.getFormulaList(new BigDecimal(recipeId));
		return result;
	}

	/**
	 * 仕上げヘッダ(1001)行の標準生産量を取得する
	 * @param list 処方フォーミュラ配列
	 * @return 標準生産量
	 */
	private BigDecimal getFormul1001Qty(
			final List<MaterialRinputRecipeFormulaList> list) {
		BigDecimal res = null;
		for (MaterialRinputRecipeFormulaList bean : list) {
			if (MaterialRinputRecipeFormulaListDao.LINE_NO_INT == bean
					.getLineNo().intValue()) {
				// 仕上げヘッダ
				res = bean.getQty();
				break;
			}
		}
		return res;
	}

	/**
	 * 購買外注オーダー更新処理、購買外注原材料投入実績削除／登録処理を行う.
	 * 
	 * @param frm 外注原材料投入実績入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final MaterialRinputDetailForm frm, final String tantoCd)
			throws NoDataException, Exception {
		PurchaseMateInjection updBean = null;

		try {
			// 計算処理実行済みの場合
			if (frm.isCalcFlg()) {
				// 発注番号で購買外注オーダ検索
				List<PurchaseSubcontract> list = purchaseSubcontractDao
						.getListByBuySubOrderNo(frm.getBuySubcontractOrderNo());
				if (list.size() == 0) {
					throw new NoDataException();
				}
				// アスプローバからの基本処方デフォルト表示用データを削除
				updBean = purchaseMateInjectionDao.getEntity(list.get(0)
						.getBuySubcontractOrderNo(), BigDecimal.ZERO,
					new BigDecimal(frm.getRecipeId()), BigDecimal.ZERO,
					BigDecimal.ZERO);
				if (updBean != null) {
					// 購買外注原材料投入実績削除
					purchaseMateInjectionDao.delete(updBean);
				}
				for (PurchaseSubcontract bean : list) {
					bean.setGoodHousingSum(AecNumberUtils.convertBigDecimal(frm
							.getStrGoodHousingSum())); // 生産出来高
					bean.setUpdatorCd(tantoCd); // 更新者ID
					// 購買外注オーダ更新処理
					purchaseSubcontractDao.update(bean);
				}
			}

			purchaseMateInjectionDao.deleteByBuySubOrdNoSeqZero(frm
					.getBuySubcontractOrderNo());

			for (MaterialRinputDetailList bean : frm.getDetailList()) {
				BigDecimal sumUseQty = AecNumberUtils.convertBigDecimal(bean
						.getStrSumUseQty());
				PurchaseMateInjection injBean = new PurchaseMateInjection();
				injBean.setBuySubcontractOrderNo(bean
						.getBuySubcontractOrderNo()); // 発注番号
				injBean.setRecipeId(bean.getRecipeId()); // レシピインデックス
				injBean.setRecipeCd(bean.getRecipeCd()); // レシピコード
				injBean.setRecipeVersion(bean.getRecipeVersion()); // レシピバージョン
				injBean.setStepNo(bean.getStepNo()); // STEP_NO
				injBean.setLineNo(bean.getLineNo()); // LINE_NO
				injBean.setSeq(bean.getSeq()); // SEQ
				injBean.setItemCd(bean.getItemCd()); // 品目コード
				injBean.setQty(sumUseQty); // 使用数量
				if (bean.getStockpdFlg().equals("1")) {
					injBean.setStockpdQty(bean.getResultQty()); // 在庫引落量
				} else {
					injBean.setStockpdQty(BigDecimal.ZERO); // 在庫引落量
				}
				injBean.setResultQty(bean.getResultQty()); // 実績数量
				injBean.setSampleQty(bean.getSampleQty()); // サンプル
				injBean.setLossQty(bean.getLossQty()); // ロス数量
				injBean.setDefectQty(bean.getDefectQty()); // 不良数量
				injBean.setAdjustQty(bean.getAdjustQty()); // 調整数量
				injBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				injBean.setInputorCd(tantoCd); // 登録者
				injBean.setUpdateDate(injBean.getInputDate()); // 更新日時
				injBean.setUpdatorCd(tantoCd); // 更新者
				injBean.setLocationCd(bean.getLocationCd());
				injBean.setLotNo(bean.getLotNo());
				injBean.setManufacturerLotNo(bean.getManufacturerLotNo());

				// 購買外注原材料投入実績登録処理
				purchaseMateInjectionDao.insert(injBean);
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
	 * 在庫引落をリセットする
	 * 
	 * @param frm 外注原材料投入実績入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws Exception 例外
	 */
	public void reset(final MaterialRinputDetailForm frm, final String tantoCd)
			throws Exception {
		try {

			// 在庫引落リセット対象を取得
			MaterialRinputDetailList bean = frm.getDetailList().get(
				Integer.parseInt(frm.getLine()));

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			String errMsg = "errors.materialrinput.move.stock.update";
			try {
				/* 在庫更新－外注投入実績取消（一投入） */
				if (!stock.deResultInjection(bean.getStepNo(),
					bean.getLineNo(), bean.getRecipeId(), bean
							.getBuySubcontractOrderNo(), tantoCd)) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			// 購買外注原材料投入実績で、在庫引落情報のレコードを削除
			int delNum = materialRinputDetailListDao.deleteStockpdInfo(bean
					.getBuySubcontractOrderNo(), bean.getRecipeId(), bean
					.getStepNo(), bean.getLineNo());
			if (delNum == 0) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
			PurchaseMateInjection updBean = null;
			updBean = purchaseMateInjectionDao.getEntity(bean
					.getBuySubcontractOrderNo(), bean.getLineNo(), bean
					.getRecipeId(), bean.getSeq(), bean.getStepNo());
			if (updBean != null) {
				updBean.setStockpdQty(BigDecimal.ZERO);

				// 購買外注原材料投入実績更新処理
				purchaseMateInjectionDao.update(updBean);
			} else {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException(0);
		}
	}

	/**
	 * BigDecimalからStringへ型変換を行う
	 * @param decimal BigDecimal値
	 * @return String String型に変換した値
	 */
	private String convertString(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

	/**
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	private ActionMessages addError(final ActionMessages errors,
			final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 外注原材料投入実績入力 ヘッダ部用Daoを設定します。
	 * @param materialRinputDetailDao 外注原材料投入実績入力 ヘッダ部用Dao
	 */
	public void setMaterialRinputDetailDao(
			final MaterialRinputDetailDao materialRinputDetailDao) {
		this.materialRinputDetailDao = materialRinputDetailDao;
	}

	/**
	 * 外注原材料投入実績入力 明細部用Daoを設定します。
	 * @param materialRinputDetailListDao 外注原材料投入実績入力 明細部用Dao
	 */
	public void setMaterialRinputDetailListDao(
			final MaterialRinputDetailListDao materialRinputDetailListDao) {
		this.materialRinputDetailListDao = materialRinputDetailListDao;
	}

	/**
	 * 外注原材料投入実績入力 処方フォーミュラ用Daoを設定します。
	 * @param materialRinputRecipeFormulaListDao 外注原材料投入実績入力処方フォーミュラ用Dao
	 */
	public void setMaterialRinputRecipeFormulaListDao(
			final MaterialRinputRecipeFormulaListDao materialRinputRecipeFormulaListDao) {
		this.materialRinputRecipeFormulaListDao = materialRinputRecipeFormulaListDao;
	}

	/**
	 * 購買外注オーダ更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買外注オーダ更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 購買外注原材料投入実績更新用Daoを設定します。
	 * @param purchaseMateInjectionDao 購買外注原材料投入実績更新用Dao
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
