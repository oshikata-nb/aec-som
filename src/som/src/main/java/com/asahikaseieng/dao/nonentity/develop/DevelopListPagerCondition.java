/*
 * Created on 2007/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

//import com.asahikaseieng.utils.AecTextUtils;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * DeveloprequestListPagerConditionクラス
 * @author FPC
 */
public class DevelopListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopListPagerCondition() {
		super();
	}

	/* 依頼番号 */
	private String srhDevelopmentRequestNo;

	/* 完成品目コード */
	private String srhItemCd;
	/* 品目名称 */
	private String srhItemName;
	/* 担当区分 */
	private String srhTantoDivision;
	/* ステータス */
	private String srhStatus;
	/* フリーキーワード */
	private String srhDevelopmentDetail;
	/* 依頼客先コード */
	private String srhVenderCd;
	/* 依頼客先名称 */
	private String srhVenderName;
	/* 依頼者コード */
	private String srhRequestTantoCd;
	/* 依頼者 */
	private String srhRequestTantoName;
	/* 開発者コード */
	private String srhDevelopAcceptTantoCd;
	/* 開発者名称 */
	private String srhDevelopAcceptTantoName;

	/* 開発依頼：アイテム */
	private String srhDevItem;
	/* 開発依頼：品番 */
	private String srhDevItemNo;
	/* 開発依頼：関連項目 */
	private String srhDevRelation;

	/**
	 * developmentDetailを取得します。
	 * @return developmentDetail
	 */
	public String getSrhDevelopmentDetail() {
		return srhDevelopmentDetail;
	}

	/**
	 * developmentDetailを設定します。
	 * @param developmentDetail developmentDetail
	 */
	public void setSrhDevelopmentDetail(final String developmentDetail) {
		this.srhDevelopmentDetail = developmentDetail;
	}

	/**
	 * developmentRequestNoを取得します。
	 * @return developmentRequestNo
	 */
	public String getSrhDevelopmentRequestNo() {
		return srhDevelopmentRequestNo;
	}

	/**
	 * developmentRequestNoを設定します。
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setSrhDevelopmentRequestNo(final String developmentRequestNo) {
		this.srhDevelopmentRequestNo = developmentRequestNo;
	}

	/**
	 * developAcceptTantoCdを取得します。
	 * @return developAcceptTantoCd
	 */
	public String getSrhDevelopAcceptTantoCd() {
		return srhDevelopAcceptTantoCd;
	}

	/**
	 * developAcceptTantoCdを設定します。
	 * @param developAcceptTantoCd developAcceptTantoCd
	 */
	public void setSrhDevelopAcceptTantoCd(final String developAcceptTantoCd) {
		this.srhDevelopAcceptTantoCd = developAcceptTantoCd;
	}

	/**
	 * developAcceptTantoNameを取得します。
	 * @return developAcceptTantoName
	 */
	public String getSrhDevelopAcceptTantoName() {
		return srhDevelopAcceptTantoName;
	}

	/**
	 * developAcceptTantoNameを設定します。
	 * @param developAcceptTantoName developAcceptTantoName
	 */
	public void setSrhDevelopAcceptTantoName(final String developAcceptTantoName) {
		this.srhDevelopAcceptTantoName = developAcceptTantoName;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setSrhItemCd(final String itemCd) {
		this.srhItemCd = itemCd;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setSrhItemName(final String itemName) {
		this.srhItemName = itemName;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setSrhVenderCd(final String venderCd) {
		this.srhVenderCd = venderCd;
	}

	/**
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setSrhVenderName(final String venderName) {
		this.srhVenderName = venderName;
	}

	/**
	 * requestTantoCdを取得します。
	 * @return requestTantoCd
	 */
	public String getSrhRequestTantoCd() {
		return srhRequestTantoCd;
	}

	/**
	 * requestTantoCdを設定します。
	 * @param requestTantoCd requestTantoCd
	 */
	public void setSrhRequestTantoCd(final String requestTantoCd) {
		this.srhRequestTantoCd = requestTantoCd;
	}

	/**
	 * requestTantoNameを取得します。
	 * @return requestTantoName
	 */
	public String getSrhRequestTantoName() {
		return srhRequestTantoName;
	}

	/**
	 * requestTantoNameを設定します。
	 * @param requestTantoName requestTantoName
	 */
	public void setSrhRequestTantoName(final String requestTantoName) {
		this.srhRequestTantoName = requestTantoName;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public String getSrhStatus() {
		return srhStatus;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setSrhStatus(final String status) {
		this.srhStatus = status;
	}

	/**
	 * tantoDivisionを取得します。
	 * @return tantoDivision
	 */
	public String getSrhTantoDivision() {
		return srhTantoDivision;
	}

	/**
	 * tantoDivisionを設定します。
	 * @param tantoDivision tantoDivision
	 */
	public void setSrhTantoDivision(final String tantoDivision) {
		this.srhTantoDivision = tantoDivision;
	}


	/**
	 * srhDevItem取得.
	 * @return srhDevItem
	 */
	public String getSrhDevItem() {
		return srhDevItem;
	}

	/**
	 * srhDevItem設定.
	 * @param srhDevItem srhDevItem
	 */
	public void setSrhDevItem(final String srhDevItem) {
		this.srhDevItem = srhDevItem;
	}

	/**
	 * srhDevItemNoを取得します。
	 * @return srhDevItemNo
	 */
	public String getSrhDevItemNo() {
		return srhDevItemNo;
	}

	/**
	 * srhDevItemNoを設定します。
	 * @param srhDevItemNo srhDevItemNo
	 */
	public void setSrhDevItemNo(final String srhDevItemNo) {
		this.srhDevItemNo = srhDevItemNo;
	}

	/**
	 * srhDevRelation取得.
	 * @return srhDevRelation
	 */
	public String getSrhDevRelation() {
		return srhDevRelation;
	}

	/**
	 * srhDevRelation設定.
	 * @param srhDevRelation srhDevRelation
	 */
	public void setSrhDevRelation(final String srhDevRelation) {
		this.srhDevRelation = srhDevRelation;
	}
}
