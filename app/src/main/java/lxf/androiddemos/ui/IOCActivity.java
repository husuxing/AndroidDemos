package lxf.androiddemos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lxf.androiddemos.R;
import lxf.androiddemos.test.Go;
import lxf.ioc.InjectUtil;
import lxf.ioc.annotation.ContentView;

@ContentView(R.layout.activity_ioc)
public class IOCActivity extends AppCompatActivity {
    private Go game;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUtil.init(this);
    }

    public void getGoInfo(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String resultData = "";
                    URL url = new URL("http://www.101weiqi.com/spec/liumusihuo/4427/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream is = connection.getInputStream();   //获取输入流，此时才真正建立链接
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferReader = new BufferedReader(isr);
                    String inputLine = "";
                    String next = "";

                    while (!resultData.endsWith("}")) {
                        while ((inputLine = bufferReader.readLine()) != null) {
                            resultData += inputLine + "\n";
                        }
                        int nextStart = resultData.indexOf("上 一 题");
                        if (nextStart == -1) {
                            nextStart = resultData.indexOf("第1题");
                        }
                        int nextEnd = resultData.indexOf("下 一 题");

                        if (nextEnd != -1) {
                            next = resultData.substring(nextStart, nextEnd);
                            nextStart = next.indexOf("href=");
                            nextEnd = next.lastIndexOf(">");
                            next = next.substring(nextStart + 6, nextEnd - 1);
                        } else {
                            next = "";
                        }

                        int start = resultData.indexOf("var g_qq");
                        int end = resultData.indexOf("var taskinfo");
                        resultData = resultData.substring(start, end);

                        start = resultData.indexOf("{");
                        end = resultData.lastIndexOf("}");
                        resultData = resultData.substring(start, end + 1);

//                            System.out.println(resultData);

                        start = resultData.indexOf("andata");
                        end = resultData.indexOf("edit_count");
                        String s1 = resultData.substring(0, start);
                        String s2 = resultData.substring(start, end);
                        String s3 = resultData.substring(end, resultData.length());
                        s2 = s2.replaceAll("\\\"[0-9]{1,}\\\"\\:", "");
                        s2 = s2.replaceFirst("\\{", "[");
                        s2 = new StringBuffer(s2).reverse().toString();
                        s2 = s2.replaceFirst("\\}", "]");
                        s2 = new StringBuffer(s2).reverse().toString();

                        resultData = s1 + s2 + s3;
                    }

//                    System.out.println(resultData);

                    game = Go.objectFromData(resultData);
                    List<String> black = game.prepos.get(0);
                    List<String> white = game.prepos.get(1);
                    getAnswers(0);
//                    System.out.println("黑棋：" + black);
//                    System.out.println("白棋：" + white);
//                    System.out.println("正解：" + answerList);
                    StringBuilder sgf = new StringBuilder();
                    sgf.append("(;AB");
                    for (String s : black) {
                        sgf.append("[").append(s).append("]");
                    }
                    sgf.append("AW");
                    for (String s : white) {
                        sgf.append("[").append(s).append("]");
                    }
                    sgf.append("C[").append(game.blackfirst ? "黑先" : "白先").append("]");
                    for (int i = 0; i < answerList.size(); i++) {
                        sgf.append("(");
                        String[] s = answerList.get(i).split(",");
                        for (int j = 0; j < s.length; j++) {
                            String s1 = "";
                            String s2 = "";
                            if (s[j].length() > 2) {
                                s1 = s[j].substring(0, 2);
                                s2 = decode(s[j].substring(2));
                            } else {
                                s1 = s[j];
                                s2 = "";
                            }
                            sgf.append(";")
                                    .append(j % 2 == 0 ? game.blackfirst ? "B" : "W" : game.blackfirst ? "W" : "B")
                                    .append("[").append(s1).append("]");
//                            if (!s2.equals("")) {
                            sgf.append("C[").append(s2);
                            if (j != s.length - 1)
                                sgf.append("]");
//                            }
                        }
                        if (success.contains(i))
                            sgf.append("【正解图】]");
                        else
                            sgf.append("【失败图】]");
                        sgf.append(")");
                    }
                    sgf.append(")");
                    System.out.println(next);
                    System.out.println(game.lu);
                    System.out.println("sgf：" + sgf.toString());
                    answerList.clear();
                    array.clear();
                    success.clear();
                    jiedian.clear();
                    answer = "";

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    List<String> answerList = new ArrayList<>();//正解集合
    //    HashMap<Integer,String> array = new HashMap<>();
    List<String> array = new ArrayList<>();
    List<Integer> success = new ArrayList<>();
    //    int m = 0;//为array排序
    String answer = "";
    List<Integer> jiedian = new ArrayList<>();
    int m = 0;

    /**
     * @param n 从0开始
     */
    private void getAnswers(int n) {
        if (hasNext(game.andata.get(n).subs)) {//有解
            if (isSingle(game.andata.get(n).subs)) {//是单一解
                int next = game.andata.get(n).subs.get(0);//下一步
                answer += game.andata.get(next).pt + game.andata.get(next).tip + ",";
                getAnswers(next);//递归
            } else {//多个解
                //将每个分支处的值存储起来
                int size = game.andata.get(n).subs.size();
                //SparseArray按key值的从小到大排列
//                m++;
//                array.put(m, size + ";" + answer);
                jiedian.add(m++);
                array.add(size + ";" + answer);
                if (array.size() > 1) {//多重分支,每遇到一个多重分支，改变上一层的总分支数
//                    String[] last = array.valueAt(array.size() - 1  - 1).split(";");
                    for (int k = array.size() - 1; k > 0; k--) {
                        String[] last = array.get(k - 1).split(";");
                        int s = Integer.parseInt(last[0]);
                        s += size - 1;

//                    array.put(m - 1, last[0] + ";" + (last.length == 1 ? "" : last[1]));
                        array.set(k - 1, s + ";" + (last.length == 1 ? "" : last[1]));
                    }
                }

                for (int i = 0; i < size; i++) {
                    int next = game.andata.get(n).subs.get(i);
                    answer += game.andata.get(next).pt + game.andata.get(next).tip + ",";
                    getAnswers(next);
                }
            }
        } else {
            answerList.add(answer);
//            String[] value = array.valueAt(array.size() - 1).split(";");
            if (game.andata.get(n).f == 0) {//正解
                success.add(answerList.size() - 1);
            }
            if (array.size() > 0) {
                String[] value = array.get(array.size() - 1).split(";");
                int num = Integer.parseInt(value[0]);//该分支的次数
                String a = value.length == 1 ? "" : value[1];//该分支存储的答案
                for (String s : answerList) {
                    if (s.contains(a)) {
                        num--;
                    }
                }
//                if (array.size() > 1){
//                    n = Integer.parseInt(array.get(array.size() - 1 - 1).split(";")[0]) - Integer.parseInt(value[0]);
//                }
                //该节点所有分支遍历完后，移除
                if (num == 0) {
//                    array.removeAt(array.size() - 1);
                    array.remove(array.size() - 1);
                    if (array.size() > 0) {
//                        value = array.valueAt(array.size() - 1).split(";");
                        for (int l = array.size() - 1; l > 0; l--) {
                            if (answerList.size() >= Integer.parseInt(array.get(l).split(";")[0])) {
                                array.remove(l);
                            }
                        }

                        if (array.size() > 0) {

                            value = array.get(array.size() - 1).split(";");
                            answer = value.length == 1 ? "" : value[1];

                        } else {
                            answer = "";
                        }
                    }


                } else {
                    answer = a;
                }
            }
        }
    }

    private boolean hasNext(List<Integer> list) {
        return list != null && list.size() > 0;
    }

    private boolean isSingle(List<Integer> list) {
        return list != null && list.size() == 1;
    }


    public String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
}
