/*
 * Created on 2020/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 受注ファイル取込 Formクラス
 * @author 
 */
public final class OrderFileImportForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 得意先グループコンボボックス */
	private List<ComboBoxItems> venderGroupCombo;
	
	/* 得意先グループコード */
	private String venderGroupCd;
	
	/** アップロードファイル名称(フルパス) */
	private FormFile uploadFile;
	
	/** アップロードファイﾙ名称(名前のみ) */
	private String uploadFileName;
	
	/** プロシージャログメッセージ */
	private String logMsg;

	/** プロシージャログフラグ */
	private String logFlg;
	
	/** md5チェックサム値*/
	private String md5;
	
	/** 同じファイルが1ヵ月以内取込されているかのフラグ　0:取込なし　1:取込あり*/
	private int checkFileImpFlg;
	
	/** ファイル文字コード*/
	private String encType;
	
	/** サーバーファイルパス*/
	private String exFileName;
	
	/**
	 * コンストラクタ.
	 */
	public OrderFileImportForm() {
		
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
	
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {

		ActionErrors errors = null;

		/* Validatorによる判定 */
		if ("upload".equals(getOp())) {
			errors = super.validate(mapping, request);
			if (errors.isEmpty()) {
				/* 添付ファイルのエラー処理 */
				if (checkFileRequired() || checkFileSize()) {
					errors.add("", new ActionMessage("errors.file"));
				}
			}
		}

		return errors;
	}

	/**
	 * ファイルのサイズチェック 1バイト以上10メガバイト以下.
	 * @return boolean true:エラー false:正常
	 */
	private boolean checkFileSize() {
		if (null != getUploadFile()) {
			if (getUploadFile().getFileName() != null
					&& !getUploadFile().getFileName().equals("")) {
				int size = getUploadFile().getFileSize();
				if (0 == size
						|| Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
								.getString("upload.file.max.size")) < size) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * ファイルの判定 ファイルが存在するか.
	 * @return true:エラー false:正常
	 */
	private boolean checkFileRequired() {

		if (null != getUploadFile()) {
			if (getUploadFile().getFileName() != null
					&& !getUploadFile().getFileName().equals("")) {
				return !StringUtils.isNotEmpty(getUploadFile().getFileName());
			}
		}
		return false;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		
		setVenderGroupCd(null);
		setUploadFile(null);
		setUploadFileName(null);
		setLogFlg("false");
		setLogMsg(null);
		setMd5(null);
		setCheckFileImpFlg(0);
		setEncType(null);
		setExFileName(null);
	}

	/**
	 * venderGroupComboを取得します。
	 * @return venderGroupCombo
	 */
	public List<ComboBoxItems> getVenderGroupCombo() {
		return venderGroupCombo;
	}

	/**
	 * venderGroupComboを設定します。
	 * @param venderGroupCombo venderGroupCombo
	 */
	public void setVenderGroupCombo(List<ComboBoxItems> venderGroupCombo) {
		this.venderGroupCombo = venderGroupCombo;
	}

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}
	
	/**
	 * uploadFileを取得します。
	 * @return uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * uploadFileを設定します。
	 * @param uploadFile uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * uploadFileNameを取得します。
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * uploadFileNameを設定します。
	 * @param uploadFileName uploadFileName
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * logMsgを取得します。
	 * @return logMsg
	 */
	public String getLogMsg() {
		return logMsg;
	}

	/**
	 * logMsgを設定します。
	 * @param logMsg logMsg
	 */
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	/**
	 * logFlgを取得します。
	 * @return logFlg
	 */
	public String getLogFlg() {
		return logFlg;
	}

	/**
	 * logFlgを設定します。
	 * @param logFlg logFlg
	 */
	public void setLogFlg(String logFlg) {
		this.logFlg = logFlg;
	}

	/**
	 * md5を取得します。
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * md5を設定します。
	 * @param md5 md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * checkFileImpFlgを取得します。
	 * @return checkFileImpFlg
	 */
	public int getCheckFileImpFlg() {
		return checkFileImpFlg;
	}

	/**
	 * checkFileImpFlgを設定します。
	 * @param checkFileImpFlg checkFileImpFlg
	 */
	public void setCheckFileImpFlg(int checkFileImpFlg) {
		this.checkFileImpFlg = checkFileImpFlg;
	}

	/**
	 * encTypeを取得します。
	 * @return encType
	 */
	public String getEncType() {
		return encType;
	}

	/**
	 * encTypeを設定します。
	 * @param encType encType
	 */
	public void setEncType(String encType) {
		this.encType = encType;
	}

	/**
	 * exFileNameを取得します。
	 * @return exFileName
	 */
	public String getExFileName() {
		return exFileName;
	}

	/**
	 * exFileNameを設定します。
	 * @param exFileName exFileName
	 */
	public void setExFileName(String exFileName) {
		this.exFileName = exFileName;
	}

}
