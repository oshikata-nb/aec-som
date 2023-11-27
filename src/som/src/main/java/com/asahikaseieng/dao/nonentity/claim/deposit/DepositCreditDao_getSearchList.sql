/*
* 入金入力（リスト)一覧検索用SQL
*
* entityName=DepositCredit
* packageName=deposit
* methodName=getSearchList
*
*/
SELECT CRE.CREDIT_DATE CREDIT_DATE --入金日付
      ,CRE.CREDIT_NO CREDIT_NO --伝票番号
      ,CRE.ROW_NO ROW_NO --行番号
      ,CRE.VENDER_CD VENDER_CD --請求先コード
      ,CRE.CATEGORY_DIVISION CATEGORY_DIVISION --分類コード
      ,CRE.CREDIT_AMOUNT CREDIT_AMOUNT --入金金額
      ,CRE.ERASER_AMOUNT ERASER_AMOUNT --消込額
      ,CRE.CREDIT_ERASER_AMOUNT CREDIT_ERASER_AMOUNT --入金消込額
      ,CRE.APPROVAL_STATUS APPROVAL_STATUS --承認ステータス
      ,CRE.TRANSMISSION_DATE --データ転送日時
      ,CLS.CATEGORY_NAME --入金分類名
      ,DECODE(CLS.DATA_TOTAL_DIVISION1
             ,NULL
             ,CLS.DATA_TOTAL_DIVISION0
             ,CLS.DATA_TOTAL_DIVISION1) DATA_TOTAL_DIVISION --ﾃﾞｰﾀ集計区分
      ,VEN.VENDER_NAME1 --取引先名
      ,VEN.VENDER_SHORTED_NAME --取引先略称
      ,DEPOSIT_UPDATE_DIVISION --売掛更新フラグ
      ,CLAIM_UPDATE_DIVISION --請求更新フラグ
      ,PAYABLE_UPDATE_DIVISION --買掛更新フラグ
      ,PAYMENT_UPDATE_DIVISION --支払更新フラグ
      ,ERASER_COMPLETE_DIVISION --消込完了フラグ
      ,CRE.ERASER_AMOUNT -- 消込額
FROM   (SELECT CATEGORY_NAME
              ,MAX(CATEGORY_DIVISION1) CATEGORY_DIVISION1
              ,MAX(DATA_TOTAL_DIVISION1) DATA_TOTAL_DIVISION1
              ,MAX(CATEGORY_DIVISION0) CATEGORY_DIVISION0
              ,MAX(DATA_TOTAL_DIVISION0) DATA_TOTAL_DIVISION0
              ,MAX(CATEGORY_DIVISION2) CATEGORY_DIVISION2
        FROM   (SELECT CLASS.CATEGORY_NAME
                      ,DECODE(CLASS.SHOW_FLG
                             ,1
                             ,CLASS.CATEGORY_DIVISION
                             ,NULL) CATEGORY_DIVISION2
                      ,DECODE(CLASS.AR_DIVISION
                             ,1
                             ,CLASS.CATEGORY_DIVISION
                             ,NULL) CATEGORY_DIVISION1
                      ,DECODE(CLASS.AR_DIVISION
                             ,1
                             ,CLASS.DATA_TOTAL_DIVISION
                             ,NULL) DATA_TOTAL_DIVISION1
                      ,DECODE(CLASS.AR_DIVISION
                             ,0
                             ,CLASS.CATEGORY_DIVISION
                             ,NULL) CATEGORY_DIVISION0
                      ,DECODE(CLASS.AR_DIVISION
                             ,0
                             ,CLASS.DATA_TOTAL_DIVISION
                             ,NULL) DATA_TOTAL_DIVISION0
                FROM   CLASSIFICATION CLASS
                       
                      ,(SELECT *
                        FROM   CLASSIFICATION
                        WHERE  DATA_TYPE = 2 --入金
                              
                              /*IF (( condition.categoryDivision != null ) && ( condition.categoryDivision != "" ))*/
                        AND    CATEGORY_DIVISION = /*condition.categoryDivision*/'1'
                              /*END*/
                        ) CLS
                
                WHERE  CLASS.DATA_TYPE = CLS.DATA_TYPE
                AND    CLASS.CATEGORY_NAME = CLS.CATEGORY_NAME) CLS
        GROUP  BY CATEGORY_NAME) CLS
      ,
       
       CLASSIFICATION CLASS
      ,VENDER         VEN
      ,CREDIT         CRE
      ,ORGANIZATION   ORG
      ,LOGIN          LGN
WHERE  CRE.DATA_TYPE = /*condition.dataType*/1
AND    CLASS.DATA_TYPE = 2 --入金
AND    VEN.VENDER_DIVISION(+) = 'TS' --TS得意先
AND    VEN.VENDER_CD(+) = CRE.VENDER_CD
AND    CLASS.CATEGORY_DIVISION(+) = CRE.CATEGORY_DIVISION
AND    ORG.ORGANIZATION_CD(+) = CRE.ORGANIZATION_CD
AND    LGN.TANTO_CD(+) = CRE.INPUTOR_CD
AND    (CLASS.CATEGORY_DIVISION = CLS.CATEGORY_DIVISION1 OR
      CLASS.CATEGORY_DIVISION = CLS.CATEGORY_DIVISION0 OR
      CLASS.CATEGORY_DIVISION = CLS.CATEGORY_DIVISION2)
      
      /*IF (( condition.organizationCd != null ) && ( condition.organizationCd != "" ))*/
AND    (CRE.ORGANIZATION_CD LIKE /*condition.organizationCd*/'%'
      OR ORG.ORGANIZATION_NAME LIKE /*condition.organizationCd*/'%')
      /*END*/
      
      /*IF (( condition.tantoCd != null ) && ( condition.tantoCd != "" ))*/
AND    (CRE.INPUTOR_CD LIKE /*condition.tantoCd*/'%'
      OR LGN.TANTO_NM LIKE /*condition.tantoCd*/'%')
      /*END*/
      
      /*IF (( condition.creditDateFrom != null ) && ( condition.creditDateFrom != "" ))*/
AND    CRE.CREDIT_DATE >=
       TO_DATE( /*condition.creditDateFrom*/'2009/06/30', 'YYYY/MM/DD') --FROM
      /*END*/
      
      /*IF (( condition.creditDateTo != null ) && ( condition.creditDateTo != "" ))*/
AND    CRE.CREDIT_DATE <=
       TO_DATE( /*condition.creditDateTo*/'2009/06/01', 'YYYY/MM/DD') --TO
      /*END*/
      
      /*IF (( condition.venderCd != null ) && ( condition.venderCd != "" ))*/
AND    (CRE.VENDER_CD LIKE /*condition.venderCd*/'%'
      OR VEN.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.venderCd*/'%'))
      /*END*/
      
      /*IF (( condition.creditNoFrom != null ) && ( condition.creditNoFrom != "" ))*/
AND    CRE.CREDIT_NO >= /*condition.creditNoFrom*/'%' --FROM
      /*END*/
      
      /*IF (( condition.creditNoTo != null ) && ( condition.creditNoTo != "" ))*/
AND    CRE.CREDIT_NO <= /*condition.creditNoTo*/'%' --TO
      /*END*/
      
      /*IF (( condition.approvalStatus != null ) && ( condition.approvalStatus != "0" ))*/
AND    CRE.APPROVAL_STATUS = /*condition.approvalStatus*/1
      /*END*/
      
      /*IF (( condition.issuedDivision != null ) && ( condition.issuedDivision != "" ))*/
AND    CRE.ISSUED_DIVISION = /*condition.issuedDivision*/0
      /*END*/

ORDER  BY CREDIT_DATE, CREDIT_NO, ROW_NO
