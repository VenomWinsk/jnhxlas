#/usr/bin
sql=$1
file=$2
pip_data=$3
echo $sql
cd /opt/hxht/hx_logprocess/hx-log
#/usr/bin/impala-shell -q "select * from hxht_maillog_db.t_iis_source limit 1000000" -B  --output_delimiter=',' --print_header | python3 pip_data.py $file
#/usr/bin/impala-shell -q "$sql" -B  --output_delimiter=',' --print_header | /usr/bin/python3 /opt/hxht/hx_logprocess/hx-log/pip_data.py $file
/usr/bin/impala-shell -q "$sql" -B  --output_delimiter=',' --print_header | /usr/bin/python3 $pip_data $file