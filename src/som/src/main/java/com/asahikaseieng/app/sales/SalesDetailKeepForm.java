/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordList;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderList;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 売上詳細(預り品) Formクラス.
 * @author tosco
 */
public final class SalesDetailKeepForm extends AbstractSalesDetailForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド

	/** 得意先一覧 */
	private List<SalesDetailKeepVenderList> venderList;

	/** 出庫ロケーション一覧 */
	private List<SalesDetailKeepSalesInoutRecordList> locationList;

	/** 出庫ロケーション削除リスト */
	private List<SalesDetailKeepSalesInoutRecordList> deleteLocationList;

	/** ロケーション選択行インデックス */
	private int lineIndex;

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 増付数(文字列) */
	private String strMatss;

	/** 入力区分 */
	private BigDecimal inputDivision;

	/** 前回入力品目コード */
	private String prevItemCd;


	/**
	 * コンストラクタ.出荷指図詳細（花王・その他）
	 */
	public SalesDetailKeepForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		} else if ("initNew".equals(getOp())) {
			clear();
		} else if ("addRow".equals(getOp())) {
			clearCheck();
		} else if ("delRow".equals(getOp())) {
			clearCheck();
		} else if ("insert".equals(getOp())) {
			clearCheck();
		} else if ("update".equals(getOp())) {
			clearCheck();
		}

	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("addRow".equals(getOp())) {
			// 行追加時のチェック
			errors = new ActionErrors();
			this.validateAddRow(errors, request);
		} else if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 明細のチェック
			validateDetail(errors, request);
		} else if ("initContinue".equals(getOp())) {
			/* イレギュラーだけど、ここでフォームをクリア */
			this.clearNew();
		}

		return errors;
	}

	/**
	 * 入力データの検証（更新時）
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateDetail(final ActionErrors errors,
			final HttpServletRequest request) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		// 品目の数値チェック
		validateSalesQuantity(getStrSalesQuantity(), getUnitDivision(),
			getVenderCd(), rb.getString("item.sales.keep.sales.quantity"),
			check, errors);

		// 受注データが存在しない売上の場合、出庫ロケーションのチェックを行う。
		if (StringUtils.isEmpty(getOrderNo())) {
			// 出庫ロケーションリスト
			List<SalesDetailKeepSalesInoutRecordList> locationList = getLocationList();
			if (locationList == null || locationList.size() == 0) {
				// 出庫ロケーションが1件も存在しない場合、エラーとする
				errors.add("", new ActionMessage(
						"errors.sales.required.disbursement.location", ""));
			} else {
				// ロケーション数分チェック
				int lineNo = 0;
				HashMap<String, BigDecimal> hMap = new HashMap<String, BigDecimal>();
				for (SalesDetailKeepSalesInoutRecordList locationBean : locationList) {
					lineNo++;
					// ロケーションコード
					validateLocationCd(locationBean.getLocationCd(), lineNo,
						errors);
					// 数量
					validateInoutQty(locationBean, getUnitDivision(),
						getVenderCd(), lineNo, check, errors, hMap);
				}
			}
		}
	}

	/**
	 * ロケーションの検証(行番号指定)
	 * @param locationCd ロケーションコード
	 * @param lineNo 行番号
	 * @param errors 検証エラー内容
	 */
	private void validateLocationCd(final String locationCd, final int lineNo,
			final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(locationCd)) {
			errors.add("",
				new ActionMessage("errors.required.row", rb
						.getString("item.sales.location"), Integer
						.toString(lineNo)));
		}
	}

	/**
	 * 行追加時入力データの検証
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateAddRow(final ActionErrors errors,
			final HttpServletRequest request) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 品目コード必須チェック
		if (StringUtils.isEmpty(getItemCd())) {
			errors.add("", new ActionMessage("errors.required", rb
					.getString("item.sales.item.cd")));
		}
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		super.clear();

		/** 納入先宛先 */
		setDeliveryAddress(null);

		/** 増付数(文字列) */
		setStrMatss(null);

		/** 入力区分 */
		setInputDivision(BigDecimal.ZERO);

		/** 得意先一覧 */
		setVenderList(new ArrayList<SalesDetailKeepVenderList>());

		/** 出庫ロケーション一覧 */
		setLocationList(new ArrayList<SalesDetailKeepSalesInoutRecordList>());

		/** 出庫ロケーション削除一覧 */
		setDeleteLocationList(new ArrayList<SalesDetailKeepSalesInoutRecordList>());

		/** ロケーション選択行インデックス */
		setLineIndex(-1);

		/** 前回入力品目コード */
		this.setPrevItemCd(null);
	}

	/**
	 * クリア処理.
	 */
	protected void clearNew() {
		/** 売上日付(文字列) */
		// setStrSalesDate(null);
		/** 売上番号 */
		setSalesNo(null);
		/** 売上番号 */
		setCancelSalesNo(null);
		/** 伝票発行済区分名称 */
		setSlipPublishCompName(null);
		/** 受注番号 */
		setOrderNo(null);
		/** 売上区分名称 */
		setCategoryName(null);
		/** 勘定年月(文字列) */
		setStrAccountYears(null);
		/** 勘定年月(文字列) */
		setStrBeforeAccountYears(null);
		/** 売上区分(分類コード) */
		setCategoryDivision(null);
		/** 品目コード */
		setItemCd(null);
		/** 品目名称 */
		setItemName(null);
		/** 他社コード1 */
		setOtherCompanyCd1(null);
		/** 得意先コード */
		setVenderCd(null);
		/** 得意先名称 */
		setVenderName1(null);
		// 得意先略称
		setVenderShortedName(null);
		/** 売上数量(文字列) */
		setStrSalesQuantity(null);
		/** 売上単価(文字列) */
		setStrSalesUnitprice(null);
		/** 標準単価 */
		setStandardUnitprice(BigDecimal.ZERO);
		/** 標準値引 */
		setStandardDiscount(BigDecimal.ZERO);
		/** 特別値引 */
		setSpecialDiscount(BigDecimal.ZERO);
		/** 標準単価(文字列) */
		setStrStandardUnitprice(null);
		/** 標準値引(文字列) */
		setStrStandardDiscount(null);
		/** 特別値引(文字列) */
		setStrSpecialDiscount(null);
		/** 売上金額 */
		setStrSalesAmount(null);
		/** 仮単価FLG */
		setTmpUnitpriceFlg(false);
		/** 納入先コード */
		// setDeliveryCd(null);
		/** 納入先名称 */
		// setDeliveryName1(null);
		// 納入先略称
		// setSearchKana(null);
		/** 納入先住所 */
		// setAddress(null);
		/** 納入先電話番号 */
		// setTelNo(null);
		/** 会計部門借方部門コード */
		setAccountDebitSectionCd(null);
		/** 会計部門借方部門名称 */
		setAccountDebitSectionName(null);
		/** 借方科目コード */
		setDebitTitleCd(null);
		/** 借方科目名称 */
		setDebitTitleName(null);
		/** 会計部門貸方部門コード */
		setAccountCreditSectionCd(null);
		/** 会計部門貸方部門名称 */
		setAccountCreditSectionName(null);
		/** 貸方科目コード */
		setCreditTitleCd(null);
		/** 貸方科目名称 */
		setCreditTitleName(null);
		/** 入庫ロケーション */
		setHousingLocationCd(null);
		/** 包装指図番号 */
		setPackageDirectionNo(null);
		/** 製品ロット番号 */
		setProductLotno(null);
		/** 理由コード */
		setRyCd(null);
		/** 理由内容 */
		setRyDescription(null);
		/** 備考(摘要) */
		setRemark(null);
		/** 担当部署 */
		setChargeOrganizationCd(null);
		/** 担当部署名称 */
		setChargeOrganizationName(null);
		/** 預り品フラグ */
		setKeepFlag(null);
		/** 荷姿 */
		setStyleOfPacking(null);
		/** 単位区分 */
		setUnitDivision(null);
		/** 小数点桁数 */
		setDecimalPoint(null);
		/** 端数区分 */
		setRound(null);
		/** 小数点桁数(URTANKA) */
		setDecimalPointUrTanka(null);
		/** 端数区分(URTANKA) */
		setRoundUrTanka(null);
		/** 小数点桁数(URKINGAKU) */
		setDecimalPointUrKingaku(null);
		/** 端数区分(URKINGAKU) */
		setRoundUrKingaku(null);
		/** 帳合コード */
		setBalanceCd(null);
		/** フォーカスフィールドID */
		setFocusId(null);
		/** 品目チェック済みフラグ */
		setItemCheckedFlag(false);
		/** 月次更新済みフラグ */
		setMonthlyUpdateDivision(0);
		/** 取消元データフラグ */
		setCancelOriginFlag(0);
		/** 売上トランザクション更新日付 */
		setUpdateDate(null);
		/** 売上トランザクション更新日付(取消元) */
		setUpdateDateOrigin(null);
		/** 納入先宛先 */
		setDeliveryAddress(null);

		/** 増付数(文字列) */
		setStrMatss(null);

		/** 入力区分 */
		setInputDivision(BigDecimal.ZERO);

		/** 得意先一覧 */
		setVenderList(new ArrayList<SalesDetailKeepVenderList>());

		/** 出庫ロケーション一覧 */
		setLocationList(new ArrayList<SalesDetailKeepSalesInoutRecordList>());

		/** 出庫ロケーション削除一覧 */
		setDeleteLocationList(new ArrayList<SalesDetailKeepSalesInoutRecordList>());

		/** ロケーション選択行インデックス */
		setLineIndex(-1);

		/** 備考(摘要) */
		setSummary(null);

		/** 前回入力品目コード */
		this.setPrevItemCd(null);

		// 2014/2/3 新消費税対応 ->
		setStrTaxAmount(null);

		setStrTaxDivision(null);

		// setStrTaxRatio(null);
		// 2014/2/3 新消費税対応 <-
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 得意先一覧を取得します。
	 * @return venderList 得意先一覧
	 */
	public List<SalesDetailKeepVenderList> getVenderList() {
		return venderList;
	}

	/**
	 * 得意先一覧を設定します。
	 * 
	 * @param venderList 得意先一覧
	 */
	public void setVenderList(final List<SalesDetailKeepVenderList> venderList) {
		this.venderList = venderList;
	}

	/**
	 * 出庫ロケーション一覧を取得します。
	 * @return locationList 出庫ロケーション一覧
	 */
	public List<SalesDetailKeepSalesInoutRecordList> getLocationList() {
		return locationList;
	}

	/**
	 * 出庫ロケーション一覧を設定します。
	 * 
	 * @param locationList 出庫ロケーション一覧
	 */
	public void setLocationList(
			final List<SalesDetailKeepSalesInoutRecordList> locationList) {
		this.locationList = locationList;
	}

	/**
	 * 出庫ロケーション削除一覧を取得します。
	 * @return deleteLocationList 出庫ロケーション削除一覧
	 */
	public List<SalesDetailKeepSalesInoutRecordList> getDeleteLocationList() {
		return deleteLocationList;
	}

	/**
	 * 出庫ロケーション削除一覧を設定します。
	 * 
	 * @param deleteLocationList 出庫ロケーション削除一覧
	 */
	public void setDeleteLocationList(
			final List<SalesDetailKeepSalesInoutRecordList> deleteLocationList) {
		this.deleteLocationList = deleteLocationList;
	}

	/**
	 * ロケーション選択インデックスを取得します。
	 * @return int ロケーション選択インデックス
	 */
	public int getLineIndex() {
		return lineIndex;
	}

	/**
	 * ロケーション選択インデックスを設定します。
	 * @param lineIndex ロケーション選択インデックス
	 */
	public void setLineIndex(final int lineIndex) {
		this.lineIndex = lineIndex;
	}

	/**
	 * 出庫ロケーション数を取得する。
	 * @return 出庫ロケーション
	 */
	public int getLocationCount() {
		int count = 0;
		if (locationList != null) {
			count = locationList.size();
		}
		return count;
	}

	/**
	 * 納入先宛先取得
	 * @return String 納入先宛先
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 増付数(文字列)を取得します。
	 * @return String 増付数(文字列)
	 */
	public String getStrMatss() {
		return strMatss;
	}

	/**
	 * 増付数(文字列)を設定します。
	 * @param strMatss 増付数(文字列)
	 */
	public void setStrMatss(final String strMatss) {
		this.strMatss = strMatss;
	}

	/**
	 * 入力区分取得
	 * @return BigDecimal 入力区分
	 */
	public BigDecimal getInputDivision() {
		return this.inputDivision;
	}

	/**
	 * 入力区分設定
	 * @param inputDivision 入力区分
	 */
	public void setInputDivision(final BigDecimal inputDivision) {
		this.inputDivision = inputDivision;
	}

	/**
	 * 前回入力品目コードを取得します。
	 * @return String 前回入力品目コード
	 */
	public String getPrevItemCd() {
		return prevItemCd;
	}

	/**
	 * 前回入力品目コードを設定します。
	 * @param prevItemCd 前回入力品目コード
	 */
	public void setPrevItemCd(final String prevItemCd) {
		this.prevItemCd = prevItemCd;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getLocationList() != null) {
			for (SalesDetailKeepSalesInoutRecordList location : getLocationList()) {
				location.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 出庫ロケーション数量の検証
	 * @param locationBean 出庫ロケーションデータ
	 * @param unitDivision 単位区分
	 * @param venderCd 取引先コード
	 * @param lineNo 行番号
	 * @param check 数値桁数チェックロジッククラス
	 * @param errors 検証エラー内容
	 * @param hMap ロケーション、ロット単位の使用数
	 */
	protected void validateInoutQty(
			final SalesDetailKeepSalesInoutRecordList locationBean,
			final String unitDivision, final String venderCd, final int lineNo,
			final CheckDigitUtilsLogic check, final ActionErrors errors,
			final HashMap<String, BigDecimal> hMap) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		String strInoutQty = locationBean.getStrQty();
		// 必須チェック
		if (StringUtils.isEmpty(strInoutQty)) {
			errors.add("", new ActionMessage("errors.required.row", rb
					.getString("item.sales.keep.inout.qty"), String
					.valueOf(lineNo)));
		}
		String value = StringUtils.replace(strInoutQty, ",", "");
		// 数値チェック
		if (!StringUtils.isEmpty(strInoutQty)) {
			CheckDigitResult result = check.checkDigit(unitDivision,
				SalesConst.VENDER_DIVISION_TS, venderCd, value);
			int code = result.getCode();
			if (code != CheckDigitUtilsLogic.SUCCESS) {
				NumberChkDisitDetail detailDisit = result.getDetail();
				switch (code) {
				case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
					errors.add("", new ActionMessage("errors.digit.number.row",
							rb.getString("item.sales.keep.inout.qty"), Integer
									.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
					errors.add("", new ActionMessage(
							"errors.digit.maxlength.row", rb
									.getString("item.sales.keep.inout.qty"),
							detailDisit.getMaxLength().toString(), Integer
									.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
					errors.add("", new ActionMessage(
							"errors.digit.integer.row", rb
									.getString("item.sales.keep.inout.qty"),
							detailDisit.getIntegerLength().toString(), Integer
									.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
					errors.add("", new ActionMessage(
							"errors.digit.decimal.row", rb
									.getString("item.sales.keep.inout.qty"),
							detailDisit.getSmallnumLength().toString(), Integer
									.toString(lineNo)));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
					errors.add("", new ActionMessage(
							"errors.sales.rang.quantity.row", rb
									.getString("item.sales.keep.inout.qty"),
							check.format(unitDivision,
								SalesConst.VENDER_DIVISION_TS, venderCd,
								SalesConst.QUANTITY_MIN), detailDisit
									.getUpperLimit().toString(), Integer
									.toString(lineNo)));
					break;
				default:
					break;
				}
			} else {
				// 入力可能数範囲チェック
				// 範囲チェック(0より大きい値となっているかチェック)
				BigDecimal val = AecNumberUtils.convertBigDecimal(value);
				if (val.compareTo(SalesConst.QUANTITY_MIN) <= 0) {
					NumberChkDisitDetail checkDetail = check.getCheckDigit(
						unitDivision, SalesConst.VENDER_DIVISION_TS, venderCd);
					errors.add("", new ActionMessage(
							"errors.sales.rang.quantity.row", rb
									.getString("item.sales.keep.inout.qty"),
							check.format(unitDivision,
								SalesConst.VENDER_DIVISION_TS, venderCd,
								SalesConst.QUANTITY_MIN), checkDetail
									.getUpperLimit().toString(), String
									.valueOf(lineNo)));
				}
				// 可能数量 = 手持在庫 ＋ 検査待
				BigDecimal inventoryQty = super.convertBigDecimal(locationBean
						.getStrInventoryQty());
				BigDecimal inspectionQty = super.convertBigDecimal(locationBean
						.getStrInspectionQty());
				BigDecimal possible = inventoryQty.add(inspectionQty);
				// 変更の場合、元の数量分を加算する
				possible = possible.add(locationBean.getPrevQty());
				// 削除対象のなかで、取消対象となる在庫を加算する
				for (SalesDetailKeepSalesInoutRecordList bean : deleteLocationList) {
					if (locationBean.getLocationCd().equals(
						bean.getLocationCd())) {
						possible = possible.add(bean.getPrevQty());
					}
				}
				// 同ロケーション、同ロットの入力チェックを行う
				String locationLot = locationBean.getLocationCd()
						+ locationBean.getLotNo();
				BigDecimal useQty = hMap.get(locationLot);
				if (useQty != null) {
					errors.add("", new ActionMessage(
							"errors.sales.out.location.duplicate.row", Integer
									.toString(lineNo)));
				} else {

					// 丸め処理
					BigDecimal possibleRd = check.round(unitDivision,
						SalesConst.VENDER_DIVISION_TS, venderCd, possible);
					// 範囲チェック(可能数以下となっているかチェック)
					if (val.compareTo(possibleRd) > 0) {
						errors.add("", new ActionMessage(
								"errors.sales.rang.inout.qty.max.row",
								rb.getString("item.sales.keep.inout.qty"),
								Integer.toString(lineNo)));
					} else {
						locationBean.setQty(val);
					}
					hMap.put(locationLot, val);
				}
			}
		}
	}
}
