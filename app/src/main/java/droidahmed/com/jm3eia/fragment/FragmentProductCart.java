package droidahmed.com.jm3eia.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.account.CartNotAuth;
import droidahmed.com.jm3eia.account.SignIn;
import droidahmed.com.jm3eia.adapter.CartGridAdapter;
import droidahmed.com.jm3eia.api.DeleteCartItem;
import droidahmed.com.jm3eia.api.ShowCartItem;
import droidahmed.com.jm3eia.api.AddCartItemNotAuth;
import droidahmed.com.jm3eia.api.CheckOutForSignUser;
import droidahmed.com.jm3eia.api.ShowCartItemAuth;
import droidahmed.com.jm3eia.controller.DatabaseHelper;
import droidahmed.com.jm3eia.controller.OnAddItem;
import droidahmed.com.jm3eia.controller.OnCancelOrder;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
import droidahmed.com.jm3eia.model.CartCheck;
import droidahmed.com.jm3eia.model.CartItem;
import droidahmed.com.jm3eia.model.CartItemResponse;
import droidahmed.com.jm3eia.model.CartQuantity;
import droidahmed.com.jm3eia.model.CheckOutData;
import droidahmed.com.jm3eia.model.DeleteProduct;
import droidahmed.com.jm3eia.model.ItemJson;
import droidahmed.com.jm3eia.model.ProductCart;
import droidahmed.com.jm3eia.model.ResponseOfCheckOut;
import droidahmed.com.jm3eia.start.MainActivity;
import droidahmed.com.jm3eia.start.SaveAuth;

/**
 * Created by ahmed on 3/15/2016.
 */
public class FragmentProductCart extends Fragment implements OnCartListener ,OnCancelOrder,OnAddItem{
    GridView lstProduct;
    ArrayList<AllProducts> pro;
    Button btnRequest, btnCancel;
    TextView tvDeliver, tvTotal, tvFinalTotal;
    private OnProcessCompleteListener ProductListener;
     AllProducts[] pros;
ArrayList<ProductCart>productCarts;
    ArrayList<ProductCart>productCartItems;
    CartItemResponse cartItemResponse;
    ResponseOfCheckOut checkResponse;
     CheckOutData checkOutDatas;
    ArrayList<CartItem> checkCart;
ArrayList<ItemJson>itemJsonArrayList;
    ArrayList<CartQuantity>cartItemArrayList;
static double pricessss;
    private JSONArray jsonArrayItem;
      OnCartListener onCartListener;
    OnCancelOrder onCancelOrder;
    SaveAuth saveAuth;
    FragmentProductCart fragmentProductCart;
    OnAddItem onAddItem;
    DatabaseHelper databaseHelper;
    int checkAdd = 0;
    int checkEnter = 0;
      @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        lstProduct = (GridView) view.findViewById(R.id.cart);
        pro = new ArrayList<>();
        // lstProduct.setAdapter(new CuListAdapter(getActivity(),pro));
        onCartListener = (OnCartListener) this;
        onCancelOrder = (OnCancelOrder) this;
        onAddItem = this;
        fragmentProductCart = this;
        productCarts = new ArrayList<>();
        productCartItems = new ArrayList<>();
        checkCart = new ArrayList<>();
        checkOutDatas = new CheckOutData();
        itemJsonArrayList = new ArrayList<>();
        final Bundle bundle = getArguments();
        jsonArrayItem = new JSONArray();
        btnRequest = (Button) view.findViewById(R.id.btnRequest);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvDeliver = (TextView) view.findViewById(R.id.tvDeliver);
        tvDeliver.setText("1 دك");
        databaseHelper = new DatabaseHelper(getActivity());
         if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
             if (getActivity().getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                 lstProduct.setHorizontalSpacing((int) -1);
             } else {
                 lstProduct.setHorizontalSpacing((int) 1);
             } }
        tvTotal = (TextView) view.findViewById(R.id.tvTotal);
        tvFinalTotal = (TextView) view.findViewById(R.id.tvFinalTotal);
        //        tvTotal.setText(""+pricessss);
        saveAuth = (SaveAuth) getActivity().getApplicationContext();


        if (new StoreData(getActivity()).getAuthName().equals("")){
            if(saveAuth.getItemJsons()!=null)
            itemJsonArrayList.addAll(saveAuth.getItemJsons());
        for (int i = 0; i < itemJsonArrayList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("ID", itemJsonArrayList.get(i).getIdItem());

                jsonObject.put("Quantity", itemJsonArrayList.get(i).getQuantityItem());
                jsonObject.put("CreatedDate", itemJsonArrayList.get(i).getTimeItem());
                jsonArrayItem.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

            if(databaseHelper.getCart()!=null&&databaseHelper.getCart().size()>0)
                if(databaseHelper.getCart().size()>0)
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    cartItemResponse = (CartItemResponse) result;
                    cartItemArrayList = cartItemResponse.getData();
                    if(saveAuth.getCartQuanPos()!=null){
                        cartItemArrayList = deletedbyId(cartItemArrayList);
                    }

                    if( databaseHelper.getCartAdd()!=null){
                        for(int i=0;i<databaseHelper.getCart().size();i++) {
                            for(int ii=0;ii<databaseHelper.getCartAdd().size();ii++) {
                                if( databaseHelper.getCartAdd().get(ii).getID()==databaseHelper.getCart().get(i).getID()){
                                    databaseHelper.getCart().get(i).setcQuantity(databaseHelper.getCartAdd().get(ii).getcQuantity());
                                }
                            }
                        }
                    }

                         lstProduct.setAdapter(new CartGridAdapter(getActivity(), databaseHelper.getCart(), onCartListener, onCancelOrder,onAddItem));

                        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View v,
                                                    int position, long id) {

                            }
                        });
                        priceProduct( databaseHelper.getCart());
                        tvTotal.setText( String.format("%.3f", priceProduct(databaseHelper.getCart()))+" "+getResources().getString(R.string.dr));
                        tvFinalTotal.setText(String.format("%.3f", (priceProduct( databaseHelper.getCart()) + 1))+" "+getResources().getString(R.string.dr));


                 }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            ShowCartItem task = new ShowCartItem(getActivity(), ProductListener);
            task.execute(databaseHelper.getCart());

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
    }else{
            if (Utility.isNetworkConnected(getActivity())) {

                ProductListener = new OnProcessCompleteListener() {

                    @Override
                    public void onSuccess(Object result) {
                        cartItemResponse = (CartItemResponse) result;
                        cartItemArrayList = cartItemResponse.getData();
                        priceProduct(cartItemArrayList);
                        tvTotal.setText(String.format("%.3f", (priceProduct(cartItemArrayList)))+" "+getResources().getString(R.string.dr));
                        tvFinalTotal.setText(  String.format("%.3f", (priceProduct(cartItemArrayList) + 1))+" "+getResources().getString(R.string.dr) );

                        lstProduct.setAdapter(new CartGridAdapter(getActivity(), cartItemArrayList, onCartListener, onCancelOrder, onAddItem));

                        lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View v,
                                                    int position, long id) {

                            }
                        });

                    }

                    @Override
                    public void onFailure() {
                        Utility.showFailureDialog(getActivity(), false);
                    }
                };

                ShowCartItemAuth task = new ShowCartItemAuth(getActivity(), ProductListener);
                task.execute(jsonArrayItem);

            } else {
                Utility.showValidateDialog(
                        getResources().getString(R.string.failure_ws),
                        getActivity());
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentProductCart).commit();

            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!new StoreData(getActivity()).getAuthName().equals("")) {
                        if(new StoreData(getActivity()).getCartAdded() != 0){
                            callCheck();
                            Log.d("uu1", "jjj");

                        }else
                        if (priceProduct(cartItemArrayList) > 10) {
                            dialog();
                            Log.d("uu2", "jjjww");

                        } else {
                            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_total), Toast.LENGTH_LONG).show();
                        }
                    }else {
                        if (bundle != null) {

                            try {
                                if (bundle.getString("CartAuth").equals("NonVisitor")) {
                                    sendNonVisitor();
                                    Log.d("uu3", "jjj");

                                } else {
                                    callCheck();
                                    Log.d("uu4", "erreer");
                                    Log.d("ii11","pp");

                                }
                            }catch (Exception e){
                                if (priceProduct(databaseHelper.getCart()) > 10) {
                                    dialog();
                                    Log.d("uu5", "fddf");

                                } else {
                                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_total), Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Log.d("ii22","pp");

                            if (priceProduct(databaseHelper.getCart()) > 10) {
                                dialog();
                                Log.d("uu5", "fddf");

                            } else {
                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_total), Toast.LENGTH_LONG).show();
                            }
                        }
                    }  }catch (Exception e){
                    Log.d("uu",e.toString());

                    }
            }
        });

         return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });

        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
       }

    @Override
    public void onAddCart(CartQuantity cartQuantity, int num,boolean watch,double price) {
        pricessss +=price;
          if(watch==true){
            FragmentProductCart  fragment =   new FragmentProductCart();
            Bundle bundle = new Bundle();
             bundle.putDouble("price",pricessss);
        if(bundle.getDouble("price")==0){
         tvTotal.setText(String.format("%.3f", price));

            }
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                    .replace(R.id.mainFragment, fragment).commit();
        }
     }
    private void dialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.cart_dialog);
        dialog.setTitle(getResources().getString(R.string.dialog_buy));

        // set the custom dialog components - text, image and button

        Button btnNotAuth = (Button) dialog.findViewById(R.id.btnNotAuth);
        // if button is clicked, close the custom dialog
        btnNotAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartNotAuth.class);
                 startActivity(intent);

                dialog.dismiss();
            }
        });
        Button btnAuth = (Button) dialog.findViewById(R.id.btnAuthCart);
        // if button is clicked, close the custom dialog
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!new StoreData(getActivity()).getAuthName().equals("")){
                    callCheck();
                }else{
                    Intent intent = new Intent(getActivity(), SignIn.class);
                    intent.putExtra("CartAuth","CartAuth");
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

private void callCheck(){
    if (Utility.isNetworkConnected(getActivity())) {

        ProductListener = new OnProcessCompleteListener() {

            @Override
            public void onSuccess(Object result) {
                checkResponse = (ResponseOfCheckOut) result;
                checkOutDatas =   checkResponse.getData();
                checkCart = checkOutDatas.getProducts();
                Toast.makeText(getActivity(),checkOutDatas.getMessage(),Toast.LENGTH_LONG).show();
                databaseHelper.delete();
                databaseHelper.deleteCart();
                databaseHelper.deleteCartAdd();
                new StoreData(getActivity()).saveCartAdded(0);
             }

            @Override
            public void onFailure() {
                Utility.showFailureDialog(getActivity(), false);
            }
        };

        CheckOutForSignUser task = new CheckOutForSignUser(getActivity(), ProductListener);
        task.execute(new StoreData(getActivity()).getAuthName(),new StoreData(getActivity()).getAuthPass());

    } else {
        Utility.showValidateDialog(
                getResources().getString(R.string.failure_ws),
                getActivity());
    }

}
    public double priceProduct(ArrayList<CartQuantity> arrayList){
        double price=0;
        for (int i=0;i<arrayList.size();i++){
            price +=   arrayList.get(i).getPrice()*arrayList.get(i).getcQuantity();
        }
        return price;
    }
    private void sendNonVisitor(){
          saveAuth = (SaveAuth) getActivity().getApplicationContext();
        saveAuth.getJsonVisitor();
        saveAuth.getJsonProduct();
        JSONObject jsonObjectSend  = new JSONObject();

        try {
            jsonObjectSend.put("VisitorData", saveAuth.getJsonVisitor());
            JSONArray jsonArray = new JSONArray();
            if(databaseHelper.getCart()!=null)
                if(databaseHelper.getCart().size()>0)
                    for(int i=0;i<databaseHelper.getCart().size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID",databaseHelper.getCart().get(i).getID());
                jsonObject.put("Quantity",databaseHelper.getCart().get(i).getcQuantity());
                jsonObject.put("CreatedDate",Utility.getCurrentTimeStamp());
                jsonArray.put(jsonObject);
            }
        jsonObjectSend.put("CartItems",jsonArray);
        } catch (JSONException e) {
        e.printStackTrace();
    }
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    checkResponse = (ResponseOfCheckOut) result;
                    checkOutDatas =   checkResponse.getData();
                    checkCart = checkOutDatas.getProducts();
                    Toast.makeText(getActivity(), checkOutDatas.getMessage(),Toast.LENGTH_LONG).show();
                    databaseHelper.deleteCart();
                    databaseHelper.deleteCartAdd();

                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            AddCartItemNotAuth task = new AddCartItemNotAuth(getActivity(), ProductListener);
            task.execute(jsonObjectSend);

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.showSecondaryMenu();
                } catch (Exception e) {

                }
            }
        });
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setVisibility(View.GONE);
        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
        img.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null)
                    mainActivity.toggle();
            }
        });
    }
    @Override
    public void cancel(CartQuantity cartQuantity) {

    try{
       if(databaseHelper.getItem(cartQuantity.getID())!=null) {
        databaseHelper.updateToDo(cartQuantity,0,0);
      }

    }catch (Exception e){

    }
        if(new StoreData(getActivity()).getAuthName().equals("")) {
             try {
                for (int i = 0; i < itemJsonArrayList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("ID", itemJsonArrayList.get(i).getIdItem());

                        jsonObject.put("Quantity", itemJsonArrayList.get(i).getQuantityItem());
                        jsonObject.put("CreatedDate", itemJsonArrayList.get(i).getTimeItem());
                        jsonArrayItem.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                databaseHelper.deleteCart(cartQuantity.getID());
                if(databaseHelper.getCart()!=null&&databaseHelper.getCart().size()>0){
                CartGridAdapter cartGridAdapter = new CartGridAdapter(getActivity(),databaseHelper.getCart(), onCartListener, onCancelOrder,onAddItem);
                cartGridAdapter.notifyDataSetChanged();
                lstProduct.setAdapter(cartGridAdapter);}else{
                    lstProduct.setAdapter(null);
                }

                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(cartQuantity.getID());
                saveAuth.setCartQuanPos(arrayList);
                cancelApi(cartQuantity);
              HashSet<ItemJson> hash= saveAuth.getItemJsons();
                hash.remove(cartQuantity);
                saveAuth.setItemJsons(hash);

             }catch (Exception e){

            }
        }else{


             cancelApiAuth(cartQuantity.getID());
            databaseHelper.updateToDo(cartQuantity,0,0);

        }

    }
    private void cancelApiAuth(final int id){
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    DeleteProduct deleteProduct = (DeleteProduct) result;
                    Toast.makeText(getActivity(),deleteProduct.getData(),Toast.LENGTH_LONG).show();
                    ProductListener = new OnProcessCompleteListener() {

                        @Override
                        public void onSuccess(Object result) {
                            cartItemResponse = (CartItemResponse) result;
                            cartItemArrayList=   cartItemResponse.getData();
//                            cartItemArrayList.remove(saveAuth.getCancelPosition());
                            databaseHelper.deleteCartAddd(id);
                            if(priceProduct(cartItemArrayList)>0){
                                priceProduct(cartItemArrayList);

                                tvFinalTotal.setText(""+(priceProduct(cartItemArrayList)+1));

                            }

                            else{
                                tvFinalTotal.setText("");
                                tvTotal.setText("");

                            }

                            CartGridAdapter cartGridAdapter = new CartGridAdapter(getActivity(), cartItemArrayList, onCartListener, onCancelOrder,onAddItem);
                            cartGridAdapter.notifyDataSetChanged();
                            lstProduct.setAdapter(cartGridAdapter);

                            lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View v,
                                                        int position, long id) {

                                }
                            });
                        }

                        @Override
                        public void onFailure() {
                            Utility.showFailureDialog(getActivity(), false);
                        }
                    };

                    ShowCartItemAuth task = new ShowCartItemAuth(getActivity(), ProductListener);
                    task.execute();
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            DeleteCartItem task = new DeleteCartItem(getActivity(), ProductListener);
            task.execute(id+"");

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

    }
    private void cancelApi(final CartQuantity cartQuantity){
        if(databaseHelper.getItem(cartQuantity.getID())!=null){
            CartCheck cartCheck =  databaseHelper.getItem(cartQuantity.getID());
            databaseHelper.deleteCartAddd(cartQuantity.getID());
            checkAdd =  cartCheck.getAdd();
            checkEnter =  cartCheck.getEnter();
            Log.d("iiiooo",cartCheck.getEnter()+"");
            databaseHelper.updateToDo(cartQuantity, 0, 0);

        }
        if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    cartItemResponse = (CartItemResponse) result;
                    cartItemArrayList=   cartItemResponse.getData();

                    Log.d("iiii", saveAuth.getCartQuanDelete() + "");
                    if( databaseHelper.getCart()!=null) {
                        if (databaseHelper.getCart().size() > 0) {
                            priceProduct(databaseHelper.getCart());

                            tvFinalTotal.setText("" + (priceProduct(databaseHelper.getCart()) + 1));

                        } else {
                            tvFinalTotal.setText("");
                            tvTotal.setText("");

                        }

                    }else{
                        tvFinalTotal.setText("");
                        tvTotal.setText("");
                    }

                    lstProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int position, long id) {

                        }
                    });

                 }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            ShowCartItem task = new ShowCartItem(getActivity(), ProductListener);
            task.execute(databaseHelper.getCart());

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

    }
    private ArrayList<CartQuantity> deletedbyId(ArrayList<CartQuantity> cartQuantities){
         for(int i=0;i<cartQuantities.size();i++){
            for (int ii =0;ii<saveAuth.getCartQuanPos().size();ii++)
            if(cartQuantities.get(i).getID()==saveAuth.getCartQuanPos().get(ii)){
             boolean c=   cartQuantities.remove(cartQuantities.get(i));
                 saveAuth.setCartQuan(cartQuantities);
                return cartQuantities;
            }
        }
        return null;
    }

    @Override
    public void add(int num, CartQuantity cartQuantity) {
        cartQuantity.setcQuantity(num);
        if(databaseHelper.getCartItemAdd(cartQuantity.getID())!=null){
            cartQuantity.setcQuantity(num);
            databaseHelper.updateCartAdd(cartQuantity);
        }else{
            databaseHelper.createCartAdd(cartQuantity);
        }
        cartItemArrayList.remove(cartQuantity);
        cartItemArrayList.add(cartQuantity);
   double  pric=   priceProduct(cartItemArrayList) ;
        tvTotal.setText(String.format("%.3f", pric));
        tvFinalTotal.setText(String.format("%.3f", pric+1));
    }
}
