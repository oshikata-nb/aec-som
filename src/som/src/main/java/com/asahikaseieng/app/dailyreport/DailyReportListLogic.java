/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dailyreport;

import java.util.List;

import com.asahikaseieng.dao.entity.dailyreport.DailyReport;
import com.asahikaseieng.dao.entity.dailyreportheader.DailyReportHeader;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportDetailListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportHeaderListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportListConditionForReport;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportDetailList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportHeaderList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;

// import com.asahikaseieng.exception.NoDataException;

/**
 * 作業日報一覧ロジック interface.
 * @author fml
 */
public interface DailyReportListLogic {

	/**
	 * 検索処理(作業日報ヘッダー)を行う.
	 * @param condition 検索条件
	 * @return DailyReportHeaderList[]
	 */
	DailyReportHeaderList[] getHeaderListEntity(
			final DailyReportListPagerCondition condition);

	/**
	 * 検索処理(作業日報明細)を行う.
	 * @param condition 検索条件
	 * @return DailyReportDetailList[]
	 */
	DailyReportDetailList[] getDetailListEntity(
			final DailyReportListPagerCondition condition);

	/**
	 * 検索処理(作業日報明細)を行う.
	 * @param condition 検索条件
	 * @return DailyReportDetailList[]
	 */
	DailyReportDetailList[] getDetailListItemEntity(
			final DailyReportListPagerCondition condition);

	/**
	 * 生産工場コンボのリスト取得
	 * @return List<LineListForComboboxes>
	 */
	List<LineListForComboboxes> getSrhLineList();

	/**
	 * 検索処理を行う.(ヘッダー)
	 * @param productionDate 製造日
	 * @param productionLine 製造ライン
	 * @param tantoDiv 担当者区分
	 * @param tantoCd 担当者CD
	 * @param seq SEQ
	 * @throws NoDataException NoDataException
	 * @return DailyReportHeader
	 */
	DailyReportHeader getEntityHeader(final String productionLine,
			final java.sql.Timestamp productionDate,
			final java.math.BigDecimal tantoDiv, final String tantoCd,
			final java.math.BigDecimal seq) throws NoDataException;

	/**
	 * 検索処理を行う.(明細)
	 * @param productionDate 製造日
	 * @param productionLine 製造ライン
	 * @param tantoDiv 担当者区分
	 * @param tantoCd 担当者CD
	 * @param directionNo 指図番号
	 * @throws NoDataException NoDataException
	 * @return DailyReport
	 */
	DailyReport getEntityDetail(final String productionLine,
			final java.sql.Timestamp productionDate,
			final java.math.BigDecimal tantoDiv, final String tantoCd,
			final String directionNo) throws NoDataException;

	/**
	 * 作業日報ヘッダーの登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void updateHeader(final DailyReportHeader bean);

	/**
	 * 作業日報ヘッダーの追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insertHeader(final DailyReportHeader bean);

	/**
	 * 作業日報ヘッダーの削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void deleteHeader(final DailyReportHeader bean) throws NoDataException;

	/**
	 * 作業日報の登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void updateDetail(final DailyReport bean);

	/**
	 * 作業日報の追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insertDetail(final DailyReport bean);

	/**
	 * 作業日報の削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void deleteDetail(final DailyReport bean) throws NoDataException;

	/**
	 * 作業日報の削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void deleteDetail(final DailyReportDetailList bean) throws NoDataException;

	/**
	 * 作業時間の合計を計算し、更新処理を行う.
	 * @param productionDate String
	 * @param productionLine String
	 */
	void update(final String productionDate, final String productionLine);

	/**
	 * 担当者検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @return LoginLogin
	 */
	LoginLogin getLoginEntity(final String tantoCd);

	/**
	 * 部署検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getOrganizationEntity(final String organizationCd);

	/**
	 * /** 帳票Excel用詳細リスト取得
	 * @param condition condition
	 * @return List<DailyReportDetailListForReport>
	 */
	List<DailyReportDetailListForReport> getDetailReportList(
			final DailyReportListConditionForReport condition);

	/**
	 * 帳票Excel用ヘッダリスト取得
	 * @param condition condition
	 * @return List<DailyReportHeaderListForReport>
	 */
	List<DailyReportHeaderListForReport> getHeaderReportList(
			final DailyReportListConditionForReport condition);

}
