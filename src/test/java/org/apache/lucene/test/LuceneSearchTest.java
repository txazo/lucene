package org.apache.lucene.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

public class LuceneSearchTest {

    @Test
    public void search() throws Exception {
        Directory directory = FSDirectory.open(new File("E:/test/LuceneDB").toPath());
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = MultiFieldQueryParser.parse(new String[]{"abc", "abc"}, new String[]{"name", "value"}, new StandardAnalyzer());
        TopDocs docs = searcher.search(query, 100);
    }

}
