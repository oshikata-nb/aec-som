/*
 * Created on 2021/04/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.util.List;

import com.asahikaseieng.dao.entity.frstorderdetail.FrstOrderDetail;
import com.asahikaseieng.dao.entity.frstorderhead.FrstOrderHead;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;

public class OrderImportDeliveryDateDataModel {

	FrstOrderHead headBean;
	FrstOrderDetail detailBean;
	Vender venderBean;
	List<OrderImportList> checkedList;
	int rowNum;
	
	/**
	 * headBeanを取得します。
	 * @return headBean
	 */
	public FrstOrderHead getHeadBean() {
		return headBean;
	}
	/**
	 * headBeanを設定します。
	 * @param headBean headBean
	 */
	public void setHeadBean(FrstOrderHead headBean) {
		this.headBean = headBean;
	}
	/**
	 * detailBeanを取得します。
	 * @return detailBean
	 */
	public FrstOrderDetail getDetailBean() {
		return detailBean;
	}
	/**
	 * detailBeanを設定します。
	 * @param detailBean detailBean
	 */
	public void setDetailBean(FrstOrderDetail detailBean) {
		this.detailBean = detailBean;
	}
	/**
	 * venderBeanを取得します。
	 * @return venderBean
	 */
	public Vender getVenderBean() {
		return venderBean;
	}
	/**
	 * venderBeanを設定します。
	 * @param venderBean venderBean
	 */
	public void setVenderBean(Vender venderBean) {
		this.venderBean = venderBean;
	}

	/**
	 * rowNumを取得します。
	 * @return rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * rowNumを設定します。
	 * @param rowNum rowNum
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * checkedListを取得します。
	 * @return checkedList
	 */
	public List<OrderImportList> getCheckedList() {
		return checkedList;
	}
	/**
	 * checkedListを設定します。
	 * @param checkedList checkedList
	 */
	public void setCheckedList(List<OrderImportList> checkedList) {
		this.checkedList = checkedList;
	}
	
	
}
