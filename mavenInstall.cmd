echo off
set workingDir=%cd%
set desktop="%userprofile%\Desktop"
echo.

echo #--------------------------------#
echo ^| Starting maven automated setup ^|
echo #--------------------------------#

cd %desktop%
if not exist "%desktop%\projectname" ( 
    mkdir "projectname"
    cd "%desktop%\projectname"
) else ( cd "%desktop%\projectname" )

if exist README.md ( del README.md )
if exist start.cmd ( del start.cmd )
if exist projectname.jar ( del projectname.jar )
if exist projectname-1-jar-with-dependencies.jar ( del projectname-1-jar-with-dependencies.jar )

copy "%workingDir%\README.md" "%desktop%\projectname" >NUL
copy "%workingDir%\src\main\resources\start.cmd" "%desktop%\projectname" >NUL
copy "%workingDir%\target\projectname-1-jar-with-dependencies.jar" "%desktop%\projectname" >NUL

cd "%desktop%\projectname"
ren projectname-1-jar-with-dependencies.jar projectname.jar

echo ^| Copying files                  ^|
echo #--------------------------------#