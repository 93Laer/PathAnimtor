# PathAnimtor
这个demo重在为大家提供学习vectorDrawable实习动画的基础知识，但是想要实现更加炫酷的效果就要读者自己去深究了
#### 本demo博客链接
http://www.jianshu.com/p/8fb448e9b022
#### 如果对cectorDrawable不太了解的建议先看看这篇博客
http://www.jianshu.com/p/069638839107
### 注意：这篇博客重在为大家提供学习vectorDrawable实习动画的基础知识，但是想要实现更加炫酷的效果就要读者自己去深究了

###### 先展示一下效果图
</br>
![登录界面的vectorDrawable](http://upload-images.jianshu.io/upload_images/1744409-aa91f15f4e34d3e4?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
###### 现在看上去还是没一点鸟用哈，也没什么特点，再看看代码吧
```
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="75dp"
    android:height="25dp"
    android:viewportHeight="40"
    android:viewportWidth="120">
        <path
            android:name="firstHLine"
            android:pathData="M 0,20 l 90,0"
            android:strokeAlpha="0.8"
            android:strokeColor="@android:color/holo_red_dark"
            android:strokeWidth="0.5" />
        <path
            android:name="circleTop"
            android:pathData="M 90,20
            A 9,9 0,1,0 81,11"
            android:strokeAlpha="0.8"
            android:strokeColor="@android:color/holo_red_dark"
            android:strokeWidth="0.5" />
        <path
            android:name="true"
            android:pathData="M 81,11 l 4,6 l 10,-8"
            android:strokeAlpha="0.8"
            android:strokeColor="@android:color/holo_red_dark"
            android:strokeLineCap="round"
            android:strokeWidth="0.5" />
        <path
            android:name="circleBouttom"
            android:pathData="
            M90,20
            A 9,9 0,1,1 90,38"
            android:strokeAlpha="0.8"
            android:strokeColor="@android:color/holo_red_dark"
            android:strokeWidth="0.5" />
        <path
            android:name="secondHLine"
            android:pathData="M 0,38 l 90,0"
            android:strokeAlpha="0.8"
            android:strokeColor="@android:color/holo_red_dark"
            android:strokeWidth="0.5" />
</vector>
```
*麻痹，更难过了*

#### 来看看没用的鸟图实现的炫酷效果吧


![登录界面的vectorDrawable的gif](http://upload-images.jianshu.io/upload_images/1744409-9352cd81dbe50f0a.gif?imageMogr2/auto-orient/strip)
*感觉还是不错呀*
###### 注：网上有太多关于这种动画的炫酷效果了，我这里只展示了我自己实现的一个小动画，有兴趣可以自己搜来看
</br>
#### 分析这个动画的步骤:
1. 当输入账户名的编辑框获得焦点时显示上面那条横线动画
2. 当输入的账户名正确时显示一个提示正确的方法
3. 当下面输入框获得焦点时显示第二条横线
4. 当输入账户名的编辑框将账户名改错了时加载收起正确符号的动画
5. 当前焦点在下面的输入框，当再次点击上面的输入框，上面获得焦点时，加载返回到上一条的过度动画
另外还有当账户名输入正确或是失败时，两个输入框获得焦点时的过渡动画，这里我就不罗列了

</br>
#### 首先定义动画吧(没有加default的都是动画显示,请自动忽略命名,也勿喷，第一次都是这样)

- default_show

```
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="trimPathEnd"
    android:valueFrom="1"
    android:valueTo="1"
    android:valueType="floatType"
    android:duration="1"
    />
```
- default_gone

```
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="trimPathEnd"
    android:valueTo="0"
    android:valueFrom="0"
    android:valueType="floatType"
    android:duration="1"
    />
```
- anim3_first_line_gone

```
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="trimPathStart"
    android:valueFrom="0"
    android:valueTo="1"
    android:valueType="floatType"
    android:duration="500"
    android:interpolator="@android:interpolator/linear_out_slow_in" />
```
- anim3_back_circle_buttom
 
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:ordering="sequentially">
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="0"
        android:valueType="floatType"
        android:duration="1"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathStart"
        android:valueFrom="1"
        android:valueTo="0"
        android:valueType="floatType"
        android:startOffset="200"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />

    </set>
```
- anim3_back_buttom_circle
```
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="trimPathEnd"
    android:valueFrom="1"
    android:valueTo="0"
    android:valueType="floatType"
    android:startOffset="600"
    android:duration="500"
    android:interpolator="@android:interpolator/linear_out_slow_in" />
```
- anim3_back_first_line

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:ordering="sequentially">
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="1"
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="0"
        android:valueType="floatType" />
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="500"
        android:propertyName="trimPathStart"
        android:startOffset="700"
        android:valueFrom="1"
        android:valueTo="0"
        android:valueType="floatType" />
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="1"
        android:propertyName="trimPathEnd"
        android:valueFrom="1"
        android:valueTo="1"
        android:valueType="floatType" />
</set>
```
- anim_show

```
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:propertyName="trimPathEnd"
    android:valueFrom="0"
    android:valueTo="1"
    android:duration="500"
    android:valueType="floatType"
    android:interpolator="@android:interpolator/linear_out_slow_in"
    tools:targetApi="lollipop"/>
```
- default_show

```
<?xml version="1.0" encoding="utf-8"?>
<objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
    android:propertyName="trimPathEnd"
    android:valueFrom="1"
    android:valueTo="1"
    android:valueType="floatType"
    android:duration="1"
    />

```
- anim2_circletop_show

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="1"
        android:valueType="floatType"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator
        android:propertyName="trimPathStart"
        android:valueFrom="0"
        android:valueTo="1"
        android:valueType="floatType"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
</set>
```
- anim2_show_true

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="0"
        android:duration="1"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/linear_out_slow_in"/>
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="1"
        android:startOffset="500"
        android:duration="500"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/linear_out_slow_in"/>
</set>

```
- anim3_circle_buttom_show

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="0"
        android:valueType="floatType"
        android:duration="1"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="1"
        android:valueType="floatType"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator
        android:propertyName="trimPathStart"
        android:valueFrom="0"
        android:valueTo="1"
        android:valueType="floatType"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
</set>

```
- anim3_second_line_show

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathEnd"
        android:valueFrom="0"
        android:valueTo="0"
        android:valueType="floatType"
        android:duration="1"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathStart"
        android:valueFrom="1"
        android:valueTo="0"
        android:valueType="floatType"
        android:startOffset="500"
        android:duration="500"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathEnd"
        android:valueFrom="1"
        android:valueTo="1"
        android:valueType="floatType"
        android:duration="1"
        android:interpolator="@android:interpolator/linear_out_slow_in" />
</set>

```
- anim_no_pass_true

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="1"
        android:valueTo="0"
        android:duration="500"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/linear_out_slow_in"/>
</set>

```
- anim_no_pass_circle

```
<?xml version="1.0" encoding="utf-8"?>
<set android:ordering="sequentially"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathEnd"
        android:valueTo="0"
        android:valueFrom="0"
        android:valueType="floatType"
        android:duration="1"
        />
    <objectAnimator
        android:propertyName="trimPathStart"
        android:valueFrom="1"
        android:valueTo="0"
        android:startOffset="200"
        android:duration="500"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/linear_out_slow_in"/>
    <objectAnimator xmlns:android="http://schemas.android.com/apk/res/android"
        android:propertyName="trimPathEnd"
        android:valueTo="1"
        android:valueFrom="1"
        android:valueType="floatType"
        android:duration="1"
        />
    <objectAnimator
        android:propertyName="trimPathEnd"
        android:valueFrom="1"
        android:valueTo="0"
        android:duration="500"
        android:valueType="floatType"
        android:interpolator="@android:interpolator/linear_out_slow_in"/>
</set>

```
#### 在Drawable下新建
- anim1

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop">
    <target
        android:name="firstHLine"//对应路径的名字
        android:animation="@animator/anim_show" />
    <target
        android:name="circleTop"
        android:animation="@animator/default_gone" />
    <target
        android:name="true"
        android:animation="@animator/default_gone" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/default_gone" />
    <target
        android:name="secondHLine"
        android:animation="@animator/default_gone" />
</animated-vector>
```
- anim2

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop"
    >
    <target
        android:name="firstHLine"
        android:animation="@animator/default_show" />
    <target
        android:name="circleTop"
        android:animation="@animator/anim2_circletop_show" />
    <target
        android:name="true"
        android:animation="@animator/anim2_show_true" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/default_gone" />
    <target
        android:name="secondHLine"
        android:animation="@animator/default_gone" />
</animated-vector>
```
- anim3

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop">
    <target
        android:name="circleTop"
        android:animation="@animator/default_gone" />
    <target
        android:name="true"
        android:animation="@animator/default_show" />
    <target
    android:name="firstHLine"
    android:animation="@animator/anim3_first_line_gone" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_circle_buttom_show" />
    <target
        android:name="secondHLine"
        android:animation="@animator/anim3_second_line_show" />
</animated-vector>
```
- anim3_back1

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop">

    <target
        android:name="true"
        android:animation="@animator/default_show" />
    <target
        android:name="circleTop"
        android:animation="@animator/default_gone" />
    <target
        android:name="secondHLine"
        android:animation="@animator/anim3_first_line_gone" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_back_circle_buttom" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_back_buttom_circle" />
    <target
        android:name="firstHLine"
        android:animation="@animator/anim3_back_first_line" />
</animated-vector>
```
- anim3_back1_no_pass

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop">

    <target
        android:name="true"
        android:animation="@animator/default_gone" />
    <target
        android:name="circleTop"
        android:animation="@animator/default_gone" />
    <target
        android:name="secondHLine"
        android:animation="@animator/anim3_first_line_gone" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_back_circle_buttom" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_back_buttom_circle" />
    <target
        android:name="firstHLine"
        android:animation="@animator/anim3_back_first_line" />
</animated-vector>
```
- anim3_no_pass

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop">
    <target
        android:name="circleTop"
        android:animation="@animator/default_gone" />
    <target
        android:name="true"
        android:animation="@animator/default_gone" />
    <target
    android:name="firstHLine"
    android:animation="@animator/anim3_first_line_gone" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/anim3_circle_buttom_show" />
    <target
        android:name="secondHLine"
        android:animation="@animator/anim3_second_line_show" />
</animated-vector>
```
- anim_judge

```
<?xml version="1.0" encoding="utf-8"?>
<animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:drawable="@drawable/login_vector"
    tools:targetApi="lollipop"
    >
    <target
        android:name="firstHLine"
        android:animation="@animator/default_show" />
    <target
        android:name="true"
        android:animation="@animator/anim_no_pass_true" />
    <target
        android:name="circleTop"
        android:animation="@animator/anim_no_pass_circle" />
    <target
        android:name="circleBouttom"
        android:animation="@animator/default_gone" />
    <target
        android:name="secondHLine"
        android:animation="@animator/default_gone" />
</animated-vector>
```
- 界面布局 

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.laer.testcamera.LoginActivity">
    <ImageView
        android:id="@+id/img1"
        android:layout_marginLeft="50dp"
        android:layout_width="300dp"
        android:layout_height="100dp"
        android:focusable="true"
        android:focusableInTouchMode="true"/>
    <LinearLayout
        android:id="@+id/l1"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:gravity="center_vertical">
        <TextView
            android:text="账户名:"
            android:layout_width="50dp"
            android:layout_height="wrap_content" />
        <EditText
            android:id="@+id/edit1"
            android:layout_width="300dp"
            android:layout_height="50dp"
            android:layout_gravity="center"
            android:background="@null"
            android:hint="账户名"
            android:paddingStart="4dp"
            android:textColor="?android:colorAccent"
            android:textColorHint="@color/colorPrimary"
            android:textSize="12sp" />
    </LinearLayout>
    <LinearLayout
        android:layout_below="@id/l1"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:gravity="center_vertical">
        <TextView
            android:text="密    码:"
            android:layout_width="50dp"
            android:layout_height="wrap_content" />
        <EditText
            android:id="@+id/edit2"
            android:layout_width="300dp"
            android:layout_height="50dp"
            android:layout_gravity="center"
            android:background="@null"
            android:hint="密码"
            android:paddingStart="4dp"
            android:textColor="?android:colorAccent"
            android:textColorHint="@color/colorPrimary"
            android:textSize="12sp" />
    </LinearLayout>
</RelativeLayout>

```
- activity的逻辑处理

```
public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener, TextWatcher {

    private ImageView img1;
    private EditText edit1;
    private EditText edit2;
    private AnimatedVectorDrawable anim1;
    private AnimatedVectorDrawable anim2;
    private AnimatedVectorDrawable anim3;
    private AnimatedVectorDrawable anim3_back1;
    private AnimatedVectorDrawable anim_judge;
    private AnimatedVectorDrawable anim3_back1_no_pass;
    private AnimatedVectorDrawable anim3_no_pass;
    private boolean isGoBack;
    private boolean isShowTrue;
    private boolean isPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        img1 = ((ImageView) findViewById(R.id.img1));
        edit1 = ((EditText) findViewById(R.id.edit1));
        edit2 = ((EditText) findViewById(R.id.edit2));
        anim1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim1);
        anim2 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim2);
        anim3 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3);
        anim3_back1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_back1);
        anim3_back1_no_pass = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_back1_no_pass);
        anim3_no_pass = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_no_pass);
        anim_judge = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_judge);
        // 设置焦点变化的监听
        edit1.setOnFocusChangeListener(this);
        edit2.setOnFocusChangeListener(this);
        // 文本变化的监听
        edit1.addTextChangedListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit1:
                if (hasFocus) {
                    if (isGoBack) {
                        if (isPass) {
                            isGoBack = false;
                            img1.setImageDrawable(anim3_back1);
                            anim3_back1.start();
                        } else {
                            isGoBack = false;
                            img1.setImageDrawable(anim3_back1_no_pass);
                            anim3_back1_no_pass.start();
                        }
                    } else {
                        img1.setImageDrawable(anim1);
                        anim1.start();
                    }
                }

                break;
            case R.id.edit2:
                if (hasFocus) {
                    isGoBack = true;
                    if (isPass){
                        img1.setImageDrawable(anim3);
                        anim3.start();
                    }else {
                        img1.setImageDrawable(anim3_no_pass);
                        anim3_no_pass.start();
                    }

                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!TextUtils.isEmpty(editable) && edit1.getText().toString().equals("123")) {
            isPass = true;
            img1.setImageDrawable(anim2);
            anim2.start();
            isShowTrue = true;
        } else if (isShowTrue) {
            isPass = false;
            img1.setImageDrawable(anim_judge);
            anim_judge.start();
            isShowTrue = false;
        }
    }
}

```
#### 顺便讲讲trimPathEnd和trimPathStart 的区别，很简单，不懂的，自己测试一下

- trimPathEnd：
  指的是从开始绘制的地方到剪切结束的地方
- trimPathStart 
  指的是从开始剪切的地方到最后

