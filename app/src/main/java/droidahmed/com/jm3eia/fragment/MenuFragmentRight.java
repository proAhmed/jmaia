package droidahmed.com.jm3eia.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.controller.ExpandableAdapter;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CategoryParent;
import droidahmed.com.jm3eia.model.CategoryParentList;
import droidahmed.com.jm3eia.model.SlidingMenuItem;
import droidahmed.com.jm3eia.start.MainActivity;


/**
 * Created by Hong Thai.
 */
public class MenuFragmentRight extends Fragment implements OnItemListener {

    private View rootView;
 //   private ExpandableListView listView;
    private ArrayList<CategoryParent> listMain;
    static ArrayList<CategoryParent>allProducts;
HashMap<CategoryParent, CategoryParent>hashChild;
    ArrayList<CategoryParent>childArrayList;
    ArrayList<CategoryParent>mainArrayList;
 ArrayList<CategoryParentList>categoryParentLists;
    private RecyclerView recyclerview;

    public static Fragment newInstance(ArrayList<CategoryParent>allProductses) {
        MenuFragmentRight fragment = new MenuFragmentRight();
        allProducts = allProductses;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create menu items
//        for (int i=0;i<allProducts.size();i++){
//         if(allProducts.get(i).isParent()==false){
//             hashChild.put(  allProducts.get(i).getPathName().get(0).getName(),allProducts.get(i));
//          }
//            String pathName =
//            allProducts.get(i).getPathName().get(0).getName();
//              listMain.add(pathName);
//            listMain= removeDuplicates(listMain);
//        }

      //  setGroupParents();


       hashChild = new HashMap<>();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_menu_right, container, false);
     //   listView = (ExpandableListView) rootView.findViewById(R.id.list);
        recyclerview = (RecyclerView)rootView. findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listMain = new ArrayList<>();
        hashChild = new HashMap<>();
        mainArrayList = new ArrayList<>();
        childArrayList   = new ArrayList<>();
        final OnItemListener onItemListener= this;

        //  View header = inflater.inflate(R.layout.view_head,  listView, false);
//        TextView textRight =(TextView) header.findViewById(R.id.textRight);
//        textRight.setText(getString(R.string.category));
//        header.setMinimumHeight(40);
     //   recyclerview.addHeaderView(header);
        listMain = new ArrayList<>();
        categoryParentLists = new ArrayList<>();

         //create menu items

        for (int i=0;i<allProducts.size();i++){

            if(allProducts.get(i).getParentID()==0){
                 CategoryParent    categoryParent = allProducts.get(i);
                mainArrayList.add(categoryParent);
             ///    Log.d("main_pa",mainArrayList.get(i).getAlias());
                categoryParentLists.add( new CategoryParentList(0,categoryParent));
            }else{
                 CategoryParent categoryParent = allProducts.get(i);
                childArrayList.add(categoryParent);
                  CategoryParentList category = new CategoryParentList(1);
                CategoryParentList places = new CategoryParentList(1, categoryParent);

                category.lists = new ArrayList<>();
                category.lists.add(new CategoryParentList(1, 1, categoryParent));
                  categoryParentLists.add(category);

             }
         }


        recyclerview.setAdapter(new ExpandableAdapter(mainArrayList,childArrayList,onItemListener));

      //  ExpandableListAdapter
 //for(int i =0;i<childArrayList.size();i++){
//    for(int x =0;x<mainArrayList.size();x++){
//        if(childArrayList.get(i).getParentID()==mainArrayList.get(x).getID()){
//            hashChild.put(mainArrayList.get(x),childArrayList.get(i));
//        }
//    }
////}
//        HashMap<Integer,CategoryParent> hashMap = new HashMap();
//        HashMap<Integer,ArrayList<CategoryParent>>category = new HashMap<>();
//        ArrayList<CategoryParent>categoryParents = new ArrayList<>();
//         for(int i =0;i<childArrayList.size();i++){
//            hashMap.put(childArrayList.get(i).getParentID(), childArrayList.get(i));
//            categoryParents.add(hashMap.get(childArrayList.get(i).getParentID())) ;
//      category.put(categoryParents.get(0).getParentID(), categoryParents);
//        }
//     //   Log.d("iii",category.toString());
//
//       ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(),listMain, category);
////          // adapter.setInflater((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE), getActivity());
//         listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),"iio",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),"iio22",Toast.LENGTH_LONG).show();
//
//            }
//        });
//        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//
//                Fragment fragmentProduct = new FragmentSubCategoryProduct();
////                    FragmentManager fm = getSupportFragmentManager();
////                    FragmentTransaction ft = fm.beginTransaction();
//                //                    ft.replace(R.id.mainFragment, fragmentProduct);
////                    ft.commit();h
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", hashChild.get(listMain.get(groupPosition)).get(childPosition).getCategoryID());
//
//
//                fragmentProduct.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
//                        .commitAllowingStateLoss();
//                MainActivity mainActivity = (MainActivity) getActivity();
//                if(mainActivity!=null)
//                    mainActivity.toggle();
//                return false;
//            }
//        });

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

    @Override
    public void onClick(int position, boolean isLongClick) {
        Fragment fragmentProduct = new FragmentSubCategoryProduct();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
        //                    ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
        Bundle bundle = new Bundle();
        bundle.putInt("id", position);


        fragmentProduct.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                .commitAllowingStateLoss();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.toggle();
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



    public class ExpandableListAdapters extends BaseExpandableListAdapter {

        private Context _context;
        private List<CategoryParent> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<Integer,  ArrayList<CategoryParent>> _listDataChild;

        public ExpandableListAdapters(Context context, List<CategoryParent> listDataHeader,
                                     HashMap<Integer, ArrayList<CategoryParent>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
            Log.d("iio",listChildData.toString());
        }



        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            Log.d("iii", _listDataChild.get(this._listDataHeader.get(groupPosition).getID()) + "");
            return _listDataChild.get(_listDataHeader.get(groupPosition).getID());        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            Log.d("iiioio",getChild(groupPosition, childPosition)+"");
if( getChild(groupPosition, childPosition)!=null) {
    final String childText = ((CategoryParent) getChild(groupPosition, childPosition)).getAlias();

    if (convertView == null) {
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.item_menu_child, null);
    }

    TextView txtListChild = (TextView) convertView
            .findViewById(R.id.child_item);

    txtListChild.setText(childText);
}
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Log.d("iiioo", _listDataChild.get(70).size()+"") ;
            return this._listDataChild.get( _listDataHeader.get(groupPosition).getID())
                    .size();
         //   return 1;
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
             if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_menu_right, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.name);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(_listDataHeader.get(groupPosition).getName());

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