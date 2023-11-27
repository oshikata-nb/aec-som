/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 納期回答まとめ入力 Formクラス.
 * @author tosco
 */
public final class PurchaseDeliverySumDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

	//
	// インスタンスフィールド

	/** 購買NO */
	private String purchaseNo;

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注日 */
	private String strOrderDate;

	/** 仕入先名称 */
	private String venderName;

	/** 納入先名称 */
	private String locationName;

	/** 全オーダー件数 */
	private String strOrderCount;

	/** 発注書発行済件数 */
	private String strIssuedCount;

	/** 納期調整中件数 */
	private String strAdjustCount;

	/** 納期確定件数 */
	private String strFixedCount;

	/** 入荷・受入済件数 */
	private String strArrivedAcceptedCount;

	/** リスト */
	private List<PurchaseDeliverySumDetailList> detailList;

	/**
	 * コンストラクタ.詳細
	 */
	public PurchaseDeliverySumDetailForm() {
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

		if ("init".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setDetailList(new ArrayList<PurchaseDeliverySumDetailList>());
		}

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 購買NO
		setPurchaseNo(null);
		// 発注書NO
		setOrderSheetNo(null);
		// 発注日
		setStrOrderDate(null);
		// 仕入先名称
		setVenderName(null);
		// 納入先名称
		setLocationName(null);
		// 全オーダー件数
		setStrOrderCount(null);
		// 発注書発行済件数
		setStrIssuedCount(null);
		// 納期調整中件数
		setStrAdjustCount(null);
		// 納期確定件数
		setStrFixedCount(null);
		// 入荷・受入済件数
		setStrArrivedAcceptedCount(null);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注書NO取得.
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定.
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 発注日取得.
	 * @return String 発注日
	 */
	public String getStrOrderDate() {
		return this.strOrderDate;
	}

	/**
	 * 発注日設定.
	 * @param strOrderDate 発注日
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 仕入先名称を取得します。
	 * @return String 仕入先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 仕入先名称を設定します。
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入先名称を取得します。
	 * @return String 納入先名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param locationName 納入先名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 全オーダー件数取得
	 * @return String 全オーダー件数
	 */
	public String getStrOrderCount() {
		return strOrderCount;
	}

	/**
	 * 全オーダー件数設定
	 * @param strOrderCount 全オーダー件数
	 */
	public void setStrOrderCount(final String strOrderCount) {
		this.strOrderCount = strOrderCount;
	}

	/**
	 * 発注書発行済件数取得
	 * @return String 発注書発行済件数
	 */
	public String getStrIssuedCount() {
		return strIssuedCount;
	}

	/**
	 * 発注書発行済件数設定
	 * @param strIssuedCount 発注書発行済件数
	 */
	public void setStrIssuedCount(final String strIssuedCount) {
		this.strIssuedCount = strIssuedCount;
	}

	/**
	 * 納期調整中件数取得
	 * @return String 納期調整中件数
	 */
	public String getStrAdjustCount() {
		return strAdjustCount;
	}

	/**
	 * 納期調整中件数設定
	 * @param strAdjustCount 納期調整中件数
	 */
	public void setStrAdjustCount(final String strAdjustCount) {
		this.strAdjustCount = strAdjustCount;
	}

	/**
	 * 納期確定件数取得
	 * @return BigDecimal 納期確定件数
	 */
	public String getStrFixedCount() {
		return strFixedCount;
	}

	/**
	 * 納期確定件数設定
	 * @param strFixedCount 納期確定件数
	 */
	public void setStrFixedCount(final String strFixedCount) {
		this.strFixedCount = strFixedCount;
	}

	/**
	 * 入荷・受入済件数取得
	 * @return String 入荷・受入済件数
	 */
	public String getStrArrivedAcceptedCount() {
		return strArrivedAcceptedCount;
	}

	/**
	 * 入荷・受入済件数設定
	 * @param strArrivedAcceptedCount 入荷・受入済件数
	 */
	public void setStrArrivedAcceptedCount(final String strArrivedAcceptedCount) {
		this.strArrivedAcceptedCount = strArrivedAcceptedCount;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 明細部リストを取得します。
	 * @return String 明細部リスト
	 */
	public List<PurchaseDeliverySumDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 明細部リストを設定します。
	 * @param detailList 明細部リスト
	 */
	public void setDetailList(final List<PurchaseDeliverySumDetailList> detailList) {
		this.detailList = detailList;
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

}
