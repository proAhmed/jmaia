package droidahmed.com.jm3eia.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.account.SignIn;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.Product;
import droidahmed.com.jm3eia.model.SlidingMenuItem;
import droidahmed.com.jm3eia.start.MainActivity;


/**
 * Created by Hong Thai.
 */
public class MenuFragmentRight extends Fragment {

    private View rootView;
    private ExpandableListView listView;
    private ArrayList<String> listMain;
    private ArrayList<AllProducts> listChild,ListChild1,ListChild2,ListChild3,ListChild4,ListChild5,ListChild6,ListChild7,ListChild8,ListChild9,ListChild10,ListChild11
            ,ListChild12,ListChild13;

   static ArrayList<AllProducts>allProducts;
HashMap<String,ArrayList<AllProducts>>hashChild;
    private final static String TAG = "MenuFragment";

    public static Fragment newInstance(ArrayList<AllProducts>allProductses) {
        MenuFragmentRight fragment = new MenuFragmentRight();
        allProducts = allProductses;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create menu items
        listMain = new ArrayList<>();
        listChild = new ArrayList<>();
        ListChild1 = new ArrayList<>();
        ListChild2 = new ArrayList<>();

        ListChild3 = new ArrayList<>();
        ListChild4 = new ArrayList<>();
        ListChild5 = new ArrayList<>();
        ListChild6 = new ArrayList<>();
        ListChild7 = new ArrayList<>();
        ListChild8 = new ArrayList<>();
        ListChild9 = new ArrayList<>();
        ListChild10 = new ArrayList<>();
        ListChild11 = new ArrayList<>();
        ListChild12 = new ArrayList<>();
        ListChild13 = new ArrayList<>();

        setGroupParents();

        for(int i =0;i<allProducts.size();i++){
           Log.d("ttt",allProducts.get(i).getCategoryID()+" : "+allProducts.get(i).getCategoryName());

            if(allProducts.get(i).getCategoryID()==116){
                 listChild.add(allProducts.get(i));

                listChild=   removeDuplicate(listChild);
                Log.i("yyy", "" + listChild.size());

            }else if (allProducts.get(i).getCategoryID()==100){
                ListChild1.add( allProducts.get(i));
                ListChild1=   removeDuplicates(ListChild1);

            }else if (allProducts.get(i).getCategoryID()==151){
                ListChild2.add( allProducts.get(i));
                ListChild2=   removeDuplicates(ListChild2);

            }else if (allProducts.get(i).getCategoryID()==129){
                ListChild3.add(allProducts.get(i));
                ListChild3 =   removeDuplicates(ListChild3);
            }else if (allProducts.get(i).getCategoryID()==125){
                ListChild4.add( allProducts.get(i));
                ListChild4 =   removeDuplicates(ListChild4);

            }else if (allProducts.get(i).getCategoryID()==148){
                ListChild5.add( allProducts.get(i));
                ListChild5 =   removeDuplicates(ListChild5);

            }else if (allProducts.get(i).getCategoryID()==111){
                ListChild6.add(allProducts.get(i));
                ListChild6 =   removeDuplicates(ListChild6);

            }else if (allProducts.get(i).getCategoryID()==115){
                ListChild7.add( allProducts.get(i));
                ListChild7 =   removeDuplicates(ListChild7);

            }else if (allProducts.get(i).getCategoryID()==120){
                ListChild8.add( allProducts.get(i));
                ListChild8 =   removeDuplicates(ListChild8);
            }else if (allProducts.get(i).getCategoryID()==106){
                ListChild9.add(allProducts.get(i));
                ListChild9 =   removeDuplicates(ListChild9);

            }
        }
        hashChild = new HashMap<>();
        hashChild.put(listMain.get(0), listChild);
        hashChild.put(listMain.get(1), ListChild12);
        hashChild.put(listMain.get(2), ListChild3);
        hashChild.put(listMain.get(3), ListChild9);
        hashChild.put(listMain.get(4), ListChild8);
        hashChild.put(listMain.get(5), ListChild1);
        hashChild.put(listMain.get(6), ListChild6);
        hashChild.put(listMain.get(7), ListChild12);
        hashChild.put(listMain.get(8), ListChild7);
        hashChild.put(listMain.get(9), ListChild5);
        hashChild.put(listMain.get(10), ListChild4);
        hashChild.put(listMain.get(11), ListChild11);
        hashChild.put(listMain.get(12), ListChild12);
        List<String> al = new ArrayList<>();


// add elements to al, including duplicates

  //         listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category),1));
//        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category1),2));
//        listMenuItems.add(new SlidingMenuItem(0, getResources().getString(R.string.category2),3));
//        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category3),4));
//        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category4),5));
//        listMenuItems.add(new SlidingMenuItem(R.drawable.fb_icon, getResources().getString(R.string.category6),6));
//        listMenuItems.add(new SlidingMenuItem(R.drawable.twitter_icon, getResources().getString(R.string.category7),7));
//        listMenuItems.add(new SlidingMenuItem(R.drawable.insta_icon, getResources().getString(R.string.category8),8));
//        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category9),9));
//        listMenuItems.add(new SlidingMenuItem(0,getResources().getString(R.string.category6),10));
//
//        listMenuItems.add(new SlidingMenuItem( 0,getResources().getString(R.string.category7),11));
//for (int i=0;i<listMenuItems.size();i++){
//    Log.d("iii",""+)
//}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_menu_right, container, false);
        listView = (ExpandableListView) rootView.findViewById(R.id.list);

        View header = inflater.inflate(R.layout.view_head,  listView, false);
        TextView textRight =(TextView) header.findViewById(R.id.textRight);
        textRight.setText(getString(R.string.category));
        header.setMinimumHeight(40);
        listView.addHeaderView(header);
        ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(),listMain, hashChild);

        // adapter.setInflater((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE), getActivity());
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fragment fragmentProduct = new FragmentSubCategoryProduct();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
                //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
                Bundle bundle = new Bundle();
                bundle.putInt("id", hashChild.get(listMain.get(groupPosition)).get(childPosition).getCategoryID());


                fragmentProduct.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                        .commitAllowingStateLoss();
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity!=null)
                    mainActivity.toggle();
                return false;
            }
        });

        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setListViewAdapter();
    }

//    private void setListViewAdapter() {
//        MyExpandableAdapter adapter = new MyExpandableAdapter(listMain, listChild);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(onItemClickListener());
//     }

    /**
     * Go to fragment when menu item clicked!
     *
     * @return
     */

    private ArrayList<AllProducts> removeDuplicate(ArrayList<AllProducts> array){
        Set<AllProducts> hs = new HashSet<>();
        hs.addAll(array);
        array.clear();
       array.addAll(hs);
        return array;
    }
    public ArrayList   removeDuplicates(ArrayList arlList)
    {
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
        return arlList;
    }
    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

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
                     convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu_right, null);
                            // get all UI view
                holder = new ViewHolder(convertView);
                // set tag for holder
             //   convertView.setTag(holder);
//            } else {
//                // if holder created, get tag from view
//                holder = (ViewHolder) convertView.getTag();
//            }
             holder.itemName.setText(getItem(position).getMenuItemName());

            return convertView;
        }

        private class ViewHolder {
            private TextView itemName;

            public ViewHolder(View v) {
                itemName = (TextView) v.findViewById(R.id.name);
             }
        }
    }

//    class MyExpandableAdapter extends BaseExpandableListAdapter {
//
//        private Activity activity;
//         private ArrayList<Object> childtems;
//         private LayoutInflater inflater;
//         private ArrayList<String> parentItems, child;
//
//        public MyExpandableAdapter(ArrayList<String> parents, ArrayList<Object> childern) {
//             this.parentItems = parents;
//             this.childtems = childern;
//         }
//
//        public void setInflater(LayoutInflater inflater, Activity activity) {
//             this.inflater = inflater;
//             this.activity = activity;
//         }
//           @Override
//         public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//            child = (ArrayList<String>) childtems.get(groupPosition);
//
//            TextView textView = null;
//
//            if (convertView == null) {
//                 convertView = inflater.inflate(R.layout.item_menu_right, null);
//             }
//
//            textView = (TextView) convertView.findViewById(R.id.textView1);
//             textView.setText(child.get(childPosition));
//
//            convertView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                 public void onClick(View view) {
//
//                 }
//             });
//
//            return convertView;
//         }
//
//        @Override
//         public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                 convertView = inflater.inflate(R.layout.item_menu_child, null);
//             }
//TextView textView = (TextView) convertView.findViewById(R.id.child_item);
//            textView.setText(parentItems.get(groupPosition));
//
//             return convertView;
//         }
//
//        @Override
//         public Object getChild(int groupPosition, int childPosition) {
//             return null;
//         }
//
//        @Override
//         public long getChildId(int groupPosition, int childPosition) {
//             return 0;
//         }
//
//        @Override
//         public int getChildrenCount(int groupPosition) {
//             return ((ArrayList<String>) childtems.get(groupPosition)).size();
//         }
//
//        @Override
//         public Object getGroup(int groupPosition) {
//             return null;
//         }
//
//        @Override
//         public int getGroupCount() {
//             return parentItems.size();
//         }
//
//        @Override
//         public void onGroupCollapsed(int groupPosition) {
//             super.onGroupCollapsed(groupPosition);
//         }
//
//        @Override
//         public void onGroupExpanded(int groupPosition) {
//             super.onGroupExpanded(groupPosition);
//         }
//
//        @Override
//         public long getGroupId(int groupPosition) {
//             return 0;
//         }
//           @Override
//         public boolean hasStableIds() {
//             return false;
//         }
//
//        @Override
//         public boolean isChildSelectable(int groupPosition, int childPosition) {
//             return false;
//         }
//
//    }
    public void setGroupParents() {
        listMain.add(getActivity().getResources().getString(R.string.category1));
        listMain.add(getActivity().getResources().getString(R.string.category2));
        listMain.add(getActivity().getResources().getString(R.string.category3));
        listMain.add(getActivity().getResources().getString(R.string.category4));
        listMain.add(getActivity().getResources().getString(R.string.category5));
        listMain.add(getActivity().getResources().getString(R.string.category6));
        listMain.add(getActivity().getResources().getString(R.string.category7));
        listMain.add(getActivity().getResources().getString(R.string.category8));
        listMain.add(getActivity().getResources().getString(R.string.category9));
        listMain.add(getActivity().getResources().getString(R.string.category10));
        listMain.add(getActivity().getResources().getString(R.string.category11));
        listMain.add(getActivity().getResources().getString(R.string.category12));
        listMain.add(getActivity().getResources().getString(R.string.category13));
        }




    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, ArrayList<AllProducts>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, ArrayList<AllProducts>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = ((AllProducts) getChild(groupPosition, childPosition)).getCategoryName();

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_menu_child, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.child_item);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
        String    headerTitle =  getGroup(groupPosition).toString();
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_menu_right, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.name);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }


}