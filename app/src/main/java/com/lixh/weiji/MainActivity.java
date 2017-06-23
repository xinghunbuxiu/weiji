package com.lixh.weiji;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.n)
    EditText n;
    @Bind(R.id.a)
    EditText a;
    @Bind(R.id.b)
    EditText b;
    @Bind(R.id.b1)
    EditText b1;
    @Bind(R.id.money)
    EditText s;
    @Bind(R.id.save)
    Button save;
    @Bind(R.id.last)
    TextView last;
    String json;
    Bean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initShare();
    }

    protected <VT extends View> VT $(@IdRes int id) {
        return (VT) this.findViewById(id);
    }

    private void initShare() {
        SharedPreferencesUtil.init(this, getPackageName() + "_preference", Context.MODE_PRIVATE);
        json = SharedPreferencesUtil.getInstance().getString("bean_config");
        if (SharedPreferencesUtil.getInstance().exists("bean_config")) {
            bean = JSON.parseObject(json, Bean.class);
//            c.setText(String.valueOf(bean.getC()));
            n.setText(String.valueOf(bean.getN()));
            a.setText(String.valueOf(bean.getA()));
            b.setText(String.valueOf(bean.getB()));
            b1.setText(String.valueOf(bean.getB1()));
            save.setText("重置");
            save.setTag(2);
        } else {
            n.setText("1.12");
            a.setText("1.1");
            b.setText("1.15");
            b1.setText("1.14");
            save.setText("保存");
            save.setTag(1);
            bean = new Bean();
        }
        initTextChange(n, String.valueOf(bean.getN()));
        initTextChange(a, String.valueOf(bean.getA()));
        initTextChange(b, String.valueOf(bean.getB()));
        initTextChange(b1, String.valueOf(bean.getB1()));
        s.requestFocus();

    }

    void initTextChange(EditText e, final String begin) {
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(s.toString().equals(begin))) {
                    save.setText("保存");
                    save.setTag(1);
                }
            }
        });
    }

    @OnClick({R.id.V1, R.id.save, R.id.tui, R.id.V2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.V1:
                if (isNull(s, a, b, b1, n)) {
                    return;
                }
                double z = Utils.V1.INSTANCE.getZ(getValue(s), getValue(a));
                double q = Utils.V1.INSTANCE.getQ(getValue(s), getValue(b));
                double w = Utils.V1.INSTANCE.getW(getValue(s), getValue(n));
                double g = Utils.V1.INSTANCE.getG(getValue(s), getValue(b1));
                double c = z - w;
                double p = g - q;
                double v1 = z - q;
                double v2 = z - q - c;
                double v3 = z - q - c - p;
                last.setText(
                        "----用币充值----" + "\n" +
                                "客户转款数z=" + z + "\n" +
                                "----------------\n" +
                                "客户实际转款数w=" + w + "\n" +
                                "----------------\n" +
                                "渠道回扣c=" + c + "\n" +
                                "----------------\n" +
                                "给上面的钱q=" + q + "\n" +
                                "----------------\n" +
                                "实际给上面的钱g=" + g + "\n" +
                                "----------------\n" +
                                "上面的回扣p=" + p + "\n" +
                                "----------------\n" +
                                "币充值利润v1=" + v1 + "\n" +
                                "----------------\n" +
                                "一个回扣的利润v2=：" + v2 + "\n" +
                                "----------------\n" +
                                "上面扣加客户的点利润v3=：" + v3);
                KeyBordUtil.hideSoftKeyboard(view);
                break;
            case R.id.V2:
                if (isNull(s, a, b, b1, n)) {
                    return;
                }
                double y = getValue(s);
                double s1 = Utils.V2.INSTANCE.getS(y, getValue(a));
                double q1 = Utils.V2.INSTANCE.getQ(s1, getValue(b));
                double g1 = Utils.V2.INSTANCE.getG(s1, getValue(b1));
                double c1 = y - Utils.parseValue(s1 / getValue(n));
                double p1 = g1 - q1;
                double v_1 = y - q1;
                double v_2 = y - q1 - c1;
                double v_3 = y - c1 - q1 - p1;
                last.setText(
                        "----用人民币充值----" + "\n" +
                                "客户充值币数s=" + s1 + "\n" +
                                "----------------\n" +
                                "给上面的钱q=" + q1 + "\n" +
                                "----------------\n" +
                                "实际给上面的钱g=" + g1 + "\n" +
                                "----------------\n" +
                                "渠道回扣c=" + c1 + "\n" +
                                "----------------\n" +
                                "上面的回扣p=" + p1 + "\n" +
                                "----------------\n" +
                                "人民币充值利润v1=：" + v_1 + "\n" +
                                "----------------\n" +
                                "一个回扣的利润v2=：" + v_2 + "\n" +
                                "----------------\n" +
                                "上面扣加客户的点利润v3=：" + v_3);
                KeyBordUtil.hideSoftKeyboard(view);
                break;
            case R.id.save:
                if (isNull(a, b, b1, n)) {
                    return;
                }
                int tag = (int) save.getTag();
                if (tag == 1) {
                    bean.setA(getValue(a));
                    bean.setB(getValue(b));
                    bean.setB1(getValue(b1));
//                    bean.setC(getValue(c));
//                    bean.setE(getValue(e));
                    bean.setN(getValue(n));
                    String config = JSON.toJSONString(bean);
                    SharedPreferencesUtil.getInstance().putString("bean_config", config);
                    save.setText("重置");
                    save.setTag(2);
                } else if (tag == 2) {
                    reset(a, b, b1, n);
                    SharedPreferencesUtil.getInstance().remove("bean_config");
                    save.setText("保存");
                    save.setTag(1);
                }

                break;
            case R.id.tui:
                if (isNull(s, a, b, b1, n)) {
                    return;
                }
                double t = getValue(s);
                double T1 = Utils.T.INSTANCE.getT1(t, getValue(b));
                double T2 = Utils.T.INSTANCE.getT2(t, getValue(b), getValue(b1));
                double T3 = Utils.T.INSTANCE.getT3(t, getValue(a));
                double T4 = Utils.T.INSTANCE.getT4(t, getValue(a), getValue(n));
                double L1 = Utils.T.INSTANCE.getL1(t, getValue(b1), getValue(n));
                double L = T1 - T3;

                last.setText(
                        "----退款---" + "\n" +
                                "上面退T1=" + T1 + "\n" +
                                "----------------\n" +
                                "上面个人退T2=" + T2 + "\n" +
                                "----------------\n" +
                                "客户退T3=" + T3 + "\n" +
                                "----------------\n" +
                                "渠道个人补T4=" + T4 + "\n" +
                                "----------------\n" +
                                "无回扣的利润L=" + L +
                                "----------------\n" +
                                "有回扣的利润L1=" + L1);
                KeyBordUtil.hideSoftKeyboard(view);
                break;
        }
    }

    private boolean isNull(EditText... edit) {
        for (EditText e : edit) {
            String value = e.getText().toString().trim();
            String warn = "";
            if (TextUtils.isEmpty(value)) {
                switch (e.getId()) {
                    case R.id.a:
                        warn = "客户返点a";
                        break;
                    case R.id.b1:
                        warn = "上面实际点数b1";
                        break;
                    case R.id.b:
                        warn = "上面返点b";
                        break;
                    case R.id.money:
                        warn = "充值币orMoney";
                        break;
                    case R.id.n:
                        warn = "客户实际返点n";
                        break;
//                    case R.id.e:
//                        warn = "渠道点数e";
//                        break;

                }
                Toast.makeText(MainActivity.this, warn, Toast.LENGTH_SHORT).show();
                return true;
            }

        }
        return false;
    }

    void reset(EditText... edit) {
        for (EditText e : edit) {
            e.setText("");
        }
    }


    private double getValue(EditText e) {
        String value = e.getText().toString().trim();
        return Double.parseDouble(value);

    }
}
