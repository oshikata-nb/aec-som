/*
* 入金承認操作可能チェック用SQL
*
* entityName=DepositCredit
* packageName=deposit
* methodName=checkApproval
*
*/
SELECT CREDIT_NO
FROM   CREDIT
WHERE  (DEPOSIT_UPDATE_DIVISION <> 0 -- 売掛更新未処理
       OR CLAIM_UPDATE_DIVISION <> 0 -- 請求更新未処理
       OR PAYABLE_UPDATE_DIVISION <> 0 -- 買掛更新未処理
       OR PAYMENT_UPDATE_DIVISION <> 0 -- 支払更新未処理
       OR ERASER_COMPLETE_DIVISION <> 0 -- 消込未完了
       OR TRANSMISSION_DATE IS NOT NULL -- 会計送信未処理
       OR ERASER_AMOUNT <> 0) -- 消込未処理
AND    CREDIT_NO = /*creditNo*/'%'
