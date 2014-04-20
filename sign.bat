@ECHO off
jarsigner -tsa https://timestamp.geotrust.com/tsa ClipServer.jar Clip
PAUSE