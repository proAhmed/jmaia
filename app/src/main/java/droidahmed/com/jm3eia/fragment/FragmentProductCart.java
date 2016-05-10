package droidahmed.com.jm3eia.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import droidahmed.com.jm3eia.R;
import droidahmed.com.jm3eia.account.CartNotAuth;
import droidahmed.com.jm3eia.account.SignIn;
import droidahmed.com.jm3eia.adapter.CartGridAdapter;
import droidahmed.com.jm3eia.api.DeleteCartItem;
import droidahmed.com.jm3eia.api.ShowCartItem;
import droidahmed.com.jm3eia.api.AddCartItemNotAuth;
import droidahmed.com.jm3eia.api.CheckOutForSignUser;
import droidahmed.com.jm3eia.controller.OnCancelOrder;
import droidahmed.com.jm3eia.controller.OnCartListener;
import droidahmed.com.jm3eia.controller.OnProcessCompleteListener;
import droidahmed.com.jm3eia.controller.StoreData;
import droidahmed.com.jm3eia.controller.Utility;
import droidahmed.com.jm3eia.model.AllProducts;
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
public class FragmentProductCart extends Fragment implements OnCartListener ,OnCancelOrder {
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        lstProduct = (GridView) view.findViewById(R.id.cart);
        pro = new ArrayList<>();
        // lstProduct.setAdapter(new CuListAdapter(getActivity(),pro));
            onCartListener = (OnCartListener) this;
            onCancelOrder = (OnCancelOrder) this;

        productCarts = new ArrayList<>();
        productCartItems = new ArrayList<>();
        checkCart  = new ArrayList<>();
        checkOutDatas = new CheckOutData();
        itemJsonArrayList = new ArrayList<>();
 final Bundle bundle = getArguments();
        jsonArrayItem = new JSONArray();
         btnRequest = (Button) view.findViewById(R.id.btnRequest);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        tvDeliver = (TextView) view.findViewById(R.id.tvDeliver);
        tvTotal = (TextView) view.findViewById(R.id.tvTotal);
        tvFinalTotal = (TextView) view.findViewById(R.id.tvFinalTotal);
 //        tvTotal.setText(""+pricessss);
        SaveAuth saveAuth = (SaveAuth) getActivity().getApplicationContext();
        JSONArray jsonArray = saveAuth.getJsonProduct();
        itemJsonArrayList.addAll(saveAuth.getItemJsons());
        for(int i=0;i<itemJsonArrayList.size();i++){
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
         if (Utility.isNetworkConnected(getActivity())) {

            ProductListener = new OnProcessCompleteListener() {

                @Override
                public void onSuccess(Object result) {
                    cartItemResponse = (CartItemResponse) result;
                    cartItemArrayList=   cartItemResponse.getData();
                    priceProduct(cartItemArrayList);
                    tvTotal.setText(""+priceProduct(cartItemArrayList));
                    //   Log.d("iiii",pro.toString());
//                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);

                    lstProduct.setAdapter(new CartGridAdapter(getActivity(), cartItemArrayList, onCartListener,onCancelOrder));

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
            task.execute(jsonArrayItem);

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (bundle!=null) {
                        if(bundle.getString("CartAuth").equals("NonVisitor")){
                            sendNonVisitor();

                        }else{
                            callCheck();

                        }
                    } else if (priceProduct(cartItemArrayList) > 10) {
                        dialog();

                    } else {
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_total), Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    if (priceProduct(cartItemArrayList) > 10) {
                        dialog();

                    } else {
                        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.price_total), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
//        ArrayList<ProductCart> result = new ArrayList<ProductCart>();
//        Set<String> titles = new HashSet<String>();
//
//        for( ProductCart item : productCarts ) {
//            if( titles.add( item.getAllProducts().getAlias()) ) {
//                result.add( item );
//            }
//        }
//        lstProduct.setAdapter(new CartGridAdapter(getActivity(), result, onCartListener));
         return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        MainActivity mainActivity = new MainActivity();
//        mainActivity.ui("Products");
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

        getActivity().findViewById(R.id.logo).setVisibility(View.GONE);
        getActivity().findViewById(R.id.textTitle).setVisibility(View.VISIBLE);
        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
        tv.setText(getResources().getString(R.string.my_cart));
    }

    @Override
    public void onAddCart(int position, int num,boolean watch,double price) {
        pricessss +=price;
        productCarts.get(position).getAllProducts();
        productCartItems.add(new ProductCart(productCarts.get(position).getAllProducts(), num));
        Log.d("uuu", productCartItems.toString());
        if(watch==true){
            FragmentProductCart  fragment =   new FragmentProductCart();
            Bundle bundle = new Bundle();
            bundle.putSerializable("cart",productCartItems);
            bundle.putDouble("price",pricessss);
        if(bundle.getDouble("price")==0){
         tvTotal.setText(String.format("%.2f", price));

            }
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("")
                    .replace(R.id.mainFragment, fragment).commit();
        }
//        if (Utility.isNetworkConnected(getActivity())) {
//
//            ProductListener = new OnProcessCompleteListener() {
//
//                @Override
//                public void onSuccess(Object result) {
//                    mainApi = (MainApi) result;
//                    pros = mainApi.getData();
//                }
//
//                @Override
//                public void onFailure() {
//                    Utility.showFailureDialog(getActivity(), false);
//                }
//            };
//
//            ShowCartItem task = new ShowCartItem(getActivity(), ProductListener);
//            task.execute(String.valueOf(position), String.valueOf(num), Utility.getCurrentTimeStamp());
//
//        } else {
//            Utility.showValidateDialog(
//                    getResources().getString(R.string.failure_ws),
//                    getActivity());
//        }
    }
    private void dialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.cart_dialog);
        dialog.setTitle("Title...");

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
                    intent.putExtra("cart_request","cart_request");
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
        SaveAuth saveAuth = (SaveAuth) getActivity().getApplicationContext();
        saveAuth.getJsonVisitor();
        saveAuth.getJsonProduct();
        JSONObject jsonObjectSend  = new JSONObject();

        try {
            jsonObjectSend.put("VisitorData", saveAuth.getJsonVisitor());

        jsonObjectSend.put("CartItems", saveAuth.getJsonProduct());
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
    public void cancel(int position) {
        cancelApi(itemJsonArrayList.get(position));
    }
    private void cancelApi(ItemJson itemJsons){
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
                            priceProduct(cartItemArrayList);
                            tvTotal.setText(""+priceProduct(cartItemArrayList));
                            //   Log.d("iiii",pro.toString());
//                    Gson gson = new Gson();
//                    String json = gson.toJson(pro);
//                    StoreData storeData = new StoreData(MainActivity.this);
//                    storeData.saveData(json);

                            lstProduct.setAdapter(new CartGridAdapter(getActivity(), cartItemArrayList, onCartListener,onCancelOrder));

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
                    task.execute(jsonArrayItem);
                }

                @Override
                public void onFailure() {
                    Utility.showFailureDialog(getActivity(), false);
                }
            };

            DeleteCartItem task = new DeleteCartItem(getActivity(), ProductListener);
            task.execute(itemJsons.getIdItem()+"");

        } else {
            Utility.showValidateDialog(
                    getResources().getString(R.string.failure_ws),
                    getActivity());
        }

    }
}
