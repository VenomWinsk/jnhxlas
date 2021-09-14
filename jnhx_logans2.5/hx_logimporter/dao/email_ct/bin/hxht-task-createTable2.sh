#!/bin/bash

#
#hive create database and tables
#

echo "*******************************"
echo "******  创建表脚本启动...    "     
echo "******           "
echo "*******************************"


curPath=$(dirname $(readlink -f "$0"))
#echo $curPath

source ${curPath%/*}/config/count.conf
echo $root_dir

table_name=$1
table_field=$2

echo $table_name

echo $table_field
#hive --hivevar path=$root_dir --hivevar table_name=$table_name --hivevar table_field="$table_field" -f ${curPath%/*}/sql/create_tables.sql
hive --hivevar path=$root_dir --hivevar table_name=$table_name --hivevar table_field="$table_field" -f ${curPath%/*}/sql/create_tables.sql


#impala-shell --var=path=$root_dir --var=table_name=$table_name --var table_field="$table_field" -f ${curPath%/*}/sql/create_tables.sql



#刷新到impala
impala-shell -q " INVALIDATE METADATA;"



echo "*******************************"
echo "******  创建表脚本结束！  "
echo "******"
echo "*******************************"
