/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.mail;

import java.util.LinkedList;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 緊急メール用クラス. スプールした後、一定時間毎に送信する.
 * @author jbd
 */
public final class MailSpooler implements Runnable {

	/**
	 * メールBean.
	 */
	private static class Message {

		private String from;

		private String fromName;

		private String to;

		private String subject;

		private String message;

		private String[] cc;

		private String[] bcc;

		public String getFrom() {
			return from;
		}

		public String getFromName() {
			return fromName;
		}

		public String getTo() {
			return to;
		}

		public String getSubject() {
			return subject;
		}

		public String getMessage() {
			return message;
		}

		public String[] getCc() {
			return cc;
		}

		public String[] getBcc() {
			return bcc;
		}

		/**
		 * コンストラクタ 一通のメールを表現します。
		 * @param from fromアドレス (ex kajihara@x1.jbd.co.jp)
		 * @param from fromネーム (ex 梶原)
		 * @param to toアドレス (ex kajihara@x1.jbd.co.jp)
		 * @param cc ccアドレス (ex kajihara@x1.jbd.co.jpの配列)
		 * @param bcc bccアドレス (ex kajihara@x1.jbd.co.jpの配列)
		 * @param subject 件名 (ex foo)
		 * @param message メッセージ (ex bar)
		 */
		public Message(final String from, final String fromName,
				final String to, final String[] cc, final String[] bcc,
				final String subject, final String message) {
			this.from = from;
			this.fromName = fromName;
			this.to = to;
			this.cc = cc;
			this.bcc = bcc;
			this.subject = subject;
			this.message = message;
		}

	}

	/** デフォルトのキューの最大長 */
	public static final int DEFAULT_MAX_QUEUE = 128;

	/** デフォルトのウェイト */
	public static final int DEFAULT_WAIT = 5;

	/** デフォルトのインターバル */
	public static final int DEFAULT_INTERVAL = 3000;

	private static MailSpooler inst;

	/**
	 * Method getInstance. 唯一のMailSpoolerインスタンスを取得します。
	 * @return MailSpooler 唯一のMailSpoolerインスタンス
	 */
	public static synchronized MailSpooler getInstance() {
		if (inst == null) {
			inst = new MailSpooler();
		}

		return inst;
	}

	private ResourceBundle bundle;

	private Log log;

	private MailSender mail;

	private boolean send;

	private boolean stopped;

	private int interval;

	private int wait;

	private int maxQueue;

	private LinkedList<Message> queue;

	/**
	 * Constructor for MailSpooler. 初期化します.
	 */
	private MailSpooler() {
		super();
		init();
	}

	/**
	 * Method init. SMTPサーバの設定等、諸情報を設定します。
	 */
	public synchronized void init() {

		bundle = Constants.RB_SYSTEM_PROPERTIES;
		log = LogFactory.getLog(this.getClass());
		mail = new MailSenderImpl();
		queue = new LinkedList<Message>();

		setInfo();
	}

	private void setInfo() {

		mail.setSubjectEncoding(bundle.getString("encoding.mail"));
		mail.setMessageEncoding(bundle.getString("encoding.mail"));
		mail.setSMTPServer(bundle.getString("smtp.address"));

		try {
			setInterval(Integer.parseInt(bundle
					.getString("mail.spooler.interval")));
		} catch (NumberFormatException e) {
			setInterval(DEFAULT_INTERVAL);
		}

		try {
			setWait(Integer.parseInt(bundle.getString("mail.spooler.wait")));
		} catch (NumberFormatException e) {
			setWait(DEFAULT_WAIT);
		}

		try {
			setMaxQueueLength(Integer.parseInt(bundle
					.getString("mail.spooler.maxqueue")));
		} catch (NumberFormatException e) {
			setMaxQueueLength(DEFAULT_MAX_QUEUE);
		}

		String isSend = bundle.getString("mail.spooler.send");
		if (isSend != null && isSend.equalsIgnoreCase("false")) {
			setSend(false);
		} else {
			setSend(true);
		}

//		if (log.isInfoEnabled()) {
//			log.info("MailSpooler interval=" + getInterval());
//			log.info("MailSpooler wait=" + getWait());
//			log.info("MailSpooler max queue=" + getMaxQueueLength());
//			log.info("MailSpooler send flag=" + isSend());
//		}
	}

	/**
	 * Method setSend. 実際にメール送信を行うかどうかのフラグを設定します.
	 * @param value 実際にメール送信を行うかどうかのフラグ(true=送信する)
	 */
	public synchronized void setSend(final boolean value) {
		this.send = value;
	}

	/**
	 * Method isSend. 実際にメール送信を行うかどうかのフラグを取得します.
	 * @return boolean 実際にメール送信を行うかどうかのフラグ(true=送信する)
	 */
	public synchronized boolean isSend() {
		return send;
	}

	/**
	 * Method setInterval. 次のメール送信までの待ち時間を設定します。
	 * @param value 待ち時間(msec単位)
	 */
	public synchronized void setInterval(final int value) {
		if (value < 0) {
			throw new IllegalArgumentException("interval=" + value);
		}

		this.interval = value;
	}

	/**
	 * Method getInterval. 次のメール送信までの待ち時間を取得します。
	 * @return int 待ち時間(msec単位)
	 */
	public synchronized int getInterval() {
		return interval;
	}

	/**
	 * Method setWait. メール一通送信ごとの待ち時間を設定します.
	 * @param value メール一通送信ごとの待ち時間(msec単位)
	 */
	public synchronized void setWait(final int value) {
		if (value < 0) {
			throw new IllegalArgumentException("wait=" + value);
		}
		this.wait = value;
	}

	/**
	 * Method setWait. メール一通送信ごとの待ち時間を取得します.
	 * @return int メール一通送信ごとの待ち時間(msec単位)
	 */
	public synchronized int getWait() {
		return wait;
	}

	/**
	 * Method clear. メール送信待ちキューをクリアします
	 */
	public void clear() {
		synchronized (queue) {
			queue.clear();
		}
	}

	/**
	 * Method setMaxQueueLength. メール送信待ちキューの最大長を設定します。 <br>
	 * キューの長さが最大長以上の場合は先頭から削除して、長さを最大長にあわせます。
	 * @param value メール送信待ちキューの最大長
	 */
	public synchronized void setMaxQueueLength(final int value) {
		if (value <= 0) {
			throw new IllegalArgumentException("maxQueue=" + value);
		}

		this.maxQueue = value;

		while (getMaxQueueLength() < getQueueLength()) {
			queue.remove(0);
		}
	}

	/**
	 * Method getMaxQueueLength. メール送信待ちキューの最大長を取得します.
	 * @return int メール送信待ちキューの最大長を設定します
	 */
	public synchronized int getMaxQueueLength() {
		return maxQueue;
	}

	/**
	 * Method getQueueLength. メール送信待ちキューの現在の長さを取得します.
	 * @return int メール送信待ちキューの現在の長さ
	 */
	public synchronized int getQueueLength() {
		return queue.size();
	}

	/**
	 * Method addMessage. メールをメール送信キューに登録します.
	 * @param from fromアドレス
	 * @param fromName fromネーム
	 * @param to toアドレス
	 * @param cc ccアドレス
	 * @param bcc bccアドレス
	 * @param subject 件名
	 * @param message 本文
	 * @return boolean 登録に成功したらtrue、失敗したらfalse
	 */
	public boolean addMessageImpl(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String subject, final String message) {

		if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to)
				|| StringUtils.isEmpty(subject) || StringUtils.isEmpty(message)) {
			// fromNameはチェック対象外
			throw new NullPointerException("from=" + from + ":subject="
					+ subject + ":to=" + to + ":message=" + message);
		}

		synchronized (queue) {
			if (getMaxQueueLength() <= getQueueLength()) {
				return false;
			}

			if (log.isInfoEnabled()) {
				log.info("addMessage:from=" + from + ":fromName=" + fromName
						+ ":to=" + to + ":subject=" + subject + ":message="
						+ message);
			}

			queue
					.add(new Message(from, fromName, to, cc, bcc, subject,
							message));
		}
		return true;
	}

	/**
	 * Method addMessage. メールをメール送信キューに登録します.
	 * @param from fromアドレス
	 * @param to toアドレス
	 * @param cc ccアドレス
	 * @param bcc bccアドレス
	 * @param subject 件名
	 * @param message 本文
	 * @return boolean 登録に成功したらtrue、失敗したらfalse
	 */
	public boolean addMessage(final String from, final String to,
			final String[] cc, final String[] bcc, final String subject,
			final String message) {
		return addMessageImpl(from, from, to, cc, bcc, subject, message);
	}

	/**
	 * Method addMessage. メールをメール送信キューに登録します.
	 * @param from fromアドレス
	 * @param fromName fromネーム
	 * @param to toアドレス
	 * @param cc ccアドレス
	 * @param bcc bccアドレス
	 * @param subject 件名
	 * @param message 本文
	 * @return boolean 登録に成功したらtrue、失敗したらfalse
	 */
	public boolean addMessage(final String from, final String fromName,
			final String to, final String[] cc, final String[] bcc,
			final String subject, final String message) {
		return addMessageImpl(from, fromName, to, cc, bcc, subject, message);
	}

	/**
	 * Method addMessage. メールをメール送信キューに登録します.
	 * @param from fromアドレス
	 * @param to toアドレス
	 * @param subject 件名
	 * @param message 本文
	 * @return boolean 登録に成功したらtrue、失敗したらfalse
	 */
	public boolean addMessage(final String from, final String to,
			final String subject, final String message) {

		return addMessageImpl(from, from, to, null, null, subject, message);
	}

	/**
	 * Method addMessage. メールをメール送信キューに登録します.
	 * @param from fromアドレス
	 * @param fromName fromネーム
	 * @param to toアドレス
	 * @param subject 件名
	 * @param message 本文
	 * @return boolean 登録に成功したらtrue、失敗したらfalse
	 */
	public boolean addMessage(final String from, final String fromName,
			final String to, final String subject, final String message) {

		return addMessageImpl(from, fromName, to, null, null, subject, message);

	}

	/**
	 * Method setStopped. スレッド停止フラグを立てます。
	 * @param value true 止める false 止めない
	 */
	public synchronized void setStopped(final boolean value) {
		this.stopped = value;
	}

	private synchronized boolean isStopped() {
		return stopped;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {

		setStopped(false);

		while (!isStopped()) {

			// application.propertiesの更新があれば反映する。。
			setInfo();

			try {
				Thread.sleep(getInterval());

				// ここでqueueから移す
				Message[] someMes = null;
				synchronized (queue) {
					someMes = queue.toArray(new Message[0]);
					// someMes = new Message[queue_.size()];
					// queue_.copyInto(someMes);
					queue.clear();
				}

				// 一通もなかったら眠る
				if (someMes.length == 0) {
					continue;
				}

				// 送信しない設定になっていたら眠る
				if (!isSend()) {
					continue;
				}

				if (!mail.connect()) {
					log.fatal("can't connect smtp server:address="
							+ mail.getSMTPServer());
					continue;
				}

				// ここでメールを送信
				// メール送信途中でも終了フラグがたっていたら、そっちを優先
				for (int i = 0; i < someMes.length && !isStopped(); i++) {
					if (0 < getWait()) {
						Thread.sleep(getWait());
					}

					boolean ret = mail.sendmail(someMes[i].getFrom(),
						someMes[i].getFromName(), someMes[i].getTo(),
						someMes[i].getCc(), someMes[i].getBcc(), null,
						someMes[i].getSubject(), someMes[i].getMessage());

					if (log.isInfoEnabled()) {
						log.info("sendmail return code=" + ret);
						log.info(" from=" + someMes[i].getFrom() + " fromName="
								+ someMes[i].getFromName() + " to="
								+ someMes[i].getTo() + " subject="
								+ someMes[i].getSubject() + " message="
								+ someMes[i].getMessage());
					}
				}

			} catch (InterruptedException e) {
				log.info("InterruptedException raised:" + e);
				setStopped(true);
			}

			mail.close();
		}

		log.info("MailSpooler#run is stopped...");
	}
}
