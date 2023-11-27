/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.picking;

import java.sql.Timestamp;

import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 出荷実績一覧ＥＸＣＥＬダウンロード用ファイル作成
 * @author a1020630
 */
public class PickingExcelDecoratorImpl implements PickingExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public PickingExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String tantoNm,
			final Timestamp currentDate) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("picking");

		/* シート名を指定 */
		builder.setSheet("Sheet1");

		/* ヘッダを情報をセット */
		setHeader(tantoNm, currentDate);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダをセット
	 * @param tantoNm 担当者名
	 */
	private void setHeader(final String tantoNm, final Timestamp currentDate) {
	}

	/* -------------------- setter -------------------- */

}
