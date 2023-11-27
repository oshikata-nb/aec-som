/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchList;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * ロットマスタ検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class OrderLotSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** 他社コード1 */
	private String srhOtherCompanyCd1;

	/** 荷姿 */
	private String srhStyleOfPacking;

	/** 単位区分 */
	private String srhUnitDivision;

	/** 取引先区分 */
	private String venderDivision;

	/** 取引先コード */
	private String venderCd;

	/** ロケーションコード */
	private String srhLocationCd;

	/** ロケーション名称 */
	private String srhLocationName;

	/** ロット番号 */
	private String srhLotNo;

	/** 検索結果リスト */
	private List<OrderLotSearchList> searchList;

	/** 販売引当残り */
	private String strSalesAssignQty;

	/** 販売引当残り */
	private String strPointingOrderQty;

	
	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.lot.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.lot.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public OrderLotSearchForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return OrderLotSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 初期化.ロット検索
	 */
	public void clear() {

		/** 検索リストのクリア */
		setSearchList(new ArrayList<OrderLotSearchList>());

		/** 検索入力：ロット番号 */
		setSrhLotNo(null);

	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 品目コード設定
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return this.srhItemName;
	}

	/**
	 * 品目名称設定
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param srhOtherCompanyCd1 他社コード1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getSrhStyleOfPacking() {
		return this.srhStyleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param srhStyleOfPacking 荷姿
	 */
	public void setSrhStyleOfPacking(final String srhStyleOfPacking) {
		this.srhStyleOfPacking = srhStyleOfPacking;
	}

	/**
	 * 単位区分
	 * @return String 単位区分
	 */
	public String getSrhUnitDivision() {
		return this.srhUnitDivision;
	}

	/**
	 * 単位区分
	 * @param srhUnitDivision 単位区分
	 */
	public void setSrhUnitDivision(final String srhUnitDivision) {
		this.srhUnitDivision = srhUnitDivision;
	}

	/**
	 * 取引先区分取得
	 * @return String 取引先区分
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * 取引先区分設定
	 * @param venderDivision 取引先区分
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * 取引先コード取得
	 * @return String 取引先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 取引先コード設定
	 * @param venderCd 取引先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * ロケーションコード
	 * @return String ロケーションコード
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * ロケーションコード
	 * @param srhLocationCd ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * ロケーション名称
	 * @return String ロケーション名称
	 */
	public String getSrhLocationName() {
		return this.srhLocationName;
	}

	/**
	 * ロケーション名称
	 * @param srhLocationName ロケーション名称
	 */
	public void setSrhLocationName(final String srhLocationName) {
		this.srhLocationName = srhLocationName;
	}

	/**
	 * ロット番号
	 * @return String ロット番号
	 */
	public String getSrhLotNo() {
		return this.srhLotNo;
	}

	/**
	 * ロット番号
	 * @param srhLotNo ロット番号
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = srhLotNo;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<OrderLotSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<OrderLotSearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 販売引当残り
	 * @return String 販売引当残り
	 */
	public String getStrSalesAssignQty() {
		return strSalesAssignQty;
	}

	/**
	 * 販売引当残り
	 * @param strSalesAssignQty 販売引当残り
	 */
	public void setStrSalesAssignQty(final String strSalesAssignQty) {
		this.strSalesAssignQty = strSalesAssignQty;
	}

	/**
	 * strPointingOrderQtyを取得します。
	 * @return strPointingOrderQty
	 */
	public String getStrPointingOrderQty() {
		return strPointingOrderQty;
	}

	/**
	 * strPointingOrderQtyを設定します。
	 * @param strPointingOrderQty strPointingOrderQty
	 */
	public void setStrPointingOrderQty(String strPointingOrderQty) {
		this.strPointingOrderQty = strPointingOrderQty;
	}

}
