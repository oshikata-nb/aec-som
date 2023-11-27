/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.carryfilelayout.CarryFileLayout;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileComboboxItems;
import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送会社詳細 Actionクラス.
 * @author t0011036
 */
public final class CarryFileDetailAction extends AbstractAction {

	private CarryFileDetailLogic carryFileDetailLogic;

    
	/**
	 * コンストラクタ.
	 */
	public CarryFileDetailAction() {
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

		CarryFileDetailForm frm = (CarryFileDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_CARRY,
			Constants.TAB_ID_CARRY_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		List<CarryFileDetail> bean = carryFileDetailLogic.getDetailEntity(frm.getCarryCd());

		if( bean.size() > 0 ){
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean.get(0));
		}
		
		frm.setCarryFileSettingList(bean);

		this.setComboboxItems(frm);
		
		return mapping.findForward("success");
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

		CarryFileDetailForm frm = (CarryFileDetailForm) form;

		// delete&insert
		List<CarryFileLayout> delList = deleteCarryFileLayout(frm, getLoginInfo(request).getTantoCd());
		
		List<CarryFileLayout> list = insertCarryFileLayout(frm, getLoginInfo(request).getTantoCd(), request);
		
		if(list == null || list.isEmpty() ){
			saveError(request, "errors.no.row.data");
			return mapping.findForward("success");
		}
		
		if( delList != null && delList.size() > 0 ){
			/* 削除処理を実行 */
			carryFileDetailLogic.delete(delList);
		}
		
		/* 追加処理を実行 */
		carryFileDetailLogic.insert(list);
		
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

		CarryFileDetailForm frm = (CarryFileDetailForm) form;

		/* 削除処理を実行 */
		carryFileDetailLogic.delete(deleteCarryFileLayout(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}


	/**
	 * 追加処理用のCarryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Carry
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private List<CarryFileLayout> insertCarryFileLayout(final CarryFileDetailForm frm, final String tantoCd,
			final HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {
		List<CarryFileLayout> newBeans = new LinkedList<CarryFileLayout>();

		/* 値を更新用Beanにコピる */
		for( CarryFileDetail bean : frm.getCarryFileSettingList() ){
			
			if( bean.getDataCls() == null || bean.getDataCls().trim().length() == 0 || ( bean.getDataCls().equals("00") && ( bean.getColumnName() == null ) ) ){
				continue;
			}
			
			CarryFileLayout newBean = new CarryFileLayout();
			IgnoreCaseBeanUtils.copyProperties(newBean, bean);
			newBeans.add(newBean);
		}
		

		int seq = 1;
		
		/* コピーしきれなかった分は手で */
		for( CarryFileLayout newBean : newBeans ){
			newBean.setUpdatorCd(tantoCd);
			if( frm.getHeaderFlgCheck() ){
				newBean.setHeaderFlg( BigDecimal.ONE );
			}
			else{
				newBean.setHeaderFlg( BigDecimal.ZERO );
			}
			
			
			newBean.setInputorCd(frm.getInputorCd());
			newBean.setInputDate(frm.getInputDate());

			if( newBean.getInputorCd() == null || newBean.getInputorCd().trim().isEmpty() ){
				newBean.setInputDate(newBean.getCurrentTimestamp());
				newBean.setInputorCd(tantoCd);
			}
			
			newBean.setSeq( BigDecimal.valueOf(seq));
			newBean.setCarryCd(frm.getCarryCd());
			
			newBean.setDescription(this.sanitaize(newBean.getDescription()));
			
			seq = seq + 1;
		}
		

		return newBeans;
	}

	/**
	 * DB登録前に入力値の無害化
	 * @param val
	 * @return
	 */
	private String sanitaize( final String val ){
		if( val == null || val.trim().length() == 0){
			return val;
		}
		
		return val.replace("--", "ーー").replace("/", "／").replace("'", "’").replace("\"", "”");
	}
	
	/**
	 * 削除処理用のCarryを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Carry
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private List<CarryFileLayout> deleteCarryFileLayout(final CarryFileDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		
		List<CarryFileLayout> newBeans = new ArrayList<CarryFileLayout>();

		try {
			newBeans = carryFileDetailLogic.getEntity(frm.getCarryCd());
		} catch (NoDataException e) {
			return null;
		}		
		
		return newBeans;
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
	 * 行追加処理.addlist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addlist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("addlist.");
		}
		// リストを取得
		CarryFileDetailForm frm = (CarryFileDetailForm) form;
		List<CarryFileDetail> detailList = frm.getCarryFileSettingList();


		// 追加用データ
		CarryFileDetail bean = new CarryFileDetail();
		// 新しい要素を追加
		detailList.add(detailList.size(), bean);

		for (int i = 0; i < detailList.size(); i++) {
			CarryFileDetail detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}
		
		// コンボボックスの値設定
		this.setComboboxItems(frm);
		
		return mapping.findForward("success");
	}

	/**
	 * コンボボックスに選択可能な値を設定
	 * @param form
	 */
	private void setComboboxItems(final CarryFileDetailForm frm){
		/* ラベルマスタの区分データを取得 */
		List<CarryFileComboboxItems> list = carryFileDetailLogic.getComboboxItems();

		for( CarryFileDetail rows : frm.getCarryFileSettingList() ){

			String dataCls = rows.getDataCls();
			
			rows.setInputReadOnly(true);
			
			// 未選択の場合、テーブル、カラムの選択をクリア
			if( dataCls == null || dataCls.trim().length() == 0 ){
				rows.ClearCombobox();
				continue;
			}
			
			String[] tableLabels;
			String[] tableValues;

			String[] colLabels;
			String[] colValues;

			// 選択中の値を取得
			String curTableName = rows.getTableName();
			String curColumnName = rows.getColumnName();
			
			rows.ClearCombobox();

			// データベース選択時
			if( dataCls.equals("00") ){
								
				// テーブルの一覧を作成
				Map< String , String > tables = new LinkedHashMap<String , String>();
				tables.put(null, "選択してください");
				for( CarryFileComboboxItems items : list ){
					if( !tables.containsKey(items.getTableName()) ){
						tables.put(items.getTableName(), items.getTableComments());
					}
				}
				
				tableLabels = new String[tables.size()];
				tableValues = new String[tables.size()];
			
				int i = 0;
				for( Entry< String , String > kv : tables.entrySet() ){
					tableValues[i] = kv.getKey();
					tableLabels[i] = kv.getValue();
					i = i + 1;
				}
				
				// テーブル一覧を設定
				rows.setTableNameLabels(tableLabels);
				rows.setTableNameValues(tableValues);
				
				// カラム一覧を作成
				if( curTableName != null && curTableName.trim().length() > 0  ){
					Map< String , String > columns = new LinkedHashMap<String , String>();
					columns.put(null, "選択してください");
					for( CarryFileComboboxItems items : list ){
						// 選択中のテーブルと同じ場合、カラム情報を設定
						if( !columns.containsKey(items.getTableName()) && items.getTableName().equals(curTableName) ){
							columns.put(items.getColumnName(), items.getColComments());
						}
					}
					
					colLabels = new String[columns.size()];
					colValues = new String[columns.size()];
				
					i = 0;

					
					for( Entry< String , String > kv : columns.entrySet() ){
						colValues[i] = kv.getKey();
						colLabels[i] = kv.getValue();
						i = i + 1;
					}
					
					// テーブル一覧を設定
					rows.setColumnNameLabels(colLabels);;
					rows.setColumnNameValues(colValues);
				}
				
				rows.setColumnName(curColumnName);
				rows.setTableName(curTableName);
				
				
			}else if( dataCls.equals("01") ){
				rows.setInputReadOnly(false);

				tableLabels = new String[1];
				tableValues = new String[1];			

				colLabels = new String[1];
				colValues = new String[1];			

				/* 「なし」を追加 */
				tableLabels[0] = "-";
				tableValues[0] = null;

				colLabels[0] = "フリー入力欄に入力してください。";
				colValues[0] = null;
				
				rows.setTableNameLabels(tableLabels);
				rows.setTableNameValues(tableValues);

				rows.setColumnNameLabels(colLabels);
				rows.setColumnNameValues(colValues);

			}else if(  dataCls.equals("02") ){

				tableLabels = new String[1];
				tableValues = new String[1];			

				colLabels = new String[1];
				colValues = new String[1];			

				/* 「なし」を追加 */
				tableLabels[0] = "-";
				tableValues[0] = null;

				colLabels[0] = "-";
				colValues[0] = null;
				
				rows.setTableNameLabels(tableLabels);
				rows.setTableNameValues(tableValues);

				rows.setColumnNameLabels(colLabels);
				rows.setColumnNameValues(colValues);

			}
			
		}
		
	}
	
	/**
	 * 行削除処理.dellist
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward dellist(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("dellist.");
		}

		// リストを取得
		CarryFileDetailForm frm = (CarryFileDetailForm) form;
		List<CarryFileDetail> detailList = frm.getCarryFileSettingList();


		for (int i = detailList.size() - 1; i >= 0; i--) {
			CarryFileDetail bean = detailList.get(i);

			if ( bean.getCheckline() == null || !bean.getCheckline()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 削除したものを保持しておく
			detailList.remove(i);
		}

		/* すべて削除したら空白行を追記 */
		if (detailList.size() == 0) {
			CarryFileDetail bean = new CarryFileDetail();
			detailList.add(bean);
		}

		for (int i = 0; i < detailList.size(); i++) {
			CarryFileDetail detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}

		return mapping.findForward("success");
	}
	
	/**
	 * 選択変更処理.changeselect
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeselect(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		CarryFileDetailForm frm = (CarryFileDetailForm) form;

		this.setComboboxItems(frm);
		
		return mapping.findForward("success");
	}

	/**
	 * 行上昇処理.rowup
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward rowup(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("rowup.");
		}

		// リストを取得
		CarryFileDetailForm frm = (CarryFileDetailForm) form;
		List<CarryFileDetail> detailList = frm.getCarryFileSettingList();


		for (int i = 1; i < detailList.size(); i++) {
			CarryFileDetail bean = detailList.get(i);

			if ( bean.getCheckline() == null || !bean.getCheckline()) {
				// チェック無は読み飛ばし
				continue;
			}
			
			CarryFileDetail beforeBean = detailList.get(i-1);
			detailList.set(i-1, bean);
			detailList.set(i, beforeBean);
			
		}

		return mapping.findForward("success");
	}

	/**
	 * 行下降処理.rowdown
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward rowdown(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("rowup.");
		}

		// リストを取得
		CarryFileDetailForm frm = (CarryFileDetailForm) form;
		List<CarryFileDetail> detailList = frm.getCarryFileSettingList();


		for (int i = detailList.size() -  2; i >= 0 ; i--) {
			CarryFileDetail bean = detailList.get(i);

			if ( bean.getCheckline() == null || !bean.getCheckline()) {
				// チェック無は読み飛ばし
				continue;
			}
			
			CarryFileDetail beforeBean = detailList.get(i+1);
			detailList.set(i+1, bean);
			detailList.set(i, beforeBean);
			
		}


		return mapping.findForward("success");
	}

	/**
	 * チェックボックス選択解除処理.clearselect
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clearselect(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		// リストを取得
		CarryFileDetailForm frm = (CarryFileDetailForm) form;
		List<CarryFileDetail> detailList = frm.getCarryFileSettingList();

		
		if (getLog().isDebugEnabled()) {
			getLog().debug("clearselect.");
		}
		
		for (int i = 0; i < detailList.size(); i++) {
			CarryFileDetail detailBean = detailList.get(i);
			detailBean.setCheckline(Boolean.FALSE);
		}
		

		return mapping.findForward("success");
	}


	
	/* -------------------- setter -------------------- */

	/**
	 * carryDetailLogicを設定します。
	 * @param carryDetailLogic carryDetailLogic
	 */
	public void setCarrFileyDetailLogic(final CarryFileDetailLogic carryFileDetailLogic) {
		this.carryFileDetailLogic = carryFileDetailLogic;
	}
}
