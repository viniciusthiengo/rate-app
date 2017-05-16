package br.com.thiengo.rateapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.morsebyte.shailesh.twostagerating.FeedbackWithRatingReceivedListener;
import com.morsebyte.shailesh.twostagerating.Settings;
import com.morsebyte.shailesh.twostagerating.TwoStageRate;


public class Util {

    public static void rateBox(final Context context){
        TwoStageRate twoStageRate = TwoStageRate.with(context);

        /*
         * APRESENTANDO O ÍCONE DO APLICATIVO NA PRIMEIRA
         * DIALOG DA RATINGBOX SE O VALOR FOR true. CASO
         * CONTRÁRIO O ÍCONE NÃO É APRESENTADO. O VALOR PADRÃO
         * É true.
         * */
        twoStageRate
            .setShowAppIcon(true);

        /*
         * NÚMERO MÍNIMO DE ESTRELAS QUE ATIVARIA O DIALOG QUE
         * PERMITI A ABERTURA DA STORE PARA A AVALIAÇÃO.
         * ABAIXO DESTE NÚMERO, UM DIALOG PARA FEEDBACK VIA
         * EMAIL, PARA OS DESENVOLVEDORES DO APLICATIVO, SERÁ
         * ABERTO. O VALOR PADRÃO É 4.
         * */
        twoStageRate
            .setThresholdRating(2);

        /*
         * CONDIÇÃO PARA QUE A RATE BOX SEJA APRESENTADA.
         * VALORES PADRÃO SÃO: 5 DIAS; 5 ABERTURAS E 5 EVENTOS
         * PERSONALIZADOS. ESSE ÚLTIMO É CONTADO DEVIDO AS
         * IVOCAÇÕES AO MÉTODO incrementEvent().
         * */
        twoStageRate
            .setInstallDays(0) // OU A CADA 5 DIAS
            .setLaunchTimes(1) // OU A CADA 1 ABERTURA DO APP
            .setEventsTimes(1); // OU A CADA 1 EVENTO PERSONALIZADO OCORRIDO

        /*
         * TIPO DA STORE. PODEMOS OPTAR PELA GOOGLE_PLAY (PADRÃO)
         * OU PELA AMAZON.
         * */
        twoStageRate
            .setStoreType(Settings.StoreType.GOOGLEPLAY);

        /*
         * TEXTO E RÓTULOS QUE SERÃO APRESENTADOS NA PRIMEIRA
         * DIALOG DO RATE BOX.
         * */
        twoStageRate
            .setRatePromptTitle("O que está achando do aplicativo?")
            .setRatePromptLaterText("Mais tarde")
            .setRatePromptNeverText("Não, obrigado")
            .setRatePromptDismissible(false);

        /*
         * TEXTOS E RÓTULOS QUE SERÃO APRESENTADOS NO SEGUNDO
         * DIALOG, DEPOIS DE O USUÁRIO TER INFORMADO AO MENOS
         * 4 ESTRELAS NA RATINGBAR.
         * */
        twoStageRate.setConfirmRateDialogTitle("Confirmar avaliação")
                .setConfirmRateDialogDescription("Gostaria de colocar sua avaliação na Play Store? Isso nos motivaria a continuar evoluindo o aplicativo.")
                .setConfirmRateDialogPositiveText("Sim")
                .setConfirmRateDialogNegativeText("Não, Obrigado!")
                .setConfirmRateDialogDismissible(true);

        /*
         * TEXTOS E RÓTULOS QUE SERÃO APRESENTADOS NO SEGUNDO
         * DIALOG, DEPOIS DE O USUÁRIO TER INFORMADO MENOS
         * DO QUE ThresholdRating() ESTRELAS NA RATINGBAR.
         * ESSE FEEDBACK É ENVIADO A NÓS, ELE NÃO SERÁ
         * SOLICITADO A INFORMAR NA STORE, POIS MENOS DO QUE
         * ThresholdRating() ESTRLEAS É ALGO RUIM EM NOSSO
         * DOMÍNIO DO PROBLEMA.
         * */
        twoStageRate.setFeedbackDialogTitle("Em que podemos evoluir?")
            .setFeedbackDialogDescription("Por favor, nos informe os problemas encontrados por ti no aplicativo, assim poderemos melhora-lo.")
            .setFeedbackDialogPositiveText("Enviar")
            .setFeedbackDialogNegativeText("Não, obrigado!")
            .setFeedbackDialogDismissible(false)
            .setFeedbackWithRatingReceivedListener(new FeedbackWithRatingReceivedListener() {
                @Override
                public void onFeedbackReceived(float rating, String feedback) {

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"thiengocalopsita@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Avaliação aplicativo");
                    intent.putExtra(Intent.EXTRA_TEXT, "Estrelas: "+rating+"\n\nAvaliação: "+feedback);
                    context.startActivity(Intent.createChooser(intent, "Enviar e-mail"));
                }
            });

        twoStageRate.showIfMeetsConditions();
    }
}
