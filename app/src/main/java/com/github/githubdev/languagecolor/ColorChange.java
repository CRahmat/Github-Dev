package com.github.githubdev.languagecolor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.github.githubdev.R;

public class ColorChange {
    public ColorChange(String language, ImageView codeLanguage, Context context) {
        if (language.equals("Java")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Java));
        } else if (language.equals("JavaScript")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.JavaScript));
        } else if (language.equals("PHP")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.PHP));
        } else if (language.equals("CSS")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.CSS));
        } else if (language.equals("TypeScript")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.TypeScript));
        } else if (language.equals("Rubby")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Ruby));
        } else if (language.equals("Go")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Go));
        } else if (language.equals("Kotlin")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Kotlin));
        } else if (language.equals("C")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.C));
        } else if (language.equals("Python")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Python));
        } else if (language.equals("HTML")) {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.HTML));
        } else if (language.equals("")) {
            codeLanguage.setVisibility(View.GONE);
        } else {
            Drawable unWrapperDrawable = AppCompatResources.getDrawable(context, R.drawable.language);
            Drawable wrapperDrawable = DrawableCompat.wrap(unWrapperDrawable);
            DrawableCompat.setTint(wrapperDrawable, ContextCompat.getColor(context, R.color.Another));
        }
    }
}
