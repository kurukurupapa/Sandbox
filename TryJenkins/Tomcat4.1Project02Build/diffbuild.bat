@echo off

rem ant���Ăяo���܂��B

set basedir=%~dp0
set batname=%~n0%~x0
set datestr=%DATE:/=%
set timestrtmp=%TIME: =0%
set timestr=%timestrtmp:~0,2%%timestrtmp:~3,2%%timestrtmp:~6,2%
set timestamp=%datestr%-%timestr%

:INIT
if "%1"=="" goto USAGE
rem set /p input=�J�n���Ă�낵���ł����H (y/n[y])
rem if "%input%"=="" set input=y
rem if not "%input%"=="y" goto EOF

:MAIN
echo �����J�n���܂��B



set target=%1
call ant.bat -f %basedir%\diffbuild.xml -lib %basedir%\lib\commons-net-3.3.jar %target%



:END
echo ����I���ł��B
rem pause
exit /b 0

:USAGE
echo �g�����F%batname% target
exit /b 0

:ERROR
echo �G���[�I���ł��B
exit /b -1

:EOF
