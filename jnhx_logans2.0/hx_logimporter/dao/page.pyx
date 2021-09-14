from tools import hx_logger

logger = hx_logger.logging.getLogger(__name__)


def foreach(data, size):
    limit = size
    totalnum = len(data)
    totalpage = int(totalnum / limit) + 1
    logger.warning("总数" + str(totalnum) + "总页数" + str(totalpage))
    for ipage in range(0, totalpage):
        cheng = ipage * limit
        if cheng > totalnum:
            offset = cheng - totalnum
        else:
            offset = limit

        chunk = data[cheng:cheng + offset]
        yield chunk

