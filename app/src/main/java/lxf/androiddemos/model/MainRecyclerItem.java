package lxf.androiddemos.model;

import android.content.Intent;
import android.view.View;

import lxf.androiddemos.ui.BehaviorActivity;
import lxf.androiddemos.ui.DatabindingActivity;
import lxf.androiddemos.ui.ViewDragHelperActivity;
import lxf.androiddemos.ui.ZxingActivity;

public class MainRecyclerItem {
    public static final String[] items = new String[]{"ViewDragHelper", "自定义Behavior", "二维码", "DataBinding"};

    private String content;

    public void onItemClick(View view) {
        Intent intent = null;
        switch (getContent()) {
            case "ViewDragHelper":
                intent = new Intent(view.getContext(), ViewDragHelperActivity.class);
                break;
            case "自定义Behavior":
                intent = new Intent(view.getContext(), BehaviorActivity.class);
                break;
            case "二维码":
                intent = new Intent(view.getContext(), ZxingActivity.class);
                break;
            case "DataBinding":
                intent = new Intent(view.getContext(), DatabindingActivity.class);
                break;
        }
        if (intent != null)
            view.getContext().startActivity(intent);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
