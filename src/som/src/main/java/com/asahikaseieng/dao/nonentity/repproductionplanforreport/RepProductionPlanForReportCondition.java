/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repproductionplanforreport;

/**
 * 生産計画入力一覧複数ページ制御クラス.
 * 
 * @author t1344224
 */
public class RepProductionPlanForReportCondition {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepProductionPlanForReportCondition() {
	}

	//
	// 検索入力用
	//
	/** 検索入力：荷主 */
	private String srhShipperDivision;

	/** 検索入力：社内製造品/外注品区分 */
	private String srhTypeDivision;

	/** 検索入力：工場名 */
	private String srhProductionLine;

	/** 検索入力：生産計画年月 */
	private String srhOrderLet;

	//
	// 検索入力
	//

	/**
	 * 検索入力：生産計画年月取得
	 * @return srhOrderLet
	 */
	public String getSrhOrderLet() {
		return srhOrderLet;
	}

	/**
	 * 検索入力：生産計画年月設定
	 * @param srhOrderLet 生産計画年月
	 */
	public void setSrhOrderLet(final String srhOrderLet) {
		this.srhOrderLet = srhOrderLet;
	}

	/**
	 * 検索入力：工場名取得
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return srhProductionLine;
	}

	/**
	 * 検索入力：工場名設定
	 * @param srhProductionLine 工場名
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：荷主取得
	 * @return srhShipperDivision
	 */
	public String getSrhShipperDivision() {
		return srhShipperDivision;
	}

	/**
	 * 検索入力：荷主設定
	 * @param srhShipperDivision 荷主
	 */
	public void setSrhShipperDivision(final String srhShipperDivision) {
		this.srhShipperDivision = srhShipperDivision;
	}

	/**
	 * 検索入力：社内製造品/外注品区分取得
	 * @return srhTypeDivision
	 */
	public String getSrhTypeDivision() {
		return srhTypeDivision;
	}

	/**
	 * 検索入力：社内製造品/外注品区分設定
	 * @param srhTypeDivision 社内製造品/外注品区分
	 */
	public void setSrhTypeDivision(final String srhTypeDivision) {
		this.srhTypeDivision = srhTypeDivision;
	}
}
