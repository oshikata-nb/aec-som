Option Explicit On
Option Strict On

Imports log4net

''' <summary>
''' DB処理クラス
''' </summary>
''' <remarks></remarks>
Public Class DatabaseAccess

#Region "定数"
    Private Const DB_TYPE_ORACLE As String = "ORACLE"
    Private Const DB_TYPE_SQLSERVER As String = "SQLSERVER"
#End Region

#Region "変数"
    ''' <summary>
    ''' DBマネージャ
    ''' </summary>
    ''' <remarks></remarks>
    Private _dbManager As AbstractDbManager = Nothing
    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private _log As ILog = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)
#End Region

#Region "構造体"
    ''' <summary>
    ''' DB接続情報
    ''' </summary>
    ''' <remarks></remarks>
    Public Structure DataBaseAccessInfo
        Public DbType As String
        Public DataSource As String
        Public InitialCatalog As String
        Public UserID As String
        Public PassWord As String

        Sub SetDataBaseAccessInfo(ByVal dbType As String, ByVal initialCatalog As String, ByVal userID As String _
                                  , ByVal passWord As String, ByVal dataSource As String)

            Me.DbType = dbType
            Me.InitialCatalog = initialCatalog
            Me.UserID = userID
            Me.PassWord = passWord
            Me.DataSource = dataSource

        End Sub
    End Structure
#End Region

#Region "プロパティ"
    ''' <summary>
    ''' LastSQL
    ''' </summary>
    ''' <value></value>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Property LastSQL() As String
        Get
            If Not _dbManager Is Nothing Then
                Return _dbManager.LastSQL
            Else
                Return String.Empty
            End If

        End Get
        Set(value As String)
            If Not _dbManager Is Nothing Then
                _dbManager.LastSQL = value
            Else
                '何もしない
            End If
        End Set
    End Property
#End Region

#Region "コンストラクタ"
    ''' <summary>
    ''' コンストラクタ
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub New()

    End Sub
#End Region

#Region "データベース接続"
    ''' <summary>
    ''' データベース接続
    ''' </summary>
    ''' <param name="accessInfo"></param>
    ''' <remarks></remarks>
    Public Sub DBConnect(ByVal accessInfo As DataBaseAccessInfo)

        Try
            Select Case accessInfo.DbType.ToUpper
                Case DB_TYPE_ORACLE
                    'TODO:Oracle使用時はコメント解除
                    '_dbManager = New OracleManager(accessInfo.DataSource _
                    '                            , accessInfo.UserID _
                    '                            , accessInfo.PassWord)
                Case DB_TYPE_SQLSERVER
                    _dbManager = New SqlserverManager(accessInfo.DataSource _
                                                     , accessInfo.InitialCatalog _
                                                     , accessInfo.UserID _
                                                     , accessInfo.PassWord)
                Case Else
                    'TODO
            End Select

        Catch ex As Exception
            _log.Error("DB接続失敗。DB_TYPE=" & accessInfo.DbType & "; USER ID=" & accessInfo.UserID & "; PASSWORD=" & accessInfo.PassWord & "; DATA SOURCE=" & accessInfo.DataSource & ";")
            Throw ex
        End Try
    End Sub
#End Region

#Region "データベース切断"
    ''' <summary>
    ''' データベース切断
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub DBDisconnect()

        If Not _dbManager Is Nothing Then
            _dbManager.Dispose()
        End If

    End Sub
#End Region

#Region "トランザクション開始"
    ''' <summary>
    ''' トランザクション開始
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub BeginTran()

        If Not _dbManager Is Nothing Then
            _dbManager.BeginTran()
        End If

    End Sub
#End Region

#Region "コミット"
    ''' <summary>
    ''' コミット
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub Commit()

        If Not _dbManager Is Nothing Then
            _dbManager.Commit()
        End If

    End Sub
#End Region

#Region "ロールバック"
    ''' <summary>
    ''' ロールバック
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub RollBack()

        If Not _dbManager Is Nothing Then
            _dbManager.RollBack()
        End If

    End Sub
#End Region

#Region "SQL実行（SELECT）"
    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetDataSet(ByVal wSql As String) As DataSet
        Dim ds As New DataSet

        If Not _dbManager Is Nothing Then
            ds = _dbManager.GetDataSet(wSql)
        End If

        Return ds

    End Function

    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <param name="tableName"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetDataSet(ByVal wSql As String, ByVal tableName As String) As DataSet
        Dim ds As New DataSet

        If Not _dbManager Is Nothing Then
            ds = _dbManager.GetDataSet(wSql, tableName)
        End If

        Return ds

    End Function
#End Region

#Region "SQL実行（SELECT以外）"
    ''' <summary>
    ''' SQL実行（SELECT以外）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function ExecuteSQL(ByVal wSql As String) As Integer
        Dim RecordCount As Integer

        If Not _dbManager Is Nothing Then
            RecordCount = _dbManager.ExecuteSQL(wSql)
        End If

        Return RecordCount

    End Function
#End Region

#Region "SQL関数変換"
    ''' <summary>
    ''' 部分文字列取得SQL文生成
    ''' </summary>
    ''' <param name="str">部分文字列抽出対象</param>
    ''' <param name="startPos">部分文字列抽出開始位置</param>
    ''' <param name="length">部分文字列の長さ</param>
    ''' <returns>部分文字列取得SQL</returns>
    ''' <remarks></remarks>
    Public Function MakeSQLSubString(str As String, startPos As Integer, length As Integer) As String
        Return _dbManager.MakeSQLSubString(str, startPos, length)
    End Function

    ''' <summary>
    ''' NULL置換文字列生成
    ''' </summary>
    ''' <param name="str">NULL判定対象文字列</param>
    ''' <param name="replaceStr">NULL置換文字列</param>
    ''' <returns>NULL置換関数文字列(SQL)</returns>
    ''' <remarks></remarks>
    Public Function MakeSQLConvertNULL(ByVal str As String, ByVal replaceStr As String) As String
        Return _dbManager.MakeSQLConvertNULL(str, replaceStr)
    End Function

    ''' <summary>
    ''' 文字列日付関数取得SQL文生成
    ''' </summary>
    ''' <param name="dateTime">変換したい日付</param>
    ''' <returns>変換後日時文字列</returns>
    ''' <remarks></remarks>
    Public Function ConvertSQLDateTime(ByVal dateTime As Date) As String
        Return _dbManager.ConvertSQLDateTime(dateTime)
    End Function

    ''' <summary>
    ''' Dual句取得
    ''' </summary>
    ''' <returns>Dual句</returns>
    ''' <remarks></remarks>
    Public Function GetDualSQL() As String
        Return _dbManager.GetDualSQL()
    End Function
#End Region

End Class
