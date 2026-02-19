@echo off
setlocal EnableExtensions EnableDelayedExpansion

set "SRC_DIR=src\main\java"
set "OUT_DIR=out"
set "LIB_DIR=lib"

if not exist "%OUT_DIR%" mkdir "%OUT_DIR%"

REM --- Build classpath: out + every jar in lib ---
set "CP=%OUT_DIR%"
for %%j in ("%LIB_DIR%\*.jar") do set "CP=!CP!;%%~fj"

REM --- Collect all sources recursively ---
set "SOURCES_FILE=%OUT_DIR%\sources.txt"
del "%SOURCES_FILE%" 2>nul
for /r "%SRC_DIR%" %%f in (*.java) do echo %%f>>"%SOURCES_FILE%"

REM --- Compile ---
javac -encoding UTF-8 -d "%OUT_DIR%" -cp "!CP!" @"%SOURCES_FILE%"
if errorlevel 1 exit /b 1

REM --- Run ---
java -cp "!CP!" org.example.Main %*

endlocal
