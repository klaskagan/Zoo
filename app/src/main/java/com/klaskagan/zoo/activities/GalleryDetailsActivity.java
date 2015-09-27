package com.klaskagan.zoo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.klaskagan.zoo.R;
import com.squareup.picasso.Picasso;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/27.
 */
public class GalleryDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_CAPTION = "extra_caption";
    private TextView mCaptionTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        mCaptionTextView = (TextView) findViewById(R.id.caption);
        mImageView = (ImageView) findViewById(R.id.image);

        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(EXTRA_IMAGE)) {
                Picasso.with(this).load(getIntent().getExtras().getString(EXTRA_IMAGE)).into(mImageView);
            }

            if (getIntent().getExtras().containsKey(EXTRA_CAPTION)) {
                mCaptionTextView.setText(getIntent().getExtras().getString(EXTRA_CAPTION));
            }
        }
    }
}
