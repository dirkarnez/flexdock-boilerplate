@echo off
SET JAVA_HOME=%USERPROFILE%\Downloads\jdk-11.0.2

if exist build (
    echo.Deleting previous build...
    rmdir /s /q build
)

.\gradlew build & .\gradlew createExe

pause