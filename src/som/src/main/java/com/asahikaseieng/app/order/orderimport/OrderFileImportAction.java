/*
 * Created on 2020/11/26
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.mozilla.universalchardet.UniversalDetector;

import com.asahikaseieng.dao.nonentity.orderimportfileloglist.OrderImportFileLogList;
import com.asahikaseieng.dao.nonentity.orderimporttemplist.OrderImportTempList;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 受注ファイル取込(ポップアップ) Actionクラス.
 * @author 
 */
public final class OrderFileImportAction extends AbstractAction {

	/** 受注ファイル取込ロジッククラス */
	private OrderFileImportLogic orderFileImportLogic;
	
	/** Exception時出力用エラーメッセージ */
	public ActionMessages exceptionErrorMsg;

	/**
	 * コンストラクタ.
	 */
	public OrderFileImportAction() {
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

		OrderFileImportForm frm = (OrderFileImportForm) form;
			
		// 得意先グループコンボボックス
		frm.setVenderGroupCombo(orderFileImportLogic.createVenderGroupCombobox());

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

		OrderFileImportForm frm = (OrderFileImportForm) form;
		
		//CSVデータの文字コード
		String encType = null;
		
		/* 入力状態のチェック */
		ActionMessages errorsInput = errorsCheck(frm);
		if (!errorsInput.isEmpty()) {
			saveErrors(request, errorsInput);
			return mapping.findForward("success");
		}
		
		/* ファイルアップロード開始 */
		//アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		
		//文字コード判別
		encType = getEncType(srhFilePath.getInputStream());
		
		frm.setEncType(encType);
		
		//ファイル名のみセット
		frm.setUploadFileName(srhFilePath.getFileName());
		
		String fileName = frm.getUploadFileName();
		//拡張子チェック(.csvでなければ弾く)
		if(!fileName.substring(fileName.length() - 4).equals(".csv")){
			
			ActionMessages messages = new ActionMessages();
			//ファイルの拡張子が.csvではありません。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderfile.not.csv"));
			
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
		File savDir = new File(orderFileImportLogic.getTmpFilePath());
		if(!savDir.exists()){
			savDir.mkdirs();
		}
		
		// ファイル・サイズのチェック
		String exFileName = orderFileImportLogic.getTmpFilePath() + srhFilePath.getFileName();
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
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.orderimport.orderfile.upload"));
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		
		//入力ストリーム再取得
		InputStream is = srhFilePath.getInputStream();
		
		String md5 = getMd5Checksum(is);
		frm.setMd5(md5);
		//取込チェック
		OrderImportTempList importInfo = checkAlreadyImportFile(frm);
		
		//過去1か月間に同じファイルが取り込まれていないかチェックする
		if(importInfo != null){
			frm.setCheckFileImpFlg(1);
		} else{
			frm.setCheckFileImpFlg(0);
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
	public ActionForward getCsvFileData(final ActionMapping mapping,
	final ActionForm form, final HttpServletRequest request,
	final HttpServletResponse response) throws Exception {
		
		OrderFileImportForm frm = (OrderFileImportForm) form;
		
		ActionMessages errorMsg = new ActionMessages();
		
		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();
		String encType = frm.getEncType();
		String exFileName = frm.getExFileName();
		
		try {
			// CSVデータをリストに格納
			List<List<String>> csvData = orderFileImportLogic.getRows(exFileName, encType);
			/** 登録処理 **/
			// CSVデータを一時取込テーブルに格納
			errorMsg = orderFileImportLogic.setTempTable(csvData, frm, tantoCd);
			if (!errorMsg.isEmpty()) {
				/* 処理エラーメッセージ */
				saveErrors(request, errorMsg);
				return mapping.findForward("success");
			}
		} catch (OptimisticLockRuntimeException e) {
			// エラーメッセージを登録する // 他のユーザにより先に更新されました。
			saveError(request, "errors.optimisticlock.data");
			//プロシージャ実行ログを取得
			getImportLog(frm, tantoCd);
			// 再検索しない
			return mapping.getInputForward();
		} catch (Exception e) {
			/* 処理エラーメッセージ */
			saveError(request, "errors.insert.data");
			//プロシージャ実行ログを取得
			getImportLog(frm, tantoCd);
			
			if(frm.getLogFlg().equals("true")){
				return mapping.findForward("success");
			}else{
				throw e;
			}
			
		}finally{
			frm.setExFileName(null);
		}
		
		//プロシージャ実行ログを取得
		getImportLog(frm, tantoCd);
		
		
		/* メッセージ */
		saveMessage(request, "message.complete.upload");
		
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * 読込ファイルのMD5チェックサム値と過去1ヵ月以内に読み込まれたファイルのMD5チェックサム値にかぶりが無いかチェック
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkMd5List(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		OrderFileImportForm frm = (OrderFileImportForm) form;
		
		//アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		
		//入力ストリーム再取得
		InputStream is = srhFilePath.getInputStream();
		
		String md5 = getMd5Checksum(is);
		frm.setMd5(md5);
		//取込チェック
		OrderImportTempList importInfo = checkAlreadyImportFile(frm);
		
		//過去1か月間に同じファイルが取り込まれていないかチェックする
		if(importInfo != null){
			frm.setCheckFileImpFlg(1);
		} else{
			frm.setCheckFileImpFlg(0);
		}
		
		return mapping.findForward("success");
	}
	
	/**
	 * 入力項目チェック.TODO
	 * @param frm form
	 * @param response response
	 * @return ActionMessages
	 */
	private ActionMessages errorsCheck(final OrderFileImportForm frm){
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
	
	/**
	 * 文字コード取得
	 * @param inputstream
	 * @return encode String
	 */
	private String getEncType(InputStream is) throws IOException{
		byte[] buf = new byte[4096];
		 // 文字コード判定ライブラリの実装
		UniversalDetector detector = new UniversalDetector(null);
	
		// 判定開始
		int nread;
		while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		// 判定終了
		detector.dataEnd();
	
		// 文字コード判定
		String encType = detector.getDetectedCharset();
		
		//SHIFT_JIS判定時に㈱等の機種依存文字が文字化けするのでMS932に変更
		try{
			if(encType.compareTo("SHIFT_JIS") == 0){
				encType = "MS932";
			}
		} catch(NullPointerException e){
			return null;
		}
	
		// 判定の初期化
		detector.reset();

		return encType;
	}
	
	/**
	 * プロシージャ実行ログを取得してセットする
	 * @param frm OrderFileImportForm
	 */
	private void getImportLog(OrderFileImportForm frm, String tantoCd){
		// ログ情報取得
		List<OrderImportFileLogList> logList = orderFileImportLogic.getImportLog(tantoCd);

		if (0 < logList.size()) {
			// 改行コード
			String crlf = System.getProperty("line.separator");

			for (OrderImportFileLogList beanLog : logList) {
				if (beanLog.getFlg().compareTo(BigDecimal.ONE) == 0
						&& StringUtils.isNotEmpty(beanLog.getMsg())) {
					String message = AecDateUtils.formatStringFromTimestamp(beanLog.getInputDate(), "yyyy/MM/dd HH:mm:ss") + " " + beanLog.getRowNo() + "行目　"+ beanLog.getMsg();
					if (StringUtils.isEmpty(frm.getLogMsg())) {
						frm.setLogMsg(message);
					} else {
						frm.setLogMsg(frm.getLogMsg() + crlf + message);
					}
				}
			}
			// ログフラグ設定
			frm.setLogFlg("true");
		}
	}
	
	/**
	 * 
	 * ログのチェックフラグを1に変更する
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateCheckFlg(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		ActionMessages errorMsg = new ActionMessages();
		
		OrderFileImportForm frm = (OrderFileImportForm) form;
		
		/* ログイン情報取得*/
		String tantoCd = getLoginInfo(request).getTantoCd();
		
		try{
			errorMsg = orderFileImportLogic.updateCheckedFlg(tantoCd);
			if(!errorMsg.isEmpty()){
				saveErrors(request, errorMsg);
			}
		} catch(Exception e){
			saveError(request, "errors.regist.data");
			saveErrors(request, errorMsg);
			return mapping.findForward("success");
		}
		
		frm.setLogFlg("false");
		frm.setLogMsg(null);
		
		/* メッセージ */
		saveMessage(request, "message.complete.regist");
		
		return mapping.findForward("success");
	}
	

	
	/**
	 * 
	 * インポートするファイルのmd5チェックサム値を取得する(重複取込確認用)
	 * @param is
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	private String getMd5Checksum(InputStream is) throws NoSuchAlgorithmException, IOException{
		MessageDigest digest = MessageDigest.getInstance("MD5");

		DigestInputStream input = new DigestInputStream(is, digest);
		while (input.read() != -1) {
		}
		StringBuilder hash = new StringBuilder();
		for (byte b : digest.digest()) {
			hash.append(String.format("%02x", b));
		}
		return hash.toString();
		
	}
	
	/**
	 * 
	 * 過去1か月間に同じファイルが取り込まれているかMD5値を使用してチェックする
	 * @return null:過去一か月間に取込なし nullではない:ファイルの読み込み日時
	 */
	private OrderImportTempList checkAlreadyImportFile(OrderFileImportForm frm) throws Exception{
		
		OrderImportTempList checksum = orderFileImportLogic.getMd5Checksum(frm);
		
		//過去一か月以内に同じMD5チェックサム値のファイルを取り込んでいたらtrue
		if(checksum != null){
			return checksum;
		}
		
		//なければnull
		return null;
		
	}
	
	/* -------------------- setter -------------------- */

	/**
	 * orderFileImportLogicを設定します。
	 * @param orderFileImportLogic orderFileImportLogic
	 */
	public void setOrderFileImportLogic(OrderFileImportLogic orderFileImportLogic) {
		this.orderFileImportLogic = orderFileImportLogic;
	}
	
}
