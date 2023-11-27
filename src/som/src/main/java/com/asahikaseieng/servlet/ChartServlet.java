/*	
 * Created on 2007/09/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.filenamefilter.ListupFiles;
import com.asahikaseieng.utils.AecDateUtils;

import org.apache.struts.action.ActionForm;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * グラフクラス
 */
public final class ChartServlet extends HttpServlet {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final int MAX_VALUE = 10;

	private static final int WIDTH = 316;

	private static final int HEIGHT = 264;

	// ローカル用パス
	// private static final String IMAGE_PATH = "../webapps/ap21/images/";
	private static final String IMAGE_PATH = "../Tomcat/webapps/ap21/images/";

	// // デバッグ用パス
	// private static final String IMAGE_PATH =
	// "F:/ProgramFiles/eclipse320/workspace/ap21/src/main/webapp/images/";

	// jsp用パス
	private static final String IMAGE_JSP_PATH = "/images/";

	// イメージファイル名
	private static final String IMAGE_NAME = "chartmap";

	// イメージファイル拡張子
	private static final String IMAGE_NAME_EXT = ".jpg";

	/**
	 * コンストラクタ.
	 */
	public ChartServlet() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public static String executeSpider(final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();

		SpiderWebPlot sp = new SpiderWebPlot(ds);
		sp.setMaxValue(MAX_VALUE);

		JFreeChart chart = new JFreeChart("香質マップ",
				JFreeChart.DEFAULT_TITLE_FONT, sp, false);

		ds = null;
		sp = null;

		// ディレクトリが存在するか調べてなければ作成する
		File dir = new File(IMAGE_PATH);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				dir = null;
				return IMAGE_JSP_PATH;
			}
		}

		// チャート画像を削除しておく
		String[] files = ListupFiles.getFileList(dir.toString());

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File fl = new File(dir + "\\" + files[i]);

				if (fl.exists()) {
					fl.delete();
				}
				fl = null;
			}
		}
		dir = null;

		String timestamp = AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyyMMddHHmmss");

		String jpegFileName = IMAGE_NAME + timestamp + IMAGE_NAME_EXT;
		File outputFile = new File(IMAGE_PATH + jpegFileName);

		try {
			// チャートをJPEGファイルへ保存する
			ChartUtilities.saveChartAsJPEG(outputFile, chart, WIDTH, HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		chart = null;
		outputFile = null;

		return IMAGE_JSP_PATH + jpegFileName;
	}
}
