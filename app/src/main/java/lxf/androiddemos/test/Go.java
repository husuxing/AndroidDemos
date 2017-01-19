package lxf.androiddemos.test;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lxf on 2017/1/19.
 */

public class Go {

    /**
     * status : 2
     * pos_y1 : 0
     * pos_y2 : 12
     * islei : false
     * qshids : []
     * signs : []
     * vote : 3.9
     * attr_type : 0
     * qtype : 1
     * id : 10355
     * points : []
     * title : 黑先胜
     * ineb : false
     * comments : []
     * specs : [{"pinyin":"liangbanchangyiqi","name":"两扳长一气"}]
     * lu : 19
     * blackfirst : true
     * luozis : []
     * username : admin
     * pos_x2 : 18
     * pos_x1 : 10
     * andata : [{"c":0,"subs":[1],"pt":"","f":0,"tip":"","o":0,"p":0,"u":0,"aids":[],"id":0},{"c":0,"subs":[2],"pt":"se","f":0,"tip":"","o":1,"p":0,"u":0,"aids":[100768,100767],"id":1},{"c":0,"subs":[3],"pt":"sd","f":0,"tip":"","o":1,"p":1,"u":0,"aids":[100768,100767],"id":2},{"c":0,"subs":[4],"pt":"sc","f":0,"tip":"","o":1,"p":2,"u":0,"aids":[100768,100767],"id":3},{"c":0,"subs":[5,6],"pt":"ra","f":0,"tip":"","o":1,"p":3,"u":0,"aids":[100768,100767],"id":4},{"c":0,"subs":[],"pt":"sf","f":0,"tip":"","o":1,"p":4,"u":0,"aids":[100768],"id":5},{"c":0,"subs":[],"pt":"sg","f":0,"tip":"","o":1,"p":4,"u":0,"aids":[100767],"id":6}]
     * edit_count : 0
     * answers : [{"username":"csong","change_count":0,"ty":1,"nu":2,"userid":1990,"st":2,"ok_count":1,"error_count":0,"bad_count":0,"v":0,"pts":[{"p":"se","c":" "},{"p":"sd","c":" "},{"p":"sc","c":" "},{"p":"ra","c":" "},{"p":"sf","c":" "}],"id":100768},{"username":"wrk99d","change_count":0,"ty":3,"nu":5,"userid":37163,"st":1,"ok_count":0,"error_count":0,"bad_count":1,"v":0,"pts":[{"p":"sc","c":" "},{"p":"ra","c":" "},{"p":"se","c":" "},{"p":"sa","c":" "}],"id":436037},{"username":"wrk99d","change_count":0,"ty":3,"nu":6,"userid":37163,"st":1,"ok_count":0,"error_count":0,"bad_count":1,"v":0,"pts":[{"p":"sc","c":" "},{"p":"ra","c":" "},{"p":"sg","c":" "},{"p":"sa","c":" "}],"id":436038},{"username":"csong","change_count":0,"ty":1,"nu":1,"userid":1990,"st":2,"ok_count":6,"error_count":0,"bad_count":0,"v":0,"pts":[{"p":"se","c":" "},{"p":"sd","c":" "},{"p":"sc","c":" "},{"p":"ra","c":" "},{"p":"sg","c":" "}],"id":100767}]
     * is_share : true
     * taskresult : {"ok_nums":[2,11,12,15,18,20,22,22,23,23,21,12,11,3,5,6,5,4,2,1,1,3,1,1,0,0,3,0,0,1],"ok_total":243,"fail_nums":[1,9,8,15,13,12,16,13,11,9,7,4,4,1,2,0,0,2,0,0,0,1,0,0,0,0,0,0,0,0],"fail_total":126}
     * prepos : [["sb","rc","rd","qd","qe","qf","pg","rh","qh","ph"],["rb","qb","qc","pc","pd","re","pe","rf","pf","rg","qg"]]
     * xuandians : []
     * sms_count : 9
     * sy : 13
     * sx : 9
     * name : null
     * nexterrorurl :
     * ans_count : 4
     * userid : 1
     * taotaiid : 0
     * bookinfos : [{"pinyin":"","bookname":"10k到1k＋的题","nodename":"杂","bookid":1883,"bookurl":1883}]
     * disuse : 2
     * isguess : false
     * qtypename : 死活题
     * myan : null
     * options : []
     * levelname : 8K
     */

    public boolean blackfirst;
    public int ans_count;
    public List<AndataBean> andata;
    public List<List<String>> prepos;

    public static Go objectFromData(String str) {

        return new Gson().fromJson(str, Go.class);
    }

    public static class TaskresultBean {
        /**
         * ok_nums : [2,11,12,15,18,20,22,22,23,23,21,12,11,3,5,6,5,4,2,1,1,3,1,1,0,0,3,0,0,1]
         * ok_total : 243
         * fail_nums : [1,9,8,15,13,12,16,13,11,9,7,4,4,1,2,0,0,2,0,0,0,1,0,0,0,0,0,0,0,0]
         * fail_total : 126
         */

        public int ok_total;
        public int fail_total;
        public List<Integer> ok_nums;
        public List<Integer> fail_nums;

        public static TaskresultBean objectFromData(String str) {

            return new Gson().fromJson(str, TaskresultBean.class);
        }
    }

    public static class SpecsBean {
        /**
         * pinyin : liangbanchangyiqi
         * name : 两扳长一气
         */

        public String pinyin;
        public String name;

        public static SpecsBean objectFromData(String str) {

            return new Gson().fromJson(str, SpecsBean.class);
        }
    }

    public static class AndataBean {
        /**
         * c : 0
         * subs : [1]
         * pt :
         * f : 0
         * tip :
         * o : 0
         * p : 0
         * u : 0
         * aids : []
         * id : 0
         */

        public int c;
        public String pt;
        public int f;
        public String tip;
        public int o;
        public int p;
        public int u;
        public int id;
        public List<Integer> subs;
        public List<?> aids;

        public static AndataBean objectFromData(String str) {

            return new Gson().fromJson(str, AndataBean.class);
        }
    }

    public static class AnswersBean {
        /**
         * username : csong
         * change_count : 0
         * ty : 1
         * nu : 2
         * userid : 1990
         * st : 2
         * ok_count : 1
         * error_count : 0
         * bad_count : 0
         * v : 0
         * pts : [{"p":"se","c":" "},{"p":"sd","c":" "},{"p":"sc","c":" "},{"p":"ra","c":" "},{"p":"sf","c":" "}]
         * id : 100768
         */

        public String username;
        public int change_count;
        public int ty;
        public int nu;
        public int userid;
        public int st;
        public int ok_count;
        public int error_count;
        public int bad_count;
        public int v;
        public int id;
        public List<PtsBean> pts;

        public static AnswersBean objectFromData(String str) {

            return new Gson().fromJson(str, AnswersBean.class);
        }

        public static class PtsBean {
            /**
             * p : se
             * c :
             */

            public String p;
            public String c;

            public static PtsBean objectFromData(String str) {

                return new Gson().fromJson(str, PtsBean.class);
            }
        }
    }

    public static class BookinfosBean {
        /**
         * pinyin :
         * bookname : 10k到1k＋的题
         * nodename : 杂
         * bookid : 1883
         * bookurl : 1883
         */

        public String pinyin;
        public String bookname;
        public String nodename;
        public int bookid;
        public int bookurl;

        public static BookinfosBean objectFromData(String str) {

            return new Gson().fromJson(str, BookinfosBean.class);
        }
    }
}
