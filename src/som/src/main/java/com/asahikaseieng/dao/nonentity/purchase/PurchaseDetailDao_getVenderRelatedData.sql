/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/

/**
 * 仕入先入力時、検索SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDetail
 * packageName=purchase
 * methodName=getVenderRelatedData
 *
 */



SELECT
	 MAX(UNION_TB.UNITPRICE_DIVISION)	AS UNITPRICE_DEFINEUNIT		-- 単価区分(購買外注オーダーの項目名に合わせる)
	,MAX(UNION_TB.NAME01)				AS UNITPRICE_UNIT      		-- 単価名称
	,MAX(UNION_TB.VENDER_SHORTED_NAME)	AS SUPPLIER_NAME			-- 仕入先略称
	,MAX(UNION_TB.ORGANIZATION_CD)		AS CHARGE_ORGANIZATION_CD	-- 担当部署コード(購買外注オーダーの項目名に合わせる)
	,MAX(UNION_TB.ORGANIZATION_NAME)	AS TANTO_SECTION_NM			-- 担当部署名称
	,MAX(UNION_TB.REDUCED_TAX_TARGET_FLG) AS REDUCED_TAX_TARGET_FLG   -- 免税計算対象フラグ(仕入インボイスパターン)
FROM
	(
		-- 単価区分と単価名称を取得
		SELECT
			 UNIT.UNITPRICE_DIVISION
			,NAME.NAME01
			,null					AS VENDER_SHORTED_NAME
			,null					AS ORGANIZATION_CD
			,null					AS ORGANIZATION_NAME
            ,null					AS REDUCED_TAX_TARGET_FLG
		FROM
			(
			SELECT
				 VENDER_CD
				,ITEM_CD
				,MAX(VERSION) AS VERSION
			FROM	UNITPRICE
			WHERE	VENDER_DIVISION = 'SI'	--発注　'SI'固定
			AND		VENDER_CD = /*venderCd*/
			AND		ITEM_CD = /*itemCd*/
			AND		CONSECUTIVE_NO = '1'
			GROUP BY	VENDER_CD, ITEM_CD
			) UNIT01
			,UNITPRICE UNIT
			,NAMES NAME
		WHERE
			UNIT.VENDER_DIVISION = 'SI'
		AND	UNIT.VENDER_CD = UNIT01.VENDER_CD
		AND	UNIT.ITEM_CD = UNIT01.ITEM_CD
		AND	UNIT.CONSECUTIVE_NO = '1'
		AND	UNIT.VERSION = UNIT01.VERSION
		AND	NAME.NAME_DIVISION(+) = 'COST'
		AND	NAME.NAME_CD(+) = UNIT.UNITPRICE_DIVISION
	UNION ALL
		-- 仕入先名称と部署コードと部署名称を取得
		SELECT
			 null					AS UNITPRICE_DIVISION
			,null					AS NAME01
			,VEN.VENDER_SHORTED_NAME
			,VEN.ORGANIZATION_CD
			,ORG.ORGANIZATION_NAME
			-- 0：免税の計算対象外(申請業者) 1：免税の計算対象(未申請業者)
			,CASE
               WHEN VEN.SI_INVOICE_PTN = '2'
                   THEN '1'
               ELSE '0'
               END REDUCED_TAX_TARGET_FLG
		FROM	VENDER VEN
				,ORGANIZATION  ORG
		WHERE	VEN.VENDER_DIVISION = 'SI'
		AND		VEN.VENDER_CD = /*venderCd*/
		AND		ORG.ORGANIZATION_CD(+) = VEN.ORGANIZATION_CD
	) UNION_TB