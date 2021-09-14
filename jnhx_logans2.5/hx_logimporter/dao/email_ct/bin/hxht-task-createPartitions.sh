#!/bin/bash

#
#hive create database and tables
#

echo "*******************************"
echo "******  创建分区脚本启动...    "
echo "******           "
echo "*******************************"


curPath=$(dirname $(readlink -f "$0"))
#echo $curPath

source ${curPath%/*}/config/count.conf
echo $root_dir

table_name=$1
project=$5
unit=$2
ana_obj=$3
rulegroup=$4
#rule=$6
#ip_part=$6
#username_part=$7
echo "tableName："$table_name "分区："$unit $ana_obj $rulegroup $project

hive -e "ALTER TABLE  hxht_maillog_db.$table_name   ADD  IF NOT EXISTS  PARTITION (unit='$unit',ana_obj='$ana_obj',rulegroup='$rulegroup',project='$project')"

impala-shell -q " INVALIDATE METADATA;"


echo "*******************************"
echo "******  创建分区脚本结束！  "
echo "******"
echo "*******************************"
