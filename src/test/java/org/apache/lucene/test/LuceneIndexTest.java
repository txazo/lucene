package org.apache.lucene.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

public class LuceneIndexTest {

    @Test
    public void test() throws Exception {
        Directory directory = FSDirectory.open(new File("E:/test/LuceneDB").toPath());
        IndexWriterConfig config = new IndexWriterConfig();
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter writer = new IndexWriter(directory, config);

        for (int i = 1; i <= 100000; i++) {
            Document document = new Document();
            document.add(new StringField("id", String.valueOf(i), Field.Store.YES));
            document.add(new TextField("name", RandomStringUtils.random(100, "abcde "), Field.Store.YES));
            document.add(new TextField("value", RandomStringUtils.random(100, "abcde "), Field.Store.YES));

            long docId = writer.addDocument(document);
            System.out.println(String.format("%d %d", i, docId));
        }

        writer.flush();
        writer.commit();
        writer.close();
        directory.close();
    }

    private static class IntField extends Field {

        protected IntField(String name, int value, IndexableFieldType type) {
            super(name, String.valueOf(value), type);
        }

    }

    private static class IndexFieldType extends FieldType {

        public IndexFieldType() {
            super();
            setIndexOptions(IndexOptions.DOCS);
            setDocValuesType(DocValuesType.NUMERIC);
            setStored(true);
            setTokenized(false);
        }
    }

}
