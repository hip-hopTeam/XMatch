package com.example.coderqiang.xmatch_android.dto;

/**
 * Created by coderqiang on 2017/11/27.
 */

public class JwtLoginDto {

    /**
     * request :
     * status : 0
     * message : 成功登录！
     * student : {"xh":"031502344","xm":"郑世强","xb":"男","sfzh":"","xz":"4","xssy":"上杭县                        ","nj":"2015","xyh":"003","xyhmc":"数学与计算机科学学院","zyhmc":"计算机科学与技术（卓越班）","zyh":"08060b","zyfx":"","zyfxmc":"","czkl":"","lxdh":"13799764784                   ","QQ":"","email":""}
     * tag : Sundry_2015,Sundry_003,Sundry_男,Sundry_080620,JW,KC_20150100900014000,KC_20150203600837104,KC_20150200900011335,KC_20150201100449046,KC_20150206700001052,KC_20150200300550005,KC_20150200310527045,KC_20150202800004103,KC_20150102700006023,KC_20150100310526022,KC_20150106800005087,KC_20150106700002117,KC_20150100300391018,KC_20150100300157002,KC_20150100300171002,KC_20150100300523002,KC_20150102700007075,KC_20150102900792033,KC_20150100900009041,KC_20150202700004043,KC_20150200300245001,KC_20150200300167001,KC_20150200310694001,KC_20150201100340044,KC_20150102800003103,KC_20160103600825074,KC_20160101100339060,KC_20160100310571001,KC_20160100300342001,KC_20160101100450034,KC_20160100300349003,KC_20160100300551003,KC_20160100900012039,KC_20160102700001101,KC_20160106800001060,KC_20160102800005119
     */

    private String request;
    private int status;
    private String message;
    private StudentBean student;
    private String tag;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static class StudentBean {
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
