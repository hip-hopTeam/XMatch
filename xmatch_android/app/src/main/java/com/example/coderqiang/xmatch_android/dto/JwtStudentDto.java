package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/11/27.
 */

public class JwtStudentDto {

    /**
     * request :
     * status : 0
     * message : 成功获取学生信息
     * data : {"xh":"031502344","xm":"郑世强","xb":"男","sfzh":"","xz":"4","xssy":"上杭县                        ","nj":"2015","xyh":"003","xyhmc":"数学与计算机科学学院","zyhmc":"计算机科学与技术（卓越班）","zyh":"08060b","zyfx":"","zyfxmc":"","czkl":"","lxdh":"13799764784","QQ":"","email":""}
     */

    private String request;
    private int status;
    private String message;
    private DataBean data;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * xh : 031502344
         * xm : 郑世强
         * xb : 男
         * sfzh :
         * xz : 4
         * xssy : 上杭县
         * nj : 2015
         * xyh : 003
         * xyhmc : 数学与计算机科学学院
         * zyhmc : 计算机科学与技术（卓越班）
         * zyh : 08060b
         * zyfx :
         * zyfxmc :
         * czkl :
         * lxdh : 13799764784
         * QQ :
         * email :
         */

        private String xh;
        private String xm;
        private String xb;
        private String sfzh;
        private String xz;
        private String xssy;
        private String nj;
        private String xyh;
        private String xyhmc;
        private String zyhmc;
        private String zyh;
        private String zyfx;
        private String zyfxmc;
        private String czkl;
        private String lxdh;
        private String QQ;
        private String email;

        public String getXh() {
            return xh;
        }

        public void setXh(String xh) {
            this.xh = xh;
        }

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }

        public String getXb() {
            return xb;
        }

        public void setXb(String xb) {
            this.xb = xb;
        }

        public String getSfzh() {
            return sfzh;
        }

        public void setSfzh(String sfzh) {
            this.sfzh = sfzh;
        }

        public String getXz() {
            return xz;
        }

        public void setXz(String xz) {
            this.xz = xz;
        }

        public String getXssy() {
            return xssy;
        }

        public void setXssy(String xssy) {
            this.xssy = xssy;
        }

        public String getNj() {
            return nj;
        }

        public void setNj(String nj) {
            this.nj = nj;
        }

        public String getXyh() {
            return xyh;
        }

        public void setXyh(String xyh) {
            this.xyh = xyh;
        }

        public String getXyhmc() {
            return xyhmc;
        }

        public void setXyhmc(String xyhmc) {
            this.xyhmc = xyhmc;
        }

        public String getZyhmc() {
            return zyhmc;
        }

        public void setZyhmc(String zyhmc) {
            this.zyhmc = zyhmc;
        }

        public String getZyh() {
            return zyh;
        }

        public void setZyh(String zyh) {
            this.zyh = zyh;
        }

        public String getZyfx() {
            return zyfx;
        }

        public void setZyfx(String zyfx) {
            this.zyfx = zyfx;
        }

        public String getZyfxmc() {
            return zyfxmc;
        }

        public void setZyfxmc(String zyfxmc) {
            this.zyfxmc = zyfxmc;
        }

        public String getCzkl() {
            return czkl;
        }

        public void setCzkl(String czkl) {
            this.czkl = czkl;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
