/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.vender.VenderListLogic;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecTextUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 相殺グループマスタ詳細 Actionクラス.
 * @author TanakaSatoru
 */
public final class OffsetGroupDetailAction extends AbstractAction {

	private OffsetGroupDetailLogic offsetGroupDetailLogic;

	private VenderListLogic venderListLogic;

	/**
	 * コンストラクタ.
	 */
	public OffsetGroupDetailAction() {
		super();
	}

	/**
	 * 相殺グループマスタ詳細画面表示処理
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

		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET_GROUP,
			Constants.TAB_ID_OFFSET_GROUP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		List<OffsetGroupDetail> list = offsetGroupDetailLogic.getEntity(frm
				.getOffsetGroupCd());

		if (list.isEmpty()) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.init");
			return mapping.findForward("back");

		} else {
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, list.get(0));

			setComboBox(frm, list);
			frm.setVenderName(null);
			frm.setInsertFlg(0);

		}

		return mapping.findForward("success");
	}

	/**
	 * 新規ボタン押下処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET_GROUP,
			Constants.TAB_ID_OFFSET_GROUP_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		setComboBoxNew(frm);
		frm.setInsertFlg(1);

		return mapping.findForward("success");
	}

	/**
	 * 相殺グループマスタ登録処理
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
		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		try {
			/* 基本情報 */
			String tantoCd = getLoginInfo(request).getTantoCd();
			/* 更新処理を実行 */
			offsetGroupDetailLogic.update(frm, tantoCd);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 相殺グループマスタ登録処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		/* 登録対象データの存在チェック */
		List<OffsetGroupDetail> list = offsetGroupDetailLogic.getEntity(frm
				.getOffsetGroupCd());

		if (!list.isEmpty()) {
			saveError(request, "errors.duplicate.data");
			return mapping.getInputForward();

		} else {
			frm.setRegistDate(AecDateUtils.getCurrentTimestamp());
			String tantoCd = getLoginInfo(request).getTantoCd();
			frm.setRegistTantoCd(tantoCd);

			try {
				/* 更新処理を実行 */
				offsetGroupDetailLogic.insert(frm, tantoCd);

			} catch (NoDataException e) {
				saveError(request, "errors.offset.vender");
				return mapping.getInputForward();

			}

		}

		/* メッセージ */
		saveMessage(request, "message.complete.insert");
		frm.setInsertFlg(0);

		return mapping.findForward("back");

	}

	/**
	 * 相殺グループマスタ削除処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		/* 削除対象データ、更新対象データを作成する:プライマリキー */
		List<OffsetGroupDetail> list = offsetGroupDetailLogic.getEntity(frm
				.getOffsetGroupCd());

		if (list.isEmpty()) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();

		} else {
			/* 削除処理を実行 */
			offsetGroupDetailLogic.delete(list.get(0).getOffsetGroupCd());

		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

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
	 * 請求先／支払先検索処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		OffsetGroupDetailForm frm = (OffsetGroupDetailForm) form;

		// 選択先コンボ
		List<ComboBoxItems> venderListSaki = new ArrayList<ComboBoxItems>();

		// 現在のリストを設定しなおす
		HashMap<String, String> map = new HashMap<String, String>();
		if (frm.getVenderCdSaki() != null) {
			String[] venderSaki = frm.getVenderCdSaki();
			for (int i = 0; i < venderSaki.length; i++) {
				int firstColon = venderSaki[i].indexOf(":");
				int lastColon = venderSaki[i].lastIndexOf(":");
				String venderDivision = venderSaki[i].substring(0, firstColon);
				String venderCd = venderSaki[i].substring(firstColon + 1,
					lastColon);
				String venderName = venderSaki[i].substring(lastColon + 1);
				// マップに設定
				map.put(venderDivision + ":" + venderCd, venderDivision + ":"
						+ venderCd);
				ComboBoxItems combo = new ComboBoxItems();
				// 請求先/支払先ID＋請求先/支払先名称設定(Value値)
				combo.setValues(venderDivision + ":" + venderCd + ":"
						+ venderName);
				// 請求先/支払先ID＋請求先/支払先名称設定(ラベル)
				combo.setLabales(venderDivision + ":" + venderCd + ":"
						+ venderName);
				venderListSaki.add(combo);
			}
		}

		// 選択元コンボ
		List<ComboBoxItems> venderListMoto = new ArrayList<ComboBoxItems>();

		try {
			// 検索条件に対する請求先/支払先の取得
			List<VenderList> venderList = venderListLogic
					.getSearchNameList(AecTextUtils.likeFilter(frm
							.getVenderName()));

			// 請求先/支払先ID、請求先/支払先名称を取得して配列に設定
			for (int i = 0; i < venderList.size(); i++) {
				VenderList vender = venderList.get(i);
				String venderDivision = vender.getVenderDivision();
				String venderCd = vender.getVenderCd();
				String venderName = vender.getVenderName1();

				ComboBoxItems combo = new ComboBoxItems();
				// 請求先/支払先ID＋請求先/支払先名称設定(Value値)
				combo.setValues(venderDivision + ":" + venderCd + ":"
						+ venderName);
				// 請求先/支払先ID＋請求先/支払先名称設定(ラベル)
				combo.setLabales(venderDivision + ":" + venderCd + ":"
						+ venderName);

				if (map.size() == 0) {
					// 選択元コンボ
					venderListMoto.add(combo);
				} else {
					if (map.get(venderDivision + ":" + venderCd) == null) {
						// 選択元コンボ
						venderListMoto.add(combo);
					}
				}
			}
			frm.setVenderMotoList(venderListMoto);
			frm.setVenderSakiList(venderListSaki);

		} catch (NoDataException e) {
			frm.setVenderMotoList(venderListMoto);
			frm.setVenderSakiList(venderListSaki);

		}

		return mapping.findForward("success");
	}

	/**
	 * 請求先/支払先のコンボボックス設定(変更画面)
	 * 
	 * @param frm 詳細画面Form
	 * @param list 検索結果リスト
	 * @return List<ComboBoxItems> のコンボボックス
	 * @throws NoDataException データ無しの例外
	 */
	private void setComboBox(final OffsetGroupDetailForm frm,
			final List<OffsetGroupDetail> list) throws NoDataException {

		// 複数 格納
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				String venderDivision = list.get(i).getVenderDivision();
				String venderCd = list.get(i).getVenderCd();
				map.put(venderDivision + ":" + venderCd, venderDivision + ":"
						+ venderCd);
			}
		}
		// 請求先/支払先一覧取得
		List<VenderList> venderList = venderListLogic.getVenderList();

		// 選択元コンボ
		List<ComboBoxItems> venderListMoto = new ArrayList<ComboBoxItems>();

		// 選択先コンボ
		List<ComboBoxItems> venderListSaki = new ArrayList<ComboBoxItems>();

		// 請求先/支払先ID、請求先/支払先名称を取得して配列に設定
		for (int i = 0; i < venderList.size(); i++) {
			VenderList vender = venderList.get(i);
			String venderDivision = vender.getVenderDivision();
			String venderCd = vender.getVenderCd();
//			String venderName = vender.getVenderName1();
			String venderShortedName = vender.getVenderShortedName();

			ComboBoxItems combo = new ComboBoxItems();
			// 請求先/支払先ID＋請求先/支払先名称設定(Value値)
			combo.setValues(venderDivision + ":" + venderCd + ":" + venderShortedName);
			// 請求先/支払先ID＋請求先/支払先名称設定(ラベル)
			combo
					.setLabales(venderDivision + ":" + venderCd + ":"
							+ venderShortedName);

			if (map.get(venderDivision + ":" + venderCd) != null) {
				// 選択先コンボ
				venderListSaki.add(combo);
			} else {
				// 選択先コンボ
				venderListMoto.add(combo);
			}
		}
		frm.setVenderMotoList(venderListMoto);
		frm.setVenderSakiList(venderListSaki);
	}

	/**
	 * 請求先/支払先のコンボボックス設定(新規画面)
	 * 
	 * @param frm 詳細画面Form
	 * @param list 検索結果リスト
	 * @return List<ComboBoxItems> のコンボボックス
	 * @throws NoDataException データ無しの例外
	 */
	private void setComboBoxNew(final OffsetGroupDetailForm frm)
			throws NoDataException {

		// 選択元コンボ
		List<ComboBoxItems> venderListMoto = new ArrayList<ComboBoxItems>();
		// 選択先コンボ
		List<ComboBoxItems> venderListSaki = new ArrayList<ComboBoxItems>();

		try {
			// 請求先/支払先の取得
			List<VenderList> venderList = venderListLogic.getVenderList();

			// 請求先/支払先ID、請求先/支払先名称を取得して配列に設定
			for (int i = 0; i < venderList.size(); i++) {
				VenderList vender = venderList.get(i);
				String venderDivision = vender.getVenderDivision();
				String venderCd = vender.getVenderCd();
				String venderName = vender.getVenderName1();

				ComboBoxItems combo = new ComboBoxItems();
				// 請求先/支払先ID＋請求先/支払先名称設定(Value値)
				combo.setValues(venderDivision + ":" + venderCd + ":"
						+ venderName);
				// 請求先/支払先ID＋請求先/支払先名称設定(ラベル)
				combo.setLabales(venderDivision + ":" + venderCd + ":"
						+ venderName);

				// 選択元コンボ
				venderListMoto.add(combo);
			}
			frm.setVenderMotoList(venderListMoto);
			frm.setVenderSakiList(venderListSaki);

		} catch (NoDataException e) {
			frm.setVenderMotoList(venderListMoto);
			frm.setVenderSakiList(venderListSaki);

		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * VenderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}

	/**
	 * offsetGroupDetailLogicを設定します。
	 * @param offsetGroupDetailLogic offsetGroupDetailLogic
	 */
	public void setOffsetGroupDetailLogic(
			final OffsetGroupDetailLogic offsetGroupDetailLogic) {
		this.offsetGroupDetailLogic = offsetGroupDetailLogic;
	}
}
