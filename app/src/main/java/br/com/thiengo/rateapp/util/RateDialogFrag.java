package br.com.thiengo.rateapp.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RatingBar;

import br.com.thiengo.rateapp.R;


public class RateDialogFrag extends DialogFragment
        implements RatingBar.OnRatingBarChangeListener,
            View.OnClickListener {

    public static final String KEY = "fragment_rate";


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /*
         * NECESSÁRIO PARA A REMOÇÃO DE BARRA DE TÍTULO EM VERSÕES
         * ANTERIORES A VERSÃO 5 DO ANDROID.
         * */
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        dialog.getWindow().requestFeature( Window.FEATURE_NO_TITLE );
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rate_dialog, container);

        RatingBar rbStars = (RatingBar) view.findViewById(R.id.rb_stars);
        rbStars.setOnRatingBarChangeListener( this );

        View bt = view.findViewById(R.id.bt_never);
        bt.setOnClickListener( this );

        bt = view.findViewById(R.id.bt_later);
        bt.setOnClickListener( this );

        return view;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if( rating >= 4 ){
            RateDialogManager.showRateDialogPlayStore( getActivity() );
            RateSPManager.neverAskAgain( getActivity() );
            dismiss();
        }
        else if( rating > 0 ){
            RateDialogManager.showRateDialogFeedback( getActivity(), rating );
            RateSPManager.updateTime( getActivity() );
            RateSPManager.updateLaunchTimes( getActivity() );
            dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.bt_later ){
            RateSPManager.updateTime( getActivity() );
            RateSPManager.updateLaunchTimes( getActivity() );
        }
        else{
            RateSPManager.neverAskAgain( getActivity() );
        }
        dismiss();
    }
}
