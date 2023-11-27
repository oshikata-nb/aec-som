/*
 * Created on 2020/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.ordervenderlink;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport.OrderVenderLinkListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterList;
import com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster.OrderVenderMasterListPagerCondition;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 取引先グループ一覧 Actionクラス.
 * @author 
 */
public final class OrderVenderLinkListAction extends AbstractSearchAction {

	private OrderVenderLinkListLogic orderVenderLinkListLogic;

	private OrderVenderLinkListExcelDecorator orderVenderLinkListExcelDecorator;
	
	/**
	 * コンストラクタ.
	 */
	public OrderVenderLinkListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}
		
		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;
		
		// 得意先グループコンボボックス
		frm.setVenderGroupCombo(orderVenderLinkListLogic.createVenderGroupAllCombobox());

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<OrderVenderMasterList>());

		/* 検索条件を取得 */
		OrderVenderMasterListPagerCondition condition = (OrderVenderMasterListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhVenderGroupCd(frm.getSrhVenderGroupCd());
		condition.setSrhDeliveryConf(frm.getSrhDeliveryConf());
		condition.setSrhItemConf(frm.getSrhItemConf());

		/* 帳票(Excel)用に検索条件を保持 */
		OrderVenderLinkListConditionForReport reportCondition = new OrderVenderLinkListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(orderVenderLinkListLogic.getList(condition));

		return mapping.findForward("success");
	}

	/**
	 * クリア処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * EXCEL作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;
		
//		boolean isSelected = false;
		String venderGroupCd = null;

		// ExcelダウンロードフラグOFF
		frm.setExcelDownloadFlg(false);
		
		try{
			
			int selectRow = frm.getSearchList().get(0).getCheckRowNo();
			
			if( selectRow == 0){
				// 未選択の場合、ゼロを返す。
				saveError(request,"errors.ordervenderlink.selected.radiobottun");
				return mapping.findForward("success");
				
			}else{
				selectRow = selectRow-1;
			}
			
			
			// ラジオボタンの選択位置はリストの最初に格納されている。それを元にグループを取得する。
			venderGroupCd = frm.getSearchList().get(selectRow).getVenderGroupCd();
			
		}catch(ArrayIndexOutOfBoundsException e){
			// 一つも選択されていない場合、エラーメッセージを表示
			saveError(request,"errors.ordervenderlink.selected.radiobottun");
			return mapping.findForward("success");
			
		}
		
//		
//		
//		
//		int i = 1;//ループ回数
//		int rowNo = frm.getSearchList().get(0).getCheckRowNo();//選択してるラジオボタンの行番号
//		for (OrderVenderMasterList bean : frm.getSearchList()) {
//			if (rowNo == i) {
//				//選択行の場合、決定し抜ける
//				isSelected = true;
//				// 処理を行う得意先グループ番号を取得
//				venderGroupCd = bean.getVenderGroupCd();
//				break;
//			} 
//			i++;
//			continue;
//		}
//		if (!isSelected) {
//			// 一つも選択されていない場合
//			saveError(request,"errors.ordervenderlink.selected.radiobottun");
//			return mapping.findForward("success");
//		}

		FileDownloadInfo info = null;

		/* 出荷伝票出力処理 */
		info = orderVenderLinkListExcelDecorator.createReport(venderGroupCd);
		
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}
	
	/**
	 * アップロード用EXCELダウンロード処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward download(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;

		String venderGroupCd = null;

		// ExcelダウンロードフラグOFF
		frm.setExcelDownloadFlg(false);

		FileDownloadInfo info = null;

		/* エクセル作成処理 */
		info = orderVenderLinkListExcelDecorator.createReport(venderGroupCd);
		
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY, info);

		// ExcelダウンロードフラグＯＮ
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 新規処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		return mapping.findForward("newPage");
	}
	
	/**
	 * アップロード処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward upload(final ActionMapping mapping,
	                            final ActionForm form,
	                            final HttpServletRequest request,
	                            final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("upload.");
		}
		
		OrderVenderLinkListForm frm = (OrderVenderLinkListForm) form;
		
		/* 入力状態のチェック */
		ActionMessages errorsInput = errorsCheck(frm);
		if (!errorsInput.isEmpty()) {
			saveErrors(request, errorsInput);
			return mapping.findForward("success");
		}
		
		// アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		
		// 存在チェック
		if (srhFilePath.getInputStream().read() == -1) {
			
			ActionMessages messages = new ActionMessages();
			// 対象ファイルが存在しないか、ファイルサイズが不正です。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.file"));
			saveErrors(request, messages);
			return mapping.findForward("success");
		}
		
		// ディレクトリがなければ作成
		File savDir = new File(orderVenderLinkListLogic.getTmpFilePath());
		if(!savDir.exists()){
			savDir.mkdirs();
		}
		
		// ファイル・サイズのチェック
		String exFileName = orderVenderLinkListLogic.getTmpFilePath()+srhFilePath.getFileName();

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
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.file"));
			saveErrors(request, messages);
			return mapping.findForward("success");
		}			
		
		// 端数処理
		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		try {
			// アップロードファイルを明細に展開する。
			ActionMessages messages = orderVenderLinkListLogic.getDataFromUploadFile(exFileName, checker, getLoginInfo(request).getTantoCd());
			if (!messages.isEmpty()) {
				/* 処理エラーメッセージ */
				saveErrors(request, messages);
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			
			ActionMessages messages = new ActionMessages();
			// 対象ファイルが存在しないか、ファイルサイズが不正です。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.ordervenderlink.insert"));
			
			saveErrors(request, messages);
			return mapping.findForward("success");
		}			
		
		//　連携データのアップロードが完了しました。
		saveMessage(request, "message.ordervenderlink.upload");
		return mapping.findForward("reSearch");
	
	}
	
	/**
	 * 入力項目チェック.
	 * 
	 * @param frm
	 *            frm
	 * @param response
	 *            response
	 * @return ActionMessages
	 */
	private ActionMessages errorsCheck(final OrderVenderLinkListForm frm) {
		ActionMessages messages = new ActionMessages();

		// アクセスメソッドを使用してFormFileオブジェクトの取得
		FormFile srhFilePath = frm.getUploadFile();
		/* ファイルパス名が未入力 */
		String tmpFileName = srhFilePath.getFileName();
		if (tmpFileName.equals("")) {
			// 対象ファイルが存在しないか、ファイルサイズが不正です。
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.file"));
		}
		
		return messages;
		
	}

	/* -------------------- setter -------------------- */

	/**
	 * orderVenderLinkListExcelDecoratorを設定します。
	 * @param orderVenderLinkListExcelDecorator orderVenderLinkListExcelDecorator
	 */
	public void setOrderVenderLinkListExcelDecorator(
			final OrderVenderLinkListExcelDecorator orderVenderLinkListExcelDecorator) {
		this.orderVenderLinkListExcelDecorator = orderVenderLinkListExcelDecorator;
	}

	/**
	 * orderVenderLinkListLogicを設定します。
	 * @param orderVenderLinkListLogic orderVenderLinkListLogic
	 */
	public void setOrderVenderLinkListLogic(final OrderVenderLinkListLogic orderVenderLinkListLogic) {
		this.orderVenderLinkListLogic = orderVenderLinkListLogic;
	}
	
}
