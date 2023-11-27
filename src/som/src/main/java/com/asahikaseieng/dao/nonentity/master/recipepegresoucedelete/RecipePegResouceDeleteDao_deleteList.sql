/*
 * 前工程設備一括削除用SQL
 *
 * entityName=RecipePegResouceDelete
 * packageName=recipepegresoucedelete
 * methodName=deleteList
 *
 */

DELETE
FROM RECIPE_PEG_RESOUCE
WHERE RESOUCE_CD = /*resouceCd*/'%'
