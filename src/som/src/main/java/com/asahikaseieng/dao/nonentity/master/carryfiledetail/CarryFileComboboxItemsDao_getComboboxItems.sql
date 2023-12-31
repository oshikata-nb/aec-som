/*
 * 運送会社連携ファイルマスタコンボボックス取得用SQL
 *
 * entityName=CarryFileComboboxItems
 * packageName=carryfiledetail
 * methodName=getComboboxItems
 *
 */
SELECT 
    USER_TAB_COMMENTS.TABLE_NAME
    , USER_TAB_COMMENTS.COMMENTS AS TABLE_COMMENTS
    , USER_COL_COMMENTS.COLUMN_NAME
    , CASE WHEN INSTR( USER_COL_COMMENTS.COMMENTS , '|' ) <> 0 THEN
         SUBSTR( USER_COL_COMMENTS.COMMENTS , 0 ,  INSTR( USER_COL_COMMENTS.COMMENTS , '|' ) - 1 ) 
        ELSE SUBSTR( USER_COL_COMMENTS.COMMENTS , 0 , 25 ) END AS  COLUMNS_COMMENTS
FROM   
    USER_COL_COMMENTS
LEFT JOIN
    USER_TAB_COMMENTS
ON
    USER_TAB_COMMENTS.TABLE_NAME = USER_COL_COMMENTS.TABLE_NAME
WHERE USER_COL_COMMENTS.TABLE_NAME IN ( 'V_SHIPPING' , 'V_SHIPPING_DETAIL' , 'ORDER_HEAD' , 'ORDER_DETAIL' , 'DELIVERY' , 'VENDER' , 'ITEM' , 'FRST_ORDER_HEAD' , 'FRST_ORDER_DETAIL' )
AND NOT USER_COL_COMMENTS.COMMENTS LIKE '%未使用%'
ORDER BY USER_TAB_COMMENTS.COMMENTS , CASE WHEN INSTR( USER_COL_COMMENTS.COMMENTS , '|' ) <> 0 THEN
         SUBSTR( USER_COL_COMMENTS.COMMENTS , 0 ,  INSTR( USER_COL_COMMENTS.COMMENTS , '|' ) - 1 ) 
        ELSE SUBSTR( USER_COL_COMMENTS.COMMENTS , 0 , 25 )  END
