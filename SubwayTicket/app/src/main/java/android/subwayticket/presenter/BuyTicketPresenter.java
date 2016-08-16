package android.subwayticket.presenter;

import android.content.Context;
import android.subwayticket.bean.BuyTicketStation;
import android.subwayticket.model.BuyTicketStationImpl;
import android.subwayticket.model.IModel.IBuyTicketStationModel;
import android.subwayticket.view.IView.IBuyTicketView;
import android.subwayticket.view.IView.IGetPriceView;
import android.util.Log;


import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lilingyong on 16/8/13.
 */
public class BuyTicketPresenter {
    private IBuyTicketStationModel iBuyTicketStationModel;
    private IBuyTicketView iBuyTicketView;
    private IGetPriceView iGetPriceView;
    private Context context;

    public BuyTicketPresenter(IBuyTicketView iBuyTicketView, Context context) {
        this.iBuyTicketView = iBuyTicketView;
        this.context = context;
        iBuyTicketStationModel =new BuyTicketStationImpl();
    }

    public BuyTicketPresenter(IGetPriceView iGetPriceView, Context context) {
        this.iGetPriceView = iGetPriceView;
        this.context = context;
        iBuyTicketStationModel =new BuyTicketStationImpl();
    }

    public void getPoint(String startstation, String endstation){
        iBuyTicketStationModel.getStartAndEndPoint(startstation,endstation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BuyTicketStation>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BuyTicketStation buyTicketStation) {
                        iGetPriceView.setPrice(buyTicketStation.getPrice());

                    }
                });
    }
    public void getPrice(String distance){
        iGetPriceView.showGetPriceLoad();
        iBuyTicketStationModel.getPrice(distance)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BuyTicketStation>() {
                    @Override
                    public void onCompleted() {
                        iGetPriceView.hideGetPriceLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BuyTicketStation buyTicketStation) {
                        Log.i("ggggggggggg",buyTicketStation.getPrice()+"");
                        iGetPriceView.setPrice(buyTicketStation.getPrice());
                    }
                });
    }

    public void buyTicket(final float price,final int userID){
        iBuyTicketView.showBuyTicketLoad();
        iBuyTicketStationModel.buyTicket(price,userID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BuyTicketStation>() {
                    @Override
                    public void onCompleted() {
                        iBuyTicketView.hideBuyTicketLoad();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BuyTicketStation buyTicketStation) {
                        iBuyTicketView.buyTicketSuccess();
                    }
                });
    }


}
