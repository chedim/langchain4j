package dev.langchain4j.store.embedding.couchbase;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreWithRemovalIT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

@EnabledIfEnvironmentVariable(named = "COUCHBASE_CLUSTER_URL", matches = ".+")
public class CouchbaseEmbeddingStoreWithRemovalCloudIT extends EmbeddingStoreWithRemovalIT {
    @Override
    protected EmbeddingStore<TextSegment> embeddingStore() {
        return CouchbaseTestUtils.cloudStore();
    }

    @Override
    protected EmbeddingModel embeddingModel() {
        return CouchbaseTestUtils.embeddingModel();
    }

    @Test
    @Disabled("should be enabled once implemented")
    void should_remove_all_by_filter() {
    }

    @Test
    @Disabled("should be enabled once implemented")
    void should_fail_to_remove_all_by_filter_null() {
    }

    @BeforeEach
    protected void clearStore() {
        embeddingStore().removeAll();
    }
}
