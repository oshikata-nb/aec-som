/*
 * Created on 2009/02/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造指図のユーティリティクラス
 * @author tosco
 */
public final class DirectionUtil {

	/**
	 * コンストラクタ
	 */
	private DirectionUtil() {
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
		String res = DirectionConst.SHIPPER_DIVISION_OWN_LABEL;
		if (shipperDivision == DirectionConst.SHIPPER_DIVISION_KAO) {
			//花王の場合のみ、花王、それ以外は自社
			res = DirectionConst.SHIPPER_DIVISION_KAO_LABEL;
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
	 * 各タブ・詳細画面の登録時に指図ヘッダ更新・製造指図・製造計画削除用データを作成する
	 * @param commonForm ActionForm
	 * @param request HttpServletRequest
	 * @return DirectionDirectionHeaderList
	 */
	public static DirectionDirectionHeaderList setDirectionHeader(final AbstractDirectionForm commonForm,
			final HttpServletRequest request) {
		DirectionDirectionHeaderList header = new DirectionDirectionHeaderList();
		try {
			//更新用beanに検索時の値をコピー
			IgnoreCaseBeanUtils.copyProperties(header, commonForm.getHeaderBean());
			Timestamp now = AecDateUtils.getCurrentTimestamp();
			header.setDirectionDate(now);
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
