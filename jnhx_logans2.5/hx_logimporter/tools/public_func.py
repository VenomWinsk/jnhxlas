def genlistby(src, key):
    '''
    get a list from src list by key
    :param src:
    :param key:
    :return:
    '''
    result = []
    for one in src:
        result.append(one[key])
    return result