/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
*/

/**
 * 検査詳細のヘッダーデータを主キーで処方ヘッダから取得用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeHeaderListDao
 * packageName=grecipe
 * methodName=getInspectionHeader
 *
 */
SELECT
     RHEAD.RECIPE_CD AS            RECIPE_CD            -- レシピコード
    ,RHEAD.RECIPE_VERSION AS       RECIPE_VERSION       -- レシピヴァージョン
    ,RHEAD.ITEM_CD AS              ITEM_CD              -- 品目コード
    ,ITEM.ITEM_NAME AS             ITEM_NAME            -- 品目名称
    ,PROC.OPERATION_CD AS          OPERATION_CD         -- 工程コード
    ,RHEAD.APPROVAL_STATUS AS      APPROVAL_STATUS      -- 承認ステータス
    ,OPE.OPERATION_NAME AS         OPERATION_NAME       -- 工程名称

    ,CASE APPROVAL_STATUS
    	WHEN 1 THEN '入力中'
    	WHEN 2 THEN '承認依頼中'
    	WHEN 3 THEN '承認済み'
    	ELSE NULL
    END APPROVAL_STATUS_NAME
FROM
	VALID_ITEM_QUEUE ITEM,
    NAMES NAME,
    OPERATION OPE,
    RECIPE_PROCEDURE PROC,
    RECIPE_HEADER RHEAD
WHERE
        RHEAD.RECIPE_ID = /*recipeId*/30908
    AND RHEAD.RECIPE_TYPE = /*recipeType*/1       --3:基本処方,1:原処方
    AND ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND PROC.RECIPE_ID(+) = RHEAD.RECIPE_ID
    AND PROC.STEP_NO(+) = /*stepNo*/1
    AND OPE.OPERATION_CD(+) = PROC.OPERATION_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
