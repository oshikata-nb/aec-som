/*
 * Created on 2018/02/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common;

import java.math.BigDecimal;

/**
 * メール送信機能定数クラス
 *
 * @author ssv
 */
public final class SendMailConstants {
	private SendMailConstants() {
	}

	/** SMTP接続用ポート番号 初期値:25 */
	public static final String DEFAULT_SMTP_PORT = "25";
	// public static final String DEFAULT_SMTP_PORT = "99"; // for error

	/** 接続時タイムアウト 初期値:10000(ミリ秒) */
	public static final String DEFAULT_TIMEOUT = "10000";
	// public static final String DEFAULT_TIMEOUT = "1"; // for error

	// ----------------------------------------------------
	// メール送信ステータス
	// ----------------------------------------------------

	// 0:正常終了
	public static final java.math.BigDecimal MAILSEND_STS_NOMALEND = new BigDecimal(
			0);

	// 1:異常終了
	public static final java.math.BigDecimal MAILSEND_STS_ABNOMALEND = new BigDecimal(
			1);

	// ----------------------------------------------------
	// メールフォーマットID
	//
	// ID採番基準は下記の通り
	//
	// 3桁(機能毎の連番) ＋ 2桁(処理区分)
	//
	// 機能毎の連番：101から開始する任意の連番
	// 処理区分 ：01=承認依頼、02=否認
	// ----------------------------------------------------
	
	public static final String ORDER_MAIL = "O_10001";
	

	// ----------------------------------------------------
	// メッセージID
	// ----------------------------------------------------

	/** ユーザー情報取得エラー */
	public static final String MSGID_GET_USRINFO_ERR = "errors.mail.user.info";

	/** 部署情報取得エラー */
	public static final String MSGID_GET_ORGINFO_ERR = "errors.mail.organization.info";
	
	/** メールテンプレート取得エラー */
	public static final String MSGID_GET_TEMPLATE_ERR = "errors.mail.template";

	/** メールパラメーター取得エラー */
	public static final String MSGID_GET_PARAMETER_ERR = "errors.mail.parameter";

	/** メール送信エラー */
	public static final String MSGID_SEND_ERR = "errors.mail.send";

	// ----------------------------------------------------
	// エラー出力許容文字数
	// ----------------------------------------------------

	public static int STAC_TRACE_MAX_LENGTH = 2000;

	// ----------------------------------------------------
	// メール送信関連
	// ----------------------------------------------------

	/** メールモード：メール送信のみ */
	public static final BigDecimal SEND_MODE_MAIL_ONLY = new BigDecimal("0");

	/** メールモード：自動FAX */
	public static final BigDecimal SEND_MODE_AUTO_FAX = new BigDecimal("1");
	
	/** 宛先取得モード:受注 */
	public static final BigDecimal GET_ORDER_INFO = new BigDecimal("0");
	
	/** 宛先取得モード:売上 */
	public static final BigDecimal GET_SALES_INFO = new BigDecimal("1");

	/** 未送信リストフォルダ名 */
	public static final String SEND_QUEUE_TEXT_DIRECTORY = "\\sendQueue";

	/** 未送信リストファイル名 */
	public static final String SEND_QUEUE_TEXT_NAME = "sendList";

	/** メール送信全体制御：送信する */
	public static final BigDecimal MAIL_SEND_FLG_ON = new BigDecimal("1");
}
