package com.example.graduatioproject_android.FragmentMy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.graduatioproject_android.LoginAndRegister.LoginActivity;
import com.example.graduatioproject_android.R;
import com.example.graduatioproject_android.tools.DestroyActivityUtil;

import java.util.Calendar;

import static com.example.graduatioproject_android.tools.GlobalVariable.NICKNAME;
import static com.example.graduatioproject_android.tools.GlobalVariable.USERNAME;

public class FragmentMy extends Fragment {

    private ImageView headIv=null;
    private TextView nicknameTv=null;
    private TextView numbernameTv=null;
    private TextView themeTv=null;
    private TextView voiceTv=null;
    private TextView informTv=null;
    private LinearLayout accountLl=null;
    private LinearLayout sosLl=null;
    private LinearLayout helpLl=null;
    private LinearLayout aboutLl=null;
    private Button exitloginBtn=null;
    private Button sectionBtn=null;
    private MyListener myListener=null;
    private Calendar calendar=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        headIv=(ImageView) view.findViewById(R.id.headIV);
        nicknameTv=(TextView) view.findViewById(R.id.nickname);
        numbernameTv=(TextView) view.findViewById(R.id.numbername);
        themeTv=(TextView) view.findViewById(R.id.themeTV);
        voiceTv=(TextView) view.findViewById(R.id.voiceTV);
        informTv=(TextView) view.findViewById(R.id.informTV);
        sosLl=(LinearLayout) view.findViewById(R.id.sosLL);
        accountLl=(LinearLayout) view.findViewById(R.id.accountLL);
        helpLl=(LinearLayout) view.findViewById(R.id.helpLL);
        aboutLl=(LinearLayout) view.findViewById(R.id.aboutLL);
        sectionBtn=(Button) view.findViewById(R.id.sectionBtn);
        exitloginBtn=(Button) view.findViewById(R.id.exitloginBtn);
        myListener=new MyListener();

        nicknameTv.setText(NICKNAME);
        numbernameTv.setText(USERNAME);

        headIv.setOnClickListener(myListener);
        themeTv.setOnClickListener(myListener);
        voiceTv.setOnClickListener(myListener);
        informTv.setOnClickListener(myListener);
        sosLl.setOnClickListener(myListener);
        accountLl.setOnClickListener(myListener);
        helpLl.setOnClickListener(myListener);
        aboutLl.setOnClickListener(myListener);
        sectionBtn.setOnClickListener(myListener);
        exitloginBtn.setOnClickListener(myListener);
        return view;
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case(R.id.headIV):
                    Intent head_jumpIntent=new Intent(getActivity(), HeadActivity.class);
                    startActivity(head_jumpIntent);
                    break;
                case(R.id.themeTV):
                    Intent theme_jumpIntent=new Intent(getActivity(), ThemeActivity.class);
                    startActivity(theme_jumpIntent);
                    break;
                case(R.id.voiceTV):
                    Intent voice_jumpIntent=new Intent(getActivity(), VoiceActivity.class);
                    startActivity(voice_jumpIntent);
                    break;
                case(R.id.informTV):
                    Intent inform_jumpIntent=new Intent(getActivity(), InformActivity.class);
                    startActivity(inform_jumpIntent);
                    break;
                case(R.id.sosLL):
                    break;
                case(R.id.accountLL):
                    break;
                case(R.id.helpLL):
                    break;
                case(R.id.aboutLL):
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("关于");
                    builder.setMessage("版本：1.0\n制作人：博0_oer~\n时间：2020.01.01");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                    break;
                case(R.id.sectionBtn):
                    Intent section_jumpIntent=new Intent(getActivity(), SetCEActivity.class);
                    startActivity(section_jumpIntent);
                    break;
                case(R.id.exitloginBtn):
                    Intent exit_jumpIntent=new Intent(getActivity(), LoginActivity.class);
                    exit_jumpIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//置于顶栈
                    startActivity(exit_jumpIntent);
                    DestroyActivityUtil destroyActivityUtil = new DestroyActivityUtil();//调用tools销毁所有activity的方法
                    destroyActivityUtil.exit();
                    break;
                default:
                    break;
            }
        }
    }
}