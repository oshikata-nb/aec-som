/*
 * Created on 2018/02/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common;

import java.math.BigDecimal;
import java.util.List;

import javax.mail.MessagingException;


/**
 * ロジッククラス interface. 共通ロジック
 */
public interface SendMailLogic {

	/**
	 * メール送信メソッド
	 * @param toUserList	メール送信先ユーザのリスト
	 * @param mailFormatId	メールフォーマットID
	 * @param subjectParamList	メール件名のパラメータリスト
	 * @param bodyParamList		メール本文のパラメータリスト
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMail(final List<String> toUserList
			, final String mailFormatId, final List<String> subjectParamList
			, final List<String> bodyParamList
			, final String tantoCd)	throws IllegalArgumentException;
	
	/**
	 * メール送信メソッド
	 * @param toUserList	メール送信先ユーザのリスト
	 * @param mailFormatId	メールフォーマットID
	 * @param subjectParamList	メール件名のパラメータリスト
	 * @param bodyParamList		メール本文のパラメータリスト
	 * @param sendMode   メール送信方法
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMail(
			final List<String> toUserList, final String mailFormatId
			, final List<String> subjectParamCsv, final List<String> bodyParamCsv, final String tantoCd
			, final BigDecimal sendMode)
					throws IllegalArgumentException, MessagingException;
	
	/**
	 * メール送信メソッド(取引先）
	 * @param toVenderDivision	メール送信先取引先区分
	 * @param venderCd	メール取引先コード
	 * @param mailFormatId	メールフォーマットID
	 * @param subjectParamList	メール件名のパラメータリスト
	 * @param bodyParamList		メール本文のパラメータリスト
	 * @param fileParamList		添付ファイルのパラメータリスト
	 * @param tantoCd 担当者コード
	 * @param sendMode メールモード 1:メール送信 自動FAX
	 * @param getMode 宛先取得モード 0:受注 1:売上
	 * @param actionSeq 実行者を判定するシーケンス
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMailVender(
			final String VenderDivision,final String venderCd, 
			final String mailFormatId, final List<String> subjectParamList, 
			final List<String> bodyParamList, final List<String> filePathList,final String tantoCd,
			final BigDecimal sendMode, final BigDecimal getMode, final BigDecimal actionSeq)
					throws IllegalArgumentException;
	
	/**
	 * メール送信メソッド(添付ファイルあり）
	 * @param toUserList	メール送信先ユーザのリスト
	 * @param mailFormatId	メールフォーマットID
	 * @param subjectParamList	メール件名のパラメータリスト
	 * @param bodyParamList		メール本文のパラメータリスト
	 * @param fileParamList		添付ファイルのパラメータリスト
	 * @throws IllegalArgumentException
	 * @throws MessagingException
	 */
	public boolean sendMail(
			final List<String> toUserList, final String mailFormatId
			, final List<String> subjectParamCsv, final List<String> bodyParamCsv
			, final List<String> filePathList,final String tantoCd, final BigDecimal sendMode)
					throws IllegalArgumentException, MessagingException;
}
