package com.github.githubdev.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.githubdev.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ScreenIntro> mListScreen;
    private TextView title;
    private TextView description;
    private ImageView imgSlide;

    public IntroViewPagerAdapter(Context context, List<ScreenIntro> mListScreen) {
        this.context = context;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.intro_design, null);

        imgSlide = layoutScreen.findViewById(R.id.intro_img);
        title = layoutScreen.findViewById(R.id.intro_title);
        description = layoutScreen.findViewById(R.id.intro_description);
        imgSlide.setAnimation(mListScreen.get(position).getImageAnimation());
        title.setAnimation(mListScreen.get(position).getTitleAnimation());
        description.setAnimation(mListScreen.get(position).getDescAnimation());
        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());
        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}