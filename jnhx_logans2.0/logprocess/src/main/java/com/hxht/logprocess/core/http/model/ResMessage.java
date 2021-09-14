package com.hxht.logprocess.core.http.model;

public class ResMessage {

    public int code;

    public String message;

    public Object data;





    /**
     * 生成成功消息
     * @param message 操作详细
     * @param data 数据包
     * @return
     */
    public static ResMessage genSucessMessage(String message, Object data){
        ResMessage resMessage = new ResMessage();

        resMessage.code = CodeDict.Success;
        resMessage.message = message;
        resMessage.data = data;

        return resMessage;
    }

    /**
     * 生成成功消息
     * @param message 操作详细
     * @param data 数据包
     * @return
     */
    public static ResMessage genSucessMessages(String number, String message, Object data){
        ResMessage resMessage = new ResMessage();

        //resMessage.number = number;
        resMessage.code = CodeDict.Success;
        resMessage.message = message;
        resMessage.data = data;

        return resMessage;
    }




    /**
     * 生成异常消息
     * @param code 操作详细
     * @param message 消息内容
     * @param data 数据包
     * @return
     */
    public static ResMessage genErrorMessage(int code, String message, Object data){
        ResMessage resMessage = new ResMessage();

        resMessage.code = code;
        resMessage.message = message;
        resMessage.data = data;

        return resMessage;
    }

    /**
     * 返回代码字典
     */
    public static class CodeDict{
        //操作成功
        public static int Success = 1000;
        //数据操作错误
        public static int DBError = 1;
        //业务服务错误
        public static int ServiceError = 101;
        //登录出错
        public static int LoginError = 102;

        //中文/英文名称重复
        public static int NameError = 105;
        //服务入口参数错误
        public static int ParamsError = 201;
        //工作流出错
        public static int ProcessError = 301;
        //查询数据为空
        public static int NoData = 100;
        //父节点禁止删除
        public static int UnNodeDel = 103;
        //30天未修改密码
        public static int passError = 202;

        //token失效
        public static int tokenEffect = 601;

        //异常
        public static int exceptionError = 501;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
