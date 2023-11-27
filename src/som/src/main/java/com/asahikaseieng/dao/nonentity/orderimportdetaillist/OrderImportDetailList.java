/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetaillist;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * OrderImportDetailクラス.
 * @author 
 */
public class OrderImportDetailList extends OrderImportDetailListBase {

	private static final long serialVersionUID = 1L;
	
	/** ポップアップチェック */
	private Boolean checked;

	/** チェックボックス */
	private Boolean checkline;

	/** 小数点桁数(金額) */
	private String smallnumLengthOrderAmount;

	/** 端数区分(金額) */
	private String roundDivisionOrderAmount;

	/** 小数点桁数(単価) */
	private String smallnumLengthUnitprice;

	/** 端数区分(単価) */
	private String roundDivisionUnitprice;

	/** 小数点桁数(数量) */
	private String smallnumLengthNum;

	/** 端数区分(数量) */
	private String roundDivisionNum;
	
	/** 小数点桁数(重量) */
	private String smallnumLengthWeight;
	
	/** 端数区分(重量) */
	private String roundDivisionWeight;

	/** 取引先コード（退避用） */
	private String venderCd;

	/** 単価決定単位区分 */
	private BigDecimal unitpriceDivision;

	/** 有効在庫数 */
	private String validInventoryQty;
	
	/** チェック済みフラグ */
	private Boolean validLine;
	
	/** 警告表示用受注単価 */
	private BigDecimal tmpOrderUnitprice;
	
	/** 受注数量(詳細画面遷移時の値保持用) */
	private BigDecimal beforeOrderQty;
	
	/** 増付数(詳細画面遷移時の値保持用) */
	private BigDecimal beforeMatss;
	
	/** コンフリクト元行 */
	private Boolean conflictLine;
	
	/** インサートフラグ */
	private int insertFlg;
	
	/**
	 * conflictLineを取得します。
	 * @return conflictLine
	 */
	public Boolean getConflictLine() {
		return conflictLine;
	}

	/**
	 * conflictLineを設定します。
	 * @param conflictLine conflictLine
	 */
	public void setConflictLine(Boolean conflictLine) {
		this.conflictLine = conflictLine;
	}

	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailList() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
		setCheckline(Boolean.FALSE);
	}
	
	/**
	 * 仮単価フラグを取得します
	 *
	 * @return blnTmpUnitpriceFlg
	 */
	public Boolean getBlnTmpUnitpriceFlg() {
		switch (AecNumberUtils.convertBigDecimalNullToZeroFromString(
				getTmpUnitpriceFlg()).intValue()) {
		case 1: /* 仮単価 */
			return true;
		default:
			return false;
		}
	}

	/**
	 * データ表示フラグ
	 * @return
	 */
	public int getShowData(){
		if( super.getDelFlg().intValue() == 0 ){
			return 1;
		}else if( super.getHeadDelFlg().intValue() == 1   ){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * checklineを取得します。
	 * @return checkline
	 */
	public Boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * @param checkline checkline
	 */
	public void setCheckline(Boolean checkline) {
		this.checkline = checkline;
	}

	/**
	 * smallnumLengthOrderAmountを取得します。
	 * @return smallnumLengthOrderAmount
	 */
	public String getSmallnumLengthOrderAmount() {
		return smallnumLengthOrderAmount;
	}

	/**
	 * smallnumLengthOrderAmountを設定します。
	 * @param smallnumLengthOrderAmount smallnumLengthOrderAmount
	 */
	public void setSmallnumLengthOrderAmount(String smallnumLengthOrderAmount) {
		this.smallnumLengthOrderAmount = smallnumLengthOrderAmount;
	}

	/**
	 * roundDivisionOrderAmountを取得します。
	 * @return roundDivisionOrderAmount
	 */
	public String getRoundDivisionOrderAmount() {
		return roundDivisionOrderAmount;
	}

	/**
	 * roundDivisionOrderAmountを設定します。
	 * @param roundDivisionOrderAmount roundDivisionOrderAmount
	 */
	public void setRoundDivisionOrderAmount(String roundDivisionOrderAmount) {
		this.roundDivisionOrderAmount = roundDivisionOrderAmount;
	}

	/**
	 * smallnumLengthUnitpriceを取得します。
	 * @return smallnumLengthUnitprice
	 */
	public String getSmallnumLengthUnitprice() {
		return smallnumLengthUnitprice;
	}

	/**
	 * smallnumLengthUnitpriceを設定します。
	 * @param smallnumLengthUnitprice smallnumLengthUnitprice
	 */
	public void setSmallnumLengthUnitprice(String smallnumLengthUnitprice) {
		this.smallnumLengthUnitprice = smallnumLengthUnitprice;
	}

	/**
	 * roundDivisionUnitpriceを取得します。
	 * @return roundDivisionUnitprice
	 */
	public String getRoundDivisionUnitprice() {
		return roundDivisionUnitprice;
	}

	/**
	 * roundDivisionUnitpriceを設定します。
	 * @param roundDivisionUnitprice roundDivisionUnitprice
	 */
	public void setRoundDivisionUnitprice(String roundDivisionUnitprice) {
		this.roundDivisionUnitprice = roundDivisionUnitprice;
	}

	/**
	 * smallnumLengthNumを取得します。
	 * @return smallnumLengthNum
	 */
	public String getSmallnumLengthNum() {
		return smallnumLengthNum;
	}

	/**
	 * smallnumLengthNumを設定します。
	 * @param smallnumLengthNum smallnumLengthNum
	 */
	public void setSmallnumLengthNum(String smallnumLengthNum) {
		this.smallnumLengthNum = smallnumLengthNum;
	}

	/**
	 * roundDivisionNumを取得します。
	 * @return roundDivisionNum
	 */
	public String getRoundDivisionNum() {
		return roundDivisionNum;
	}

	/**
	 * roundDivisionNumを設定します。
	 * @param roundDivisionNum roundDivisionNum
	 */
	public void setRoundDivisionNum(String roundDivisionNum) {
		this.roundDivisionNum = roundDivisionNum;
	}

	/**
	 * smallnumLengthWeightを取得します。
	 * @return smallnumLengthWeight
	 */
	public String getSmallnumLengthWeight() {
		return smallnumLengthWeight;
	}

	/**
	 * smallnumLengthWeightを設定します。
	 * @param smallnumLengthWeight smallnumLengthWeight
	 */
	public void setSmallnumLengthWeight(String smallnumLengthWeight) {
		this.smallnumLengthWeight = smallnumLengthWeight;
	}

	/**
	 * roundDivisionWeightを取得します。
	 * @return roundDivisionWeight
	 */
	public String getRoundDivisionWeight() {
		return roundDivisionWeight;
	}

	/**
	 * roundDivisionWeightを設定します。
	 * @param roundDivisionWeight roundDivisionWeight
	 */
	public void setRoundDivisionWeight(String roundDivisionWeight) {
		this.roundDivisionWeight = roundDivisionWeight;
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
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * unitpriceDivisionを取得します。
	 * @return unitpriceDivision
	 */
	public BigDecimal getUnitpriceDivision() {
		return unitpriceDivision;
	}

	/**
	 * unitpriceDivisionを設定します。
	 * @param unitpriceDivision unitpriceDivision
	 */
	public void setUnitpriceDivision(BigDecimal unitpriceDivision) {
		this.unitpriceDivision = unitpriceDivision;
	}

	/**
	 * validInventoryQtyを取得します。
	 * @return validInventoryQty
	 */
	public String getValidInventoryQty() {
		return validInventoryQty;
	}

	/**
	 * validInventoryQtyを設定します。
	 * @param validInventoryQty validInventoryQty
	 */
	public void setValidInventoryQty(String validInventoryQty) {
		this.validInventoryQty = validInventoryQty;
	}
	
	/**
	 * validLineを取得します。
	 * @return validLine
	 */
	public Boolean getValidLine() {
		return validLine;
	}

	/**
	 * validLineを設定します。
	 * @param validLine validLine
	 */
	public void setValidLine(Boolean validLine) {
		this.validLine = validLine;
	}

	/**
	 * tmpOrderUnitpriceを取得します。
	 * @return tmpOrderUnitprice
	 */
	public BigDecimal getTmpOrderUnitprice() {
		return tmpOrderUnitprice;
	}

	/**
	 * tmpOrderUnitpriceを設定します。
	 * @param tmpOrderUnitprice tmpOrderUnitprice
	 */
	public void setTmpOrderUnitprice(BigDecimal tmpOrderUnitprice) {
		this.tmpOrderUnitprice = tmpOrderUnitprice;
	}

	/**
	 * beforeOrderQtyを取得します。
	 * @return beforeOrderQty
	 */
	public BigDecimal getBeforeOrderQty() {
		return beforeOrderQty;
	}

	/**
	 * beforeOrderQtyを設定します。
	 * @param beforeOrderQty beforeOrderQty
	 */
	public void setBeforeOrderQty(BigDecimal beforeOrderQty) {
		this.beforeOrderQty = beforeOrderQty;
	}

	/**
	 * beforeMatssを取得します。
	 * @return beforeMatss
	 */
	public BigDecimal getBeforeMatss() {
		return beforeMatss;
	}

	/**
	 * beforeMatssを設定します。
	 * @param beforeMatss beforeMatss
	 */
	public void setBeforeMatss(BigDecimal beforeMatss) {
		this.beforeMatss = beforeMatss;
	}

	
	/**
	 * 入力承認チェック
	 * @return
	 */
	public boolean getInputApprovaledCheck(){
		
		if( super.getInputApprovalFlg() != null && super.getInputApprovalFlg().intValue() == 1 ){
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * 納期承認チェック
	 * @return
	 */
	public boolean getDelDateApprovaledCheck(){
		
		if( super.getDelDateSendFlg() != null && super.getDelDateSendFlg().intValue() == 1 ){
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * 登録済みチェック
	 * @return
	 */
	public boolean getInsertedFlg(){
		
		if( AecStringUtils.isNotNullAndEmpty( super.getFrstOrderNo() ) ){
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * insertFlgを取得します。
	 * @return insertFlg
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * insertFlgを設定します。
	 * @param insertFlg insertFlg
	 */
	public void setInsertFlg(int insertFlg) {
		this.insertFlg = insertFlg;
	}

}