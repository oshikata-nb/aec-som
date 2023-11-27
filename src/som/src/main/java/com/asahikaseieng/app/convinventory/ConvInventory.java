/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.convinventory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.asahikaseieng.Constants;

/**
 * 在庫数量換算
 * @author t0011036
 */
public final class ConvInventory {

	/**
	 * コンストラクタ
	 */
	private ConvInventory() {
	}

	/**
	 * 在庫数量換算
	 * @param request HttpServletRequest
	 * @return TakeInventoryLogic
	 */
	public static ConvInventoryLogic getConvInventory(
			final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (ConvInventoryLogic) session
				.getAttribute(Constants.CONV_INVENTORY_KEY);
	}
}
