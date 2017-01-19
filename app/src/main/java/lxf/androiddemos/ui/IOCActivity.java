package lxf.androiddemos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
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
                    java.net.URL url = new URL("http://www.101weiqi.com/spec/liangbanchangyiqi/14035/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream is = connection.getInputStream();   //获取输入流，此时才真正建立链接
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferReader = new BufferedReader(isr);
                    String inputLine = "";
                    while ((inputLine = bufferReader.readLine()) != null) {
                        resultData += inputLine + "\n";
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
                    System.out.println(resultData);

                    game = Go.objectFromData(resultData);
                    List<String> black = game.prepos.get(0);
                    List<String> white = game.prepos.get(1);
                    getAnswers(0);
                    System.out.println("黑棋：" + black);
                    System.out.println("白棋：" + white);
                    System.out.println("正解：" + answerList);
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
                            sgf.append(";")
                                    .append(j % 2 == 0 ? game.blackfirst ? "B" : "W" : game.blackfirst ? "W" : "B")
                                    .append("[").append(s[j]).append("]");
                        }
                        sgf.append(")");
                    }
                    sgf.append(")");
                    System.out.println("sgf：" + sgf.toString());
                    answerList.clear();
                    answer = "";

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    List<String> answerList = new ArrayList<>();//正解集合
    SparseArray<String> array = new SparseArray<>();
    int m = 0;//为array排序
    String answer = "";

    /**
     * @param n 从0开始
     */
    private void getAnswers(int n) {
        if (hasNext(game.andata.get(n).subs)) {//有解
            if (isSingle(game.andata.get(n).subs)) {//是单一解
                int next = game.andata.get(n).subs.get(0);//下一步
                answer += game.andata.get(next).pt + ",";
                getAnswers(next);//递归
            } else {//多个解
                //将每个分支处的值存储起来
                int size = game.andata.get(n).subs.size();
                //SparseArray按key值的从小到大排列
                m++;
                array.put(m, size + ";" + answer);
                if (m > 1) {//多重分支,每遇到一个多重分支，改变上一层的总分支数
                    String[] last = array.valueAt(array.size() - 1  - 1).split(";");
                    last[0] += size - 1;

                    array.put(m - 1, last[0] + ";" + (last.length == 1?"":last[1]));
                }

                for (int i = 0; i < size; i++) {
                    int next = game.andata.get(n).subs.get(i);
                    answer += game.andata.get(next).pt + ",";
                    getAnswers(next);
                }
            }
        } else {
            answerList.add(answer);
            String[] value = array.valueAt(array.size() - 1).split(";");
            int num = Integer.parseInt(value[0]);//该分支的次数
            String a = value.length == 1?"":value[1];//该分支存储的答案
            for (String s : answerList) {
                if (s.contains(a)) {
                    num--;
                }
            }
            //该节点所有分支遍历完后，移除
            if (num == 0) {
                array.removeAt(array.size() - 1);
                if (array.size() > 0) {
                    value = array.valueAt(array.size() - 1).split(";");
                    answer = value.length == 1?"":value[1];
                }
            } else {
                answer = a;
            }
        }
    }

    private boolean hasNext(List<Integer> list) {
        return list != null && list.size() > 0;
    }

    private boolean isSingle(List<Integer> list) {
        return list != null && list.size() == 1;
    }
}
