/*
 * Created on 2022/08/08
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.struts.AbstractAction;

/**
 * 製造実績ファイル取込(ポップアップ) Actionクラス.
 * @author 
 */
public final class RdirectionFileImportAction extends AbstractAction {

	/** 受注ファイル取込ロジッククラス */
	private RdirectionFileImportLogic rdirectionFileImportLogic;
	
	/** Exception時出力用エラーメッセージ */
	public ActionMessages exceptionErrorMsg;

	/** スクロールバーサイズ */
	private static final Integer ERROR_COUNT_SCROLLBAR = 10;
	
	/**
	 * コンストラクタ.
	 */
	public RdirectionFileImportAction() {
		super();
	}

	/**
	 * 初期処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

//		RdirectionFileImportForm frm = (RdirectionFileImportForm) form;
			
		/* 初期検索有り */
		return mapping.findForward("success");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ActionForward upload(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		if (getLog().isDebugEnabled()) {
			getLog().debug("search");
		}
		
		RdirectionFileImportForm frm = (RdirectionFileImportForm)form;
		
		/* 入力状態のチェック */
		ActionMessages errorsInput = errorsCheck(frm);
		if (!errorsInput.isEmpty()) {
			saveErrors(request, errorsInput);
			return mapping.findForward("success");
		}
		
		/* ファイルアップロード開始 */
		//アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		
		//ファイル名のみセット
		frm.setUploadFileName(srhFilePath.getFileName());
		
		String fileName = frm.getUploadFileName();
		//拡張子チェック(.xlsでなければ弾く)
		if(!fileName.substring(fileName.length() - 4).toLowerCase().equals(".xls")){
			
			ActionMessages messages = new ActionMessages();
			//ファイルの拡張子が.xlsではありません。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.not.xls"));
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		
		// 存在チェック
		if (srhFilePath.getInputStream().read() == -1) {
			
			ActionMessages messages = new ActionMessages();
			// 対象ファイルが存在しないか、ファイルサイズが不正です。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.file"));
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		
		// ディレクトリがなければ作成
		File savDir = new File(rdirectionFileImportLogic.getTmpFilePath());
		if(!savDir.exists()){
			savDir.mkdirs();
		}		

		// ファイル・サイズのチェック
		String exFileName = rdirectionFileImportLogic.getTmpFilePath() + srhFilePath.getFileName();
		frm.setExFileName(exFileName);

		try {
			// getInputStreamメソッドを使用し、入力ストリームを取得
			InputStream is = srhFilePath.getInputStream();

			// 入力ストリームをバッファリング
			BufferedInputStream inBuffer = new BufferedInputStream(is);

			// ファイルのアップロード先を指定して、出力ストリームを生成
			FileOutputStream fos = new FileOutputStream(exFileName);

			// 出力ストリームをバッファリング
			BufferedOutputStream outBuffer = new BufferedOutputStream(fos);

			int contents = 0;

			// 入力データがなくなるまで入出力処理を実行
			while ((contents = inBuffer.read()) != -1) {
				outBuffer.write(contents);
			}

			outBuffer.flush();
			inBuffer.close();
			outBuffer.close();
			
		} catch (Exception e) {
			ActionMessages messages = new ActionMessages();
			// 対象ファイルが存在しないか、ファイルサイズが不正です。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.upload"));
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}

		return mapping.findForward("success");
	}

	/**
	 * 画面で確認終了後、ファイルの取込処理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getElsFileData(final ActionMapping mapping,
	final ActionForm form, final HttpServletRequest request,
	final HttpServletResponse response) throws Exception {
		
		RdirectionFileImportForm frm = (RdirectionFileImportForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		try{
			// アップロードファイルを展開
			ActionMessages messages = rdirectionFileImportLogic.getDataFromUploadFile(frm.getExFileName(), tantoCd, request);
			if (!messages.isEmpty()) {
				if (messages.size() > ERROR_COUNT_SCROLLBAR) {
					frm.setLogFlg("true");
				} else {
					frm.setLogFlg("false");
				}
				/* 処理エラーメッセージ */
				saveErrors(request, messages);
				return mapping.findForward("success");
			}
		}catch(RdirectionFileImportLogicException e){
			String[] replacements = e.getReplacements();
			if (replacements != null) {
				int len = replacements.length;
				for (int i = 0; i < len; i++) {
					String buf = getMessageResource(request, replacements[i]);
					if (StringUtils.isNotEmpty(buf)) {
						replacements[i] = buf;
					}
				}
			}
			// アップロードファイルの取込に失敗しました。取込ファイルの内容を確認してください。
			ActionMessages messages = new ActionMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getKey(), replacements));
			if (StringUtils.isNotEmpty(e.getModuleCd())) {
				// エラーログに出力する
				rdirectionFileImportLogic.outPutErrorLog(e.getModuleCd(), e.getInsideErrCd(), e.getInsideErrMsg(), tantoCd);
			}
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}finally{
			frm.setExFileName("");
		}
		frm.setLogFlg("false");
		/* メッセージ */
		saveMessage(request, "message.complete.upload");
		return mapping.findForward("success");
	}
	
	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}
	
	/**
	 * 入力項目チェック.
	 * @param frm form
	 * @param response response
	 * @return ActionMessages
	 */
	private ActionMessages errorsCheck(final RdirectionFileImportForm frm){
		ActionMessages messages = new ActionMessages();

		//アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		/* ファイルパス名が未入力 */	
		String tmpFilename = srhFilePath.getFileName();
		if (tmpFilename.equals("")){
			messages.add(ActionMessages.GLOBAL_MESSAGE,
				new ActionMessage("errors.file"));
		}
		return messages;
		
	}
	
	/* -------------------- setter -------------------- */

	/**
	 * rdirectionFileImportLogicを設定します。
	 * @param rdirectionFileImportLogic rdirectionFileImportLogic
	 */
	public void setRdirectionFileImportLogic(RdirectionFileImportLogic rdirectionFileImportLogic) {
		this.rdirectionFileImportLogic = rdirectionFileImportLogic;
	}

}
