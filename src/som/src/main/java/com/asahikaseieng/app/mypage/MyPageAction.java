/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.assistance.authority.AuthorityLogic;
import com.asahikaseieng.dao.entity.gadget.GadgetLink;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecStackTrace;

/**
 * マイページ Actionクラス.
 * @author jbd
 */
public final class MyPageAction extends AbstractAction {

	private MyPageLogic myPageLogic;

	/* 権限のアシスタントロジック */
	private AuthorityLogic authorityLogic;

	/**
	 * コンストラクタ.
	 */
	public MyPageAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		MyPageForm frm = (MyPageForm) form;

		/* ユーザー情報をセッションから取り出す */
		String tantoCd = getLoginInfo(request).getTantoCd();
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		BigDecimal postId = getLoginInfo(request).getRoleId();

		/* ガジェット権限リストを取得 */
		List<Authority> authList = authorityLogic.getGadgetAuthorityList(
			tantoCd, organizationCd, postId);

		/* 現在の権限に応じて表示すべきガジェット情報を更新 */
		myPageLogic.updateGadgetConfig(tantoCd, authList);

		/* データを取得しフォームにセット */
		frm.setGadgetList(myPageLogic.getGadgetList(tantoCd));

		// ガジェット表示用のリンクデータ取得、設定

		for (int i = 0; i < frm.getGadgetList().size(); i++) {
			GadgetConfig confList = frm.getGadgetList().get(i);
			// SQL文取得

			String sqlCd = confList.getGadget().getSqlCd();

			List<GadgetLink> linkList = null;
			if (StringUtils.isEmpty(sqlCd)) {
				// SQL文がNULLの場合

				linkList = new ArrayList<GadgetLink>();
				confList.setLinkList(linkList); // リンクデータ
				confList.setDataCnt(linkList.size()); // リンクデータ件数
			} else {
				// リンクデータ検索SQL実行

				linkList = myPageLogic.getGadgetLinkList(sqlCd, tantoCd);
				confList.setLinkList(linkList); // リンクデータ
				confList.setDataCnt(linkList.size()); // リンクデータ件数

				// 最大表示件数を超えた場合

				int maxLine = confList.getGadget().getMaxLine().intValue();
				if (maxLine != 0 && maxLine < confList.getDataCnt()) {
					// 最大表示件数分のみデータ設定

					linkList = linkList.subList(0, confList.getGadget()
							.getMaxLine().intValue());
					confList.setLinkList(linkList); // リンクデータ
				}
			}
		}

		/* 後で権限確認で利用する為に、ガジェットリストをセッションに格納 */
		request.getSession(false).setAttribute(Constants.GADGET_KEY,
			frm.getGadgetList());

		return mapping.findForward("success");
	}

	/**
	 * ガジェットの移動時DB更新処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public ActionForward regist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		MyPageForm frm = (MyPageForm) form;

		/* ユーザー情報をセッションから取り出す */
		LoginInfo info = getLoginInfo(request);

		try {
			myPageLogic.registGadget(frm.getLeftIds(), frm.getRightIds(), info
					.getTantoCd(), frm.getLeftStatus(), frm.getRightStatus());
		} catch (Exception e) { // ajaxによるリクエストなので例外に対する特別な対処を行う
			// スタックトレースを出力
			getLog().error(AecStackTrace.getStackTrace(e));

			return mapping.findForward("global.gadget.move.error");
		}
		return mapping.findForward("global.empty");
	}

	/* -------------------- setter -------------------- */

	/**
	 * myPageLogicを設定します。
	 * @param myPageLogic MyPageLogic
	 */
	public void setMyPageLogic(final MyPageLogic myPageLogic) {
		this.myPageLogic = myPageLogic;
	}

	/**
	 * authorityLogicを設定します。
	 * @param authorityLogic AuthorityLogic
	 */
	public void setAuthorityLogic(final AuthorityLogic authorityLogic) {
		this.authorityLogic = authorityLogic;
	}

}
