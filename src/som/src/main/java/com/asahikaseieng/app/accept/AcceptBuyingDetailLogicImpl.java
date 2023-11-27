/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.comboboxes.ComboboxesBean;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.dao.entity.master.bumon.Bumon;
import com.asahikaseieng.dao.entity.master.bumon.BumonDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailList;
import com.asahikaseieng.dao.nonentity.accept.AcceptBuyingDetailListDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptDetailSalesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxesDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 受入入力詳細 ロジック実装クラス
 * @author tosco
 */
public class AcceptBuyingDetailLogicImpl implements AcceptBuyingDetailLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 区分 仕入単価 */
	public static final String UNIT_DIVISION_SITANKA = "SITANKA";

	/** 区分 運賃 */
	public static final String UNIT_DIVISION_UNTIN = "UNTIN";

	/** 区分 仕入金額 */
	public static final String UNIT_DIVISION_SIKINGAKU = "SIKINGAKU";

	/** 区分 消費税額 */
	public static final String UNIT_DIVISION_TAX_AMOUNT = "TAX_AMOUNT";

	/** 仕入ステータス 1:未承認 */
	public static final BigDecimal STATUS2_NOTAPPROVAL = new BigDecimal(1);

	/** 仕入ステータス 2:承認依頼中 */
	public static final BigDecimal STATUS2_APPROVALREQUEST = new BigDecimal(2);

	/** ﾃﾞｰﾀ種別 3:仕入 */
	public static final BigDecimal DATA_TYPE_SIIRE = new BigDecimal(3);

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 算出区分 1:明細単位 */
	public static final BigDecimal CALC_DIVISION_MEISAI = new BigDecimal(1);

	/** 算出区分 4:自社ﾏｽﾀ */
	public static final BigDecimal CALC_DIVISION_COMPANY = new BigDecimal(4);

	/** 消費税課税区分 1:外税 */
	public static final BigDecimal TAX_DIVISION_OUT = new BigDecimal(1);

	/** 消費税課税区分 2:内税 */
	public static final BigDecimal TAX_DIVISION_IN = new BigDecimal(2);

	/** ﾃﾞｰﾀ集計区分 2:返品 * */
	public static final String DATA_TOTAL_DIVISION_HENPIN = "2";

	/** ﾃﾞｰﾀ集計区分 3:値引き * */
	public static final String DATA_TOTAL_DIVISION_NEBIKI = "3";

	/** 仕入入力 詳細画面用Dao */
	private AcceptBuyingDetailListDao acceptBuyingDetailListDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 仕入区分コンボボックス用DAO */
	private AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao;

	/** 会計部門マスタ用Dao */
	private BumonDao bumonDao;

	/** 受入入力 売上トラン登録用データ取得Dao */
	private AcceptDetailSalesDao acceptDetailSalesDao;

	// 軽減税率対応
	private TaxMasterListForComboboxesDao taxMasterListForComboboxesDao;

	private CommonLogic commonLogic;

	/**
	 * コンストラクタ.
	 */
	public AcceptBuyingDetailLogicImpl() {
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @param frm 仕入入力画面FORM
	 */
	public void createAcceptStockingDivisionCombobox(final AcceptBuyingDetailForm frm) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		String[] categoryDivisionList = null;
		String[] reversalFlgList = null;

		// 仕入区分検索
		List<AcceptStockingDivisionComboboxes> list = acceptStockingDivisionComboboxesDao.findStockingDivision();
		if (list != null) {
			categoryDivisionList = new String[list.size()];
			reversalFlgList = new String[list.size()];
			int index = 0;
			for (AcceptStockingDivisionComboboxes bean : list) {
				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(bean.getCategoryDivision().toString());
				combo.setLabales(bean.getCategoryDivision().toString() + ":"	+ bean.getCategoryName().toString());
				res.add(combo);

				// 分類コードリスト
				categoryDivisionList[index] = bean.getCategoryDivision();
				// 仕分反転フラグリスト
				if (DATA_TOTAL_DIVISION_HENPIN.equals(bean.getDataTotalDivision()) || DATA_TOTAL_DIVISION_NEBIKI.equals(bean
								.getDataTotalDivision())) {
					// ﾃﾞｰﾀ集計区分=2:返品,3:値引の場合
					reversalFlgList[index] = "1";
				} else {
					reversalFlgList[index] = "0";
				}

				index++;
			}
		}

		frm.setStockinDivisionCombo(res);
		frm.setCategoryDivisionList(categoryDivisionList);
		frm.setReversalFlgList(reversalFlgList);
	}

	/**
	 * 購買データ検索処理、数値フォーマット処理を行なう.
	 * @param slipNo 仕入番号
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<AcceptBuyingDetailList> 仕入入力画面データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<AcceptBuyingDetailList> getEntity(final String slipNo,	final CheckDigitUtilsLogic check) throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strWherehousingUnitprice = null;
		String strFareAmount = null;
		String strStockingAmount = null;
		String strAcceptQuantity = null;
		String strIncreaseQuantity = null;
		String strStockingQuantity = null;
		BigDecimal acceptConvertQuantitySum = new BigDecimal(0);
		String strAcceptConvertQuantitySum = null;

		// 会社コード取得
		String companyCd = acceptDetailSalesDao.getCompanyCd();

		List<AcceptBuyingDetailList> list = acceptBuyingDetailListDao.getEntity(slipNo, companyCd);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (AcceptBuyingDetailList bean : list) {
			// 発注数量計算
			calcStockAmount(bean);
			// 発注数量
			strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = check.format(UNIT_DIVISION_KG,VENDER_DIV_SI, bean.getVenderCd(), bean.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

			// 仕入単価
			strWherehousingUnitprice = check.format(UNIT_DIVISION_SITANKA,VENDER_DIV_SI, bean.getVenderCd(), bean.getHousingUnitprice());
			bean.setStrWherehousingUnitprice(strWherehousingUnitprice);

			// 運賃
			strFareAmount = check.format(UNIT_DIVISION_UNTIN, VENDER_DIV_SI,bean.getVenderCd(), bean.getFareAmount());
			bean.setStrFareAmount(strFareAmount);

			// 仕入金額
			strStockingAmount = check.format(UNIT_DIVISION_SIKINGAKU,VENDER_DIV_SI, bean.getVenderCd(), bean.getStockingAmount());
			bean.setStrStockingAmount(strStockingAmount);

			// 受入数量
			strAcceptQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,	bean.getVenderCd(), bean.getAcceptQuantity());
			bean.setStrAcceptQuantity(strAcceptQuantity);

			// 増付数量
			strIncreaseQuantity = check.format(bean.getUnitDiv(),VENDER_DIV_SI, bean.getVenderCd(), bean.getIncreaseQuantity());
			bean.setStrIncreaseQuantity(strIncreaseQuantity);

			// 仕入数量
			strStockingQuantity = check.format(bean.getUnitDiv(),VENDER_DIV_SI, bean.getVenderCd(), bean.getStockingQuantity());
			bean.setStrStockingQuantity(strStockingQuantity);

			// 受入重量を加算する
			if (bean.getAcceptConvertQuantity() != null) {
				acceptConvertQuantitySum = acceptConvertQuantitySum.add(bean.getAcceptConvertQuantity());
			}
		}

		// 受入重量合計
		strAcceptConvertQuantitySum = check.format(UNIT_DIVISION_KG,VENDER_DIV_SI, list.get(0).getVenderCd(), acceptConvertQuantitySum);
		list.get(0).setStrAcceptConvertQuantitySum(strAcceptConvertQuantitySum);
		return list;
	}

	/**
	 * 消費税額算出
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 仕入入力画面Form
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void calcStockAmount(final AcceptBuyingDetailList bean) {

		BigDecimal calcDiv = bean.getCalcDivision(); // 取引先ﾏｽﾀ.算出区分
		BigDecimal compCalcDiv = bean.getCompCalcDivision(); // 自社ﾏｽﾀ.消費税算出区分

		// 取引先ﾏｽﾀ.算出区分＝1(明細)の場合
		// 取引先ﾏｽﾀ.算出区分＝4(自社ﾏｽﾀ) かつ 自社ﾏｽﾀ.消費税算出区分＝1(明細)の場合
		if (CALC_DIVISION_MEISAI.equals(calcDiv)	|| (CALC_DIVISION_COMPANY.equals(calcDiv) && CALC_DIVISION_MEISAI.equals(compCalcDiv))) {

			// 消費税課税区分=1:外税または2:内税
			if (TAX_DIVISION_IN.equals(bean.getTaxDivision())) {
				BigDecimal stockingAmount = bean.getStockingAmount(); // 仕入金額
				BigDecimal taxRatio = bean.getTaxAmount(); // 消費税率
				if (stockingAmount != null && taxRatio != null) {
					stockingAmount = stockingAmount.add(taxRatio);
				}
				bean.setStockingAmount(stockingAmount);
			}
		}
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 会計部門マスタ、科目マスタにデータがない場合はエラーとする。
	 * @param frm 仕入入力画面FORM
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final AcceptBuyingDetailForm frm) {
		ActionMessages errors = new ActionMessages();
		ResourceBundle rb = ResourceBundle.getBundle(Constants.APPLICATION_PROPERTIES);

		// 会計部門マスタ検索(借方)
		Bumon bumonDebitBean = bumonDao.getEntity(frm.getAccountDebitSectionCd());
		if (bumonDebitBean == null) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.bumon", rb.getString("item.accept.account.debit.section.cd"));
		}

		// 会計部門マスタ検索(貸方)
		Bumon bumonCreditBean = bumonDao.getEntity(frm.getAccountCreditSectionCd());
		if (bumonCreditBean == null) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.bumon", rb.getString("item.accept.account.credit.section.cd"));
		}

		// 科目マスタ検索(借方)
		int debitCnt = acceptBuyingDetailListDao.getCountAccounts(frm.getDebitTitleCd());
		if (debitCnt == 0) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.accounts", rb.getString("item.accept.debit.title.cd"));
		}

		// 科目マスタ検索(貸方)
		int creditCnt = acceptBuyingDetailListDao.getCountAccounts(frm.getCreditTitleCd());
		if (creditCnt == 0) {
			// データが存在しない場合
			errors = addError(errors, "errors.accept.no.accounts", rb.getString("item.accept.credit.title.cd"));
		}

		return errors;
	}

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * @param frm 仕入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param check 数値項目用表示ロジッククラス
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final AcceptBuyingDetailForm frm, final String tantoCd,final CheckDigitUtilsLogic check) throws NoDataException, Exception {
		try {
			int count = 0;
			for (AcceptBuyingDetailList detailBean : frm.getDetailList()) {
				PurchaseSubcontract updBean = new PurchaseSubcontract();

				// 先頭データのみ設定
				if (count == 0) {
					updBean.setHousingUnitprice(AecNumberUtils.convertBigDecimal(frm.getStrHousingUnitprice())); // 仕入単価
					updBean.setFareAmount(AecNumberUtils.convertBigDecimal(frm.getStrFareAmount())); // 運賃
					updBean.setTaxDivision(AecNumberUtils.convertBigDecimal(frm.getTaxDivision())); // 消費税課税区分

					updBean.setTaxRatio(new BigDecimal(commonLogic.getTaxRatio(updBean.getTaxCd(), null, null, null, null)));
					updBean.setTaxDivision(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getTaxDivision())));

					updBean.setStockingAmount(AecNumberUtils.convertBigDecimal(frm.getStrInvoiceAmount())); 								// 軽減措置仕入金額
					updBean.setTaxAmount(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getStrInvoiceTaxAmount()))); // 軽減措置消費税金額

					// 2014/2/26 新消費税対応 <-

				}
				// 更新項目セット
				setCommonData(updBean, frm, tantoCd, check);

				// 支払先コードの設定
				updBean.setPayeeCd(detailBean.getPaymentInvoiceCd());

				// ロット分割データ
				updBean.setPurchaseNo(detailBean.getPurchaseNo()); // 購買NO
				updBean.setIncreaseQuantity(detailBean.getIncreaseQuantity()); // 増付数量
				updBean.setStockingQuantity(detailBean.getStockingQuantity()); // 仕入数量
				updBean.setUpdateDate(detailBean.getUpdateDate()); // 更新日時

				// 運賃がNullの場合運賃0とする updateUnlessNull対策
				if (updBean.getFareAmount() == null) {
					updBean.setFareAmount(BigDecimal.ZERO);
				}

				// 増付数量がNullの場合増付数量0とする updateUnlessNull対策
				if (updBean.getIncreaseQuantity() == null) {
					updBean.setIncreaseQuantity(BigDecimal.ZERO);
				}

				// 購買外注オーダ更新処理
				purchaseSubcontractDao.updateUnlessNull(updBean);

				count++;
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 購買外注オーダー.仕入ステータスの更新処理を行う.
	 * @param frm 仕入入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws OptimisticLockRuntimeException 排他エラー
	 */
	public void approve(final AcceptBuyingDetailForm frm, final String tantoCd)	throws OptimisticLockRuntimeException {
		try {
			for (AcceptBuyingDetailList detailBean : frm.getDetailList()) {
				PurchaseSubcontract updBean = new PurchaseSubcontract();
				updBean.setPurchaseNo(detailBean.getPurchaseNo()); // 購買NO
				updBean.setStatus2(STATUS2_APPROVALREQUEST); // 仕入ｽﾃｰﾀｽ(承認依頼中)
				updBean.setUpdateDate(detailBean.getUpdateDate()); // 更新日時
				updBean.setUpdatorCd(tantoCd); // 更新者ID

				// 購買外注オーダ更新処理
				purchaseSubcontractDao.updateUnlessNull(updBean);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 仕入単価を取得する.
	 * @param frm 仕入入力画面FORM
	 * @param check 数値項目用表示ロジッククラス
	 */
	public void getHousingUnitprice(final AcceptBuyingDetailForm frm,	final CheckDigitUtilsLogic check) {

		// テーブル'UNITPRICE'から仕入単価を取得
		AcceptBuyingDetailList bean = acceptBuyingDetailListDao.getHousingUnitprice(frm.getVenderCd(), frm.getItemCd(),AecNumberUtils.convertBigDecimal(frm.getSumStockingQuantity()));

		if (bean != null) {
			// 仕入単価
			frm.setStrHousingUnitprice(check.format(UNIT_DIVISION_SITANKA,VENDER_DIV_SI, frm.getVenderCd(), bean.getHousingUnitprice()));
		}
	}

	/**
	 * 仕入入力画面の更新項目の値設定
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 仕入入力画面Form
	 * @param tantoCd 担当者コード
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void setCommonData(final PurchaseSubcontract updBean,final AcceptBuyingDetailForm frm, final String tantoCd,final CheckDigitUtilsLogic check) {

		// 分類マスタ検索
		BigDecimal dataTotalDivision = acceptBuyingDetailListDao.getDataTotalDivision(frm.getCategoryDivision());
		updBean.setDataType(DATA_TYPE_SIIRE); // データ種別(3:仕入)
		updBean.setCategoryDivision(frm.getCategoryDivision()); // 仕入区分(分類コード)
		updBean.setDataTotalDivision(dataTotalDivision); // 分類コード

		updBean.setStockingDate(AecDateUtils.getTimestampYmdFormat(frm.getStrStockingDate())); // 仕入日付
		String stockingDate = frm.getStrStockingDate().replace("/", "");
		updBean.setAccountYears(stockingDate.substring(0, 6)); // 勘定年月(仕入日の年月(YYYYMM))

		updBean.setAccountDebitSectionCd(frm.getAccountDebitSectionCd()); // 会計部門借方コード
		updBean.setAccountCreditSectionCd(frm.getAccountCreditSectionCd()); // 会計部門貸方コード
		updBean.setDebitTitleCd(frm.getDebitTitleCd()); // 借方科目コード
		updBean.setCreditTitleCd(frm.getCreditTitleCd()); // 貸方科目コード
		updBean.setOrderSheetRemark2(frm.getOrderSheetRemark2()); // 発注書備考
		updBean.setRemark2(frm.getRemark2()); // 備考

		updBean.setTaxDivision(AecNumberUtils.convertNullToZero(AecNumberUtils.convertBigDecimal(frm.getTaxDivision())));
		updBean.setTaxCd(frm.getTaxCd());
		updBean.setTaxRatio(new BigDecimal(commonLogic.getTaxRatio(updBean.getTaxCd(), null, null, null, null)));

		updBean.setStatus2(STATUS2_NOTAPPROVAL); // 仕入ｽﾃｰﾀｽ(未承認)
		updBean.setUpdatorCd(tantoCd); // 更新者

	}

	/**
	 * メッセージを追加する
	 *
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	private ActionMessages addError(final ActionMessages errors,final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	// 軽減税率対応
	/**
	 * 消費税率コンボボックス用データ取得
	 *
	 * @param locale            言語コード
	 * @return bean            コンボボックス
	 * @throws Exception            NoDataException
	 */
	public ComboboxesBean getPurchaseTaxCombobox() throws NoDataException {

		ComboboxesBean bean = new ComboboxesBean();

		// コンボボックスデータを取得
		List<TaxMasterListForComboboxes> list = taxMasterListForComboboxesDao.getPurchaseTaxListForComboboxes();
		if (list == null) { throw new NoDataException(); }

		String[] values = new String[list.size()];
		String[] labels = new String[list.size()];
		String[] keys   = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getComboboxName();
			values[i] = list.get(i).getTaxCd();
			keys[i]   = list.get(i).getTaxRatio();
		}

		bean.setLabels(labels);
		bean.setValues(values);
		bean.setInvisibility(keys);
		return bean;
	}


	/* -------------------- setter -------------------- */

	/**
	 * 仕入入力詳細画面用Daoを設定します。
	 * @param acceptBuyingDetailListDao 仕入入力詳細画面用Dao
	 */
	public void setAcceptBuyingDetailListDao(final AcceptBuyingDetailListDao acceptBuyingDetailListDao) {
		this.acceptBuyingDetailListDao = acceptBuyingDetailListDao;
	}

	/**
	 * 購買外注オーダ更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買外注オーダ更新用Dao
	 */
	public void setPurchaseSubcontractDao(final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 仕入区分コンボボックス用DAOを設定します。
	 * @param acceptStockingDivisionComboboxesDao 仕入区分コンボボックス用DAO
	 */
	public void setAcceptStockingDivisionComboboxesDao(final AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao) {
		this.acceptStockingDivisionComboboxesDao = acceptStockingDivisionComboboxesDao;
	}

	/**
	 * 会計部門マスタDaoを設定します。
	 * @param bumonDao 会計部門マスタDao
	 */
	public void setBumonDao(final BumonDao bumonDao) {
		this.bumonDao = bumonDao;
	}

	/**
	 * 受入入力 売上トラン登録用データ取得Daoを設定します。
	 * @param acceptDetailSalesDao 受入入力 売上トラン登録用データ取得Dao
	 */
	public void setAcceptDetailSalesDao(final AcceptDetailSalesDao acceptDetailSalesDao) {
		this.acceptDetailSalesDao = acceptDetailSalesDao;
	}

	/**
	 * taxMasterListForComboboxesDaoを設定します。
	 * @param taxMasterListForComboboxesDao taxMasterListForComboboxesDao
	 */
	public void setTaxMasterListForComboboxesDao(TaxMasterListForComboboxesDao taxMasterListForComboboxesDao) {
		this.taxMasterListForComboboxesDao = taxMasterListForComboboxesDao;
	}

	/**
	 * commonLogicを設定します。
	 * @param commonLogic commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}
}
