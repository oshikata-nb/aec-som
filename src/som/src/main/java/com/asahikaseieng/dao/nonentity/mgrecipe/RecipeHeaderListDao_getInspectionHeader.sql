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
 * entityName=RecipeHeader
 * packageName=mgrecipe
 * methodName=getInspectionHeader
 *
 */
SELECT
     RHEAD.RECIPE_CD AS            RECIPE_CD            -- レシピコード
    ,RHEAD.RECIPE_VERSION AS       RECIPE_VERSION       -- レシピヴァージョン
    ,RHEAD.RECIPE_NAME AS          RECIPE_NAME          -- 処方名称
    ,RHEAD.ITEM_CD AS              ITEM_CD              -- 品目コード
    ,ITEM.ITEM_NAME AS             ITEM_NAME            -- 品目名称
    ,RHEAD.RECIPE_USE AS           RECIPE_USE           -- 用途
    ,PROC.OPERATION_CD AS          OPERATION_CD         -- 工程コード
    ,RHEAD.APPROVAL_STATUS AS      APPROVAL_STATUS      -- 承認ステータス
    ,OPE.OPERATION_NAME AS         OPERATION_NAME       -- 工程名称
    ,FORMULA.QTY AS                SUM_QTY              -- 配合量計

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
                RECIPE_ID = /*recipeId*/
            AND STEP_NO = /*stepNo*/
            AND LINE_TYPE = -1
        GROUP BY
            RECIPE_ID
           ,STEP_NO
    ) FORMULA,
    VALID_ITEM_QUEUE MAX_ITEM,
    VALID_ITEM_QUEUE ITEM,
    NAMES NAME,
    OPERATION OPE,
    RECIPE_PROCEDURE PROC,
    RECIPE_HEADER RHEAD
WHERE
        RHEAD.RECIPE_ID = /*recipeId*/30814
    AND RHEAD.RECIPE_TYPE = /*recipeType*/'3'        --3:基本処方,1:原処方
    AND MAX_ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND PROC.RECIPE_ID(+) = RHEAD.RECIPE_ID
    AND PROC.STEP_NO(+) = /*stepNo*/1
    AND OPE.OPERATION_CD(+) = PROC.OPERATION_CD
    AND FORMULA.RECIPE_ID(+) = RHEAD.RECIPE_ID
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
    