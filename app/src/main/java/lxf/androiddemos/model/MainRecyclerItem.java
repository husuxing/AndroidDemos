package lxf.androiddemos.model;

import android.content.Intent;
import android.view.View;

import lxf.androiddemos.ui.activity.*;

public class MainRecyclerItem {
    public static final String[] items = new String[]{"app版本自动更新","DataBinding", "自定义Behavior", "二维码", "ViewDragHelper", "FlowLayout", "IOC", "View"};

    private String content;

    public void onItemClick(View view) {
        Intent intent = null;
        switch (getContent()) {
            case "ViewDragHelper":
                intent = new Intent(view.getContext(), ViewDragHelperActivity.class);
                break;
            case "FlowLayout":
                intent = new Intent(view.getContext(), FlowActivity.class);
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
            case "IOC":
                intent = new Intent(view.getContext(), IOCActivity.class);
                break;
            case "View":
                intent = new Intent(view.getContext(), ViewActivity.class);
                break;
            case "app版本自动更新":
                intent = new Intent(view.getContext(), UpdateActivity.class);
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
