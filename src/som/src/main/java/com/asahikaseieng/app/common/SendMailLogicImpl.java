/*
 * Created on 2018/02/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.companysetting.CompanySetting;
import com.asahikaseieng.dao.entity.master.companysetting.CompanySettingDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplateDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.sendmailerrorlog.SendMailErrorLog;
import com.asahikaseieng.dao.entity.sendmailerrorlog.SendMailErrorLogDao;
import com.asahikaseieng.dao.entity.sendmaillog.SendMailLog;
import com.asahikaseieng.dao.entity.sendmaillog.SendMailLogDao;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLog;
import com.asahikaseieng.dao.entity.slipsalesactionlog.SlipSalesActionLogDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 共通 ロジック実装クラス
 */
public class SendMailLogicImpl implements SendMailLogic {

	private LoginDao loginDao;

	
	private CompanySettingDao companySettingDao;

	private MailTemplateDao mailTemplateDao;
	
	private SendMailLogDao sendMailLogDao;

	private SendMailErrorLogDao sendMailErrorLogDao;

	private VenderDao venderDao;

	private OrganizationDao organizationDao;
	
	private SlipSalesActionLogDao slipSalesActionLogDao;

	/**
	 * コンストラクタ
	 */
	public SendMailLogicImpl() {
	}

	/**
	 * メール送信メソッド(添付ファイルあり）
	 *
	 * @param toUserList
	 *            メール送信先ユーザのリスト
	 * @param mailFormatId
	 *            メールフォーマットID
	 * @param subjectParamList
	 *            メール件名のパラメータリスト
	 * @param bodyParamList
	 *            メール本文のパラメータリスト
	 * @param fileParamList
	 *            添付ファイルのパラメータリスト
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMail(final List<String> toUserList,
			final String mailFormatId, final List<String> subjectParamList,
			final List<String> bodyParamList, final List<String> filePathList,
			final String tantoCd, final BigDecimal sendMode)
			throws IllegalArgumentException {

		boolean blRtn = false;
		boolean isError = false;
		List<String> toAddresses = new ArrayList<String>();

		String fromAddress = StringUtils.EMPTY;
		Login user = new Login();
		// 宛先のユーザーIDからメールアドレスを取得する
		for (int i = 0; i < toUserList.size(); i++) {

			String toAddress = StringUtils.EMPTY;
			String toTantoCd = toUserList.get(i);

			try {
				user = getTanto(toTantoCd);
			} catch (NoDataException e) {
				// 宛先情報が取得できなかった場合
				String msgId = SendMailConstants.MSGID_GET_USRINFO_ERR;
				// エラーログの出力
				insertSendMailErrorLog(mailFormatId, tantoCd, toUserList, toAddresses, msgId, e);
				isError = true;
				continue;
			}

			toAddress = user.getMailAddress();
			toAddresses.add(toAddress);
		}
		//送信元のメールアドレスを取得
		try {
			user = getTanto(tantoCd);
		} catch (NoDataException e) {
			// ユーザー情報取得エラー
			String msgId = SendMailConstants.MSGID_GET_USRINFO_ERR;
			// エラーログの出力
			insertSendMailErrorLog(mailFormatId, tantoCd, toUserList, toAddresses, msgId, e);
			isError = true;
		}

		fromAddress = user.getMailAddress();

		
		// メールテンプレートの取得
		MailTemplate template = mailTemplateDao.getTemplate(mailFormatId);
		if (template == null || template.getTextTitle() == null
				|| template.getTextBody() == null) {
			// テンプレートが取得できなかった場合
			String msgId = SendMailConstants.MSGID_GET_TEMPLATE_ERR;
			// エラーログの出力
			insertSendMailErrorLog(mailFormatId, tantoCd, toUserList, toAddresses, msgId, null);
			isError = true;
		}
		String subject = MessageFormat.format(template.getTextTitle(),
			subjectParamList.toArray());
		String body = MessageFormat.format(template.getTextBody(),
			bodyParamList.toArray());
		// メール送信実行
		if (!sendMailImpl(toUserList, toAddresses, null, null, null,
				fromAddress, null, subject, body, filePathList,
				mailFormatId, tantoCd, sendMode)) {
			isError = true;
		}

		if (isError) {
			// メール送信失敗とする
			blRtn = false;
		} else {
			// メール送信成功とする
			blRtn = true;
		}

		return blRtn;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendMail(final List<String> toUserList,
			final String mailFormatId, final List<String> subjectParamList,
			final List<String> bodyParamList, final String tantoCd)
			throws IllegalArgumentException {

		return sendMail(toUserList, mailFormatId, subjectParamList,
				bodyParamList, null, tantoCd,
				SendMailConstants.SEND_MODE_MAIL_ONLY);

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sendMail(final List<String> toUserList,
			final String mailFormatId, final List<String> subjectParamList,
			final List<String> bodyParamList, final String tantoCd,
			final BigDecimal sendMode) throws IllegalArgumentException {

		return sendMail(toUserList, mailFormatId, subjectParamList,
				bodyParamList, null, tantoCd, sendMode);

	}

	/**
	 * メール送信メソッド(取引先への送信用）
	 *
	 * @param toVenderDivision
	 *            メール送信先取引先区分
	 * @param venderCd
	 *            メール取引先コード
	 * @param mailFormatId
	 *            メールフォーマットID
	 * @param subjectParamList
	 *            メール件名のパラメータリスト
	 * @param bodyParamList
	 *            メール本文のパラメータリスト
	 * @param fileParamList
	 *            添付ファイルのパラメータリスト
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMailVender(final String venderDivision,
			final String venderCd, final String mailFormatId,
			final List<String> subjectParamList,
			final List<String> bodyParamList, final List<String> filePathList,
			final String tantoCd, final BigDecimal sendMode, final BigDecimal getMode, final BigDecimal actionSeq)
			throws IllegalArgumentException {
		boolean blRtn = false;
		boolean isError = false;
		String fromAddress = null; // 送信元メールアドレス
		List<String> toAddresses = new ArrayList<String>(); // 送信先メールアドレス
		List<String> bccAddresses = new ArrayList<String>(); // bccアドレス
		String faxNo = null;
		Organization organization = new Organization();
		String subject = null;
		String body = null;
		
		// 取引先M検索
		Vender beanVender = venderDao.getEntity(venderCd, venderDivision);
		
		insertLog(actionSeq,tantoCd,"sendMailVender", "start");

		if (beanVender != null) {
			
			// 担当部門検索
			try {
				
				if(beanVender.getMailOrganizationCd() != null && !beanVender.getMailOrganizationCd().equals("")){
					organization = this.getOrganization( beanVender.getMailOrganizationCd() );

					if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
						fromAddress = organization.getFromMailAddress1();
						
						if(organization.getBccSendFlg().compareTo(BigDecimal.ONE) == 0){
							// 部署マスタでBCC送信設定を行っている場合、FromアドレスをBCCに設定する。
							bccAddresses.add(organization.getFromMailAddress1());
						}
					}
					
				}
				
			} catch (NoDataException e) {
				// 宛先情報が取得できなかった場合
				String msgId = SendMailConstants.MSGID_GET_ORGINFO_ERR;
				// エラーログの出力
				insertSendMailErrorLog(mailFormatId, tantoCd, null, toAddresses, msgId, e);
				isError = true;
				return false;
			}
			
			if(getMode.compareTo(SendMailConstants.GET_ORDER_INFO) == 0){
				// -------------------------------------------------------------------------------------
				// 受注関連情報取得
				// -------------------------------------------------------------------------------------
				if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
					// メール送信の場合のみ、メールアドレスを設定する。
					if(beanVender.getOrderMailAddress1() != null){
						toAddresses.add(beanVender.getOrderMailAddress1()); // 受注メールアドレス1
					}
					if(beanVender.getOrderMailAddress2() != null){
						toAddresses.add(beanVender.getOrderMailAddress2()); // 受注メールアドレス2
					}
					if(beanVender.getOrderMailAddress3() != null){
						toAddresses.add(beanVender.getOrderMailAddress3()); // 受注メールアドレス3
					}
				}				
				if (sendMode.compareTo(SendMailConstants.SEND_MODE_AUTO_FAX) == 0) {
					// FAX送信の場合のみFAX番号を設定する。
					if(beanVender.getOrderFaxNo()!=null){// NULLの場合置換処理でエラーが発生していたため修正
						faxNo = beanVender.getOrderFaxNo().replace("-", ""); // 受注FAX番号(ハイフンは取り除く)
					}
				}
			} else if(getMode.compareTo(SendMailConstants.GET_SALES_INFO) == 0){ 
				// -------------------------------------------------------------------------------------
				// 売上関連情報取得
				// -------------------------------------------------------------------------------------
				if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
					// メール送信の場合のみ、メールアドレスを設定する。
					if(beanVender.getSalesMailAddress1() != null){
						toAddresses.add(beanVender.getSalesMailAddress1()); // 売上メールアドレス1
					}
					if(beanVender.getSalesMailAddress2() != null){
						toAddresses.add(beanVender.getSalesMailAddress2()); // 売上メールアドレス2
					}
					if(beanVender.getSalesMailAddress3() != null){
						toAddresses.add(beanVender.getSalesMailAddress3()); // 売上メールアドレス3
					}
				}
				if (sendMode.compareTo(SendMailConstants.SEND_MODE_AUTO_FAX) == 0) {
					// FAX送信の場合のみFAX番号を設定する。
					if(beanVender.getSalesFaxNo()!=null){// NULLの場合置換処理でエラーが発生していたため修正
						faxNo = beanVender.getSalesFaxNo().replace("-", ""); // 売上FAX番号
					}
				}
			}
		}

		if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
			// メール

			// メールテンプレートの取得
			MailTemplate template = mailTemplateDao.getTemplate(mailFormatId);

			subject = MessageFormat.format(template.getTextTitle(),	subjectParamList.toArray());
			body = MessageFormat.format(template.getTextBody(),	bodyParamList.toArray());
		} else if (sendMode.compareTo(SendMailConstants.SEND_MODE_AUTO_FAX) == 0) {
			// 自動FAX

			// メールテンプレートの取得
			MailTemplate template = mailTemplateDao.getTemplate(mailFormatId);

			if(template.getTextTitle()!=null){
				subject = MessageFormat.format(template.getTextTitle(),	subjectParamList.toArray());
			}
			
			if(template.getTextBody() != null){
				body = MessageFormat.format(template.getTextBody(),	bodyParamList.toArray());
			}
		}
		

		// メール送信実行
		if (!sendMailImpl(null, toAddresses, faxNo, null, bccAddresses, fromAddress,null, subject, body, filePathList, mailFormatId, tantoCd,sendMode)) {
			isError = true;
		}

		// 送信後のメールを宛先、ファイルログを保存 20221121
		mailLog(beanVender.getVenderCd(),beanVender.getVenderName1(),beanVender.getVenderName2(),
				toAddresses,bccAddresses,filePathList,faxNo,tantoCd,actionSeq);

		if (isError) {
			// メール送信失敗とする
			blRtn = false;
		} else {
			// メール送信成功とする
			blRtn = true;
		}

		return blRtn;
	}
	/**
	 * メールログ
	 *
	 * @param venderCd
	 *            取引先コード
	 * @param venderName1
	 *            取引先名称1
	 * @param venderName2
	 *            取引先名称2
	 * @param toAddresses
	 *            メール送信先リスト
	 * @param bccAddresses
	 *            BCCアドレスリスト
	 * @param filePathList
	 *            ファイルパスリスト
	 * @param faxNo
	 *            ファックス番号
	 * @param tantoCd
	 *            ログインユーザ
	 */
	private void mailLog(String venderCd, String venderName1, String venderName2,List<String> toAddresses,
			List<String> bccAddresses,
			List<String> filePathList,
			String faxNo,String tantoCd,BigDecimal actionSeq){
		
		//シーケンスが未設定の場合はログを保存しない。
		if(actionSeq==null || actionSeq.equals(BigDecimal.ZERO)){
			return;
		}
		
		//　取引先コードをログへ保管する。
		if( venderCd != null){
			insertLog(actionSeq, tantoCd, "venderCd", venderCd);
		}
		
		//　取引先名称をログへ保管する。
		if( venderName1 != null){
			if ( venderName2 != null ){
				insertLog(actionSeq, tantoCd, "venderName", venderName1 +" "+ venderName2);
			}
			else{
				insertLog(actionSeq, tantoCd, "venderName", venderName1);
			}
		}
		
		// メールアドレスをログへ保管する。
		for (int i=0;i<toAddresses.size(); i++){
			insertLog(actionSeq,tantoCd,"mail:to", toAddresses.get(i));
		}
		
		//　BCCをログに保管する
		for (int i=0;i<bccAddresses.size(); i++){
			insertLog(actionSeq,tantoCd,"mail:bcc", bccAddresses.get(i));
		}
		
		//　FAXをログへ保管する。
		if( faxNo != null){
			insertLog(actionSeq,tantoCd,"fax:to", faxNo);
		}

		//　ファイルをログに保管する
		for (int i=0;i<filePathList.size(); i++){
			insertLog(actionSeq,tantoCd,"mail:file", filePathList.get(i));
		}
	}

	/**
	 * メール送信メソッド
	 *
	 * @param toTantoCd            宛先担当者コード
	 * @param toAddressCsv            宛先アドレス（abc@def.co.jp）
	 * @param toFaxNo            宛先FAX番号
	 * @param ccAddresses            CCアドレス（abc@def.co.jpのリスト）
	 * @param bccAddresses            BCCアドレス（abc@def.co.jpのリスト）
	 * @param fromAddress            送信元アドレス（abc@def.co.jp）
	 * @param replyToAddresses            返信先アドレス（abc@def.co.jpのリスト）
	 * @param subject*            メール件名
	 * @param mainText            メール本文
	 * @param filePath            添付ファイルパス（絶対パス C:\folder\folder...\file.ext）
	 * @param mailFormatId            メールID
	 * @param loginTantoCd            ログイン担当者コード
	 * @throws FileNotFoundException 　添付ファイルが見つからない。
	 * @throws MessagingException
	 */
	private boolean sendMailImpl(final List<String> toTantoCdList,
			final List<String> toAddresses, final String toFaxNo,
			final List<String> ccAddresses, final List<String> bccAddresses,
			final String fromAddress, final List<String> replyToAddresses,
			final String subject, final String mainText,
			final List<String> filePathList, final String mailFormatId,
			final String loginTantoCd, final BigDecimal sendMode) throws IllegalArgumentException {
		// 必須チェック（宛先アドレス、メール件名、メール本文）
		if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
			// メールの場合、宛先と件名と本文がない場合エラーとする。
			if ( toAddresses.isEmpty() || StringUtils.isEmpty(subject) || StringUtils.isEmpty(mainText)) {
				String msgId = SendMailConstants.MSGID_GET_PARAMETER_ERR;
				// エラーログの出力
				insertSendMailErrorLog(mailFormatId, loginTantoCd, toTantoCdList,toAddresses, msgId, null);
				return false;
			}
		}

		// 自社マスタ拡張設定から設定取得
		CompanySetting comBean = companySettingDao.getEntity(Constants.COMPANY_CD);

		String host = comBean.getMailSendServer();
		String defaultFromAddress = null;

		if (sendMode.compareTo(SendMailConstants.SEND_MODE_AUTO_FAX) == 0) {
			// 自動FAX
			defaultFromAddress = comBean.getAutoFaxSender();
			toAddresses.add(toFaxNo + comBean.getAutoFaxCommonDomain());
		} else if (StringUtils.isEmpty(fromAddress)) {
			// メール(送信元アドレスが未設定の場合)
			defaultFromAddress = comBean.getMailFromAddress();
		} else {
			// メール
			defaultFromAddress = fromAddress;
		}

		// 接続設定
		Properties props = new Properties();
		props.put("mail.smtp.host", host); // SMTPサーバ（メール送信サーバ）の設定
		props.put("mail.smtp.port", SendMailConstants.DEFAULT_SMTP_PORT);
		props.put("mail.smtp.connectiontimeout", SendMailConstants.DEFAULT_TIMEOUT);
		props.put("mail.smtp.timeout", SendMailConstants.DEFAULT_TIMEOUT);
		props.put("mail.debug", "false");
		Session session = Session.getInstance(props);

		try {
			// メールの作成
			Message msg = new MimeMessage(session);

			// 宛先アドレス
			msg.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(StringUtils.join(toAddresses.iterator(), ','), true));

			// CCアドレス
			if (ccAddresses != null && ccAddresses.size() > 0) {
				msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(StringUtils.join(ccAddresses.iterator(), ","), true));
			}
			// BCCアドレス
			if (bccAddresses != null && bccAddresses.size() > 0) {
				msg.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(StringUtils.join(bccAddresses.iterator(), ","), true));
			}
			// 送信元アドレス
			if (StringUtils.isNotEmpty(fromAddress)) {
				msg.setFrom(new InternetAddress(fromAddress, true));
			} else {
				// 指定がない場合、自社マスタから取得した値を設定
				msg.setFrom(new InternetAddress(defaultFromAddress, true));
			}

			// 返信先アドレス
			if (replyToAddresses != null && replyToAddresses.size() > 0) {
				msg.setReplyTo(InternetAddress.parse(StringUtils.join(replyToAddresses.iterator(), ","), true));
			}

			msg.setSubject(myUnescape(subject)); // メール件名
			MimeMultipart mp = new MimeMultipart(); // メール本文
			MimeBodyPart partText = new MimeBodyPart(); // テキストパート

			if (sendMode.compareTo(SendMailConstants.SEND_MODE_MAIL_ONLY) == 0) {
				partText.setText(myUnescape(mainText), "x-windows-iso2022jp"); // 文字列をセット
				partText.setHeader("Content-Type","text/plain; charset=x-windows-iso2022jp"); // キャラクタセットを「x-windows-iso2022jp」
				partText.setHeader("Content-Transfer-Encoding", "Base64"); // エンコード形式「Base64」
				mp.addBodyPart(partText); // 本文をセット
			}

			if (filePathList != null) {
				// ファイルパート
				for (String filePath : filePathList) {
					if (StringUtils.isNotEmpty(filePath)) {
						File fil = new File(filePath);
						String fileName = ""; // ファイル名

						if (fil.exists()) {
							// 指定されたファイルが存在する場合のみ
							MimeBodyPart partFile = new MimeBodyPart();
							partFile.setDataHandler(new DataHandler(new FileDataSource(filePath)));
							fileName = partFile.getDataHandler().getName();
							try {
								// 大文字「ISO-2022-JP」はNG
								fileName = MimeUtility.encodeWord(partFile.getDataHandler().getName(),"x-windows-iso2022jp", null);
								partFile.setHeader("Content-Type","application/pdf; name=" + fileName); // PDF、ファイル名
								partFile.setHeader("Content-Transfer-Encoding",	"Base64"); // エンコード形式「Base64」
							} catch (UnsupportedEncodingException e) {
							}

							partFile.setFileName(fileName);
							mp.addBodyPart(partFile);
						}else{
							throw new IllegalArgumentException("指定された添付ファイルが存在しません。filepath:"+filePath);
						}
						//エラー返す
					}
				}
			}

			msg.setContent(mp);

			// 送信
			if (AecNumberUtils.compareToEqual(comBean.getMailSendFlg(), SendMailConstants.MAIL_SEND_FLG_ON)) {
				Transport.send(msg);
			}
			//メール送信ログに追加
			insertSendMailLog(mailFormatId, loginTantoCd, toTantoCdList, toAddresses, subject, mainText);
			
			return true;

			// 複数人にメールする場合は「SendFailedException」の例外も発生するため、
			// キャッチして対処する必要があります
		} catch (MessagingException ex) {
			String msgId = SendMailConstants.MSGID_SEND_ERR;
			// エラーログの出力
			insertSendMailErrorLog(mailFormatId, loginTantoCd, toTantoCdList,toAddresses, msgId, ex);
			return false;
		}
	}

	/**
	 * 担当者情報取得(担当者マスタ)
	 *
	 * @param tantoCd
	 *            担当者コード
	 * @return Login 担当者情報
	 * @throws NoDataException
	 */
	private Login getTanto(String tantoCd) throws NoDataException {

		// 担当者マスタからレコードを取得
		Login bean = loginDao.getEntity(tantoCd);

		if (bean == null || StringUtils.isBlank(bean.getMailAddress())) {
			// 担当者マスタに存在しない場合
			throw new NoDataException();
		}

		return bean;
	}
	
	/**
	 * 担当者情報取得(担当者マスタ)
	 *
	 * @param tantoCd
	 *            担当者コード
	 * @return Login 担当者情報
	 * @throws NoDataException
	 */
	private Organization getOrganization(String organizationCd) throws NoDataException {

		// 担当者マスタからレコードを取得
		Organization bean = this.organizationDao.getEntity(organizationCd);

		if (bean == null || StringUtils.isBlank(bean.getFromMailAddress1())) {
			// 担当者マスタに存在しない場合
			throw new NoDataException();
		}

		return bean;
	}
	
	/**
	 * insertLog
	 * 
	 * @param tantoCd String
	 * @param action String
	 * @param actionMsg String
	 */
	public void insertLog(BigDecimal seq,String tantoCd,String action,String actionMsg){

		SlipSalesActionLog bean = new SlipSalesActionLog();
		bean.setActionSeq(seq);
		bean.setLogDate(AecDateUtils.getCurrentTimestamp());
		bean.setTantoCd(tantoCd);
		bean.setAction(action);
		bean.setActionMsg(actionMsg);
		slipSalesActionLogDao.insert(bean);
	}

	/**
	 * slipSalesActionLogDaoを設定します。
	 * @param salesDao salesDao
	 */
	public void setSlipSalesActionLogDao(SlipSalesActionLogDao slipSalesActionLogDao) {
		this.slipSalesActionLogDao = slipSalesActionLogDao;
	}
	/**
	 * メール送信ログの登録
	 *
	 * @param mailFormatId
	 * @param loginTantoCd
	 * @param toTantoCd
	 * @param toAddress
	 * @param msgId
	 * @param ex
	 */
	private void insertSendMailLog(final String mailFormatId,
			final String loginTantoCd, final List<String> toTantoCdList,
			List<String> toAddresses, String textTitle, String textBody) {
		
		// メールエラーログ出力
		SendMailLog bean = new SendMailLog();
		bean.setSendDate(AecDateUtils.getCurrentTimestamp());
		bean.setMailFormatId(mailFormatId);
		bean.setTextTitle(textTitle);
		bean.setTextBody(textBody);
		bean.setLoginTantoCd(loginTantoCd);
		if(toTantoCdList != null){
			for(int i=0; i < toTantoCdList.size(); i++){
				bean.setToTantoCd01(toTantoCdList.get(i));
				if(i == 1) bean.setToTantoCd02(toTantoCdList.get(1));
				if(i == 2) bean.setToTantoCd03(toTantoCdList.get(2));
			}
		}
		for(int i=0; i < toAddresses.size(); i++){
			bean.setToMailAddress01(toAddresses.get(0));
			if(i == 1) bean.setToMailAddress02(toAddresses.get(1));
			if(i == 2) bean.setToMailAddress03(toAddresses.get(2));
		}
		sendMailLogDao.insert(bean);
	}

	/**
	 * メール送信エラーログの登録
	 *
	 * @param mailFormatId
	 * @param loginTantoCd
	 * @param toTantoCd
	 * @param toAddress
	 * @param msgId
	 * @param ex
	 */
	private void insertSendMailErrorLog(final String mailFormatId,
			final String loginTantoCd, final List<String> toTantoCdList,
			List<String> toAddresses, String msgId, Exception ex) {

		String errMsg = StringUtils.EMPTY;
		if (ex != null) {
			java.lang.StackTraceElement[] stack = ex.getStackTrace();
			errMsg = "Message : " + ex.getMessage() + ", ClassName : "
					+ stack[0].getClassName() + ", MethodName : "
					+ stack[0].getMethodName() + ", FileName : "
					+ stack[0].getFileName() + ", LineNumber : "
					+ stack[0].getLineNumber();
			for (int i = 0; i < stack.length; i++) {
				errMsg = errMsg + "\n" + stack[i].toString();
			}
			errMsg = errMsg.substring(0, SendMailConstants.STAC_TRACE_MAX_LENGTH);
		}

		// メールエラーログ出力
		SendMailErrorLog errBean = new SendMailErrorLog();
		errBean.setLogString(errMsg);
		errBean.setErrorDate(AecDateUtils.getCurrentTimestamp());
		errBean.setMailFormatId(mailFormatId);
		errBean.setMessageId(msgId);
		errBean.setLoginTantoCd(loginTantoCd);
		if(toTantoCdList != null){
			for(int i=0; i < toTantoCdList.size(); i++){
				errBean.setToTantoCd01(toTantoCdList.get(i));
				if(i == 1) errBean.setToTantoCd02(toTantoCdList.get(1));
				if(i == 2) errBean.setToTantoCd03(toTantoCdList.get(2));
			}
		}
		for(int i=0; i < toAddresses.size(); i++){
			errBean.setToMailAddress01(toAddresses.get(0));
			if(i == 1) errBean.setToMailAddress02(toAddresses.get(1));
			if(i == 2) errBean.setToMailAddress03(toAddresses.get(2));
		}
		sendMailErrorLogDao.insert(errBean);
	}

	/**
	 * メールの件名、本文をアンスケープ ※今のところ、改行文字のみ
	 *
	 * @param text
	 * @return
	 */
	private String myUnescape(final String text) {

		// 改行文字のアンエスケープ
		String val = text.replaceAll("\\\\n", "\n");

		return val;
	}

	/**
	 * @param loginDao
	 *            セットする loginDao
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * companySettingDaoを設定します。
	 * @param companySettingDao companySettingDao
	 */
	public void setCompanySettingDao(CompanySettingDao companySettingDao) {
		this.companySettingDao = companySettingDao;
	}

	/**
	 * @param mailTemplateDao
	 *            セットする mailTemplateDao
	 */
	public void setMailTemplateDao(MailTemplateDao mailTemplateDao) {
		this.mailTemplateDao = mailTemplateDao;
	}

	/**
	 * sendMailLogDaoを設定します。
	 * @param sendMailLogDao sendMailLogDao
	 */
	public void setSendMailLogDao(SendMailLogDao sendMailLogDao) {
		this.sendMailLogDao = sendMailLogDao;
	}

	/**
	 * @param sendMailErrorLogDao
	 *            セットする sendMailErrorLogDao
	 */
	public void setSendMailErroLogDao(SendMailErrorLogDao sendMailErrorLogDao) {
		this.sendMailErrorLogDao = sendMailErrorLogDao;
	}

	/**
	 * @param venderDao
	 *            セットする venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * organizationDaoを取得します。
	 * @return organizationDao
	 */
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao organizationDao
	 */
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

}
