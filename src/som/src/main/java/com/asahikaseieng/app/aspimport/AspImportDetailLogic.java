/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.aspimport;

import java.util.List;


/**
 * ASP取り込みロジック interface.
 * @author
 */
public interface AspImportDetailLogic {

	/**
	 * 購買外注作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	int createPurchaseSubcontract
		(final String loginUserId, final String loginUserOrganizationCd) throws Exception;

	/**
	 * 製造/包装指図作成処理
	 * @param loginUserId ログインユーザ
	 * @param loginUserOrganizationCd ログインユーザー部署
	 * @return int 登録件数
	 * @throws Exception 例外
	 */
	List<Integer> createDirection
		(final String loginUserId, final String loginUserOrganizationCd) throws Exception;
}
