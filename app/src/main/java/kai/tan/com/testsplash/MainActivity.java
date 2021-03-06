package kai.tan.com.testsplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isFulllScreen = false;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        smoothSwitchScreen();
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {

                isFulllScreen = !isFulllScreen;

                if (isFulllScreen) {

                    button.setText("退出全屏");

                    WindowManager.LayoutParams params = getWindow().getAttributes();

                    params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;

                    getWindow().setAttributes(params);

                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                } else {

                    button.setText("全屏");

                    WindowManager.LayoutParams params = getWindow().getAttributes();

                    params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);

                    getWindow().setAttributes(params);

                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                }

            }

        });
    }

    public void click(View view) {
        go();
    }

    private void go() {
        Intent intent = new Intent(MainActivity.this, FullActivity.class);
        startActivity(intent);
    }

    private void smoothSwitchScreen() {
        // 5.0以上修复了此bug
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup rootView = ((ViewGroup) this.findViewById(android.R.id.content));
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            rootView.setPadding(0, statusBarHeight, 0, 0);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
    }
}
