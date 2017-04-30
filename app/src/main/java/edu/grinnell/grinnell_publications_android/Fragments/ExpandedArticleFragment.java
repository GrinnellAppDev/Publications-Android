package edu.grinnell.grinnell_publications_android.Fragments;

        import android.graphics.drawable.Drawable;
        import android.support.annotation.Nullable;
        import android.os.Bundle;

        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;

        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
        import edu.grinnell.grinnell_publications_android.Services.Implementation.RealmLocalClient;
        import edu.grinnell.grinnell_publications_android.Models.Interfaces.UserInterface;
        import edu.grinnell.grinnell_publications_android.R;

        import java.io.InputStream;
        import java.net.URL;

        import static android.support.design.widget.Snackbar.make;
        import static android.support.v4.content.ContextCompat.getDrawable;
        import static android.support.design.widget.Snackbar.LENGTH_SHORT;

/**
 * Fragment displays articles and allows users to mark article as favorited.
 *
 * @author Yazan Kittaneh
 */

public class ExpandedArticleFragment extends Fragment implements UserInterface {

    private ImageView mHeaderImage;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private FloatingActionButton mFavoriteButton;
    private TextView mArticleContent;
    private Toolbar mArticleToolbar;
    private TextView mAuthor;
    private static Story mStory;


    private boolean isFavorited = false;
    private int colorFilter;
    private String snackBarMessage;

    private LinearLayout mArticleDetail1 =
            (LinearLayout) getActivity().findViewById(R.id.article_detail1);
    private TextView mArticleAuthor;
    private ImageView mAuthorImage;
    private LinearLayout mArticleDetail2 =
            (LinearLayout) getActivity().findViewById(R.id.article_detail2);
    private TextView mPublisher;
    private ImageView mPublisherIcon;
    private LinearLayout mArticleDetail3 =
            (LinearLayout) getActivity().findViewById(R.id.article_detail3);
    private TextView mDatePublished;
    private ImageView mPublishedIcon;

    public ExpandedArticleFragment() {
    }

    public static ExpandedArticleFragment newInstance(Story story) {
        ExpandedArticleFragment articleFragment = new ExpandedArticleFragment();
        mStory = story;
        return articleFragment;
    }

    /* http://stackoverflow.com/questions/6407324/how-to-display-image-from-url-on-android */
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public static ExpandedArticleFragment newInstance() {
        return new ExpandedArticleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View expandedArticleFragment =
                inflater.inflate(R.layout.fragment_extended_article, container, false);
        initializeUI(expandedArticleFragment);
        return expandedArticleFragment;
    }

    @Override
    public void initializeUI(View view) {
        mArticleToolbar.setNavigationIcon(getDrawable(getContext(), R.drawable.ic_action_back));

        mArticleAuthor = (TextView) mArticleDetail1.findViewById(R.id.article_detail_text);
        mArticleAuthor.setText(mStory.getAuthor().get(0).getFullName());
        mAuthorImage = (ImageView) mArticleDetail1.findViewById(R.id.article_detail_image);
        mAuthorImage.setImageResource(R.drawable.ic_person_black_24dp);

        mPublisher = (TextView) mArticleDetail2.findViewById(R.id.article_detail_text);
        mPublisher.setText(mStory.getPublication().getPublicationName());
        mPublisherIcon = (ImageView) mArticleDetail2.findViewById(R.id.article_detail_image);
        mPublisherIcon.setImageResource(R.drawable.ic_picture_in_picture_black_24dp);

        mDatePublished = (TextView) mArticleDetail3.findViewById(R.id.article_detail_text);
        mDatePublished.setText(mStory.getPublicationDate());
        mPublishedIcon = (ImageView) mArticleDetail3.findViewById(R.id.article_detail_image);
        mPublishedIcon.setImageResource(R.drawable.ic_event_black_24dp);

        mHeaderImage.setImageDrawable(LoadImageFromWebOperations(mStory.getThumbnailUrl()));

        bindView(view);
        loadPlaceHolderData();
        setOnClickListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHeaderImage = null;
        mCollapsingToolbar = null;
        mFavoriteButton = null;
        mArticleContent = null;
        mArticleToolbar = null;
    }

    private void bindView(View view) {
        mHeaderImage = (ImageView) view.findViewById(R.id.header_image);
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        mFavoriteButton = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        mArticleToolbar = (Toolbar) view.findViewById(R.id.article_toolbar);
    }

    public void setOnClickListeners() {
        mArticleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBookmark(mStory.getPublication(), mStory.getArticleId());
                make(view, getText(R.string.article_sample_favorited), LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Fills fragment with placeholder content
     */
    private void loadPlaceHolderData() {
        Drawable defaultImage = getDrawable(getContext(), R.drawable.grinnell_gates);
        setHeaderText(getText(R.string.article_sample_title).toString());
        setContentText(getText(R.string.article_sample_content).toString());
        setHeaderImage(defaultImage);
    }

    /**
     * Gets image from Header
     **/
    public Drawable getHeaderImage() {
        return this.mHeaderImage.getDrawable();
    }

    /**
     * Gets text from content
     **/
    public String getTextContent() {
        return this.mArticleContent.getText().toString();
    }

    /**
     * Sets the heading image
     **/
    private void setHeaderImage(Drawable image) {
        this.mHeaderImage.setImageDrawable(image);
    }

    /**
     * Sets text into header field
     **/
    private void setHeaderText(String titleText) {
        this.mCollapsingToolbar.setTitle(titleText);
    }

    /**
     * Sets text into content field
     **/
    private void setContentText(String content) {
        this.mArticleContent.setText(content);
    }

    private void addBookmark(String publication_id, String article_id) {
        RealmLocalClient client = new RealmLocalClient();
        client.addBookmark(publication_id, article_id);
    }
}
