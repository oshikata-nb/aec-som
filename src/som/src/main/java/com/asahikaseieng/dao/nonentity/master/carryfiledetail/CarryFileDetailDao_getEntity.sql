/*
 * 運送会社マスタ用SQL
 *
 * entityName=CarryFileDetail
 * packageName=carryfiledetail
 * methodName=getEntity
 *
 */
SELECT
    CARRY.CARRY_CD
    , CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2 AS CARRY_NAME
    , CARRY_FILE_LAYOUT.SEQ
    , CARRY_FILE_LAYOUT.HEADER_FLG
    , CARRY_FILE_LAYOUT.DATA_CLS
    , CARRY_FILE_LAYOUT.TABLE_NAME
	, USER_TAB_COMMENTS.COMMENTS AS TABLE_COMMENTS
    , CARRY_FILE_LAYOUT.COLUMN_NAME
	, USER_COL_COMMENTS.COMMENTS AS COL_COMMENTS
    , CARRY_FILE_LAYOUT.HEADER_NAME
    , CARRY_FILE_LAYOUT.LINK_FLG
    , CARRY_FILE_LAYOUT.DESCRIPTION
    , CARRY_FILE_LAYOUT.SUM_FLG
    , CARRY_FILE_LAYOUT.UPDATE_DATE 
    , CARRY_FILE_LAYOUT.INPUT_DATE
    , CARRY_FILE_LAYOUT.INPUTOR_CD
FROM
    CARRY
LEFT JOIN
    CARRY_FILE_LAYOUT
ON
    CARRY.CARRY_CD = CARRY_FILE_LAYOUT.CARRY_CD
LEFT JOIN
    USER_TAB_COMMENTS
ON
    USER_TAB_COMMENTS.TABLE_NAME = CARRY_FILE_LAYOUT.TABLE_NAME
    AND USER_TAB_COMMENTS.TABLE_NAME = CARRY_FILE_LAYOUT.TABLE_NAME
LEFT JOIN
    USER_COL_COMMENTS
ON
    USER_COL_COMMENTS.TABLE_NAME = CARRY_FILE_LAYOUT.TABLE_NAME
    AND USER_COL_COMMENTS.COLUMN_NAME = CARRY_FILE_LAYOUT.COLUMN_NAME
WHERE CARRY.CARRY_CD LIKE  /*carryCd*/'%'
ORDER BY SEQ ASC
