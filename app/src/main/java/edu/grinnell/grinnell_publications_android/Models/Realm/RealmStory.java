package edu.grinnell.grinnell_publications_android.Models.Realm;

import edu.grinnell.grinnell_publications_android.Models.Interfaces.Publication;
import edu.grinnell.grinnell_publications_android.Models.Interfaces.Story;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * This class implements @code{Story} using Realm Persistence
 *
 * @author Albert Owusu-Asare, Jemuel Santos
 * @see Story
 * @see RealmObject
 * @since 1.1 Fri May  6 10:01:38 CDT 2016
 */
public class RealmStory extends RealmObject implements Story {
  private String mDatePublished;
  private String mBrief;
  private String mHeaderImage;
  private String mPublication;
  private String mPublicationId;
  private String mDateEdited;
  private String mArticleId;
  private String mTitle;
  private String mContent;
  private RealmList<RealmAuthor> mAuthors;

  /* Default constructor for Realm */
  public RealmStory() {
  }

  public RealmStory(String mDatePublished,
                    String mBrief,
                    String mHeaderImage,
                    String mPublication,
                    String mPublicationId,
                    String mDateEdited,
                    String mArticleId,
                    String mTitle,
                    String mContent,
                    RealmList mAuthors) {
    this.mDatePublished = mDatePublished;
    this.mBrief = mBrief;
    this.mHeaderImage = mHeaderImage;
    this.mPublication = mPublication;
    this.mPublicationId = mPublicationId;
    this.mDateEdited = mDateEdited;
    this.mArticleId = mArticleId;
    this.mTitle = mTitle;
    this.mContent = mContent;
    this.mAuthors = mAuthors;
  }

  /** Setters */
  public void setPublicationId(String publicationId) {
    if (publicationId.isEmpty() || publicationId == null) {
      return;
    }
    this.mPublicationId = publicationId;
  }

  /**
   * Sets the story's date of publication.
   * @param datePublished date when the story was published
   */
  public void setDatePublished(String datePublished) {
    if (datePublished.isEmpty() || datePublished == null) {
      return;
    }
    this.mDatePublished = datePublished;
  }

  /**
   * Sets when the story was edited
   * @param dateEdited date when story was edited
   */
  public void setDateEdited(String dateEdited) {
    if (dateEdited.isEmpty() || dateEdited == null) {
      return;
    }
    this.mDateEdited = dateEdited;
  }

  /**
   * Sets who the authors of the story are
   * @param authors authors of the story
   */
  public void setAuthors(RealmList<RealmAuthor> authors) {
    if (authors.isEmpty() || authors == null) {
      return;
    }
    this.mAuthors = authors;
  }

  /**
   * Sets the content of the story
   * @param content the content of the story
   */
  public void setContent(String content) {
    if (content.isEmpty() || content == null) {
      return;
    }
    this.mContent = content;
  }

  /**
   * Sets the brief summary of the story
   * @param brief short summary of the story
   */
  public void setBrief(String brief) {
    if (brief.isEmpty() || brief == null) {
      return;
    }
    this.mBrief = brief;
  }

  /**
   * Sets the title of the story
   * @param title title of the story
   */
  public void setTitle(String title) {
    if (title.isEmpty() || title == null) {
      return;
    }
    this.mTitle = title;
  }

  /**
   * Sets the ID of the given article
   * @param articleId string that represents the ID of the article
   */
  public void setArticleId(String articleId) { //changed from int to string
    if (articleId == "0") {
      return;
    }
    this.mArticleId = articleId;
  }

  /**
   * Sets the header image for the story
   * @param headerImage string that is used as the header image of the story
   */
  public void setHeaderImage(String headerImage) {
    if (headerImage.isEmpty() || headerImage == null) {
      return;
    }
    this.mHeaderImage = headerImage;
  }

  /** Getters */
  public String getPublication() {
    return this.mPublication;
  }

  public String getPublicationId() {
    return this.mPublicationId;
  }

  @Override public String getDatePublished() {
    return this.mDatePublished;
  }

  @Override public String getDateEdited() {
    return this.mDateEdited;
  }

  @Override public RealmList<RealmAuthor> getAuthors() {
    return this.mAuthors;
  }

  @Override public String getContent() {
    return this.mContent;
  }

  @Override public String getBrief() {
    return this.mBrief;
  }

  @Override public String getTitle() {
    return this.mTitle;
  }

  @Override public String getArticleId() {
    return this.mArticleId;
  }

  @Override public String getHeaderImage() {
    return this.mHeaderImage;
  }
}
