# import logging
# logging.basicConfig(format='%(asctime)s - %(name)s - [process:%(process)s,thread:%(thread)s] : %(message)s')
# getLogger = logging.getLogger
import logging
from config import read_config


class Logger:
    def __init__(self, name=__name__):
        # 创建一个loggger
        self.__name = name
        self.logger = logging.getLogger(self.__name)
        self.logger.setLevel(logging.WARNING)

        logname = read_config.log_output_path
        fh = logging.FileHandler(logname, mode='a', encoding='utf-8')  # 不拆分日志文件，a指追加模式,w为覆盖模式
        fh.setLevel(logging.DEBUG)

        # formatter = logging.Formatter('%(asctime)s-%(name)s-%(filename)s-[line:%(lineno)d]'
        #                               '-%(levelname)s-[日志信息]: %(message)s',
        #                               datefmt='%a, %d %b %Y %H:%M:%S')
        formatter = logging.Formatter('%(asctime)s - %(name)s - [process:%(process)s,thread:%(thread)s] : %(message)s')
        fh.setFormatter(formatter)

        ch = logging.StreamHandler()
        ch.setLevel(logging.CRITICAL)
        ch.setFormatter(formatter)

        self.logger.addHandler(fh)
        self.logger.addHandler(ch)


    @property
    def get_log(self):
        """定义一个函数，回调logger实例"""
        return self.logger

