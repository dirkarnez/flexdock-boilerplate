@echo off
SET JAVA_HOME=%USERPROFILE%\Downloads\jdk-11.0.2

if exist build (
    echo.Deleting previous build...
    rmdir /s /q build
)

xcopy /E /I %~dp0images\ %~dp0build\launch4j\images\

.\gradlew build & .\gradlew createExe

pause