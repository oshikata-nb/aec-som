/*
 * Created on 2008/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 入金入力－詳細情報 Formクラス
 * @author tosco
 */
public final class DepositDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 入金番号 */
	private String creditNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 入金日付 */
	private Date creditDate;

	/** 入金日付(String型) */
	private String strCreditDate;

	/** 入金番号 */
	private String slipNo;

	/** 請求先区分 */
	private String venderDivision;

	/** 請求先 */
	private String venderCd;

	/** 請求先名称 */
	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 銀行マスタコード */
	private String bankMasterCd;

	/** 借方部門コード */
	private String debitSectionCd;

	/** 貸方部門コード */
	private String creditSectionCd;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 明細データリスト */
	private List<CreditBean> detailList;

	/** 更新フラグ */
	private int insertFlg;

	/** 入金分類コンボボックス内容 */
	private List<ComboBoxItems> categoryList;

	private List<ComboBoxItems> notCategoryList;

	/** 科目コンボボックス */
	private List<ComboBoxItems> accountList;

	/** 手形種別コンボボックス */
	private List<ComboBoxItems> noteList;

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

	/** 承認ステータス */
	private String approvalStatus;

	/** 承認者 */
	private String approvaldby;

	/** 承認日時 */
	private Timestamp approvaldate;

	/** 摘要 */
	private String remark;

	/** 休日メッセージ */
	private String holidayMsg;

	/** カーソル位置 */
	private String cursor;

	/** 銀行マスタコード1 */
	private String bankMasterCd1;

	/** 預金種別1 */
	private BigDecimal accountDivision1;

	/** 口座番号1 */
	private String accountNo1;

	/** 銀行マスタコード2 */
	private String bankMasterCd2;

	/** 預金種別2 */
	private BigDecimal accountDivision2;

	/** 口座番号2 */
	private String accountNo2;

	/** 銀行マスタコード3 */
	private String bankMasterCd3;

	/** 預金種別3 */
	private BigDecimal accountDivision3;

	/** 口座番号3 */
	private String accountNo3;

	/** 銀行マスタコード4 */
	private String bankMasterCd4;

	/** 預金種別4 */
	private BigDecimal accountDivision4;

	/** 口座番号4 */
	private String accountNo4;

	/** 前受金区分 */
	private BigDecimal advanceDivision;
	
	/** 実際の入金額 */
	private String realDepositAmount;
	/** 経理システムへの連携額 */
	private String accSysAmount;
	/** 客先への請求額 */
	private String claimAmount;
	/** １～７、９の入力額 */
	private String inputPtn_No1to7and9;
	/** ⑧調整額の入力額の入力額 */
	private String inputPtn_No8;
	/** ⑩調整額（売上＞請求書)の額の入力額 */
	private String inputPtn_No10;
	/** １1～１９の入力額の入力額 */
	private String inputPtn_No11to19;
	/** ⑳調整額（売上＜請求書)の額の入力額 */
	private String inputPtn_No20;
	/** ２1～２９の入力額の入力額 */
	private String inputPtnNo21to29;
	/** 会計システムのみの調整額の入力額 */
	private String inputPtn_AccSys;

	/**
	 * コンストラクタ.入金入力－詳細情報
	 */
	public DepositDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);
		// 削除チェックボックスを初期化
		if (detailList != null) {
			for (CreditBean bean : detailList) {
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
		return errors;
	}

	/**
	 * Stringオブジェクトを摘要文字列に変換する
	 * @param d 日付文字(yyyy/MM/dd)
	 * @return 摘要文字列
	 */
	private String getDateRemarkString(final String d) {
		String res = null;

		if (!StringUtils.isEmpty(d)) {
			res = d.replaceAll("/", "");
			res = res.substring(2, 4) + "年" + res.substring(4, 6) + "月入金";
		}

		return res;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 入金番号
		setSlipNo(null);
		// 入金日付
		setCreditDate(AecDateUtils.getCurrentTimestamp());
		setStrCreditDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		// 請求先区分
		setVenderDivision("TS");
		// 請求先
		setVenderCd(null);
		// 請求先名称
		setVenderName1(null);
		// 請求先略称
		setVenderShortedName(null);
		// 銀行マスタコード
		setBankMasterCd(null);
		// 借方部門コード
		setDebitSectionCd(null);
		// 貸方部門コード
		setCreditSectionCd(null);
		// 貸方科目コード
		setCreditTitleCd(null);
		// 新規更新切替フラグ
		setInsertFlg(DepositDetailAction.INSERT_FLAG_UPDATE);
		// 変更フラグ
		setDirtyFlg(null);
		// 明細データ
		setDetailList(null);
		// 入金分類コンボボックス内容
		setCategoryList(null);
		setNotCategoryList(null);
		// 勘定科目コンボボックス
		setAccountList(null);
		// 手形種別コンボボックス
		setNoteList(null);
		// イベントが発生した行番号
		setIndex(0);
		// 会計部門
		setAccountSectionCode(null);
		// 入金伝票発行区分
		setCreditIssuedDivision(new BigDecimal("0"));
		// 科目Map
		setDebitMap(null);
		// 戻り先
		setBackForward(null);
		// 承認ステータス
		setApprovalStatus(null);
		// 承認者
		setApprovaldby(null);
		// 承認日時
		setApprovaldate(null);
		// 摘要
		setRemark(getDateRemarkString(getStrCreditDate()));
		// 休日メッセージ
		setHolidayMsg(null);
		// カーソル位置
		setCursor(null);
		// 銀行マスタコード1
		setBankMasterCd1(null);
		// 預金種別1
		setAccountDivision1(null);
		// 口座番号1
		setAccountNo1(null);
		// 銀行マスタコード2
		setBankMasterCd2(null);
		// 預金種別2
		setAccountDivision2(null);
		// 口座番号2
		setAccountNo2(null);
		// 銀行マスタコード3
		setBankMasterCd3(null);
		// 預金種別3
		setAccountDivision3(null);
		// 口座番号3
		setAccountNo3(null);
		// 銀行マスタコード4
		setBankMasterCd4(null);
		// 預金種別4
		setAccountDivision4(null);
		// 口座番号4
		setAccountNo4(null);
		// 前受金区分
		setAdvanceDivision(BigDecimal.ONE);
		
		setRealDepositAmount("0");
		setAccSysAmount("0");
		setClaimAmount("0");
		setInputPtn_No1to7and9("0");
		setInputPtn_No8("0");
		setInputPtn_No10("0");
		setInputPtn_No11to19("0");
		setInputPtn_No20("0");
		setInputPtnNo21to29("0");
		setInputPtn_AccSys("0");
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 入金日付取得.
	 * @return Date 入金日付
	 * 
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 入金日付設定.
	 * @param creditDate 入金日付
	 * 
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 入金日付(String型)取得.
	 * @return String 入金日付(String型)
	 */
	public String getStrCreditDate() {
		return strCreditDate;
	}

	/**
	 * 入金日付(String型)設定.
	 * @param strCreditDate 入金日付(String型)
	 */
	public void setStrCreditDate(final String strCreditDate) {
		this.strCreditDate = strCreditDate;
	}

	/**
	 * 入金番号取得.
	 * @return String 入金番号
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 入金番号設定.
	 * @param slipNo 入金番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
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
	 * 入金データリストを取得します。
	 * 
	 * @return List<DepositDetail> 入金データリスト
	 * 
	 */
	public List<CreditBean> getDetailList() {
		return detailList;
	}

	/**
	 * 入金データリストを設定します。
	 * 
	 * @param detailList 入金データリスト
	 * 
	 */
	public void setDetailList(final List<CreditBean> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 入金分類コンボボックス内容を取得します。
	 * @return 入金分類コンボボックス内容
	 */
	public List<ComboBoxItems> getCategoryList() {
		return categoryList;
	}

	/**
	 * 入金分類コンボボックス内容を設定します。
	 * @param categoryList 入金分類コンボボックス内容
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
	 * 入金伝票発行区分を取得します。
	 * @return 入金伝票発行区分
	 */
	public BigDecimal getCreditIssuedDivision() {
		return creditIssuedDivision;
	}

	/**
	 * 入金伝票発行区分を設定します。
	 * @param creditIssuedDivision 入金伝票発行区分
	 */
	public void setCreditIssuedDivision(final BigDecimal creditIssuedDivision) {
		this.creditIssuedDivision = creditIssuedDivision;
	}

	/**
	 * 入金番号を取得します。
	 * @return 入金番号
	 */
	public String getCreditNo() {
		return creditNo;
	}

	/**
	 * 入金番号を設定します。
	 * @param creditNo 入金番号
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
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
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
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
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
	 * noteListを取得します。
	 * @return noteList
	 */
	public List<ComboBoxItems> getNoteList() {
		return noteList;
	}

	/**
	 * noteListを設定します。
	 * @param noteList noteList
	 */
	public void setNoteList(final List<ComboBoxItems> noteList) {
		this.noteList = noteList;
	}

	/**
	 * accountDivision1を取得します。
	 * @return accountDivision1
	 */
	public BigDecimal getAccountDivision1() {
		return accountDivision1;
	}

	/**
	 * accountDivision1を設定します。
	 * @param accountDivision1 accountDivision1
	 */
	public void setAccountDivision1(final BigDecimal accountDivision1) {
		this.accountDivision1 = accountDivision1;
	}

	/**
	 * accountDivision2を取得します。
	 * @return accountDivision2
	 */
	public BigDecimal getAccountDivision2() {
		return accountDivision2;
	}

	/**
	 * accountDivision2を設定します。
	 * @param accountDivision2 accountDivision2
	 */
	public void setAccountDivision2(final BigDecimal accountDivision2) {
		this.accountDivision2 = accountDivision2;
	}

	/**
	 * accountDivision3を取得します。
	 * @return accountDivision3
	 */
	public BigDecimal getAccountDivision3() {
		return accountDivision3;
	}

	/**
	 * accountDivision3を設定します。
	 * @param accountDivision3 accountDivision3
	 */
	public void setAccountDivision3(final BigDecimal accountDivision3) {
		this.accountDivision3 = accountDivision3;
	}

	/**
	 * accountDivision4を取得します。
	 * @return accountDivision4
	 */
	public BigDecimal getAccountDivision4() {
		return accountDivision4;
	}

	/**
	 * accountDivision4を設定します。
	 * @param accountDivision4 accountDivision4
	 */
	public void setAccountDivision4(final BigDecimal accountDivision4) {
		this.accountDivision4 = accountDivision4;
	}

	/**
	 * accountNo1を取得します。
	 * @return accountNo1
	 */
	public String getAccountNo1() {
		return accountNo1;
	}

	/**
	 * accountNo1を設定します。
	 * @param accountNo1 accountNo1
	 */
	public void setAccountNo1(final String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	/**
	 * accountNo2を取得します。
	 * @return accountNo2
	 */
	public String getAccountNo2() {
		return accountNo2;
	}

	/**
	 * accountNo2を設定します。
	 * @param accountNo2 accountNo2
	 */
	public void setAccountNo2(final String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	/**
	 * accountNo3を取得します。
	 * @return accountNo3
	 */
	public String getAccountNo3() {
		return accountNo3;
	}

	/**
	 * accountNo3を設定します。
	 * @param accountNo3 accountNo3
	 */
	public void setAccountNo3(final String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	/**
	 * accountNo4を取得します。
	 * @return accountNo4
	 */
	public String getAccountNo4() {
		return accountNo4;
	}

	/**
	 * accountNo4を設定します。
	 * @param accountNo4 accountNo4
	 */
	public void setAccountNo4(final String accountNo4) {
		this.accountNo4 = accountNo4;
	}

	/**
	 * bankMasterCd1を取得します。
	 * @return bankMasterCd1
	 */
	public String getBankMasterCd1() {
		return bankMasterCd1;
	}

	/**
	 * bankMasterCd1を設定します。
	 * @param bankMasterCd1 bankMasterCd1
	 */
	public void setBankMasterCd1(final String bankMasterCd1) {
		this.bankMasterCd1 = bankMasterCd1;
	}

	/**
	 * bankMasterCd2を取得します。
	 * @return bankMasterCd2
	 */
	public String getBankMasterCd2() {
		return bankMasterCd2;
	}

	/**
	 * bankMasterCd2を設定します。
	 * @param bankMasterCd2 bankMasterCd2
	 */
	public void setBankMasterCd2(final String bankMasterCd2) {
		this.bankMasterCd2 = bankMasterCd2;
	}

	/**
	 * bankMasterCd3を取得します。
	 * @return bankMasterCd3
	 */
	public String getBankMasterCd3() {
		return bankMasterCd3;
	}

	/**
	 * bankMasterCd3を設定します。
	 * @param bankMasterCd3 bankMasterCd3
	 */
	public void setBankMasterCd3(final String bankMasterCd3) {
		this.bankMasterCd3 = bankMasterCd3;
	}

	/**
	 * bankMasterCd4を取得します。
	 * @return bankMasterCd4
	 */
	public String getBankMasterCd4() {
		return bankMasterCd4;
	}

	/**
	 * bankMasterCd4を設定します。
	 * @param bankMasterCd4 bankMasterCd4
	 */
	public void setBankMasterCd4(final String bankMasterCd4) {
		this.bankMasterCd4 = bankMasterCd4;
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
	 * creditTitleCdを取得します。
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return creditTitleCd;
	}

	/**
	 * creditTitleCdを設定します。
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
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
	 * bankMasterCdを取得します。
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return bankMasterCd;
	}

	/**
	 * bankMasterCdを設定します。
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * advanceDivisionを取得します。
	 * @return advanceDivision
	 */
	public BigDecimal getAdvanceDivision() {
		return advanceDivision;
	}

	/**
	 * advanceDivisionを設定します。
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}

	/**
	 * notCategoryListを取得します。
	 * @return notCategoryList
	 */
	public List<ComboBoxItems> getNotCategoryList() {
		return notCategoryList;
	}

	/**
	 * notCategoryListを設定します。
	 * @param notCategoryList notCategoryList
	 */
	public void setNotCategoryList(final List<ComboBoxItems> notCategoryList) {
		this.notCategoryList = notCategoryList;
	}

	/**
	 * realDepositAmountを取得します。
	 * @return realDepositAmount
	 */
	public String getRealDepositAmount() {
		return realDepositAmount;
	}

	/**
	 * realDepositAmountを設定します。
	 * @param realDepositAmount realDepositAmount
	 */
	public void setRealDepositAmount(String realDepositAmount) {
		this.realDepositAmount = realDepositAmount;
	}

	/**
	 * accSysAmountを取得します。
	 * @return accSysAmount
	 */
	public String getAccSysAmount() {
		return accSysAmount;
	}

	/**
	 * accSysAmountを設定します。
	 * @param accSysAmount accSysAmount
	 */
	public void setAccSysAmount(String accSysAmount) {
		this.accSysAmount = accSysAmount;
	}

	/**
	 * claimAmountを取得します。
	 * @return claimAmount
	 */
	public String getClaimAmount() {
		return claimAmount;
	}

	/**
	 * claimAmountを設定します。
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * inputPtn_No1to7and9を取得します。
	 * @return inputPtn_No1to7and9
	 */
	public String getInputPtn_No1to7and9() {
		return inputPtn_No1to7and9;
	}

	/**
	 * inputPtn_No1to7and9を設定します。
	 * @param inputPtn_No1to7and9 inputPtn_No1to7and9
	 */
	public void setInputPtn_No1to7and9(String inputPtn_No1to7and9) {
		this.inputPtn_No1to7and9 = inputPtn_No1to7and9;
	}

	/**
	 * inputPtn_No8を取得します。
	 * @return inputPtn_No8
	 */
	public String getInputPtn_No8() {
		return inputPtn_No8;
	}

	/**
	 * inputPtn_No8を設定します。
	 * @param inputPtn_No8 inputPtn_No8
	 */
	public void setInputPtn_No8(String inputPtn_No8) {
		this.inputPtn_No8 = inputPtn_No8;
	}

	/**
	 * inputPtn_No10を取得します。
	 * @return inputPtn_No10
	 */
	public String getInputPtn_No10() {
		return inputPtn_No10;
	}

	/**
	 * inputPtn_No10を設定します。
	 * @param inputPtn_No10 inputPtn_No10
	 */
	public void setInputPtn_No10(String inputPtn_No10) {
		this.inputPtn_No10 = inputPtn_No10;
	}

	/**
	 * inputPtn_No11to19を取得します。
	 * @return inputPtn_No11to19
	 */
	public String getInputPtn_No11to19() {
		return inputPtn_No11to19;
	}

	/**
	 * inputPtn_No11to19を設定します。
	 * @param inputPtn_No11to19 inputPtn_No11to19
	 */
	public void setInputPtn_No11to19(String inputPtn_No11to19) {
		this.inputPtn_No11to19 = inputPtn_No11to19;
	}

	/**
	 * inputPtn_No20を取得します。
	 * @return inputPtn_No20
	 */
	public String getInputPtn_No20() {
		return inputPtn_No20;
	}

	/**
	 * inputPtn_No20を設定します。
	 * @param inputPtn_No20 inputPtn_No20
	 */
	public void setInputPtn_No20(String inputPtn_No20) {
		this.inputPtn_No20 = inputPtn_No20;
	}

	/**
	 * inputPtnNo21to29を取得します。
	 * @return inputPtnNo21to29
	 */
	public String getInputPtnNo21to29() {
		return inputPtnNo21to29;
	}

	/**
	 * inputPtnNo21to29を設定します。
	 * @param inputPtnNo21to29 inputPtnNo21to29
	 */
	public void setInputPtnNo21to29(String inputPtnNo21to29) {
		this.inputPtnNo21to29 = inputPtnNo21to29;
	}

	/**
	 * inputPtn_AccSysを取得します。
	 * @return inputPtn_AccSys
	 */
	public String getInputPtn_AccSys() {
		return inputPtn_AccSys;
	}

	/**
	 * inputPtn_AccSysを設定します。
	 * @param inputPtn_AccSys inputPtn_AccSys
	 */
	public void setInputPtn_AccSys(String inputPtn_AccSys) {
		this.inputPtn_AccSys = inputPtn_AccSys;
	}
}
