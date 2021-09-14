import datetime
from tools.hx_logger import Logger
import urllib.parse
import re

logger = Logger(__name__).get_log


class InnerFunction(object):
    def splitParams(self, df, params, results):
        if not params or not results:
            logger.warning("splitParams输入参数有误")
            return df
        if len(params) < 2 or len(results) < 1:
            logger.warning("函数格式错误")
            return df
        fkey = params[0]
        fir = params[1]
        sec = params[2]

        pf = df[fkey].str.split(fir, expand=True)
        df = df.drop(columns=[fkey])
        index = 0
        for key in pf.columns:
            pf2 = pf[key].str.split(sec, expand=True)
            filed = results[index]
            index += 1
            if filed:
                df[filed] = pf2[1]
        return df

    ipc = None

    def setIpCity(self, ipc):
        self.ipc = ipc

    def geos(self, ip, ipc, countrys, citys):

        loc = ""
        pattern = re.compile(r'((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})(\.((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})){3}')
        if pattern.search(ip):
            loc = ipc.get_geo(ip)
        if loc:
            countrys.append(loc["contry"])
            citys.append(loc["qcc"])
        else:
            countrys.append("unkown")
            citys.append("unkown")

    def getGeo(self, df, params, results):
        fkey = params[0]

        countrys = []
        citys = []

        df[fkey].apply(lambda x: self.geos(x, self.ipc, countrys, citys))
        df[results[0]] = countrys
        df[results[1]] = citys
        return df

    def rmTmp(self, df):
        droplist = []
        for one in df.columns:
            if one[0:3] == "tmp":
                droplist.append(one)
        df = df.drop(columns=droplist)
        return df

    def pDate(self, input, format):
        t = datetime.datetime.strptime(input, format)
        t = str(t.date())
        return t

    def parseDate(self, df, params, results):
        fkey = params[0]
        format = params[1]
        tkey = results[0]
        # print("11123121")
        # (tkey)
        df[tkey] = df[fkey].apply(lambda x: self.pDate(x, format))
        return df

    def weichaiDate(self, input, format):
        d, m, y = input.split('/')
        month = {
            'Jan': '01',
            'Feb': '02',
            'Mar': '03',
            'Apr': '04',
            'May': '05',
            'Jun': '06',
            'Jul': '07',
            'Aug': '08',
            'Sept': '09',
            'Oct': '10',
            'Nov': '11',
            'Dec': '12',
        }
        m = month[m]
        t = f'{y}{m}{d}'
        t = datetime.datetime.strptime(t, format)
        t = str(t.date())
        return t

    def parseDate2(self, df, params, results):
        fkey = params[0]
        format = params[1]
        tkey = results[0]
        df[tkey] = df[fkey].apply(lambda x: self.weichaiDate(x, format))
        return df

    def pTime(self, input, format):
        t = datetime.datetime.strptime(input, format)
        t = str(t.time())
        return t

    def parseTime(self, df, params, results):
        fkey = params[0]
        format = params[1]
        tkey = results[0]
        df[tkey] = df[fkey].apply(lambda x: self.pTime(x, format))
        return df

    def addStr(self, df, params, results):
        fkey = params[0]
        tkey = results[0]
        addstr = params[1]
        df[tkey] = df[fkey] + addstr
        return df

    def getURL(self, df, params, results):
        fkey = params[0]
        df[results[0]] = df[fkey].apply(lambda x: urllib.parse.unquote(x))
        return df

