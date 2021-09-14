for i in `ls $1`
do
    iconv -c -f $2 -t $3 $i > $i.tmp
    echo "已处理${i}"
done