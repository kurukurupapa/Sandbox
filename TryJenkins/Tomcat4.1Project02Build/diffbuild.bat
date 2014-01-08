@echo off

rem antを呼び出します。

set basedir=%~dp0
set batname=%~n0%~x0
set datestr=%DATE:/=%
set timestrtmp=%TIME: =0%
set timestr=%timestrtmp:~0,2%%timestrtmp:~3,2%%timestrtmp:~6,2%
set timestamp=%datestr%-%timestr%

:INIT
if "%1"=="" goto USAGE
rem set /p input=開始してよろしいですか？ (y/n[y])
rem if "%input%"=="" set input=y
rem if not "%input%"=="y" goto EOF

:MAIN
echo 処理開始します。



set target=%1
call ant.bat -f %basedir%\diffbuild.xml -lib %basedir%\lib\commons-net-3.3.jar %target%



:END
echo 正常終了です。
rem pause
exit /b 0

:USAGE
echo 使い方：%batname% target
exit /b 0

:ERROR
echo エラー終了です。
exit /b -1

:EOF
