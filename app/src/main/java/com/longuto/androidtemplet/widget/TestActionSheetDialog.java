package com.longuto.androidtemplet.widget;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by yltang,
 * Date on 2018/10/12.
 * PS: 测试ActionSheetDialog
 */
public class TestActionSheetDialog {

    ActionSheetDialog mActionSheetDialog;
    Context context;
    List<String> data = new ArrayList<>();

    public TestActionSheetDialog(Context context) {
        this.context = context;
        mActionSheetDialog = new ActionSheetDialog(context)
                .setShowPostion(ActionSheetDialog.TYPE_MIDDLE)
                .builder();
    }

    public TestActionSheetDialog setData(ActionSheetDialog.OnSheetItemClickListener listener) {
        data.add("测试一");
        data.add("测试二");
        data.add("测试三");
        data.add("测试四");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        data.add("测试五");
        for (String temp : data) {
            mActionSheetDialog.addSheetItem(temp, ActionSheetDialog.SheetItemColor.BLUE, listener);
        }
        return this;
    }

    public void show() {
        if(null != mActionSheetDialog) {
            mActionSheetDialog.show();
        }
    }
}
