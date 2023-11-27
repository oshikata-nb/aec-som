/*
 * Created on 2020/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.mailtemplate;

import java.io.Serializable;

/**
 * NamesDetailクラス.
 * @author ssv
 */
public class MailTemplateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MailTemplateBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MAIL_TEMPLATE";
	
	/*  */
	/** COLUMNアノテーション mailFotmatId */
	public static final String mailFotmatId_COLUMN = "MAIL_FORMAT_ID";

	/*  */
	/** COLUMNアノテーション languageId */
	public static final String languageId_COLUMN = "LANGUAGE_ID";

	/*  */
	/** COLUMNアノテーション description */
	public static final String description_COLUMN = "DESCRIPTION";

	/*  */
	/** COLUMNアノテーション textTitle */
	public static final String textTitle_COLUMN = "TEXT_TITLE";

	/*  */
	/** COLUMNアノテーション textBody */
	public static final String textBody_COLUMN = "TEXT_BODY";
	
	/*  */
	/** COLUMNアノテーション pdfPath */
	public static final String pdfPath_COLUMN = "PDF_PATH";

	//
	// インスタンスフィールド
	//

	/**  */
	private String mailFormatId;

	/**  */
	private String languageId;

	/**  */
	private String description;

	/**  */
	private String textTitle;

	/**  */
	private String textBody;
	
	private String pdfPath;

	//
	// インスタンスメソッド
	//

	/**
	 * nameDivision取得.
	 * 表示順
	 * @return nameDivision
	 */
	public String getMailFormatId() {
		return this.mailFormatId;
	}

	/**
	 * mailFormatId設定.
	 * 表示順
	 * @param nameDivision nameDivision
	 */
	public void setMailFormatId(final String mailFormatId) {
		this.mailFormatId = mailFormatId;
	}

	/**
	 * languageId取得.
	 * 表示順
	 * @return nameDivision
	 */
	public String getLanguageId() {
		return this.languageId;
	}

	/**
	 * languageId設定.
	 * 表示順
	 * @param nameDivision nameDivision
	 */
	public void setLanguageId(final String languageId) {
		this.languageId = languageId;
	}

	/**
	 * description取得.
	 * 表示(0),非表示(1)
	 * @return nameCd
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * description設定.
	 *
	 * @param nameCd nameCd
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * textTitle取得.
	 *
	 * @return textTitle
	 */
	public String getTextTitle() {
		return this.textTitle;
	}

	/**
	 * textTitle設定.
	 *
	 * @param textTitle textTitle
	 */
	public void setTextTitle(final String textTitle) {
		this.textTitle = textTitle;
	}

	/**
	 * textBody取得.
	 *
	 * @return textBody
	 */
	public String getTextBody() {
		return this.textBody;
	}

	/**
	 * textBody設定.
	 *
	 * @param textBody textBody
	 */
	public void setTextBody(final String textBody) {
		this.textBody = textBody;
	}

	/**
	 * pdfPathを取得します。
	 * @return pdfPath
	 */
	public String getPDFPath() {
		return pdfPath;
	}

	/**
	 * pdfPathを設定します。
	 * @param pdfPath pdfPath
	 */
	public void setPDFPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
}

