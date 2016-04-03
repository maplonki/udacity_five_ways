package com.fiveways.cook.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fiveways.cook.R;
import com.fiveways.cook.models.RecipeModel;

import java.util.List;

/**
 * Created by hugo on 4/2/16.
 */
public class RecipePagerAdapter extends PagerAdapter {


    public interface OnRecipeDetailListener {
        void onRecipeDetail(String detailUri);
    }

    private Context mContext;
    private List<RecipeModel> mRecipeList;
    private OnRecipeDetailListener recipeListener;

    private View.OnClickListener recipeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof String) {
                if (recipeListener != null) {
                    recipeListener.onRecipeDetail((String) v.getTag());
                }
            }
        }
    };

    public RecipePagerAdapter(Context mContext, List<RecipeModel> mRecipeList, OnRecipeDetailListener recipeListener) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        this.recipeListener = recipeListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RecipeModel recipe = mRecipeList.get(position);

        View parentView = View.inflate(mContext, R.layout.page_recipe, null);

        ImageView recipeImage = (ImageView) parentView.findViewById(R.id.page_recipe_iv_image);
        TextView recipeTitle = (TextView) parentView.findViewById(R.id.page_recipe_tv_title);


        recipeImage.setTag(recipe.getUrl());

        recipeImage.setOnClickListener(recipeClickListener);

        Glide.with(mContext).load(recipe.getImage()).into(recipeImage);
        recipeTitle.setText(recipe.getLabel());

        container.addView(parentView);


        return parentView;
    }

    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
