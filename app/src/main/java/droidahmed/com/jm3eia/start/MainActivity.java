package droidahmed.com.jm3eia.start;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.api.CategoriesByParent;
import droidahmed.com.jm3eia.api.GetHome;
import droidahmed.com.jm3eia.api.StaticPagesAbout;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.fragment.FragmentProduct;
import droidahmed.com.jm3eia.fragment.FragmentProductCart;
import droidahmed.com.jm3eia.fragment.MenuFragment;
import droidahmed.com.jm3eia.fragment.MenuFragmentRight;
import droidahmed.com.jm3eia.fragment.NotifyFragment;
import droidahmed.com.jm3eia.model.AboutPage;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.CategoryParent;
import droidahmed.com.jm3eia.model.MainApi;
import droidahmed.com.jm3eia.model.MainCategory;

import static android.Manifest.permission.BLUETOOTH;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends SlidingFragmentActivity {
    ImageView imageToggle, imgLogo, imageToggleRight;
    TextView tvTitle;
    ArrayList<CategoryParent> pro;
    private OnProcessCompleteListener ProductListener;
    MainCategory mainApi;
    MainApi mainPro;
    ArrayList<AllProducts> productsList;
    ArrayList<CartQuantity> cartItemsModify;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Locale locale = new Locale("ar");
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        this.getApplicationContext().getResources().updateConfiguration(config, null);
        SlidingMenu sm = getSlidingMenu();
//        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP_MR1) {
//            if(!checkPermission()){
//                requestPermission();}}
        cartItemsModify = new ArrayList<>();
        sm.setMode(SlidingMenu.LEFT_RIGHT);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        // sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        productsList = new ArrayList<>();
        setBehindContentView(R.layout.menu_slide);
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);


        sm.setSecondaryMenu(R.layout.menu_frame_two);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame_two, MenuFragment.newInstance())
                .commit();


        setBehindView();



        if (getIntent().getExtras() != null) {
            if("notify".equalsIgnoreCase(getIntent().getExtras().getString("notify"))){
            Fragment fragment = new NotifyFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainFragment, fragment)
                    .commitAllowingStateLoss();
        }else
            if (getIntent().getStringExtra("CartAuth").equals("CartAuth")) {
                FragmentProductCart fragment = new FragmentProductCart();
                Bundle bundles = new Bundle();
                bundles.putString("CartAuth", "CartAuth");
                fragment.setArguments(bundles);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragment)
                        .commitAllowingStateLoss();
                new StoreData(MainActivity.this).setDialogType("Auth");
            } else if (getIntent().getStringExtra("CartAuth").equals("NonVisitor")) {
                FragmentProductCart fragment = new FragmentProductCart();
                Bundle bundles = new Bundle();
                bundles.putString("CartAuth", "NonVisitor");
                fragment.setArguments(bundles);
                new StoreData(MainActivity.this).setDialogType("unAuth");

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragment)
                       .commitAllowingStateLoss();
            }

        } else if (Utility.isNetworkConnected(MainActivity.this)) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    mainApi = (MainCategory) result;
                    pro = mainApi.getData();
                    //                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);
                    final Bundle bundle = new Bundle();
                    bundle.putSerializable("array", new ArrayList<>(Arrays.asList(pro)));


                    Fragment fragmentProduct = new FragmentProduct();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.mainFragment, fragmentProduct).addToBackStack("");
                    ft.commitAllowingStateLoss();

                    fragmentProduct.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainFragment, fragmentProduct)
                            .commitAllowingStateLoss();
                    Fragment menuFragment = new MenuFragmentRight();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("category", pro);
                    menuFragment.setArguments(bundle1);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.menu_slide, menuFragment)
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


        ProductListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                mainPro = (MainApi) result;
                productsList = mainPro.getData();
                for (int i = 0; i < productsList.size(); i++) {

                    AllProducts allProducts = productsList.get(i);
                    CartQuantity cartItem = new CartQuantity(allProducts.getID(), allProducts.getCode(), allProducts.getCategoryID(),
                            allProducts.getBrandID(), allProducts.getPrice(), allProducts.getQuantity(), allProducts.getPicture(), allProducts.getSliderPictures(),
                            allProducts.getCreatedDate(), allProducts.getModifiedDate(), allProducts.getViewed(), allProducts.getFeatured(), allProducts.getState(),
                            allProducts.getProductID(), allProducts.getLanguageID(), allProducts.getName(), allProducts.getAlias(),
                            allProducts.getContents(), allProducts.getDescription(), allProducts.getKeywords(), allProducts.getCategoryName(), allProducts.getBrandName(),
                            1);

                    cartItemsModify.add(cartItem);
                    //    saveAuth.setCartQuan(cartItemsModify);
                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    Gson gson = new Gson();

                    String json = gson.toJson(cartItemsModify);

                    editor.putString("jsons", json);
                    editor.commit();
                }

            }

            @Override
            public void onFailure() {

            }
        };

        GetHome task = new GetHome(this, ProductListener, "1");
        task.execute();


//        SlidingMenu sm = getSlidingMenu();
//        sm.setMode(SlidingMenu.LEFT_RIGHT);
//        sm.setShadowWidthRes(R.dimen.shadow_width);
//        sm.setShadowDrawable(R.drawable.shadow);
//        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        sm.setFadeDegree(0.35f);
//   //     sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        Drawable myIcon = getResources().getDrawable( R.drawable.section_icon );
//        ColorFilter filter = new LightingColorFilter( getResources().getColor(R.color.orange), getResources().getColor(R.color.orange) );
//        assert myIcon != null;
//        myIcon.setColorFilter(filter);

        imageToggle = (ImageView) findViewById(R.id.imageToggle);
        imageToggleRight = (ImageView) findViewById(R.id.imageToggleCategory);
        // imageToggleRight.setImageDrawable(myIcon);

        imgLogo = (ImageView) findViewById(R.id.logo);
        tvTitle = (TextView) findViewById(R.id.textTitle);

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED;
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0) {
//
//                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//
//                    if (locationAccepted ){
//
//                    }
//                    else {
//
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
//                                showMessageOKCancel("You need to allow access the permissions",
//                                        new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE},
//                                                            PERMISSION_REQUEST_CODE);
//                                                }
//                                            }
//                                        });
//                                return;
//                            }
//                        }
//
//                    }
//                }
//
//
//                break;
//        }
//    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.delete();
    }
}
