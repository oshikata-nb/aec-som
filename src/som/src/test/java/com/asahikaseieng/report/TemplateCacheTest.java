/*
 * Created on 2007/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import junit.framework.TestCase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 * TemplateCacheのテストケース.
 * @author jbd
 */
public class TemplateCacheTest extends TestCase {

	/**
	 * コンストラクタ.
	 * @param name テスト名
	 */
	public TemplateCacheTest(final String name) {
		super(name);
	}

	/**
	 * getInstanceのテストケース.
	 */
	public void testGetInstance() {
		assertNotNull(TemplateCache.getInstance());
	}

	/**
	 * getTemplateのテストケース.
	 * @throws JRException JRException
	 */
	public void testGetTemplate() throws JRException {
		TemplateCache.getInstance().clear();

		// キャッシュクリア
		TemplateCache.getInstance().clear();

		// 本体のjasperファイル
		JasperReport jr = TemplateCache.getInstance().getTemplate(
			"template.sample.report");
		assertNotNull(jr);

		// キャッシュ有り
		jr = TemplateCache.getInstance().getTemplate("template.sample.report");
		assertNotNull(jr);
	}

	/**
	 * getTemplateテスト,
	 */
	public void testGetTemplateError() {

		/* キャッシュクリア */
		TemplateCache.getInstance().clear();

		/* テンプレートファイル読み込み：≠JasperReport */
		try {
			TemplateCache.getInstance().getTemplate(
				"template-cache-test.jrxml");
			fail("Should raise a " + JRException.class);
		} catch (JRException e) {
			;
		}
	}
}
