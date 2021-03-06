package droidahmed.com.jm3eia.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.account.SignIn;
import droidahmed.com.jm3eia.api.SignOutApi;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.SignOut;
import droidahmed.com.jm3eia.model.SlidingMenuItem;
import droidahmed.com.jm3eia.model.UserSignOutResponse;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveData;


/**
 * Created by Hong Thai.
 */
public class MenuFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private ArrayList<SlidingMenuItem> listMenuItems;
    private final static String TAG = "MenuFragment";
    DatabaseHelper databaseHelper;
    OnProcessCompleteListener onProfileListener;
    public static Fragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create menu items
        listMenuItems = new ArrayList<>();
         listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.main_page),1));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.cart),2));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.cart_history),3));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.setting),4));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.notify),5));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.contact_us),6));
        listMenuItems.add(new SlidingMenuItem(R.drawable.fb_icon, getResources().getString(R.string.facebook),7));
        listMenuItems.add(new SlidingMenuItem(R.drawable.twitter_icon, getResources().getString(R.string.twitter),8));
        listMenuItems.add(new SlidingMenuItem(R.drawable.insta_icon, getResources().getString(R.string.instagram),9));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.call_us),10));
        listMenuItems.add(new SlidingMenuItem(0,"",11));
//
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.about_us),12));
        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.rules),13));

        if(new StoreData(getActivity()).getLogin().equals("")) {
            listMenuItems.add(new SlidingMenuItem( 0,getResources().getString(R.string.login),14));

        }else {
            listMenuItems.add(new SlidingMenuItem( 0,getResources().getString(R.string.sign_out),14));

        }
//for (int i=0;i<listMenuItems.size();i++){
 //}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);

        View header = inflater.inflate(R.layout.view_head,  listView, false);
        header.setMinimumHeight(40);
        listView.addHeaderView(header);
         return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        SlidingMenuAdapter adapter = new SlidingMenuAdapter(getActivity(), R.layout.item_menu, listMenuItems);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(onItemClickListener());
        Log.i(TAG, "create adapter " + listMenuItems.size());
    }

    /**
     * Go to fragment when menu item clicked!
     *
     * @return
     */
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MainActivity mainActivity = (MainActivity) getActivity();

switch (position){

    case 1:
        ft.replace(R.id.mainFragment, new FragmentProduct());
        ft.commit();

        mainActivity. toggle();
        break;
    case 2:
        ft.replace(R.id.mainFragment, new FragmentProductCart());
         ft.commit();

        mainActivity. toggle();
         break;
    case 3:
        if(!new StoreData(getActivity()).getAuthName().equals("")) {

            ft.replace(R.id.mainFragment, new HistoryCartFragment());
            ft.commit();
        }else{
            Toast.makeText(getActivity(),"يجب عليك تسجيل الدخول اولا",Toast.LENGTH_LONG).show();
        }
        mainActivity. toggle();
        break;
    case 4:
        if(!new StoreData(getActivity()).getAuthName().equals("")) {
            ft.replace(R.id.mainFragment, new SettingFragment());
            ft.commit();

        }else{
            Toast.makeText(getActivity(),"يجب عليك تسجيل الدخول اولا",Toast.LENGTH_LONG).show();

        }
        mainActivity.toggle();

        break;
    case 5:
        ft.replace(R.id.mainFragment, new NotifyFragment());
        ft.commit();

        mainActivity. toggle();
        break;

    case 7:
        Fragment fragmentProduct = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("link", "https://www.facebook.com/jm3eia");

        fragmentProduct.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                .commitAllowingStateLoss();
        mainActivity. toggle();

//        ft.replace(R.id.mainFragment, new FragmentProductCart());
//        ft.commit();
//        MainActivity mainActivity4 = (MainActivity) getActivity();
//
//        mainActivity4. toggle();
        break;

    case 8:
        Fragment webFragment = new WebFragment();
        Bundle bundles = new Bundle();
        bundles.putSerializable("link", "https://twitter.com/@jm3eia");

        webFragment.setArguments(bundles);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, webFragment).addToBackStack("")
                .commitAllowingStateLoss();
//        ft.replace(R.id.mainFragment, new FragmentProductCart());
//        ft.commit();
//        MainActivity mainActivity4 = (MainActivity) getActivity();
//
        mainActivity. toggle();
        break;
    case 9:
        Fragment webFragments = new WebFragment();
        Bundle bundless = new Bundle();
        bundless.putSerializable("link", "https://www.instagram.com/jm3eia/");

        webFragments.setArguments(bundless);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, webFragments).addToBackStack("")
                .commitAllowingStateLoss();

        mainActivity. toggle();
//        ft.replace(R.id.mainFragment, new FragmentProductCart());
//        ft.commit();
//        MainActivity mainActivity4 = (MainActivity) getActivity();
//
//        mainActivity4. toggle();
        break;
    case 11:
         Utility.call(getActivity(), 666000931);
        mainActivity. toggle();

        break;
    case 12:
        Fragment aboutFragments = new AboutFragment();

         getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, aboutFragments).addToBackStack("")
                .commitAllowingStateLoss();

        mainActivity. toggle();

        break;
    case 13:
        Fragment ruleFragments = new RulesFragment();

         getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, ruleFragments).addToBackStack("")
                .commitAllowingStateLoss();

        mainActivity. toggle();

        break;
    case 14:
        if(new StoreData(getActivity()).getLogin().equals("login")){
            dialog();
            mainActivity. toggle();



        }else{
            Intent intent = new Intent(getActivity(), SignIn.class);
            startActivity(intent);
            mainActivity. toggle();
        }


        break;
 }
//                SlidingMenuItem item = (SlidingMenuItem) parent.getItemAtPosition(position);
//                ((MainActivity) getActivity()).transactionFragments(ContactFragment.newInstance(item.getMenuItemName()),
//                        R.id.mainFragment);
            }
        };
    }

    /**
     * private class to make a listview adapter based on ArrayAdapter
     */
    private class SlidingMenuAdapter extends ArrayAdapter<SlidingMenuItem> {

        private FragmentActivity activity;
        private ArrayList<SlidingMenuItem> items;

        public SlidingMenuAdapter(FragmentActivity activity, int resource, ArrayList<SlidingMenuItem> objects) {
            super(activity, resource, objects);
            this.activity = activity;
            this.items = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            // If holder not exist then locate all view from UI file.
        //    if (convertView == null) {
                // inflate UI from XML file
                if (items.get(position).getPositions() == 0||items.get(position).getPositions() == 1||items.get(position).getPositions() == 2|items.get(position).getPositions() == 3
                        |items.get(position).getPositions() == 4  |items.get(position).getPositions() == 12|items.get(position).getPositions() == 13) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, null);
                } else if (items.get(position).getPositions() == 7||items.get(position).getPositions() == 8||items.get(position).getPositions() == 9) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_two, null);
                } else if (items.get(position).getPositions() == 6||items.get(position).getPositions() == 10) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_four
                            , null);
                }else if (items.get(position).getPositions() == 11) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_three
                            , null);
                }else if (items.get(position).getPositions() == 14) {
                    if(new StoreData(getActivity()).getLogin().equals("")) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_five
                                , null);
                    }else{
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_six
                                , null);
                    }
                }                // get all UI view
                holder = new ViewHolder(convertView);
                // set tag for holder
             //   convertView.setTag(holder);
//            } else {
//                // if holder created, get tag from view
//                holder = (ViewHolder) convertView.getTag();
//            }
if(getItem(position).getPositions()!=11)
            holder.itemName.setText(getItem(position).getMenuItemName());
            if(getItem(position).getImageResource()!=0)
           holder.itemImage.setImageResource(getItem(position).getImageResource());

            return convertView;
        }

        private class ViewHolder {
            private TextView itemName;
            private ImageView itemImage;

            public ViewHolder(View v) {
                itemName = (TextView) v.findViewById(R.id.name);
                itemImage = (ImageView) v.findViewById(R.id.image);
            }
        }
    }

    private  void dialog(){
          final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.sign_dialog);
        dialog.setTitle(getResources().getString(R.string.dialog_sure));

        // set the custom dialog components - text, image and button
        Button yes = (Button) dialog.findViewById(R.id.yes);
         Button no = (Button) dialog.findViewById(R.id.no);

         // if button is clicked, close the custom dialog
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                dialog.dismiss();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
            private void signOut(){
                if (Utility.isNetworkConnected(getActivity())) {

                    onProfileListener = new OnProcessCompleteListener() {

                        @Override
                        public void onSuccess(Object result) {
                            SignOut userSingOutResponse = (SignOut) result;
                             Toast.makeText(getActivity(), userSingOutResponse.getIsResult(), Toast.LENGTH_LONG).show();
                            new StoreData(getActivity()).savLogin("");
                            setListViewAdapter();
                            listMenuItems.remove(13);
                            listMenuItems.add(new SlidingMenuItem(0, getResources().getString(R.string.login), 14));
                            databaseHelper = new DatabaseHelper(getActivity());
                            new StoreData(getActivity()).saveAuthName("");
                            new StoreData(getActivity()).saveAuthPass("");
                            new StoreData(getActivity()).saveGada("");
                            new StoreData(getActivity()).saveHouse("");
                            new StoreData(getActivity()).saveMobile("");
                            new StoreData(getActivity()).saveEmail("");
                            new StoreData(getActivity()).saveFullName("");
                            new StoreData(getActivity()).saveStreet("");
                            new StoreData(getActivity()).saveWidget("");
                            new StoreData(getActivity()).saveZone("");
                            databaseHelper.delete();
                            databaseHelper.deleteCart();
                            databaseHelper.deleteCartAdd();
                            databaseHelper.remove();
                            databaseHelper.removeCart();
                            databaseHelper.removeCartAdd();
                       }

                        @Override
                        public void onFailure() {
                            Utility.showFailureDialog(getActivity(), false);
                        }
                    };

                    SignOutApi task = new SignOutApi(getActivity(), onProfileListener);
                    task.execute();

                } else {
                    Utility.showValidateDialog(
                            getResources().getString(R.string.failure_ws),
                            getActivity());
                }
        }
}