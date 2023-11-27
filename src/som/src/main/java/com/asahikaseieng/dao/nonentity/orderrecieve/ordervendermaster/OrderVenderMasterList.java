/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster;

import java.math.BigDecimal;


/**
 * OrderVenderLinkListクラス.
 * @author 
 */
public class OrderVenderMasterList extends OrderVenderMasterListBase {

	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ.
	 */
	public OrderVenderMasterList() {
		super();
	}
	
	//
	// 定数
	//

	/** COLUMNアノテーション venderGroupName */
	public static final String venderGroupName_COLUMN = "VENDER_GROUP_NAME";
	
	/** COLUMNアノテーション deliveryConf */
	public static final String deliveryConf_COLUMN = "DELIVERY_CONF";
	
	/** COLUMNアノテーション deliveryConf */
	public static final String deliveryConfName_COLUMN = "DELIVERY_CONF_NAME";
	
	/** COLUMNアノテーション itemConf */
	public static final String itemConf_COLUMN = "ITEM_CONF";
	
	/** COLUMNアノテーション itemConf */
	public static final String itemConfName_COLUMN = "ITEM_CONF_NAME";
	
	//
	// インスタンスフィールド
	//
	
	private BigDecimal deliveryConf;
	
	private String deliveryConfName;
	
	private BigDecimal itemConf;
	
	private String itemConfName;
	
	/** ラジオボタン*/
	private Boolean rdoBtn;
	
	/** ラジオボタンのチェック行 */
	private Boolean checkFlg;
	
	private Integer checkRowNo;
	
	/* ---------- getter ,setter ---------- */
	
	/**
	 * deliveryConfを取得します。
	 * @return deliveryConf
	 */
	public BigDecimal getDeliveryConf() {
		return deliveryConf;
	}

	/**
	 * deliveryConfを設定します。
	 * @param deliveryConf deliveryConf
	 */
	public void setDeliveryConf(BigDecimal deliveryConf) {
		this.deliveryConf = deliveryConf;
	}

	/**
	 * deliveryConfNameを取得します。
	 * @return deliveryConfName
	 */
	public String getDeliveryConfName() {
		return deliveryConfName;
	}

	/**
	 * deliveryConfNameを設定します。
	 * @param deliveryConfName deliveryConfName
	 */
	public void setDeliveryConfName(String deliveryConfName) {
		this.deliveryConfName = deliveryConfName;
	}

	/**
	 * itemConfを取得します。
	 * @return itemConf
	 */
	public BigDecimal getItemConf() {
		return itemConf;
	}

	/**
	 * itemConfを設定します。
	 * @param itemConf itemConf
	 */
	public void setItemConf(BigDecimal itemConf) {
		this.itemConf = itemConf;
	}
	
	/**
	 * itemConfNameを取得します。
	 * @return itemConfName
	 */
	public String getItemConfName() {
		return itemConfName;
	}

	/**
	 * itemConfNameを設定します。
	 * @param itemConfName itemConfName
	 */
	public void setItemConfName(String itemConfName) {
		this.itemConfName = itemConfName;
	}
	
	/**
	 * checkFlgを取得します。
	 * @return checkFlg
	 */
	public Boolean getCheckFlg() {
		return checkFlg;
	}

	/**
	 * checkFlgを設定します。
	 * @param checkFlg checkFlg
	 */
	public void setCheckFlg(Boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * rdoBtnを取得します。
	 * @return rdoBtn
	 */
	public Boolean getRdoBtn() {
		return rdoBtn;
	}

	/**
	 * rdoBtnを設定します。
	 * @param rdoBtn rdoBtn
	 */
	public void setRdoBtn(Boolean rdoBtn) {
		this.rdoBtn = rdoBtn;
	}

	/**
	 * checkRowNoを取得します。
	 * @return checkRowNo
	 */
	public Integer getCheckRowNo() {
		return checkRowNo;
	}

	/**
	 * checkRowNoを設定します。
	 * @param checkRowNo checkRowNo
	 */
	public void setCheckRowNo(Integer checkRowNo) {
		this.checkRowNo = checkRowNo;
	}

}

