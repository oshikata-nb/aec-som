/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.Constants;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ファイルダウンロードサーブレット.
 * @author jbd
 */
public class FileDownloadServlet extends HttpServlet {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public FileDownloadServlet() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		FileDownloadInfo info = (FileDownloadInfo) request.getSession(false)
				.getAttribute(Constants.DOWNLOAD_FILE_KEY);

		if (info != null) {
			// ssl環境ではIEからno-cacheにしているとダウンロードできない
			// ダミー値をいれる(2003.3.14 kj)
			response.setHeader("Cache-Control", "azuma-mikihisa");
			response.setHeader("Pragma", "azuma-mikihisa");

			if (StringUtils.isNotEmpty(info.getDisposition())) {
				// IEではファイルダウンロード時に日本語を取り扱う際、
				// 必ずShift-JISとして解釈しようとする仕様の為(非標準)
				// 画面上はUTF-8だが下記の処理にする 2007/04/18
				response.setHeader("Content-disposition", info.getDisposition()
						+ "; filename=\""
						+ new String(
								info.getFileName().getBytes("Windows-31J"),
								"ISO8859_1") + "\"");
			}

			response.setContentType(info.getContentType());

			BufferedOutputStream out = new BufferedOutputStream(response
					.getOutputStream());
			if (StringUtils.isNotEmpty(info.getPath())) {
				try {
					// InputStreamの場合
					File file = new File(info.getPath());
					BufferedInputStream in = new BufferedInputStream(
							new FileInputStream(file));

					try {
						IOUtils.copy(in, out);
					} finally {
						out.flush();
						in.close();
						if (info.isDeleteOnExist()) {
							if (file != null && file.exists()) {
								file.delete();
							}
						}
					}
				} finally {
					out.flush();
				}
			} else {
				// バイナリの場合
				out.write(info.getBytes(), 0, info.getBytes().length);
				out.flush();
			}

			// ファイル情報削除
			request.getSession(false).removeAttribute(
				Constants.DOWNLOAD_FILE_KEY);
		}
	}
}
