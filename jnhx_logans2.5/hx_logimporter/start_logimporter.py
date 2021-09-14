import os
import sys
import time

from tools.hx_logger import Logger

logger = Logger(__name__).get_log

startcmd_pylog = "nohup /usr/bin/python3 /opt/hxht/hx_logprocess/hx_logimporter/hx_logimporter_main.py >> " \
           "/data/data0/log/hx_logimporter.log & "


def read_flag():
    try:
        with open("data/run.flag", 'r') as reader:
            s = reader.read()
            return s
    except Exception:
        return "0"


def stop():
    programs = os.popen("ps -fe | grep 'logimporter_main' | grep -v 'grep' | awk '{print $2}'").read().strip()
    ss = programs.split('\n')
    for pid in ss:
        os.popen("kill -9 " + pid)
        print("关闭程序" + pid)


def start():
    os.popen(startcmd_pylog)
    print("启动程序" + startcmd_pylog)


def restart():
    stop()
    start()


class HxDaemon:
    def run(self):
        sys.stdout.write('Daemon started with pid {}\n'.format(os.getpid()))
        while True:
            x = read_flag()
            if x == "0":
                time.sleep(60)
                continue
            xi = int(x)
            ct = int(time.time())
            spend = ct - xi
            if spend > 1000:
                restart()
            time.sleep(60)


if __name__ == '__main__':
    PIDFILE = '/tmp/daemon-example.pid'
    LOG = '/tmp/daemon-example.log'
    # daemon = HxDaemon(pidfile=PIDFILE, stdout=LOG, stderr=LOG)
    daemon = HxDaemon()

    if len(sys.argv) != 2:
        print('Usage: {} [start|stop]'.format(sys.argv[0]), file=sys.stderr)
        raise SystemExit(1)

    if 'start' == sys.argv[1]:
        start()
        daemon.run()
    elif 'stop' == sys.argv[1]:
        stop()
    elif 'restart' == sys.argv[1]:
        restart()
        daemon.run()
    else:
        daemon.run()
