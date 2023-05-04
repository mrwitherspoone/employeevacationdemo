#!/bin/sh


java -jar /app.jar &

nginx &
  

wait -n
  
# Exit with status of process that exited first
exit $?
