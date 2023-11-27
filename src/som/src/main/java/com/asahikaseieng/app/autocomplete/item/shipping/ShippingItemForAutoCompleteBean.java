/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.shipping;

import java.math.BigDecimal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 品目マスタのオートコンプリート結果表示用Bean
 * @author tosco
 */
public class ShippingItemForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 名称１ */
	private String name01;

	/** 小数点桁数 */
	private String smallnumLength;

	/** 端数区分 */
	private String roundDivision;

	/** ロット管理区分 */
	private BigDecimal lotDivision;

	/**
	 * コンストラクタ
	 */
	public ShippingItemForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * 
	 * @param name 名称
	 */
	public ShippingItemForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

	/**
	 * 他社コード１を取得します。
	 * 
	 * @return 他社コード１
	 * 
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * 
	 * @param otherCompanyCd1 他社コード１
	 * 
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿を取得します。
	 * 
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * 
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 運用管理単位を取得します。
	 * 
	 * @return 運用管理単位
	 * 
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * 
	 * @param unitOfOperationManagement 運用管理単位
	 * 
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 名称１を取得します。
	 * 
	 * @return 名称１
	 * 
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * 名称１を設定します。
	 * 
	 * @param name01 名称１
	 * 
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * 小数点桁数を取得します。
	 * 
	 * @return 小数点桁数
	 */
	public String getSmallnumLength() {
		return smallnumLength;
	}

	/**
	 * 小数点桁数を設定します。
	 * 
	 * @param smallnumLength 小数点桁数
	 */
	public void setSmallnumLength(final String smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * 端数区分を取得します。
	 * 
	 * @return 端数区分
	 * 
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * 
	 * @param roundDivision 端数区分
	 * 
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * lotDivisionを取得します。
	 * @return lotDivision
	 */
	public BigDecimal getLotDivision() {
		return lotDivision;
	}

	/**
	 * lotDivisionを設定します。
	 * @param lotDivision lotDivision
	 */
	public void setLotDivision(final BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}
}
