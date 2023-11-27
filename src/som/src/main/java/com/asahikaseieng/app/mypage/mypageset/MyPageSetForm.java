/*
 * Created on 2008/06/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.mypageset;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * マイページ Formクラス.
 * @author jbd
 */
public final class MyPageSetForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 担当者コード */
	private String tantoCd;

	/** マイページ設定フラグ（0:担当マスタ一覧より、1:マイページ設定とり）hama */
	private String myPageSetFlg;

	/**
	 * コンストラクタ.
	 */
	public MyPageSetForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 値をクリアする.
	 */
	private void clear() {
		/** 担当者コード */
		setTantoCd(null);

	}

	/**
	 * 担当者コード取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 担当者コード設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * マイページ設定フラグの取得
	 * @return myPageSetFlg
	 */
	public String getMyPageSetFlg() {
		return myPageSetFlg;
	}

	/**
	 * マイページ設定フラグの設定
	 * @param myPageSetFlg myPageSetFlg
	 */
	public void setMyPageSetFlg(final String myPageSetFlg) {
		this.myPageSetFlg = myPageSetFlg;
	}

}
