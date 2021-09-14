from dao import mysql_opt_default
srcStr = "DATE1"
sql = f'select regex from basic_regex where name = "{srcStr}"'
cursor = mysql_opt_default.connect()
x =0
try:
    cursor.execute(sql)
    x = cursor.fetchone()[0]
except Exception as e:
    print(str(type(e))[8:-2])
    if str(type(e))[8:-2] == 'pymysql.err.ProgrammingError':
        print("no table")
    if str(type(e))[8:-2] == 'pymysql.err.InternalError':
        print("ziduan wrong")
    if str(type(e))[8:-2] == 'TypeError':
        print("ziduan wrong")


print("okkk")
cursor.close()