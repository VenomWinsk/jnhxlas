from dao.hdfs_opt import HDFSOpt
import pandas as pd
import pyarrow.parquet as pq
import pyarrow as pa
conn = HDFSOpt()
fs = conn.get_fs()
data = {
    '性别': ['男', '女', '女', '男', '男'],
    '姓名': ['小明', '小红', '小芳', '大黑', '张三'],
    '年龄': [20, 21, 25, 24, 29]}
allchunk = pd.DataFrame(data, index=['one', 'two', 'three', 'four', 'five'],
                        columns=['姓名', '性别', '年龄', '职业'])

print(allchunk)
adf = pa.Table.from_pandas(allchunk)
size = len(allchunk)
# print(allchunk)
filepath = '/home/hxht_data/mlogs/ok.parquet'
pq.write_to_dataset(adf, root_path=filepath,
                    filesystem=fs)
fs.close()
print("输出" + str(size) + "条数据>>hdfs_parquet:")
