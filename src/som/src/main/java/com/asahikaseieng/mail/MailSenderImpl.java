/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.asahikaseieng.Constants;
import com.asahikaseieng.utils.AecStringUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.mail.smtp.SMTPMessage;

/**
 * メール送信クラス.
 * @author jbd
 */
public class MailSenderImpl implements MailSender {

	private static Log log = LogFactory.getLog(MailSenderImpl.class);

	private static final String DEFAULT_ENCODING = Constants.RB_SYSTEM_PROPERTIES
			.getString("encoding.mail");

	private String smtpAddress;

	private boolean isConnect;

	private Transport transport;

	private String subjectEncode;

	private String messageEncode;

	private Session session;

	private boolean isPopBeforeSmtp;

	private boolean isSmtpAuth;

	private String userId;

	private String password;

	/**
	 * コンストラクタ.
	 */
	public MailSenderImpl() {

		smtpAddress = null;
		isConnect = false;
		transport = null;
		session = null;
		userId = null;
		password = null;
		isPopBeforeSmtp = false;
		isSmtpAuth = false;

		subjectEncode = DEFAULT_ENCODING;
		messageEncode = DEFAULT_ENCODING;

		ResourceBundle bundle = Constants.RB_SYSTEM_PROPERTIES;

		// pop before smtp設定
		String popBeforeSmtp = bundle.getString("pop.before.smtp");
		if (popBeforeSmtp != null && popBeforeSmtp.equals("true")) {
			isPopBeforeSmtp = true;
		}
		// smtp認証設定
		String smtpAuth = bundle.getString("smtp.authentication");
		if (smtpAuth != null && smtpAuth.equals("true")) {
			isSmtpAuth = true;
		}
		// 認証設定がある場合、ユーザID・パスワード取得
		if (isPopBeforeSmtp() || isSmtpAuth()) {
			setUserId(bundle.getString("mail.userid"));
			setPassword(bundle.getString("mail.password"));
		}
		// リソースバンドルから接続情報を設定する
		setSubjectEncoding(Constants.RB_SYSTEM_PROPERTIES
				.getString("encoding.mail"));
		setMessageEncoding(Constants.RB_SYSTEM_PROPERTIES
				.getString("encoding.mail"));
		setSMTPServer(Constants.RB_SYSTEM_PROPERTIES.getString("smtp.address"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSubjectEncoding(final String encode) {
		if (encode == null) {
			throw new NullPointerException();
		}

		subjectEncode = encode;
	}

	/**
	 * Header Subject:用のエンコーディング指定を取得します。
	 * @return エンコーディング文字列（UTF-8,SJIS...)
	 */
	public String getSubjectEncoding() {
		return subjectEncode;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setMessageEncoding(final String encode) {
		if (encode == null) {
			throw new NullPointerException();
		}

		messageEncode = encode;
	}

	/**
	 * メッセージ本体用のエンコーディング指定を取得します。
	 * @return エンコーディング文字列（UTF-8,SJIS...)
	 */
	public String getMessageEncoding() {
		return messageEncode;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSMTPServer(final String address) {
		if (address == null) {
			throw new NullPointerException();
		}
		smtpAddress = address;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSMTPServer() {
		return smtpAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isConnect() {
		return isConnect;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean connect() {

		if (isConnect()) {
			return false;
		}

		if (getSMTPServer() == null) {
			return false;
		}

		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpAddress);

		// SMTP authを利用する
		if (isSmtpAuth()) {
			props.put("mail.smtp.auth", "true");
		}

		session = Session.getDefaultInstance(props, null);

		try {
			// pop before smtp
			if (isPopBeforeSmtp()) {
				Store store = session.getStore("pop3");
				store.connect(smtpAddress, getUserId(), getPassword());
				store.close();
			}

			transport = session.getTransport("smtp");

			// smtp認証
			if (isSmtpAuth()) {
				transport.connect(smtpAddress, getUserId(), getPassword());
			} else {
				transport.connect();
			}

		} catch (MessagingException e) {
			return false;
		}

		isConnect = true;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() {
		if (transport != null && isConnect()) {
			try {
				transport.close();
			} catch (MessagingException e) {
				log.fatal("SMTP Close Exception:" + e.getMessage());
			}

			transport = null;
			session = null;
			isConnect = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendmail(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String returnPath, final String subject, final String message) {
		return sendmailImpl(from, fromName, to, cc, bcc, returnPath, subject,
			message, null, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendmail(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String returnPath, final String subject,
			final String message, final String replyTo) {
		return sendmailImpl(from, fromName, to, cc, bcc, returnPath, subject,
			message, replyTo, null, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendmail(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String returnPath, final String subject,
			final String message, final String filePath, final String fileName) {

		return sendmailImpl(from, fromName, to, cc, bcc, returnPath, subject,
			message, null, filePath, fileName);

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendmail(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String returnPath, final String subject,
			final String message, final String replyTo, final String filePath,
			final String fileName) {

		return sendmailImpl(from, fromName, to, cc, bcc, returnPath, subject,
			message, replyTo, filePath, fileName);
	}

	/**
	 * SMTPサーバにメール送信依頼をします 完全同期の為、送信完了まで制御は戻りません。 (実装)<br>
	 * filePathの設定により、ファイルを添付して送信する。
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
	private boolean sendmailImpl(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String returnPath, final String subject,
			final String message, final String replyTo, final String filePath,
			final String fileName) {

		/* 添付ファイルPathが入ってて、ファイルが存在しない場合は何もしない */
		if (StringUtils.isNotEmpty(filePath)) {
			File fil = new File(filePath);
			if (!fil.exists()) {
				if (log.isFatalEnabled()) {
					log.fatal("Attachment File is not exist!");
					log.fatal("Failure:" + to);
				}
				return false;
			}
		}

		if (!isConnect()) {
			if (log.isFatalEnabled()) {
				log.fatal("SMTP is not Connect!");
				log.fatal("Failure:" + to);
			}
			return false;
		}

		if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to)
				|| StringUtils.isEmpty(subject) || StringUtils.isEmpty(message)) {
			throw new IllegalArgumentException("from:" + from + " to:" + to
					+ " subject:" + subject + " message:" + message);
		}

		String ret;
		if (StringUtils.isEmpty(returnPath)) {
			ret = from;
		} else {
			ret = returnPath;
		}

		String retFromName;
		if (StringUtils.isEmpty(fromName)) {
			retFromName = from;
		} else {
			retFromName = fromName;
		}

		try {
			SMTPMessage smtpMessage = new SMTPMessage(session);

			smtpMessage.setEnvelopeFrom(ret);

			ResourceBundle bundle = null;
			bundle = Constants.RB_SYSTEM_PROPERTIES;
			// Subjectと同様、From名欄の文字化けに対応する為MimeUtility.encodeTextをかましてあります。
			smtpMessage.setFrom(new InternetAddress(from, MimeUtility
					.encodeText(retFromName, getSubjectEncoding(), "B"),
					getSubjectEncoding()));
			if (replyTo == null) {
				smtpMessage.setHeader("Reply-To", from);
			} else {
				smtpMessage.setHeader("Reply-To", replyTo);
			}

			smtpMessage.setRecipients(Message.RecipientType.TO, to);

			if (cc != null && 0 < cc.length) {
				for (int i = 0; i < cc.length; i++) {
					if (cc[i] != null) {
						smtpMessage.addRecipient(Message.RecipientType.CC,
							new InternetAddress(cc[i]));
					}
				}
			}

			if (bcc != null && 0 < bcc.length) {
				for (int i = 0; i < bcc.length; i++) {
					if (bcc[i] != null) {
						smtpMessage.addRecipient(Message.RecipientType.BCC,
							new InternetAddress(bcc[i]));
					}
				}
			}

			// 機種依存文字を正しい文字に変換する。
			String replacedSubject = AecStringUtils
					.replaceInvalidCharacters(subject);

			// Windows-31JとShift_JISのマッピングの違いを吸収。
			// Subjectは先にエンコードしてからMessageに渡す。(PostPetによる文字化け対応(MimeUtility.encodeText使用))
			smtpMessage.setSubject(MimeUtility.encodeText(replacedSubject,
				getSubjectEncoding(), "B"));

			// 機種依存文字を正しい文字に変換する。(文字化けするので、最後に改行二つつける)
			String replacedMessage = AecStringUtils
					.replaceInvalidCharacters(message + "\r\n\r\n");

			// Windows-31JとShift_JISのマッピングの違いを吸収。
			// String encMessage = new String(replacedMessage.getBytes(bundle
			// .getString("encoding")), "SJIS");
			String encMessage = replacedMessage;

			/* 添付ファイルがない場合 */
			if (StringUtils.isEmpty(filePath)) {
				/* 本文を設定 */
				smtpMessage.setText(encMessage, getMessageEncoding());
			} else {
				/* 添付ファイルがある場合 */
				/** １つ目のボディパートを作成 * */
				MimeBodyPart mbp1 = new MimeBodyPart();
				// メールの内容を指定
				mbp1.setText(encMessage, getMessageEncoding());

				// 複数のボディを格納するマルチパートオブジェクトを生成
				Multipart mp = new MimeMultipart();
				// １つ目のボディパートを追加
				mp.addBodyPart(mbp1);

				/** ２つ目のボディパートを作成 * */
				MimeBodyPart mbp2 = new MimeBodyPart();
				// 添付するファイル名を指定
				FileDataSource fds = new FileDataSource(filePath);
				mbp2.setDataHandler(new DataHandler(fds));
				mbp2.setFileName(MimeUtility.encodeWord(new String(fileName
						.getBytes(bundle.getString("encoding")), "SJIS"),
					bundle.getString("encoding.mail"), "B"));

				// ２つ目のボディパートを追加
				mp.addBodyPart(mbp2);

				/* 本文を設定 */
				smtpMessage.setContent(mp);
			}

			smtpMessage.setSentDate(new Date());

			transport.sendMessage(smtpMessage, smtpMessage.getAllRecipients());

			if (log.isInfoEnabled()) {
				log.info("Success:" + to);
			}

		} catch (Exception e) {
			/* 例外内容を出力 */
			if (log.isFatalEnabled()) {
				log.fatal("Failure:" + to);
				log.fatal(e.getMessage());
			}
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isPopBeforeSmtp() {
		return isPopBeforeSmtp;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isSmtpAuth() {
		return isSmtpAuth;
	}

	/**
	 * メール送信時のパスワードを取得する
	 * @return passwored
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * メール送信時のユーザIDを取得する
	 * @return userid
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPassword(final String value) {
		password = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setUserId(final String value) {
		userId = value;
	}

}
