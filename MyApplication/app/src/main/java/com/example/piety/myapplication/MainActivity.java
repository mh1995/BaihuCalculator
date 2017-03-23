package com.example.piety.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int m=4;
    double p = 0;
    int[] TN = new int[4];//存储玩家拖鸟数目
    int[] HN = new int[4];//存储玩家活鸟数目
    int[] HX = new int[4];//存储玩家胡子数目
    int[] resultTN = new int[4];//存储玩家拖鸟输赢结果
    int[] resultHN = new int[4];//存储玩家活鸟输赢结果
    EditText[] edTN = new EditText[4];
    EditText[] edHN = new EditText[4];
    EditText[] edHX = new EditText[4];
    private Button btnExit,btnCalaulate,btnReset;
    private EditText price;
    private TextView rs1,rs2,rs3,rs4,role4;
    private RadioButton three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalaulate =(Button) findViewById(R.id.btn_result);
        price = (EditText)findViewById(R.id.et_price);
        edTN[0]= (EditText)findViewById(R.id.et_tn1);
        edTN[1]= (EditText)findViewById(R.id.et_tn2);
        edTN[2]= (EditText)findViewById(R.id.et_tn3);
        edTN[3]= (EditText)findViewById(R.id.et_tn4);
        edHN[0]= (EditText)findViewById(R.id.et_hn1);
        edHN[1]= (EditText)findViewById(R.id.et_hn2);
        edHN[2]= (EditText)findViewById(R.id.et_hn3);
        edHN[3]= (EditText)findViewById(R.id.et_hn4);
        edHX[0]= (EditText)findViewById(R.id.et_hx1);
        edHX[1]= (EditText)findViewById(R.id.et_hx2);
        edHX[2]= (EditText)findViewById(R.id.et_hx3);
        edHX[3]= (EditText)findViewById(R.id.et_hx4);
        rs1 = (TextView) findViewById(R.id.tv_rs1);
        rs2 = (TextView) findViewById(R.id.tv_rs2);
        rs3 = (TextView) findViewById(R.id.tv_rs3);
        rs4 = (TextView) findViewById(R.id.tv_rs4);
        role4 = (TextView)findViewById(R.id.tv_07);
        setNumber();
        three = (RadioButton) findViewById(R.id.rb_three);
        //选择人数
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(three.isChecked()==true)
                {
                    m=3;
                    role4.setEnabled(false);
                    edHN[3].setEnabled(false);
                    edTN[3].setEnabled(false);
                    edHX[3].setEnabled(false);
                    rs4.setEnabled(false);
                }
                else{
                    m=4;
                    role4.setEnabled(true);
                    edHN[3].setEnabled(true);
                    edTN[3].setEnabled(true);
                    edHX[3].setEnabled(true);
                    rs4.setEnabled(true);
                }
            }
        });
        //计算
        btnCalaulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p = Double.parseDouble(price.getText().toString().equals("")?"0":price.getText().toString());
                for(int i=0;i<m;i++){
                    TN[i]=Integer.parseInt(edTN[i].getText().toString().equals("")?"0":edTN[i].getText().toString());
                    HN[i]=Integer.parseInt(edHN[i].getText().toString().equals("")?"0":edHN[i].getText().toString());
                    HX[i]=Integer.parseInt(edHX[i].getText().toString().equals("")?"0":edHX[i].getText().toString());
                }
                cTuoNiao();
                hxRint();
                cHuoNiao();
                rs1.setText(resultHN[0]+resultTN[0]+"");
                rs2.setText(resultHN[1]+resultTN[1]+"");
                rs3.setText(resultHN[2]+resultTN[2]+"");
                rs4.setText(resultHN[3]+resultTN[3]+"");
            }
        });
        //退出
        btnExit = (Button) findViewById(R.id.btn_out);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        //清除数据
        btnReset = (Button) findViewById(R.id.btn_remove);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price.setText("0");
                for(int i=0;i<m;i++){
                    edHN[i].setText("0");
                    edTN[i].setText("0");
                    edHX[i].setText("0");
                }
                rs1.setText("0");
                rs2.setText("0");
                rs3.setText("0");
                rs4.setText("0");
            }
        });

    }
    //默认打开数字键盘
    public void setNumber(){
        price.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        for(int i=0;i<4;i++) {
            edHN[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            edTN[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            edHX[i].setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }
    }
    //计算拖鸟输赢
    public void cTuoNiao(){
        for(int i = 0;i<m;i++) {
            resultTN[i]=0;
            for(int j = 0;j<m;j++){
               if(HX[i]!=HX[j]){
                   if(HX[i]>HX[j])resultTN[i]+=(TN[i]+TN[j]);
                   else resultTN[i]-=(TN[i]+TN[j]);
               }
            }
        }
    }
    //胡子四舍五入
    public void hxRint()
    {
        for(int i=0;i<4;i++)
        {
            if(Math.abs(HX[i])==5) HX[i]++;
            HX[i]=(int) Math.rint(HX[i]/10.0)*10;
        }
    }
    //计算活鸟输赢
    public void cHuoNiao(){
        for(int i = 0;i<m;i++) {
            resultHN[i]=0;
            for(int j = 0;j<m;j++){
                resultHN[i]+=((HX[i]-HX[j])*p*(HN[i]+1)*(HN[j]+1));
            }
        }
    }
}
