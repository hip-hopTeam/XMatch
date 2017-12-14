package com.example.coderqiang.xmatch_android.dto;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/27.
 */

public class JwtCourseDto {

    /**
     * status : 0
     * message : 获取课表成功！
     * count : 15
     * data : [{"kkhm":"20170100300190001","xh":"031502344","xm":"郑世强","kkjhid":"201732793017140","kcdm":"00300190","jhxf":2,"sdxf":0,"kcmc":"计算机图形学","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"011081,015085","sksj":"1120,5120","skdd":"西3-104,西3-303","rkjs":"85118","rkjsxm":"谢伙生","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300063001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930172895","kcdm":"00300063","jhxf":3,"sdxf":0,"kcmc":"编译方法","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"011181,015175","sksj":"1520,5321","skdd":"西1-505,文2-204","rkjs":"16084","rkjsxm":"甘敏","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100310482001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930173735","kcdm":"00310482","jhxf":2,"sdxf":0,"kcmc":"数据挖掘技术","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"111181,115185","sksj":"1120,5120","skdd":"东2-301,东3-506","rkjs":"15041","rkjsxm":"苏雅茹","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100310718001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930174687","kcdm":"00310718","jhxf":2,"sdxf":0,"kcmc":"现代搜索引擎技术及应用","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"012112","sksj":"2930","skdd":"东3-306","rkjs":"09008","rkjsxm":"廖祥文","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300237001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930177090","kcdm":"00300237","jhxf":2,"sdxf":0,"kcmc":"宽带网及宽带接入技术","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"113183,115185","sksj":"3520,5720","skdd":"东3-305,东3-305","rkjs":"02005","rkjsxm":"孙及园","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300169001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930177747","kcdm":"00300169","jhxf":2,"sdxf":0,"kcmc":"计算方法","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"111181,114184","sksj":"1320,4720","skdd":"东3-506,东3-307","rkjs":"88005","rkjsxm":"白清源","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300198001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930177607","kcdm":"00300198","jhxf":2,"sdxf":0,"kcmc":"计算机网络体系结构","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"011081,013083","sksj":"1320,3720","skdd":"东3-306,东3-306","rkjs":"11065","rkjsxm":"郑相涵","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300202001","xh":"031502344","xm":"郑世强","kkjhid":"2017327930178144","kcdm":"00300202","jhxf":3,"sdxf":0,"kcmc":"计算机系统结构","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"012182,014184","sksj":"2122,4520","skdd":"西3-104,东3-307","rkjs":"11037","rkjsxm":"陈开志","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300254002","xh":"031502344","xm":"郑世强","kkjhid":"20175121116557055","kcdm":"00300254","jhxf":2,"sdxf":0,"kcmc":"人工智能","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"013083,015085","sksj":"3520,5720","skdd":"东3-306,东3-306","rkjs":"16097","rkjsxm":"王石平","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100310573002","xh":"031502344","xm":"郑世强","kkjhid":"20175121124205334","kcdm":"00310573","jhxf":3,"sdxf":0,"kcmc":"软件工程A","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"013183,025185","sksj":"3120,5322","skdd":"文2-105,东2-303","rkjs":"10044","rkjsxm":"张栋","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300480002","xh":"031502344","xm":"郑世强","kkjhid":"2017512112505334","kcdm":"00300480","jhxf":2,"sdxf":0,"kcmc":"软件工程实践","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"036146","sksj":"6140","skdd":"数计3-301","rkjs":"10044","rkjsxm":"张栋","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300387002","xh":"031502344","xm":"郑世强","kkjhid":"20175121138537055","kcdm":"00300387","jhxf":2,"sdxf":0,"kcmc":"现代计算机接口技术","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"012082,014084","sksj":"2320,4720","skdd":"东3-305,东3-305","rkjs":"95015","rkjsxm":"苏力","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100310612002","xh":"031502344","xm":"郑世强","kkjhid":"20175121139297055","kcdm":"00310612","jhxf":1.5,"sdxf":0,"kcmc":"现代计算机接口技术实践","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"047127","sksj":"7140","skdd":"数计3-402","rkjs":"95015","rkjsxm":"苏力","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100300126023","xh":"031502344","xm":"郑世强","kkjhid":"20175251046375795","kcdm":"00300126","jhxf":3,"sdxf":0,"kcmc":"概率论与数理统计","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"012082,112182,014084,114184","sksj":"2121,2121,4320,4320","skdd":"西3-103,西3-103,西3-103,西3-103","rkjs":"06412","rkjsxm":"何秀萍","bj":"","xxlx":"","xxlxmc":""},{"kkhm":"20170100100079006","xh":"031502344","xm":"郑世强","kkjhid":"2017327930177055","kcdm":"00100079","jhxf":2,"sdxf":0,"kcmc":"电气工程实践","kkxq":"201701","kslb":"001","kslbmc":"正常考考试","bfcj":"","shyh":"","qzz":"091105","sksj":"","skdd":"机电工程实践中心","rkjs":"83515","rkjsxm":"徐立宇","bj":"","xxlx":"","xxlxmc":""}]
     * request :
     */

    private int status;
    private String message;
    private int count;
    private String request;
    private List<DataBean> data;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * kkhm : 20170100300190001
         * xh : 031502344
         * xm : 郑世强
         * kkjhid : 201732793017140
         * kcdm : 00300190
         * jhxf : 2
         * sdxf : 0
         * kcmc : 计算机图形学
         * kkxq : 201701
         * kslb : 001
         * kslbmc : 正常考考试
         * bfcj :
         * shyh :
         * qzz : 011081,015085
         * sksj : 1120,5120
         * skdd : 西3-104,西3-303
         * rkjs : 85118
         * rkjsxm : 谢伙生
         * bj :
         * xxlx :
         * xxlxmc :
         */

        private String kkhm;
        private String xh;
        private String xm;
        private String kkjhid;
        private String kcdm;
        private float jhxf;
        private float sdxf;
        private String kcmc;
        private String kkxq;
        private String kslb;
        private String kslbmc;
        private String bfcj;
        private String shyh;
        private String qzz;
        private String sksj;
        private String skdd;
        private String rkjs;
        private String rkjsxm;
        private String bj;
        private String xxlx;
        private String xxlxmc;

        public String getKkhm() {
            return kkhm;
        }

        public void setKkhm(String kkhm) {
            this.kkhm = kkhm;
        }

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

        public String getKkjhid() {
            return kkjhid;
        }

        public void setKkjhid(String kkjhid) {
            this.kkjhid = kkjhid;
        }

        public String getKcdm() {
            return kcdm;
        }

        public void setKcdm(String kcdm) {
            this.kcdm = kcdm;
        }

        public float getJhxf() {
            return jhxf;
        }

        public void setJhxf(float jhxf) {
            this.jhxf = jhxf;
        }

        public float getSdxf() {
            return sdxf;
        }

        public void setSdxf(float sdxf) {
            this.sdxf = sdxf;
        }

        public String getKcmc() {
            return kcmc;
        }

        public void setKcmc(String kcmc) {
            this.kcmc = kcmc;
        }

        public String getKkxq() {
            return kkxq;
        }

        public void setKkxq(String kkxq) {
            this.kkxq = kkxq;
        }

        public String getKslb() {
            return kslb;
        }

        public void setKslb(String kslb) {
            this.kslb = kslb;
        }

        public String getKslbmc() {
            return kslbmc;
        }

        public void setKslbmc(String kslbmc) {
            this.kslbmc = kslbmc;
        }

        public String getBfcj() {
            return bfcj;
        }

        public void setBfcj(String bfcj) {
            this.bfcj = bfcj;
        }

        public String getShyh() {
            return shyh;
        }

        public void setShyh(String shyh) {
            this.shyh = shyh;
        }

        public String getQzz() {
            return qzz;
        }

        public void setQzz(String qzz) {
            this.qzz = qzz;
        }

        public String getSksj() {
            return sksj;
        }

        public void setSksj(String sksj) {
            this.sksj = sksj;
        }

        public String getSkdd() {
            return skdd;
        }

        public void setSkdd(String skdd) {
            this.skdd = skdd;
        }

        public String getRkjs() {
            return rkjs;
        }

        public void setRkjs(String rkjs) {
            this.rkjs = rkjs;
        }

        public String getRkjsxm() {
            return rkjsxm;
        }

        public void setRkjsxm(String rkjsxm) {
            this.rkjsxm = rkjsxm;
        }

        public String getBj() {
            return bj;
        }

        public void setBj(String bj) {
            this.bj = bj;
        }

        public String getXxlx() {
            return xxlx;
        }

        public void setXxlx(String xxlx) {
            this.xxlx = xxlx;
        }

        public String getXxlxmc() {
            return xxlxmc;
        }

        public void setXxlxmc(String xxlxmc) {
            this.xxlxmc = xxlxmc;
        }
    }
}
