/*
 * Created on 2007/11/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DevelopDtoBaseクラス.
 * @author FPC
 */
public class DevelopBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopBase() {
	}
	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "DEVELOPMENT_REQUEST";

	/** COLUMNアノテーション developmentRequestNo */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション tantoDivision */
	public static final String tantoDivision_COLUMN = "TANTO_DIVISION";

	/** COLUMNアノテーション hopeFinishedDate */
	public static final String hopeFinishedDate_COLUMN = "HOPE_FINISHED_DATE";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション requestTantoCd */
	public static final String requestTantoCd_COLUMN = "REQUEST_TANTO_CD";

	/** COLUMNアノテーション requestTantoName */
	public static final String requestTantoName_COLUMN = "REQUEST_TANTO_NAME";

	/** COLUMNアノテーション requestDate */
	public static final String requestDate_COLUMN = "REQUEST_DATE";

	/** COLUMNアノテーション developAcceptTanto */
	public static final String developAcceptTanto_COLUMN = "DEVELOP_ACCEPT_TANTO_CD";

	/** COLUMNアノテーション developAcceptTantoName */
	public static final String developAcceptTantoName_COLUMN = "DEVELOP_ACCEPT_TANTO_NAME";

	/** COLUMNアノテーション developAcceptDate */
	public static final String developAcceptDate_COLUMN = "DEVELOP_ACCEPT_DATE";

	/** COLUMNアノテーション reqAppTantoCd */
	public static final String reqAppTantoCd_COLUMN = "REQ_APP_TANTO_CD";

	/** COLUMNアノテーション reqAppTantoName */
	public static final String reqAppTantoName_COLUMN = "REQ_APP_TANTO_NAME";

	/** COLUMNアノテーション reqAppDate */
	public static final String reqAppDate_COLUMN = "REQ_APP_DATE";

	/** COLUMNアノテーション developAppTantoCd */
	public static final String developAppTantoCd_COLUMN = "DEVELOP_APP_TANTO_CD";

	/** COLUMNアノテーション developAppTantoName */
	public static final String developAppTantoName_COLUMN = "DEVELOP_APP_TANTO_NAME";

	/** COLUMNアノテーション developAppDate */
	public static final String developAppDate_COLUMN = "DEVELOP_APP_DATE";

	/** COLUMNアノテーション developmentDivision */
	public static final String developmentDivision_COLUMN = "DEVELOPMENT_DIVISION";

	/** COLUMNアノテーション application */
	public static final String application_COLUMN = "APPLICATION";

	/** COLUMNアノテーション property */
	public static final String property_COLUMN = "PROPERTY";

	/** COLUMNアノテーション hopeCostFrom */
	public static final String hopeCostFrom_COLUMN = "HOPE_COST_FROM";

	/** COLUMNアノテーション hopeCostTo */
	public static final String hopeCostTo_COLUMN = "HOPE_COST_TO";

	/** COLUMNアノテーション solventInformation */
	public static final String solventInformation_COLUMN = "SOLVENT_INFORMATION";

	/** COLUMNアノテーション allergyInformation */
	public static final String allergyInformation_COLUMN = "ALLERGY_INFORMATION";

	/** COLUMNアノテーション developmentDetail */
	public static final String developmentDetail_COLUMN = "DEVELOPMENT_DETAIL";

	/** COLUMNアノテーション developmentPlan */
	public static final String developmentPlan_COLUMN = "DEVELOPMENT_PLAN";

	/** COLUMNアノテーション developmentPlanDate */
	public static final String developmentPlanDate_COLUMN = "DEVELOPMENT_PLAN_DATE";

	/** COLUMNアノテーション verificationResult */
	public static final String verificationResult_COLUMN = "VERIFICATION_RESULT";

	/** COLUMNアノテーション verificationResultDate */
	public static final String verificationResultDate_COLUMN = "VERIFICATION_RESULT_DATE";

	/** COLUMNアノテーション revueResult */
	public static final String revueResult_COLUMN = "REVUE_RESULT";

	/** COLUMNアノテーション revueResultDate */
	public static final String revueResultDate_COLUMN = "REVUE_RESULT_DATE";

	/** COLUMNアノテーション finishedDate */
	public static final String finishedDate_COLUMN = "FINISHED_DATE";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション appManTantoCd */
	public static final String appManTantoCd_COLUMN = "APP_MAN_TANTO_CD";

	/** COLUMNアノテーション appManTantoName */
	public static final String appManTantoName_COLUMN = "APP_MAN_TANTO_NAME";

	/** COLUMNアノテーション appManDate */
	public static final String appManDate_COLUMN = "APP_MAN_DATE";

	/** COLUMNアノテーション devItem */
	public static final String devItem_COLUMN = "DEV_ITEM";

	/** COLUMNアノテーション devItemNo */
	public static final String devItemNo_COLUMN = "DEV_ITEM_NO";

	/** COLUMNアノテーション devRelation */
	public static final String devRelation_COLUMN = "DEV_RELATION";

	//
	// インスタンスフィールド
	//

	private String developmentRequestNo;

	private java.math.BigDecimal status;

	private String statusName;

	private java.math.BigDecimal tantoDivision;

	private java.sql.Timestamp hopeFinishedDate;

	private String venderCd;

	private String venderName;

	private String requestTantoCd;

	private String requestTantoName;

	private java.sql.Timestamp requestDate;

	private String developAcceptTantoCd;

	private String developAcceptTantoName;

	private java.sql.Timestamp developAcceptDate;

	private String reqAppTantoCd;

	private String reqAppTantoName;

	private java.sql.Timestamp reqAppDate;

	private String developAppTantoCd;

	private String developAppTantoName;

	private java.sql.Timestamp developAppDate;

	private java.math.BigDecimal developmentDivision;

	private String application;

	private java.math.BigDecimal property;

	private java.math.BigDecimal hopeCostFrom;

	private java.math.BigDecimal hopeCostTo;

	private String solventInformation;

	private String allergyInformation;

	private String developmentDetail;

	private String developmentPlan;

	private java.sql.Timestamp developmentPlanDate;

	private String verificationResult;

	private java.sql.Timestamp verificationResultDate;

	private String revueResult;

	private java.sql.Timestamp revueResultDate;

	private java.sql.Timestamp finishedDate;

	private java.math.BigDecimal cost;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String itemCd;

	private String itemName;

	private String appManTantoCd;

	private String appManTantoName;

	private java.sql.Timestamp appManDate;

	//081009 アイテム(フリーキーワード)
	private String devItem;
	private String devItemNo;
	private String devRelation;

	//
	// インスタンスメソッド
	//

	/**
	 * developmentRequestNo取得.開発依頼番号
	 * @return developmentRequestNo
	 */
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * developmentRequestNo設定.開発依頼番号
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
	}

	/**
	 * status取得.ステータス
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.ステータス
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * statusName取得.ステータス名称
	 * @return statusName
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * statusName設定.ステータス名称
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * tantoDivision取得.担当区分
	 * @return tantoDivision
	 */
	public java.math.BigDecimal getTantoDivision() {
		return this.tantoDivision;
	}

	/**
	 * tantoDivision設定.担当区分
	 * @param tantoDivision tantoDivision
	 */
	public void setTantoDivision(final java.math.BigDecimal tantoDivision) {
		this.tantoDivision = tantoDivision;
	}

	/**
	 * hopeFinishedDate取得.希望完成日
	 * @return hopeFinishedDate
	 */
	public java.sql.Timestamp getHopeFinishedDate() {
		return this.hopeFinishedDate;
	}

	/**
	 * hopeFinishedDate設定.希望完成日
	 * @param hopeFinishedDate hopeFinishedDate
	 */
	public void setHopeFinishedDate(final java.sql.Timestamp hopeFinishedDate) {
		this.hopeFinishedDate = hopeFinishedDate;
	}

	/**
	 * vender取得.依頼元コード
	 * @return vender
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * vender設定.依頼元コード
	 * @param vender vender
	 */
	public void setVenderCd(final String vender) {
		this.venderCd = vender;
	}

	/**
	 * venderName取得.依頼元名称
	 * @return venderName
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * venderName設定.依頼元名称
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * requestTantoCd取得.依頼者コード
	 * @return requestTantoCd
	 */
	public String getRequestTantoCd() {
		return this.requestTantoCd;
	}

	/**
	 * requestTantoCd設定.依頼者コード
	 * @param requestTantoCd requestTantoCd
	 */
	public void setRequestTantoCd(final String requestTantoCd) {
		this.requestTantoCd = requestTantoCd;
	}

	/**
	 * requestTantoName取得.依頼者
	 * @return requestTantoName
	 */
	public String getRequestTantoName() {
		return this.requestTantoName;
	}

	/**
	 * requestTantoName設定.依頼者
	 * @param requestTantoName requestTantoName
	 */
	public void setRequestTantoName(final String requestTantoName) {
		this.requestTantoName = requestTantoName;
	}

	/**
	 * requestDate取得.開発依頼日
	 * @return requestDate
	 */
	public java.sql.Timestamp getRequestDate() {
		return this.requestDate;
	}

	/**
	 * requestDate設定.開発依頼日
	 * @param requestDate requestDate
	 */
	public void setRequestDate(final java.sql.Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * developmentPlan取得.開発計画
	 * @return developmentPlan
	 */
	public String getDevelopmentPlan() {
		return this.developmentPlan;
	}

	/**
	 * developmentPlan設定.開発計画
	 * @param developmentPlan developmentPlan
	 */
	public void setDevelopmentPlan(final String developmentPlan) {
		this.developmentPlan = developmentPlan;
	}

	/**
	 * developmentPlanDate取得.開発計画登録日
	 * @return developmentPlanDate
	 */
	public java.sql.Timestamp getDevelopmentPlanDate() {
		return this.developmentPlanDate;
	}

	/**
	 * developmentPlanDate設定.開発計画登録日
	 * @param developmentPlanDate developmentPlanDate
	 */
	public void setDevelopmentPlanDate(final java.sql.Timestamp developmentPlanDate) {
		this.developmentPlanDate = developmentPlanDate;
	}

	/**
	 * finishedDate取得.完成日
	 * @return finishedDate
	 */
	public java.sql.Timestamp getFinishedDate() {
		return this.finishedDate;
	}

	/**
	 * finishedDate設定.完成日
	 * @param finishedDate finishedDate
	 */
	public void setFinishedDate(final java.sql.Timestamp finishedDate) {
		this.finishedDate = finishedDate;
	}

	/**
	 * developmentDivision取得.依頼内容
	 * @return developmentDivision
	 */
	public java.math.BigDecimal getDevelopmentDivision() {
		return this.developmentDivision;
	}

	/**
	 * developmentDivision設定.依頼内容
	 * @param developmentDivision developmentDivision
	 */
	public void setDevelopmentDivision(final java.math.BigDecimal developmentDivision) {
		this.developmentDivision = developmentDivision;
	}

	/**
	 * allergyInformation取得.アレルギー情報
	 * @return allergyInformation
	 */
	public String getAllergyInformation() {
		return this.allergyInformation;
	}

	/**
	 * allergyInformation設定.アレルギー情報
	 * @param allergyInformation allergyInformation
	 */
	public void setAllergyInformation(final String allergyInformation) {
		this.allergyInformation = allergyInformation;
	}

	/**
	 * application取得.用途
	 * @return application
	 */
	public String getApplication() {
		return this.application;
	}

	/**
	 * application設定.用途
	 * @param application application
	 */
	public void setApplication(final String application) {
		this.application = application;
	}

	/**
	 * hopeCostFrom取得.希望原価(FROM)
	 * @return hopeCostFrom
	 */
	public java.math.BigDecimal getHopeCostFrom() {
		return this.hopeCostFrom;
	}

	/**
	 * hopeCostFrom設定.希望原価(FROM)
	 * @param hopeCostFrom hopeCostFrom
	 */
	public void setHopeCostFrom(final java.math.BigDecimal hopeCostFrom) {
		this.hopeCostFrom = hopeCostFrom;
	}

	/**
	 * hopeCostTo取得.希望原価(TO)
	 * @return hopeCostTo
	 */
	public java.math.BigDecimal getHopeCostTo() {
		return this.hopeCostTo;
	}

	/**
	 * hopeCostTo設定.希望原価(TO)
	 * @param hopeCostTo hopeCostTo
	 */
	public void setHopeCostTo(final java.math.BigDecimal hopeCostTo) {
		this.hopeCostTo = hopeCostTo;
	}

	/**
	 * property取得.香料性状
	 * @return property
	 */
	public java.math.BigDecimal getProperty() {
		return this.property;
	}

	/**
	 * property設定.香料性状
	 * @param property property
	 */
	public void setProperty(final java.math.BigDecimal property) {
		this.property = property;
	}

	/**
	 * solventInformation取得.溶剤指定
	 * @return solventInformation
	 */
	public String getSolventInformation() {
		return this.solventInformation;
	}

	/**
	 * solventInformation設定.溶剤指定
	 * @param solventInformation solventInformation
	 */
	public void setSolventInformation(final String solventInformation) {
		this.solventInformation = solventInformation;
	}

	/**
	 * developmentDetail取得.詳細内容
	 * @return developmentDetail
	 */
	public String getDevelopmentDetail() {
		return this.developmentDetail;
	}

	/**
	 * developmentDetail設定.詳細内容
	 * @param developmentDetail developmentDetail
	 */
	public void setDevelopmentDetail(final String developmentDetail) {
		this.developmentDetail = developmentDetail;
	}

	/**
	 * developAcceptTanto取得.開発者コード
	 * @return developAcceptTanto
	 */
	public String getDevelopAcceptTantoCd() {
		return this.developAcceptTantoCd;
	}

	/**
	 * developAcceptTantoCd設定.開発者コード
	 * @param developAcceptTantoCd developAcceptTantoCd
	 */
	public void setDevelopAcceptTantoCd(final String developAcceptTantoCd) {
		this.developAcceptTantoCd = developAcceptTantoCd;
	}

	/**
	 * developAcceptTantoName取得.開発者名称
	 * @return developAcceptTantoName
	 */
	public String getDevelopAcceptTantoName() {
		return this.developAcceptTantoName;
	}

	/**
	 * developAcceptTantoName設定.開発者名称
	 * @param developAcceptTantoName developAcceptTantoName
	 */
	public void setDevelopAcceptTantoName(final String developAcceptTantoName) {
		this.developAcceptTantoName = developAcceptTantoName;
	}

	/**
	 * developAcceptDate取得.開発受領日
	 * @return developAcceptDate
	 */
	public java.sql.Timestamp getDevelopAcceptDate() {
		return this.developAcceptDate;
	}

	/**
	 * developAcceptDate設定.開発受領日
	 * @param developAcceptDate developAcceptDate
	 */
	public void setDevelopAcceptDate(final java.sql.Timestamp developAcceptDate) {
		this.developAcceptDate = developAcceptDate;
	}

	/**
	 * reqAppTantoCd取得.営業承認者コード
	 * @return reqAppTantoCd
	 */
	public String getReqAppTantoCd() {
		return this.reqAppTantoCd;
	}

	/**
	 * reqAppTantoCd設定.営業承認者コード
	 * @param reqAppTantoCd reqAppTantoCd
	 */
	public void setReqAppTantoCd(final String reqAppTantoCd) {
		this.reqAppTantoCd = reqAppTantoCd;
	}

	/**
	 * reqAppTantoName取得.営業承認者名称
	 * @return reqAppTantoName
	 */
	public String getReqAppTantoName() {
		return this.reqAppTantoName;
	}

	/**
	 * reqAppTantoName設定.営業承認者名称
	 * @param reqAppTantoName reqAppTantoName
	 */
	public void setReqAppTantoName(final String reqAppTantoName) {
		this.reqAppTantoName = reqAppTantoName;
	}

	/**
	 * reqAppDate取得.開発依頼承認日
	 * @return reqAppDate
	 */
	public java.sql.Timestamp getReqAppDate() {
		return this.reqAppDate;
	}

	/**
	 * reqAppDate設定.開発依頼承認日
	 * @param reqAppDate reqAppDate
	 */
	public void setReqAppDate(final java.sql.Timestamp reqAppDate) {
		this.reqAppDate = reqAppDate;
	}

	/**
	 * developAppTantoCd取得.研究開発承認者
	 * @return developAppTantoCd
	 */
	public String getDevelopAppTantoCd() {
		return this.developAppTantoCd;
	}

	/**
	 * developAppTantoCd設定.研究開発承認者
	 * @param developAppTantoCd developAppTantoCd
	 */
	public void setDevelopAppTantoCd(final String developAppTantoCd) {
		this.developAppTantoCd = developAppTantoCd;
	}

	/**
	 * developAppTantoName取得.研究開発承認者
	 * @return developAppTantoName
	 */
	public String getDevelopAppTantoName() {
		return this.developAppTantoName;
	}

	/**
	 * developAppTantoName設定.研究開発承認者
	 * @param developAppTantoName developAppTantoName
	 */
	public void setDevelopAppTantoName(final String developAppTantoName) {
		this.developAppTantoName = developAppTantoName;
	}

	/**
	 * developAppDate取得.研究開発承認日(開発完了日)
	 * @return developAppDate
	 */
	public java.sql.Timestamp getDevelopAppDate() {
		return this.developAppDate;
	}

	/**
	 * developAppDate設定.研究開発承認日(開発完了日)
	 * @param developAppDate developAppDate
	 */
	public void setDevelopAppDate(final java.sql.Timestamp developAppDate) {
		this.developAppDate = developAppDate;
	}

	/**
	 * revueResult取得.レビュー結果
	 * @return revueResult
	 */
	public String getRevueResult() {
		return this.revueResult;
	}

	/**
	 * revueResult設定.レビュー結果
	 * @param revueResult revueResult
	 */
	public void setRevueResult(final String revueResult) {
		this.revueResult = revueResult;
	}

	/**
	 * revueResultDate取得.レビュー結果登録日
	 * @return revueResultDate
	 */
	public java.sql.Timestamp getRevueResultDate() {
		return this.revueResultDate;
	}

	/**
	 * revueResultDate設定.レビュー結果登録日
	 * @param revueResultDate revueResultDate
	 */
	public void setRevueResultDate(final java.sql.Timestamp revueResultDate) {
		this.revueResultDate = revueResultDate;
	}

	/**
	 * verificationResult取得.検証結果
	 * @return verificationResult
	 */
	public String getVerificationResult() {
		return this.verificationResult;
	}

	/**
	 * verificationResult設定.検証結果
	 * @param verificationResult verificationResult
	 */
	public void setVerificationResult(final String verificationResult) {
		this.verificationResult = verificationResult;
	}

	/**
	 * verificationResultDate取得.検証結果登録日
	 * @return verificationResultDate
	 */
	public java.sql.Timestamp getVerificationResultDate() {
		return this.verificationResultDate;
	}

	/**
	 * verificationResultDate設定.検証結果登録日
	 * @param verificationResultDate verificationResultDate
	 */
	public void setVerificationResultDate(final java.sql.Timestamp verificationResultDate) {
		this.verificationResultDate = verificationResultDate;
	}

	/**
	 * costを取得します。.原価
	 * @return cost
	 */
	public java.math.BigDecimal getCost() {
		return cost;
	}


	/**
	 * costを設定します。.原価
	 * @param cost cost
	 */
	public void setCost(final java.math.BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * appManTantoCd取得.責任者承認者
	 * @return appManTantoCd
	 */
	public String getAppManTantoCd() {
		return this.appManTantoCd;
	}

	/**
	 * appManTantoCd設定.責任者承認者
	 * @param appManTantoCd appManTantoCd
	 */
	public void setAppManTantoCd(final String appManTantoCd) {
		this.appManTantoCd = appManTantoCd;
	}

	/**
	 * appManTantoName取得.責任者承認者
	 * @return appManTantoName
	 */
	public String getAppManTantoName() {
		return this.appManTantoName;
	}

	/**
	 * appManTantoName設定.責任者承認者
	 * @param appManTantoName appManTantoName
	 */
	public void setAppManTantoName(final String appManTantoName) {
		this.appManTantoName = appManTantoName;
	}

	/**
	 * appManDate取得.責任者承認日
	 * @return appManDate
	 */
	public java.sql.Timestamp getAppManDate() {
		return this.appManDate;
	}

	/**
	 * appManDate設定.責任者承認日
	 * @param appManDate appManDate
	 */
	public void setAppManDate(final java.sql.Timestamp appManDate) {
		this.appManDate = appManDate;
	}

	/**
	 * itemCd取得.品目コード
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.品目コード
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * itemName取得.品目名称
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.品目名称
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * inputDateを取得します。.登録日時
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。.登録日時
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。.登録者コード
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。.登録者コード
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDateを取得します。.更新日時
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。.更新日時
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。.更新者コード
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。.更新者コード
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * devItem取得.
	 * @return devItem
	 */
	public String getDevItem() {
		return devItem;
	}

	/**
	 * devItem設定.
	 * @param devItem devItem
	 */
	public void setDevItem(final String devItem) {
		this.devItem = devItem;
	}

	/**
	 * devItemNo取得.
	 * @return devItemNo
	 */
	public String getDevItemNo() {
		return devItemNo;
	}

	/**
	 * devItemNo設定.
	 * @param devItemNo devItemNo
	 */
	public void setDevItemNo(final String devItemNo) {
		this.devItemNo = devItemNo;
	}

	/**
	 * devRelation取得.
	 * @return devRelation
	 */
	public String getDevRelation() {
		return devRelation;
	}

	/**
	 * devRelation設定.
	 * @param devRelation devRelation
	 */
	public void setDevRelation(final String devRelation) {
		this.devRelation = devRelation;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
