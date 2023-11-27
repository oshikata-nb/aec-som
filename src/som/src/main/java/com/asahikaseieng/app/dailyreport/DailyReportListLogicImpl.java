/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dailyreport;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.dailyreport.DailyReport;
import com.asahikaseieng.dao.entity.dailyreport.DailyReportDao;
import com.asahikaseieng.dao.entity.dailyreportheader.DailyReportHeader;
import com.asahikaseieng.dao.entity.dailyreportheader.DailyReportHeaderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportDetailListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportHeaderListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportListConditionForReport;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportDetailList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportDetailListDao;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportHeaderList;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportHeaderListDao;
import com.asahikaseieng.dao.nonentity.dailyreport.DailyReportListPagerCondition;
import com.asahikaseieng.dao.nonentity.dailyreport.directionheaderupdate.DirectionHeaderUpdateDao;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLoginDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 作業日報一覧ロジック 実装クラス.
 * @author fml
 */
public class DailyReportListLogicImpl implements DailyReportListLogic {

	private LineListForComboboxesDao lineListForComboboxesDao;

	private DailyReportHeaderListDao dailyReportHeaderListDao;

	private DailyReportDetailListDao dailyReportDetailListDao;

	private DailyReportHeaderDao dailyReportHeaderDao;

	private DailyReportDao dailyReportDao;

	private DirectionHeaderUpdateDao directionHeaderUpdateDao;

	private LoginLoginDao loginLoginDao;

	private OrganizationDetailDao organizationDetailDao;

	private DailyReportDetailListForReportDao dailyReportDetailListForReportDao;

	private DailyReportHeaderListForReportDao dailyReportHeaderListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public DailyReportListLogicImpl() {
	}

	/**
	 * 生産工場リスト取得
	 * @return List<LineListForComboboxes>
	 */
	public List<LineListForComboboxes> getSrhLineList() {
		List<LineListForComboboxes> list = lineListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 帳票Excel用ヘッダリスト取得
	 * @param condition condition
	 * @return List<LineListForComboboxes>
	 */
	public List<DailyReportHeaderListForReport> getHeaderReportList(
			final DailyReportListConditionForReport condition) {
		List<DailyReportHeaderListForReport> list = dailyReportHeaderListForReportDao
				.getHeaderListForReprot(condition);
		return list;
	}

	/**
	 * 帳票Excel用詳細リスト取得
	 * @param condition condition
	 * @return List<LineListForComboboxes>
	 */
	public List<DailyReportDetailListForReport> getDetailReportList(
			final DailyReportListConditionForReport condition) {
		List<DailyReportDetailListForReport> list = dailyReportDetailListForReportDao
				.getDetailListForReprot(condition);
		return list;
	}

	/**
	 * 作業日報ヘッダー取得
	 * @param condition 検索条件
	 * @return DailyReportHeaderList
	 */
	public DailyReportHeaderList[] getHeaderListEntity(
			final DailyReportListPagerCondition condition) {
		/* パラメータチェック */
		checkParams(condition);

		DailyReportHeaderList[] bean = dailyReportHeaderListDao
				.getHeaderListEntity(condition);
		return bean;
	}

	/**
	 * 作業日報明細取得
	 * @param condition 検索条件
	 * @return DailyReportDetailList
	 */
	public DailyReportDetailList[] getDetailListEntity(
			final DailyReportListPagerCondition condition) {
		/* パラメータチェック */
		checkParams(condition);

		DailyReportDetailList[] bean = dailyReportDetailListDao
				.getDetailListEntity(condition);
		return bean;
	}

	/**
	 * 作業日報明細取得
	 * @param condition 検索条件
	 * @return DailyReportDetailList
	 */
	public DailyReportDetailList[] getDetailListItemEntity(
			final DailyReportListPagerCondition condition) {
		/* パラメータチェック */
		checkParams(condition);

		DailyReportDetailList[] bean = dailyReportDetailListDao
				.getDetailListItemEntity(condition);
		return bean;
	}

	/**
	 * 作業日報ヘッダー検索（登録用）
	 * @param productionDate 製造日
	 * @param productionLine 製造ライン
	 * @param tantoDivision 担当区分
	 * @param tantoCd 担当者CD
	 * @param seq SEQ
	 * @return DailyReportHeader
	 * @throws NoDataException NoDataException
	 */
	public DailyReportHeader getEntityHeader(final String productionLine,
			final java.sql.Timestamp productionDate,
			final java.math.BigDecimal tantoDivision, final String tantoCd,
			final java.math.BigDecimal seq) throws NoDataException {

		if (productionDate == null) {
			throw new IllegalArgumentException("productionDate is empty");
		}
		if (StringUtils.isEmpty(productionLine)) {
			throw new IllegalArgumentException("productionLine is empty");
		}
		if (seq == null) {
			throw new IllegalArgumentException("seq is empty");
		}
		if (tantoDivision == null) {
			throw new IllegalArgumentException("tantoDivision is empty");
		}
		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}

		DailyReportHeader bean = dailyReportHeaderDao.getEntity(productionLine,
			productionDate, tantoDivision, tantoCd, seq);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 作業日報検索（登録用）
	 * @param productionDate 製造日
	 * @param productionLine 製造ライン
	 * @param tantoDivision 担当区分
	 * @param tantoCd 担当者CD
	 * @param directionNo 指図番号
	 * @return DailyReport
	 * @throws NoDataException NoDataException
	 */
	public DailyReport getEntityDetail(final String productionLine,
			final java.sql.Timestamp productionDate,
			final java.math.BigDecimal tantoDivision, final String tantoCd,
			final String directionNo) throws NoDataException {

		if (productionDate == null) {
			throw new IllegalArgumentException("productionDate is empty");
		}
		if (StringUtils.isEmpty(productionLine)) {
			throw new IllegalArgumentException("productionLine is empty");
		}
		if (tantoDivision == null) {
			throw new IllegalArgumentException("tantoDivision is empty");
		}
		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}
		if (StringUtils.isEmpty(directionNo)) {
			throw new IllegalArgumentException("directionNo is empty");
		}

		DailyReport bean = dailyReportDao.getEntity(productionLine,
			productionDate, tantoDivision, tantoCd, directionNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 作業日報ヘッダーの更新
	 * @param bean 登録データ
	 */
	public void updateHeader(final DailyReportHeader bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			dailyReportHeaderDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException ee) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 作業日報ヘッダーの登録
	 * @param bean 登録データ
	 */
	public void insertHeader(final DailyReportHeader bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			dailyReportHeaderDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 作業日報ヘッダーの削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteHeader(final DailyReportHeader bean)
			throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			dailyReportHeaderDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 作業日報の更新
	 * @param bean 登録データ
	 */
	public void updateDetail(final DailyReport bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			dailyReportDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 作業日報の登録
	 * @param bean 登録データ
	 */
	public void insertDetail(final DailyReport bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			dailyReportDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 作業日報の削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteDetail(final DailyReport bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			dailyReportDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 作業日報の削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteDetail(final DailyReportDetailList bean)
			throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			dailyReportDetailListDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 作業時間の合計を計算し、更新処理を行う.
	 * @param productionDate String
	 * @param productionLine String
	 */
	public void update(final String productionDate, final String productionLine) {
		if (StringUtils.isEmpty(productionDate)) {
			throw new IllegalArgumentException("productionDate is empty");
		}
		if (StringUtils.isEmpty(productionLine)) {
			throw new IllegalArgumentException("productionLine is empty");
		}

		try {
			/* 更新処理 */
			directionHeaderUpdateDao.update(productionDate, productionLine);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginLogin
	 */
	public LoginLogin getLoginEntity(final String tantoCd) {
		LoginLogin bean = loginLoginDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 部署検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getOrganizationEntity(final String organizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(organizationCd);
		return bean;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final DailyReportListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * dailyReportDaoを設定します。
	 * @param dailyReportDao dailyReportDao
	 */
	public void setDailyReportDao(final DailyReportDao dailyReportDao) {
		this.dailyReportDao = dailyReportDao;
	}

	/**
	 * dailyReportHeaderDaoを設定します。
	 * @param dailyReportHeaderDao dailyReportHeaderDao
	 */
	public void setDailyReportHeaderDao(
			final DailyReportHeaderDao dailyReportHeaderDao) {
		this.dailyReportHeaderDao = dailyReportHeaderDao;
	}

	/**
	 * dailyReportHeaderListDaoを設定します。
	 * @param dailyReportHeaderListDao DailyReportHeaderListDao
	 */
	public void setDailyReportHeaderListDao(
			final DailyReportHeaderListDao dailyReportHeaderListDao) {
		this.dailyReportHeaderListDao = dailyReportHeaderListDao;
	}

	/**
	 * dailyReportDetailListDaoを設定します。
	 * @param dailyReportDetailListDao DailyReportDetailListDao
	 */
	public void setDailyReportDetailListDao(
			final DailyReportDetailListDao dailyReportDetailListDao) {
		this.dailyReportDetailListDao = dailyReportDetailListDao;
	}

	/**
	 * directionHeaderDaoを設定します。
	 * @param directionHeaderUpdateDao DirectionHeaderUpdateDao
	 */
	public void setDirectionHeaderUpdateDao(
			final DirectionHeaderUpdateDao directionHeaderUpdateDao) {
		this.directionHeaderUpdateDao = directionHeaderUpdateDao;
	}

	/**
	 * lineListForComboboxesDaoを設定します。
	 * @param lineListForComboboxesDao LineListForComboboxesDao
	 */
	public void setLineListForComboboxesDao(
			final LineListForComboboxesDao lineListForComboboxesDao) {
		this.lineListForComboboxesDao = lineListForComboboxesDao;
	}

	/**
	 * loginLoginDaoを設定します。
	 * @param loginLoginDao loginLoginDao
	 */
	public void setLoginLoginDao(final LoginLoginDao loginLoginDao) {
		this.loginLoginDao = loginLoginDao;
	}

	/**
	 * organizationDetailDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * dailyReportDetailListForReportDaoを設定します。
	 * @param dailyReportDetailListForReportDao
	 *            dailyReportDetailListForReportDao
	 */
	public void setDailyReportDetailListForReportDao(
			final DailyReportDetailListForReportDao dailyReportDetailListForReportDao) {
		this.dailyReportDetailListForReportDao = dailyReportDetailListForReportDao;
	}

	/**
	 * dailyReportHeaderListForReportDaoを設定します。
	 * @param dailyReportHeaderListForReportDao
	 *            dailyReportHeaderListForReportDao
	 */
	public void setDailyReportHeaderListForReportDao(
			final DailyReportHeaderListForReportDao dailyReportHeaderListForReportDao) {
		this.dailyReportHeaderListForReportDao = dailyReportHeaderListForReportDao;
	}
}
