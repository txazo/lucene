package org.apache.lucene.test;

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

        Document document = new Document();
        document.add(new IntField("id", 2, new IndexFieldType()));
        document.add(new TextField("name", "root", Field.Store.YES));

        long docId = writer.addDocument(document);
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
