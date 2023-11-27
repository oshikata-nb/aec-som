/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeAndNumberDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;

/**
 * 在庫・受払ソースを操作するクラス(受注用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでZaiCtrlDaoを設定すること。<BR>
 * ＤＢデータを使って、受注に関する受払ソース操作（入力、取消）を行う。<BR>
 * 処理コードPLngOpno=101(受注）受払区分PLngInoutdivision=4(出荷払い出し)で処理を行います。
 * @author a7710658
 */
public class StockinoutForOrder {

	/** 在庫更新PROCEDURE DAO宣言 */
	private ZaiCtrlDao zaiCtrlDao;

	/** Log */
	private static Log log = LogFactory.getLog(StockinoutForOrder.class);

	/**
	 * コンストラクタ
	 * @param zaiCtrlDao 在庫受払操作Dao
	 */
	public StockinoutForOrder(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 受注入力(全）<BR>
	 * ＤＢの受注データを基に出庫予定日に在庫減の予定INOUT_SOURCEを作成する。
	 * @param orderNo 受注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryOrder(final String orderNo, final String loginUser) {
		return entryOrder(orderNo, null, loginUser);
	}

	/**
	 * 受注入力(一詳細）<BR>
	 * ＤＢの受注データを基に出庫予定日に在庫減の予定INOUT_SOURCEを作成する。
	 * @param orderNo 受注番号
	 * @param rowNo 行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryOrder(final String orderNo, final BigDecimal rowNo,
			final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setStrCode(orderNo);
		dto.setLngRowNo(rowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryOrder(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForOrder.entryOrder(" + orderNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryOrder()");
			throw e;
		}
		return true;
	}

	/**
	 * 受注取消(全）<BR>
	 * ＤＢの受注データを基に出庫予定日に在庫減の予定INOUT_SOURCEを取り消す。
	 * @param orderNo 受注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelOrder(final String orderNo, final String loginUser) {
		return cancelOrder(orderNo, null, loginUser);
	}

	/**
	 * 受注取消(一詳細）<BR>
	 * ＤＢの受注データを基に出庫予定日に在庫減の予定INOUT_SOURCEを取り消す。
	 * @param orderNo 受注番号
	 * @param rowNo 行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelOrder(final String orderNo, final BigDecimal rowNo,
			final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setStrCode(orderNo);
		dto.setLngRowNo(rowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryOrder(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForOrder.entryOrder(" + orderNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("cancelOrder()");
			throw e;
		}
		return true;
	}

	/**
	 * 受注完了(全）<BR>
	 * ＤＢの受注データを基に受払ソースを無効にする。
	 * @param orderNo 受注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completeOrder(final String orderNo, final String loginUser) {
		return completeOrder(orderNo, null, loginUser);
	}

	/**
	 * 受注完了(一詳細）<BR>
	 * ＤＢの受注データを基に受払ソースを無効にする。
	 * @param orderNo 受注番号
	 * @param rowNo 行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completeOrder(final String orderNo, final BigDecimal rowNo,
			final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_COMPLETE_FLG);
		dto.setStrCode(orderNo);
		dto.setLngRowNo(rowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryOrder(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForOrder.completeOrder(" + orderNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("completeOrder()");
			throw e;
		}
		return true;
	}

}
