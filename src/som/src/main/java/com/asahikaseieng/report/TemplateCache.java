/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import java.io.File;
import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Templateをキャッシュする.
 * @author jbd
 */
public final class TemplateCache {

	private static TemplateCache instance;

	/**
	 * Instance.
	 * @return instance
	 */
	public static synchronized TemplateCache getInstance() {
		if (instance == null) {
			instance = new TemplateCache();
		}

		return instance;
	}

	/**
	 * ファイル情報内部クラス.
	 */
	private class FileInfoBase {

		private long lastModified;

		/**
		 * コンストラクタ
		 * 
		 */
		public FileInfoBase() {
			super();
		}

		public final long getLastModified() {
			return lastModified;
		}

		public final void setLastModified(final long last) {
			lastModified = last;
		}
	}

	/**
	 * Template情報内部クラス.
	 */
	private class TemplateInfo extends FileInfoBase {

		private JasperReport template;

		/**
		 * コンストラクタ
		 * 
		 */
		public TemplateInfo() {
			super();
		}

		public final JasperReport getTemplate() {
			return template;
		}

		public final void setTemplate(final JasperReport jr) {
			template = jr;
		}
	}

	/* キャッシュテーブル */
	private Hashtable table = new Hashtable();

	/**
	 * コンストラクタ.
	 */
	private TemplateCache() {
		super();
	}

	/**
	 * Cacheクリア
	 */
	public void clear() {
		synchronized (table) {
			table.clear();
		}
	}

	/**
	 * JasperReport情報を取得する.
	 * @param path ファイルパス
	 * @return POIFSFileSystem情報
	 * @throws JRException I/O例外
	 */
	private TemplateInfo getTemplateInfo(final File file) throws JRException {

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);

		TemplateInfo ti = new TemplateInfo();
		ti.setLastModified(file.lastModified());
		ti.setTemplate(jasperReport);

		return ti;
	}

	/**
	 * JasperReportを取得する.
	 * @param key String
	 * @return JasperReport
	 * @throws JRException JRException
	 */
	@SuppressWarnings("unchecked")
	public JasperReport getTemplate(final String key) throws JRException {
		TemplateInfo ti = null;

		ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;
		try {
			File file = new File(this.getClass().getResource(
				rb.getString("excel.template.path") + rb.getString(key))
					.getPath());

			// キャッシュ検索
			synchronized (table) {
				ti = (TemplateInfo) table.get(file.getName());
			}

			// キャッシュにない、もしくは新しく更新されている場合、
			// ファイルを取得しなおす
			if (ti == null
					|| (ti != null && file.lastModified() > ti
							.getLastModified())) {

				ti = getTemplateInfo(file);

				synchronized (table) {
					table.put(file.getName(), ti);
				}
			}

		} catch (MissingResourceException e) {
			throw new JRException(e);
		}
		return ti.getTemplate();
	}
}
