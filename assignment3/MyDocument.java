package no.ntnu.idi.ir;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * The task is to update the given `MyDocument' class (i.e implement the 'Document(File F)' method) to index the following fields per document:
 *
 * - path, the path of the file
 * - from, whatever is stored in the from field of the given message
 * - subject, the subject of the e-mail
 * - contents, the actual e-mail contents
 *
 * All `from', `subject', and `contents' should be searchable, i.e. store their re-spective term vectors. 
 * Look at the given `NewsDocument' class that reads a text file and has better methods for `from', `subject', and `contents'.
 *
 * =============
 *
 * Lucene official documentation:
 * http://lucene.apache.org/core/4_10_1/index.html
 *
 * Useful resources: 
 *   http://oak.cs.ucla.edu/cs144/projects/lucene/
 *   http://lingpipe-blog.com/2014/03/08/lucene-4-essentials-for-text-search-and-indexing/
 * 
 */


public class MyDocument{

    /**
     * Create a indexed document that can be used to search on subject, body and from.
    */
	public static Document Document (File f) throws java.io.FileNotFoundException{

        // make a new, empty document
        Document doc = new Document();

        // use the news document wrapper
        NewsDocument newsDocument = new NewsDocument(f);

        //TODO: create structured lucene document

        /**
         * Create a new field for each of the given fields
         * Use the get methods in the NewsDocument class to retrieve the different strings for the fields
         * Every field is stored
         * Then we index the tokens produced by running the field's value through an Analyzer.
         * In the id field we also separately disable the storing of norms.
         *
         * We then add each of the fields to the document
         * Once we are done, we return the document
        */

        Field id = new Field("id", newsDocument.getId(), Field.Store.YES, Field.Index.ANALYZED_NO_NORMS);
        Field from = new Field("from", newsDocument.getFrom(), Field.Store.YES, Field.Index.ANALYZED);
        Field subject = new Field("subject", newsDocument.getSubject(), Field.Store.YES, Field.Index.ANALYZED);
        Field contents = new Field("contents", newsDocument.getContent(), Field.Store.YES, Field.Index.ANALYZED);

        doc.add(id);
        doc.add(from);
        doc.add(subject);
        doc.add(contents);

		return doc;
	}

}
