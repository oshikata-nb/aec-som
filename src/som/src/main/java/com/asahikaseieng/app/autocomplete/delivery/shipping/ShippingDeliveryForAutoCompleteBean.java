/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.shipping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;

/**
 * 品目マスタのオートコンプリート結果表示用Bean
 * @author t0011036
 */
public class ShippingDeliveryForAutoCompleteBean extends GeneralParameterBean {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String carryCd;

	/**
	 * コンストラクタ
	 */
	public ShippingDeliveryForAutoCompleteBean() {
	}

	/**
	 * コンストラクタ
	 * @param code コード
	 * @param name 名称
	 */
	public ShippingDeliveryForAutoCompleteBean(final String code,
			final String name) {
		super(code, name);
	}

	/**
	 * 運送会社コードを取得します。
	 * @return 運送会社コード
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * 運送会社コードを設定します。
	 * @param carryCd 運送会社コード
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

}
