/*
 * Created on 2007/12/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.io.Serializable;

/**
 * DevelopMutiboxクラス
 * @author FPC
 */
public class DevelopMultibox implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopMultibox() {
	}

	/* 用途 */
	private String app;

	/* 用途Label */
	private String appName;

	/**
	 * appを取得します。
	 * @return app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * appを設定します。
	 * @param app app
	 */
	public void setApp(final String app) {
		this.app = app;
	}

	/**
	 * appNameを取得します。
	 * @return appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * appNameを設定します。
	 * @param appName appName
	 */
	public void setAppName(final String appName) {
		this.appName = appName;
	}
}
