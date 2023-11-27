/*
 *　得意先グループ連携情報一覧(納入先)帳票用SQL
 *
 * entityName=OrderVenderLinkListForReport
 * packageName=ordervenderlinklistforreport
 * methodName=getDeliveryListForReport
 *
 */
WITH TMP( VENDER_GROUP_CD , BALANCE_CD , UPPER_BALANCE_CD, SHOP_LEVEL , LOOP_COUNT ) AS (
SELECT
    ORDER_VENDER_MASTER.VENDER_GROUP_CD
    , TO_NCHAR( ORDER_VENDER_MASTER.BALANCE_CD ) AS BALANCE_CD
    , NULL AS UPPER_BALANCE_CD
    , 1 AS SHOP_LEVEL
    , 1 AS LOOP_COUNT
FROM
    ORDER_VENDER_MASTER
WHERE
    ORDER_VENDER_MASTER.VENDER_GROUP_CD = /*venderGroupCd*/'001'
UNION ALL
SELECT
    TMP.VENDER_GROUP_CD
    , BALANCE.BALANCE_CD
    , BALANCE.UPPER_BALANCE_CD
    , BALANCE.SHOP_LEVEL
    , TMP.LOOP_COUNT + 1 AS LOOP_COUNT
FROM
    BALANCE
INNER JOIN
    TMP
ON
    TMP.BALANCE_CD = BALANCE.UPPER_BALANCE_CD
    AND TMP.SHOP_LEVEL + 1 = BALANCE.SHOP_LEVEL
WHERE
    TMP.LOOP_COUNT < 99
) , 
ORDER_VENDER_EX AS (
SELECT
    TMP.VENDER_GROUP_CD
    , TMP.BALANCE_CD
FROM
    TMP
GROUP BY
    TMP.VENDER_GROUP_CD
    , TMP.BALANCE_CD
)
SELECT
        TMP.SOM_DELIVERY_CD
    ,   MAX( TMP.DELIVERY_NAME ) AS DELIVERY_NAME
    ,   MAX( TMP.DELIVERY_FULL_NAME ) AS DELIVERY_FULL_NAME
    ,   MAX( TMP.ADDRESS ) AS ADDRESS
    ,   MAX( TMP.TEL_NO ) AS TEL_NO
    ,   MAX( TMP.ZIPCODE_NO ) AS ZIPCODE_NO
    ,   MAX( TMP.CARRY_NAME ) AS CARRY_NAME
    ,   MAX( TMP.DELIVERY_CD1 ) AS DELIVERY_CD1
    ,   MAX( TMP.DELIVERY_CD2 ) AS DELIVERY_CD2
    ,   MAX( TMP.DELIVERY_CD3 ) AS DELIVERY_CD3
FROM
(
    SELECT
        ORDER_VENDER_DELIVERY.SOM_DELIVERY_CD
    ,   DELIVERY.SEARCH_KANA DELIVERY_NAME
    ,   DELIVERY.DELIVERY_NAME1 || DELIVERY.DELIVERY_NAME2 DELIVERY_FULL_NAME
    ,   DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 ADDRESS
    ,   DELIVERY.TEL_NO
    ,   DELIVERY.ZIPCODE_NO
    ,   CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2 CARRY_NAME
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_01 DELIVERY_CD1
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_02 DELIVERY_CD2
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_03 DELIVERY_CD3
    FROM
        ORDER_VENDER_EX ORDER_VENDER_MASTER
    INNER JOIN
        ORDER_VENDER_DELIVERY
    ON
        ORDER_VENDER_MASTER.VENDER_GROUP_CD = ORDER_VENDER_DELIVERY.VENDER_GROUP_CD
    LEFT JOIN 
        DELIVERY 
    ON 
        DELIVERY.DELIVERY_CD = ORDER_VENDER_DELIVERY.SOM_DELIVERY_CD
    LEFT JOIN
        CARRY
    ON
        DELIVERY.CARRY_CD = CARRY.CARRY_CD
    GROUP BY
        ORDER_VENDER_DELIVERY.SOM_DELIVERY_CD
    ,   DELIVERY.SEARCH_KANA
    ,   DELIVERY.DELIVERY_NAME1 || DELIVERY.DELIVERY_NAME2
    ,   DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3
    ,   DELIVERY.TEL_NO
    ,   DELIVERY.ZIPCODE_NO
    ,   CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_01
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_02
    ,   ORDER_VENDER_DELIVERY.CTM_DELIVERY_CD_03
    UNION ALL
    SELECT
        SALES_TERMS.DELIVERY_CD AS SOM_DELIVERY_CD
    ,   DELIVERY.SEARCH_KANA AS DELIVERY_NAME
    ,   DELIVERY.DELIVERY_NAME1 || DELIVERY.DELIVERY_NAME2 AS DELIVERY_FULL_NAME
    ,   DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 AS ADDRESS
    ,   DELIVERY.TEL_NO
    ,   DELIVERY.ZIPCODE_NO
    ,   CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2 AS CARRY_NAME
    ,   NULL AS DELIVERY_CD1
    ,   NULL AS DELIVERY_CD2
    ,   NULL AS DELIVERY_CD3
    FROM
        SALES_TERMS
    INNER JOIN
        ORDER_VENDER_EX ORDER_VENDER_MASTER
    ON
        ORDER_VENDER_MASTER.BALANCE_CD = SALES_TERMS.BALANCE_CD
    INNER JOIN 
        DELIVERY
    ON 
        DELIVERY.DELIVERY_CD = SALES_TERMS.DELIVERY_CD
    LEFT JOIN
        CARRY
    ON
        DELIVERY.CARRY_CD = CARRY.CARRY_CD
    GROUP BY 
        SALES_TERMS.DELIVERY_CD
    ,   DELIVERY.SEARCH_KANA
    ,   DELIVERY.DELIVERY_NAME1 || DELIVERY.DELIVERY_NAME2
    ,   DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 
    ,   DELIVERY.TEL_NO
    ,   DELIVERY.ZIPCODE_NO
    ,   CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2
) TMP
GROUP BY
    TMP.SOM_DELIVERY_CD
ORDER BY
    TMP.SOM_DELIVERY_CD
