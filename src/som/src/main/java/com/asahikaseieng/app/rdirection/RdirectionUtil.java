/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造実績のユーティリティクラス
 * @author tosco
 */
public final class RdirectionUtil {

	/**
	 * コンストラクタ
	 */
	private RdirectionUtil() {
	}

	/**
	 * BigDecimalの文字列表現を取得する
	 * BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	public static String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}
	/**
	 * 荷主に対応する文字列を取得する
	 * 花王の場合のみ、花王、それ以外は自社
	 * @param shipperDivision 荷主
	 * @return 荷主ラベル
	 */
	public static String getShipperDivisionlabel(final int shipperDivision) {
		String res = RdirectionConst.SHIPPER_DIVISION_OWN_LABEL;
		if (shipperDivision == RdirectionConst.SHIPPER_DIVISION_KAO) {
			//花王の場合のみ、花王、それ以外は自社
			res = RdirectionConst.SHIPPER_DIVISION_KAO_LABEL;
		}
		return res;
	}
	/**
	 * メッセージを追加する
	 *
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	public static ActionMessages addError(final ActionMessages errors,
											final String key,
											final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/**
	 * 各タブ・詳細画面の登録時に指図ヘッダ更新用データを作成する
	 * 指図ステータスを6(FA受信)、指図記録フラグ等をクリアする
	 * @param commonForm ActionForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	public static RdirectionDirectionHeaderList setDirectionHeader(final AbstractRdirectionForm commonForm,
			final HttpServletRequest request) {
		RdirectionDirectionHeaderList header = new RdirectionDirectionHeaderList();
		try {
			//更新用beanに検索時の値をコピー
			IgnoreCaseBeanUtils.copyProperties(header, commonForm.getHeaderBean());
			header.setDirectionStatus(RdirectionConst.DIRECTION_STATUS_FA_RECEIVE);
			header.setProductRecordFlag(RdirectionConst.PRODUCT_RECORD_FLAG_UN_ISSUED);
			header.setProductRecordDate(null);
			header.setProductRecordTantoCd(null);
			//担当者コード
			LoginInfo bean = (LoginInfo) request.getSession(false).getAttribute(Constants.LOGIN_KEY);
			String tantoCd = bean.getTantoCd();
			header.setUpdatorCd(tantoCd);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return header;
	}

}
