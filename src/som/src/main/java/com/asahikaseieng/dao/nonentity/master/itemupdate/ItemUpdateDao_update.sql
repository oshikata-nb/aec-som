/*
 * 有効品目更新用SQL
 *
 * entityName=ItemUpdate
 * packageName=itemupdate
 * methodName=update
 *
 */

INSERT INTO ITEM

	(SELECT ITEM_QUEUE.*
		   ,0
	 FROM   ITEM_QUEUE
	 WHERE  ITEM_CD = /*itemCd*/'%'
	 AND    VERSION = /*version*/1)
