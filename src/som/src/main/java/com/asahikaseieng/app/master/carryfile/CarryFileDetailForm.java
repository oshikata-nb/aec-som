/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import com.asahikaseieng.dao.nonentity.master.carryfiledetail.CarryFileDetail;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 運送会社詳細 Formクラス.
 * @author a1053739
 */
public final class CarryFileDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

    
	/* 運送会社コード */
	private String carryCd;

	/* 運送会社名称1 */
	private String carryName;
	
	/* 運送会社設定ファイルリスト */
	private List< CarryFileDetail > carryFileSettingList;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 登録日時 */
	private java.sql.Timestamp inputDate;

	/* 入力者 */
	private String inputorCd;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* ヘッダ出力フラグ */
	private Boolean headerFlgCheck;

	
	/**
	 * コンストラクタ.
	 */
	public CarryFileDetailForm() {
	}

    
    public ActionServlet getServlet() {
        return  super.getServlet();
    }
    
	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setCarryCd(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		
		this.carryFileSettingList = new ArrayList<CarryFileDetail>();
	}


	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryFileSettingListを取得します。
	 * @return carryFileSettingList
	 */
	public List< CarryFileDetail > getCarryFileSettingList() {
		return carryFileSettingList;
	}

	/**
	 * carryFileSettingListを設定します。
	 * @param carryFileSettingList carryFileSettingList
	 */
	public void setCarryFileSettingList(List< CarryFileDetail > carryFileSettingList) {
		this.carryFileSettingList = carryFileSettingList;
	}
	
	/**
	 * 運送会社設定ファイルリストサイズ取得
	 * ##ここにメソッドの説明を書いてください##
	 * @return
	 */
	public int getCarryFileSettingListCount(){
		if( this.carryFileSettingList == null ){
			return 0;
		}else{
			return this.carryFileSettingList.size();
		}
			
	}


	/**
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}


	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(String carryName) {
		this.carryName = carryName;
	}


	/**
	 * headerFlgCheckを取得します。
	 * @return headerFlgCheck
	 */
	public Boolean getHeaderFlgCheck() {
		return headerFlgCheck;
	}


	/**
	 * headerFlgCheckを設定します。
	 * @param headerFlgCheck headerFlgCheck
	 */
	public void setHeaderFlgCheck(Boolean headerFlgCheck) {
		this.headerFlgCheck = headerFlgCheck;
	}


	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return inputDate;
	}


	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}


	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}


	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

}
