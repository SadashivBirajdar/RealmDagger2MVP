package sample.sadashiv.examplerealmmvp.model.realm;

import java.util.UUID;

import dagger.Module;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import sample.sadashiv.examplerealmmvp.model.Author;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.model.Publisher;
import sample.sadashiv.examplerealmmvp.ui.add.AddBookView;

@Module
public class RealmService {

    Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    public RealmResults<Book> getAllBooks() {
        return mRealm.where(Book.class).findAll();
    }

    public Book getBook(final String bookId) {
        return mRealm.where(Book.class).equalTo("id", bookId).findFirst();
    }

    public RealmList<Book> getPublisherBooks(final String publisher) {
        Publisher foundPublisher = getPublisher(publisher, mRealm);
        return foundPublisher == null ? new RealmList<Book>() : foundPublisher.getBooks();
    }

    public RealmList<Book> getAuthorBooks(final String author) {
        Author foundAuthor = getAuthor(splitAuthorName(author), mRealm);
        return foundAuthor == null ? new RealmList<Book>() : foundAuthor.getBooks();
    }

    public void addBookAsync(final String title, final String author, final String isbn, final String publisher,
            final AddBookView addBookView) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Book book = realm.createObject(Book.class, UUID.randomUUID().toString());
                    book.setTitle(title);
                    book.setAuthor(createOrGetAuthor(author, book, realm));
                    book.setPublisher(createOrGetPublisher(publisher, book, realm));
                    book.setIsbn(isbn);
                    if(addBookView != null){
                        addBookView.finish();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    if(addBookView != null){
                        addBookView.showAddBookError();
                    }
                }
            }
        });
    }

    private Author createOrGetAuthor(final String author, final Book book, final Realm realm) {
        String[] authorName = splitAuthorName(author);
        Author foundAuthor = getAuthor(authorName, realm);
        if(foundAuthor == null) {
            foundAuthor = addAuthor(authorName, realm);
        }
        foundAuthor.getBooks().add(book);
        return foundAuthor;
    }

    private Author addAuthor(final String[] authorName, final Realm realm) {
        String authorID = UUID.randomUUID().toString();
        Author author = realm.createObject(Author.class, authorID);
        author.setName(authorName[0]);
        if(authorName.length >= 2) {
            author.setLastname(authorName[1]);
        } else {
            author.setLastname("");
        }
        return author;
    }

    private Publisher createOrGetPublisher(final String publisher, final Book book, final Realm realm) {
        Publisher foundPublisher = getPublisher(publisher, realm);
        if(foundPublisher == null) {
            foundPublisher = addPublisher(publisher, realm);
        }
        foundPublisher.getBooks().add(book);
        return foundPublisher;
    }

    private Publisher addPublisher(final String publisherName, final Realm realm) {
        String publisherID = UUID.randomUUID().toString();
        Publisher publisher = realm.createObject(Publisher.class, publisherID);
        publisher.setName(publisherName);
        return publisher;
    }

    private Author getAuthor(final String[] authorName, final Realm realm) {
        if(authorName.length >= 2) {
            return realm.where(Author.class).equalTo("name", authorName[0]).equalTo("lastname", authorName[1]).findFirst();
        } else {
            return realm.where(Author.class).equalTo("name", authorName[0]).findFirst();
        }
    }

    private String[] splitAuthorName(final String author) {
        return author.split(" ");
    }

    private Publisher getPublisher(final String publisher, final Realm realm) {
        return realm.where(Publisher.class).equalTo("name", publisher).findFirst();
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Exception e);
    }
}
