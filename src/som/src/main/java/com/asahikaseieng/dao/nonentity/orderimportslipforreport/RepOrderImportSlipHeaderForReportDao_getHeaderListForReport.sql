/*
 * entityName=RepOrderSlipHeaderForReport
 * packageName=orderslipforreport
 * methodName=getHeaderListForReport
 * 20191225 PG修正 バインド変数From S.Tanaka
 */
WITH TMP AS (
	SELECT
	    REP_SLIP_ORDER_IMPORT_HEADER.PK_NO
	FROM
	    REP_SLIP_ORDER_IMPORT_HEADER
	WHERE
	    REP_SLIP_ORDER_IMPORT_HEADER.PK_NO IS NOT NULL
    --受注グループ番号From
    /*IF (condition.srhFrstOrderNoFrom != null) && (condition.srhFrstOrderNoFrom != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.PK_NO >= /*condition.srhFrstOrderNoFrom*/'GR00000000' 
    /*END*/
    --受注グループ番号To
    /*IF (condition.srhFrstOrderNoTo!= null) && (condition.srhFrstOrderNoTo != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.PK_NO <= /*condition.srhFrstOrderNoTo*/'LR99999999' 
    /*END*/
    --客先注文番号
    /*IF (condition.srhCtmOrderNo != null) && (condition.srhCtmOrderNo != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.CUSTOMER_ORDER_NO LIKE /*condition.srhCtmOrderNo*/'00000000'
    /*END*/
    --得意先グループコード
    /*IF ( condition.srhVenderGroupCd != "0" ) && (condition.srhVenderGroupCd != "99999")*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.VENDER_GROUP_CD = /*condition.srhVenderGroupCd*/'%'
    /*END*/
    /*IF (condition.srhVenderGroupCd == "99999")*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.VENDER_GROUP_CD IS NULL
    /*END*/
    --取込日From
    /*IF (condition.srhImportDateFrom != null) && (condition.srhImportDateFrom != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.IMPORT_DATE, 'YYYY/MM/DD') >= /*condition.srhImportDateFrom*/'1900/01/01'
    /*END*/
    --取込日To
    /*IF (condition.srhImportDateTo!= null) && (condition.srhImportDateTo != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.IMPORT_DATE, 'YYYY/MM/DD') <= /*condition.srhImportDateTo*/'2199/01/01'
    /*END*/
    --受注番号From
    /*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.FIXED_ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000000' 
    /*END*/
    --受注番号To
    /*IF (condition.srhOrderNoTo!= null) && (condition.srhOrderNoTo != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.FIXED_ORDER_NO <= /*condition.srhOrderNoTo*/'JU99999999' 
    /*END*/
    -- 受注区分
    /*IF (condition.srhOrderDivision != 0)*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.ORDER_DIVISION = /*condition.srhOrderDivision*/3
    /*END*/
    --受注ステータス
    -- 受注ステータスが先付受注の場合
    /*IF (condition.srhOrderStatus == 90)*/
        AND REP_SLIP_ORDER_IMPORT_HEADER.FIXED_ORDER_NO IS NULL
    /*END*/
    -- ステータスが受注登録の場合
    /*IF (condition.srhOrderStatus == 99)*/
        AND REP_SLIP_ORDER_IMPORT_HEADER.FIXED_ORDER_NO IS NOT NULL
    /*END*/
                
    --受注区分が全ての場合
    /*IF (condition.srhOrderDivision == 0)*/
        -- ステータスが全てでは無い場合
        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 99) && (condition.srhOrderStatus != 90))*/
            -- 出荷ステータス検索
            AND (REP_SLIP_ORDER_IMPORT_HEADER.SHIPPING_STATUS_CD = /*condition.srhOrderStatus*/3
            
            -- ステータスが出荷未確定の場合
            /*IF (condition.srhOrderStatus == 1) */
            OR REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD = 7
            /*END*/
            
            -- ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
            /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
            OR REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD  > 1 AND REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD  < 6
            /*END*/
            
            -- ステータスが出荷未確定の場合
            /*IF (condition.srhOrderStatus == 5)*/
            OR REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD  = 6
            /*END*/
            )
        /*END*/
        
    /*END*/

    
    -- 受注区分が全てではなく　かつ　仕入直送品では無い場合
    /*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
        -- ステータスが全てでは無い場合
        /*IF ((condition.srhOrderStatus != 0) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
            AND REP_SLIP_ORDER_IMPORT_HEADER.SHIPPING_STATUS_CD = /*condition.srhOrderStatus*/3
        /*END*/
    /*END*/

    -- 受注区分が仕入直送品の場合
    /*IF ((condition.srhOrderDivision == 3) && (condition.srhOrderStatus != 90) && (condition.srhOrderStatus != 99))*/
        
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
        /*IF (condition.srhOrderStatus == 1) */
        AND REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD  = 7
        /*END*/
    
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定より上　かつ　ステータスが出荷確定未満
        /*IF (condition.srhOrderStatus > 1) && (condition.srhOrderStatus < 5)*/
        AND REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD < 6
        /*END*/
        
        -- 受注区分が仕入直送品　かつ　ステータスが出荷未確定の場合
        /*IF (condition.srhOrderStatus == 5)*/
        AND REP_SLIP_ORDER_IMPORT_HEADER.PURCHASE_STATUS_CD = 6
        /*END*/
    
    /*END*/
    --受注日From
    /*IF (condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'1900/01/01'
    /*END*/
    --受注日To
    /*IF (condition.srhOrderDateTo!= null) && (condition.srhOrderDateTo != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2199/01/01'
    /*END*/
    --納入先
    /*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
    AND	(REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
        OR REP_SLIP_ORDER_IMPORT_HEADER.SEARCH_KANA LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_CD_01 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_CD_02 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_CD_03 LIKE /*condition.srhDeliveryCd*/'%' 
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_NAME_01 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_NAME_02 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_NAME_03 LIKE /*condition.srhDeliveryCd*/'%'
    )
    /*END*/
    --住所
    /*IF (condition.srhAddress != null) && (condition.srhAddress != "")*/
    AND (REP_SLIP_ORDER_IMPORT_HEADER.ADDRESS1 || REP_SLIP_ORDER_IMPORT_HEADER.ADDRESS2 || REP_SLIP_ORDER_IMPORT_HEADER.ADDRESS3 LIKE /*condition.srhAddress*/'%'
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_ADDRESS_01 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_ADDRESS_02 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_ADDRESS_03 LIKE /*condition.srhAddress*/'%')
    /*END*/
    --納入先電話番号
    /*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
    AND (REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%')
    /*END*/
    --客先返信
    /*IF (condition.srhSlipPublishComp == "1")*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_DATE_SEND_DATE IS NULL
    /*END*/
    /*IF (condition.srhSlipPublishComp == "2")*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_DATE_SEND_DATE IS NOT NULL
    /*END*/
    --出荷予定日From
    /*IF (condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'1900/01/01'
    /*END*/
    --出荷予定日To
    /*IF (condition.srhScheduledShippingDateTo!= null) && (condition.srhScheduledShippingDateTo != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2199/01/01'
    /*END*/
    --得意先
    /*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
    AND (REP_SLIP_ORDER_IMPORT_HEADER.VENDER_CD LIKE /*condition.srhVenderCd*/'%' 
        OR REP_SLIP_ORDER_IMPORT_HEADER.VENDER_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
        --2次店以下で検索
        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_HEADER.BALANCE_CD ,2) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_HEADER.BALANCE_CD ,3) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_HEADER.BALANCE_CD ,4) = REPLACE (/*condition.srhVenderCd*/'','%','')
        OR FNC_GET_UPPER_VENDER(REP_SLIP_ORDER_IMPORT_HEADER.BALANCE_CD ,5) = REPLACE (/*condition.srhVenderCd*/'','%','')
        --客先得意先で検索
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_CD_01 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_CD_02 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_CD_03 LIKE /*condition.srhVenderCd*/'%' 
        OR REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_NAME_01 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_NAME_02 || REP_SLIP_ORDER_IMPORT_HEADER.CTM_VENDER_NAME_03 LIKE /*condition.srhVenderCd*/'%'
    )
    /*END*/
    --担当部署
    /*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
    AND	(REP_SLIP_ORDER_IMPORT_HEADER.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR REP_SLIP_ORDER_IMPORT_HEADER.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
    /*END*/
    --仮単価FLG
    /*IF (condition.srhTmpUnitpriceFlg != "9")*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.TMP_UNITPRICE_FLG = /*condition.srhTmpUnitpriceFlg*/0
    /*END*/
    --営業担当者
    /*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
    AND	(REP_SLIP_ORDER_IMPORT_HEADER.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR REP_SLIP_ORDER_IMPORT_HEADER.SALES_TANTO_NAME LIKE /*condition.srhSalesTantoCd*/'%')
    /*END*/
    --入力担当者
    /*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
    AND	(REP_SLIP_ORDER_IMPORT_HEADER.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR REP_SLIP_ORDER_IMPORT_HEADER.INPUT_TANTO_NAME LIKE /*condition.srhInputTantoCd*/'%')
    /*END*/
    --出荷BC
    /*IF (condition.srhCarryBc != null) && (condition.srhCarryBc != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.CARRY_BC LIKE /*condition.srhCarryBc*/'%'
    /*END*/
    --品目
    /*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
    AND	(REP_SLIP_ORDER_IMPORT_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR REP_SLIP_ORDER_IMPORT_HEADER.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
    /*END*/
    --他社コード1
    /*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
    /*END*/
    --希望納期From
    /*IF (condition.srhSuggestedDeliverlimitFrom != null) && (condition.srhSuggestedDeliverlimitFrom != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitFrom*/'1900/01/01'
    /*END*/
    --希望納期To
    /*IF (condition.srhSuggestedDeliverlimitTo != null) && (condition.srhSuggestedDeliverlimitTo != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.SUGGESTED_DELIVERLIMIT, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitTo*/'2199/01/01'
    /*END*/
    --運送会社
    /*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
        /*IF ( condition.srhCarryCd != "0" )*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
        /*END*/
    /*END*/
    --エラー状態
    /*IF ( condition.srhErrorStatus == "1" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.ERROR_FLG = '1'
    /*END*/
    /*IF ( condition.srhErrorStatus == "2" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.ERROR_FLG = '0'
    /*END*/

    --データ取込区分
    /*IF ( condition.srhInputDivision == "1" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.ORDER_IMP_NO IS NOT NULL  
    /*END*/
    /*IF ( condition.srhInputDivision == "2" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.ORDER_IMP_NO IS NULL  
    /*END*/

    --納品予定日From
    /*IF (condition.srhDeliveryExpectedDateFrom != null) && (condition.srhDeliveryExpectedDateFrom != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') >= /*condition.srhDeliveryExpectedDateFrom*/'1900/01/01'
    /*END*/
    --納品予定日To
    /*IF (condition.srhDeliveryExpectedDateTo != null) && (condition.srhDeliveryExpectedDateTo != "")*/
    AND TO_CHAR(REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_EXPECTED_DATE, 'YYYY/MM/DD') <= /*condition.srhDeliveryExpectedDateTo*/'2199/01/01'
    /*END*/
    --取込番号From
   	/*IF (condition.srhTempNoFrom != null) && (condition.srhTempNoFrom != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.ORDER_IMP_NO >= /*condition.srhTempNoFrom*/'OI000000001' 
    /*END*/
    --取込番号To
    /*IF (condition.srhTempNoTo != null) && (condition.srhTempNoTo != "")*/
    AND	REP_SLIP_ORDER_IMPORT_HEADER.ORDER_IMP_NO <= /*condition.srhTempNoTo*/'OI999999999' 
    /*END*/
    --入力チェック
    /*IF ( condition.srhOrderInputCheck == "1" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.INPUT_APPROVAL_DATE IS NULL
    /*END*/
    /*IF ( condition.srhOrderInputCheck == "2" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.INPUT_APPROVAL_DATE IS NOT NULL
    /*END*/
    --納期連絡
    /*IF ( condition.srhDelDateSend == "1" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_DATE_SEND_DATE IS NULL
    /*END*/
    /*IF ( condition.srhDelDateSend == "2" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_DATE_SEND_DATE IS NOT NULL
    /*END*/
    --削除・キャンセル
    /*IF ( condition.srhDeleteCancel == "0" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_FLG = 0
    /*END*/
    /*IF ( condition.srhDeleteCancel == "1" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.DEL_FLG = 1
    /*END*/
    /*IF ( condition.srhDeleteCancel == "2" )*/
    AND REP_SLIP_ORDER_IMPORT_HEADER.CANCEL_FLG = 1
    /*END*/
    /*IF ( condition.srhDeleteCancel == "3" )*/
    AND ( REP_SLIP_ORDER_IMPORT_HEADER.DEL_FLG = 1 OR REP_SLIP_ORDER_IMPORT_HEADER.CANCEL_FLG = 1 )
    /*END*/
    /*IF ( condition.srhDeleteCancel == "4" )*/
    AND ( REP_SLIP_ORDER_IMPORT_HEADER.DEL_FLG <> 1 AND REP_SLIP_ORDER_IMPORT_HEADER.CANCEL_FLG <> 1 )
    /*END*/
    )
SELECT
    REP_SLIP_ORDER_IMPORT_HEADER.ORDER_NO
,   REP_SLIP_ORDER_IMPORT_HEADER.PK_NO
,   REP_SLIP_ORDER_IMPORT_HEADER.PK_ROW
,   REP_SLIP_ORDER_IMPORT_HEADER.ORDER_DIVISION
,   REP_SLIP_ORDER_IMPORT_HEADER.DIVISION_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.ORDER_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.ORGANIZATION_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.ORGANIZATION_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.INPUT_TANTO_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.INPUT_TANTO_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.SALES_TANTO_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.SALES_TANTO_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.VENDER_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.VENDER_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_ADDRESS
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_ADDRESS_ALL
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_TEL_NO
,   REP_SLIP_ORDER_IMPORT_HEADER.BALANCE_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.STATUS
,   REP_SLIP_ORDER_IMPORT_HEADER.CUSTOMER_ORDER_NO
,   REP_SLIP_ORDER_IMPORT_HEADER.ORDER_AMOUNT
,   REP_SLIP_ORDER_IMPORT_HEADER.SUGGESTED_DELIVERLIMIT
,   REP_SLIP_ORDER_IMPORT_HEADER.SCHEDULED_SHIPPING_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.LEAD_TIME
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_EXPECTED_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_EXPECTED_TIME
,   REP_SLIP_ORDER_IMPORT_HEADER.CARRY_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.CARRY_NAME1
,   REP_SLIP_ORDER_IMPORT_HEADER.CARRY_FARE
,   REP_SLIP_ORDER_IMPORT_HEADER.CARRY_INVOICE_FLAG
,   REP_SLIP_ORDER_IMPORT_HEADER.CARRY_INVOICE_STRING
,   REP_SLIP_ORDER_IMPORT_HEADER.ORDER_PICTURE
,   REP_SLIP_ORDER_IMPORT_HEADER.PRINT_SUMMERY
,   REP_SLIP_ORDER_IMPORT_HEADER.DELIVERY_SLIP_SUMMERY
,   REP_SLIP_ORDER_IMPORT_HEADER.ORDER_SUMMERY
,   REP_SLIP_ORDER_IMPORT_HEADER.SLIP_PUBLISH_COMP
,   REP_SLIP_ORDER_IMPORT_HEADER.SLIP_PUBLISH_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.INPUT_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.INPUTOR_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.INPUTOR_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.UPDATE_DATE
,   REP_SLIP_ORDER_IMPORT_HEADER.UPDATOR_CD
,   REP_SLIP_ORDER_IMPORT_HEADER.UPDATOR_NAME
,   REP_SLIP_ORDER_IMPORT_HEADER.ORG_ZIPCODE
,   REP_SLIP_ORDER_IMPORT_HEADER.ORG_ADDRESS
,   REP_SLIP_ORDER_IMPORT_HEADER.ORG_TEL_NO
,   REP_SLIP_ORDER_IMPORT_HEADER.ORG_FAX_NO
FROM
    REP_SLIP_ORDER_IMPORT_HEADER
WHERE
	REP_SLIP_ORDER_IMPORT_HEADER.PK_NO IN ( SELECT PK_NO FROM TMP )
ORDER BY
    REP_SLIP_ORDER_IMPORT_HEADER.PK_NO ASC ,  REP_SLIP_ORDER_IMPORT_HEADER.PK_ROW
