/*
 * Created on 2022/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

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
 * 製造実績ファイル取込 Formクラス
 * @author 
 */
public final class RdirectionFileImportForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 得意先グループコンボボックス */
	private List<ComboBoxItems> venderGroupCombo;
	
	/** アップロードファイル名称(フルパス) */
	private FormFile uploadFile;
	
	/** アップロードファイﾙ名称(名前のみ) */
	private String uploadFileName;
	
	/** プロシージャログメッセージ */
	private String logMsg;

	/** プロシージャログフラグ */
	private String logFlg;
	
	/** サーバーファイルパス*/
	private String exFileName;
	
	/**
	 * コンストラクタ.
	 */
	public RdirectionFileImportForm() {
		
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
		
		setUploadFile(null);
		setUploadFileName(null);
		setLogFlg("false");
		setLogMsg(null);
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
