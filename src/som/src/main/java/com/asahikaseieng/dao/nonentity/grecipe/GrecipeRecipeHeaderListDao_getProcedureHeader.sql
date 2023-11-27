/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
*/

/**
 * 工程詳細のヘッダーデータを主キーで処方ヘッダから取得用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeHeaderListDao
 * packageName=grecipe
 * methodName=getProcedureHeader
 *
 */
SELECT
	 RHEAD.RECIPE_CD AS            RECIPE_CD
	,RHEAD.RECIPE_VERSION AS       RECIPE_VERSION
	,RHEAD.RECIPE_NAME AS          RECIPE_NAME
	,RHEAD.ITEM_CD AS              ITEM_CD
    ,ITEM.ITEM_NAME AS             ITEM_NAME
    ,LINE.PRODUCTION_LINE_NAME AS  PRODUCTION_LINE_NAME
    ,RHEAD.RECIPE_STATUS AS        RECIPE_STATUS
    ,RHEAD.APPROVAL_STATUS AS      APPROVAL_STATUS
	,RHEAD.RECIPE_USE AS           RECIPE_USE
    ,FORMULA.QTY AS                SUM_QTY
    ,ITEM.STYLE_OF_PACKING AS      STYLE_OF_PACKING
	,NAMES.NAME_CD AS              NAME_CD				-- 名称コード

    ,CASE APPROVAL_STATUS
    	WHEN 1 THEN '入力中'
    	WHEN 2 THEN '承認依頼中'
    	WHEN 3 THEN '承認済み'
    	ELSE NULL
    END APPROVAL_STATUS_NAME
FROM
    (
        SELECT
             RECIPE_ID AS RECIPE_ID
            ,STEP_NO AS STEP_NO
            ,SUM(QTY) AS QTY
        FROM
            RECIPE_FORMULA
        WHERE
                RECIPE_ID = /*recipeId*/30759
            AND STEP_NO = /*stepNo*/1
            AND LINE_TYPE = -1
        GROUP BY
            RECIPE_ID
           ,STEP_NO
    ) FORMULA,
    VALID_ITEM_QUEUE ITEM,
	(SELECT NAME_CD
	 ,		NAME01
	 FROM NAMES
	 WHERE	NAME_DIVISION = 'UNIT'
	) NAMES,
    LINE LINE,
	RECIPE_HEADER RHEAD
WHERE
		RHEAD.RECIPE_ID = /*recipeId*/30759
    AND RHEAD.RECIPE_TYPE = /*recipeType*/'3'	    --3:基本処方,1:原処方
    AND ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
	AND NAMES.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
    AND LINE.PRODUCTION_LINE(+) = RHEAD.PRODUCTION_LINE
    AND FORMULA.RECIPE_ID(+) = RHEAD.RECIPE_ID
