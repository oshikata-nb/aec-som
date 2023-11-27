/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.order;

import java.math.BigDecimal;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 受注用品目マスタのオートコンプリート結果表示用Bean
 * @author tosco
 */
public class OrderItemForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 帳合コード */
	private String balanceCd;

	/** ロット管理区分 */
	private BigDecimal lotDivision;

	/**
	 * コンストラクタ
	 */
	public OrderItemForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public OrderItemForAutoCompleteBean(final String code, final String name) {
		super(code, name);
	}

	/**
	 * 他社コード１を取得します。
	 * @return 他社コード１
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return 運用管理単位
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitOfOperationManagement 運用管理単位
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * 帳合コードを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * 帳合コードを設定します。
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
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
