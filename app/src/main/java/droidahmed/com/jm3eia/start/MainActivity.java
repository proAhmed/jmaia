package droidahmed.com.jm3eia.start;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.CuListAdapter;
import droidahmed.com.jm3eia.api.CategoriesByParent;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.fragment.FragmentProduct;
import droidahmed.com.jm3eia.fragment.FragmentProductCart;
import droidahmed.com.jm3eia.fragment.MenuFragment;
import droidahmed.com.jm3eia.fragment.MenuFragmentRight;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CategoryParent;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.MainCategory;
import droidahmed.com.jm3eia.model.PathName;

public class MainActivity extends SlidingFragmentActivity {
  ImageView  imageToggle,imgLogo,imageToggleRight;
    TextView tvTitle;
    ArrayList<CategoryParent>  pro;
    private OnProcessCompleteListener ProductListener;
    MainCategory mainApi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Locale locale = new Locale("ar");
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        this.getApplicationContext().getResources().updateConfiguration(config, null);
              SlidingMenu sm = getSlidingMenu();

        sm.setMode(SlidingMenu.LEFT_RIGHT);
                sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
      // sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        setBehindContentView(R.layout.menu_slide);
        setContentView(R.layout.activity_main);


        sm.setSecondaryMenu(R.layout.menu_frame_two);
         getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame_two, MenuFragment.newInstance())
                .commit();


        setBehindView();
        if (Utility.isNetworkConnected(MainActivity.this)) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainCategory) result;
                    pro=   mainApi.getData();
                 //   Log.d("iiii",pro.toString());
//                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable("array", new ArrayList<>(Arrays.asList(pro)));


if(getIntent().getExtras()!=null){
    if(getIntent().getExtras().getString("CartAuth").equals("CartAuth")){
        FragmentProductCart fragment = new FragmentProductCart();
        Bundle bundles = new Bundle();
        bundles.putString("CartAuth","CartAuth");
         getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragment)
                .commitAllowingStateLoss();
    }
}else {
    Fragment fragmentProduct = new FragmentProduct();
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.mainFragment, fragmentProduct);
    ft.commit();

    fragmentProduct.setArguments(bundle);
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.mainFragment, fragmentProduct)
            .commitAllowingStateLoss();
}
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.menu_slide, MenuFragmentRight.newInstance( pro))
                                    .commitAllowingStateLoss();

                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(MainActivity.this, false);
                }
            };

            CategoriesByParent task = new CategoriesByParent(MainActivity.this, ProductListener);
            task.execute();

        } else {
            Utility.showValidateDialog(
                   getResources().getString(R.string.failure_ws),
                    MainActivity.this);
        }

//        SlidingMenu sm = getSlidingMenu();
//        sm.setMode(SlidingMenu.LEFT_RIGHT);
//        sm.setShadowWidthRes(R.dimen.shadow_width);
//        sm.setShadowDrawable(R.drawable.shadow);
//        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        sm.setFadeDegree(0.35f);
//   //     sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        imageToggle = (ImageView) findViewById(R.id.imageToggle);
        imageToggleRight = (ImageView) findViewById(R.id.imageToggleCategory);

        imgLogo = (ImageView) findViewById(R.id.logo);
        tvTitle = (TextView) findViewById(R.id.textTitle);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    private void setBehindView() {
        setBehindContentView(R.layout.menu_slide);

        //transaction fragment to sliding menu
    //    transactionFragments(MenuFragment.newInstance(), R.id.menu_slide);
    }
    public void transactionFragments(Fragment fragment, int viewResource) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(viewResource, fragment);
        ft.commit();
        toggle();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
