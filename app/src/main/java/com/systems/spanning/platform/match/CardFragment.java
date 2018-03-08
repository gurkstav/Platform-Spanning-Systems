package com.systems.spanning.platform.match;

import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CardFragment extends DialogFragment{
    private Match match;
    ImageView background;
    TextView activityType;
    TextView activityLocation;
    TextView activityDate;
    TextView activityTime;
    TextView activityMin;
    TextView activityMax;
    TextView activityDescription;
    Button backToActivities;
    Button sendEmail;




    static CardFragment newInstance(Match match){
        CardFragment cf = new CardFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putParcelable("Match", match);
        cf.setArguments(args);
        return cf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            match = getArguments().getParcelable("Match");
        }
    }

    @Override
    public int show(android.support.v4.app.FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_fragment, container, false);
        background = v.findViewById(R.id.background_image_view);
        activityType = v.findViewById(R.id.activityType);
        activityLocation = v.findViewById(R.id.activityLocation);
        activityDate = v.findViewById(R.id.activityDate);
        activityTime = v.findViewById(R.id.activityTime);
        activityMin = v.findViewById(R.id.activityMinPart);
        activityMax = v.findViewById(R.id.activityMaxPart);
        activityDescription = v.findViewById(R.id.activityDescription);
        backToActivities = v.findViewById(R.id.back_to_activities);
        sendEmail = v.findViewById(R.id.button_contact);

        Resources res = getContext().getResources();
        String type = String.format(res.getString(R.string.activity_type), match.getType());
        String date = String.format(res.getString(R.string.activity_date), match.getDate());
       // String time = String.format(res.getString(R.string.activity_time), match.getTime());
        String location = String.format(res.getString(R.string.activity_location), match.getLocation());
        String max = String.format(res.getString(R.string.max_participants), match.getMax_participants());
        String min = String.format(res.getString(R.string.min_participants), match.getMin_participants());
       // String description = String.format(res.getString(R.string.activity_description), match.getDescription());

        String email = String.format(res.getString(R.string.userEmail), match.getEmail());

        activityType.setText(type);
        activityLocation.setText(location);
        activityDate.setText(date);
       // activityTime.setText(time);
        activityMin.setText(min);
        activityMax.setText(max);
       // activityDescription.setText(description);

        switch(match.getType()){
            case "Sport":
                background.setBackgroundResource(R.drawable.sport_card);
                break;
            case "Events":
                background.setBackgroundResource(R.drawable.event_card);
                break;
            case "Music":
                background.setBackgroundResource(R.drawable.music_card);
                break;
            case "Games":
                background.setBackgroundResource(R.drawable.games_card);
                break;
            case "Others":
                background.setBackgroundResource(R.drawable.other_card);
                break;
            case "Outdoor":
                background.setBackgroundResource(R.drawable.outdoor_card);
                break;
            case "Shopping":
                background.setBackgroundResource(R.drawable.shopping_card);
                break;
            default:
                background.setBackgroundResource(R.drawable.other_card);
                break;
        }

        backToActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return v;
    }
}