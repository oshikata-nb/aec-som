/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
*/

/**
 * 検査詳細のヘッダーデータを主キーで製造指図ヘッダから取得用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionHeaderList
 * packageName=direction
 * methodName=getInspectionHeader
 *
 */
SELECT
	 DHEAD.RECIPE_CD AS            RECIPE_CD            -- レシピコード
    ,DHEAD.RECIPE_VERSION AS       RECIPE_VERSION       -- レシピヴァージョン
    ,RHEAD.RECIPE_NAME AS          RECIPE_NAME          -- 処方名称
    ,DHEAD.ITEM_CD AS              ITEM_CD              -- 品目コード
    ,ITEM.ITEM_NAME AS             ITEM_NAME            -- 品目名称
    ,RHEAD.RECIPE_USE AS           RECIPE_USE           -- 用途
    ,PROC.OPERATION_CD AS          OPERATION_CD         -- 工程コード
    ,OPE.OPERATION_NAME AS         OPERATION_NAME       -- 工程名称
FROM
	VALID_ITEM_QUEUE ITEM,
    NAMES NAME,
    OPERATION OPE,
    DIRECTION_PROCEDURE PROC,
    DIRECTION_HEADER DHEAD,
    RECIPE_HEADER RHEAD
WHERE
		DHEAD.DIRECTION_DIVISION = '1'
	AND DHEAD.DIRECTION_NO = /*directionNo*/'ISONO00001'	
    AND RHEAD.RECIPE_VERSION(+) = DHEAD.RECIPE_VERSION
    AND RHEAD.RECIPE_ID(+) = DHEAD.RECIPE_ID
    AND ITEM.ITEM_CD(+) = DHEAD.ITEM_CD
    AND PROC.DIRECTION_NO(+) = DHEAD.DIRECTION_NO
    AND PROC.STEP_NO(+) = /*stepNo*/'1'
    AND OPE.OPERATION_CD(+) = PROC.OPERATION_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
