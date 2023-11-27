/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.inoutmonthlyreportstatus.InoutMonthlyReportStatus;
import com.asahikaseieng.dao.entity.inoutmonthlyreportstatus.InoutMonthlyReportStatusDao;
import com.asahikaseieng.dao.entity.inoutmonthlyreporttri.InoutMonthlyReportTri;
import com.asahikaseieng.dao.entity.inoutmonthlyreporttri.InoutMonthlyReportTriDao;
import com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepTempInoutMonthlyReport;
import com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepTempInoutMonthlyReportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 受払月報ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class InoutMonthlyReportExcelDecoratorImpl implements
		InoutMonthlyReportExcelDecorator {

	private InoutMonthlyReportStatusDao inoutMonthlyReportStatusDao;

	private InoutMonthlyReportTriDao inoutMonthlyReportTriDao;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RepTempInoutMonthlyReportDao repTempInoutMonthlyReportDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public InoutMonthlyReportExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public void createTemp(final String dateFrom, final String dateTo,
			final String tantoCd, final String tantoName,
			final Timestamp currentDate) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		// 受払月報トリガーテーブル作成
		setTriggerTable(dateFrom, dateTo, tantoCd);

		return;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setStatus(final String dateFrom, final String dateTo,
			final String tantoCd, final String status) {

		// 受払月報ステータステーブル作成
		setStatusTable(dateFrom, dateTo, tantoCd, status);

		return;
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String dateFrom,
			final String dateTo, final String tantoCd, final String tantoName,
			final Timestamp currentDate) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("inout_monthly_report");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 受払月報TEMP取得用ヴューを検索
		List<RepTempInoutMonthlyReport> detailTempList = repTempInoutMonthlyReportDao
				.getListForReport(tantoCd);

		/* 明細をセット */
		setDetail(detailTempList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * Tempテーブルから取得し詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RepTempInoutMonthlyReport> detailTempList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepTempInoutMonthlyReport bean : detailTempList) {
			sCol = TEMP_START_COL;

			// 開始月、終了月
			if (sCol == TEMP_START_COL) {
				builder.setExcelDataString((short) 0, (short) 1, bean
						.getDateFrom());
				builder.setExcelDataString((short) 0, (short) 2, bean
						.getDateTo());
			}

			// ロケーションコード
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			// ロケーション名
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			// 財務分類
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getFinancialClassCd());
			// 品目コード
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			// 品目名称
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// Kg換算係数(在庫）
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getKgOfFractionManagement());
			// 前月末残完成在庫
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLm1());
			// 前月末残検査待ち
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLm2());
			// 前月末残返品
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLm3());
			// 前月末残検品在庫
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLm4());
			// 受入生産
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getIn1());
			// 受入仕入
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getIn2());
			// 受入返品
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getIn3());
			// 受入移庫振替
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getIn4());
			// 入庫合計
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInAll());
			// 払出出荷
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut1());

			// 払出売付
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut7());

			// 払出返品
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut2());
			// 払出工場戻し
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut3());
			// 払出営業見本
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut4());
			// 払出移庫振替
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut5());
			// 払出その他
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOut6());
			// 出庫合計
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOutAll());
			// 在庫高完成在庫
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNm1());
			// 在庫高検査待ち
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNm2());
			// 在庫高返品
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNm3());
			// 在庫高検品在庫
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNm4());

			sRow++;
		}
	}

	/**
	 * トリガーテーブルに詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setTriggerTable(final String dateFrom, final String dateTo,
			final String tantoCd) {

		InoutMonthlyReportTri newBean = new InoutMonthlyReportTri();

		InoutMonthlyReportTri bean = inoutMonthlyReportTriDao
				.getEntity(tantoCd);

		// 担当者CD
		newBean.setTantoCd(tantoCd);
		// 開始月
		newBean.setDateFrom(dateFrom);
		// 終了月
		newBean.setDateTo(dateTo);
		// 登録者
		newBean.setInputorCd(tantoCd);
		// 登録日時
		newBean.setInputDate(AecDateUtils.getCurrentTimestamp());
		// 更新者
		newBean.setUpdatorCd(tantoCd);
		// 更新日時
		if (bean == null) {
			newBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
		} else {
			newBean.setUpdateDate(bean.getUpdateDate());
		}

		if (bean == null) {
			// トリガーテーブルに挿入
			insertInoutMonthlyReportTrigger(newBean);
		} else {
			// トリガーテーブルを更新
			updateInoutMonthlyReportTrigger(newBean);
		}

		bean = inoutMonthlyReportTriDao.getEntity(tantoCd);
		// 更新日時
		newBean.setUpdateDate(bean.getUpdateDate());

		// 作成したトリガーテーブルを削除する
		deleteInoutMonthlyReportTrigger(newBean);
	}

	/**
	 * 受払月報トリガーテーブルに挿入する。
	 * @param bean 受払月報トリガーデータ
	 */
	private void insertInoutMonthlyReportTrigger(
			final InoutMonthlyReportTri bean) {
		try {
			// 受払月報トリガーテーブルに挿入
			int updateNum = inoutMonthlyReportTriDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受払月報トリガーテーブルを更新する。
	 * @param bean 受払月報トリガーデータ
	 */
	private void updateInoutMonthlyReportTrigger(
			final InoutMonthlyReportTri bean) {
		try {
			// 受払月報トリガーテーブルに挿入
			int updateNum = inoutMonthlyReportTriDao.update(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受払月報トリガーテーブルを削除する。
	 * @param bean 受払月報トリガーデータ
	 */
	private void deleteInoutMonthlyReportTrigger(
			final InoutMonthlyReportTri bean) {
		try {
			// 受払月報トリガーテーブルを削除
			int updateNum = inoutMonthlyReportTriDao.delete(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受払月報ステータステーブルに詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setStatusTable(final String dateFrom, final String dateTo,
			final String tantoCd, final String status) {

		InoutMonthlyReportStatus newBean = new InoutMonthlyReportStatus();

		InoutMonthlyReportStatus bean = inoutMonthlyReportStatusDao
				.getEntity(tantoCd);

		// 担当者CD
		newBean.setTantoCd(tantoCd);
		// ステータス（1:開始,2:完了）
		if (status.equals("1")) {
			newBean.setStatus(new BigDecimal("1"));
		} else {
			newBean.setStatus(new BigDecimal("2"));
		}
		// 登録者
		newBean.setInputorCd(tantoCd);
		// 登録日時
		if (bean == null) {
			newBean.setInputDate(AecDateUtils.getCurrentTimestamp());
		} else {
			if (status.equals("1")) {
				newBean.setInputDate(AecDateUtils.getCurrentTimestamp());
			} else {
				newBean.setInputDate(bean.getInputDate());
			}
		}

		// 更新者
		newBean.setUpdatorCd(tantoCd);
		// 登録日時
		if (bean == null) {
			newBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
		} else {
			newBean.setUpdateDate(bean.getUpdateDate());
		}

		if (bean == null) {
			// 挿入
			insertInoutMonthlyReportStatus(newBean);
		} else {
			// 更新
			updateInoutMonthlyReportStatus(newBean);
		}

	}

	/**
	 * 受払月報ステータスに挿入する。
	 * @param bean 受払月報ステータスデータ
	 */
	private void insertInoutMonthlyReportStatus(
			final InoutMonthlyReportStatus bean) {
		try {
			// 受払月報TEMPに挿入
			int updateNum = inoutMonthlyReportStatusDao.insert(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受払月報ステータスを更新する。
	 * @param bean 受払月報ステータスデータ
	 */
	private void updateInoutMonthlyReportStatus(
			final InoutMonthlyReportStatus bean) {
		try {
			// 受払月報TEMPに挿入
			int updateNum = inoutMonthlyReportStatusDao.update(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 受払月報TEMP検索用Dao
	 * @param repTempInoutMonthlyReportDao 受払月報TEMP検索用Dao
	 */
	public void setRepTempInoutMonthlyReportDao(
			final RepTempInoutMonthlyReportDao repTempInoutMonthlyReportDao) {
		this.repTempInoutMonthlyReportDao = repTempInoutMonthlyReportDao;
	}

	/**
	 * inoutMonthlyReportStatusDaoを設定します。
	 * @param inoutMonthlyReportStatusDao inoutMonthlyReportStatusDao
	 */
	public void setInoutMonthlyReportStatusDao(
			final InoutMonthlyReportStatusDao inoutMonthlyReportStatusDao) {
		this.inoutMonthlyReportStatusDao = inoutMonthlyReportStatusDao;
	}

	/**
	 * inoutMonthlyReportTriDaoを設定します。
	 * @param inoutMonthlyReportTriDao inoutMonthlyReportTriDao
	 */
	public void setInoutMonthlyReportTriDao(
			final InoutMonthlyReportTriDao inoutMonthlyReportTriDao) {
		this.inoutMonthlyReportTriDao = inoutMonthlyReportTriDao;
	}

}
