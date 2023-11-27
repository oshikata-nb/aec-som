/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 運送会社詳細 Formクラス.
 * @author t0011036
 */
public final class CarryDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 運送会社コード */
	private String carryCd;

	/* 運送会社名称1 */
	private String carryName1;

	/* 運送会社名称2 */
	private String carryName2;

	/* 帳票出力順 */
	private BigDecimal reportOutputNum;

	/* 帳票出力順 */
	private String strReportOutputNum;

	/* 運送会社略称 */
	private String abbreviation;

	/* 郵便番号 */
	private String zipcodeNo;

	/* 電話番号 */
	private String telNo;

	/* FAX番号 */
	private String faxNo;

	/* 住所1 */
	private String address1;

	/* 住所2 */
	private String address2;

	/* 住所3 */
	private String address3;

	/* 荷札発行 */
	private BigDecimal labelPublish;

	/* FLAN運送店コード */
	private String flanTransShopCd;
	
	/* バーコード発行区分 */
	private BigDecimal bcPublishDivision;

	/* バーコード発行区分初期値 */
	private BigDecimal bcPublishDivisionInit;

	/* バーコードヘッダ */
	private String bcHeader;
	
	/* バーコードフッタ */
	private String bcFooter;
	
	/* バーコード連番(開始) */
	private BigDecimal bcNumStart;
	
	/* バーコード連番(終了) */
	private BigDecimal bcNumEnd;
	
	/* バーコード連番(現在値) */
	private BigDecimal bcNumPresent;
	
	/* バーコード桁数 */
	private BigDecimal bcNumberOfDigit;

	/* チェックディジット開始 */
	private BigDecimal bcNumCheckDigitStart;

	/* チェックディジット開始*/
	private BigDecimal bcNumCheckDigitEnd;

	/* 次回発行バーコード */
	private String nextPublishBc;
	
	/* 備考 */
	private String remarks;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	
	/**
	 * コンストラクタ.
	 */
	public CarryDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setCarryCd(null);
		setCarryName1(null);
		setCarryName2(null);
		setReportOutputNum(null);
		setAbbreviation(null);
		setZipcodeNo(null);
		setTelNo(null);
		setFaxNo(null);
		setAddress1(null);
		setAddress2(null);
		setAddress3(null);
		setLabelPublish(null);
		setFlanTransShopCd(null);
		setBcPublishDivision(null);
		setBcHeader(null);
		setBcFooter(null);
		setBcNumStart(null);
		setBcNumEnd(null);
		setBcNumPresent(null);
		setBcNumberOfDigit(null);
		setBcNumCheckDigitStart(null);
		setBcNumCheckDigitEnd(null);
		setBcNumberOfDigit(null);
		setNextPublishBc(null);
		setRemarks(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setStrReportOutputNum(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * abbreviationを取得します。
	 * @return abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * abbreviationを設定します。
	 * @param abbreviation abbreviation
	 */
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * address1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * address1を設定します。
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * address2を設定します。
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * address3を設定します。
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1を取得します。
	 * @return carryName1
	 */
	public String getCarryName1() {
		return carryName1;
	}

	/**
	 * carryName1を設定します。
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryName2を取得します。
	 * @return carryName2
	 */
	public String getCarryName2() {
		return carryName2;
	}

	/**
	 * carryName2を設定します。
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * faxNoを取得します。
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoを設定します。
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * flanTransShopCdを取得します。
	 * @return flanTransShopCd
	 */
	public String getFlanTransShopCd() {
		return flanTransShopCd;
	}

	/**
	 * flanTransShopCdを設定します。
	 * @param flanTransShopCd flanTransShopCd
	 */
	public void setFlanTransShopCd(final String flanTransShopCd) {
		this.flanTransShopCd = flanTransShopCd;
	}

	/**
	 * bcPublishDivisionを取得します。
	 * @return bcPublishDivision
	 */
	public BigDecimal getBcPublishDivision() {
		return bcPublishDivision;
	}

	/**
	 * bcPublishDivisionを設定します。
	 * @param bcPublishDivision bcPublishDivision
	 */
	public void setBcPublishDivision(BigDecimal bcPublishDivision) {
		this.bcPublishDivision = bcPublishDivision;
	}

	/**
	 * bcHeaderを取得します。
	 * @return bcHeader
	 */
	public String getBcHeader() {
		return bcHeader;
	}

	/**
	 * bcHeaderを設定します。
	 * @param bcHeader bcHeader
	 */
	public void setBcHeader(String bcHeader) {
		this.bcHeader = bcHeader;
	}

	/**
	 * bcFooterを取得します。
	 * @return bcFooter
	 */
	public String getBcFooter() {
		return bcFooter;
	}

	/**
	 * bcFooterを設定します。
	 * @param bcFooter bcFooter
	 */
	public void setBcFooter(String bcFooter) {
		this.bcFooter = bcFooter;
	}

	/**
	 * bcNumStartを取得します。
	 * @return bcNumStart
	 */
	public BigDecimal getBcNumStart() {
		return bcNumStart;
	}

	/**
	 * bcNumStartを設定します。
	 * @param bcNumStart bcNumStart
	 */
	public void setBcNumStart(BigDecimal bcNumStart) {
		this.bcNumStart = bcNumStart;
	}

	/**
	 * bcNumEndを取得します。
	 * @return bcNumEnd
	 */
	public BigDecimal getBcNumEnd() {
		return bcNumEnd;
	}

	/**
	 * bcNumEndを設定します。
	 * @param bcNumEnd bcNumEnd
	 */
	public void setBcNumEnd(BigDecimal bcNumEnd) {
		this.bcNumEnd = bcNumEnd;
	}

	/**
	 * bcNumPresentを取得します。
	 * @return bcNumPresent
	 */
	public BigDecimal getBcNumPresent() {
		return bcNumPresent;
	}

	/**
	 * bcNumPresentを設定します。
	 * @param bcNumPresent bcNumPresent
	 */
	public void setBcNumPresent(BigDecimal bcNumPresent) {
		this.bcNumPresent = bcNumPresent;
	}

	/**
	 * bcNumberOfDigitを取得します。
	 * @return bcNumberOfDigit
	 */
	public BigDecimal getBcNumberOfDigit() {
		return bcNumberOfDigit;
	}

	/**
	 * bcNumberOfDigitを設定します。
	 * @param bcNumberOfDigit bcNumberOfDigit
	 */
	public void setBcNumberOfDigit(BigDecimal bcNumberOfDigit) {
		this.bcNumberOfDigit = bcNumberOfDigit;
	}

	/**
	 * nextPublishBcを取得します。
	 * @return nextPublishBc
	 */
	public String getNextPublishBc() {
		return nextPublishBc;
	}

	/**
	 * nextPublishBcを設定します。
	 * @param nextPublishBc nextPublishBc
	 */
	public void setNextPublishBc(String nextPublishBc) {
		this.nextPublishBc = nextPublishBc;
	}

	/**
	 * labelPublishを取得します。
	 * @return labelPublish
	 */
	public BigDecimal getLabelPublish() {
		return labelPublish;
	}

	/**
	 * labelPublishを設定します。
	 * @param labelPublish labelPublish
	 */
	public void setLabelPublish(final BigDecimal labelPublish) {
		this.labelPublish = labelPublish;
	}

	/**
	 * remarksを取得します。
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * remarksを設定します。
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * telNoを取得します。
	 * @return telNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * telNoを設定します。
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * zipcodeNoを取得します。
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return zipcodeNo;
	}

	/**
	 * zipcodeNoを設定します。
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * reportOutputNumを取得します。
	 * @return reportOutputNum
	 */
	public BigDecimal getReportOutputNum() {
		return reportOutputNum;
	}

	/**
	 * reportOutputNumを設定します。
	 * @param reportOutputNum reportOutputNum
	 */
	public void setReportOutputNum(final BigDecimal reportOutputNum) {
		this.reportOutputNum = reportOutputNum;
	}

	/**
	 * strReportOutputNumを取得します。
	 * @return strReportOutputNum
	 */
	public String getStrReportOutputNum() {
		return strReportOutputNum;
	}

	/**
	 * strReportOutputNumを設定します。
	 * @param strReportOutputNum strReportOutputNum
	 */
	public void setStrReportOutputNum(final String strReportOutputNum) {
		this.strReportOutputNum = strReportOutputNum;
	}
	
	/**
	 * バーコード要素の長さの合計を取得します。
	 * @return バーコード要素の長さの合計
	 */
	public int getBcSettingLength() {
		int bcNoDLength = 0;
		if( this.bcNumberOfDigit != null  )
		{
			bcNoDLength = this.bcNumberOfDigit.intValue();
		}
		
		return this.bcHeader.length() + this.bcFooter.length() + bcNoDLength;
	}

	/**
	 * ヘッダがスタート/ストップキャラクタを含んでいるか確認します。
	 * @return true/false あり/なし
	 */
	public int getBcHeaderHasStartChar() {
		if( this.bcHeader.matches("^[a-dA-D][0-9\\-\\$\\:\\/\\.\\+\\@]*")) {
			return 1;
			
		}else{
			return 0;
		}
	}

	/**
	 * フッタがスタート/ストップキャラクタを含んでいるか確認します。
	 * @return true/false あり/なし
	 */
	public int getBcFooterHasEndChar() {
		if( this.bcFooter.matches("[0-9\\-\\$\\:\\/\\.\\+\\@]*[a-dA-D]$")){
			return 1;
			
		}else{
			return 0;
		}
	}
	
	/**
	 * bcPublishDivisionInitを取得します。
	 * @return bcPublishDivisionInit
	 */
	public BigDecimal getBcPublishDivisionInit() {
		return bcPublishDivisionInit;
	}

	/**
	 * bcPublishDivisionInitを設定します。
	 * @param bcPublishDivisionInit bcPublishDivisionInit
	 */
	public void setBcPublishDivisionInit(BigDecimal bcPublishDivisionInit) {
		this.bcPublishDivisionInit = bcPublishDivisionInit;
	}


	/**
	 * bcNumCheckDigitEndを取得します。
	 * @return bcNumCheckDigitEnd
	 */
	public BigDecimal getBcNumCheckDigitEnd() {
		return bcNumCheckDigitEnd;
	}

	/**
	 * bcNumCheckDigitEndを設定します。
	 * @param bcNumCheckDigitEnd bcNumCheckDigitEnd
	 */
	public void setBcNumCheckDigitEnd(BigDecimal bcNumCheckDigitEnd) {
		this.bcNumCheckDigitEnd = bcNumCheckDigitEnd;
	}

	/**
	 * bcNumCheckDigitStartを取得します。
	 * @return bcNumCheckDigitStart
	 */
	public BigDecimal getBcNumCheckDigitStart() {
		return bcNumCheckDigitStart;
	}

	/**
	 * bcNumCheckDigitStartを設定します。
	 * @param bcNumCheckDigitStart bcNumCheckDigitStart
	 */
	public void setBcNumCheckDigitStart(BigDecimal bcNumCheckDigitStart) {
		this.bcNumCheckDigitStart = bcNumCheckDigitStart;
	}

}
