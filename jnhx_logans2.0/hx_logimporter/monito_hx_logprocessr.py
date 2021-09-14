import os
import sys
import time
from tools.hx_logger import Logger
logger = Logger(__name__).get_log


class Monitor:
    def __init__(self, name, path, type, status_path):
        self._class_type = 'logprocess'
        self._name = name
        self._path = path
        self._type = type
        self._status_path = status_path
        self._pids = []
        self._get_pid()

    def _get_pid(self):
        if self._type == 'py':
            get_pidcmd = "ps -fe | grep '" + self._path + "' | grep -v 'grep' | awk '{print $2}'"
        else:
            get_pidcmd = "lsof -i:8070 |grep -v COMMAND | awk '{print $2}'"
        programs = os.popen(get_pidcmd).read().strip()
        pids = programs.split('\n')
        for pid in pids:
            if pid != '':
                self._pids.append(pid)
        if self._pids:
            print(f"已存在{self._name}的进程:{self._pids}")

    def check_status(self):
        if self._type == 'py':
            # 若不存在运行状态存储文件或不存在进程id，则重新启动
            if not os.path.exists(self._status_path) or not self._pids:
                self.restart()
            else:
                with open(self._status_path, 'r')as f:
                    timeStamp = int(f.read())
                    if time.time() - timeStamp > 300:
                        self.restart()
        if self._type == 'jar':
            if not self._pids:
                self.restart()

    def stop(self):
        if self._pids:
            for pid in self._pids:
                os.popen(f"kill -15 {pid}")
                print(f"关闭程序 {pid}")
        print(f"检测到{self._name}未启动")

    def start(self):
        if not os.path.exists(f'/data/data1/log/'):
            os.mkdir(f'/data/data1/log/')
        if self._type == 'py':
            start_cmd = f'nohup /usr/bin/python3 {self._path} >> /data/data1/log/{self._class_type}_{self._name}.log 2>&1 &'
            os.system(start_cmd)

        if self._type == 'jar':
            start_cmd = "/usr/bin/bash /opt/hxht/hx_logprocess/hx_logimporter/qidong-java.sh"
            os.system(start_cmd)
            time.sleep(15)

        self._get_pid()
        if self._pids:
            print(f"程序{self._name}已启动，进程号为{self._pids}")
        else:
            print(f"{self._name}启动失败")

    def restart(self):
        self.stop()
        self.start()


if __name__ == '__main__':

    if len(sys.argv) != 2:
        print('Usage: {} [stand|start|stop]'.format(sys.argv[0]), file=sys.stderr)
        raise SystemExit(1)

    hx_logimporter = Monitor(name='hx_logimporter_main', type='py',
                             path='/opt/hxht/hx_logprocess/hx_logimporter/hx_logimporter_main.py',
                             status_path='/opt/hxht/hx_logprocess/hx_logimporter/data/run.flag')
    webcheck = Monitor(name='webcheck', type='py', path='/opt/hxht/hx_logprocess/webserver/webcheck.py',
                       status_path='/opt/hxht/hx_logprocess/webserver/data/run.flag')

    logprocess_SNAPSHOT = Monitor(name='logprocess-SNAPSHOT', type='jar',
                                  path='/opt/hxht/hx_logprocess/hx-log/logprocess-SNAPSHOT.jar',
                                  status_path=None)
    if sys.argv[1] == 'stand':
        hx_logimporter.check_status()
        webcheck.check_status()
        logprocess_SNAPSHOT.check_status()

    if sys.argv[1] == 'stop':
        hx_logimporter.stop()
        webcheck.stop()
        logprocess_SNAPSHOT.stop()

    if sys.argv[1] == 'start':
        hx_logimporter.start()
        webcheck.start()
        logprocess_SNAPSHOT.start()
