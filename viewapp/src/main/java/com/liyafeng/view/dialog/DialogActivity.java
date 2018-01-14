package com.liyafeng.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.liyafeng.view.R;

public class DialogActivity extends AppCompatActivity {

    /**
     * @param args
     * 一个全屏的dialog，想让dialog像Activity一样
     * 如果你设置dialog最外层布局为match_parent，那么他实际是没有效果的
     * 会自动变为warp_content
     * -----------------------------------
     * dialog本质上是一个新的PhoneWindow，只不过没有Activity那样的生命周期
     * ---------------------------------------------
     *  <item name="android:windowFullscreen">true</item>
     * <item name="android:windowContentOverlay">@null</item>
     * 在style设置了全屏后，那么代码中就不用设置全屏，window背景默认白色
     *
     * ----------------------------------------
     * 注意，你要的是window的大小还是，window内容的大小
     *
     * -----------------------------------------
     * 设置一个空的style，然后将内容布局设置为match_parent，直接就是全屏
     *
     * -----------------------------------------
     * AlertDialog是默认有几个按钮，也可以设置文案，也可以自定义布局
     * guide/topics/ui/dialogs.html
     * 但是注意，alertDialog只能用系统的几个主题，你用自己的主题不管用，里面有判断
     * resolveDialogTheme
     */
    public static void main(String[] args) {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        new CustomDialog(this).createDialog();

    }
}

class CustomDialog extends android.app.Dialog{

    public CustomDialog(@NonNull Context context) {
        super(context,R.style.popupDialog);
    }

    public void createDialog(){
        setContentView(R.layout.dialog_custom);
        Window window = getWindow();
        show();

        //这2种方式都可以改变宽高，但是周围会有留白，留白是因为使用了系统的theme，换成一个空theme即可
//       WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        window.getAttributes().height = ViewGroup.LayoutParams.MATCH_PARENT;
//        window.getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
//        window.setAttributes(window.getAttributes());

//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }
}