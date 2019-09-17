package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hustacm1701.countbyheart.object.Info;
import com.hustacm1701.countbyheart.object.Today;

public class RecordActivity extends AppCompatActivity {

    private Today today = Today.getInstance();
    private TextView punchDay,today_number,today_correct,today_error;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        AnswerActivity last = AnswerActivity.ansActivity;
        if (last!=null){
            last.finish();
        }

        init();
    }

    private void init(){
        fitWindows();
//      初始化控件：
        punchDay = findViewById(R.id.punch_day);
        today_correct = findViewById(R.id.today_correct);
        today_error = findViewById(R.id.today_error);
        today_number = findViewById(R.id.today_number);
        confirm = findViewById(R.id.confirm);

        Info info = Info.getInstance();
        info.punch();

        punchDay.setText(info.getPunchDay()+"");
        today_number.setText(today.getTaskNumber()+"");
        today_error.setText(today.getCompletedNumber()-today.getCorrectNumber()+"");
        today_correct.setText(today.getCorrectNumber()+"");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    protected void fitWindows(){
        Window window = getWindow();//设置系统栏是否适应的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}