/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

/**
 * 一時データ作成プロシージャ呼び出し(原処方情報からの取得)クラス.DAO
 * @author 956
 */
public interface CreateTempCallDao {

	/** BEANアノテーション */
	Class BEAN = ProcedureCall.class;

	/** PROCEDURE_CALLアノテーション createAgriculturalTemp()*/
	String createAgriculturalTemp_PROCEDURE_CALL = "FUN_CREATE_AGRICULTURAL_TEMP";

	/** PROCEDURE_CALLアノテーション createAllergyTemp()*/
	String createAllergyTemp_PROCEDURE_CALL = "FUN_CREATE_ALLERGY_TEMP";

	/** PROCEDURE_CALLアノテーション createBseTemp()*/
	String createBseTemp_PROCEDURE_CALL = "FUN_CREATE_BSE_TEMP";

	/** PROCEDURE_CALLアノテーション createComponentTemp()*/
	String createComponentTemp_PROCEDURE_CALL = "FUN_CREATE_COMPONENT_TEMP";

	/** PROCEDURE_CALLアノテーション createGhsTemp()*/
	String createGhsTemp_PROCEDURE_CALL = "FUN_CREATE_GHS_TEMP";

	/** PROCEDURE_CALLアノテーション createGmoTemp()*/
	String createGmoTemp_PROCEDURE_CALL = "FUN_CREATE_GMO_TEMP";

	/** PROCEDURE_CALLアノテーション createPrtrTemp()*/
	String createPrtrTemp_PROCEDURE_CALL = "FUN_CREATE_PRTR_TEMP";

	/** PROCEDURE_CALLアノテーション createRoanhoTemp()*/
	String createRoanhoTemp_PROCEDURE_CALL = "FUN_CREATE_ROANHO_TEMP";

	/** PROCEDURE_CALLアノテーション createSyobohoTemp()*/
	String createSolventTemp_PROCEDURE_CALL = "FUN_CREATE_SOLVENT_TEMP";

	/** PROCEDURE_CALLアノテーション createSyobohoTemp()*/
	String createSyobohoTemp_PROCEDURE_CALL = "FUN_CREATE_SYOBOHO_TEMP";

	/**
	 * 原処方からの残留農薬一時データ作成
	 * @param dto パラメータDTO
	 */
	void createAgriculturalTemp(TempDto dto);
	/**
	 * 原処方からのアレルギー情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createAllergyTemp(TempDto dto);
	/**
	 * 原処方からのＢＳＥ情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createBseTemp(TempDto dto);
	/**
	 * 原処方からの成分重量情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createComponentTemp(TempDto dto);
	/**
	 * 原処方からのＧＨＳ情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createGhsTemp(TempDto dto);
	/**
	 * 原処方からのＧＭＯ情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createGmoTemp(TempDto dto);
	/**
	 * 原処方からのＰＲＴＲ情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createPrtrTemp(TempDto dto);
	/**
	 * 原処方からの労働安全衛生法通知対象情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createRoanhoTemp(TempDto dto);
	/**
	 * 原処方からの第５７条有機溶剤情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createSolventTemp(TempDto dto);
	/**
	 * 原処方からの消防法表示情報一時データ作成
	 * @param dto パラメータDTO
	 */
	void createSyobohoTemp(TempDto dto);
}
