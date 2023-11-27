/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.mail;

/**
 * メール送信インタフェース.
 * @author jbd
 */
public interface MailSender {
	/**
	 * SMTPサーバとの接続状態を取得します.
	 * @return true 接続状態 false 非接続状態
	 */
	boolean isConnect();

	/**
	 * SMTPサーバと接続します.
	 * @return true 接続に成功 false 接続に失敗
	 */
	boolean connect();

	/**
	 * SMTPサーバとの接続状態を切断します.
	 */
	void close();

	/**
	 * SMTPサーバにメール送信依頼をします 完全同期の為、送信完了まで制御は戻りません。
	 * (返信先アドレス指定は不可。返信先アドレスにはメール送信者アドレスがセットされます。).
	 * @param from メール送信者アドレス
	 * @param fromName アドレス表示名
	 * @param to メール受信者アドレス
	 * @param cc メールCC受信者アドレス配列
	 * @param bcc メールBCC受信者アドレス配列
	 * @param returnPath メール不達時エラーメール送信先アドレス
	 * @param subject メール件名
	 * @param message メール本文
	 * @return true メール送信に成功 false メール送信に失敗
	 */
	boolean sendmail(final String from, final String fromName, final String to,
			final String[] cc, final String[] bcc, final String returnPath,
			final String subject, final String message);

	/**
	 * SMTPサーバにメール送信依頼をします 完全同期の為、送信完了まで制御は戻りません。 (返信先アドレス指定は可能。).
	 * @param from メール送信者アドレス
	 * @param fromName アドレス表示名
	 * @param to メール受信者アドレス
	 * @param cc メールCC受信者アドレス配列
	 * @param bcc メールBCC受信者アドレス配列
	 * @param returnPath メール不達時エラーメール送信先アドレス
	 * @param subject メール件名
	 * @param message メール本文
	 * @param replyTo 返信先アドレス
	 * @return true メール送信に成功 false メール送信に失敗
	 */
	boolean sendmail(final String from, final String fromName, final String to,
			final String[] cc, final String[] bcc, final String returnPath,
			final String subject, final String message, final String replyTo);

	/**
	 * SMTPサーバにメール送信依頼をします 完全同期の為、送信完了まで制御は戻りません。<br>
	 * 添付ファイルあり。<br>
	 * (返信先アドレス指定は不可。返信先アドレスにはメール送信者アドレスがセットされます。).
	 * @param from メール送信者アドレス
	 * @param fromName アドレス表示名
	 * @param to メール受信者アドレス
	 * @param cc メールCC受信者アドレス配列
	 * @param bcc メールBCC受信者アドレス配列
	 * @param returnPath メール不達時エラーメール送信先アドレス
	 * @param subject メール件名
	 * @param message メール本文
	 * @param filePath 添付ファイルの格納Path
	 * @param fileName 添付ファイル名
	 * @return true メール送信に成功 false メール送信に失敗
	 */
	boolean sendmail(final String from, final String fromName, final String to,
			final String[] cc, final String[] bcc, final String returnPath,
			final String subject, final String message, final String filePath,
			final String fileName);

	/**
	 * SMTPサーバにメール送信依頼をします 完全同期の為、送信完了まで制御は戻りません。<br>
	 * 添付ファイルあり。<br>
	 * (返信先アドレス指定は可能。).
	 * @param from メール送信者アドレス
	 * @param fromName アドレス表示名
	 * @param to メール受信者アドレス
	 * @param cc メールCC受信者アドレス配列
	 * @param bcc メールBCC受信者アドレス配列
	 * @param returnPath メール不達時エラーメール送信先アドレス
	 * @param subject メール件名
	 * @param message メール本文
	 * @param replyTo 返信先アドレス
	 * @param filePath 添付ファイルの格納Path
	 * @param fileName 添付ファイル名
	 * @return true メール送信に成功 false メール送信に失敗
	 */
	boolean sendmail(final String from, final String fromName, final String to,
			final String[] cc, final String[] bcc, final String returnPath,
			final String subject, final String message, final String replyTo,
			final String filePath, final String fileName);

	/**
	 * pop before smtpの設定状態を取得する.
	 * @return true:設定されている false:設定されていない
	 */
	boolean isPopBeforeSmtp();

	/**
	 * smtp認証の設定状態を取得する.
	 * @return true:設定されている false:設定されていない
	 */
	boolean isSmtpAuth();

	/**
	 * Header Subject:用のエンコーディング指定をします。
	 * @param encode エンコーディング文字列（UTF-8,SJIS...)
	 */
	void setSubjectEncoding(final String encode);

	/**
	 * メッセージ本体用のエンコーディング指定をします。
	 * @param encode エンコーディング文字列（UTF-8,SJIS...)
	 */
	void setMessageEncoding(final String encode);

	/**
	 * メール送信に利用するSMTPサーバのアドレスを設定します。
	 * @param address SMTPサーバアドレス
	 */
	void setSMTPServer(final String address);

	/**
	 * メール送信に利用するSMTPサーバのアドレスを取得する.
	 * @return SMTPサーバのアドレス
	 */
	String getSMTPServer();

	/**
	 * メール送信時のパスワードを設定する.
	 * @param value password
	 */
	void setPassword(final String value);

	/**
	 * メール送信時のユーザIDを設定する.
	 * @param value userId
	 */
	void setUserId(final String value);

}
