package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderFragment extends Fragment {

    private static final int FRIEND_NUMBER = 10;

    private static int FRAGMENT_COUNT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View fragmentView = inflater.inflate(R.layout.fragment_placeholder, container, false);
        ListView listView = fragmentView.findViewById(R.id.friend_list);
        final LottieAnimationView loadingAnimationView = fragmentView.findViewById(R.id.fragment_animation_view);

        loadingAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        List<Map<String,Object>> list = getData();
        String[] from = {"name","num"};
        int[] to = {R.id.name,R.id.num};
        listView.setAdapter(new SimpleAdapter(getActivity(),list,R.layout.friends,from,to));
        loadingAnimationView.setVisibility(View.INVISIBLE);
        if (FRAGMENT_COUNT == 0) {
            listView.setAlpha(0f);
            loadingAnimationView.setVisibility(View.VISIBLE);
            FRAGMENT_COUNT++;
        }
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LottieAnimationView loadingAnimationView = getActivity().findViewById(R.id.fragment_animation_view);
        final View listView = getActivity().findViewById(R.id.friend_list);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                loadingAnimationView.animate().alpha(0f).setDuration(1000).setListener(null);
                listView.animate().alpha(1f).setDuration(1000);
            }
        }, 5000);
    }

    public List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (int i = 0;i <  FRIEND_NUMBER;i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name","邱宇森");
            map.put("num","8209210415");
            list.add(map);
        }
        return list;
    }
}

