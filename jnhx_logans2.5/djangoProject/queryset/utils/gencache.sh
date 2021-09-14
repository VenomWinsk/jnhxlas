#/usr/bin
sql=$1
echo $sql
/usr/bin/impala-shell -q "$sql"