/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;

/**
 * 在庫・受払ソースを操作するクラス(購買外注用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでZaiCtrlDao(PLSQL実行DAO)を設定すること。<BR>
 * ＤＢデータを使って、計画作成、発注確定、受入入力、発注完了<BR>
 * に関する在庫・受払ソース操作（入力、取消）を行う。
 * @author a7710658
 */
public class StockinoutForPurchase {
	/** 在庫更新PROCEDURE DAO宣言 */
	private ZaiCtrlDao zaiCtrlDao;

	/** Log */
	private static Log log = LogFactory.getLog(StockinoutForPurchase.class);

	/**
	 * コンストラクタ
	 * @param zaiCtrlDao PLSQL実行DAO
	 */
	public StockinoutForPurchase(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 発注入力
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryPurchase(final String purchaseNo, final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryPurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.entryPurchase(" + purchaseNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryPurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 発注入力取消
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean canselPurchase(final String purchaseNo,
			final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryPurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.canselPurchase(" + purchaseNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("canselPurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 発注確定
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean fixPurchase(final String purchaseNo, final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_KAKUTEI_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryPurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.fixPurchase(" + purchaseNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("fixPurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 発注確定取消
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean defixPurchase(final String purchaseNo, final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_KAKUTEI_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryPurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.defixPurchase(" + purchaseNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("defixPurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 受入入力
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean receivePurchase(final String purchaseNo,
			final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.receivePurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.defixPurchase(" + purchaseNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("receivePurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 受入入力取消
	 * @param purchaseNo 購買NO
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean dereceivePurchase(final String purchaseNo,
			final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setStrCode(purchaseNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.receivePurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.defixPurchase(" + purchaseNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("dereceivePurchase()");
			throw e;
		}
		return true;
	}

	/**
	 * 発注完了
	 * @param buySubcontractOrderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completePurchase(final String buySubcontractOrderNo,
			final String loginUser) {
		StrCodeDto dto = new StrCodeDto();
		dto.setLngFlg(StockinoutConstant.BD_COMPLETE_FLG);
		dto.setStrCode(buySubcontractOrderNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.completePurchase(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.defixPurchase("
					+ buySubcontractOrderNo + "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("completePurchase()");
			throw e;
		}
		return true;
	}
}
