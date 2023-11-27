CREATE OR REPLACE PROCEDURE AP21.PRO_ARROLLBACK
	(
		 P_STR_ORGANIZATION_CD		IN	NVARCHAR2
		,P_STR_CREDIT_DATE		IN	NVARCHAR2
		,P_STR_INPUTOR_CD		IN	NVARCHAR2
		,P_NUM_RET				OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_ARROLLBACK
	処理内容	:	売掛ロールバック処理
	引数		:	P_STR_SECTION_CD		部門コード	NVARCHAR2型
	引数		:	P_STR_CREDIT_DATE		売掛締め日	NVARCHAR2型
	引数		:	P_STR_INPUTOR_CD		登録者ＩＤ	NVARCHAR2型
	引数(戻値)	:	P_NUM_RET				実行結果(0:正常終了, 99:異常終了)	NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.07.31	1.00	TOSCO       		creation
	2009.06.10								SOM殿向けカスタマイズ
*/----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_DepositTrgDiv0		CONSTANT SALES.DEPOSIT_TARGET_DIVISION%TYPE		:= 0;			-- 売掛対象(未処理)
	c_DepositUpdDiv0		CONSTANT SALES.DEPOSIT_UPDATE_DIVISION%TYPE		:= 0;			-- 売掛更新フラグ(未処理)
	c_DepositUpdDiv1		CONSTANT SALES.DEPOSIT_UPDATE_DIVISION%TYPE		:= 1;			-- 売掛更新フラグ(更新済)
	c_VenderDivTS			CONSTANT VENDER.VENDER_DIVISION%TYPE			:= 'TS';		-- 取引先区分(TS:得意先)
	daCreditDate			DEPOSIT_HEADER.CREDIT_DATE%TYPE;								-- 売掛締め日
BEGIN
	-- 文字型をDate型へキャスト
	daCreditDate := TO_DATE(P_STR_CREDIT_DATE, 'YYYY/MM/DD');
	--------------------------------------------------------------------------------
	--仮締めデータ削除処理
	--------------------------------------------------------------------------------
	-------------------------------------------------
	--★仮締め売掛ヘッダー削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_DEPOSIT_HEADER
	WHERE
		VENDER_CD IN (
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					);
	-------------------------------------------------
	--★仮締め売掛売上トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_DEPOSIT_SALES
	WHERE
		DECODE(INVOICE_CD, NULL, VENDER_CD, INVOICE_CD) IN 
		(
			SELECT
				VENDER_CD
			FROM 
				VENDER
			WHERE
				('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
			AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
	  		AND VENDER_DIVISION = c_VenderDivTS
		);
	-------------------------------------------------
	--★仮締め売掛入金トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_DEPOSIT_CREDIT
	WHERE
		VENDER_CD IN (
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					);
	-------------------------------------------------
	--★仮締め売掛支払トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_DEPOSIT_PAYMENT
	WHERE
		SUPPLIER_CD IN (
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					);
	-------------------------------------------------
	--★仮締め売掛グループ間相殺トランザクション削除
	-------------------------------------------------
	DELETE FROM TMPDEPT_OFFSET_GROUP_DATA
	WHERE
		VENDER_CD IN (
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					);
	--------------------------------------------------------------------------------
	--★売掛ヘッダー削除
	--------------------------------------------------------------------------------
	DELETE FROM DEPOSIT_HEADER 
	WHERE
			CREDIT_DATE = daCreditDate
	AND		VENDER_CD IN (
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					);
	----------------------------------------------------------------------------------------------------------
	--各トランザクション更新処理
	----------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--★売上トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE SALES
		SET
			DEPOSIT_TARGET_DIVISION		=	c_DepositTrgDiv0				-- 売掛対象(未処理)
		,	DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv0				-- 売掛更新フラグ(未処理)
		,	DEPOSIT_NO					=	NULL							-- 売掛番号
		,	DELIVERY_UPDATE_DATE		=	NULL							-- 売掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv1				-- 売掛更新フラグ(更新済)
	AND		DELIVERY_UPDATE_DATE		= 	daCreditDate					-- 売掛締め日
	AND		DECODE(INVOICE_CD, NULL, VENDER_CD, INVOICE_CD) IN				-- 請求先
				(
					SELECT
						VENDER_CD
					FROM 
						VENDER
					WHERE
						('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
					AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
			  		AND VENDER_DIVISION = c_VenderDivTS
				)
	;
	--------------------------------------------------------------------------------
	--★入金トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE CREDIT
		SET
			DEPOSIT_TARGET_DIVISION		=	c_DepositTrgDiv0				-- 売掛対象(未処理)
		,	DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv0				-- 売掛更新フラグ(未処理)
		,	DEPOSIT_NO					=	NULL							-- 売掛番号
		,	DELIVERY_UPDATE_DATE		=	NULL							-- 売掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv1				-- 売掛更新フラグ(更新済)
	AND		DELIVERY_UPDATE_DATE		= 	daCreditDate					-- 売掛締め日
	AND		VENDER_CD IN (													-- 請求先
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					)
	;
	--------------------------------------------------------------------------------
	--★支払トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE PAYMENT
		SET
			DEPOSIT_TARGET_DIVISION		=	c_DepositTrgDiv0				-- 売掛対象(未処理)
		,	DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv0				-- 売掛更新フラグ(未処理)
		,	DEPOSIT_NO					=	NULL							-- 売掛番号
		,	DELIVERY_UPDATE_DATE		=	NULL							-- 売掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv1				-- 売掛更新フラグ(更新済)
	AND		DELIVERY_UPDATE_DATE		= 	daCreditDate					-- 売掛締め日
	AND		SUPPLIER_CD IN (												-- 仕入先
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					)
	;
	--------------------------------------------------------------------------------
	--★グループ間相殺トランザクション更新
	--------------------------------------------------------------------------------
	UPDATE OFFSET_GROUP_DATA
		SET
			DEPOSIT_TARGET_DIVISION		=	c_DepositTrgDiv0				-- 売掛対象(未処理)
		,	DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv0				-- 売掛更新フラグ(未処理)
		,	DEPOSIT_NO					=	NULL							-- 売掛番号
		,	DELIVERY_UPDATE_DATE		=	NULL							-- 売掛締め日
		,	UPDATE_DATE					=	SYSDATE							-- 更新日時
		,	UPDATOR_CD					=	P_STR_INPUTOR_CD				-- 更新者ＩＤ
	WHERE
			DEPOSIT_UPDATE_DIVISION		=	c_DepositUpdDiv1				-- 売掛更新フラグ(更新済)
	AND		DELIVERY_UPDATE_DATE		= 	daCreditDate					-- 売掛締め日
	AND		VENDER_CD IN (													-- 請求先
						SELECT
							VENDER_CD
						FROM 
							VENDER
						WHERE
							('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
						AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
				  		AND VENDER_DIVISION = c_VenderDivTS
					)
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
END PRO_ARROLLBACK;
/
