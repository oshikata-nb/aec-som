/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/

/**
 * 包装指図－製造指図ヘッダー情報取得用SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionHeaderDetail
 * packageName=pkgdirection
 * methodName=getEntity
 *
 */
SELECT
     DIRHEAD.DIRECTION_DIVISION
    ,DIRHEAD.DIRECTION_NO
    ,DIRHEAD.DIRECTION_DATE
    ,DIRHEAD.DIRECTION_STATUS
    ,DIRHEAD.RECIPE_ID
    ,DIRHEAD.RECIPE_CD
    ,DIRHEAD.RECIPE_VERSION
    ,DIRHEAD.PRODUCTION_LINE
    ,DIRHEAD.COMPOUND_TANK_NO
    ,DIRHEAD.PACKAGE_LINE
    ,DIRHEAD.ITEM_CD
    ,DIRHEAD.PLANED_QTY
    ,DIRHEAD.LOT_NO
    ,DIRHEAD.PLANED_SDATE
    ,DIRHEAD.PLANED_EDATE
    ,DIRHEAD.REMARK
    ,DIRHEAD.NOTES
    ,DIRHEAD.INPUT_DATE
    ,DIRHEAD.INPUTOR_CD
    ,DIRHEAD.UPDATE_DATE
    ,DIRHEAD.UPDATOR_CD
    ,DIRHEAD.RECIPE_CD || DECODE(DIRHEAD.RECIPE_VERSION, NULL, '', ','
                                 || TO_CHAR(DIRHEAD.RECIPE_VERSION)) RECIPE_CD_VERSION
    ,ITEM.ITEM_NAME AS             ITEM_NAME
    ,ITEM.STYLE_OF_PACKING AS      STYLE_OF_PACKING
    ,ITEM.OTHER_COMPANY_CD1 AS     OTHER_COMPANY_CD1
    ,ITEM.SHIPPER_DIVISION AS      SHIPPER_DIVISION
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
    ,ITEM.PARENT_ITEM_CD AS        PARENT_ITEM_CD
    ,LINE.PRODUCTION_LINE_NAME AS  PRODUCTION_LINE_NAME
    ,NAME.NAME01 AS                UNIT_NAME
    ,RCPHEAD.RECIPE_NAME
    ,RCPRES1.RESOUCE_NAME AS PACKAGE_LINE_NAME
	,FILL_TANK_NO.FILL_TANK AS FILL_TANK
    ,RCPRES2.RESOUCE_NAME AS FILL_TANK_NAME
    ,DECODE((SELECT COUNT(*) FROM JISSEKI_SEIHIN
              WHERE SEIHINCODE = CASE SIGN(LENGTH(DIRHEAD.ITEM_CD) - 8)
				                  WHEN -1 THEN RPAD(DIRHEAD.ITEM_CD, 8, ' ')
                                  WHEN  0 THEN DIRHEAD.ITEM_CD
                                  WHEN  1 THEN SUBSTR(DIRHEAD.ITEM_CD, 1, 8) END
                AND HOSOSASHIZUNO = CASE SIGN(LENGTH(DIRHEAD.DIRECTION_NO) - 11)
                                     WHEN -1 THEN RPAD(DIRHEAD.DIRECTION_NO, 11, ' ')
                                     WHEN  0 THEN DIRHEAD.DIRECTION_NO
                                     WHEN  1 THEN SUBSTR(DIRHEAD.DIRECTION_NO, 1, 11) END),
            0, '0', '1') JISSEKI_FLAG
FROM
    LINE LINE,
    NAMES NAME,
    ITEM,
    RECIPE_HEADER RCPHEAD,
    DIRECTION_HEADER DIRHEAD,
    RECIPE_RESOUCE RCPRES1,
    RECIPE_RESOUCE RCPRES2
	,(SELECT DECODE(HOLD_TANK_NO, NULL, COMPOUND_TANK_NO, HOLD_TANK_NO) FILL_TANK
			,PKG_DIRECTION_NO
	   FROM DIRECTION_HEADER,
	   (SELECT MIN(DIRECTION_NO) DIRECTION_NO
			  ,PKG_DIRECTION_NO 
          FROM DIRECTION_DETAIL
	     WHERE PKG_DIRECTION_NO = /*pkgDirectionNo*/'H0904200001'
         GROUP BY PKG_DIRECTION_NO) DIRECTION_DETAIL
       WHERE DIRECTION_HEADER.DIRECTION_NO = DIRECTION_DETAIL.DIRECTION_NO
	    AND DIRECTION_DIVISION = 1 -- 1:バッチ指図
	 ) FILL_TANK_NO
WHERE
        DIRHEAD.ITEM_CD = ITEM.ITEM_CD(+)
    AND DIRHEAD.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)
    AND DIRHEAD.PACKAGE_LINE = RCPRES1.RESOUCE_CD(+)
    AND FILL_TANK_NO.FILL_TANK = RCPRES2.RESOUCE_CD(+)
    AND DIRHEAD.RECIPE_ID = RCPHEAD.RECIPE_ID(+)
    AND DIRHEAD.DIRECTION_DIVISION = /*directionDivision*/'2'
    AND DIRHEAD.DIRECTION_NO = /*pkgDirectionNo*/'H0904200001'
    AND DIRHEAD.DIRECTION_NO = FILL_TANK_NO.PKG_DIRECTION_NO(+)