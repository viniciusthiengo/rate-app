package br.com.thiengo.rateapp.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


public class RateDialogManager {

    public static void showRateDialog(Context context, Bundle savedInstanceState) {

        RateSPManager.updateLaunchTimes( context, savedInstanceState );
        FragmentManager fm = getFragManager( context );

        if( RateSPManager.canShowDialog( context )
                && fm.findFragmentByTag(RateDialogFrag.KEY) == null ){

            RateDialogFrag dialog = new RateDialogFrag();
            dialog.setCancelable(false);
            dialog.show( fm, RateDialogFrag.KEY );
        }
    }

    public static void showRateDialogPlayStore( Context context ) {
        FragmentManager fm = getFragManager( context );

        RateDialogFrag dialog = new RateDialogPlayStoreFrag();
        dialog.setCancelable(false);
        dialog.show( fm, RateDialogFrag.KEY );
    }

    public static void showRateDialogFeedback( Context context, float rating ) {
        FragmentManager fm = getFragManager( context );

        RateDialogFeedbackFrag dialog = new RateDialogFeedbackFrag();
        dialog.setRating( rating );
        dialog.setCancelable(false);
        dialog.show( fm, RateDialogFrag.KEY );
    }

    private static FragmentManager getFragManager( Context context ){
        AppCompatActivity activity = (AppCompatActivity) context;
        return activity.getSupportFragmentManager();
    }
}
