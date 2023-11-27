/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/

/**
 * Asprova詳細のヘッダーデータを主キーで処方ヘッダから取得用SQL
 *
 * @author tosco
 *
 * entityName=RecipeHeader
 * packageName=mgrecipe
 * methodName=getAsprovaHeader
 *
 */
SELECT
     RHEAD.RECIPE_CD AS            RECIPE_CD            -- レシピコード
    ,RHEAD.RECIPE_VERSION AS       RECIPE_VERSION       -- レシピヴァージョン
    ,RHEAD.RECIPE_NAME AS          RECIPE_NAME          -- 処方名称
    ,RHEAD.ITEM_CD AS              ITEM_CD              -- 品目コード
    ,ITEM.ITEM_NAME AS             ITEM_NAME            -- 品目名称
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION -- 単位区分
    ,NAME.NAME01 AS                UNIT_NAME            -- 単位
    ,LINE.PRODUCTION_LINE_NAME AS  PRODUCTION_LINE_NAME -- 生産工場
    ,RHEAD.RECIPE_STATUS AS        RECIPE_STATUS        -- RECIPE_STATUS
    ,RHEAD.APPROVAL_STATUS AS      APPROVAL_STATUS      -- 承認ステータス
    ,RHEAD.RECIPE_USE AS           RECIPE_USE           -- 用途
    ,ITEM.STYLE_OF_PACKING AS      STYLE_OF_PACKING     -- 荷姿

    ,CASE APPROVAL_STATUS
    	WHEN 1 THEN '入力中'
    	WHEN 2 THEN '承認依頼中'
    	WHEN 3 THEN '承認済み'
    	ELSE NULL
    END APPROVAL_STATUS_NAME
FROM
    VALID_ITEM_QUEUE MAX_ITEM,
    VALID_ITEM_QUEUE ITEM,
    LINE LINE,
    NAMES NAME,
    RECIPE_HEADER RHEAD
WHERE
        RHEAD.RECIPE_ID = /*recipeId*/30809
    AND RHEAD.RECIPE_TYPE = /*recipeType*/'3'        --3:基本処方,1:原処方
    AND MAX_ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND LINE.PRODUCTION_LINE(+) = RHEAD.PRODUCTION_LINE
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
    