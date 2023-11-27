/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentVender;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 支払入力－詳細画面 Formクラス(カスタマイズ)
 * @author tosco
 */
public final class PaymentCsmDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 支払日付 */
	private Date paymentDate;

	/** 支払日付(String型) */
	private String strPaymentDate;

	/** 支払番号 */
	private String slipNo;

	/** 支払先 */
	private String customerCd;

	/** 支払先名称 */
	private String customerName;

	/** 支払データリスト */
	private List<PaymentCsmBean> detailList;

	/** 更新フラグ */
	private int insertFlg;

	/** 入金分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	/** 科目コンボボックス */
	private List<ComboBoxItems> accountList;

	/** イベントが発生した行番号 */
	private int index;

	/** 会計部門 */
	private String accountSectionCode;

	/** 入金伝票発行区分 */
	private BigDecimal creditIssuedDivision;

	/** 科目Map */
	private Map<String, String> debitMap;

	/** 戻り先文字列 */
	private String backForward;

	/** 支払先AutoCompleteリスト */
	private List<AltPaymentVender> autoList;

	/** 支払残高 */
	private String payableAmount;

	/** 支払先の振込区分 */
	private int payingCheckDivision;

	/** 支払先の銀行コード */
	private String bankCd;

	/** 支払先の預金種別 */
	private String accountDivision;

	/** 支払先の口座番号 */
	private String accountNo;

	/** 支払先の銀行名 */
	private String bankName;

	/** 支払合計金額 */
	private String strSumAmount;

	/** 繰越残高 */
	private String strBalanceCarriedForward;

	/** 支払予定日 */
	private Timestamp paymentScheduledDate;

	/** 支払済金額 */
	private String strPaidAmount;

	/** 支払残高 */
	private String strBalanceForward;

	/** 相殺額 */
	private String strOffsetAmount;

	/** 支払合計 */
	private String strPaymentAmount;

	/** 仕入割引額 */
	private String strPurchaseDiscountAmount;

	/** 手数料 */
	private String strCommission;

	/** 支払月区分 */
	private BigDecimal creditMonthDivision;

	/** 手形サイト */
	private BigDecimal noteSight;

	/** 支払予定額 */
	private String strPaymentScheduledAmount;

	/** 借方部門コード */
	private String debitSectionCd;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 貸方部門コード */
	private String creditSectionCd;

	/** 承認ステータス */
	private String approvalStatus;

	/** 承認者 */
	private String approvaldby;

	/** 承認日時 */
	private Timestamp approvaldate;

	/** 休日メッセージ */
	private String holidayMsg;

	/** カーソル位置 */
	private String cursor;

	/** 支払予定リスト */
	private List<AltPayment> headerList;

	/**
	 * コンストラクタ.所属マスタ詳細
	 */
	public PaymentCsmDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);
		// 削除チェックボックスを初期化
		if (detailList != null) {
			for (PaymentCsmBean bean : detailList) {
				bean.setDeleteFlag(false);
			}
		}

		if ("init".equals(getOp())) {
			clear();
		}
		if ("initNew".equals(getOp())) {
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setDetailList(new ArrayList<PaymentCsmBean>());
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		// if ("setScheduled".equals(getOp())) {
		// errors = new ActionErrors();
		//
		// /* 支払日付のチェック */
		// this.validatePaymentDate(errors, request);
		// }

		return errors;
	}

	/**
	 * 支払日付のチェック
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	// private void validatePaymentDate(final ActionErrors errors,
	// final HttpServletRequest request) {
	// /* 支払日付が入力されている場合 */
	// if (StringUtils.isNotEmpty(getStrPaymentDate())) {
	// ResourceBundle rb = ResourceBundle
	// .getBundle(Constants.APPLICATION_PROPERTIES);
	// String paymentDate = getStrPaymentDate();
	//
	// /* 支払日付に / がない場合 */
	// if (paymentDate.indexOf("/") == 0) {
	// /* 日付文字列に変換 */
	// if (paymentDate.length() == 8) {
	// paymentDate = paymentDate.substring(0, 4) + "/"
	// + paymentDate.subSequence(4, 6) + "/"
	// + paymentDate.subSequence(6, 8);
	// } else if (paymentDate.length() == 6) {
	// paymentDate = paymentDate.substring(0, 2) + "/"
	// + paymentDate.subSequence(2, 4) + "/"
	// + paymentDate.subSequence(4, 6);
	// }
	// }
	//
	// /* 支払日付が正しくない場合 */
	// if (AecDateUtils.getTimestampYmdFormat(paymentDate) == null) {
	// /* クリア */
	// setHeaderList(new ArrayList<AltPayment>());
	// setDetailList(new ArrayList<PaymentCsmBean>());
	//
	// errors.add("", new ActionMessage("errors.date", rb
	// .getString("item.payment.srhPaymentDate")));
	// }
	// }
	// }
	/**
	 * クリア処理.
	 */
	protected void clear() {

		// 支払データリストのクリア
		setDetailList(new ArrayList<PaymentCsmBean>());

		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 支払日付
		setPaymentDate(null);
		// 支払日付
		setStrPaymentDate(null);
		// 支払番号
		setSlipNo(null);
		// 支払先
		setCustomerCd(null);
		// 請求先名称
		setCustomerName(null);
		// 新規更新切替フラグ
		setInsertFlg(PaymentCsmDetailAction.INSERT_FLAG_UPDATE);
		// 変更フラグ
		setDirtyFlg(null);
		// 明細データ
		setDetailList(null);
		// 入金分類コンボボックス内容
		setCategoryList(null);
		// 科目コンボボックス
		setAccountList(null);
		// イベントが発生した行番号
		setIndex(0);
		// 会計部門
		setAccountSectionCode(null);
		// 伝票発行区分
		setCreditIssuedDivision(null);
		// 科目Map
		setDebitMap(null);
		// 戻り先
		setBackForward(null);
		// 支払先AutoCompleteリスト
		setAutoList(null);
		// 繰越残
		setPayableAmount(null);
		// 振込区分
		setPayingCheckDivision(0);
		// 支払先の預金種別
		setAccountDivision(null);
		// 支払先の口座番号
		setAccountNo(null);
		// 支払先の銀行コード
		setBankCd(null);
		// 支払先の銀行名
		setBankName(null);
		// 支払予定日
		setPaymentScheduledDate(null);
		// 支払月区分
		setCreditMonthDivision(BigDecimal.ZERO);
		// 手形サイト
		setNoteSight(BigDecimal.ZERO);
		// 支払済金額
		setStrPaidAmount("0");
		// 支払残高
		setStrBalanceForward("0");
		// 相殺額
		setStrOffsetAmount("0");
		// 支払合計
		setStrPaymentAmount("0");
		// 仕入割引額
		setStrPurchaseDiscountAmount("0");
		// 手数料
		setStrCommission("0");
		// 支払予定額
		setStrPaymentScheduledAmount("0");
		// 繰越残
		setStrBalanceCarriedForward(null);
		// 借方部門コード
		setDebitSectionCd(null);
		// 借方科目コード
		setDebitTitleCd(null);
		// 貸方部門コード
		setCreditSectionCd(null);
		// 承認ステータス
		setApprovalStatus(null);
		// 承認者
		setApprovaldby(null);
		// 承認日時
		setApprovaldate(null);
		// 休日メッセージ
		setHolidayMsg(null);
		// カーソル位置
		setCursor(null);
		// 支払予定リスト
		setHeaderList(new ArrayList<AltPayment>());
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 * 
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 * 
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称取得.
	 * @return String 部署名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称設定.
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 支払日付取得.
	 * @return Date 支払日付
	 * 
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 支払日付設定.
	 * @param paymentDate 支払日付
	 * 
	 */
	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 支払日付(String型)取得.
	 * @return String 支払日付(String型)
	 */
	public String getStrPaymentDate() {
		return strPaymentDate;
	}

	/**
	 * 支払日付(String型)設定.
	 * @param strPaymentDate 支払日付(String型)
	 */
	public void setStrPaymentDate(final String strPaymentDate) {
		this.strPaymentDate = strPaymentDate;
	}

	/**
	 * 支払番号取得.
	 * @return String 支払番号
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 支払番号設定.
	 * @param slipNo 支払番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 支払先取得.
	 * @return String 支払先
	 */
	public String getCustomerCd() {
		return customerCd;
	}

	/**
	 * 支払先設定.
	 * @param customerCd 支払先
	 */
	public void setCustomerCd(final String customerCd) {
		this.customerCd = customerCd;
	}

	/**
	 * 支払先名取得.
	 * @return String 支払先名
	 * 
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 支払先名設定.
	 * @param customerName 支払先名
	 * 
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * insertFlgを取得します。
	 * 
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * 
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 支払データリストを取得します。
	 * @return List<PaymentDetail> 支払データリスト
	 */
	public List<PaymentCsmBean> getDetailList() {
		return detailList;
	}

	/**
	 * 支払データリストを設定します。
	 * @param detailList 支払データリスト
	 */
	public void setDetailList(final List<PaymentCsmBean> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 支払分類コンボボックス内容を取得します。
	 * @return 支払分類コンボボックス内容
	 */
	public List<ComboBoxItems> getCategoryList() {
		return categoryList;
	}

	/**
	 * 支払分類コンボボックス内容を設定します。
	 * @param categoryList 支払分類コンボボックス内容
	 */
	public void setCategoryList(final List<ComboBoxItems> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * 科目コンボボックスを取得します。
	 * @return 科目コンボボックス
	 */
	public List<ComboBoxItems> getAccountList() {
		return accountList;
	}

	/**
	 * 科目コンボボックスを設定します。
	 * @param accountList 科目コンボボックス
	 */
	public void setAccountList(final List<ComboBoxItems> accountList) {
		this.accountList = accountList;
	}

	/**
	 * イベントが発生した行番号を取得します。
	 * @return イベントが発生した行番号
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * イベントが発生した行番号を設定します。
	 * @param index イベントが発生した行番号
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	/**
	 * 会計部門を取得します。
	 * @return 会計部門
	 */
	public String getAccountSectionCode() {
		return accountSectionCode;
	}

	/**
	 * 会計部門を設定します。
	 * @param accountSectionCode 会計部門
	 */
	public void setAccountSectionCode(final String accountSectionCode) {
		this.accountSectionCode = accountSectionCode;
	}

	/**
	 * 支払伝票発行区分を取得します。
	 * @return 支払伝票発行区分
	 */
	public BigDecimal getCreditIssuedDivision() {
		return creditIssuedDivision;
	}

	/**
	 * 支払伝票発行区分を設定します。
	 * @param creditIssuedDivision 支払伝票発行区分
	 */
	public void setCreditIssuedDivision(final BigDecimal creditIssuedDivision) {
		this.creditIssuedDivision = creditIssuedDivision;
	}

	/**
	 * 科目Mapを取得します。
	 * @param value 科目コード
	 * @return 科目Map
	 */
	public String getDebitLabel(final String value) {
		setDebitMapList(accountList);
		String label = debitMap.get(value);
		StringBuilder buf = new StringBuilder();
		if (StringUtils.isNotEmpty(label)) {
			buf.append(label);
		} else {
			if (StringUtils.isNotEmpty(value)) {
				buf.append(value);
			}
		}
		return buf.toString();
	}

	/**
	 * 科目Mapを設定します。
	 * @param debitMap 科目Map
	 */
	public void setDebitMap(final Map<String, String> debitMap) {
		this.debitMap = debitMap;
	}

	/**
	 * 科目Mapを設定します。
	 * @param list 科目配列
	 */
	public void setDebitMapList(final List<ComboBoxItems> list) {
		if (debitMap == null) {
			debitMap = new HashMap<String, String>();
			if (list != null) {
				for (ComboBoxItems item : list) {
					debitMap.put(item.getValues(), item.getLabales());
				}
			}
		}
	}

	/**
	 * 戻り先文字列を取得します。
	 * @return 戻り先文字列
	 */
	public String getBackForward() {
		return backForward;
	}

	/**
	 * 戻り先文字列を設定します。
	 * @param backForward 戻り先文字列
	 */
	public void setBackForward(final String backForward) {
		this.backForward = backForward;
	}

	/**
	 * 支払先AutoCompleteリストを取得します。
	 * @return 支払先AutoCompleteリスト
	 */
	public List<AltPaymentVender> getAutoList() {
		return autoList;
	}

	/**
	 * 支払先AutoCompleteリストを設定します。
	 * @param autoList 支払先AutoCompleteリスト
	 */
	public void setAutoList(final List<AltPaymentVender> autoList) {
		this.autoList = autoList;
	}

	/**
	 * 支払残高を取得します。
	 * @return 支払残高
	 */
	public String getPayableAmount() {
		return payableAmount;
	}

	/**
	 * 支払残高を設定します。
	 * @param payableAmount 支払残高
	 */
	public void setPayableAmount(final String payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * 支払先の振込区分を取得します。
	 * @return 支払先の振込区分
	 */
	public int getPayingCheckDivision() {
		return payingCheckDivision;
	}

	/**
	 * 支払先の振込区分を設定します。
	 * @param payingCheckDivision 支払先の振込区分
	 */
	public void setPayingCheckDivision(final int payingCheckDivision) {
		this.payingCheckDivision = payingCheckDivision;
	}

	/**
	 * 支払先の預金種別を取得します。
	 * @return 支払先の預金種別
	 */
	public String getAccountDivision() {
		return accountDivision;
	}

	/**
	 * 支払先の預金種別を設定します。
	 * @param accountDivision 支払先の預金種別
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * 支払先の口座番号を取得します。
	 * @return 支払先の口座番号
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * 支払先の口座番号を設定します。
	 * @param accountNo 支払先の口座番号
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 支払先の銀行コードを取得します。
	 * @return 支払先の銀行コード
	 */
	public String getBankCd() {
		return bankCd;
	}

	/**
	 * 支払先の銀行コードを設定します。
	 * @param bankCd 支払先の銀行コード
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * 支払先の銀行名を取得します。
	 * @return 支払先の銀行名
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 支払先の銀行名を設定します。
	 * @param bankName 支払先の銀行名
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 手数料取得
	 * @return String手数料
	 */
	public String getStrCommission() {
		return strCommission;
	}

	/**
	 * 手数料設定
	 * @param strCommission 手数料
	 */
	public void setStrCommission(final String strCommission) {
		this.strCommission = strCommission;
	}

	/**
	 * 支払予定額取得
	 * @return String 支払予定額
	 */
	public String getStrPaymentScheduledAmount() {
		return strPaymentScheduledAmount;
	}

	/**
	 * 支払予定額設定
	 * @param strPaymentScheduledAmount 支払予定額
	 */
	public void setStrPaymentScheduledAmount(
			final String strPaymentScheduledAmount) {
		this.strPaymentScheduledAmount = strPaymentScheduledAmount;
	}

	/**
	 * 仕入割引額取得
	 * @return String 仕入割引額
	 */
	public String getStrPurchaseDiscountAmount() {
		return strPurchaseDiscountAmount;
	}

	/**
	 * 仕入割引額設定
	 * @param strPurchaseDiscountAmount 仕入割引額
	 */
	public void setStrPurchaseDiscountAmount(
			final String strPurchaseDiscountAmount) {
		this.strPurchaseDiscountAmount = strPurchaseDiscountAmount;
	}

	/**
	 * 支払合計金額取得
	 * @return String 支払合計金額
	 */
	public String getStrSumAmount() {
		return strSumAmount;
	}

	/**
	 * 支払合計金額設定
	 * @param strSumAmount 支払合計金額
	 */
	public void setStrSumAmount(final String strSumAmount) {
		this.strSumAmount = strSumAmount;
	}

	/**
	 * 繰越残高取得
	 * @return String 繰越残高
	 */
	public String getStrBalanceCarriedForward() {
		return strBalanceCarriedForward;
	}

	/**
	 * 繰越残高設定
	 * @param strBalanceCarriedForward 繰越残高
	 */
	public void setStrBalanceCarriedForward(
			final String strBalanceCarriedForward) {
		this.strBalanceCarriedForward = strBalanceCarriedForward;
	}

	/**
	 * creditSectionCdを取得します。
	 * @return creditSectionCd
	 */
	public String getCreditSectionCd() {
		return creditSectionCd;
	}

	/**
	 * creditSectionCdを設定します。
	 * @param creditSectionCd creditSectionCd
	 */
	public void setCreditSectionCd(final String creditSectionCd) {
		this.creditSectionCd = creditSectionCd;
	}

	/**
	 * debitSectionCdを取得します。
	 * @return debitSectionCd
	 */
	public String getDebitSectionCd() {
		return debitSectionCd;
	}

	/**
	 * debitSectionCdを設定します。
	 * @param debitSectionCd debitSectionCd
	 */
	public void setDebitSectionCd(final String debitSectionCd) {
		this.debitSectionCd = debitSectionCd;
	}

	/**
	 * debitTitleCdを取得します。
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return debitTitleCd;
	}

	/**
	 * debitTitleCdを設定します。
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * approvaldateを取得します。
	 * @return approvaldate
	 */
	public Timestamp getApprovaldate() {
		return approvaldate;
	}

	/**
	 * approvaldateを設定します。
	 * @param approvaldate approvaldate
	 */
	public void setApprovaldate(final Timestamp approvaldate) {
		this.approvaldate = approvaldate;
	}

	/**
	 * approvaldbyを取得します。
	 * @return approvaldby
	 */
	public String getApprovaldby() {
		return approvaldby;
	}

	/**
	 * approvaldbyを設定します。
	 * @param approvaldby approvaldby
	 */
	public void setApprovaldby(final String approvaldby) {
		this.approvaldby = approvaldby;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * cursorを取得します。
	 * @return cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * cursorを設定します。
	 * @param cursor cursor
	 */
	public void setCursor(final String cursor) {
		this.cursor = cursor;
	}

	/**
	 * holidayMsgを取得します。
	 * @return holidayMsg
	 */
	public String getHolidayMsg() {
		return holidayMsg;
	}

	/**
	 * holidayMsgを設定します。
	 * @param holidayMsg holidayMsg
	 */
	public void setHolidayMsg(final String holidayMsg) {
		this.holidayMsg = holidayMsg;
	}

	/**
	 * paymentScheduledDateを取得します。
	 * @return paymentScheduledDate
	 */
	public Timestamp getPaymentScheduledDate() {
		return paymentScheduledDate;
	}

	/**
	 * paymentScheduledDateを設定します。
	 * @param paymentScheduledDate paymentScheduledDate
	 */
	public void setPaymentScheduledDate(final Timestamp paymentScheduledDate) {
		this.paymentScheduledDate = paymentScheduledDate;
	}

	/**
	 * headerListを取得します。
	 * @return headerList
	 */
	public List<AltPayment> getHeaderList() {
		return headerList;
	}

	/**
	 * headerListを設定します。
	 * @param headerList headerList
	 */
	public void setHeaderList(final List<AltPayment> headerList) {
		this.headerList = headerList;
	}

	/**
	 * strOffsetAmountを取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * strOffsetAmountを設定します。
	 * @param strOffsetAmount strOffsetAmount
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

	/**
	 * strPaymentAmountを取得します。
	 * @return strPaymentAmount
	 */
	public String getStrPaymentAmount() {
		return strPaymentAmount;
	}

	/**
	 * strPaymentAmountを設定します。
	 * @param strPaymentAmount strPaymentAmount
	 */
	public void setStrPaymentAmount(final String strPaymentAmount) {
		this.strPaymentAmount = strPaymentAmount;
	}

	/**
	 * strPaidAmountを取得します。
	 * @return strPaidAmount
	 */
	public String getStrPaidAmount() {
		return strPaidAmount;
	}

	/**
	 * strPaidAmountを設定します。
	 * @param strPaidAmount strPaidAmount
	 */
	public void setStrPaidAmount(final String strPaidAmount) {
		this.strPaidAmount = strPaidAmount;
	}

	/**
	 * strBalanceForwardを取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * strBalanceForwardを設定します。
	 * @param strBalanceForward strBalanceForward
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * creditMonthDivisionを取得します。
	 * @return creditMonthDivision
	 */
	public BigDecimal getCreditMonthDivision() {
		return creditMonthDivision;
	}

	/**
	 * creditMonthDivisionを設定します。
	 * @param creditMonthDivision creditMonthDivision
	 */
	public void setCreditMonthDivision(final BigDecimal creditMonthDivision) {
		this.creditMonthDivision = creditMonthDivision;
	}

	/**
	 * noteSightを取得します。
	 * @return noteSight
	 */
	public BigDecimal getNoteSight() {
		return noteSight;
	}

	/**
	 * noteSightを設定します。
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final BigDecimal noteSight) {
		this.noteSight = noteSight;
	}
}
