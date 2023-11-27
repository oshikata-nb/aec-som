/*
 * Created on 2007/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.servlet;

import junit.framework.TestCase;

/**
 * FileDownloadInfoのテストケース.
 * @author jbd
 */
public class FileDownloadInfoTest extends TestCase {

	/**
	 * コンストラクタ.
	 * @param name name
	 */
	public FileDownloadInfoTest(final String name) {
		super(name);
	}

	/**
	 * createPDFDownloadInfoのテスト.
	 */
	public void testCreatePDFDownloadInfo() {
		FileDownloadInfo fdi = FileDownloadInfo.createPDFDownloadInfo("a",
			"report.shippinglist.filename");
		assertEquals("a", fdi.getPath());
		assertEquals("出荷実績データ一覧.pdf", fdi.getFileName());
		assertEquals("application/pdf", fdi.getContentType());
		assertEquals("inline", fdi.getDisposition());
	}

	/**
	 * fileDownloadInfoStringByteArrayのテスト.
	 */
	public void testFileDownloadInfoStringByteArray() {
		FileDownloadInfo fdi = new FileDownloadInfo("abc", new byte[] {});
		assertEquals("abc", fdi.getFileName());
		assertEquals(0, fdi.getBytes().length);
		assertTrue(fdi.isDeleteOnExist());
		fdi.setDeleteOnExist(false);
		assertFalse(fdi.isDeleteOnExist());
	}
}
