package droidahmed.com.jm3eia.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.adapter.ExpandableListAdapter;
import droidahmed.com.jm3eia.adapter.ExpandableListSubAdapters;
import droidahmed.com.jm3eia.controller.OnChildClick;
import droidahmed.com.jm3eia.controller.OnItemListener;
import droidahmed.com.jm3eia.model.CategoryParent;
import droidahmed.com.jm3eia.model.CategoryParentList;
 import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;


/**
 * Created by Hong Thai.
 */
public class MenuFragmentRight extends Fragment implements OnItemListener,OnChildClick {

    private View rootView;
    //   private ExpandableListView listView;
       ArrayList<CategoryParent>allProducts;
     ArrayList<CategoryParent>childArrayList,childSubArrayList;
    ArrayList<Object>childArrayObjects;
    static boolean checkArrow;
    ArrayList<CategoryParent>mainArrayList;
    ArrayList<CategoryParentList>categoryParentLists;
    //  private RecyclerView recyclerview;
    private ExpandableListView expandableListView;
     HashMap<String,ArrayList<CategoryParent>>hashMap,hashChild;
    SaveAuth saveAuth;
    OnChildClick onChildClick;
    ArrayList<CategoryParent>arrayHash;
    Bundle bundle;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_menu_right, container, false);
         hashChild = new HashMap<>();
        mainArrayList = new ArrayList<>();
        childSubArrayList = new ArrayList<>();
        childArrayList   = new ArrayList<>();
        childArrayObjects = new ArrayList<>();
        final OnItemListener onItemListener= this;
         arrayHash = new ArrayList<>();
         categoryParentLists = new ArrayList<>();
        hashMap = new HashMap<>();
        arrayHash = new ArrayList<>();
        saveAuth = (SaveAuth) getActivity().getApplicationContext();
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.list);
        saveAuth.setArrowCheck(false);
        bundle = new Bundle();
         onChildClick = this;
        Bundle bundles = getArguments();
        allProducts = (ArrayList<CategoryParent>) bundles.getSerializable("category");
        //create menu items
        for (int i=0;i<allProducts.size();i++) {
        }
        for (int i=0;i<allProducts.size();i++){
            allProducts.get(i).setKeywords("child");
            if(allProducts.get(i).getParentID()==0){
                CategoryParent    categoryParent = allProducts.get(i);
                categoryParent.setKeywords("parent");

                mainArrayList.add(categoryParent);

                categoryParentLists.add( new CategoryParentList(0,categoryParent));
            }else if(allProducts.get(i).getParentID()!=0&&allProducts.get(i).isParent()){
                CategoryParent categoryChild = allProducts.get(i);
                categoryChild.setKeywords("child0");
                childSubArrayList.add(categoryChild);
                childArrayList.add(categoryChild);
                CategoryParentList category = new CategoryParentList(2);
                Log.d("nnnpppp",childSubArrayList.size()+"");

                category.lists = new ArrayList<>();
                category.lists.add(new CategoryParentList(1, 2, categoryChild));
                categoryParentLists.add(category);

            }else{
                CategoryParent categoryChild = allProducts.get(i);
                categoryChild.setKeywords("child");
                childArrayList.add(categoryChild);
                CategoryParentList category = new CategoryParentList(1);

                category.lists = new ArrayList<>();
                category.lists.add(new CategoryParentList(1, 1, categoryChild));
                categoryParentLists.add(category);
            }
        }

        updateMap();
        updateChildMap();
    Log.d("nnnccc",hashMap.get(mainArrayList.get(2).getAlias()).get(0).getName()+ "  "+mainArrayList.size());


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
         final ExpandableListAdapter expandableListAdapter =
                new ExpandableListAdapter(getActivity(), mainArrayList, hashMap, onItemListener,onChildClick);
        // expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setAdapter(new ParentLevel(getActivity(),onItemListener,onChildClick));
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                ArrayList<CategoryParent> hashs = hashMap.get(mainArrayList.get(groupPosition).getAlias());
             //   secondLevelELV.setAdapter(new SecondLevelAdapter(context,hashs));

                return false;
            }
        });
        if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
             expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        } else {

            expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        }

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
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

        checkArrow = isLongClick;
        fragmentProduct.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                .commitAllowingStateLoss();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.toggle();
    }

    ///////////////////////////////////////
    ArrayList<CategoryParent> tempList,tempChild;

    private ArrayList<CategoryParent> getListToAddToHashMap(CategoryParent object){
        ArrayList<CategoryParent> tempList = new ArrayList<>();
        for(CategoryParent childObject : childSubArrayList)
        {

            if(childObject.getID()==object.getParentID())
            {
                tempList.add(childObject);

            }
        }

         return tempList;
    }
    private ArrayList<CategoryParent> getListToAddToHashMaps(CategoryParent object){
        ArrayList<CategoryParent> tempList = new ArrayList<>();
        for(CategoryParent childObject : childArrayList)
        {

            if(childObject.getParentID()==object.getID())
            {
                tempList.add(childObject);

            }
        }

        return tempList;
    }
    private void updateMap(){
        tempList = new ArrayList<>(); // this is the list you will populate
        for(CategoryParent object : mainArrayList)
        {
            tempList = getListToAddToHashMaps(object);
            if(!tempList.isEmpty())
            {
                hashMap.put(object.getAlias(), tempList);
            }

        }

    }
    private void updateChildMap(){
        tempChild = new ArrayList<>(); // this is the list you will populate
        for(CategoryParent object : childArrayList) {
            for(int i =0;childSubArrayList.size()>i;i++){
                 if (childSubArrayList.get(i).getID() == object.getParentID()) {
                    tempChild = getListToAddToHashMap(object);
                    if (!tempChild.isEmpty()) {
                        hashChild.put(object.getAlias(), tempChild);
                    }

                }}

        }
        Log.d("nnnnbbb",tempChild.size()+"   "+hashChild.size());

    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void onClick(CategoryParent categoryParent) {
        try {
            Fragment fragmentProduct = new FragmentSubCategoryProduct();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                                        ft.replace(R.id.mainFragment, fragmentProduct);
//                    ft.commit();h
               bundle.putInt("id", categoryParent.getID());

            fragmentProduct.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, fragmentProduct).addToBackStack("")
                    .commitAllowingStateLoss();
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.toggle();
        }catch (Exception e){
         }
    }

    ///////////////////////////////////////////////////////////////////////////

    public class ExpandableListAdapters extends BaseExpandableListAdapter {

        private Context _context;
        private List<CategoryParent> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, ArrayList<CategoryParent>> _listDataChild;
        OnItemListener onItemListener;
        OnChildClick onChildClick;
        public ExpandableListAdapters(Context context, List<CategoryParent> listDataHeader,
                                      HashMap<String, ArrayList<CategoryParent>> listChildData,
                                      OnItemListener onItemListener, OnChildClick onChildClick) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
            this.onItemListener =onItemListener;
            this.onChildClick =onChildClick;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            Log.d("pppch",""+this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                    .get(childPosititon));
            return this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = ((CategoryParent) getChild(groupPosition, childPosition)).getAlias();

//        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_child_right, null);
//        }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.name);
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);

            txtListChild.setText(childText);
            ExpandableListSubAdapters SecondLevelexplv = new ExpandableListSubAdapters(getActivity(), mainArrayList, hashMap, onItemListener,onChildClick);
//            SecondLevelexplv.se(new SecondLevelAdapter());
//            return SecondLevelexplv;
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!((CategoryParent) getChild(groupPosition, childPosition)).isParent()){
                        onChildClick.onClick(((CategoryParent) getChild(groupPosition, childPosition)));
                    }else{

                    }
                }
            });
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if(this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())==null){
                return 0;
            }else{
                return this._listDataChild.get(this._listDataHeader.get(groupPosition).getAlias())
                        .size();
            }

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
        public View getGroupView(final int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = ((CategoryParent) getGroup(groupPosition)).getAlias();
//        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_menu_right_mod, null);
//        }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.name);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);
            Picasso.with(_context).load("http://jm3eia.com/" +((CategoryParent) getGroup(groupPosition)).getPicture()).into(imageView);
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);
            lblListHeader.setText(headerTitle);
            ImageView arrow_image = (ImageView) convertView.findViewById(R.id.arrow_image);
            if (isExpanded) {
                arrow_image.setImageResource(R.drawable.arrow_down);
            } else {
                arrow_image.setImageResource(R.drawable.arrow);
            }
            if (!((CategoryParent) getGroup(groupPosition)).isParent()) {
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemListener.onClick(((CategoryParent) getGroup(groupPosition)).getID(), false);
                    }
                });
            }
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

    //////////////////////////////////////////////////////////////////////////





    private class ParentLevel extends BaseExpandableListAdapter {
        private Context context;
        OnChildClick onChildClick;
        OnItemListener onItemListener;
        ParentLevel(Context context, OnItemListener onItemListener, OnChildClick onChildClick) {
            this.context = context;
            this.onItemListener = onItemListener;
            this.onChildClick = onChildClick;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return hashMap.get(mainArrayList.get(groupPosition).getAlias())
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = ((CategoryParent) getChild(groupPosition, childPosition)).getAlias();


       ArrayList<CategoryParent> hashs = hashMap.get(mainArrayList.get(groupPosition).getAlias());

            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_right, null);
             TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.name);
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);

            txtListChild.setText(childText);

            if(hashChild.get(childText)!=null ) {
                SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(getActivity());
                secondLevelELV.setAdapter(new SecondLevelAdapter(context,hashs));
//            Log.d("pppp",hashs.size()+"");
//                 elv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT,  AbsListView.LayoutParams.WRAP_CONTENT));
//                elv.setAdapter(new SecondLevelAdapter(context, hashs));
                return secondLevelELV;
            }else{
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        onChildClick.onClick(((CategoryParent) getChild(groupPosition, childPosition)));
                    }
                });
            }

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if(hashMap.get(mainArrayList.get(groupPosition).getAlias())==null){
                return 0;
            }else{
                return hashMap.get(mainArrayList.get(groupPosition).getAlias())
                        .size();
            }

        }

        @Override
        public Object getGroup(int groupPosition) {
            return mainArrayList.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return mainArrayList.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = ((CategoryParent) getGroup(groupPosition)).getAlias();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_menu_right_mod, null);
            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.name);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);
            Picasso.with(getActivity()).load("http://jm3eia.com/" +((CategoryParent) getGroup(groupPosition)).getPicture()).into(imageView);
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);
            lblListHeader.setText(headerTitle);
            ImageView arrow_image = (ImageView) convertView.findViewById(R.id.arrow_image);
            if (isExpanded) {
                arrow_image.setImageResource(R.drawable.arrow_down);
            } else {
                arrow_image.setImageResource(R.drawable.arrow);
            }
            if (!((CategoryParent) getGroup(groupPosition)).isParent()) {
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemListener.onClick(((CategoryParent) getGroup(groupPosition)).getID(), false);
                    }
                });
            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        private Context context;
        ArrayList<CategoryParent>child;
        public SecondLevelAdapter(Context context,ArrayList<CategoryParent>child) {
            this.context = context;
            this.child = child;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            Log.d("pppp00",child.size()+"");

            return child.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_third, null);

                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);
                text.setText(child.get(groupPosition).getAlias());
            Log.d("pppp888",child.get(groupPosition).getAlias());

            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return hashChild.get(child.get(groupPosition).getAlias()).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_four, null);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);
            final String childText = ((CategoryParent) getChild(groupPosition, childPosition)).getAlias();
            text.setText(childText);
            Log.d("pppp888",child.get(groupPosition).getAlias());
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.lin);
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onChildClick.onClick(((CategoryParent) getChild(groupPosition, childPosition)));
                }
            });
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Log.d("ppp111",hashChild.get(child.get(groupPosition).getAlias()).size()+"");
            if(hashChild.get(child.get(groupPosition).getAlias())==null){
                return 0;
            }else{
                return hashChild.get(child.get(groupPosition).getAlias())
                        .size();
            }
     }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}