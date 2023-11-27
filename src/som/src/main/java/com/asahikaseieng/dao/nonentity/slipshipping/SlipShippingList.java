/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.math.BigDecimal;

import com.asahikaseieng.app.comboboxes.SelectLabelPublish;
import com.asahikaseieng.app.comboboxes.SelectSlipShippingShippingStatus;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷帳票_検索結果表示用データ格納クラス.
 *
 * @author tosco
 */
public class SlipShippingList extends SlipShippingListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipShippingList() {
		super();
	}

	/** チェックボックス */
	private boolean slipShippingCheckBox;

	/** 出荷予定日(String) */
	private String strScheduledShippingDate;

	/** ステータス(String) */
	private String strShippingStatus;

	/** 荷札種別(String) */
	private String strLabelPublish;

	/** 出荷伝票(Boolean) */
	private Boolean boolSlipPublishComp;

	/** 出荷指図書(Boolean) */
	private Boolean boolSlipShippingOrderComp;

	/** 出荷予定表(Boolean) */
	private Boolean boolSlipShippingScheduleComp;

	/** 荷札(Boolean) */
	private Boolean boolSlipShippingTagComp;

	/** 出荷依頼書(Boolean) */
	private Boolean boolSlipShippingRequestComp;
	
	/** 運賃表(Boolean) */
	private Boolean boolSlipShippingFareComp;
	
	/** 納品伝票(Boolean) */
	private Boolean boolSlipShippingDeliveryComp;

	/** 新荷札(Boolean) */
	private Boolean boolSlipShippingNewTagComp;

	/** 新郵政(Boolean) */
	private Boolean boolSlipShippingPostalComp;


	/* ---------- callbacker ---------- */

	/**
	 * init.
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		//取得した日付をyyyy/MM/ddに変換し、セット
		setStrScheduledShippingDate(AecDateUtils.dateFormat(getScheduledShippingDate(), "yyyy/MM/dd"));

		//ステータスを文字列に
		if (getShippingStatus() != null  &&  !getShippingStatus().equals("")) {
			setStrShippingStatus(SelectSlipShippingShippingStatus
					.getLabelName(getShippingStatus().toString()));
		}

		//荷札種別
		if (getLabelPublish() != null  &&  !getLabelPublish().equals("")) {
			setStrLabelPublish(SelectLabelPublish.getLabelName(getLabelPublish().toString()));
		}

		//出荷伝票を設定
		if (!getSlipPublishComp().equals(new BigDecimal(0))) {
			setBoolSlipPublishComp(Boolean.TRUE);
		} else {
			setBoolSlipPublishComp(Boolean.FALSE);
		}

		//出荷指図書を設定
		if (!getSlipShippingOrderComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingOrderComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingOrderComp(Boolean.FALSE);
		}

		//出荷予定表を設定
		if (!getSlipShippingScheduleComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingScheduleComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingScheduleComp(Boolean.FALSE);
		}

		//荷札を設定
		if (!getSlipShippingTagComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingTagComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingTagComp(Boolean.FALSE);
		}


		//出荷依頼書を設定
		if (!getSlipShippingRequestComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingRequestComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingRequestComp(Boolean.FALSE);
		}	
		
		//運賃表を設定
		if (!getSlipShippingFareComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingFareComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingFareComp(Boolean.FALSE);
		}
		
		//納品伝票を設定
		if (!getSlipShippingDeliveryComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingDeliveryComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingDeliveryComp(Boolean.FALSE);
		}

		//新荷札を設定
		if (!getSlipShippingNewTagComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingNewTagComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingNewTagComp(Boolean.FALSE);
		}

		//新郵政を設定
		if (!getSlipShippingPostalComp().equals(new BigDecimal(0))) {
			setBoolSlipShippingPostalComp(Boolean.TRUE);
		} else {
			setBoolSlipShippingPostalComp(Boolean.FALSE);
		}	
	}
		
		
	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * チェックボックスを取得します。
	 * @return slipShippingCheckBox
	 */
	public boolean isSlipShippingCheckBox() {
		return slipShippingCheckBox;
	}

	/**
	 * チェックボックスを設定します。
	 * @param slipShippingCheckBox チェックボックス
	 */
	public void setSlipShippingCheckBox(final boolean slipShippingCheckBox) {
		this.slipShippingCheckBox = slipShippingCheckBox;
	}

	/**
	 * 出荷予定日(String) を取得します。
	 * @return strScheduledShippingDate
	 */
	public String getStrScheduledShippingDate() {
		return strScheduledShippingDate;
	}

	/**
	 * 出荷予定日(String) を設定します。
	 * @param strScheduledShippingDate 出荷予定日(String) 
	 */
	public void setStrScheduledShippingDate(final String strScheduledShippingDate) {
		this.strScheduledShippingDate = strScheduledShippingDate;
	}

	/**
	 * ステータス(String)を取得します。
	 * @return strShippingStatus
	 */
	public String getStrShippingStatus() {
		return strShippingStatus;
	}

	/**
	 * ステータス(String)を設定します。
	 * @param strShippingStatus ステータス(String)
	 */
	public void setStrShippingStatus(final String strShippingStatus) {
		this.strShippingStatus = strShippingStatus;
	}

	/**
	 * 荷札種別(String)を取得します。
	 * @return strLabelPublish
	 */
	public String getStrLabelPublish() {
		return strLabelPublish;
	}

	/**
	 * 荷札種別(String)を設定します。
	 * @param strLabelPublish 荷札種別(String)
	 */
	public void setStrLabelPublish(final String strLabelPublish) {
		this.strLabelPublish = strLabelPublish;
	}

	/**
	 * 伝票(Boolean)を取得します。
	 * @return boolSlipPublishComp
	 */
	public Boolean getBoolSlipPublishComp() {
		return boolSlipPublishComp;
	}

	/**
	 * 伝票(Boolean)を設定します。
	 * @param boolSlipPublishComp 伝票(Boolean)
	 */
	public void setBoolSlipPublishComp(final Boolean boolSlipPublishComp) {
		this.boolSlipPublishComp = boolSlipPublishComp;
	}

	/**
	 * 指図書(Boolean)を取得します。
	 * @return boolSlipShippingOrderComp
	 */
	public Boolean getBoolSlipShippingOrderComp() {
		return boolSlipShippingOrderComp;
	}

	/**
	 * 指図書(Boolean)を設定します。
	 * @param boolSlipShippingOrderComp 指図書(Boolean)
	 */
	public void setBoolSlipShippingOrderComp(final Boolean boolSlipShippingOrderComp) {
		this.boolSlipShippingOrderComp = boolSlipShippingOrderComp;
	}

	/**
	 * 依頼書(Boolean)を取得します。
	 * @return boolSlipShippingRequestComp
	 */
	public Boolean getBoolSlipShippingRequestComp() {
		return boolSlipShippingRequestComp;
	}

	/**
	 * 依頼書(Boolean)を設定します。
	 * @param boolSlipShippingRequestComp 依頼書(Boolean)
	 */
	public void setBoolSlipShippingRequestComp(final Boolean boolSlipShippingRequestComp) {
		this.boolSlipShippingRequestComp = boolSlipShippingRequestComp;
	}

	/**
	 * 運賃表(Boolean)を取得します。
	 * @return boolSlipShippingFareComp
	 */
	public Boolean getBoolSlipShippingFareComp() {
		return boolSlipShippingFareComp;
	}

	/**
	 * 運賃表(Boolean)を設定します。
	 * @param boolSlipShippingFareComp 運賃表(Boolean)
	 */
	public void setBoolSlipShippingFareComp(final Boolean boolSlipShippingFareComp) {
		this.boolSlipShippingFareComp = boolSlipShippingFareComp;
	}


	/**
	 * 予定表(Boolean)を取得します。
	 * @return boolSlipShippingScheduleComp
	 */
	public Boolean getBoolSlipShippingScheduleComp() {
		return boolSlipShippingScheduleComp;
	}

	/**
	 * 予定表(Boolean)を設定します。
	 * @param boolSlipShippingScheduleComp 予定表(Boolean)
	 */
	public void setBoolSlipShippingScheduleComp(final Boolean boolSlipShippingScheduleComp) {
		this.boolSlipShippingScheduleComp = boolSlipShippingScheduleComp;
	}

	/**
	 * 荷札(Boolean)を取得します。
	 * @return boolSlipShippingTagComp
	 */
	public Boolean getBoolSlipShippingTagComp() {
		return boolSlipShippingTagComp;
	}

	/**
	 * 荷札(Boolean)を設定します。
	 * @param boolSlipShippingTagComp 荷札(Boolean)
	 */
	public void setBoolSlipShippingTagComp(final Boolean boolSlipShippingTagComp) {
		this.boolSlipShippingTagComp = boolSlipShippingTagComp;
	}


	/**
	 * boolSlipShippingDeliveryCompを取得します。
	 * @return boolSlipShippingDeliveryComp
	 */
	public Boolean getBoolSlipShippingDeliveryComp() {
		return boolSlipShippingDeliveryComp;
	}


	/**
	 * boolSlipShippingDeliveryCompを設定します。
	 * @param boolSlipShippingDeliveryComp boolSlipShippingDeliveryComp
	 */
	public void setBoolSlipShippingDeliveryComp(Boolean boolSlipShippingDeliveryComp) {
		this.boolSlipShippingDeliveryComp = boolSlipShippingDeliveryComp;
	}


	/**
	 * boolSlipShippingNewTagCompを取得します。
	 * @return boolSlipShippingNewTagComp
	 */
	public Boolean getBoolSlipShippingNewTagComp() {
		return boolSlipShippingNewTagComp;
	}


	/**
	 * boolSlipShippingNewTagCompを設定します。
	 * @param boolSlipShippingNewTagComp boolSlipShippingNewTagComp
	 */
	public void setBoolSlipShippingNewTagComp(Boolean boolSlipShippingNewTagComp) {
		this.boolSlipShippingNewTagComp = boolSlipShippingNewTagComp;
	}


	/**
	 * boolSlipShippingPostalCompを取得します。
	 * @return boolSlipShippingPostalComp
	 */
	public Boolean getBoolSlipShippingPostalComp() {
		return boolSlipShippingPostalComp;
	}


	/**
	 * boolSlipShippingPostalCompを設定します。
	 * @param boolSlipShippingPostalComp boolSlipShippingPostalComp
	 */
	public void setBoolSlipShippingPostalComp(Boolean boolSlipShippingPostalComp) {
		this.boolSlipShippingPostalComp = boolSlipShippingPostalComp;
	}



}
