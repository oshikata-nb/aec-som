Option Explicit On
Option Strict On

Imports Oracle.DataAccess.Client
Imports System.Text
Imports log4net

''' <summary>
''' Oracle処理クラス
''' </summary>
''' <remarks></remarks>
Public Class OracleManager
    Inherits AbstractDbManager

#Region "変数"
    ''' <summary>
    ''' 接続リトライ回数
    ''' </summary>
    ''' <remarks></remarks>
    Private ReTryCount As String = System.Configuration.ConfigurationManager.AppSettings("ReTryCount")
    ''' <summary>
    ''' 接続待機時間
    ''' </summary>
    ''' <remarks></remarks>
    Private WaitTime As String = System.Configuration.ConfigurationManager.AppSettings("WaitTime")
    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private _log As ILog = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)
#End Region

#Region "コンストラクタ"
    ''' <summary>
    ''' コンストラクタ
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub New(ByVal dataSource As String, ByVal user As String, ByVal pass As String)
        Try

            ' ＤＢコネクション設定
            Dim connectionString As String = String.Format("Data Source={0};User ID={1};Password={2};Pooling=False;" _
                                                           , dataSource, user, pass)
            _dbCommand = New OracleCommand()

            If _dbConnection Is Nothing Then
                _dbConnection = New OracleConnection(connectionString)
            Else
                If _dbConnection.ConnectionString.Equals(connectionString) = False Then
                    _dbConnection.Close()
                End If
            End If

            ' ＤＢ接続
            Me.Open()

        Catch ex As Exception
            _log.Error(ex)
            Throw ex
        End Try

    End Sub
#End Region

#Region "データベース接続"
    ''' <summary>
    ''' Oracle接続
    ''' </summary>
    ''' <remarks></remarks>
    Protected Overrides Sub Open()
        Try
            ' DB接続
            If _dbConnection.State <> ConnectionState.Open Then
                _dbConnection.Open()
            End If

        Catch ex As Exception
            _log.Error(ex)

            If _dbConnection IsNot Nothing Then
                _dbConnection.Dispose()
            End If

            Throw ex
        End Try
    End Sub
#End Region

#Region "トランザクション開始"
    ''' <summary>
    ''' トランザクション開始
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub BeginTran()

        If Not _dbConnection Is Nothing Then
            _dbTransaction = _dbConnection.BeginTransaction
        End If

    End Sub
#End Region

#Region "コミット"
    ''' <summary>
    ''' コミット
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub Commit()

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Commit()
        End If

        _dbTransaction = Nothing

    End Sub
#End Region

#Region "ロールバック"
    ''' <summary>
    ''' ロールバック
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub RollBack()

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Rollback()
        End If

        _dbTransaction = Nothing

    End Sub
#End Region

#Region "Dispose"
    ''' <summary>
    ''' 終了処理
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub Dispose()

        If Not _dbConnection Is Nothing Then
            _dbConnection.Close()
            _dbConnection.Dispose()
            _dbConnection = Nothing
        End If

        If Not _dbCommand Is Nothing Then
            _dbCommand.Dispose()
            _dbCommand = Nothing
        End If

        If Not _dbDataAdapter Is Nothing Then
            CType(_dbDataAdapter, OracleDataAdapter).Dispose()
            _dbDataAdapter = Nothing
        End If

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Dispose()
            _dbTransaction = Nothing
        End If

        GC.SuppressFinalize(Me)
    End Sub
#End Region

#Region "SQL実行（SELECT）"
    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDataSet(ByVal wSql As String) As DataSet
        Dim ds As New DataSet
        Try
            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbDataAdapter = New OracleDataAdapter(wSql, CType(_dbConnection, OracleConnection))
                _dbDataAdapter.Fill(ds)
                LastSQL = ""
            End If

            Return ds

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            If ConnectReTry(ex) Then
                Try
                    _dbDataAdapter = New OracleDataAdapter(wSql, CType(_dbConnection, OracleConnection))
                    _dbDataAdapter.Fill(ds)
                    LastSQL = ""

                    Return ds
                Catch ex2 As Exception
                    _log.Error(ex2)
                    Throw ex2
                End Try
            End If
            Throw ex
        End Try
    End Function

    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDataSet(ByVal wSql As String, ByVal tableName As String) As DataSet

        Dim ds As New DataSet
        Try
            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbDataAdapter = New OracleDataAdapter(wSql, CType(_dbConnection, OracleConnection))
                CType(_dbDataAdapter, OracleDataAdapter).Fill(ds, tableName)
                LastSQL = ""
            End If

            Return ds

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            If ConnectReTry(ex) Then
                Try
                    _dbDataAdapter = New OracleDataAdapter(wSql, CType(_dbConnection, OracleConnection))
                    CType(_dbDataAdapter, OracleDataAdapter).Fill(ds, tableName)
                    LastSQL = ""

                    Return ds
                Catch ex2 As Exception
                    _log.Error(ex2)
                    Throw ex2
                End Try
            End If
            Throw ex
        End Try

    End Function
#End Region

#Region "SQL実行（SELECT以外）"
    ''' <summary>
    ''' SQL実行（SELECT以外）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function ExecuteSQL(ByVal wSql As String) As Integer
        Dim RecordCount As Integer
        Try
            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbCommand = New OracleCommand(wSql, CType(_dbConnection, OracleConnection))
                RecordCount = _dbCommand.ExecuteNonQuery()
                LastSQL = ""
            End If

            Return RecordCount

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            If ConnectReTry(ex) Then
                Try
                    _dbCommand = New OracleCommand(wSql, CType(_dbConnection, OracleConnection))
                    RecordCount = _dbCommand.ExecuteNonQuery()
                    LastSQL = ""

                    Return RecordCount
                Catch ex2 As Exception
                    _log.Error(ex2)
                    Throw ex2
                End Try
            End If

            Throw ex
        End Try

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
    Public Overrides Function MakeSQLSubString(str As String, startPos As Integer, length As Integer) As String
        Return " substr( " & str & ", " & startPos & ", " & length & ") "
    End Function

    ''' <summary>
    ''' NULL置換文字列生成
    ''' </summary>
    ''' <param name="str">NULL判定対象文字列</param>
    ''' <param name="replaceStr">NULL置換文字列</param>
    ''' <returns>NULL置換関数文字列(SQL)</returns>
    ''' <remarks></remarks>
    Public Overrides Function MakeSQLConvertNULL(ByVal str As String, ByVal replaceStr As String) As String
        Return " NVL(" & str & ",'" & replaceStr & "' )"
    End Function

    ''' <summary>
    ''' 文字列選択置換SQL文生成
    ''' </summary>
    ''' <param name="str">置換対象文字列リスト</param>
    ''' <param name="replaceStr">置換文字列リスト</param>
    ''' <param name="elseStr">例外文字列</param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function MakeSQLReplace(ByVal str As String, ByVal pattern As String(), ByVal replaceStr As String(), ByVal elseStr As String) As String
        ' SQL生成用
        Dim strSQL As New StringBuilder

        strSQL.Append("DECODE( " & str & " ,")

        For position As Integer = 0 To pattern.Length - 1
            strSQL.Append(" '" & pattern(position) & "', '" & replaceStr(position) & "', ")
        Next

        strSQL.Append(" '" & elseStr & "' ) AS EXT_TYPE")

        Return strSQL.ToString

    End Function
    ''' <summary>
    ''' 文字列日付関数取得SQL文生成
    ''' </summary>
    ''' <param name="dateTime">変換したい日付</param>
    ''' <returns>変換後日時文字列</returns>
    ''' <remarks></remarks>
    Public Overrides Function ConvertSQLDateTime(ByVal dateTime As Date) As String
        ' SQL生成用
        Dim strSQL As New StringBuilder

        strSQL.Append(" to_date('")
        strSQL.Append(dateTime.ToString("yyyy/MM/dd HH:mm:ss"))
        strSQL.Append("','yyyy/mm/dd hh24:mi:ss')")

        Return strSQL.ToString

    End Function

    ''' <summary>
    ''' Dual句SQL文取得
    ''' </summary>
    ''' <returns>Dual句</returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDualSQL() As String

        Return " FROM DUAL "

    End Function

#End Region

#Region "再接続処理"
    ''' <summary>
    ''' 再接続
    ''' </summary>
    ''' <param name="ex"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function ConnectReTry(ByVal ex As Exception) As Boolean

        'ORA-03135 : 接続が失われました()
        'ORA-12545: TNS: ターゲット・ホストまたはオブジェクトが見つかりません。
        'ORA-03114 : ORACLEに接続されていません。
        If ex.Message.IndexOf("ORA-03135") > -1 OrElse _
           ex.Message.IndexOf("ORA-12545") > -1 OrElse _
           ex.Message.IndexOf("ORA-03114") > -1 OrElse _
           _dbConnection.State = ConnectionState.Closed Then

            '再接続リトライ回数が設定されている場合
            If String.IsNullOrEmpty(ReTryCount.Trim) = False AndAlso IsNumeric(ReTryCount) Then
                ' ＤＢコネクション設定
                Dim connectionString As String = String.Format("Data Source={0};User ID={1};Password={2};Pooling=False;", _
                                                               System.Configuration.ConfigurationManager.AppSettings("Servicename"), _
                                                               System.Configuration.ConfigurationManager.AppSettings("UserName"), _
                                                               System.Configuration.ConfigurationManager.AppSettings("PassWord"))

                For cnt As Integer = 0 To Integer.Parse(ReTryCount) - 1

                    '待機時間
                    If String.IsNullOrEmpty(WaitTime.Trim) = False AndAlso IsNumeric(WaitTime) Then
                        Threading.Thread.Sleep(Integer.Parse(WaitTime) * 1000)
                    End If

                    '再接続設定
                    _dbCommand = New OracleCommand()
                    If _dbConnection.ConnectionString.Equals(connectionString) = False Then
                        _dbConnection.Close()
                    End If
                    _dbConnection = New OracleConnection(connectionString)

                    Try
                        _log.Info("DB Reconnect implementation")    '2015.6.25 SSV Chg
                        ' ＤＢ接続
                        Me.Open()
                    Catch ex2 As Exception
                        _log.Error(ex2)
                    End Try

                    If _dbConnection.State = ConnectionState.Open Then
                        '再接続　成功
                        Return True
                    End If
                Next
            End If
        End If
        Return False
    End Function
#End Region

End Class