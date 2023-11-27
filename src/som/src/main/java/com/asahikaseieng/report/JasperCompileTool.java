/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import java.io.File;
import java.io.FilenameFilter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JasperCompileTool.
 * @author jbd
 */
public final class JasperCompileTool {

	private static final String path = "src/main/resources/template";

	/**
	 * コンストラクタ.
	 */
	private JasperCompileTool() {
		super();
	}

	/**
	 * main.
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		Log log = LogFactory.getLog(JasperCompileTool.class);

		File dir = new File(path);
		String[] files = dir.list(new FilenameFilter() {
			public boolean accept(final File dir, final String name) {
				if (new File(dir, name).isFile() && (name.endsWith("xml") || name.endsWith("jrxml"))) {
					return true;
				}
				return false;
			}
		});
		try {
			for (int i = 0; i < files.length; i++) {
				JasperCompileManager.compileReportToFile(path
						+ SystemUtils.FILE_SEPARATOR + files[i]);
				log.debug(files[i] + " compile complete");
			}
		} catch (JRException e) {
			log.debug("" + e.getMessage());
		}
	}
}
