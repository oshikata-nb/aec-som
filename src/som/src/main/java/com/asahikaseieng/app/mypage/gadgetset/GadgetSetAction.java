/*
 * Created on 2007/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mypage.gadgetset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.mypage.MyPageLogic;
import com.asahikaseieng.assistance.authority.AuthorityLogic;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 組織マスタ詳細 Actionクラス.
 * @author TanakaSatoru
 */
public final class GadgetSetAction extends AbstractAction {

	private AuthorityLogic authorityLogic;

	private MyPageLogic myPageLogic;

	private GadgetSetLogic gadgetSetLogic;

	/**
	 * コンストラクタ.
	 */
	public GadgetSetAction() {
		super();
	}

	/**
	 * ガジェット設定画面 表示処理.
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

		GadgetSetForm frm = (GadgetSetForm) form;

		// ユーザー情報をセッションから取り出す

		String tantoCd = getLoginInfo(request).getTantoCd();
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		BigDecimal postId = getLoginInfo(request).getRoleId();

		// 閲覧可能なガジェットリストを取得

		List<Authority> gadgetList = authorityLogic.getGadgetAuthorityList(
			tantoCd, organizationCd, postId);

		// 担当者に紐づくガジェット設定取得

		List<GadgetConfig> tantoGadgetList = myPageLogic.getGadgetList(tantoCd);

		// ガジェット設定
		setComboBox(frm, gadgetList, tantoGadgetList);

		return mapping.findForward("success");
	}

	/**
	 * ガジェット設定NEWテーブル登録処理(DELETE/INSERT).
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}

		GadgetSetForm frm = (GadgetSetForm) form;

		// ユーザー情報をセッションから取り出す

		String tantoCd = getLoginInfo(request).getTantoCd();

		List<GadgetConfig> list = new ArrayList<GadgetConfig>();

		// １列目のガジェットを設定

		String[] gadgetId1 = frm.getGadgetId1();
		if (gadgetId1 != null) {
			for (int i = 0; i < gadgetId1.length; i++) {
				String[] id = gadgetId1[i].split("_", 0);
				GadgetConfig conf = new GadgetConfig();
				conf.setTantoCd(tantoCd); // 担当者コード

				conf.setGadgetId(id[0]); // ガジェットID
				conf.setMenuId(new BigDecimal(id[1])); // メニューID
				conf.setTabId(new BigDecimal(id[2])); // タブID
				conf.setLaneNo(Constants.FIRST_LANE); // レーン番号
				conf.setVerticalOrder(new BigDecimal(i)); // 順番
				// 表示状態

				if (id.length < 4) {
					conf.setSlideStatus(Constants.SLIDE_STATUS_MAX);
				} else {
					conf.setSlideStatus(new BigDecimal(id[3]));
				}
				list.add(conf);
			}
		}

		// ２列目のガジェットを設定

		String[] gadgetId2 = frm.getGadgetId2();
		if (gadgetId2 != null) {
			for (int i = 0; i < gadgetId2.length; i++) {
				String[] id = gadgetId2[i].split("_", 0);
				GadgetConfig conf = new GadgetConfig();
				conf.setTantoCd(tantoCd); // 担当者コード

				conf.setGadgetId(id[0]); // ガジェットID
				conf.setMenuId(new BigDecimal(id[1])); // メニューID
				conf.setTabId(new BigDecimal(id[2])); // タブID
				conf.setLaneNo(Constants.SECOND_LANE); // レーン番号
				conf.setVerticalOrder(new BigDecimal(i)); // 順番
				// 表示状態

				if (id.length < 4) {
					conf.setSlideStatus(Constants.SLIDE_STATUS_MAX);
				} else {
					conf.setSlideStatus(new BigDecimal(id[3]));
				}
				list.add(conf);
			}
		}

		try {
			// ガジェット設定NEWテーブル 登録処理実行(DELETE/INSERT)
			gadgetSetLogic.update(tantoCd, list);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * ガジェットのコンボボックス設定
	 * 
	 * 
	 * @param frm 画面Form
	 * @param gadgetList 閲覧可能なガジェットリスト
	 * @param tantoGadgetList 担当者に紐づくガジェット設定リスト
	 * 
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private void setComboBox(final GadgetSetForm frm,
			final List<Authority> gadgetList,
			final List<GadgetConfig> tantoGadgetList) throws NoDataException {

		// 担当者のロールに紐づくガジェットリスト格納

		SortedMap<String, Authority> map = new TreeMap<String, Authority>(
				Authority.createSortComparator());
		for (int i = 0; i < gadgetList.size(); i++) {
			Authority bean = gadgetList.get(i);
			String gadgetId = bean.getGadgetId(); // ガジェットID
			String menuId = bean.getMenuId().toString(); // メニューID
			String tabId = bean.getTabId().toString(); // タブID
			String key = gadgetId + "_" + menuId + "_" + tabId;
			map.put(key, bean);
		}

		// １列目コンボ
		List<ComboBoxItems> gadget1 = new ArrayList<ComboBoxItems>();
		// ２列目コンボ

		List<ComboBoxItems> gadget2 = new ArrayList<ComboBoxItems>();

		// 担当者に紐づくガジェット設定を１列目、２列目に分けてコンボに設定

		for (int i = 0; i < tantoGadgetList.size(); i++) {
			GadgetConfig conf = tantoGadgetList.get(i);
			String gadgetId = conf.getGadgetId(); // ガジェットID
			String menuId = conf.getMenuId().toString(); // メニューID
			String tabId = conf.getTabId().toString(); // タブID
			// 表示状態

			String slideStatus = "";
			if (conf.getSlideStatus() == null) {
				slideStatus = "0";
			} else {
				slideStatus = conf.getSlideStatus().toString();
			}

			// key、value
			String key = gadgetId + "_" + menuId + "_" + tabId;
			String value = gadgetId + "_" + menuId + "_" + tabId + "_"
					+ slideStatus;

			ComboBoxItems combo = new ComboBoxItems();
			combo.setValues(value); // Value値

			if (map.get(key) != null) {
				Authority auth = map.get(key);

				String title = auth.getTitle(); // タイトル
				combo.setLabales(title); // ラベル値

				if (conf.getLaneNo().compareTo(Constants.FIRST_LANE) == 0) {
					// １列目コンボ

					gadget1.add(combo);
				} else {
					// ２列目コンボ

					gadget2.add(combo);
				}

				// すでにガジェット設定にある場合はMAPから削除
				map.remove(key);
			}
		}

		// 選択可能ガジェットコンボ

		List<ComboBoxItems> gadgetMoto = new ArrayList<ComboBoxItems>();

		// 設定可能なガジェットを選択可能ガジェットコンボに設定

		Set<String> keys = map.keySet();
		for (String key : keys) {
			Authority bean = map.get(key);

			// 選択可能ガジェットコンボ

			ComboBoxItems combo = new ComboBoxItems();
			String title = bean.getTitle(); // タイトル
			combo.setLabales(title); // ラベル値
			combo.setValues(key); // Value値
			gadgetMoto.add(combo);
		}

		frm.setGadgetMoto(gadgetMoto);
		frm.setGadget1(gadget1);
		frm.setGadget2(gadget2);
	}

	/* -------------------- setter -------------------- */

	/**
	 * authorityLogicを設定します。
	 * 
	 * @param authorityLogic AuthorityLogic
	 */
	public void setAuthorityLogic(final AuthorityLogic authorityLogic) {
		this.authorityLogic = authorityLogic;
	}

	/**
	 * myPageLogicを設定します。
	 * 
	 * @param myPageLogic MyPageLogic
	 */
	public void setMyPageLogic(final MyPageLogic myPageLogic) {
		this.myPageLogic = myPageLogic;
	}

	/**
	 * gadgetSetLogicを設定します。
	 * 
	 * @param gadgetSetLogic GadgetSetLogic
	 */
	public void setGadgetSetLogic(final GadgetSetLogic gadgetSetLogic) {
		this.gadgetSetLogic = gadgetSetLogic;
	}

}
