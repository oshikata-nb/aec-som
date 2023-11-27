/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationlowerlist.LocationLowerList;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ロケーション詳細 Actionクラス.
 * @author t0011036
 */
public final class LocationDetailAction extends AbstractAction {

	private LocationDetailLogic locationDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public LocationDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		LocationDetailForm frm = (LocationDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_LOCATION,
			Constants.TAB_ID_LOCATION_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		try {
			/* 初期検索 */
			LocationDetail bean = locationDetailLogic.getDetailEntity(frm
					.getLocationCd());

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic checker = CheckDigitUtil
					.getCheckDigitUtils(request);

			/* 数値文字列に変換 */
			String strPossibleWeight = checker.format(frm.getUnitDivision(),
				bean.getPossibleWeight());

			bean.setStrPossibleWeight(strPossibleWeight);

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

			int locationLevel = 1;

			/* ロケーションレベル計算 */
			if (!StringUtils.isEmpty(bean.getUpperLocationCd())) {
				locationLevel = locationDetailLogic.calcLocationLevel(bean
						.getLocationCd(), bean.getUpperLocationCd());
			}

			frm.setLocationLevel(new BigDecimal(locationLevel));
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 棚卸リスト取得
	 * @param frm 画面データ
	 */
	public void setCountDivisionCombobox(final LocationDetailForm frm) {
		/* ラベルマスタの区分データを取得 */
		List<NamesListForComboboxes> list = locationDetailLogic
				.getCountDivisionList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setCountDivisionLabels(labels);
		frm.setCountDivisionValues(values);
	}

	/**
	 * 登録処理処理.
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
			getLog().debug("update");
		}

		LocationDetailForm frm = (LocationDetailForm) form;

		if (!StringUtils.isEmpty(frm.getUpperLocationCd())) {
			/* 上位ロケーションコードチェック */
			LocationDetail beanLocation = locationDetailLogic
					.getDetailEntity(frm.getUpperLocationCd());

			if (beanLocation == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.upper.location.cd");
				return mapping.findForward("success");
			}

			/* 上位ロケーション循環参照チェック */
			if (frm.getLocationCd().equals(frm.getUpperLocationCd())) {
				/* エラーメッセージ */
				saveError(request, "errors.same.upper.location.cd");
				return mapping.findForward("success");
			}
		}

		/* ロケーションレベル計算 */
		if (!StringUtils.isEmpty(frm.getUpperLocationCd())) {
			int locationLevel = locationDetailLogic.calcLocationLevel(frm
					.getLocationCd(), frm.getUpperLocationCd());

			if (locationLevel == -1) {
				/* エラーメッセージ */
				saveError(request, "errors.loop.upper.location.cd");
				return mapping.findForward("success");
			}

			frm.setLocationLevel(new BigDecimal(locationLevel));
		} else {
			frm.setLocationLevel(new BigDecimal("1"));
		}

		/* 下位ロケーションチェック */
		List<LocationLowerList> list = locationDetailLogic.getLowerLocation(frm
				.getLocationCd());

		if (list == null) {
			frm.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
		} else {
			if (list.size() == 0) {
				frm.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
			} else {
				frm.setAvailableFlg(new BigDecimal("0"));
			}
		}

		if (!StringUtils.isEmpty(frm.getTantoCd())) {
			/* 営業担当者コードチェック */
			LoginLogin beanLogin = locationDetailLogic.getLoginEntity(frm
					.getTantoCd());

			if (beanLogin == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.location.tanto.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getSectionCd())) {
			/* 会計部門コードチェック */
			BumonDetail beanBumon = locationDetailLogic.getBumonEntity(frm
					.getSectionCd());

			if (beanBumon == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.location.section.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getItemCd())) {
			/* ローリー原料品目コードチェック */
			ItemQueueLastVersion beanItemQueue = locationDetailLogic
					.getItemQueueEntity(frm.getItemCd());

			if (beanItemQueue == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.location.item.cd");
				return mapping.findForward("success");
			}
		}

		frm.setPossibleWeight(AecNumberUtils.convertBigDecimal(frm
				.getStrPossibleWeight())); /* 可能重量 */

		BigDecimal calc = frm.getPossibleWeight();

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		/* 演算結果を丸める */
		BigDecimal result = check
				.round(frm.getUnitDivision(), null, null, calc);

		frm.setPossibleWeight(result);

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			locationDetailLogic.insert(insertLocation(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			locationDetailLogic.update(updateLocation(frm,
				getLoginInfo(request).getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
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
			getLog().debug("delete");
		}

		LocationDetailForm frm = (LocationDetailForm) form;

		/* 削除処理を実行 */
		locationDetailLogic.delete(deleteLocation(frm, getLoginInfo(request)
				.getTantoCd()));

		Location bean = new Location();

		if (!StringUtils.isEmpty(frm.getUpperLocationCd())) {
			try {
				bean = locationDetailLogic.getEntity(frm.getUpperLocationCd());

				if (bean.getUpdateDate() != null) {
					/* 更新処理を実行 */
					locationDetailLogic.update(updateUpperLocation(bean,
						getLoginInfo(request).getTantoCd()));
				}
			} catch (NoDataException e) {
				bean = null;
			}
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用の上位Locationを作成する.
	 * @param bean 上位ロケーションデータ
	 * @param tantoCd 担当者コード
	 * @return Location
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Location updateUpperLocation(final Location bean,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		List<LocationLowerList> list = locationDetailLogic
				.getLowerLocation(bean.getLocationCd());

		/* 上位ロケーションの在庫可能フラグセット */
		if (list == null) {
			bean.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
		} else {
			if (list.size() == 0) {
				bean.setAvailableFlg(new BigDecimal("1")); /* 最下位ロケーション */
			} else {
				bean.setAvailableFlg(new BigDecimal("0"));
			}
		}

		bean.setUpdatorCd(tantoCd);

		return bean;
	}

	/**
	 * 更新処理用のLocationを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Location
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Location updateLocation(final LocationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Location newBean = new Location();

		try {
			newBean = locationDetailLogic.getEntity(frm.getLocationCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のLocationを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Location
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Location insertLocation(final LocationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Location newBean = new Location();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のLocationを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Location
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Location deleteLocation(final LocationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Location newBean = new Location();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
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
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		LocationDetailForm frm = (LocationDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_LOCATION,
			Constants.TAB_ID_LOCATION_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		/* 棚卸区分セット */
		setCountDivisionCombobox(frm);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * locationDetailLogicを設定します。
	 * @param locationDetailLogic LocationDetailLogic
	 */
	public void setLocationDetailLogic(
			final LocationDetailLogic locationDetailLogic) {
		this.locationDetailLogic = locationDetailLogic;
	}
}
