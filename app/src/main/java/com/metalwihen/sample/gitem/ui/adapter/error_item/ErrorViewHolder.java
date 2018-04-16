package com.metalwihen.sample.gitem.ui.adapter.error_item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.metalwihen.sample.gitem.R;

/**
 * Created on 16/04/18.
 */

public class ErrorViewHolder extends RecyclerView.ViewHolder {

    private TextView errorText;
    private Button retryButton;

    public ErrorViewHolder(View itemView) {
        super(itemView);

        errorText = (TextView) itemView.findViewById(R.id.error_text);
        retryButton = (Button) itemView.findViewById(R.id.retry_button);
    }

    public TextView getErrorText() {
        return errorText;
    }

    public Button getRetryButton() {
        return retryButton;
    }
}
