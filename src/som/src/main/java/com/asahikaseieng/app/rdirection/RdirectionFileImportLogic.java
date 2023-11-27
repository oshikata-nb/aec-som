/*
 * Created on 2022/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;

/**
 * 製造実績取込詳細ロジック interface.
 * @author 
 */
public interface RdirectionFileImportLogic {
	
	/**
	 * TMPファイルパス取得
	 */
	String getTmpFilePath();

	/**
	 * 担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getLoginEntity(final String tantoCd);
	
	/**
	 * ロケーション検索.
	 * ##ここにメソッドの説明を書いてください##
	 * @param locationCd ロケーションコード
	 * @return Location
	 */
	Location getLocationEntity(final String locationCd);
	
	/**
	 * EXCELからデータを読み込む.
	 * @param srhFilePath Excelファイルパス
	 * @param tantoCd 担当者ID
	 * @param request HttpServletRequest
	 * @return Actionメッセージ
	 */
	ActionMessages getDataFromUploadFile(final String srhFilePath, final String tantoCd,final HttpServletRequest request) throws IOException,Exception;

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd, final String strErrorMsg, final String tantoCd) throws Exception;

}
