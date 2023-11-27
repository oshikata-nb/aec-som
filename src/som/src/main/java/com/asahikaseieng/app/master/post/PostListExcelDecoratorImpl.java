/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.役職マスタ一覧
 * @author t0011036
 */
public class PostListExcelDecoratorImpl implements PostListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private PostListLogic postListLogic;

	/**
	 * コンストラクタ
	 */
	public PostListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PostListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("postlist");

		/* 明細をセット */
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetail(final PostListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<PostListForReport> list = postListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("postlist");

		/* リスト部 */
		for (PostListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPostId());
			builder.setExcelDataString(rowNum, colNum++, bean.getPostName());

			rowNum++;
		}
	}

	/**
	 * postListLogicを設定します。
	 * @param postListLogic postListLogic
	 */
	public void setPostListLogic(final PostListLogic postListLogic) {
		this.postListLogic = postListLogic;
	}
}
