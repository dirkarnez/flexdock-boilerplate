@echo off

cd build\launch4j

if exist jre (
    echo.Deleting previous JRE...
    rmdir /s /q jre
)

echo.Unzipping JRE...
"C:\Program Files\7-Zip\7z.exe" x ..\..\OpenJDK11U-jre_x64_windows_hotspot_11.0.13_8.zip -o.\
ren "jdk-11.0.13+8-jre" "jre"

pause