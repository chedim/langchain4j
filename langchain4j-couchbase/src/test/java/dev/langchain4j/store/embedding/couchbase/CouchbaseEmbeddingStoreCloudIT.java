package dev.langchain4j.store.embedding.couchbase;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

@EnabledIfEnvironmentVariable(named = "COUCHBASE_CLUSTER_URL", matches = ".+")
class CouchbaseEmbeddingStoreCloudIT extends EmbeddingStoreIT {

    EmbeddingStore<TextSegment> embeddingStore = new CouchbaseEmbeddingStore.Builder(System.getenv("COUCHBASE_CLUSTER_URL"))
            .username(System.getenv("COUCHBASE_USERNAME"))
            .password(System.getenv("COUCHBASE_PASSWORD"))
            .bucketName(System.getenv("COUCHBASE_BUCKET"))
            .scopeName(System.getenv("COUCHBASE_SCOPE"))
            .collectionName(System.getenv("COUCHBASE_COLLECTION"))
            .searchIndexName(System.getenv("COUCHBASE_FTS_INDEX"))
            .dimensions(CouchbaseEmbeddingStoreIT.TEST_DIMENSIONS)
            .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();

    @Override
    protected EmbeddingStore<TextSegment> embeddingStore() {
        return embeddingStore;
    }

    @Override
    protected EmbeddingModel embeddingModel() {
        return embeddingModel;
    }

    @Override
    protected void ensureStoreIsEmpty() {
        embeddingStore.removeAll();
    }

    @Override
    @SneakyThrows
    protected void awaitUntilPersisted() {
        Thread.sleep(1000);
    }
}
