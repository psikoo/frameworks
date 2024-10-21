echo off
set workingDir=%cd%
set desktop="%userprofile%\Desktop"
echo.

echo #--------------------------------#
echo ^| Starting maven automated setup ^|
echo #--------------------------------#

cd %desktop%
if not exist "%desktop%\cliframe" ( 
    mkdir "cliframe"
    cd "%desktop%\cliframe"
) else ( cd "%desktop%\cliframe" )

if exist README.md ( del README.md )
if exist start.cmd ( del start.cmd )
if exist ansi.cmd ( del ansi.cmd )
if exist cliframe.jar ( del cliframe.jar )
if exist cliframe-1-jar-with-dependencies.jar ( del cliframe-1-jar-with-dependencies.jar )

copy "%workingDir%\README.md" "%desktop%\cliframe" >NUL
copy "%workingDir%\src\main\resources\start.cmd" "%desktop%\cliframe" >NUL
copy "%workingDir%\src\main\resources\ansi.cmd" "%desktop%\cliframe" >NUL
copy "%workingDir%\target\cliframe-1-jar-with-dependencies.jar" "%desktop%\cliframe" >NUL

cd "%desktop%\cliframe"
ren cliframe-1-jar-with-dependencies.jar cliframe.jar

echo ^| Copying files                  ^|
echo #--------------------------------#