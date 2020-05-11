package com.hjwblog.robo_cmp.bean;

public class HttpResp {

        private HttpData data;
        private int code;
        private String msg;
        public void setData(HttpData data) {
            this.data = data;
        }
        public HttpData getData() {
            return data;
        }

        public void setCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

}
