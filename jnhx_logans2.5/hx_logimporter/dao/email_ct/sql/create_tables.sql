
create database if not exists hxht_maillog_db;
use hxht_maillog_db;

create EXTERNAL table if not exists ${VAR:table_name}
(
${VAR:table_field}
)
partitioned by(unit string,ana_obj string,rulegroup string,project string)
sort by(req_ip)
STORED AS PARQUET
location '${VAR:path}';
