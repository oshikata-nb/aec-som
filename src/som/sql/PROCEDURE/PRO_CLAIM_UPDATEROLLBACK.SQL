CREATE OR REPLACE PROCEDURE PRO_CLAIM_UPDATEROLLBACK
	(
		 P_STR_SECTION_CD		IN	NVARCHAR2
		,P_STR_VENDER_CD		IN	NVARCHAR2
		,P_STR_CREDIT_DATE		IN	NVARCHAR2
		,P_STR_INPUTOR_CD		IN	NVARCHAR2
		,P_NUM_RET				OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_CLAIM_UPDATEROLLBACK
	処理内容	:	請求更新ロールバック処理
	引数		:	P_STR_SECTION_CD		部門コード		NVARCHAR2型
	引数		:	P_STR_VENDER_CD			請求先コード	NVARCHAR2型
	引数		:	P_STR_CREDIT_DATE		請求締め日		NVARCHAR2型
	引数		:	P_STR_INPUTOR_CD		登録者ＩＤ		NVARCHAR2型
	引数(戻値)	:	P_NUM_RET				実行結果(0:正常終了, 99:異常終了)	NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.04	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_ClaimTrgDiv0		CONSTANT SALES.CLAIM_TARGET_DIVISION%TYPE		:= 0;			-- 請求対象(未処理)
	c_ClaimUpdDiv0		CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE		:= 0;			-- 請求更新フラグ(未処理)
	c_ClaimUpdDiv1		CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE		:= 1;			-- 請求更新フラグ(更新済)

	daCreditDate		CLAIM_HEADER.CREDIT_DATE%TYPE;									-- 請求締め日

BEGIN

	-- 文字型をDate型へキャスト
	daCreditDate := TO_DATE(P_STR_CREDIT_DATE, 'YYYY/MM/DD');

	--------------------------------------------------------------------------------
	--仮締めデータ削除処理
	--------------------------------------------------------------------------------
	-------------------------------------------------
	--★仮締め請求ヘッダー削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_CLAIM_HEADER
	WHERE
		('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND	('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR CUSTOMER_CD = P_STR_VENDER_CD)
	;

	-------------------------------------------------
	--★仮締め売上トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_CLAIM_SALES
	WHERE
		('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND	('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
		 (CUSTOMER_CD = P_STR_VENDER_CD AND INVOICE_CD IS NULL) OR
		 INVOICE_CD = P_STR_VENDER_CD
		)
	;

	-------------------------------------------------
	--★仮締め入金トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_CLAIM_CREDIT
	WHERE
		('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND	('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR CUSTOMER_CD = P_STR_VENDER_CD)
	;

	-------------------------------------------------
	--★仮締め支払トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_CLAIM_PAYMENT
	WHERE
		('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND	('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR SUPPLIER_CD = P_STR_VENDER_CD)
	;

	-------------------------------------------------
	--★仮締めグループ間相殺トランザクション削除
	-------------------------------------------------
	DELETE FROM TMPCLAIM_OFFSET_GROUP_DATA
	WHERE
		('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND	('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
	;

	--------------------------------------------------------------------------------
	--★請求ヘッダー削除
	--------------------------------------------------------------------------------
	DELETE FROM CLAIM_HEADER 
	WHERE
			('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR CUSTOMER_CD = P_STR_VENDER_CD)
	AND		CREDIT_DATE = daCreditDate;

	----------------------------------------------------------------------------------------------------------
	--各トランザクション更新処理
	----------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--★売上トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE SALES
		SET
			CLAIM_TARGET_DIVISION		=	c_ClaimTrgDiv0				-- 請求対象(未処理)
		,	CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv0				-- 請求更新フラグ(未処理)
		,	CLAIM_NO					=	NULL						-- 請求番号
		,	INVOICE_UPDATE_DATE			=	NULL						-- 請求締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
		 	 (CUSTOMER_CD = P_STR_VENDER_CD AND INVOICE_CD IS NULL) OR
		 	 INVOICE_CD = P_STR_VENDER_CD
			)
	AND		CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv1				-- 請求更新フラグ(更新済)
	AND		INVOICE_UPDATE_DATE		    =   daCreditDate				-- 請求締め日
	;

	--------------------------------------------------------------------------------
	--★入金トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE CREDIT
		SET
			CLAIM_TARGET_DIVISION		=	c_ClaimTrgDiv0				-- 請求対象(未処理)
		,	CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv0				-- 請求更新フラグ(未処理)
		,	CLAIM_NO					=	NULL						-- 請求番号
		,	INVOICE_UPDATE_DATE			=	NULL						-- 請求締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR CUSTOMER_CD = P_STR_VENDER_CD)
	AND		CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv1				-- 請求更新フラグ(更新済)
	AND		INVOICE_UPDATE_DATE		    =   daCreditDate				-- 請求締め日
	;

	--------------------------------------------------------------------------------
	--★支払トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE PAYMENT
		SET
			CLAIM_TARGET_DIVISION		=	c_ClaimTrgDiv0				-- 請求対象(未処理)
		,	CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv0				-- 請求更新フラグ(未処理)
		,	CLAIM_NO					=	NULL						-- 請求番号
		,	INVOICE_UPDATE_DATE			=	NULL						-- 請求締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR SUPPLIER_CD = P_STR_VENDER_CD)
	AND		CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv1				-- 請求更新フラグ(更新済)
	AND		INVOICE_UPDATE_DATE		    =   daCreditDate				-- 請求締め日
	;

	--------------------------------------------------------------------------------
	--★グループ間相殺トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE OFFSET_GROUP_DATA
		SET
			CLAIM_TARGET_DIVISION		=	c_ClaimTrgDiv0				-- 請求対象(未処理)
		,	CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv0				-- 請求更新フラグ(未処理)
		,	CLAIM_NO					=	NULL						-- 請求番号
		,	INVOICE_UPDATE_DATE			=	NULL						-- 請求締め日
		,	UPDATE_DATE					=	SYSDATE						-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD			-- 更新者ＩＤ
	WHERE
			('ALL' = NVL(P_STR_SECTION_CD, 'ALL') OR SECTION_CD = P_STR_SECTION_CD)
	AND		('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
	AND		CLAIM_UPDATE_DIVISION		=	c_ClaimUpdDiv1				-- 請求更新フラグ(更新済)
	AND		INVOICE_UPDATE_DATE		    =   daCreditDate				-- 請求締め日
	;

	--------------------------------------------------------------------------------
	--戻り値設定
	--------------------------------------------------------------------------------
	P_NUM_RET := 0;

EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		--ロールバック
		ROLLBACK;

		--戻り値設定
		P_NUM_RET := 99;

END PRO_CLAIM_UPDATEROLLBACK;
/
